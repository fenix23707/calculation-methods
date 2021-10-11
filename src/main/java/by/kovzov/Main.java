package by.kovzov;

import by.kovzov.interpolation.Spline;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Double, Double> func = x -> x * x + 4 * Math.sin(x);
        double start = 0;
        double end = Math.PI;
        double h = Math.PI / 5;

        double tableValues[][] = Spline.getTableValues(start, end, h, func);
        Spline.printTable(tableValues);

        Spline.getOrdinate(0, tableValues[0], tableValues[1]);
    }
}
