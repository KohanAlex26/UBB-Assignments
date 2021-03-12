INSERT INTO Team(TeamId,TeamName,SportiveId) VALUES(1,'Madrid',3);
INSERT INTO Team(TeamId,TeamName,SportiveId) VALUES(2,'Manchester',2);
INSERT INTO Team(TeamId,TeamName,SportiveId) VALUES(3,'Liverpool',1);
UPDATE Team
SET TeamName='Manchester'
WHERE SportiveId=1 or SportiveId=3;

INSERT INTO Team(TeamId,TeamName) VALUES(4,'City');