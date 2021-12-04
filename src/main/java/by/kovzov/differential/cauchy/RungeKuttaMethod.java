package by.kovzov.differential.cauchy;

import java.util.function.BiFunction;

/**
 * Runge–Kutta method fourth order
 */
public class RungeKuttaMethod extends CauchyProblemAbstract {
    @Override
    public double[] calculate(double y0, double a, double b, double h, BiFunction<Double, Double, Double> func) {
        int n = getN(a, b, h);
        double y[] = new double[n];
        y[0] = y0;

        double x = a;
        for (int i = 1; i < n; i++) {
            double k1 = getK1(x, y[i - 1], func);
            double k2 = getK2(x, y[i - 1], h, k1, func);
            double k3 = getK3(x, y[i - 1], h, k2, func);
            double k4 = getK4(x, y[i - 1], h, k3, func);

            double deltaY = h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            y[i] = y[i - 1] + deltaY;
            x += h;
        }

        return y;
    }


    private double getK1(double x, double y, BiFunction<Double, Double, Double> func) {
        return func.apply(x, y);
    }

    private double getK2(double x, double y, double h, double k1, BiFunction<Double, Double, Double> func) {
        return func.apply(x + 0.5 * h, y + 0.5 * h * k1);
    }

    private double getK3(double x, double y, double h, double k2, BiFunction<Double, Double, Double> func) {
        return func.apply(x + 0.5 * h, y + 0.5 * h * k2);
    }

    private double getK4(double x, double y, double h, double k3, BiFunction<Double, Double, Double> func) {
        return func.apply(x + h, y + h * k3);
    }
}
