package com.example.helloworld;

public class PrimeNumbers {
    public static void main(String[] args)
    {
        for(String i:args)
        {
            if(isPrime(Integer.parseInt(i)))
                System.out.println(i);
        }

    }
    public static boolean isPrime(int x)
    {
        if(x<2)
            return false;
        if(x==2)
            return true;
        if(x%2==0)
            return false;
        int d=3;
        while(d*d<=x)
        {
            if(x%d==0)
                return false;
            d=d+1;
        }
        return true;
    }


}