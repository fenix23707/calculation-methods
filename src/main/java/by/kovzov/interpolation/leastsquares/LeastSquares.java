package by.kovzov.interpolation.leastsquares;

import java.util.function.BiFunction;

public class LeastSquares {
    private FunctionCoefficients funcCoefficients;

    public LeastSquares(FunctionCoefficients funcCoefficients) {
        this.funcCoefficients = funcCoefficients;
    }

    public double[] getOrdinate(double[] abscissas, double[] ordinates) {
        double [] coefficients = funcCoefficients.getCoefficients(abscissas, ordinates);
        double result[] = new double[abscissas.length];

        BiFunction<Double, double[], Double> func = funcCoefficients.getFunction();

        for (int i = 0; i < result.length; i++) {
            result[i] = func.apply(abscissas[i], coefficients);
        }
        return result;
    }

    public static void printOrdinate(double[] ordinates) {
        for (int i = 0; i < ordinates.length; i++) {
            System.out.printf("%.3f\t", ordinates[i]);
        }
        System.out.println();
    }
}
