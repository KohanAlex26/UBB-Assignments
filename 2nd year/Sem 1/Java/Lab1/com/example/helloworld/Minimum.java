package com.example.helloworld;

public class Minimum {
    public static void main(String[] args)
    {
        double mn=10000;
        for(String i:args)
        {
            if(Double.parseDouble(i)<mn)
                mn=Double.parseDouble(i);
        }
        System.out.println(mn);
    }
}
