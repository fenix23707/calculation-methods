package by.kovzov.differential.boundary;

import java.util.List;
import java.util.function.Function;

public interface BoundaryValueProblem {
    double[] calculate(double a, double b,
                     double h,
                     double[] nz,
                     Function<Double, Double> p,
                     Function<Double, Double> q,
                     Function<Double, Double> f);
}
