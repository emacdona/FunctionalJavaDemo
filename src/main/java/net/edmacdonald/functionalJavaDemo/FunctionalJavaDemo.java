package net.edmacdonald.functionalJavaDemo;

import fj.F;
import fj.data.Java;
import fj.data.List;

import java.util.ArrayList;
import java.util.Arrays;

public class FunctionalJavaDemo {
    public static void main(String[] args)
    {
        List<Integer> integers = S.convertList(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));

        List<Double> doubles = List.list(   3.1415, /* approx pi                    */
                                            0.5772, /* approx Euler's constant      */
                                            1.6180  /* approx phi (golden ratio)    */
        );

        System.out.println("Integers");
        integers.map(S.<Integer>display());

        System.out.println();
        System.out.println("Doubles");
        doubles.map(S.<Double>display());

        System.out.println();
        System.out.println("Odd numbers");
        integers.filter(S.oddFilter()).map(S.<Integer>display());

        System.out.println();
        System.out.println("Even numbers");
        integers.filter(S.evenFilter()).map(S.<Integer>display());

        System.out.println();
        System.out.println("Numbers divisible by 2 and 3");
        integers.filter(S.divisibleByFilter(2)).filter(S.divisibleByFilter(3)).map(S.<Integer>display());

        System.out.println();
        System.out.println("Numbers divisible by 2 and 5");
        integers.filter(S.divisibleByFilter(2)).filter(S.divisibleByFilter(5)).map(S.<Integer>display());

    }
}

class S{ /* S for "static" cuz this class has a bunch of static methods */
    public static <T> F<T,T> display() {
        return new F<T, T>(){
            public T f(T t) {
                System.out.println(t);
                return t;
            }
        };
    }

    public static F<Integer, Boolean> oddFilter() {
        return new F<Integer, Boolean>(){
            public Boolean f(Integer integer) {
                return (integer % 2) == 1;
            }
        };
    }

    public static F<Integer, Boolean> evenFilter() {
        return new F<Integer, Boolean>(){
            public Boolean f(Integer integer) {
                return (integer % 2) == 0;
            }
        };
    }

    public static F<Integer, Boolean> divisibleByFilter(final Integer divisor) {
        return new F<Integer, Boolean>(){
            public Boolean f(Integer integer) {
                return (integer % divisor) == 0;
            }
        };
    }

    public static <T> List<T> convertList(java.util.List<T> list){
        return Java.<T>ArrayList_List().f(new ArrayList<T>(list));
    }
}
