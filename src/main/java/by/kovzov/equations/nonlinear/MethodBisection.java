package by.kovzov.equations.nonlinear;


public class MethodBisection {
    private static boolean checkExit(double a, double b, double accuracy) {
        return Math.abs(b - a) <= Math.abs(accuracy);
    }

    public static double findRoot(double a, double b,SingleVariableFunction func, double accuracy) {
        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }
        while (!checkExit(a, b, accuracy)) {
            double midl = (a + b) / 2;
            double mullFuncAOnFuncMidl = func.getY(a) * func.getY(midl);
            if (mullFuncAOnFuncMidl < 0) {
                b = midl;
            } else {
                a = midl;
            }
        }
        return a;
    }
}
