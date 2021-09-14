package by.kovzov.interpolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class NewtonPolynomialTest {

    @Parameterized.Parameter(0)
    public double[] abscissas;

    @Parameterized.Parameter(1)
    public double x;


    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{new double[]{0, 0.628, 1.257}, 0.71});
        data.add(new Object[]{new double[]{0.628, 1.257, 1.885}, 1.54});
        data.add(new Object[]{new double[]{1.885, 2.513, 3.142}, 3.01});
        return data;
    }

    @Test
    public void getOrdinateTest() {
        Function<Double, Double> func = x -> x * x + 4 * Math.sin(x);
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = NewtonPolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }
}