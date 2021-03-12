#include "Planet.h"
#include "PlanetRepo.h"
#include "Controller.h"
#include "UserInterface.h"
#include <iostream>
using namespace std;

int main() {
	PlanetRepo pr;
	int repoSize = 100;
	pr = createPlanetRepo(repoSize);
	 Controller ctrl;
	ctrl = createController(&pr);
	loadPlanetData(&ctrl);
	/*displayUI(&ctrl);*/

	/*int repoSize = 100;
	PlanetRepo pr = createPlanetRepo(repoSize);
	Controller ctrl = createController(&pr);*/
	char s[] = "a";
	////char g[100] = "";
	Planet p1;
	p1= createPlanet(s, UNKNOWN, 213);
	cout << p1.type;
	//cout << getName(&p1)<<endl;
	/*<<getType(&p1)<<endl<<getDistanceFromEarth(&p1)<<endl;*/
	/*toString(p1, g);*/
	/*pr.elems[0] = p1;*/
	//cout << pr.capacity << endl << pr.length<<endl;
	/*destroy(&pr);*/
	/*cout << pr.elems[0].name;*/
	/*add(&pr, p1);
	cout << getDistanceFromEarth(&ctrl.repo->elems[0]);*/
	/*remove(&pr, p1);
	cout << pr.elems[0].name;*/
	//char l[100] = "b";
	/*cout << findPlanet(&pr, l);*/
	/*ConvertToSmall(l);*/
	/*cout << l;*/
	/*char op;
	scanf(" %c", &op);
	char planetName[30];
	float dte;
	int planetType;
	printf("Insert new planet\n");
	printf("\tPlanet name: ");
	scanf("%s", planetName);

	printf("\tDistance to earth: ");
	scanf("%f", &dte);
	addPlanet(&ctrl, planetName, UNKNOWN, dte);
	displayPlanets(&ctrl);*/
	//loadPlanetData(&ctrl);
	/*addPlanet(&ctrl, l, UNKNOWN, 123);*/
	/*cout << pr.elems[1].type;*/
	//loadPlanetData(&ctrl);
	/*cout << pr.elems[2].name<<endl;*/
	/*char m[100] = "TOI-132 b";
	cout<<removePlanet(&ctrl, m);*/
	/*cout << pr.elems[2].name<<endl;*/
	/*displayPlanets(&ctrl);*/
	//char n[100] = "HD 219077 b";
	/*char x[100] = "";
	toString(p1, x);
	cout << x<<endl;*/
	//Planet p3;
	/*searchPlanetByName(&ctrl, n, &p3);*/
	/*char z[100] = "";*/
	/*toString(p3, z);*/
	//displayPlanets(&ctrl);
	//cout << endl << endl;
	/*Planet p2[100];
	int numPlanetsOfType=0;*/
	/*cout << numPlanetsOfType;*/
	/*searchPlanetsByType(&ctrl, TERRESTRIAL, p2, &numPlanetsOfType);*/
	//cout << numPlanetsOfType;
	/*for(int i=0;i<=numPlanetsOfType-1;i++)
	{
		char d[100] = "";
		toString(p2[i], d);*/
		//cout << d << endl;
	/*}*/
	/*cout << z;*/
	/*searchPlanetsByType(&ctrl, TERRESTRIAL,)*/

	/*Planet* p2 = new Planet();
	*p2 = createPlanet(g, NEPTUNE_LIKE, 245);*/
	/**p1 = *p2;*/
	/*cout << getType(p1);*/

	/*add(&pr, *p1);*/
	//cout << getName(&pr.elems[0]);
	/*cout << pr.elems[0].name;*/

	/*add(&pr, *p2);*/
	/*cout << pr.length;*/

	/*remove(&pr, *p1);*/
	/*cout << pr.length;*/
	//cout << pr.elems[0].type;
	/*char l[1000] = "";
	toString(*p1, l);*/
	//cout << l;
	/*char c[100] = "a";*/
	/*cout << findPlanet(&pr, c);*/
	/*loadPlanetData(&ctrl);*/
	return 0;
}