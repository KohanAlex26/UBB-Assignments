DELETE FROM Sportive;
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(6,'Pop','Ion','1999-10-02');
SELECT @@ERROR
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(2,'Gica','Dorel','2000-09-12');
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(3,'Ioan','Ramon','2001-02-03');

UPDATE Sportive
SET FirstName='Ana',DOB='2002-05-30'
WHERE SportiveId=2;
SELECT @@ROWCOUNT
SELECT @@VERSION
SELECT @@SERVERNAME

DELETE FROM Sportive WHERE LastName='Ramon' and FirstName='Ioan';
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(3,'Ioan','Ramon','2001-02-03');
INSERT INTO Sportive(SportiveId,FirstName,LastName) VALUES(4,'Lica','George');

UPDATE Sportive
SET DOB='2005-05-03'
WHERE DOB IS NULL;

UPDATE Sportive
SET FirstName='Marin'
WHERE SportiveId IN (2,7,8);

UPDATE Sportive
SET LastName='Grigore'
WHERE SportiveId BETWEEN 2 and 3;

UPDATE Sportive
SET LastName='Razvan'
WHERE FirstName LIKE 'P%';
INSERT INTO Sportive(SportiveId,FirstName,LastName,DOB) VALUES(5,'Ana','Maria','2011-03-03');

UPDATE Sportive
SET LastName='ion'
WHERE SportiveId=2