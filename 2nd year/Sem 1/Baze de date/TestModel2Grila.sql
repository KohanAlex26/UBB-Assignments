USE TestModel2Grila
GO
CREATE TABLE R(
RID INT PRIMARY KEY,
A1 VARCHAR(100),
K2 INT not null,
A2 INT,
A3 INT,
A4 INT,
A5 VARCHAR(100),
A6 INT
)


CREATE TABLE InsertLog(
LogId int IDENTITY(1,1) PRIMARY KEY,
A5Value VARCHAR(100),
NumRows INT,
DateTimeOp VARCHAR(100)
)

INSERT R VALUES(2,' Punctu-acela de miscare, mult mai slab ca boaba spumii,',100,1,3,3,'M1',22)
INSERT R VALUES(3,'E stapanul fara margini peste marginile lumii... ',200,1,3,3,'M1',22)
INSERT R VALUES(4,'De-atunci negura eterna se desface in fasii,',150,2,3,4,'M1',23)
INSERT R VALUES(5,'De atunci rasare lumea, luna, soare si stihii... ',700,2,4,4,'M2',29)
INSERT R VALUES(6,'De atunci si pana astazi colonii de lumi pierdute ',300,3,4,5,'M2',29)
INSERT R VALUES(7,'Vin din sure vai de chaos pe carari necunoscute',350,3,4,5,'M5',23)
INSERT R VALUES(8,'Si in roiuri luminoase izvorand din infinit,',400,3,5,7,'M5',29)
INSERT R VALUES(9,'Sunt atrase in viata de un dor nemarginit.',500,4,5,7,'M2',30)
INSERT R VALUES(10,'Iar in lumea asta mare, noi copii ai lumii mici,',450,4,5,7,'M7',30)
INSERT R VALUES(11,'Facem pe pamantul nostru musunoaie de furnici; ',250 ,4 ,6 ,7 ,'M7' ,30)
INSERT R VALUES(12,'Microscopice popoare, regi, osteni si invatati ',800,5 ,6, 7, 'M6' ,22)
INSERT R VALUES(13,'Ne succedem generatii si ne credem minunati; ',750 ,5 ,6 ,7, 'M6' ,23)


SELECT*
FROM R
SELECT*
FROM R
SELECT*
FROM R

SELECT r1.RID, r1.K2
FROM R r1 INNER JOIN R r2 ON r1.A2 = r2.A3

SELECT *
FROM R r1 INNER JOIN R r2 ON r1.A2 = r2.A3
 INNER JOIN R r3 ON r2.A3 = r3.A4

WHERE r1.A1 LIKE '_%'
GROUP BY r1.RID, r1.K2
HAVING COUNT(*) >= 6SELECT r1.A6, MAX(r1.A2) MaxA2
FROM R r1
WHERE r1.A5 IN ('M1', 'M2')
GROUP BY r1.A6
EXCEPT
SELECT DISTINCT r2.A6, r2.A2
FROM R r2



CREATE OR ALTER FUNCTION ufF1(@A5 CHAR(2))
RETURNS INT
BEGIN
RETURN
 (SELECT COUNT(*)
 FROM R
 WHERE A5 = @A5)
END


CREATE OR ALTER TRIGGER TrOnInsert
 ON R
 FOR INSERT
AS
 INSERT InsertLog(A5Value, NumRows, DateTimeOp)
 SELECT i.A5, dbo.ufF1(i.A5), GETDATE()
 FROM inserted i INSERT R(RID, K2, A5) VALUES
 (14, 14, 'M1'), (15, 15, 'M1'), (16, 16, 'M2')
INSERT R(RID, K2, A5) VALUES
 (17, 17, 'M1'), (18, 18, 'M3')