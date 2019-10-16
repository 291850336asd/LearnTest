package com.meng.jdk8.streamtheory;

import java.util.*;
import java.util.stream.Collectors;

public class Test1Collectors {

    public static void main(String[] args) {

        Student student1 = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhaoliu", 80, 40);
        Student student5 = new Student("lisi", 90, 40);
        List<Student> list = Arrays.asList(student1, student2, student3, student4,student5);
        List<Student> list1 = list.stream().collect(Collectors.toList());
        list1.forEach(System.out::println);

        System.out.println("------------");
        long studentCount = list.stream().collect(Collectors.counting());
        System.out.println(studentCount);
        studentCount = list.stream().count();
        System.out.println(studentCount);

        System.out.println("------Collector--------");


        list.stream().collect(Collectors.minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        double avgStudentSocre = list.stream().collect(Collectors.averagingInt(Student::getScore));
        double sumStudentSocre = list.stream().collect(Collectors.summingInt(Student::getScore));
        System.out.println(avgStudentSocre);
        System.out.println(sumStudentSocre);
        IntSummaryStatistics intSummaryStatistics = list.stream().collect(Collectors.summarizingInt(Student::getScore));
        System.out.println(intSummaryStatistics.getAverage());
        String studentNames = list.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(studentNames);
        studentNames = list.stream().map(Student::getName).collect(Collectors.joining(",", "begin ", " end"));
        System.out.println(studentNames);
        System.out.println("-------------");
        list.stream().collect(Collectors.groupingBy(Student::getScore,Collectors.groupingBy(Student::getName))).forEach((key, value)->{
            value.forEach((itemKey, itemValue) ->{
                itemValue.forEach(item ->System.out.println("key -> " + key + "  value -> " + "itemkey :" + itemKey + " itemvalue : " + itemValue));
            });
        });
        System.out.println("--------------");
        list.stream().collect(Collectors.partitioningBy(item -> item.getScore() > 80)).forEach((key, value) -> {
            System.out.println(key + "   "  + value);
        });
        System.out.println("--------------");
        list.stream().collect(Collectors.partitioningBy(item -> item.getScore() > 80, Collectors.partitioningBy(item-> item.getScore() > 90))).forEach((key, value) -> {
            System.out.println(key + "   "  + value);
        });
        System.out.println("---------------");
        list.stream().collect(Collectors.partitioningBy(item -> item.getScore() > 80,Collectors.counting())).forEach((item, value)-> {
            System.out.println(item + "  " + value);
        });
        System.out.println("---------------");
        Map<String, Student> studentMap = list.stream().collect(Collectors.groupingBy(Student::getName,Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Student::getScore)),Optional::get)));
        studentMap.forEach((key, value) -> System.out.println(key + "  " + value));
    }


    private static class Student{
        private String name;
        private int score;
        private int age;

        public Student(String name, int score, int age) {
            this.name = name;
            this.score = score;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name=" + name  +
                    ", score=" + score +
                    ", age=" + age +
                    '}';
        }
    }

}
