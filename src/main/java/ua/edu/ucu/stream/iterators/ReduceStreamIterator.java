package ua.edu.ucu.stream.iterators;

import ua.edu.ucu.function.IntBinaryOperator;

import java.util.Iterator;

public class ReduceStreamIterator implements Iterator<Integer> {
    private final Iterator<Integer> stream;
    private final IntBinaryOperator operator;
    private int identity;

    public ReduceStreamIterator(Iterator<Integer> iterator, int identity, IntBinaryOperator operator) {
        stream = iterator;
        this.operator = operator;
        this.identity = identity;
    }
    @Override
    public boolean hasNext() {
        return stream.hasNext();
    }

    @Override
    public Integer next() {
        identity = operator.apply(identity, stream.next());
        return identity;
    }
}
