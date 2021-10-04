package by.kovzov.algebra.linear.slae;

import by.kovzov.matrix.MatrixOperations;
import by.kovzov.matrix.VectorOperations;

public class MethodFixedPointIteration extends SolvingSLE{
    private double[][] matrixA;
    private double[] vectorF;
    private double accuracy;
    protected double[][]matrixB;

    public MethodFixedPointIteration(double[][] matrixA, double[] vectorF, double accuracy) {
        if((matrixA.length != matrixA[0].length) && (matrixA.length!=vectorF.length)) {
            throw new IllegalArgumentException("lengths don't equal");
        }
            this.matrixA = matrixA;
            this.vectorF = vectorF;
            this.accuracy = Math.abs(accuracy);
            this.matrixB = createMatrixB();
    }

    private double[][] createMatrixB() {
        double[][] matrixB = new double[matrixA.length][matrixA.length];
        for(int i = 0; i < matrixB.length; i++) {
            for(int j = 0; j < matrixB[0].length; j++) {
                if (i == j) {
                    matrixB[i][j] = 0;
                } else {
                    matrixB[i][j] = - matrixA[i][j]/matrixA[i][i];
                }
            }
        }
        return matrixB;
    }

    private double[] createVectorG() {
        double[] vectorG = new double[vectorF.length];
        for(int i = 0; i < vectorG.length; i++) {
            vectorG[i] = vectorF[i]/matrixA[i][i];
        }
        return vectorG;
    }

    private int determineNumberOfIterations(double accuracy,
                                            double[] vectorG, double[] X0) {
        int numberOfIterations;
        double normB = MatrixOperations.norm(matrixB);
        double temp = accuracy*(1-normB)/ VectorOperations.norm(vectorG);
        numberOfIterations = (int)(Math.log(temp)/Math.log(normB));
        return numberOfIterations + 1;
    }

    private boolean checkExitCondition(double[] x, double[] x0) {
        double max = Math.abs(x[0] - x0[0]);
        for(int i = 1; i < x0.length; i++) {
            if (Math.abs(x[i] - x0[i]) > max) {
               max = Math.abs(x[i] - x0[i]);
            }
        }
        if (max < accuracy) {
            return true;
        } else {
            return false;
        }
    }

    protected double[] findX( double[] x0 , double[] vectorG) {
        double [] x = new double[matrixB.length];
        for(int i = 0; i < matrixB.length; i++) {
            double temp = 0;
            for( int j = 0; j < matrixB.length; j++) {
                temp +=matrixB[i][j]*x0[j];
            }
            x[i] = temp + vectorG[i];
        }
        return x;
    }

    @Override
    public double[] getAnswer() {

        double[] vectorG = createVectorG();
        double[] x = vectorG;
        double[] x0;
        int counter = 0;
        do{
            x0 = x;
            x = findX(x0, vectorG);
        }while(!checkExitCondition(x,x0));
        return x;
    }

    @Override
    public boolean isCompatible() {
        for(int i=0;i<matrixA.length;i++){
            //нахождение суммы на строке всех элементов, кроме элемента главной диагонали
            double sum=0;
            for(int j=0;j<matrixA.length;j++){
                if (i != j){
                    sum += Math.abs(matrixA[i][j]);
                }
            }
            if (Math.abs(matrixA[i][i]) < sum)
                return false;
        }
        return true;
    }
}
