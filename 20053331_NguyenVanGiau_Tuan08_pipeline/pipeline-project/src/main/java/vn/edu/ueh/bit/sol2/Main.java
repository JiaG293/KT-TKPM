package vn.edu.ueh.bit.sol2;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> square = init();
        Function<Integer, Integer> half = step01();
        Function<Integer, String> toString = finalStep();
        Function<Integer, String> pipeline = square.andThen(half)
                .andThen(toString);
        String result = pipeline.apply(5);
        System.out.println(result);
    }

    private static Function<Integer, String> finalStep() {
        return Object::toString;
    }

    private static Function<Integer, Integer> step01() {
        return s -> s / 2;
    }

    private static Function<Integer, Integer> init() {
        //....
        return s -> s * s;
    }
}
