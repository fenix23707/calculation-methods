package by.kovzov.differential;

import by.kovzov.differential.cauchy.AdvancedEulerMethod;
import by.kovzov.differential.cauchy.CauchyProblemAbstract;
import by.kovzov.differential.cauchy.RungeKuttaMethod;
import by.kovzov.matrix.VectorOperations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CauchyProblemTest {

    @Parameterized.Parameter(0)
    public CauchyProblemAbstract problem;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[] {new AdvancedEulerMethod()});
        data.add(new Object[] {new RungeKuttaMethod()});
        return data;
    }

    @Test
    public void calculateMainTest() {
        double a = 1;
        double b = 1.5;
        double h = 0.1;
        double y0 = 0;
        BiFunction<Double,Double, Double> func = (x, y) -> (x * x * y * y - (2 * x + 1) * y + x) / x;
        double actual[] = problem.calculate(y0, a, b, h, func);
        double expected[] = {0,0.087, 0.154, 0.207, 0.251, 0.289};

        System.out.println(problem.getClass().getSimpleName());
        VectorOperations.printVector(actual);
        System.out.println();
        VectorOperations.printVector(expected);
        System.out.println();
        assertArrayEquals(expected, actual, 0.01);
    }

    @Test
    public void calculateExampleTest() {
        double a = 1.6;
        double b = 2.6;
        double h = 0.1;
        double y0 = 4.6;
        BiFunction<Double,Double, Double> func = (x, y) -> x + Math.cos(y/3);
        double actual[] = problem.calculate(y0, a, b, h, func);
        double expected[] = {4.6, 4.766, 4.9364, 5.11, 5.2899, 5.4728, 5.6597, 5.8506, 6.0456, 6.2447, 6.4479};

//        System.out.println(problem.getClass().getSimpleName());
//        VectorOperations.printVector(actual);
//        System.out.println();
//        VectorOperations.printVector(expected);
//        System.out.println();
        assertArrayEquals(expected, actual, 0.01);
    }
}