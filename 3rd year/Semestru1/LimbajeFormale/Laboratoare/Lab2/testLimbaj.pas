char a="Q"; 0a
int k;
read(k);
int i;
i=2;
int c;
c=0;
while(c!=k)
{
	bool ok;
	ok=false;
	int d;
	d=2;
	while(d*d<=i)
	{
		if(i%d==0)
			ok=true;
		d=d + 1;
	}	
	if(ok==false)
	{
		write(i);
		c=c + 1;
	}
	i=i + 1;
}
