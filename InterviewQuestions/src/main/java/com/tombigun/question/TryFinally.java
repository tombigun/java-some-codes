package com.tombigun.question;

/**
 * @author tombigun
 * @version 1.0
 * @date 2018/1/17
 */
public class TryFinally {
    public static void main(String[] args) {
        System.out.println(new B().getValue());
    }

    static class A {
        protected int value;

        public A(int v) {
            setValue(v);
        }
        public void setValue(int value) {
            this.value = value;
        }
        public int getValue() {
            try {
                value++;
                return value;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
            return value;
        }
    }
    static class B extends A {
        public B() {
            super(5);
            setValue(getValue() - 3);
        }
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}