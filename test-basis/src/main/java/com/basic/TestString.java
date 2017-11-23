package com.basic;

public class TestString {

    public static void main(String[] args) {

        String s1 = "100";
        String s2 = "100";

        String s3 = new String("100");
        String s4 = new String("100");

        System.out.println(s1 == s2);
        System.out.println(s3 == s4);


        String s5 = new String("200");
        String s6 = "200";

        System.out.println(s5 == s6);
    }
}
