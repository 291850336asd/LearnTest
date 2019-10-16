package com.meng.jdk8.lambda.objectfunctioninterface.example3function;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> {
            return "hello world";
        };
        System.out.println(supplier.get());
        System.out.println("---------");
        Supplier<Student> studentSupplier = () -> new Student("student",23);
        System.out.println(studentSupplier.get().getName());
        System.out.println("---------");
        Supplier<Student> studentSupplier2 = Student::new;
        System.out.println(studentSupplier2.get().getName());

    }


    @Getter
    @Setter
    private static class Student{
        private String name = "zhangsan";
        private int age = 2;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Student() {

        }
    }
}
