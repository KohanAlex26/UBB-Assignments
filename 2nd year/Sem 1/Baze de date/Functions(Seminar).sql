CREATE OR ALTER	FUNCTION CountSportiveById(@SportiveId INT)
RETURNS INT
BEGIN
	RETURN (SELECT COUNT(*)
			FROM Sportive
			WHERE SportiveId=@SportiveId)
END


PRINT dbo.CountSportiveById(2)
GO

CREATE OR ALTER	FUNCTION FilterSportiveById(@SportiveId INT)
RETURNS TABLE
	RETURN (SELECT *
			FROM Sportive
			WHERE SportiveId=@SportiveId)

SELECT * FROM FilterSportiveById(2)
SELECT * FROM FilterSportiveById(3)
