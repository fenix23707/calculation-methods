package by.kovzov.algebra.linear.slae;

public class MethodSeidel extends MethodFixedPointIteration{
    public MethodSeidel(double[][] matrixA, double[] vectorF, double accuracy) {
        super(matrixA, vectorF, accuracy);
    }
    protected double[] findX( double[] x0 , double[] vectorG) {
        double [] x = new double[matrixB.length];
        for(int i = 0; i < matrixB.length; i++) {
            double temp = 0;
            for(int j = 0; j < i; j++) {
                temp +=matrixB[i][j]*x[j];
            }
            for(int j = i; j < matrixB.length; j++) {
                temp +=matrixB[i][j]*x0[j];
            }
            x[i] = temp + vectorG[i];
        }
        return x;
    }
}
