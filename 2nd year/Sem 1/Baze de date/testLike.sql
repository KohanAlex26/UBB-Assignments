USE TestLike
GO

CREATE TABLE test(
ID INT PRIMARY KEY,
a VARCHAR(100),
)

INSERT test VALUES(1,' c'),(2,'d'),(3,'a b')
INSERT test VALUES(4,'a b')
INSERT test VALUES(5,'a b')
SELECT *
FROM test
WHERE a LIKE'a%_b'



SELECT a,AVG(ID) as e
FROM test
WHERE ID>0
GROUP BY a
ORDER BY e ASC