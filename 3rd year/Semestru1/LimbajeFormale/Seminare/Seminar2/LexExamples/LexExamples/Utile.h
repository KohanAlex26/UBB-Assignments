#include <map>

#include <iterator>
#include <vector>
#include <iostream>
#include <fstream>
using namespace std;

std::vector<std::string> TS;  




typedef struct {
 int n;
 int elem[200][2];
} FIP;




void addFIP(int cod, int posTS, FIP& f){
 f.elem[f.n][0]=cod;
 f.elem[f.n++][1]=posTS;
}

FIP fip;
int pozTS=0;

void printFIP(FIP& f){
	ofstream fipFile;
    fipFile.open("FIP.txt");
	cout<<"nr elem fip"<<f.n<<endl;
	cout<<"Forma interna a programului: "<<endl;
	for(int i=0;i<f.n;i++){
		fipFile<<f.elem[i][0]<<" "<<f.elem[i][1]<<endl;
	         cout<<f.elem[i][0]<<" "<<f.elem[i][1]<<endl;
	}

	fipFile.close();
}
