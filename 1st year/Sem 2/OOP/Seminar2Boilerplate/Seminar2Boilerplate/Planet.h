#ifndef PLANET_H
#define PLANET_H


#define _CRT_SECURE_NO_WARNINGS

typedef enum {
	NEPTUNE_LIKE, 
	GAS_GIANT, 
	TERRESTRIAL, 
	SUPER_EARTH, 
	UNKNOWN
}PlanetType;

typedef struct {
	char name[30];
	PlanetType type;
	float distanceToEarth;
}Planet;


/***
@brief Creates a new planet
@param name - the name of the new planet
@param type - the type of the new planet, see @link PlanetType @endlink for details
@param distance - new planet's distance to earth
@return a struct of type @link Planet @endlink 
*/
Planet createPlanet(char name[], PlanetType type, double distance);

/// return the pointer which points to the name of the planet
char* getName(Planet* p);

/// return the type of the planet
PlanetType getType(Planet* p);

/// return the distance from earth
double getDistanceFromEarth(Planet* p);

/***
@brief Function used to represent the Planet p as a string
@param p - a struct of type @link Planet @endlink
@param str [out] -  a string containing the description of planet p
*/
void toString(Planet p, char str[]);

#endif // !PLANET_H