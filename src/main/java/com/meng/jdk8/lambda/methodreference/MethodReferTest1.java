package com.meng.jdk8.lambda.methodreference;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferTest1 {

    //https://www.codementor.io/eh3rrera/using-java-8-method-reference-du10866vx
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world");
//        list.forEach(s-> System.out.println(s));
        list.forEach(System.out::println);


        System.out.println("----------1 Class::staticMethod----------");
        List<Integer> listNUmbers = Arrays.asList(12,5,45,18,33,24,40);
        findNumbers(listNUmbers, (v1, v2) -> isMoreThanFifty(v1, v2));
        findNumbers(listNUmbers, MethodReferTest1::isMoreThanFifty);

        Student student = new Student("zhangsan", 21);
        Student student2 = new Student("lisi", 24);
        Student student3 = new Student("wangwu", 51);
        Student student4 = new Student("zhaoliu", 11);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        Collections.sort(studentList, MethodReferTest1::compareStudentByScore);
        studentList.forEach(System.out::println);
        studentList.sort(MethodReferTest1::compareStudentByName);
        studentList.forEach(System.out::println);
        System.out.println("---------Instance method reference of an object of a particular type-----------");
        studentList.sort(student::compareStudentByScore);
        studentList.forEach(System.out::println);
        System.out.println("---------Class:instance method-----------");
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student other) {
                //studentList.sort(Student::compareStudentByName);   默认调用方式第一个参数，第二个或更多将作为参数
                return student1.compareStudentByName(other);
            }
        });
        studentList.sort((student1, other) -> student1.compareStudentByName(other));
        studentList.sort(Student::compareStudentByName);

        List<String> citys = Arrays.asList("bj", "qd","hb","sh","sjz");
//        Collections.sort(citys, (v1, v2)-> v1.compareTo(v2));
//        citys.forEach(System.out::println);
        Collections.sort(citys, String::compareToIgnoreCase);
        citys.forEach(System.out::println);
        System.out.println("-----------Constructor method reference ClassName::new----------");
        System.out.println(student.getString(()-> "hahah"));
        System.out.println(student.getString(String::new));
        System.out.println(student.getString2("hahha", v1 -> v1));
        System.out.println(student.getString2("hahha", String::new));
    }



    public static int compareStudentByScore(Student student1, Student student2){
        return  student1.getSocre() - student2.getSocre();
    }

    public static int compareStudentByName(Student student1, Student student2){
        return  student1.getName().charAt(0) - student2.getName().charAt(0);
    }

    public static boolean isMoreThanFifty(int n1, int n2){
        return n1 + n2 > 50;
    }

    public static List<Integer> findNumbers(List<Integer> list, BiPredicate<Integer, Integer> biPredicate){
        List<Integer> newList = new ArrayList<>();
        for (Integer item : list){
            if(biPredicate.test(item, item+ 10)){
                newList.add(item);
            }
        }
        return newList;
    }



    private static class Student {
        private int socre;
        private String name;

        public Student() {
        }


        public String getString(Supplier<String> stringSupplier){
            return stringSupplier.get() + "test";
        }

        public String getString2(String str, Function<String, String> function){
            return function.apply(str) + "test";
        }

        public  int compareStudentByScore(Student student1, Student student2){
            return  student1.getSocre() - student2.getSocre();
        }

        public int compareStudentBySocre(Student other){
            return getSocre() - other.getSocre();
        }

        public int compareStudentByName(Student other){
            return getName().compareTo(other.getName());
        }

        public Student(String name,int socre) {
            this.socre = socre;
            this.name = name;
        }

        public int getSocre() {
            return socre;
        }

        public void setSocre(int socre) {
            this.socre = socre;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "socre=" + socre +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}