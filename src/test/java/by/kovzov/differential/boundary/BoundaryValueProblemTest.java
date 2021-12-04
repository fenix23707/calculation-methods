package by.kovzov.differential.boundary;

import by.kovzov.differential.system.DifferentialSystemAbstract;
import by.kovzov.differential.system.RungeKuttaMethod;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

import static org.junit.Assert.*;

public class BoundaryValueProblemTest {

    @Test
    public void calculateTest() {
        DifferentialSystemAbstract diffSystem = new RungeKuttaMethod();
        BoundaryValueProblem problem = new ReductionMethod(diffSystem);

        double a = 0;
        double b = 0.5;
        double h = 0.1;
        double[] nz = {1, 1, 0, 0, 0, 0.5 * Math.sin(0.5)};
        Function<Double, Double> p = x -> 2 * x;
        Function<Double, Double> q = x -> -1.0;
        Function<Double, Double> f = x -> 2 * (x * x + 1) * Math.cos(x);
        double[] actual = problem.calculate(a, b, h, nz, p, q, f);

        double accuracy = 0.0001;
        Function<Double, Double> func = x -> x * Math.sin(x);
        double[] expected = new double[actual.length];
        double x = a;
        for (int i = 0; i < expected.length; i++) {
            expected[i] = func.apply(a + h * i);
        }
        System.out.println("actual:   " + Arrays.toString(actual));
        System.out.println("expected: " + Arrays.toString(expected));
        assertArrayEquals(expected, actual, accuracy);
    }

    @Test
    public void calculateExampleTest() {
        DifferentialSystemAbstract diffSystem = new RungeKuttaMethod();
        BoundaryValueProblem problem = new ReductionMethod(diffSystem);

        double a = 0.5;
        double b = 1;
        double h = 0.1;
        double[] nz = {0, 1, 1, 0, 1, 3};
        Function<Double, Double> p = x -> 1 / x;
        Function<Double, Double> q = x -> -2.0;
        Function<Double, Double> f = x -> -2 * x * x;
        double[] actual = problem.calculate(a, b, h, nz, p, q, f);

        double accuracy = 0.0001;
        Function<Double, Double> func = x -> x * x + 2;
        double[] expected = new double[actual.length];
        double x = a;
        for (int i = 0; i < expected.length; i++) {
            expected[i] = func.apply(a + h * i);
        }

        assertArrayEquals(expected, actual, accuracy);
    }
}