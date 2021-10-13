package by.kovzov.integration;

import java.util.function.Function;

public class TrapezoidMethodIntegral extends AbstractIntegral {
    @Override
    double calculate(double a, double b, Function<Double, Double> func, double accuracy, double m) {
        int n = numberOfSplits(a, b, accuracy, m);
        double h = Math.abs(b - a) / n;

        double prevX = a;
        double result = 0;
        for (int i = 0; i < n; i++) {
            double x = prevX + h;
            result += (func.apply(x) + func.apply(prevX)) / 2 * h;
            prevX = x;
        }

        return result;
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
        temp = temp * temp * temp * M / 12 / accuracy;
        return (int) Math.sqrt(temp);
    }
}
