package com.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestLambda {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("tom", "jack", "mike", "jk");

        System.out.println(names);

        /*Collections.sort(names);
        System.out.println(names);*/

        /*Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });*/

//        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));

//        Collections.sort(names, String::compareTo);

        names.sort(String::compareTo);


        System.out.println(names);



    }
}
