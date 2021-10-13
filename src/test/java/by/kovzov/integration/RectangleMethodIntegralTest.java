package by.kovzov.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;

public class RectangleMethodIntegralTest {
    @Test
    public void calculateExampleTest() {
        double a = 0;
        double b = 1;
        Function<Double, Double> func = x -> 1 / (1 + x * x);
        Function< Double, Double> antiderivative = x -> Math.atan(x);
        double m = 2;
        double accuracy = 0.0005;
        AbstractIntegral integral = new RectangleMethodIntegral();

        double actual = integral.calculate(a, b, func, accuracy, m);
        double expected = antiderivative.apply(b) - antiderivative.apply(a);

        assertEquals(expected, actual, accuracy);
    }

    @Test
    public void numberOfSplitsTestExampleTest() {
        double a = 0;
        double b = 1;
        double m = 2;
        double accuracy = 0.0005;
        AbstractIntegral integral = new RectangleMethodIntegral();

        int actual = integral.numberOfSplits(a, b, accuracy, m);
        assertEquals(12, actual);
    }
}