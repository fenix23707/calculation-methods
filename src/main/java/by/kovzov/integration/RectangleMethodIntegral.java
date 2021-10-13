package by.kovzov.integration;

import java.util.function.Function;

public class RectangleMethodIntegral extends AbstractIntegral {

    @Override
    public double calculate(double a, double b, Function<Double, Double> func, double accuracy, double M) {
        int n = numberOfSplits(a, b, accuracy, M);
        double h = Math.abs(b - a) / n;

        double result = 0;
        for (int i = 0; i < n; i++) {
            double x = a + h * (i + 0.5);
            result += func.apply(x);
        }
        return result * h;
    }

    /**
     *
     * @param a left bound
     * @param b right bound
     * @param accuracy
     * @param M = max|f''(x)|, x in [a,b]
     * @return
     */
    @Override
    protected int numberOfSplits(double a, double b, double accuracy, double M) {
        double temp = b - a;
        temp = temp * temp * temp * M / 24 / accuracy;
        return (int) Math.sqrt(temp);
    }
}
