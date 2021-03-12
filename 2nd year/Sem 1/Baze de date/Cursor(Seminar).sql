DECLARE @SportiveID char(10), @FirstName varchar(50)

DECLARE SportiveCursor CURSOR FOR
	SELECT SportiveId,FirstName
	FROM Sportive

OPEN SportiveCursor 
	FETCH NEXT FROM SportiveCursor
	INTO @SportiveID,@FirstName
	WHILE @@FETCH_STATUS=0
		BEGIN
			PRINT @SportiveID + ' ' + @FirstName --CAST(@SportiveID AS VARCHAR(10))
			FETCH NEXT FROM SportiveCursor
			INTO @SportiveID,@FirstName
		END
CLOSE SportiveCursor
DEALLOCATE SportiveCursor

SELECT * FROM Sportive

DECLARE @SportiveId1 VARCHAR(30) ='1', @SqlSt VARCHAR(max)
SET @SqlSt='SELECT SportiveId FROM Sportive Where SportiveId='+@SportiveId1
EXEC(@SqlSt)
PRINT @SqlSt


--sp_executesql

DECLARE @SportiveID3 NVARCHAR(300),@SqlSt1 NVARCHAR(max),@ParmDefinition NVARCHAR(300)=N'@SportiveId NVARCHAR(300)';
SET @SqlSt1=N'Select SportiveId From Sportive WHERE SportiveId = @SportiveId';
PRINT @SqlSt1
SET @SportiveID3=1
EXECUTE sp_executesql @SqlSt1,@ParmDefinition,
						@SportiveId=@SportiveID3;
