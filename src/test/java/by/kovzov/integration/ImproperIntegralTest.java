package by.kovzov.integration;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ImproperIntegralTest {
    @Test
    public void calculateTest1() {
        double a = 0;
        double b = 2.5;
        Function<Double, Double> func = x -> x * Math.exp(-x * x) / (2 + Math.sin(x));
        double m = 0.2;
        double accuracy = 0.0005;
        AbstractIntegral integral = new RectangleMethodIntegral();

        double actual = integral.calculate(a, b, func, accuracy, m);
        double expected = 0.1875674541932246;

        System.out.printf("actual = %f\t expected = %f\t\n", actual, expected);
        assertEquals(expected, actual, accuracy);
    }

    @Test
    public void calculateTest2() {
        double a = 0;
        double b = 3;
        Function<Double, Double> func = x -> Math.exp(-5*x) * Math.sin(3*x);
        double m = 6.58;
        double accuracy = 0.005;
        AbstractIntegral integral = new SimpsonMethodIntegral();

        double actual = integral.calculate(a, b, func, accuracy, m);
        double expected = 0.08823529411764706;

        System.out.printf("actual = %f\t expected = %f\t\n", actual, expected);
        assertEquals(expected, actual, accuracy);
    }
}
