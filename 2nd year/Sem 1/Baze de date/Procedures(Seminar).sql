CREATE or ALTER PROCEDURE uspGet_Sportives(@sportiveid int,@numberofsportives int OUTPUT) as
	SELECT @numberofsportives=COUNT(*)
	FROM Sportive
	WHERE SportiveId=@sportiveid
	IF @numberofsportives=0
		RAISERROR('No sportives found',10,1)



DECLARE @number int=0
EXEC uspGet_Sportives 10,@numberofsportives=@number OUTPUT
PRINT @number


SELECT * FROM Sportive
GO

CREATE or AlTER PROC uspGetAllSportives
AS
BEGIN
	SELECT *
	FROM Sportive
END




SELECT *
FROM sys.sql_modules
WHERE object_id IN
	(SELECT *
	FROM sys.objects
	WHERE type='P')