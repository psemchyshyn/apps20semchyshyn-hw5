package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

public interface IntStream {

    Double average();

    Integer max();

    Integer min();
    
    IntStream flatMap(IntToIntStreamFunction func);

    long count();

    IntStream filter(IntPredicate predicate);

    void forEach(IntConsumer action);

    IntStream map(IntUnaryOperator mapper);

    IntStream reduceStream(int identity, IntBinaryOperator op);

    int reduce(int identity, IntBinaryOperator op);

    Integer sum();

    int[] toArray();
}
