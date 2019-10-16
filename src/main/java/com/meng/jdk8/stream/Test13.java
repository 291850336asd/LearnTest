package com.meng.jdk8.stream;


import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test13 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        Student student1 = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhangsan", 80, 40);
        List<Student> list = Arrays.asList(student1, student2, student3, student4);
        Map<String, List<Student>> nameS = list.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(nameS);
        Map<Integer, List<Student>> scoreS = list.stream().collect(Collectors.groupingBy(Student::getScore));
        System.out.println(scoreS);

        //统计
        Map<String, Long> nameCount = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        System.out.println(nameCount);
        Map<String, Double> nameSocreCount = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));
        System.out.println(nameSocreCount);
        //分区

        Map<Boolean, List<Student>>  partitionS = list.stream().collect(Collectors.partitioningBy(item -> item.getScore() >= 90));
        System.out.println(partitionS);
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
                    "name='" + name  +
                    ", score=" + score +
                    ", age=" + age +
                    '}';
        }
    }
}
