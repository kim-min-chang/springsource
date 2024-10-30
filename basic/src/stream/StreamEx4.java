package stream;

import java.util.List;
import java.util.stream.IntStream;

public class StreamEx4 {
    public static void main(String[] args) {
        IntStream stream = IntStream.rangeClosed(1, 10);
        // 2의 배수 개수
        System.out.println("2의 배수 개수 " + stream.filter(i -> i % 2 == 0).count());
        // 합
        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 합 " + stream.filter(i -> i % 2 == 0).sum());
        // 평균
        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 평균 " + stream.filter(i -> i % 2 == 0).average());
        // 최대값
        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 최대값 " + stream.filter(i -> i % 2 == 0).max());
        // 최소값
        stream = IntStream.rangeClosed(1, 10);
        System.out.println("2의 배수 최소값 " + stream.filter(i -> i % 2 == 0).min());

        // Optional : NullPointerException
        // List<String> list;

    }
}
