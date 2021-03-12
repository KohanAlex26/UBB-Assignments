USE TestTheoretical2
GO

CREATE TABLE S(
ID VARCHAR(100) PRIMARY KEY,
A VARCHAR(100),
B VARCHAR(100),
C VARCHAR(100),
D INT,
E INT,
F INT)

INSERT INTO S VALUES('t1', 'a1', 'b2', 'Şi abia plecă bătrânul... Ce mai freamăt, ce mai zbucium!', 0, 1, 0)
INSERT INTO S VALUES('t2', 'a1', 'b2', 'Codrul clocoti de zgomot şi de arme şi de bucium,', 1, 2, 1)
INSERT INTO S VALUES('t3', 'a1', 'b3', 'Iar la poala lui cea verde mii de capete pletoase,', 0, 3, 0)
INSERT INTO S VALUES('t4', 'a1', 'b3', 'Mii de coifuri lucitoare ies din umbra-ntunecoasă;', 2, 123, -1)
INSERT INTO S VALUES('t5', 'a1', 'b3', 'Călăreţii umplu câmpul şi roiesc după un semn', -1, 4, -1)--1SELECT DISTINCT A, B FROM S SELECT * FROM S WHERE B = 'b2' AND B = 'b3'SELECT * FROM S WHERE B = 'b3'
UNION SELECT * FROM S WHERE B = 'b3'SELECT * FROM S WHERE D >= 0
EXCEPT SELECT * FROM S WHERE E <> 4--2SELECT B, C, COUNT(*)
FROM S
GROUP BY B, C
HAVING D <= 1SELECT *
FROM S
WHERE C LIKE '%de%'

SELECT B
FROM S
GROUP BY B

SELECT *
FROM S


SELECT D
FROM S
GROUP BY D

