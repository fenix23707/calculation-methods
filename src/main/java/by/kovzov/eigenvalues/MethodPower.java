package by.kovzov.eigenvalues;


public class MethodPower {
    double[][] matrixA;
    double[] eigenvector;

    public MethodPower(double[][] matrixA) {
        this.matrixA = matrixA;
    }

    private boolean checkExit(double l0,double l1, double accur) {
        return Math.abs(l0 - l1) < accur;
    }

    private double[] findY(double[][] matrixA, double[]y0) {
        double[] y = new double[y0.length];

        for(int i = 0; i < y.length; i++) {
            double sum = 0;
            for(int j = 0; j < y.length; j++) {
                sum += matrixA[i][j] * y0[j];
            }
            y[i] = sum;
        }

        return y;
    }

    private double findMaxEigenvalueAndEigenvector(double[][] matrixA, double[] y0, double accur) {
        double l0, l1 = y0[0];
        double[] y1;
        int counter = 0;
        do{
            l0 = l1;
            y1 = findY(matrixA,y0);
            l1 = y1[0]/y0[0];
            y0 = y1;
        } while (!checkExit(l0, l1, accur));
        System.out.println(counter);
        //цикл, который приводит вектор к вектору с нормой = 1
        for( int i = 0; i < matrixA.length; i++) {
            y1[i] /=y1[matrixA.length -1];
        }
        eigenvector = y1;

        return l1;
    }

    public double[] getEigenvector(double accuracy) {
        if(eigenvector == null) {
            getMaxEigenvalue(accuracy);
        }
        return eigenvector;
    }

    public double getMaxEigenvalue(double accuracy) {
        double[] y0 = new double[matrixA.length];
        for(int i = 0; i < matrixA.length; i++) {
            y0[i] = 1;
        }
        return findMaxEigenvalueAndEigenvector(matrixA,y0,accuracy);
    }
}
