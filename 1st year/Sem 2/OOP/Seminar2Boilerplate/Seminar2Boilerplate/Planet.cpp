#include "Planet.h"
#include <iostream>
#include <string.h>


Planet createPlanet(char name[], PlanetType type, double distance) {
	Planet pl;
	/// @todo set planet's pl fields to the values set as parameters function's parameters;
	// hint: use strcpy for the planet's name strcpy(pl.name, name);
	/*pl.distanceToEarth = 0;
	pl.type = UNKNOWN;
	*/
	strcpy(pl.name, name);
	pl.type = type;
	pl.distanceToEarth = distance;
	return pl;
}
char* getName(Planet* p) {
	/// @todo return planet's p name
	return p->name;
}
PlanetType getType(Planet* p) {
	/// @todo return planet's p type
	return p->type;
}

double getDistanceFromEarth(Planet* p) {
	/// @todo return planet's p distance to earth
	return p->distanceToEarth;
}
 
void toString(Planet p, char str[]) {

	char planetTypeToString[5][30] = {
		"NEPTUNE_LIKE",
		"GAS_GIANT",
		"TERRESTRIAL",
		"SUPER_EARTH",
		"UNKNOWN"
	};
	sprintf(str, "Planet %s is a %s planet and its distance to Earth is %.2lf.", p.name, planetTypeToString[p.type], p.distanceToEarth);
}