package by.kovzov.differential.boundary;

import by.kovzov.differential.system.DifferentialSystemAbstract;
import by.kovzov.differential.system.RungeKuttaMethod;
import by.kovzov.matrix.VectorOperations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BoundaryValueProblemTest {
    private static DifferentialSystemAbstract diffSystem = new RungeKuttaMethod();

    @Parameterized.Parameters
    public static List<Object> data() {
        return Arrays.asList(
                new ReductionMethod(diffSystem),
                new GridMethod()
        );
    }

    @Parameterized.Parameter(0)
    public BoundaryValueProblem problem;

    @Test
    public void calculateTest() {
        double a = 0;
        double b = 0.5;
        double h = 0.1;
        double[] nz = {1, 1, 0, 0, 0, 0.5 * Math.sin(0.5)};
        Function<Double, Double> p = x -> 2 * x;
        Function<Double, Double> q = x -> -1.0;
        Function<Double, Double> f = x -> 2 * (x * x + 1) * Math.cos(x);
        double[] actual = problem.calculate(a, b, h, nz, p, q, f);

        double accuracy = 0.001;
        Function<Double, Double> func = x -> x * Math.sin(x);
        double[] expected = new double[actual.length];
        double x = a;
        for (int i = 0; i < expected.length; i++) {
            expected[i] = func.apply(a + h * i);
        }
        System.out.println(problem.getClass().getSimpleName());
        System.out.println("actual:   " + Arrays.toString(actual));
        System.out.println("expected: " + Arrays.toString(expected));
        assertArrayEquals(expected, actual, accuracy);
    }

    @Test
    public void calculateGridTest() {
        double a = 0;
        double b = 1;
        double h = 0.1;
        double[] nz = {0, 1, 1, 2, 0, 0};
        Function<Double, Double> p = x -> -1d;
        Function<Double, Double> q = x -> -2.0;
        Function<Double, Double> f = x -> -3 * Math.exp(-x);
        double[] actual = problem.calculate(a, b, h, nz, p, q, f);

        double accuracy = 1;
        Function<Double, Double> func = x -> (x + 1) * Math.exp(-x);
        double[] expected = new double[actual.length];
        double x = a;
        for (int i = 0; i < expected.length; i++) {
            expected[i] = func.apply(a + h * i);
        }
        System.out.println(problem.getClass().getSimpleName());
        System.out.print("actual: ");
        VectorOperations.printVector(actual);
        System.out.println();
        System.out.print("expected: ");
        VectorOperations.printVector(expected);
        System.out.println("\n");
        assertArrayEquals(expected, actual, accuracy);
    }

   /* @Test
    public void calculateGridExampleTest() {
        double a = 0;
        double b = 1;
        double h = 0.1;
        double[] nz = {1, 1, 0, 0, 0, 3};
        Function<Double, Double> p = x -> 1d;
        Function<Double, Double> q = x -> - 1/x;
        Function<Double, Double> f = x -> 2 * x + 4;
        double[] actual = problem.calculate(a, b, h, nz, p, q, f);

        double accuracy = 1;
        Function<Double, Double> func = x -> 2 * x * x + x;
        double[] expected = new double[actual.length];
        double x = a;
        for (int i = 0; i < expected.length; i++) {
            expected[i] = func.apply(a + h * i);
        }
        System.out.println(problem.getClass().getSimpleName());
        System.out.println("actual:   " + Arrays.toString(actual));
        System.out.println("expected: " + Arrays.toString(expected));
        assertArrayEquals(expected, actual, accuracy);
    }
*/
}
