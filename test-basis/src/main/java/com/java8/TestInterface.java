package com.java8;

public class TestInterface {


    interface Formula {
        double calculate(int a);

        default double sqrt(int a){
            return Math.sqrt(a);
        }
    }


    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(9));
        System.out.println(formula.sqrt(9));


    }
}
