package by.kovzov;

import by.kovzov.interpolation.LagrangePolynomial;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        double start = 0;
        double end = 1;
        double h = 0.2;
        Function<Double, Double> func = x -> x * x + 4 * Math.sin(x);
        LagrangePolynomial.printTableOfValues(0, Math.PI, Math.PI / 5, func);

    }
}
