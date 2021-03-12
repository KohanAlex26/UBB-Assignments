USE TestTheoretical
GO

CREATE TABLE T(
ID1 INT,
ID2 INT,
ID3 INT,
C1 VARCHAR(100),
C2 INT,
C3 INT,
C4 INT
PRIMARY KEY (ID1,ID2,ID3)
)

INSERT INTO T VALUES(1,1,1,'Pe copite iau în fugă fața negrului pămînt', -2, null, 0)
INSERT INTO T VALUES(1,1,2,'Lănci scînteie lungi în soare, arcuri se întind în vînt', -1, 2, 1)
INSERT INTO T VALUES(1,2,1,'Și ca nouri de aramă și ca ropotul de grindeni', 0, null, 0)
INSERT INTO T VALUES(1,2,2,'Orizontu-ntunecîndu-l, vin săgeți de pretutindeni', 1, null, -1)
INSERT INTO T VALUES(2,1,1,'Vîjîind ca vijelia și ca plesnetul de ploaie…', 2, 4, -1)
INSERT INTO T VALUES(2,1,2,'Urlă cîmpul și de tropot și de strigăt de bătaie.', 3, 4, -1)

SELECT AVG(C3)
FROM T
WHERE C2 > 1
GROUP BY ID1, ID2, ID3
HAVING SUM(C2) <= 1SELECT AVG(C3)
FROM T
WHERE C2 >=0
GROUP BY ID1
HAVING SUM(C2) >=0SELECT * FROM T WHERE C3>5SELECT * FROM T WHERE C1 LIKE '_%'SELECT * FROM T WHERE ID1 = ID2
INTERSECT
SELECT * FROM T WHERE ID2 = ID3

SELECT * FROM T t1
WHERE t1.C4 > (SELECT MAX(t2.C4)
 FROM T t2)