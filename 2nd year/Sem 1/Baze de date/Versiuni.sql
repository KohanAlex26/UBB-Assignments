USE Test
GO

CREATE or ALTER PROCEDURE AddColumn(@param INT) as
ALTER TABLE tabel
ADD age INT;

IF(@param=1)
BEGIN
	DECLARE @versiune INT
	SELECT @versiune = versiune FROM tabel
	DECLARE @procedureName VARCHAR(100)
	SET @procedureName='RemoveColumn'
	INSERT downgradeTable VALUES(@versiune,@procedureName,@versiune)
	UPDATE tabel SET versiune = versiune+1

	SET @procedureName='AddColumn'
	INSERT upgradeTable VALUES(@versiune+1,@procedureName,@versiune+1)
END
GO


CREATE or ALTER PROCEDURE RemoveColumn(@param INT) as
ALTER TABLE tabel
DROP COLUMN age;

IF(@param=1)
BEGIN
	DECLARE @versiune INT
	SELECT @versiune = versiune FROM tabel
	DECLARE @procedureName VARCHAR(100)
	SET @procedureName='AddColumn'
	INSERT downgradeTable VALUES(@versiune,@procedureName,@versiune)
	UPDATE tabel SET versiune = versiune+1

	SET @procedureName='RemoveColumn'
	INSERT upgradeTable VALUES(@versiune+1,@procedureName,@versiune+1)
END
GO



CREATE or ALTER PROCEDURE RemovePrimaryKey(@param INT) as
ALTER TABLE tabel
DROP CONSTRAINT PK__tabel;

IF(@param=1)
BEGIN
	DECLARE @versiune INT
	SELECT @versiune = versiune FROM tabel
	DECLARE @procedureName VARCHAR(100)
	SET @procedureName='AddPrimaryKey'
	INSERT downgradeTable VALUES(@versiune,@procedureName,@versiune)
	UPDATE tabel SET versiune = versiune+1

	SET @procedureName='RemovePrimaryKey'
	INSERT upgradeTable VALUES(@versiune+1,@procedureName,@versiune+1)
END
GO



CREATE or ALTER PROCEDURE AddPrimaryKey(@param INT) as
ALTER TABLE tabel
ADD CONSTRAINT PK__tabel PRIMARY KEY (id);

IF(@param=1)
BEGIN
	DECLARE @versiune INT
	SELECT @versiune = versiune FROM tabel
	DECLARE @procedureName VARCHAR(100)
	SET @procedureName='RemovePrimaryKey'
	INSERT downgradeTable VALUES(@versiune,@procedureName,@versiune)
	UPDATE tabel SET versiune = versiune+1

	SET @procedureName='AddPrimaryKey'
	INSERT upgradeTable VALUES(@versiune+1,@procedureName,@versiune+1)
END
GO



CREATE or ALTER PROCEDURE RemoveForeignKey(@param INT) as
ALTER TABLE tabel
DROP CONSTRAINT FK__tabel__test;
IF(@param=1)
BEGIN
	DECLARE @versiune INT=1
	SELECT @versiune = versiune FROM tabel
	DECLARE @procedureName VARCHAR(100)
	SET @procedureName='AddForeignKey'
	INSERT downgradeTable VALUES(@versiune,@procedureName,@versiune)
	UPDATE tabel SET versiune = versiune+1

	SET @procedureName='RemoveForeignKey'
	INSERT upgradeTable VALUES(@versiune+1,@procedureName,@versiune+1)
END
GO



CREATE or ALTER PROCEDURE AddForeignKey(@param INT) as
ALTER TABLE tabel
ADD CONSTRAINT FK__tabel__test FOREIGN KEY (numar) REFERENCES test(idTest);

IF(@param=1)
BEGIN
	DECLARE @versiune INT=1
	SELECT @versiune = versiune FROM tabel
	DECLARE @procedureName VARCHAR(100)
	SET @procedureName='RemoveForeignKey'
	INSERT downgradeTable VALUES(@versiune,@procedureName,@versiune)
	UPDATE tabel SET versiune = versiune+1


	SET @procedureName='AddForeignKey'
	INSERT upgradeTable VALUES(@versiune+1,@procedureName,@versiune+1)
END
GO



CREATE or ALTER PROCEDURE DropTable as
DROP TABLE tabel
GO

CREATE or ALTER PROCEDURE AddTable as
CREATE TABLE tabel
(
id INT PRIMARY KEY,
versiune INT,
numar INT,
caracter VARCHAR(100)
)
GO

CREATE TABLE test
(idTest INT PRIMARY KEY
)



CREATE or ALTER PROCEDURE getVersion(@version int) AS
	DECLARE @currentVersion INT
	SELECT @currentVersion = versiune FROM tabel
	DECLARE @procedureName VARCHAR(100)

	IF(@version<(SELECT MIN(newVersion) FROM downgradeTable) or @version>(SELECT MAX(newVersion)FROM upgradeTable))
		RAISERROR('The desired verion is incorrect',16,1)

	IF @currentVersion>@version
		BEGIN
		WHILE @currentVersion!=@version BEGIN
			SELECT @procedureName=procedureName FROM downgradeTable WHERE newVersion = @currentVersion-1
			SET @currentVersion=@currentVersion-1
			IF(@procedureName='AddColumn')
			BEGIN
			EXEC AddColumn 0
			END
			IF(@procedureName='RemoveColumn')
			BEGIN
			EXEC RemoveColumn 0
			END
			IF(@procedureName='AddPrimaryKey')
			BEGIN
			EXEC AddPrimaryKey 0
			END
			IF(@procedureName='RemovePrimaryKey')
			BEGIN
			EXEC RemovePrimaryKey 0
			END
			IF(@procedureName='AddForeignKey')
			BEGIN
			EXEC AddForeignKey 0
			END
			IF(@procedureName='RemoveForeignKey')
			BEGIN
			EXEC RemoveForeignKey 0
			END
		END
		END
	ELSE
		BEGIN
		WHILE @currentVersion != @version BEGIN
			SELECT @procedureName=procedureName FROM upgradeTable WHERE newVersion= @currentVersion+1
			SET @currentVersion=@currentVersion+1
			IF(@procedureName='AddColumn')
			BEGIN
			EXEC AddColumn 0
			END
			IF(@procedureName='RemoveColumn')
			BEGIN
			EXEC RemoveColumn 0
			END
			IF(@procedureName='AddPrimaryKey')
			BEGIN
			EXEC AddPrimaryKey 0
			END
			IF(@procedureName='RemovePrimaryKey')
			BEGIN
			EXEC RemovePrimaryKey 0
			END
			IF(@procedureName='AddForeignKey')
			BEGIN
			EXEC AddForeignKey 0
			END
			IF(@procedureName='RemoveForeignKey')
			BEGIN
			EXEC RemoveForeignKey 0
			END
		END
		END
	UPDATE tabel SET versiune = @currentVersion
GO


DROP PROCEDURE getVersion



CREATE TABLE downgradeTable
(id INT PRIMARY KEY,
procedureName VARCHAR(100),
newVersion INT
)

CREATE TABLE upgradeTable
(id INT PRIMARY KEY,
procedureName VARCHAR(100),
newVersion INT
)


DELETE FROM downgradeTable
DELETE FROM upgradeTable
DELETE FROM test
EXECUTE DropTable
EXECUTE AddTable
GO
INSERT INTO tabel VALUES(1,1,1,'a'),(2,1,2,'b')
INSERT INTO test VALUES(1),(2)
EXECUTE AddColumn 1
EXECUTE AddForeignKey 1
EXECUTE RemoveForeignKey 1
EXECUTE RemoveColumn 1
EXECUTE getVersion 2
EXECUTE getVersion 1
EXECUTE getVersion 3