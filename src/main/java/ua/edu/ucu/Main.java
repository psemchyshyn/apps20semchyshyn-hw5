package ua.edu.ucu;

import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;


public class Main {
    public static void main(String[] args) {
//        IntPredicate predicate = new IntPredicate() {
//            @Override
//            public boolean test(int value) {
//                return value % 2 == 0;
//            }
//        };
//
//        IntUnaryOperator mapper = new IntUnaryOperator() {
//            @Override
//            public int apply(int operand) {
//                return operand * 2;
//            }
//        };
//
//        IntBinaryOperator operator = new IntBinaryOperator() {
//            @Override
//            public int apply(int left, int right) {
//                return left + right;
//            }
//        };
//        IntToIntStreamFunction function = new IntToIntStreamFunction() {
//            @Override
//            public IntStream applyAsIntStream(int value) {
//                return AsIntStream.of(value, 10*value, 100*value);
//            }
//        };
//
//        IntBinaryOperator op = new IntBinaryOperator() {
//            @Override
//            public int apply(int left, int right) {
//                return left + right;
//            }
//        };
//        IntStream stream = AsIntStream.of(0, 5, 6, 7, 8, 9);
//        int[] arr = stream
//                .filter(predicate)
//                .map(mapper)
//                .flatMap(function)
//                .reduceStream(0, op)
//                .toArray();
//        for (Integer integer: arr) {
//            System.out.println(integer);
//        }
        IntStream intStream = AsIntStream.of(-1, 0, 1, 2, 3); // input values
        int[] res = intStream
                .filter(x -> x > 0) // 1, 2, 3
                .map(x -> x * x) // 1, 4, 9
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)) // 0, 1, 2, 3, 4, 5, 8, 9, 10
                .toArray();
//                .reduce(0, (sum, x) -> sum += x); // 42
        for (Integer integer: res) {
            System.out.println(integer);
        }
    }
}
