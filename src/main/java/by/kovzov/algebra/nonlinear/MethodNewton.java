package by.kovzov.algebra.nonlinear;

import by.kovzov.algebra.linear.slae.MethodGaussChooseElement;
import by.kovzov.equations.nonlinear.MultiVariableFunction;

public class MethodNewton {
    private static boolean checkExit(double[] a, double[] b, double accuracy) {
        for(int i = 0; i < a.length; i++)
            if(Math.abs(b[i] - a[i]) > Math.abs(accuracy)) {
                return false;
            }
        return true;
    }

    private static double[]findX(double[] x0, MultiVariableFunction[] func, MultiVariableFunction[][] derivative) throws Exception {
        double[] x = new double[x0.length];
        double[][] W = new double[derivative.length][derivative.length];

        for(int i = 0; i < derivative.length; i++) {
            for(int j = 0; j < derivative[0].length;j++) {
                W[i][j] = derivative[i][j].getY(x0);
            }
        }


        double[] f = new double[func.length];
        for(int  i= 0; i < derivative[0].length;i++) {
            f[i] = -func[i].getY(x0);
        }

        MethodGaussChooseElement gaussChooseElement =  new MethodGaussChooseElement(W,f);
        double[] deltaX = gaussChooseElement.getAnswer();
        for(int i = 0; i < deltaX.length; i++) {
            x[i] = deltaX[i] + x0[i];
        }
        return x;
    }


    public static double[] findRoot(double x0[], MultiVariableFunction[] func,
                                  MultiVariableFunction[][] derivative, double accuracy) throws Exception {
        double[] x = x0;
        int counter = 0;
        do{
            counter++;
            x0 = x;
            x = findX(x0,func,derivative);

        }while(!checkExit(x,x0,accuracy));
        System.out.println("Число итераций: " + counter);
        return x;

    }
}