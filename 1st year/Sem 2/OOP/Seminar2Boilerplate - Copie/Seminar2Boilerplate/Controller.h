#pragma once
#include "Planet.h"
#include "PlanetRepo.h"

typedef struct {
	PlanetRepo* repo;
	/// @todo future work keep operation stacks for undo and redo functionality (not now, later :) )
} Controller;

Controller createController(PlanetRepo* repo);

void loadPlanetData(Controller *ctrl);

/**
@brief Adds a new planet to the repository. 
@param ctrl - pointer to controller structure
@param planetName - the name of the new planet to add; the names are used to uniquely identity the planets 
@param planetType - the type of the planet
@param distanceToEarth - planet's distance to earth
@return true if the operation completed succesfully, false otherwise (planet already exists in repo, couldn't allocate memory etc.)
*/
bool addPlanet(Controller* ctrl, char* planetName, PlanetType planetType, float distanceToEarth);

/**
@brief Removes a planet to the repository.
@param ctrl - pointer to controller structure
@param planetName - the name of the planet to remove
@return true if the operation completed succesfully, false otherwise (the planet does not exist in the repository)
*/
bool removePlanet(Controller* ctrl, char* planetName);

/**
@brief Displays all the planets from the repository.
@param ctrl - pointer to controller structure
*/
void displayPlanets(Controller* ctrl);

/** 
@brief Searches for a planet with name planetName
@param ctrl - pointer to contrller structure
@param planetName - the name of the planet to search
@param pl [out] - the planet or nullptr if the planet does not exist in the repository
*/
int searchPlanetByName(Controller* ctrl, char* planetName, Planet* pl);

/**
@brief Searches and returns all the planets with given type
@param ctrl - pointer to contrller structure
@param type - the type of the planets to search
@param pl [out] - array containg the planets with the requested types
@param numPlanetsOfType [out] - the number of planets with the requested type
*/
void searchPlanetsByType(Controller *ctrl, PlanetType type, Planet pl[], int *numPlanetsOfType);



