package ua.edu.ucu.stream.iterators;

import ua.edu.ucu.function.IntUnaryOperator;
import ua.edu.ucu.stream.IntStream;

import java.util.Iterator;

public class MapStreamIterator implements Iterator<Integer> {
    private final Iterator<Integer> stream;
    private final IntUnaryOperator action;

    public MapStreamIterator(Iterator<Integer> iterator, IntUnaryOperator action) {
        this.stream = iterator;
        this.action = action;
    }

    @Override
    public boolean hasNext() {
        return stream.hasNext();
    }

    @Override
    public Integer next() {
        return action.apply(stream.next());
    }
}
