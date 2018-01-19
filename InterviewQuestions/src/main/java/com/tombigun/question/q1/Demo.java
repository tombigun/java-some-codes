package com.tombigun.question.q1;

/**
 * @author tombigun
 * @version 1.0
 * @date 2018/1/17
 */
public class Demo {

    public static void main(String[] args) {
        SuperClass clz = new SubClass();
        //你觉得这里输出什么?
        System.out.println(clz.name);
    }

}
