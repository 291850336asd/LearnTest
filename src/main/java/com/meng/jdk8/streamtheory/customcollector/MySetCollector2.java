package com.meng.jdk8.streamtheory.customcollector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MySetCollector2<T> implements Collector<T, Set<T>, Map<T,T>> {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","hello", "world", "welcome","a","hello", "world", "welcome");
        Map<String, String> map = list.stream().collect(new MySetCollector2<>());
//        Map<String, String> map = list.parallelStream().collect(new MySetCollector2<>());
        map.forEach((key, value)-> System.out.println(key + " : " + value));
    }

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        return HashSet<T>::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        return (ts, e) -> {
            System.out.println("accumulator " + Thread.currentThread().getName());
            ts.add(e);
        };
//        return Set<T>::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        return (item1, item2) ->{
            item1.addAll(item2);
            return item1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked!");
        return (set) -> {
            Map<T, T> map = new HashMap<>();
            set.forEach(item->map.put(item, item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
//        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED,Characteristics.CONCURRENT));
    }
}
