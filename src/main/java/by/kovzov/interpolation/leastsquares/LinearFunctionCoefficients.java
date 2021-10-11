package by.kovzov.interpolation.leastsquares;

import by.kovzov.algebra.linear.slae.MethodGauss;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LinearFunctionCoefficients implements FunctionCoefficients{
    @Override
    public double[] getCoefficients(double[] x, double[] y) {
        double xx[] = new double[x.length];
        double xy[] = new double[x.length];

        for (int i = 0; i < x.length; i++) {
            xx[i] = x[i] * x [i];
            xy[i] = x[i] * y[i];
        }

        double sumX = Arrays.stream(x).parallel().sum();
        double sumY = Arrays.stream(y).parallel().sum();
        double sumXX = Arrays.stream(xx).parallel().sum();
        double sumXY = Arrays.stream(xy).parallel().sum();

        double matrix[][] = new double[][] {
                {sumXX, sumX},
                {sumX, x.length}
        };
        double F[] = new double[]{sumXY, sumY};

        MethodGauss methodGauss = new MethodGauss(matrix, F);
        return methodGauss.getAnswer();
    }

    @Override
    public BiFunction<Double,double[], Double> getFunction() {
        return (x, c) -> c[0] * x + c[1];
    }
}
