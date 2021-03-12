#include "UserInterface.h"

#include <stdio.h>
#include <stdlib.h>
#include <cstring>

void printOptions() {
	printf("Please select an option: \n \t a\t - add a new planet\n \t r\t - remove a planet\n \t d\t - display all planets\n \t s\t - search for a planet with name\n \t e\t - exit\n");
}

void uiAddNewPlanet(Controller* ctrl) {
	system("@cls||clear");
	char c;
	char planetName[30];
	float dte;
	int planetType;
	int ok=false;
	printf("Insert new planet\n");
	printf("\tPlanet name: ");
	scanf("%c", &c);
	gets_s(planetName);
	printf("\tPlanet type (0 - neptune like, 1 - gas giant, 2 - terrestrial, 3 - super earth, 4 - unknown): ");
	scanf("%d", &planetType);
	printf("\tDistance to earth: ");
	scanf("%f", &dte);
	if (planetType == 0)
		ok = addPlanet(ctrl, planetName, NEPTUNE_LIKE, dte);
	else
	if (planetType == 1)
		ok = addPlanet(ctrl, planetName, GAS_GIANT, dte);
	else
	if (planetType == 2)
		ok = addPlanet(ctrl, planetName, TERRESTRIAL, dte);
	else
	if (planetType == 3)
		ok = addPlanet(ctrl, planetName, SUPER_EARTH, dte);
	else
	if (planetType == 4)
		ok = addPlanet(ctrl, planetName, UNKNOWN, dte);

	if (planetType >= 0 && planetType <= 4)
	{
		if (ok==true)
			printf("\nOperation completed successfully\n");
		else
			printf("\nWas not completed!\n");
	}
	else
		printf("\nWas not completed!\n");

}

void uiSearchPlanet(Controller* ctrl) {
	system("@cls||clear");
	char planetName[30];
	char c;
	scanf("%c", &c);
	printf("\tPlanet name: ");
	gets_s(planetName);

	char s[100] = "";
	Planet pl = createPlanet(s, UNKNOWN, 0);

	searchPlanetByName(ctrl, planetName, &pl);
	
	if (searchPlanetByName(ctrl, planetName, &pl)==1)
	{
		char aux[100] = "";
		toString(pl, aux);
		printf("%s\n", aux);
		printf("\nOperation completed successfully\n");
	}
	else
		printf("\nWas not completed!\n");

}
void uiRemovePlanet(Controller* ctrl) {
	system("@cls||clear");
	char planetName[30];
	char c;
	scanf("%c", &c);
	printf("\tPlanet name: ");
	gets_s(planetName);

	if (removePlanet(ctrl, planetName) == true)
		/// @todo check if operation completed successfully and display message to the user
		printf("\nOperation completed successfully\n");
	else
		printf("\nWas not completed!\n");
		/// @todo implementation

}

void uiDisplayPlanets(Controller* ctrl) {
	/// @todo implementation
	displayPlanets(ctrl);
}


void displayUI(Controller* ctrl) {
	char op;
	do {
		printOptions();
		scanf(" %c", &op);
		switch (op)
		{
		case 'a':
			uiAddNewPlanet(ctrl);
			break;
		case 'r':
			/// @todo
			uiRemovePlanet(ctrl);
			break;
		case 'd':
			/// @todo
			uiDisplayPlanets(ctrl);
			break;
		case 's':
			/// @todo
			uiSearchPlanet(ctrl);
			break;
		case 'e':
			printf("Exit.\n");
			exit(0);
		default:
			printf("Unknown option %c \n", op);
			continue;
		}
	} while (true);
}


