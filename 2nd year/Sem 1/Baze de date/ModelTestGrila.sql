USE ModelTestGrila


CREATE TABLE R(
FK1 INT,
FK2 INT,
PRIMARY KEY(FK1,FK2),
C1 VARCHAR(100),
C2 VARCHAR(100),
C3 INT,
C4 INT,
C5 VARCHAR(100)
)
INSERT R VALUES(1,1,'Pisica pe acoperisul fierbinte','Tennessee Williams',100,20,'AB')
INSERT R VALUES(1,2,'Conul Leonida fata cu reactiunea','Ion Luca Caragiale',50,50,'CQ')
INSERT R VALUES(1,3,'Concert din muzica de Bach ','Hortensia Papadat-Bengescu',50,10,'QC')
INSERT R VALUES(2,1,'Fata babei si fata mosneagului ','Ion Luca Caragiale',100,100,'QM')
INSERT R VALUES(2,2,'Frumosii nebuni ai marilor orase ','Fanus Neagu',10,10,'BA')
INSERT R VALUES(2,3,'3 Frumoasa calatorie a ursilor panda povestita de un saxofonist care avea o iubita la Frankfurt','Matei Visniec',100,20,'MQ')
INSERT R VALUES(3,1,'Mansarda la Paris cu vedere spre moarte ','Matei Visniec',200,10,'PQ')
INSERT R VALUES(3,2,'Richard al III-lea se interzice sau Scene din viata lui Meyerhold','Matei Visniec',100,50,'PQ')
INSERT R VALUES(3,3,'Masinaria Cehov. Nina sau despre fragilitatea pescarusilor impaiati','Matei Visniec',100,100,'AZ')
INSERT R VALUES(4,1,'Omul de zapada care voia sa intalneasca soarele ','Matei Visniec',100,100,'CP')
INSERT R VALUES(4,2,'Extraterestrul care isi dorea ca amintire o pijama','Matei Visniec',50,10,'CQ')
INSERT R VALUES(4,3,'O femeie draguta cu o floare si ferestre spre nord','Edvard Radzinski',10,100,'CP')
INSERT R VALUES(4,4,'Trenul din zori nu mai opreste aici ','Tennessee Williams',200,200,'MA')


SELECT C2, SUM(C3) TotalC3, AVG(C3) AvgC3
FROM R
WHERE C3 >= 100 OR C1 LIKE '%Pisica%'
GROUP BY C2
HAVING SUM(C3) > 100



SELECT *
FROM
 (SELECT FK1, FK2, C3+C4 TotalC3C4
 FROM R
 WHERE FK1 = FK2) r1
INNER JOIN
 (SELECT FK1, FK2, C5
 FROM R
 WHERE C5 LIKE '%Q%') r2 ON r1.FK1 = r2.FK1 AND r1.FK2 = r2.FK2


 CREATE OR ALTER TRIGGER TrOnUpdate
 ON R
 FOR UPDATE
AS
 DECLARE @total INT = 0
 SELECT @total = SUM(i.C3 - d.C3)
 FROM deleted d INNER JOIN inserted i ON d.FK1 = i.FK1 AND d.FK2 = i.FK2
 WHERE d.C3 < i.C3
 PRINT @total

 UPDATE R
SET C3 = 300
WHERE FK1 < FK2