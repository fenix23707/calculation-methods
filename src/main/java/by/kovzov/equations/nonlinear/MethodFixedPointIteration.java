package by.kovzov.equations.nonlinear;

public class MethodFixedPointIteration {
    private static boolean checkExit(double a, double b, double accuracy) {
        return Math.abs(b - a) <= Math.abs(accuracy);
    }

    public static double findRoot(double a, double b,SingleVariableFunction func, double accuracy) {
        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }
        double x0,x = func.getY((a + b)/2);
        int count = 0;
        do{
            count++;
            x0 = x;
            x = func.getY(x0);
        }while (!checkExit(x0, x, accuracy));
        System.out.println("Число итераций: " + count);
        return x;
    }
}
