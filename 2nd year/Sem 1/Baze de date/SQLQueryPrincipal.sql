DROP TABLE Sportive_Trainer
DROP TABLE Sportive_Field
DROP TABLE Award
DROP TABLE Team
DROP TABLE Award
DROP TABLE Nutritionist
DROP TABLE Sportive
DROP TABLE TrainerBestAward
DROP TABLE Field
DROP TABLE Trainer

CREATE TABLE Sportive
	(SportiveId INT PRIMARY KEY ,
	FirstName VARCHAR(50),
	LastName VARCHAR(20),
	DOB DATE,
	TeamId INT FOREIGN KEY REFERENCES Team(TeamId) ON DELETE CASCADE,
	NutritionistId INT FOREIGN KEY REFERENCES Nutritionist(NutritionistId) ON DELETE CASCADE)

CREATE TABLE Award
	(AwardId INT PRIMARY KEY,
	DOA DATE,
	TeamId INT FOREIGN KEY REFERENCES Team(TeamId) ON DELETE CASCADE)

CREATE TABLE Team
	(TeamId INT PRIMARY KEY,
	TeamName VARCHAR(20))

CREATE TABLE Nutritionist
	(NutritionistId INT PRIMARY KEY,
	NutritionistName VARCHAR(20),
	YearsOfExperience INT)

CREATE TABLE Field
	(FieldId INT PRIMARY KEY,
	FieldName VARCHAR(20),
	Seats INT)
	 
CREATE TABLE Sportive_Field
	(SportiveId INT FOREIGN KEY REFERENCES Sportive(SportiveId) ON DELETE CASCADE,
	FieldId INT FOREIGN KEY REFERENCES Field(FieldId)
	PRIMARY KEY(SportiveId,FieldId))

CREATE TABLE Trainer
	(TrainerId INT PRIMARY KEY,
	FirstName VARCHAR(20),
	LastName VARCHAR(20),
	DOB DATE)

CREATE TABLE Sportive_Trainer
	(SportiveId INT FOREIGN KEY REFERENCES Sportive(SportiveId) ON DELETE CASCADE,
	TrainerId INT FOREIGN KEY REFERENCES Trainer(TrainerId)
	PRIMARY KEY(SportiveId,TrainerId))

CREATE TABLE TrainerBestAward
	(TrainerBestAwardId INT PRIMARY KEY,
	DOA DATE,
	Location VARCHAR(20),
	TrainerId INT UNIQUE FOREIGN KEY REFERENCES Trainer(TrainerId))