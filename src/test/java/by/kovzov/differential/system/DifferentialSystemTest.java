package by.kovzov.differential.system;

import by.kovzov.matrix.MatrixOperations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;
import static java.lang.Math.*;

@RunWith(Parameterized.class)
public class DifferentialSystemTest {

    @Parameterized.Parameter
    public static DifferentialSystemAbstract differential;

    @Parameterized.Parameters
    public static List<Object> data() {
        List<Object> data = new ArrayList<>();
        data.add(new EulerMethod());
        data.add(new RungeKuttaMethod());

        return data;
    }

    @Test
    public void calculateTest() {
        double a = 0;
        double b = 0.5;
        double h = 0.1;
        double[] nz = new double[]{0, 0};
        List<Function<double[], Double>> funcs = Arrays.asList(
                v -> v[2] - cos(v[0]),
                v -> v[1] + cos(v[0])
        );


        double[][] actual = differential.calculate(nz, a, b, h, funcs);

        System.out.println(differential.getClass().getSimpleName());
        MatrixOperations.printMatrix(actual);
        System.out.println();

        double expected[][] = {
                {0, -0.095, -0.18, -0.255, -0.32, -0.375},
                {0, 0.095, 0.18, 0.255, 0.32, 0.375}
        };

        double[][] transActual = MatrixOperations.transpose(actual);
        double delta = 0.1;
        assertArrayEquals(expected[0], transActual[1], delta);
        assertArrayEquals(expected[1], transActual[2], delta);
    }

    @Test
    public void calculateExampleTest() {
        double a = 0;
        double b = 0.5;
        double h = 0.1;
        double[] nz = new double[]{0.5, 1};
        List<Function<double[], Double>> funcs = Arrays.asList(
                v -> exp(-abs(v[2] * v[2] + v[1] * v[1])) + 2 * v[0],
                v -> 2 * v[1] * v[1] + v[2]
        );

        double[][] actual = differential.calculate(nz, a, b, h, funcs);

        double expected[][] = {
                {0.5, 0.53403, 0.57946, 0.63792, 0.71165, 0.80286},
                {1, 1.16116, 1.34819, 1.56755, 1.82771, 2.13997}
        };

        double[][] transActual = MatrixOperations.transpose(actual);
        double delta = 1;
        assertArrayEquals(expected[0], transActual[1], delta);
        assertArrayEquals(expected[1], transActual[2], delta);
    }

}