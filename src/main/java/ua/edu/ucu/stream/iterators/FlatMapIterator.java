package ua.edu.ucu.stream.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private final Iterator<Integer> stream;
    private final IntToIntStreamFunction function;
    private Iterator<Integer> helper;

    public FlatMapIterator(Iterator iterator, IntToIntStreamFunction func) {
        stream = iterator;
        function = func;
    }
    @Override
    public boolean hasNext() {
        return (helper != null && helper.hasNext()) || stream.hasNext();
    }

    @Override
    public Integer next() {
        if (helper == null || !helper.hasNext()) {
            helper = ((AsIntStream) function.applyAsIntStream(stream.next())).iterator();
        }
        return helper.next();
    }
}
