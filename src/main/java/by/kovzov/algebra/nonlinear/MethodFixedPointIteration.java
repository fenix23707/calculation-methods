package by.kovzov.algebra.nonlinear;


import by.kovzov.equations.nonlinear.MultiVariableFunction;

public class MethodFixedPointIteration {
    private static boolean checkExit(double[] a, double[] b, double accuracy) {
        for(int i = 0; i < a.length; i++)
         if(Math.abs(b[i] - a[i]) > Math.abs(accuracy)) {
             return false;
         }
         return true;
    }

    private static double[]findX(double[] x0, MultiVariableFunction[] func) {
        double[] x = new double[x0.length];

        for(int i = 0; i < x0.length; i++) {
            x[i] = func[i].getY(x0);
        }
        return x;
    }
    public static double[] findRoot(double x0[], MultiVariableFunction[] func, double accuracy) {

        double[] x = x0;
        int count = 0;
        do{
            count++;
            x0 = x;
            x = findX(x0,func);
        }while (!checkExit(x0, x, accuracy));
        System.out.println("Число итераций: " + count);
        return x;
    }
}
