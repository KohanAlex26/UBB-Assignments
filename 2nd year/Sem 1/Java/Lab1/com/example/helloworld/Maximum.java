package com.example.helloworld;

public class Maximum {
    public static void main(String[] args)
    {
        double mx=-10000;
        for(String i:args)
        {
            if(Double.parseDouble(i)>mx)
                mx=Double.parseDouble(i);
        }
        System.out.println(mx);
    }
}
