package by.kovzov.interpolation;

import by.kovzov.interpolation.leastsquares.FunctionCoefficients;
import by.kovzov.interpolation.leastsquares.LeastSquares;
import by.kovzov.interpolation.leastsquares.LinearFunctionCoefficients;
import by.kovzov.interpolation.leastsquares.ParabolicFunctionCoefficients;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class LeastSquaresTest {
    private double[] abscissas = new double[]{-4, -3, -2, -1, 0, 1, 2, 3, 4};

    private double[] ordinates = new double[]{6, 3, 1, 0.3, -0.1,-0.2, 0, 0.2, 1};

    @Parameterized.Parameter(0)
    public FunctionCoefficients funcCoefficients;

    @Parameterized.Parameters
    public static List<Object> data() {
        return Arrays.asList(
                new LinearFunctionCoefficients(),
                new ParabolicFunctionCoefficients()
        );
    }

    @Test
    public void getOrdinateLinearFunctionTest() {
        LeastSquares leastSquares = new LeastSquares(funcCoefficients);
        double[] actual = leastSquares.getOrdinate(abscissas, ordinates);

        System.out.print("actual:   ");
        leastSquares.printOrdinate(actual);
        System.out.print("expected: ");
        leastSquares.printOrdinate(ordinates);
        System.out.println();

        Assert.assertArrayEquals(ordinates, actual, 5);
    }
}