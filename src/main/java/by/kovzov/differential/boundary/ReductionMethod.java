package by.kovzov.differential.boundary;

import by.kovzov.algebra.linear.slae.MethodGauss;
import by.kovzov.differential.system.DifferentialSystemAbstract;

import java.util.Arrays;
import java.util.function.Function;

public class ReductionMethod implements BoundaryValueProblem {
    private DifferentialSystemAbstract difSystem;

    public ReductionMethod(DifferentialSystemAbstract difSystem) {
        this.difSystem = difSystem;
    }

    @Override
    public double[] calculate(double a, double b,
                            double h,
                            double[] nz, // nz[0]=a1, nz[1]=a2, nz[2]=b1, nz[3]=b2, nz[4]=y1, nz[5]=y2
                            Function<Double, Double> p,
                            Function<Double, Double> q,
                            Function<Double, Double> f) {
        double[][] y0s0 = getY0S0(a, b, h, p, q, f);
        double[][] y1s1 = getY1S1(a, b, h, p, q);
        double[][] y2s2 = getY2S2(a, b, h, p, q);

        double[] c = getC(y0s0, y1s1, y2s2, nz);
        double[] result = getResult(y0s0, y1s1, y2s2, c);
        return result;
    }

    private double[][] getY0S0(double a, double b,
                               double h,
                               Function<Double, Double> p,
                               Function<Double, Double> q,
                               Function<Double, Double> f) {
        double[] nz = {0, 0};
        // v[0] = x; v[1] = y(x); v[2] = s(x)
        Function<double[], Double> y0 = v -> v[2];  // Y0' = S0
        Function<double[], Double> s0 = v -> f.apply(v[0]) - p.apply(v[0]) * v[2] - q.apply(v[0]) * v[1]; // S0' = f - p*S0 - q*Y0
        return difSystem.calculate(nz, a, b, h, Arrays.asList(y0, s0));
    }

    private double[][] getY1S1(double a, double b,
                               double h,
                               Function<Double, Double> p,
                               Function<Double, Double> q) {
        double[] nz = {1, 0};
        // v[0] = x; v[1] = y(x); v[2] = s(x)
        Function<double[], Double> y0 = v -> v[2];  // Y1' = S1
        Function<double[], Double> s0 = v -> 0 - p.apply(v[0]) * v[2] - q.apply(v[0]) * v[1]; // S1' = f - p*S1 - q*Y1
        return difSystem.calculate(nz, a, b, h, Arrays.asList(y0, s0));
    }

    private double[][] getY2S2(double a, double b,
                               double h,
                               Function<Double, Double> p,
                               Function<Double, Double> q) {
        double[] nz = {0, 1};
        // v[0] = x; v[1] = y(x); v[2] = s(x)
        Function<double[], Double> y0 = v -> v[2];  // Y2' = S2
        Function<double[], Double> s0 = v -> 0 - p.apply(v[0]) * v[2] - q.apply(v[0]) * v[1]; // S2' = f - p*S2 - q*Y2
        return difSystem.calculate(nz, a, b, h, Arrays.asList(y0, s0));
    }

    private double[] getC(double[][] y0s0, double[][] y1s1, double[][] y2s2, double[] nz) {
        // y' = s
        double y0b = y0s0[y0s0.length - 1][1];
        double s0b = y0s0[y0s0.length - 1][2];
        double y1b = y1s1[y1s1.length - 1][1];
        double s1b = y1s1[y1s1.length - 1][2];
        double y2b = y2s2[y2s2.length - 1][1];
        double s2b = y2s2[y2s2.length - 1][2];

        double vector2 = nz[5] - nz[1] * y0b - nz[3] * s0b; // y2 - a2*Y0(b) - b2S0(b)
        double matrix11 = nz[1] * y1b + nz[3] * s1b;//a2*Y1(B) + b2*S1(b)
        double matrix12 = nz[1] * y2b + nz[3] * s2b;//a2*Y2(B) + b2*S2(b)
        double[] vector = {nz[4], vector2};
        double[][] matrix = {
                {nz[0], nz[2]},
                {matrix11, matrix12}
        };

        MethodGauss methodGauss = new MethodGauss(matrix, vector);
        double[] c = methodGauss.getAnswer();
        return c;
    }

    private double[] getResult(double[][] y0s0, double[][] y1s1, double[][] y2s2, double[] c) {
        int size = y0s0.length;
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = y0s0[i][1] + c[0] * y1s1[i][1] + c[1] * y2s2[i][1]; //Y0 + c1Y1 + c2Y2
        }
        return result;
    }
}
