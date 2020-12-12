package ua.edu.ucu.stream.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterStreamIterator implements Iterator<Integer> {
    private final Iterator<Integer> stream;
    private final IntPredicate predicate;
    private int curr;

    public FilterStreamIterator(Iterator<Integer> iterator, IntPredicate predicate) {
        this.stream = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while (stream.hasNext()) {
            curr = stream.next();
            if (predicate.test(curr)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return curr;
    }
}
