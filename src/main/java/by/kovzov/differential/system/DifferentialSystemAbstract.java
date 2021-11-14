package by.kovzov.differential.system;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class DifferentialSystemAbstract {
    public abstract double[][] calculate(double[] nz, double a, double b, double h, List< Function<double[], Double>> funcs);

    protected int getN(double a,double b, double h) {
        return (int) ((b - a) / h + 1);
    }

}
