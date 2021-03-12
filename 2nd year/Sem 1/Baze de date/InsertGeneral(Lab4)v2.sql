USE Sport
GO

DROP TABLE Team
DROP TABLE Nutritionist
DROP TABLE Sportive

CREATE TABLE Team
	(TeamId INT PRIMARY KEY,
	TeamName VARCHAR(20))
GO

CREATE TABLE Nutritionist
	(NutritionistId INT PRIMARY KEY,
	NutritionistName VARCHAR(20),
	YearsOfExperience INT)
GO

CREATE TABLE Sportive
	(SportiveId INT PRIMARY KEY ,
	FirstName VARCHAR(50),
	LastName VARCHAR(20),
	DOB DATE,
	TeamId INT FOREIGN KEY REFERENCES Team(TeamId) ON DELETE CASCADE,
	NutritionistId INT FOREIGN KEY REFERENCES Nutritionist(NutritionistId) ON DELETE CASCADE)
GO	

	

CREATE TABLE LogTable(
	OpType VARCHAR(100),
	OpDate DATETIME,
	TableName VARCHAR(100),
	numberOfRows INT
)
GO

--1
CREATE OR ALTER FUNCTION checkNumber(@elem VARCHAR(100))
RETURNS INT
BEGIN
	DECLARE @param INT
	SET @param=0
	IF @elem not like '%[^0-9]%'
	BEGIN
		SET @param=1
	END
	ELSE
	BEGIN
		SET @param=0
	END
	RETURN (@param)
END
GO

CREATE OR ALTER FUNCTION checkLeter(@elem VARCHAR(100))
RETURNS INT
BEGIN
	DECLARE @param INT
	SET @param=0
	IF @elem not like '%[^a-z ]%'
	BEGIN
		SET @param=1
	END
	ELSE
	BEGIN
		SET @param=0
	END
	RETURN (@param)
END
GO

PRINT dbo.checkLeter('ana1 are mere')

CREATE OR ALTER FUNCTION checkId(@elem VARCHAR(100))
RETURNS INT
BEGIN
	DECLARE @param INT
	SET @param=0
	IF @elem>0
	BEGIN
		SET @param=1
	END
	ELSE
	BEGIN
		SET @param=0
	END
	RETURN (@param)
END
GO

CREATE OR ALTER PROCEDURE sp_insert_Sportive @a1 VARCHAR(100),@a2 VARCHAR(100),@a3 VARCHAR(100),@a4 VARCHAR(100),@a5 VARCHAR(100),@a6 VARCHAR(100)
AS
BEGIN 
	DECLARE @e INT
	DECLARE @e1 INT
	SET @e= dbo.checkNumber(@a1)
	IF(@e=1)
	BEGIN
	SET @e1= dbo.checkId(@a1)
	END
	IF @e=1 and @e1=1
	BEGIN
	INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB,TeamId,NutritionistId) VALUES
    (
        @a1, 
        @a2 , 
        @a3,
        @a4,
		@a5,
		@a6
    )
	END
	ELSE
	BEGIN
	RAISERROR('BAD',16,1)
	END
END
GO

CREATE OR ALTER PROCEDURE sp_insert_Nutritionist1 @a1 VARCHAR(100),@a2 VARCHAR(100),@a3 VARCHAR(100)
AS
BEGIN 
	DECLARE @e INT
	DECLARE @e1 INT
	SET @e= dbo.checkNumber(@a1)
	IF(@e=1)
	BEGIN
	SET @e1= dbo.checkId(@a1)
	END
	IF @e=1 and @e1=1
	BEGIN
	INSERT INTO Nutritionist(NutritionistId,NutritionistName,YearsOfExperience) VALUES
    (
        @a1, 
        @a2, 
        @a3
    )
	END
	ELSE
	BEGIN
	RAISERROR('BAD',16,1)
	END
END
GO


--2(View)


CREATE OR ALTER VIEW viewname
AS
	SELECT S.SportiveId,S.FirstName,N.NutritionistId,N.NutritionistName,T.TeamId,T.TeamName
	From Sportive S
	FULL JOIN Nutritionist N
	ON S.NutritionistId=N.NutritionistId
	FULL JOIN Team T
	ON S.TeamId=T.TeamId
GO

SELECT *
FROM viewname


--3(Triggers)

--inserts
CREATE OR ALTER TRIGGER whenAddingNutritionist
	ON Nutritionist
	FOR INSERT
AS
BEGIN
	DECLARE @insertedValues INT = (SELECT COUNT(*) FROM inserted)
	DECLARE @deletedValues INT=(SELECT COUNT(*) FROM deleted)

	IF(@insertedValues>0 and @deletedValues=0)
	INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows) 
		SELECT 'INSERT',GETDATE(),'Nutritionist',@insertedValues
END
GO

CREATE OR ALTER TRIGGER whenAddingSportive
	ON Sportive
	FOR INSERT
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
		SELECT 'INSERT',GETDATE(),'Sportive',@@ROWCOUNT
		FROM inserted
GO
CREATE OR ALTER TRIGGER whenAddingTeam
	ON Team
	FOR INSERT
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
		SELECT 'INSERT',GETDATE(),'Team',@@ROWCOUNT
		FROM inserted
GO

--deletes
CREATE OR ALTER TRIGGER whenDeletingNutritionist
	ON Nutritionist
	FOR DELETE
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows) 
		SELECT 'DELETE',GETDATE(),'Nutritionist',@@ROWCOUNT
		FROM deleted
GO
CREATE OR ALTER TRIGGER whenDeletingSportive
	ON Sportive
	FOR DELETE
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
		SELECT 'DELETE',GETDATE(),'Sportive',@@ROWCOUNT
		FROM deleted
GO
CREATE OR ALTER TRIGGER whenDeletingTeam
	ON Team
	FOR DELETE
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows) 
		SELECT 'DELETE',GETDATE(),'Team',@@ROWCOUNT
		FROM deleted
GO
--updates
CREATE OR ALTER TRIGGER whenUpdateNutritionist
	ON Nutritionist
	FOR UPDATE
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows) 
		SELECT 'UPDATE',GETDATE(),'Nutritionist',@@ROWCOUNT
		FROM inserted
Go
CREATE OR ALTER TRIGGER whenUpdateSportive
	ON Sportive
	FOR UPDATE
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
		SELECT 'UPDATE',GETDATE(),'Sportive',@@ROWCOUNT
		FROM inserted
GO
CREATE OR ALTER TRIGGER whenUpdateTeam
	ON Team
	FOR UPDATE
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
		SELECT 'UPDATE',GETDATE(),'Team',@@ROWCOUNT
		FROM inserted
GO


--4

--Clustered Index
ALTER TABLE [dbo].Sportive
DROP CONSTRAINT PK__Sportive__CCAC651472986370
GO

CREATE CLUSTERED INDEX indexName
ON [dbo].Sportive (FirstName)
GO

ALTER TABLE [dbo].Sportive
ADD CONSTRAINT [PK_Sportive] PRIMARY KEY (SportiveId)
GO

--NonClustered
CREATE INDEX index_name
ON Sportive (DOB);



--FirstName(Clustered), DOB(NonClustered)


--Clustered Index Scan
SELECT *
FROM Sportive

--Clustered Index Seek

Select *
From Sportive
Where FirstName='a'

--Nonclustered Index Scan
SELECT FirstName
FROM Sportive

--Nonclustered Index Seek

SELECT DOB
FROM Sportive
WHERE DOB='20-10-2000'

--Key lookup
SELECT *
FROM Sportive
WHERE SportiveId=2

--Test

INSERT Nutritionist VALUES(7,'g',7),(8,'h',8)
INSERT Team VALUES(1,'a'),(2,'b'),(3,'c')
INSERT Sportive VALUES(1,'a','b','10-05-2005',1,1),(2,'a','b','12-10-2006',2,2)
SELECT @@ROWCOUNT as num
SELECT * FROM LogAdd 

--UPDATE
UPDATE Sportive
SET FirstName='z'
WHERE SportiveId=1 or SportiveId=2;

--DELETE
DELETE FROM Sportive WHERE FirstName='z';

sp_insert_Nutritionist1 'a','h',11
sp_insert_Sportive 3,'a','a','11-05-2005',5,8



CREATE OR ALTER TRIGGER whenAddingSportive
	ON Sportive
	FOR INSERT
		AS INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
		SELECT 'INSERT',GETDATE(),'Sportive',@@ROWCOUNT
		FROM inserted
GO


CREATE OR ALTER TRIGGER whenChangingSportive
ON Sportive
FOR INSERT, UPDATE, DELETE
AS
BEGIN	

	DECLARE @insertedValues INT = (SELECT COUNT(*) FROM inserted)
	DECLARE @deletedValues INT=(SELECT COUNT(*) FROM deleted)

	IF(@insertedValues>0 AND @deletedValues=0)
	INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
	VALUES(
		'INSERT',
		GETDATE(),
		'Sportive',
		@insertedValues)


	IF(@insertedValues=0 AND @deletedValues>0)
	INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
	VALUES(
		'DELETE',
		GETDATE(),
		'Sportive',
		@deletedValues)
	
	IF(@insertedValues>0 AND @deletedValues>0)
	INSERT INTO LogTable(OpType,OpDate,TableName,numberOfRows)
	VALUES(
		'UPDATE',
		GETDATE(),
		'Sportive',
		@insertedValues)

END
