package by.kovzov.interpolation;

import java.util.function.Function;

public class LagrangePolynomial {
    public static void printTableOfValues(double start, double end, double h, Function<Double, Double> func) {
        while (start <= end) {
            System.out.printf("x = %.3f\ty = %.3f\n", start, func.apply(start));
            start += h;
        }
    }

    public static double getOrdinate(double x, double[] abscissas, double[] ordinates) {
        double result = 0;
        for (int i = 0; i < abscissas.length; i++) {
            result += getItem(x, abscissas, i) * ordinates[i];
        }
        return result;
    }

    private static double getItem(double x, double[] abscissas, int itemNum) {
        double itemUp = 1;
        double itemDown = 1;

        for (int i = 0; i < abscissas.length; i++) {
            if (i != itemNum) {
                itemUp *= x - abscissas[i];
                itemDown *= abscissas[itemNum] - abscissas[i];
            }
        }
        return itemUp / itemDown;
    }
}
