package com.java8;

import java.util.Optional;

public class TestOptionals {

    public static void main(String[] args) {


        Optional<String> o = Optional.of("bam");

        System.out.println(o.isPresent());
        System.out.println(o.get());
        System.out.println(o.orElse("fallback"));


    }
}
