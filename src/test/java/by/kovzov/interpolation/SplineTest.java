package by.kovzov.interpolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SplineTest {
    @Parameterized.Parameter(0)
    public double x;



    public static Function<Double, Double> func = x -> x * x + 4 * Math.sin(x);

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{0.71});
        data.add(new Object[]{1.54});
        data.add(new Object[]{3.01});

        return data;
    }

    @Test
    public void getOrdinateTest() {
        double start = 0;
        double end = Math.PI;
        double h = Math.PI/5;

        double[][] tableValues = Spline.getTableValues(start, end, h, func);
        double abscissas[] = tableValues[0];
        double ordinates[] = tableValues[1];

        double actual = Spline.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t my answer = %f\tfunc(x) = %f\n", x, actual, func.apply(x));
        assertEquals(actual, func.apply(x), 0.1);
    }
}