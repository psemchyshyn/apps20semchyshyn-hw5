package ua.edu.ucu.stream;

import org.w3c.dom.traversal.DocumentTraversal;
import ua.edu.ucu.function.*;
import ua.edu.ucu.stream.iterators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AsIntStream implements IntStream, Iterable<Integer> {
    private Iterator<Integer> traversal;

    private AsIntStream(Iterator iterator) {
        traversal = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new ArrayIterator(values));
    }

    public boolean empty() {
        return !traversal.hasNext();
    }

    @Override
    public Double average() {
        if (empty()) throw  new IllegalArgumentException("Empty");

        int average = 0;
        int size = 0;
        for (Integer integer: this) {
            average += integer;
            size++;
        }
        return ((double) average) / size;
    }

    @Override
    public Integer max() {
        if (empty()) throw  new IllegalArgumentException("Empty");
        int max = (int) Double.NEGATIVE_INFINITY;
        for (Integer integer: this) {
            if (integer > max) {
                max = integer;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        if (empty()) throw  new IllegalArgumentException("Empty");
        int min = (int) Double.POSITIVE_INFINITY;
        for (Integer integer: this) {
            if (integer < min) {
                min = integer;
            }
        }
        return min;
    }

    @Override
    public long count() {
        int size = 0;
        for (Integer integer: this) {
            size++;
        }
        return size;
    }

    @Override
    public Integer sum() {
        if (empty()) throw  new IllegalArgumentException("Empty");
        int sum = 0;
        for (Integer integer: this) {
            sum += integer;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterStreamIterator(traversal, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (Integer integer: this) {
            action.accept(integer);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapStreamIterator(traversal, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(traversal, func));
    }

    @Override
    public IntStream reduceStream(int identity, IntBinaryOperator operator) {
        return new AsIntStream(new ReduceStreamIterator(traversal, identity, operator));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int temp = identity;
        for (Integer integer: (AsIntStream) reduceStream(identity, op)) {
            temp = integer;
        }
        return temp;
    }

    @Override
    public int[] toArray() {
        List<Integer> resArr = new ArrayList<>();
        for (Integer integer: this) {
            resArr.add(integer);
        }
        int[] arr = new int[resArr.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = resArr.get(i);
        }
        return arr;
    }

    @Override
    public Iterator<Integer> iterator() {
        return traversal;
    }
}
