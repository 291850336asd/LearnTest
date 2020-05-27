package com.meng.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println(getStr());
    }

    public static String getStr(){
        String str = null;
        try{
            str ="hello";
            int i = 1/0;
            return str;
        }catch (Exception e){
        }
        finally {
            System.out.println("----");
            str = "name";
        }
        return str;
    }
}
