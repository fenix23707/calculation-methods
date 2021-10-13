package by.kovzov.integration;

import java.util.function.Function;

public abstract class AbstractIntegral {
    abstract double calculate(double a, double b, Function<Double, Double> func, double accuracy, double M);

    protected abstract int numberOfSplits(double a, double b,double accuracy, double M);
}
