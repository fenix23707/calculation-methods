package by.kovzov.differential;

import java.util.function.BiFunction;

public class AdvancedEulerMethod extends CauchyProblemAbstract {
    @Override
    public double[] calculate(double y0, double a, double b, double h, BiFunction<Double, Double, Double> func) {
        int n = getN(a,b,h);
        double[] y = new double[n];
        y[0] = y0;
        double x = a;
        for (int i = 1; i < n; i++) {
            y[i] = y[i - 1] + h * func.apply(x + 0.5 * h, y[i - 1] + 0.5 * h * func.apply(x, y[i - 1]));
            x += h;
        }
        return y;
    }


}
