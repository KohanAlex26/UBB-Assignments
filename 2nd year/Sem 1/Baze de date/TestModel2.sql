USE TestModel2
GO


CREATE TABLE DroneManufactures(
DMID INT PRIMARY KEY,
DMName VARCHAR(100)
)
GO
CREATE TABLE DroneModels(
DMOID INT PRIMARY KEY,
DMID INT REFERENCES DroneManufactures(DMID),
DMOName VARCHAR(100),
DMOBattery INT,
DMOSpeed INT
)
GO
CREATE TABLE Drone(
DID INT PRIMARY KEY,
DMOID INT REFERENCES DroneModels(DMOID),
DSerial INT
)
GO
CREATE TABLE PizzaShop(
PSID INT PRIMARY KEY,
PSName VARCHAR(100) UNIQUE,
PSAddress VARCHAR(100)
)
GO
CREATE TABLE Customer(
CID INT PRIMARY KEY,
CName VARCHAR(100) UNIQUE,
CScore INT
)
GO
CREATE TABLE Delivery(
CID INT REFERENCES Customer(CID),
PSID INT REFERENCES PizzaShop(PSID),
DID INT REFERENCES Drone(DID),
DDate DATE,
DTime TIME,
PRIMARY KEY(CID,PSID,DID)
)


--b
CREATE OR ALTER PROC addDelivery(@CName VARCHAR(100),@PSName VARCHAR(100),@DSerial INT,@DDate DATE,@DTime TIME)
AS
	DECLARE @CID INT,@PSID INT,@DID INT
	IF NOT EXISTS(SELECT @CID FROM Customer WHERE CName=@CName)
	BEGIN
	RAISERROR('Invalid customer',16,1)
	RETURN
	END
	IF NOT EXISTS(SELECT @PSID FROM PizzaShop WHERE PSName=@PSName)
	BEGIN
	RAISERROR('Invalid PizzaShop',16,1)
	RETURN
	END
	IF NOT EXISTS(SELECT @DID FROM Drone WHERE DSerial=@DSerial)
	BEGIN
	RAISERROR('Invalid Drone',16,1)
	RETURN
	END
	SELECT @CID=(SELECT CID FROM Customer WHERE CName=@CName),@PSID=(SELECT PSID FROM PizzaShop WHERE PSName=@PSName),@DID=(SELECT DID FROM Drone WHERE DSerial=@DSerial)
	IF EXISTS(SELECT * FROM Delivery WHERE CID=@CID and PSID=@PSID and DID=@DID)
		UPDATE Delivery
		SET DDate=@DDate,DTime=@DTime
		WHERE CID=@CID and PSID=@PSID and DID=@DID
	ELSE
		INSERT Delivery(CID,PSID,DID,DDate,DTime)
		VALUES(@CID,@PSID,@DID,@DDate,@DTime)

GO

INSERT Customer VALUES(1,'a',1)
INSERT PizzaShop VALUES(1,'a','a')
INSERT DroneManufactures VALUES(3,'c')
INSERT DroneModels VALUES(4,3,'a',1,1)
INSERT Drone VALUES(4,3,1)


exec addDelivery @CName='a',@PSName='a',@DSerial=1,@DDate='2000-10-02',@DTime='12:00'

--c
CREATE OR ALTER VIEW viewFavorite
AS
		SELECT DM.DMID
				FROM DroneManufactures DM
				INNER JOIN DroneModels DMO
				ON DM.DMID=DMO.DMID
				INNER JOIN Drone D
				ON D.DMOID=DMO.DMOID
				GROUP BY DM.DMID
				HAVING COUNT(*)=
				(SELECT MAX(S.e)
				FROM
				(SELECT DM.DMID,COUNT(*) as e
				FROM DroneManufactures DM
				INNER JOIN DroneModels DMO
				ON DM.DMID=DMO.DMID
				INNER JOIN Drone D
				ON D.DMOID=DMO.DMOID
				GROUP BY DM.DMID) as S)
GO

SELECT *
FROM viewFavorite





				SELECT DM.DMName
				FROM DroneManufactures DM
				WHERE DM.DMID IN
				
				(SELECT DMO.DMID
				FROM DroneModels DMO
				
				WHERE DMO.DMID IN(SELECT D.DMOID
				FROM Drone D)
				GROUP BY DMO.DMOID


				)
				GROUP BY DM.DMName



				--
				SELECT DMO.DMOID
				FROM DroneModels DMO
				WHERE DMO.DMOID IN(SELECT D.DMOID
				FROM Drone D
				GROUP BY D.DMOID
				)
				
				SELECT *
				FROM DroneManufactures
				SELECT *
				FROM DroneModels
				SELECT *
				FROM Drone
				

				SELECT *
				FROM DroneManufactures DM
				INNER JOIN 
				(SELECT *
				FROM
				(SELECT DMO.DMOID,DMO.DMID,COUNT(*) as e
				FROM DroneModels DMO
				INNER JOIN Drone D
				ON D.DMOID=DMO.DMOID
				GROUP BY DMO.DMOID,DMO.DMID) as S)AS f
				ON DM.DMID=f.DMID


				SELECT *
				FROM DroneManufactures
				SELECT *
				FROM DroneModels
				SELECT *
				FROM Drone

	
				SELECT max(l.r)
				FROM
				(SELECT g.DMID,COUNT(*) as r
				FROM
				(SELECT Manufactures.DMID
				FROM DroneManufactures Manufactures
				INNER JOIN
				(SELECT *
				FROM DroneModels Models
				)as S
				ON Manufactures.DMID=S.DMID
				INNER JOIN Drone drones
				ON drones.DMOID=S.DMOID) as g
				GROUP BY g.DMID) as l



--varianta lunga
				SELECT g.DMID,COUNT(*) as r
				FROM
				(SELECT Manufactures.DMID
				FROM DroneManufactures Manufactures
				INNER JOIN
				(SELECT *
				FROM DroneModels Models
				)as S
				ON Manufactures.DMID=S.DMID
				INNER JOIN Drone drones
				ON drones.DMOID=S.DMOID) as g
				GROUP BY g.DMID
				HAVING COUNT(*)=(SELECT max(l.r)
				FROM
				(SELECT g.DMID,COUNT(*) as r
				FROM
				(SELECT Manufactures.DMID
				FROM DroneManufactures Manufactures
				INNER JOIN
				(SELECT *
				FROM DroneModels Models
				)as S
				ON Manufactures.DMID=S.DMID
				INNER JOIN Drone drones
				ON drones.DMOID=S.DMOID) as g
				GROUP BY g.DMID) as l)


--parte din varianta ok
				SELECT T.DMID,COUNT(*)
				FROM
				(SELECT DM.DMID,DM.DMName,DMO.DMOID,D.DID
				FROM DroneManufactures DM
				INNER JOIN DroneModels DMO
				ON DM.DMID=DMO.DMID
				INNER JOIN Drone D
				ON D.DMOID=DMO.DMOID) as T
				GROUP BY T.DMID

--varianta ok
				SELECT DM.DMID
				FROM DroneManufactures DM
				INNER JOIN DroneModels DMO
				ON DM.DMID=DMO.DMID
				INNER JOIN Drone D
				ON D.DMOID=DMO.DMOID
				GROUP BY DM.DMID
				HAVING COUNT(*)=
				(SELECT MAX(S.e)
				FROM
				(SELECT DM.DMID,COUNT(*) as e
				FROM DroneManufactures DM
				INNER JOIN DroneModels DMO
				ON DM.DMID=DMO.DMID
				INNER JOIN Drone D
				ON D.DMOID=DMO.DMOID
				GROUP BY DM.DMID) as S)

CREATE OR ALTER FUNCTION ufNamesOfCustomers(@D INT)
RETURNS TABLE
RETURN SELECT CName
	   FROM Customer C
	   WHERE C.CID IN(SELECT CID
	   FROM Delivery D
	   GROUP BY D.CID
	   HAVING COUNT(*)>@D
	   )

SELECT *
FROM ufNamesOfCustomers(0)


--exemplu de lucruri grupate descending
SELECT DM.DMID,COUNT(*)
FROM DroneManufactures DM
INNER JOIN DroneModels DMO
ON DM.DMID=DMO.DMID
WHERE DMO.DMID<3
GROUP BY DM.DMID
ORDER BY COUNT(*) DESC


--modelele de drone care au mai putin de n drone
CREATE OR ALTER FUNCTION lessThanNDrones(@n INT)
RETURNS TABLE
RETURN SELECT DMO.DMID,COUNT(*) as e
		FROM DroneModels DMO
		INNER JOIN Drone D
		ON D.DMOID=DMO.DMOID
		GROUP BY DMO.DMID
		HAVING COUNT(*)<@n

SELECT *
FROM lessThanNDrones(3)

--varianta scurta manufacters cu numar maxim de drone
SELECT dmn.DMID
FROM Drone d,DroneModels dm,DroneManufactures dmn
WHERE d.DMOID=dm.DMOID and dm.DMID=dmn.DMID
GROUP BY dmn.DMID
HAVING COUNT(*)=(SELECT MAX(S.e)
	FROM (SELECT COUNT(*) as e
	FROM Drone d,DroneModels dm,DroneManufactures dmn
	WHERE d.DMOID=dm.DMOID and dm.DMID=dmn.DMID
	GROUP BY dmn.DMID)as S)