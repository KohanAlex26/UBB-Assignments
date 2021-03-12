#include "Controller.h"
#include "PlanetRepo.h"
#include <iostream>


Controller createController(PlanetRepo* repo) {
	Controller ctrl;
	ctrl.repo = repo;
	return ctrl;
}

void loadPlanetData(Controller *ctrl) {
	/// add some random data to the repsotiory
	/// optionally you can create a file and load the inital planet data from that file; this will give you some bonus points :)
	Planet pl = createPlanet((char*)"TOI-132 b", NEPTUNE_LIKE, 249.2);
	/// @todo after implementing the function addPlanet, use addPlanet insead of add
	addPlanet(ctrl, pl.name,pl.type,pl.distanceToEarth);
	pl = createPlanet((char*)"HD 219077 b", SUPER_EARTH, 311.3);
	addPlanet(ctrl, pl.name, pl.type, pl.distanceToEarth);
	pl = createPlanet((char*)"Nu Ophiuchi b", TERRESTRIAL, 28.13);
	addPlanet(ctrl, pl.name, pl.type, pl.distanceToEarth);
	pl = createPlanet((char*)"UCF-1.02", NEPTUNE_LIKE, 138.3);
	addPlanet(ctrl, pl.name, pl.type, pl.distanceToEarth);
	pl = createPlanet((char*)"WISE 1217+1626 b", UNKNOWN, 67.3);
	addPlanet(ctrl, pl.name, pl.type, pl.distanceToEarth);
	pl = createPlanet((char*)"Kepler-38b", GAS_GIANT, 134);
	addPlanet(ctrl, pl.name, pl.type, pl.distanceToEarth);
	pl = createPlanet((char*)"Kappa Andromedae b", TERRESTRIAL, 33.3);
	addPlanet(ctrl, pl.name, pl.type, pl.distanceToEarth);
}

bool addPlanet(Controller* ctrl, char* planetName, PlanetType planetType, float distanceToEarth) {
	///  a planet is uniquely identited by it's name. you must not allow the user to add two planets with the same name
	/// @todo search for a planet with name planetName does not exist in the controller's repository
	/// if it does not exist, add this planet to the repository, otherwise don't add it and return false
	// use the functions declared in PlanetRepo.h to interact with the repository
	PlanetRepo pr1 = *ctrl->repo;
	int i = 0;
	bool found = false;
	while (i <= ctrl->repo->length && found == false)
	{
		if (strcmp(ctrl->repo->elems[i].name, planetName) == 0)
			found = true;
		else
			i++;
	}
	if (found == true)
		return false;
	if (ctrl->repo->length == ctrl->repo->capacity)
	{
		ctrl->repo->elems = (Planet*)realloc(ctrl->repo->elems, 2 * sizeof(Planet) * ctrl->repo->capacity);
		ctrl->repo->capacity = 2 * ctrl->repo->capacity;
		if (ctrl->repo->elems == nullptr)
			return false;
	}
	Planet* p1 = new Planet();
	*p1 = createPlanet(planetName,planetType, distanceToEarth);
	ctrl->repo->elems[ctrl->repo->length + 1] = *p1;
	ctrl->repo->length++;
	return true;
}

bool removePlanet(Controller* ctrl, char* planetName) {
	/// @todo remove the planet with name planetName if it exists in the repo
	// use the functions declared in PlanetRepo.h to interact with the repository
	int i = 0, j;
	bool found = false;
	while (i <= ctrl->repo->length && found == false)
	{
		if (strcmp(ctrl->repo->elems[i].name, planetName) == 0)
			found = true;
		else
			i++;
	}
	if (found == false)
		return false;
	for (j = i;j <= ctrl->repo->capacity - 1;j++)
		ctrl->repo->elems[j] = ctrl->repo->elems[j + 1];
	ctrl->repo->length--;
	return true;
}

void displayPlanets(Controller* ctrl) {
	/// @todo display all the planets; hint: use the method void toString(Planet p, char str[]); declared in Planet.h
	using namespace std;
	int i;
	for (i = 0;i <= ctrl->repo->length;i++)
	{
		char s[100] = "";
		toString(ctrl->repo->elems[i], s);
		cout << s << endl;
	}
}


int searchPlanetByName(Controller* ctrl, char* planetName, Planet* pl) {
	/// @todo search planet by name , hint: you can use a function from the PlanetRepo module
	int i = 0;
	bool found = false;
	char aux1[100] = "";
	strcpy(aux1, planetName);
	ConvertToSmall(aux1);
	while (i <= ctrl->repo->length && found == false)
	{
		char aux[1000] = "";
		strcpy(aux, ctrl->repo->elems[i].name);
		ConvertToSmall(aux);
		if (strcmp(aux, aux1) == 0)
		{
			*pl = ctrl->repo->elems[i];
			found = true;
			return true;
		}
		i++;
	}
	return false;
}

void searchPlanetsByType(Controller* ctrl, PlanetType type, Planet pl[], int* numPlanetsOfType) {
	/// @todo search and return planets with type
	*numPlanetsOfType = 0;
	int i,j=-1;
	for(i=0;i <= ctrl->repo->length;i++)
		if (getType(&ctrl->repo->elems[i]) == type)
		{
			(*numPlanetsOfType)++;
			j++;
			pl[j] = ctrl->repo->elems[i];
		}
}