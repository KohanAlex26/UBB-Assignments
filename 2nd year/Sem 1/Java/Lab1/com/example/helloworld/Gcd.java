package com.example.helloworld;

public class Gcd {
    public static void main(String[] args) {
        System.out.println(gcd(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
    public static int gcd(int x,int y)
    {
        while(x!=y)
        {
            if(x>y)
                x=x-y;
            else
                y=y-x;
        }
        return x;
    }
    public static int lcm(int x,int y)
    {
        return (x*y)/gcd(x,y);
    }
}
