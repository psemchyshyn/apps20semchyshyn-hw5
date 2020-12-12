package ua.edu.ucu.stream.iterators;

import java.util.Iterator;

public class ArrayIterator implements Iterator<Integer>{

    private int curr;
    private int[] elements;
    public ArrayIterator(int[] values) {
        elements = values;
    }

    @Override
    public boolean hasNext() {
        return curr < elements.length;
    }

    @Override
    public Integer next() {
        Integer data = elements[curr++];
        return data;
    }
}
