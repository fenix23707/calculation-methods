package by.kovzov.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IntegralTest {
    private double a = 1;

    private double b = 5;

    private Function<Double, Double> func = x -> Math.log(x) * Math.log(x) + (Math.log(x) * Math.log(x) * Math.log(x)) / 3;

    private Function<Double, Double> antiderivative = x -> Math.log(x) * Math.log(x) * Math.log(x) * x / 3;

    private double accuracy = 0.0005;

    @Parameterized.Parameter(0)
    public AbstractIntegral integral;

    @Parameterized.Parameter(1)
    public double m;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] {new RectangleMethodIntegral(), 2});
        data.add(new Object[] {new TrapezoidMethodIntegral(), 2});
        data.add(new Object[] {new SimpsonMethodIntegral(), 10});
        data.add(new Object[] {new GaussMethodIntegral(), 0});

        return data;
    }


    @Test
    public void calculateTest() {
        int n = integral.numberOfSplits(a, b, accuracy, m);
        double actual = integral.calculate(a, b, func, accuracy, m);

        double expected = antiderivative.apply(b) - antiderivative.apply(a);

        System.out.printf("%25s: actual: %.7f\texpected: %.7f\tn = %2d\n",
                integral.getClass().getSimpleName(),
                actual,
                expected,
                n

        );

        assertEquals(expected, actual, accuracy);
    }
}
