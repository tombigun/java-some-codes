package com.java8;

public class TestInterface2 {


    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }


    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);

        Integer bxxx = converter.convert("123");
        System.out.println(bxxx);

        Converter<String, Integer> converter2 = Integer::valueOf;

        System.out.println(converter2.convert("555"));



        int num = 100;

        converter2.convert(num + "");

        num = 300;
    }


}
