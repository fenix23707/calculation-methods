package by.kovzov.interpolation.leastsquares;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface FunctionCoefficients {
    double[] getCoefficients(double[] abscissas, double[] ordinates);

    BiFunction<Double,double[], Double> getFunction();
}
