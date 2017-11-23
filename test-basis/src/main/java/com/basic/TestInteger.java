package com.basic;

public class TestInteger {

    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;

        Integer i3 = 1000;
        Integer i4 = 1000;


        Integer i5 = new Integer(100);
        Integer i6 = new Integer(100);


        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);

    }
}
