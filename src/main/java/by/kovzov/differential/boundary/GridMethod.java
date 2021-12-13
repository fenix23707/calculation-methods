package by.kovzov.differential.boundary;

import by.kovzov.algebra.linear.slae.MethodTridiagonalMatrix;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class GridMethod implements BoundaryValueProblem {
    @Override
    public double[] calculate(double a, double b,
                              double h,
                              double[] nz, // nz[0]=a1, nz[1]=a2, nz[2]=b1, nz[3]=b2, nz[4]=y1, nz[5]=y2
                              Function<Double, Double> p,
                              Function<Double, Double> q,
                              Function<Double, Double> f) {

        double[] ordinates = getOrdinates(a, b, h, nz, p, q, f);
        return ordinates;
    }


    private double[] getA(double h, double[] abscissas, Function<Double, Double> p, double[] nz) {

        double[] A = new double[abscissas.length + 1];
        double component = 1 / (h * h);
        for (int i = 0; i < abscissas.length; i++) {
            A[i] = component - p.apply(abscissas[i]) / (2 * h);
        }
        A[A.length - 1] = - nz[3] / h;
        return A;
    }

    private double[] getB(double h, double[] abscissas, Function<Double, Double> q, double[] nz) {
        double[] B = new double[abscissas.length + 2];
        B[0] = nz[0] - nz[2] / h;
        double component = -2 / (h * h);
        for (int i = 0; i < abscissas.length; i++) {
            B[i + 1] = component + q.apply(abscissas[i]);
        }
        B[B.length - 1] = nz[1] + nz[3] / h;
        return B;
    }

    private double[] getC(double h, double[] abscissas, Function<Double, Double> p, double[] nz) {
        double[] C = new double[abscissas.length + 1];
        C[0] = nz[2] / h;
        double component = 1 / (h * h);
        for (int i = 0; i < abscissas.length; i++) {
            C[i + 1] = component + p.apply(abscissas[i]) / (2 * h);
        }
        return C;
    }

    private double[] getF(double h, double[] abscissas, Function<Double, Double> f, double[] nz) {
        double[] F = new double[abscissas.length + 2];
        F[0] = nz[4];
        for (int i = 0; i < abscissas.length; i++) {
            F[i + 1] = f.apply(abscissas[i]);
        }
        F[F.length - 1] = nz[5];
        return F;
    }

    private double[] getAbscissas(double a, double b, double h) {
        int size = numberOfPoints(a, b, h);
        double[] abscissas = new double[size];
        double x = a;
        for (int i = 0; i < abscissas.length; i++) {
            abscissas[i] = x;
            x += h;
        }
        return abscissas;
    }

    private double[] getOrdinates(double a, double b,
                                  double h,
                                  double[] nz,
                                  Function<Double, Double> p,
                                  Function<Double, Double> q,
                                  Function<Double, Double> f) {
        double[] abscissas = getAbscissas(a + h, b - h, h);
        double[] A = getA(h, abscissas, p, nz);
        double[] B = getB(h, abscissas, q, nz);
        double[] C = getC(h, abscissas, p, nz);
        double[] F = getF(h, abscissas, f, nz);

        double tridiagonalMatrix[][] = new double[3][];
        tridiagonalMatrix[0] = C;
        tridiagonalMatrix[1] = B;
        tridiagonalMatrix[2] = A;
        MethodTridiagonalMatrix methodTridiagonalMatrix = new MethodTridiagonalMatrix(
                tridiagonalMatrix,
                F
        );
        return methodTridiagonalMatrix.findX();
    }

    private static int numberOfPoints(double start, double end, double h) {
        return (int) ((end - start) / h) + 1;
    }
}
