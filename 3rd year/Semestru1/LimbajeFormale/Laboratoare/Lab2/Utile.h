#include <map>

#include <iterator>
#include <vector>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

std::vector<std::string> TS;  


typedef struct {
 int n;
 int elem[200][2];
} FIP;

typedef struct {
 int n;
 char elem[200][10];
} ST;



void addFIP(int cod1, int posTS, FIP& fip){
 fip.elem[fip.n][0]=cod1;
 fip.elem[fip.n++][1]=posTS;
}

void addFIP2(int cod1, char* cod, int len, ST& f,FIP& fip){
 	fip.elem[fip.n][0]=cod1;
	bool ok=true;
	int i=0;
	while(i!=f.n && ok==true)
	{
		int j=0;
		while(f.elem[i][j]==cod[j] && j!=len)
		{
			j++;
		}
		if(j==len)
			ok=false;
		i++;
	}

		fip.elem[fip.n++][1]=i;
}

void addST(char* cod, int len, ST& f){
	bool ok=true;
	int i=0;
	while(i!=f.n && ok==true)
	{
		int j=0;
		while(f.elem[i][j]==cod[j] && j!=len)
		{
			j++;
		}
		if(j==len)
			ok=false;
		i++;
	}
	if(ok==true)
	{
		i=0;
		while(i!=len)
		{
			f.elem[f.n][i]=cod[i];
			i++;
		}
		f.n++;
	}
	// f.elem[f.n][i]=posTS;
	// i++;
	// f.elem[f.n][i]='\0';
	// i++;
}



FIP fip;
ST st;
int pozTS=0;
int nr=0;

void printFIP(FIP& f){
	ofstream fipFile;
    fipFile.open("FIP.txt");
	cout<<"nr elem fip "<<f.n<<endl;
	cout<<"Forma interna a programului: "<<endl;
	for(int i=0;i<f.n;i++){
		fipFile<<f.elem[i][0]<<" "<<f.elem[i][1]<<endl;
	         cout<<f.elem[i][0]<<" "<<f.elem[i][1]<<endl;
	}

	fipFile.close();
}

void printST(ST& f){
	ofstream stFile;
    stFile.open("ST.txt");
	cout<<"nr elem st "<<f.n<<endl;
	cout<<"Symbol table: "<<endl;
	for(int i=0;i<f.n;i++){
		int j=0;
		stFile<<i<<" ";
		cout<<i<<" ";
		while(f.elem[i][j]!='\0')
		{
			stFile<<f.elem[i][j];
	         cout<<f.elem[i][j];
			 j++;
		}
		// stFile<<" "<<f.elem[i][j];
		// cout<<" "<<f.elem[i][j];	
		stFile<<endl;
		cout<<endl;	
	}

	stFile.close();
}