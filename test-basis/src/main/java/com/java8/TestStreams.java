package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreams {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc1", "bbb2", "ddd1", "eee2", "eee1");


        Optional<String> a = strings.stream()
                .filter(s -> s.startsWith("a"))
                .reduce((s, s2) -> s + "#" + s2);

        System.out.println(a.get());
        a.ifPresent(System.out::println);

        System.out.println(strings);


        Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println);




        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok

    }
}
