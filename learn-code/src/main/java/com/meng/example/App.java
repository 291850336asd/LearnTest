package com.meng.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println(Math.abs("E004000000224495101".hashCode())%8 + 1);
        System.out.println( "Hello World!" );

        int i=2;
        i = i++;
        System.out.println(i);//2
        int j = i;
        System.out.println(j);

//        int i=2;
//        int j = i++;
//        System.out.println(i);
//        System.out.println(j);
    }
}
