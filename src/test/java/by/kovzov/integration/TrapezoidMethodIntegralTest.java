package by.kovzov.integration;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;

public class TrapezoidMethodIntegralTest {

    @Test
    public void calculateTest() {
        double a = 0;
        double b = 1;
        Function<Double, Double> func = x -> 1 / (1 + x * x);
        Function< Double, Double> antiderivative = x -> Math.atan(x);
        double m = 2;
        double accuracy = 0.0005;
        AbstractIntegral integral = new TrapezoidMethodIntegral();

        double actual = integral.calculate(a, b, func, accuracy, m);
        double expected = antiderivative.apply(b) - antiderivative.apply(a);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void numberOfSplitsTest() {
        double a = 0;
        double b = 1;
        double m = 2;
        double accuracy = 0.0005;
        AbstractIntegral integral = new TrapezoidMethodIntegral();

        int actual = integral.numberOfSplits(a, b, accuracy, m);
        assertEquals(18, actual);
    }
}