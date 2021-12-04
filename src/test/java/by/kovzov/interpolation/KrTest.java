package by.kovzov.interpolation;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class KrTest {
    @Test
    public void krTest() {
        Function<Double, Double> func = x -> x * x + 4 * x - 7;
        double x = - 4.2;
        double abscissas[] = new double[]{-5, -4.5, -4, -3.5};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }


    @Test
    public void krTest2() {
        Function<Double, Double> func = x -> x * x + 4 * x - 7;
        double x = -4.8;
        double abscissas[] = new double[]{-5, -4.5, -4, -3.5};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }

    @Test
    public void krTest3() {
        Function<Double, Double> func = x -> x * x + 4 * x - 7;
        double x = -3.52;
        double abscissas[] = new double[]{ -4.5, -4, -3.5, -3};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }

    @Test
    public void krTest4() {
        Function<Double, Double> func = x -> x * x + 4 * x - 7;
        double x = -2;
        double abscissas[] = new double[]{ -4, -3.5, -3, -2.5};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }
}
