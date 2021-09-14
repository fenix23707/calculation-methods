package by.kovzov.interpolation;

public class NewtonPolynomial {
    public static double getOrdinate(double x, double[] abscissas, double[] ordinates) {
        double result = 0;
        double multiplier = 1;
        for (int i = 0; i < abscissas.length; i++) {
            result += multiplier * getTempOrdinate(0, i, abscissas, ordinates);
            multiplier *= x - abscissas[i];
        }

        return result;
    }

    private static double getTempOrdinate(int startIndex,
                                          int endIndex,
                                          double[] abscissas,
                                          double[] ordinates) {
        int deference = endIndex - startIndex;
        if (deference < 0) {
            throw new IllegalArgumentException("start: "
                    + startIndex + " or end: "
                    + endIndex + " is incorrect");
        } else if (deference == 0) {
            return ordinates[startIndex];
        }
        double numberUp = getTempOrdinate(startIndex + 1, endIndex, abscissas, ordinates)
                - getTempOrdinate(startIndex, endIndex - 1, abscissas, ordinates);
        double numberDown = abscissas[endIndex] - abscissas[startIndex];
        return numberUp / numberDown;
    }
}
