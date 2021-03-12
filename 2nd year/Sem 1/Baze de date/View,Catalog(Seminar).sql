***Functions, Views, System Catalog, Triggers, [,Merge] in SQL Server

USE Sport
GO

SELECT *
FROM Sportive


GO
CREATE OR ALTER	FUNCTION a(@SportiveId INT)
RETURNS	@b TABLE(SportiveId INT,FirstName VARCHAR(100),LastName VARCHAR(100))
BEGIN
	INSERT @b
	SELECT SportiveId,FirstName,LastName
	FROM Sportive
	WHERE SportiveId=@SportiveId

	IF @@ROWCOUNT=0
	INSERT @b
	VALUES(0,"No Sportives with that Id.",NULL)
	RETURN
END

* VIEWS *
CREATE VIEW viewname
AS
	SELECT_st

SELECT *
FROM viewname

CREATE OR ALTER VIEW sportive_details AS
	SELECT SportiveId
	FROM Sportive S
	INNER JOIN Team T
	ON S.SportiveId = T.SportiveId;

SELECT * FROM sportive_details

* System Catalog *
