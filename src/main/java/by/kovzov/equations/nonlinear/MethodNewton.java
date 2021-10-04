package by.kovzov.equations.nonlinear;

public class MethodNewton {
    private static boolean checkExit(double a, double b, double accuracy) {
        return Math.abs(b - a) <= Math.abs(accuracy);
    }

    public static double findRoot(double x0,SingleVariableFunction func,
                                  SingleVariableFunction derivative,double accuracy) {
        double x = x0;
        int counter = 0;
        do{
            counter++;
            x0 = x;
            double temp1 = func.getY(x0);
            double temp2 = derivative.getY(x0);
            double temp3 = temp1/temp2;
            x = x0 - temp1/temp2;
        }while(!checkExit(x,x0,accuracy));
        System.out.println("Число итераций: " + counter);
        return x;

    }
}
