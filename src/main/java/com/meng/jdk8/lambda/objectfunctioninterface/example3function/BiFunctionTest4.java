package com.meng.jdk8.lambda.objectfunctioninterface.example3function;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class BiFunctionTest4 {

    public static void main(String[] args) {
        Person person = new Person("zhangsan", 20);
        Person person2 = new Person("lisi", 30);
        Person person3 = new Person("wangwu", 40);
        List<Person> persons = Arrays.asList(person, person2, person3);

        getPersonsByUserByName("lisi", persons).get(0).setAge(31);
        System.out.println(person2);

        System.out.println(getPersonsByUserByAge(31, persons).get(0));
        System.out.println(getPersonsByUserByAge(31, persons).get(0));
        System.out.println(getPersonsByUserByAge2(31, persons,
                ((integer, personList) ->
                        personList.stream().filter(personItem->personItem.getAge() > integer).collect(Collectors.toList()))).get(0));
    }


    public static List<Person> getPersonsByUserByName(String username, List<Person> personList){
        return personList.stream().filter(s -> s.getUserName().equals(username)).collect(Collectors.toList());
    }

    public static List<Person> getPersonsByUserByAge(int age, List<Person> persons){
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (integer, personList)
                -> personList.stream().filter(s-> s.getAge() > integer).collect(Collectors.toList());
        return biFunction.apply(age, persons);
    }

    public static List<Person> getPersonsByUserByAge2(int age, List<Person> persons,
                                                      BiFunction<Integer, List<Person>, List<Person>> biFunction){
        return biFunction.apply(age, persons);
    }

    private static class Person{
        private String userName;
        private int age;

        Person(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    '}';
        }
    }
}
