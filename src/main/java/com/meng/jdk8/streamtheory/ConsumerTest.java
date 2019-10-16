package com.meng.jdk8.streamtheory;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerTest {

    public static void main(String[] args) {
        ConsumerTest consumerTest = new ConsumerTest();
        IntConsumer intConsumer = item -> {
            item = item +1;
            System.out.println(item);
        };
        Consumer consumer = item -> {
            item = item + "--" + 1;
            System.out.println(item);
        };

        System.out.println(consumer instanceof  Consumer);
        System.out.println(consumer instanceof  IntConsumer);
        System.out.println(intConsumer instanceof  Consumer);
        System.out.println(intConsumer instanceof  IntConsumer);
        consumerTest.test(consumer);
        consumerTest.test(consumer::accept);
        consumerTest.test(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                consumer.accept(t);
            }
        });
        consumerTest.test(intConsumer::accept);

    }

    public void test(Consumer<Integer> action){
        System.out.println("---"  + (action instanceof  Consumer));
        action.accept(1000);
    }
}
