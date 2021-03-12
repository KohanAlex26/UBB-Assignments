
DROP TABLE Sportive
DROP TABLE Nutritionist


CREATE or ALTER PROCEDURE InsertGeneral(@tableName VARCHAR(100)) as
IF @tableName='Sportive'
BEGIN
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(1,'a','a','2000-09-12');
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(2,'a','a','2001-02-03');
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(3,'b','b','2000-09-12');
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(4,'c','c','2001-02-03');
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(5,'d','d','2000-09-12');
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(6,'e','e','2001-02-03');
END

IF @tableName='Nutritionist'
BEGIN
INSERT INTO Nutritionist VALUES(1,'b',1,1);
INSERT INTO Nutritionist VALUES(2,'a',2,2);
INSERT INTO Nutritionist VALUES(3,'c',3,3);
INSERT INTO Nutritionist VALUES(4,'d',4,4);
INSERT INTO Nutritionist VALUES(5,'e',5,5);
INSERT INTO Nutritionist VALUES(6,'f',6,6);
END
GO

CREATE TABLE Sportive
	(SportiveId INT PRIMARY KEY ,
	FirstName VARCHAR(50),
	LastName VARCHAR(20),
	DOB DATE)

CREATE TABLE Nutritionist
	(NutritionistId INT PRIMARY KEY,
	NutritionistName VARCHAR(20),
	YearsOfExperience INT,
	SportiveId INT FOREIGN KEY REFERENCES Sportive(SportiveId) ON DELETE CASCADE)

CREATE TABLE LogAdd(
	OpType VARCHAR(100),
	OpDate DATETIME,
	TableName VARCHAR(100),
	number INT
)

CREATE TABLE LogDelete(
	OpType VARCHAR(100),
	OpDate DATETIME,
	TableName VARCHAR(100),
	number INT
)



EXEC InsertGeneral Sportive
EXEC InsertGeneral Nutritionist

CREATE OR ALTER	FUNCTION CheckSportiveId(@SportiveId INT)
RETURNS INT
BEGIN
	BEGIN TRY
		SELECT CONVERT(INT, @SportiveId)
		PRINT 'INTEGER'
		RETURN 1
	END TRY
	BEGIN CATCH
		PRINT 'NOT AN INTEGER'
		RETURN 0
	END CATCH
END

CREATE OR ALTER	FUNCTION CheckNutritionistName(@SportiveId INT)
RETURNS INT
BEGIN
	RETURN (SELECT COUNT(*)
			FROM Sportive
			WHERE SportiveId=@SportiveId)
END

--Clustered Index
ALTER TABLE [dbo].Nutritionist
DROP CONSTRAINT PK__Nutritio__F4399C8CA7033863
GO

CREATE CLUSTERED INDEX indexName
ON [dbo].Nutritionist (NutritionistName)
GO

ALTER TABLE [dbo].Nutritionist
ADD CONSTRAINT [PK_Nutritionist] PRIMARY KEY (NutritionistId)
GO

--NonClustered
CREATE INDEX index_name
ON Nutritionist (YearsOfExperience);


--Clustered Index Scan
SELECT *
FROM Nutritionist

--Clustered Index Seek

Select *
From  Nutritionist
Where NutritionistName='b'

--Nonclustered Index Scan
SELECT YearsOfExperience
FROM Nutritionist

--Nonclustered Index Seek

SELECT YearsOfExperience
FROM Nutritionist
WHERE YearsOfExperience=1

--Key lookup
SELECT *
FROM Nutritionist
WITH(index=index_name)

--Trigger
CREATE OR ALTER TRIGGER whenAddingNutritionist
	ON Nutritionist
	FOR INSERT
		AS INSERT INTO LogBuy(PName,OpDate,Quantity) 
		SELECT PName,GETDATE(),PQuantity
		FROM inserted

CREATE OR ALTER TRIGGER whenAddingSportive
	ON Nutritionist
	FOR INSERT
		AS INSERT INTO LogBuy(PName,OpDate,Quantity) 
		SELECT PName,GETDATE(),PQuantity
		FROM inserted


--View
--1
CREATE VIEW viewname
AS
	SELECT NutritionistId,NutritionistName,Sportive.SportiveId,Sportive.FirstName,LogAdd.OpType,LogDelete.OpType as OpTypeDelete
	From Nutritionist,Sportive,LogAdd,LogDelete
GO

SELECT *
FROM viewname



SELECT ISNUMERIC('1232312.0')

DECLARE @p INT
BEGIN TRY
		SET @p='a'
		PRINT 'good'
	END TRY
	BEGIN CATCH
		PRINT 'NOT'
	END CATCH






	BEGIN TRY
		select ISNUMERIC(@p)
		PRINT 'INTEGER'
	END TRY
	BEGIN CATCH
		PRINT 'NOT AN INTEGER'
	END CATCH


GO

CREATE PROCEDURE sp_insert_Sportive9 @seed VARCHAR(100),@seed2 VARCHAR(100)
AS
BEGIN
	select case when @seed not like '%[^0-9]\+%' then 'Number' else 'Not a Number' end
	INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES
    (
        @seed, 
        @seed2 + CONVERT(VARCHAR(10), @seed), 
        'LastName' + CONVERT(VARCHAR(10), @seed),
        '1-1-1950'
    )
END
GO


CREATE OR ALTER PROCEDURE sp_insert_Sportive10 @seed VARCHAR(100),@seed2 VARCHAR(100)
AS
BEGIN 
	DECLARE @e INT
	SET @e= dbo.checkNumber(@seed)
	IF @e=1
	BEGIN
	INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES
    (
        @seed, 
        @seed2 + CONVERT(VARCHAR(10), @seed), 
        'LastName' + CONVERT(VARCHAR(10), @seed),
        '1-1-1950'
    )
	END
	ELSE
	BEGIN
	RAISERROR('BAD',16,1)
	END
END
GO


CREATE OR ALTER PROCEDURE sp_insert_Sportive12 @seed VARCHAR(100),@seed2 VARCHAR(100),@seed3 VARCHAR(100),@seed4 VARCHAR(100)
AS
BEGIN 
	DECLARE @e INT
	DECLARE @e1 INT
	SET @e= dbo.checkNumber(@seed)
	SET @e1= dbo.checkId(@seed)
	IF @e=1 and @e1=1
	BEGIN
	INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES
    (
        @seed, 
        @seed2 , 
        @seed3,
        @seed4
    )
	END
	ELSE
	BEGIN
	RAISERROR('BAD',16,1)
	END
END
GO

CREATE OR ALTER PROCEDURE sp_insert_Nutritionist1 @seed VARCHAR(100),@seed2 VARCHAR(100),@seed3 VARCHAR(100),@seed4 VARCHAR(100)
AS
BEGIN 
	DECLARE @e INT
	DECLARE @e1 INT
	SET @e= dbo.checkNumber(@seed)
	SET @e1= dbo.checkId(@seed)
	IF @e=1 and @e1=1
	BEGIN
	INSERT INTO Nutritionist(NutritionistId,NutritionistName,YearsOfExperience,SportiveId) VALUES
    (
        @seed, 
        @seed2, 
        @seed3,
        @seed4
    )
	END
	ELSE
	BEGIN
	RAISERROR('BAD',16,1)
	END
END
GO
sp_insert_Sportive12 '1023230','a','unguru','12-10-2000'
sp_insert_Nutritionist1 '10','alex','1','1'

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

PRINT dbo.checkNumber ('123423244324323')


DECLARE @seed INT
DECLARE @seed2 VARCHAR(100)
CREATE FUNCTION A
as
SE
sp_insert_Sportive9 90000,'a'



SELECT dbo.checkNumber('abv') as e