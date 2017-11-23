package com.java8;

import java.util.HashMap;
import java.util.Map;

public class TestMaps {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));


        System.out.println(map);


        map.computeIfPresent(3, (k, s) -> k+"");


        boolean b = map.remove(3, "33");
        System.out.println(b + map.get(3));

        String s = map.getOrDefault(3, "not found");
        System.out.println(s);


        String merge = map.merge(9, "aaa", (s1, s2) -> s1 + s2);
        System.out.println(merge);

        System.out.println(map);
    }
}
