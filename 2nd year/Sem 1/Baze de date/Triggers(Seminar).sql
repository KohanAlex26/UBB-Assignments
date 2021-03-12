CREATE TABLE Product(
	PID INT,
	PName VARCHAR(100),
	PQuantity INT
)

CREATE TABLE LogBuy(
	PName VARCHAR(100),
	OpDate DATE,
	Quantity INT
)

CREATE TABLE LogSell(
	PName VARCHAR(100),
	OpDate DATE,
	Quantity INT
)


INSERT Product VALUES(1,'a',1),(2,'b',2),(3,'c',3)

CREATE OR ALTER TRIGGER whenAddingProduct
	ON Product 
	FOR INSERT
		AS INSERT INTO LogBuy(PName,OpDate,Quantity) 
		SELECT PName,GETDATE(),PQuantity
		FROM inserted

select GETDATE()

INSERT Product VALUES(4,'a',5),(5,'b',5)


INSERT Product VALUES(6,'a',5)

CREATE OR ALTER TRIGGER whenDeletingProduct
	ON Product 
	FOR DELETE
		AS INSERT INTO LogSell(PName,OpDate,Quantity) 
		SELECT PName,GETDATE(),PQuantity
		FROM deleted

DELETE From Product WHERE PQuantity=5
SELECT * FROM LogSell
Select * FROM Product
Select * From LogBuy
