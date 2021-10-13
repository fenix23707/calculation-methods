package by.kovzov.integration;

import java.util.function.Function;

public class SimpsonMethodIntegral extends AbstractIntegral{
    @Override
    double calculate(double a, double b, Function<Double, Double> func, double accuracy, double M) {
        int n = numberOfSplits(a, b, accuracy, M);
        double h = (b - a) / (2 * n);
        double result = 0;
        for (int i = 1; i < n; i++) {
            double oddX = a + (2 * i - 1) * h; // x(2i - 1)
            double evenX = a + (2 * i) * h; // x(2i)
            result += 4 * func.apply(oddX) + 2 * func.apply(evenX);
        }
        result += func.apply(a); //f(x0)
        result += 4 * func.apply(a + h * (2 * n - 1)); //4 * f(x(2n - 1))
        result += func.apply(a + h * (2 * n)); //f(x(2n))
        return result * h / 3;
    }

    /**
     *
     * @param a left bound
     * @param b right bound
     * @param accuracy
     * @param M = max|f''''(x)|, x in [a,b]
     * @return
     */
    @Override
    protected int numberOfSplits(double a, double b, double accuracy, double M) {
        double temp = b - a;
        temp = temp * temp * temp * temp * temp * M / 180 / accuracy;
        return (int) Math.pow(temp, 1.0 / 4.0);
    }
}
