package by.kovzov.differential.cauchy;

import java.util.function.BiFunction;

public abstract class CauchyProblemAbstract {
    public abstract double [] calculate(double y0, double a, double b, double h, BiFunction<Double, Double, Double> func);

    protected int getN(double a,double b, double h) {
        return (int) ((b - a) / h + 1);
    }
}
