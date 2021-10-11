package by.kovzov.interpolation.leastsquares;

import by.kovzov.algebra.linear.slae.MethodGauss;

import java.util.Arrays;
import java.util.function.BiFunction;

public class ParabolicFunctionCoefficients implements FunctionCoefficients{
    @Override
    public double[] getCoefficients(double[] x, double[] y) {
        double xx[] = new double[x.length];
        double xxx[] = new double[x.length];
        double xxxx[] = new double[x.length];
        double xy[] = new double[x.length];
        double xxy[] = new double[x.length];

        for (int i = 0; i < x.length; i++) {
            xx[i] = x[i] * x[i];
            xxx[i] = x[i] * x[i] * x[i];
            xxxx[i] = x[i] * x[i] * x[i] * x[i];
            xy[i] = x[i] * y[i];
            xxy[i] = x[i] * x[i] * y[i];
        }

        double sumX = Arrays.stream(x).parallel().sum();
        double sumY = Arrays.stream(y).parallel().sum();
        double sumXX = Arrays.stream(xx).parallel().sum();
        double sumXXX = Arrays.stream(xxx).parallel().sum();
        double sumXXXX = Arrays.stream(xxxx).parallel().sum();
        double sumXY = Arrays.stream(xy).parallel().sum();
        double sumXXY = Arrays.stream(xxy).parallel().sum();

        double matrix[][] = new double[][] {
                {sumXXXX, sumXXX, sumXX},
                {sumXXX, sumXX, sumX},
                {sumXX, sumX, x.length}
        };
        double F[] = new double[] {sumXXY, sumXY, sumY};

        MethodGauss methodGauss = new MethodGauss(matrix, F);
        return methodGauss.getAnswer();
    }

    @Override
    public BiFunction<Double, double[], Double> getFunction() {
        return (x, c) -> c[0] * x * x + c[1] * x + c[2];
    }
}
