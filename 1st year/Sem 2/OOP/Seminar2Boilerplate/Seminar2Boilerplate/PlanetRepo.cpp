#include "PlanetRepo.h"
#include <stdlib.h>
#include <cstring>
#include <ctype.h>
#include <iostream>
using namespace std;
PlanetRepo createPlanetRepo(int capacity) {
	/// @todo create a new planet repo; the elems field must be dynamically allocated (malloc)
	PlanetRepo r;
	r.length = -1;	
	r.capacity = capacity;
	r.elems = (Planet*)malloc(sizeof(Planet)*r.capacity);
	return r;
}


void destroy(PlanetRepo* repo) {
	/// @todo destroy (free) the memory occupied by repo->elems
	free(repo->elems);
}

bool add(PlanetRepo* repo, Planet pl) {
	/// @todo add a new planet to the dynamically allocated array repo->elems
	/// you must also take into account the situation in which you want to add more planets that the capacity of the repo->elems (use realloc to resize it)
	int i = 0;
	bool found = false;
	while (i <= repo->length && found == false)
	{
		if (strcmp(getName(&repo->elems[i]), pl.name) == 0)
			found = true;
		else
		i++;
	}
	if(found==true)
		return false;
	if (repo->length == repo->capacity)
	{
		repo->elems = (Planet*)realloc(repo->elems, 2 * sizeof(Planet) * repo->capacity);
		repo->capacity = 2 * repo->capacity;
		if (repo->elems == nullptr)
			return false;
	}
	repo->elems[repo->length+1] = pl; 
	repo->length++;
	return true;
}

bool remove(PlanetRepo* repo, Planet pl) {
	/// @todo remove planet pl from the repository 
	/// return false if the planet does not exist in the repository
	int i = 0,j;
	bool found = false;
	while (i <= repo->length && found == false)
	{
		if (strcmp(repo->elems[i].name, pl.name) == 0)
			found = true;
		else
			i++;
	}
	if (found == false)
		return false;
	for (j = i;j <= repo->capacity - 1;j++)
		repo->elems[j] = repo->elems[j + 1];
	repo->length--;
	return true;
}

void ConvertToSmall(char s[]) {
	int i;
	for (i = 0;i <= strlen(s) - 1;i++)
		if (s[i] >= 'A' && s[i] <= 'Z')
			s[i] = s[i] + ('a' - 'A');
}

int findPlanet(PlanetRepo* repo, char* planetName) {
	/// @todo search for the planet with name planetName in the repository
	/// the comparison of the names should be CaSe inSenSITIVE :)
	int i = 0, j;
	bool found = false;
	while (i <= repo->length && found == false)
	{
		char aux[1000]="";
		strcpy(aux, repo->elems[i].name);
		ConvertToSmall(aux);
		if (strcmp(aux, planetName) == 0)
			return 1;
		i++;
	}
	return -1;
}
