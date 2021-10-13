package by.kovzov.integration;

import java.util.function.Function;

public class GaussMethodIntegral extends AbstractIntegral{
    private double[] x = { -0.93246951, -0.6612093, -0.23861918, 0.93246951, 0.6612093, 0.23861918 };

    private double[] c = {0.171324492, 0.36076157, 0.46793, .171324492, 0.36076157, 0.46793};
    @Override
    double calculate(double a, double b, Function<Double, Double> func, double accuracy, double M) {
        int n = numberOfSplits(a, b, accuracy, M);

        double h = (b - a) / 2;
        double add = (b + a) / 2;

        double result = 0;
        for (int i = 0; i < n; i++) {
            double temp = h * x[i] + add;
            result += c[i] * func.apply(temp);
        }
        return result * h;
    }

    @Override
    protected int numberOfSplits(double a, double b, double accuracy, double M) {
        return x.length;
    }
}
