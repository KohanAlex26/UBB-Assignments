SET ANSI_PADDING ON
SET ANSI_NULLS ON
SET ANSI_WARNINGS ON
SET CONCAT_NULL_YIELDS_NULL ON
SET NUMERIC_ROUNDABORT OFF
SET QUOTED_IDENTIFIER ON
--WITH SCHEMABINDING
STDEV,STDEVP,VAR,VARP

--fragmentation
---internal frag
---extent frag
---logical frag
--sys.dm_db_index_phisical_stats
--avg_fragmentation_in_percent
--avg_page_space_used_in_percent

--Control-of-Flow
--BEGIN..END
--BREAK
--CONTINUE
--GOTO label
--IF.ELSE
--WHILE
--RETURN
--RETURN intexpr
--THROW

BEGIN TRY
	SELECT 1/0
END TRY
BEGIN CATCH
	SELECT ERROR_NUMBER(),ERROR_SEVERITY(),ERROR_MESSAGE()
END CATCH

--WAITFOR
WAITFOR TIME '04:25'

WAITFOR DELAY '02:00'

--ARITH_ABORT ON