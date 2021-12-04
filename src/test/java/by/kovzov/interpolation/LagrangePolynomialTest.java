package by.kovzov.interpolation;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class LagrangePolynomialTest {


    @Test
    public void getOrdinateTest() {
        Function<Double, Double> func = x -> Math.sin(x);
        double x = 0.3;
        double abscissas[] = new double[]{0, 0.2, 0.4};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));


        assertEquals(Math.sin(x), result, 0.001);

    }

    @Test
    public void getOrdinateTest1() {
        Function<Double, Double> func = x -> x * x + 4 * Math.sin(x);
        double x = 0.71;
        double abscissas[] = new double[]{0, 0.628, 1.257, 1.885, 2.513};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }


    @Test
    public void getOrdinateTest2() {
        Function<Double, Double> func = x -> x * x + 4 * Math.sin(x);
        double x = 1.54;
        double abscissas[] = new double[]{0.628, 1.257, 1.885, 2.513, 3.142};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }

    @Test
    public void getOrdinateTest3() {
        Function<Double, Double> func = x -> x * x + 4 * Math.sin(x);
        double x = 3.01;
        double abscissas[] = new double[]{0.628, 1.257, 1.885, 2.513, 3.142};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }

    @Test
    public void krTest() {
        Function<Double, Double> func = x -> x * x + 4 * x - 7;
        double x = - 4.2;
        double abscissas[] = new double[]{-5, -4.5, -4, -3.5, -3};
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
        double abscissas[] = new double[]{-5, -4.5, -4, -3.5, -3};
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
        double abscissas[] = new double[]{-5, -4.5, -4, -3.5, -3};
        double ordinates[] = new double[abscissas.length];

        for (int i = 0; i < abscissas.length; i++) {
            ordinates[i] = func.apply(abscissas[i]);
        }

        double result = LagrangePolynomial.getOrdinate(x, abscissas, ordinates);
        System.out.printf("x = %f\t y = %f\tfunc(x) = %f\n", x, result, func.apply(x));

        assertEquals(func.apply(x), result, 0.1);
    }


}