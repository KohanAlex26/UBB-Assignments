USE TestModel
GO

DROP TABLE StationsRoutes
DROP TABLE Stations
DROP TABLE Routes
DROP TABLE Trains
DROP TABLE TrainTypes
GO
CREATE TABLE TrainTypes(
TTID INT PRIMARY KEY,
TTName VARCHAR(50),
TTDescription VARCHAR(50))

CREATE TABLE Trains(
TID INT PRIMARY KEY,
TName VARCHAR(50),
TTID INT REFERENCES TrainTypes(TTID))

CREATE TABLE Stations(
SID INT PRIMARY KEY,
SName VARCHAR(50) UNIQUE)

CREATE TABLE Routes(
RID INT PRIMARY KEY,
RName VARCHAR(50) UNIQUE,
TID INT REFERENCES Trains(TID))

CREATE TABLE StationsRoutes(
SID INT REFERENCES Stations(SID),
RID INT REFERENCES Routes(RID),
Arrival TIME,
Departure TIME,
PRIMARY KEY(SID,RID))

--2
CREATE OR ALTER PROC uspUpdateRoute(@RName VARCHAR(50),@SName VARCHAR(50),@Arrival TIME,@Departure TIME) 
AS
	DECLARE @SID INT, @RID INT
	IF NOT EXISTS(SELECT * FROM Stations WHERE SNAME=@Sname)
	BEGIN
	RAISERROR('Invalid station name.',16,1)
	RETURN
	END
	IF NOT EXISTS(SELECT * FROM Routes WHERE RName=@RName)
	BEGIN
	RAISERROR('Invalid route name.',16,1)
	RETURN
	END

	SELECT @SID=(SELECT SID FROM Stations WHERE SName=@Sname),
	@RID=(SELECT RID FROM Routes WHERE RName=@RName)

	IF EXISTS(SELECT *
			FROM StationsRoutes
			WHERE SID=@SID AND RID=@RID
			)
		UPDATE StationsRoutes
		SET Arrival=@Arrival,Departure=@Departure
		WHERE SID=@SID AND RID=@RID
	ELSE
		INSERT StationsRoutes(SID,RID,Arrival,Departure)
		VALUES(@SID,@RID,@Arrival,@Departure)
GO

SELECT * FROM TrainTypes
SELECT * FROM Trains
SELECT * FROM Routes
SELECT * FROM Stations
SELECT * FROM StationsRoutes

INSERT TrainTypes VALUES(1,'type1','descr'),(2,'type2','descr')
INSERT Trains VALUES(1,'t1',1),(2,'t2',1),(3,'t3',1)
INSERT Routes VALUES(1,'r1',1),(2,'r2',2),(3,'r3',3)
INSERT Stations VALUES(1,'s1'),(2,'s2'),(3,'s3')

INSERT Trains VALUES(4,'t4',1)
INSERT Routes VALUES(4,'r4',4)

EXEC uspUpdateRoute @RName='r1',@SName='s7',@Arrival='6:10',@Departure='6:20'

EXEC uspUpdateRoute @RName='r1',@SName='s1',@Arrival='7:30',@Departure='7:40'
EXEC uspUpdateRoute @RName='r1',@SName='s2',@Arrival='8:00',@Departure='8:10'
EXEC uspUpdateRoute @RName='r1',@SName='s3',@Arrival='8:30',@Departure='8:40'

EXEC uspUpdateRoute @RName='r2',@SName='s1',@Arrival='7:30',@Departure='7:40'
EXEC uspUpdateRoute @RName='r2',@SName='s2',@Arrival='8:00',@Departure='8:10'
EXEC uspUpdateRoute @RName='r2',@SName='s3',@Arrival='8:30',@Departure='8:40'

EXEC uspUpdateRoute @RName='r3',@SName='s1',@Arrival='7:30',@Departure='7:40'


--3
SELECT SID
FROM Stations
EXCEPT
SELECT SID
FROM StationsRoutes
WHERE RID=3


CREATE OR ALTER VIEW vRoutesWithAllStations
AS
	SELECT r.RName
	FROM Routes r
	WHERE NOT EXISTS
		(SELECT SID
		FROM Stations
		EXCEPT
		SELECT SID
		FROM StationsRoutes
		WHERE RID=r.RID)
GO


SELECT *
FROM vRoutesWithAllStations
GO



CREATE OR ALTER FUNCTION ufFilterStaionsByNumOfRoutes(@R INT)
RETURNS TABLE
RETURN SELECT S.SName
	FROM Stations S
	WHERE S.SID IN
		(SELECT SR.SID
		FROM StationsRoutes SR
		GROUP BY SR.SID
		HAVING COUNT(*)>@R)

SELECT *
FROM ufFilterStaionsByNumOfRoutes(2)






--optional(view)
CREATE OR ALTER VIEW vRoutesWithNoStations
AS
	SELECT r.RName
	FROM Routes r
	WHERE NOT EXISTS
		(
		SELECT SID
		FROM StationsRoutes
		WHERE RID=r.RID)
GO

SELECT *
FROM vRoutesWithNoStations
GO

--optional(function)
CREATE OR ALTER FUNCTION ufFilterStaionsByNumOfRoutesMin()
RETURNS TABLE
RETURN SELECT S.SName
	FROM Stations S
	WHERE S.SID IN
		(SELECT SR.SID
		FROM StationsRoutes SR
		GROUP BY SR.SID
		HAVING COUNT(*)>(SELECT MIN(e) as f
						FROM (SELECT COUNT(*) as e
						FROM StationsRoutes SR2
						GROUP BY SR2.SID) AS S
						)			
		)

SELECT *
FROM ufFilterStaionsByNumOfRoutesMin()