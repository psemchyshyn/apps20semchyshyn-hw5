package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.zip.Checksum;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    
    private IntStream intStreamEmpty;
    private IntStream intStreamOne;
    private IntStream intStreamMany;

    @Before
    public void init() {
        int[] arrEmpty = {};
        int[] arrOne = {5};
        int[] arrMany = {-1, 0, 1, 2, 3};
        intStreamEmpty = AsIntStream.of(arrEmpty);
        intStreamOne = AsIntStream.of(arrOne);
        intStreamMany = AsIntStream.of(arrMany);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        double res = intStreamEmpty.average();
    }

    @Test
    public void testAverageOne() {
        double res = intStreamOne.average();
        double expected = 5.0;
        assertEquals(expected, res, 0.01);
    }

    @Test
    public void testAverageMany() {
        double res = intStreamMany.average();
        double expected = 1.0;
        assertEquals(expected, res, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumEmpty() {
        int res = intStreamEmpty.sum();
    }

    @Test
    public void testSumOne() {
        int res = intStreamOne.sum();
        int expected = 5;
        assertEquals(expected, res);
    }

    @Test
    public void testSumMany() {
        int res = intStreamMany.sum();
        int expected = 5;
        assertEquals(expected, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() {
        int res = intStreamEmpty.max();
    }

    @Test
    public void testMaxOne() {
        int res = intStreamOne.max();
        int expected = 5;
        assertEquals(expected, res);
    }

    @Test
    public void testMaxMany() {
        int res = intStreamMany.max();
        int expected = 3;
        assertEquals(expected, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmpty() {
        int res = intStreamEmpty.min();
    }

    @Test
    public void testMinOne() {
        int res = intStreamOne.min();
        int expected = 5;
        assertEquals(expected, res);
    }

    @Test
    public void testMinMany() {
        int res = intStreamMany.min();
        int expected = -1;
        assertEquals(expected, res);
    }

    @Test
    public void testCountEmpty() {
        long res = intStreamEmpty.count();
        long expected = 0;
        assertEquals(expected,res);
    }

    @Test
    public void testCountOne() {
        long res = intStreamOne.count();
        long expected = 1;
        assertEquals(expected,res);
    }

    @Test
    public void testCountMany() {
        long res = intStreamMany.count();
        long expected = 5;
        assertEquals(expected,res);
    }

    @Test
    public void testReduceEmpty() {
        long res = intStreamEmpty.reduce(1, (product, x) -> product *= x);
        long expected = 1;
        assertEquals(expected, res);
    }

    @Test
    public void testReduceOne() {
        long res = intStreamOne.reduce(1, (product, x) -> product *= x);
        long expected = 5;
        assertEquals(expected, res);
    }

    @Test
    public void testReduceMany() {
        long res = intStreamMany.reduce(1, (product, x) -> product *= x);
        long expected = 0;
        assertEquals(expected,res);
    }

    @Test
    public void testToArrayEmpty() {
        int[] res = intStreamEmpty.toArray();
        int[] expected = {};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testToArrayOne() {
        int[] res = intStreamOne.toArray();
        int[] expected = {5};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testToArrayMany() {
        int[] res = intStreamMany.toArray();
        int[] expected = {-1, 0, 1, 2, 3};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testFilterEmpty() {
        int[] res = intStreamEmpty.filter( a -> a < 0).toArray();
        int[] expected = {};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testFilterOne() {
        int[] res = intStreamOne.filter( a -> a < 0).toArray();
        int[] expected = {};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testFilterMany() {
        int[] res = intStreamMany.filter( a -> a < 0).toArray();
        int[] expected = {-1};
        assertArrayEquals(expected, res);
    }


    @Test
    public void testMapEmpty() {
        int[] res = intStreamEmpty.map( a -> a*a).toArray();
        int[] expected = {};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testMapOne() {
        int[] res = intStreamOne.map( a -> a*a).toArray();
        int[] expected = {25};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testMapMany() {
        int[] res = intStreamMany.map( a -> a*a).toArray();
        int[] expected = {1, 0, 1, 4, 9};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testForEachEmpty() {
        StringBuilder res = new StringBuilder();
        intStreamEmpty.forEach(x -> res.append(x));
        String expected = "";
        assertEquals(expected, res.toString());
    }

    @Test
    public void testForEachOne() {
        StringBuilder res = new StringBuilder();
        intStreamOne.forEach(x -> res.append(x));
        String expected = "5";
        assertEquals(expected, res.toString());
    }

    @Test
    public void testForEachMany() {
        StringBuilder res = new StringBuilder();
        intStreamMany.forEach(x -> res.append(x));
        String expected = "-10123";
        assertEquals(expected, res.toString());
    }

    @Test
    public void testFlatMapEmpty() {
        int[] res = intStreamEmpty.flatMap(a -> AsIntStream.of(a, 10*a, 100*a)).toArray();
        int[] expected = {};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testFlatMapOne() {
        int[] res = intStreamOne.flatMap(a -> AsIntStream.of(a, 10*a, 100*a)).toArray();
        int[] expected = {5, 50, 500};
        assertArrayEquals(expected, res);
    }

    @Test
    public void testFlatMapMany() {
        int[] res = intStreamMany.flatMap(a -> AsIntStream.of(a, 10*a, 100*a)).toArray();
        int[] expected = {-1, -10, -100, 0, 0, 0, 1, 10, 100, 2, 20, 200, 3, 30, 300};
        assertArrayEquals(expected, res);
    }

    
    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStreamMany);
        assertEquals(expResult, result);        
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStreamMany);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStreamMany);
        assertEquals(expResult, result);        
    }
    
}
