package vn.edu.ueh.bit.sol1;

public class Main {
    public static void main(String[] args) {
        Pipeline<Integer, Long> pipeline = Pipeline.of(input -> input.longValue() * 2);
        pipeline = pipeline
                .withNextPipe(input -> input + 4)
                .withNextPipe(input -> input + 2)
                .withNextPipe(input -> input + 5);
        Long result = pipeline.process(5);
        System.out.println(result);
    }
}
