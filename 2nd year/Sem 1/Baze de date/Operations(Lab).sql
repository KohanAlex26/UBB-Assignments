SELECT NutritionistName FROM Nutritionist
UNION
SELECT FirstName FROM Sportive;

SELECT FirstName
FROM Sportive
INTERSECT
SELECT NutritionistName
FROM Nutritionist;

SELECT FirstName
FROM Sportive
EXCEPT
SELECT NutritionistName
FROM Nutritionist;

SELECT *
FROM Sportive S
INNER JOIN Team T
ON S.SportiveId = T.SportiveId;

SELECT *
FROM Sportive S
LEFT JOIN Team T
ON S.SportiveId = T.SportiveId;

SELECT *
FROM Sportive S
RIGHT JOIN Team T
ON S.SportiveId = T.SportiveId;

SELECT *
FROM Team T
FULL JOIN Sportive S
ON S.SportiveId = T.SportiveId;

SELECT *
FROM Sportive
WHERE FirstName IN ('Pop','Ioan','Remus') and SportiveId>0 ;


SELECT SportiveId,FirstName,LastName
FROM Sportive S
WHERE EXISTS (SELECT SportiveId FROM Team T WHERE T.TeamName='Manchester' and S.SportiveId=T.SportiveId)

SELECT TeamName
FROM Team T
WHERE EXISTS
(SELECT FirstName FROM Sportive S WHERE (SportiveId>=1 and SportiveId<=5) and S.SportiveId=T.SportiveId);

SELECT SportiveId,FirstName
FROM   (SELECT SportiveId,LastName,FirstName
        FROM Sportive) AS g

SELECT COUNT(FirstName),LastName
FROM Sportive
WHERE SportiveId>0 or FirstName='Alex'
GROUP BY LastName
ORDER BY COUNT(FirstName);


SELECT COUNT(FirstName),LastName
FROM Sportive
WHERE SportiveId>0
GROUP BY LastName
HAVING COUNT(FirstName)=1
ORDER BY COUNT(FirstName);

//
SELECT COUNT(FirstName),LastName
FROM Sportive
WHERE SportiveId>0
GROUP BY LastName
HAVING MAX(SportiveId) > (SELECT MAX(SportiveId)
                               FROM   Sportive WHERE SportiveId<3)
ORDER BY COUNT(FirstName);

SELECT TOP 1 *
FROM Sportive
WHERE SportiveId>0;

SELECT DISTINCT LastName
FROM Sportive;