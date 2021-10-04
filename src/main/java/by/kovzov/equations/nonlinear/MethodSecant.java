package by.kovzov.equations.nonlinear;

public class MethodSecant {
    private static boolean checkExit(double a, double b, double accuracy) {
        return Math.abs(b - a) <= Math.abs(accuracy);
    }

    public static double findRoot(double a,double b0,SingleVariableFunction func,double accuracy) {
        double b = b0;
        int counter = 0;
        do{
            counter++;
            b0 = b;
            b = b0 - ((b0 - a) /  (func.getY(b0) - func.getY(a))) * func.getY(b0);
        }while(!checkExit(b0,b,accuracy));
        System.out.println("Число итераций: " + counter);
        return b;
    }
}
