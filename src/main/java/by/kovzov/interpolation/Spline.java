package by.kovzov.interpolation;

import by.kovzov.algebra.linear.slae.MethodTridiagonalMatrix;
import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Spline {
    public static double[][] getTableValues(double start, double end, double h, Function<Double, Double> func) {

        double[][] tableValues = new double[2][numberOfPoints(start, end, h)];
        for (int i = 0; i < tableValues[0].length; i++) {
            tableValues[0][i] = start;
            tableValues[1][i] = func.apply(start);
            start += h;
        }
        return tableValues;
    }

    public static void printTable(double[][] table) {
        for (int i = 0; i < table[0].length; i++) {
            System.out.printf("\t%10.3f", table[0][i]);
        }

        System.out.println();

        for (int i = 0; i < table[1].length; i++) {
            System.out.printf("\t%10.3f", table[1][i]);
        }

        System.out.println();
    }

    private static int numberOfPoints(double start, double end, double h) {
        return (int) ((end - start) / h) + 1;
    }

    public static double getOrdinate(double x, double[] abscissas, double[] ordinates) {
        double h[] = getH(abscissas);
        double a[] = getA(ordinates);
        double c[] = getC(ordinates, h);
        double d[] = getD(c, h);
        double b[] = getB(c, d, ordinates, h);

        int i = findIntervalIndex(x, abscissas);
        return a[i] + b[i] * (x - abscissas[i]) +
                c[i] * (x - abscissas[i]) * (x - abscissas[i]) / 2 +
                d[i] * (x - abscissas[i]) * (x - abscissas[i]) * (x - abscissas[i]) / 6;
    }

    private static int findIntervalIndex(double x, double[] abscissas) {
        for (int i = 0; i < abscissas.length - 1; i++) {
            if (x >= abscissas[i] && x <= abscissas[i + 1]) {
                return i + 1;
            }
        }

        if (x < abscissas[0]) {
            return 0;
        } else {
            return abscissas.length - 1;
        }
    }

    private static double[] getH(double abscissas[]) {
        double h[] = new double[abscissas.length - 1];
        for (int i = 0; i < abscissas.length - 1; i++) {
            h[i] = abscissas[i + 1] - abscissas[i];
        }
        return h;
    }

    private static double[] getA(double[] ordinates) {
        return ordinates;
    }

    private static double[] getB(double[] c, double[] d, double[] y, double[] h) {
        double[] b = new double[c.length];

        b[0] = 0;
        for (int i = 1; i < c.length; i++) {
            b[i] = (c[i] * h[i - 1] / 2) -
                    (d[i] * h[i - 1] * h[i - 1] / 6) +
                    (y[i] - y[i - 1]) / h[i - 1];
        }

        return b;
    }

    private static double[] getD(double[] c, double[] h) {
        double[] d = new double[c.length];

        d[0] = 0;
        for (int i = 1; i < c.length; i++) {
            d[i] = (c[i] - c[i - 1]) / h[i - 1];
        }
        return d;
    }

    private static double[] getC(double y[], double h[]) {
        double A[] = Arrays.copyOfRange(h, 0, h.length - 1);
        double B[] = new double[y.length - 2];
        double C[] = Arrays.copyOfRange(h, 1, h.length);
        double F[] = new double[y.length - 2];

        //find B
        for (int i = 0; i < h.length - 1; i++) {
            B[i] = 2 * (h[i] + h[i + 1]);
        }

        //find F
        for (int i = 0; i < h.length - 1; i++) {
            F[i] = 6 * ((y[i + 2] - y[i + 1]) / h[i + 1] - (y[i + 1] - y[i]) / h[i]);
        }

        double tridiagonalMatrix[][] = new double[3][h.length - 1];
        tridiagonalMatrix[0] = C;
        tridiagonalMatrix[1] = B;
        tridiagonalMatrix[2] = A;

        MethodTridiagonalMatrix methodTridiagonalMatrix = new MethodTridiagonalMatrix(
                tridiagonalMatrix,
                F
        );
        double tempC[] = methodTridiagonalMatrix.findX();
        List<Double> list = Arrays.stream(tempC)
                .mapToObj(Double::new)
                .collect(Collectors.toList());
        list.add(0, 0d);
        list.add(0d);
        return list.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
