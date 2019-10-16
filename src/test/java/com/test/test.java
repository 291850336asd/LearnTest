package com.test;

public class test {

    public static void  main(String args[]){
        String requestValue =  "String value = \"\";"
                + "if($args != null)\n" +
                "  {\n" +
                "for(Object o : $args) \n" +
                "{\n" +
                 " if(0 != null)" +
                " value += com.alibaba.fastjson.JSON.toJSONString(o);\n"  +
                "}\n" +
                "}\n"
                + "statistic.value=value;";
        System.out.println(requestValue);
    }
}
