#pragma once
#include "Planet.h"

typedef struct{
	Planet* elems;		/** dynamic array containing the planets */
	int length;			/**  actual length of the array */
	int capacity;		/**  maximum capacity of the array */
}PlanetRepo;



/** 
@brief Creates a dynamic array of generic elements, with a given capacity.
@param capacity - maximum capacity for the planet repository
@return the created dynamic array
*/
PlanetRepo createPlanetRepo(int capacity);

/** 
@brief Destroys the repository (frees the allocated memory, capacity becomes 0, lenght becomes 0)
@param repo The planet repository to be destoyed
*/
void destroy(PlanetRepo* repo);

/** 
@brief Adds a new planet to the repository
@param repo - pointer to the planet repository
@param pl - the planet to be added
@return - true if the operation completed successfully, false otherwise
*/
bool add(PlanetRepo* repo, Planet pl);

/**
@brief Removes a new planet to the pository
@param repo - the planet repository
@param pl - the planet to be removed
@return - true if the operation completed successfully, false otherwise
*/
bool remove(PlanetRepo* repo, Planet pl);

/**
@brief Searches for a planet with name planetName in the repo.
@param - the planet repository
@param - the name of the planet to search
@return the index of the planet in the repository or -1 if the planet cannot be found in the repo.
*/
int findPlanet(PlanetRepo* repo, char* planetName);


void ConvertToSmall(char s[]);