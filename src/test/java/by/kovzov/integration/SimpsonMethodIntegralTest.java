package by.kovzov.integration;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;

public class SimpsonMethodIntegralTest {

    @Test
    public void calculate() {
        double a = 0;
        double b = 1;
        Function<Double, Double> func = x -> 1 / (1 + x * x);
        Function< Double, Double> antiderivative = x -> Math.atan(x);
        double m = 10;
        double accuracy = 0.0005;
        AbstractIntegral integral = new SimpsonMethodIntegral();

        double actual = integral.calculate(a, b, func, accuracy, m);
        double expected = antiderivative.apply(b) - antiderivative.apply(a);

        assertEquals(expected, actual, accuracy);
    }

    @Test
    public void numberOfSplits() {
        double a = 0;
        double b = 1;
        double m = 24;
        double accuracy = 0.0005;
        AbstractIntegral integral = new SimpsonMethodIntegral();

        int actual = integral.numberOfSplits(a, b, accuracy, m);
        assertEquals(4, actual);
    }
}