package com.example.helloworld;

public class HelloWorld {
    public static void main(String[] args)
    {
        int sum=0;
        for(String i:args)
        {
            sum=sum+Integer.parseInt(i);
        }
        System.out.println(sum);
    }
}
