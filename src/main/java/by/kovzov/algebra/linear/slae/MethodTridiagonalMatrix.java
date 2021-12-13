package by.kovzov.algebra.linear.slae;

public class MethodTridiagonalMatrix extends SolvingSLE {
    /*матрица которая должна хранить 3 диагонали:
     * tridiagonal[0] - над главной диагональ
     * tridiagonal[1] - главная диагональ
     * tridiagonal[2] - под главной диагональ*/
    private double[][] tridiagonal;
    private double[] vectorF;

    public MethodTridiagonalMatrix(double[][] tridiagonalMatrix, double[] vectorF) {
        this.tridiagonal = tridiagonalMatrix;
        this.vectorF = vectorF;
    }

    //прямой ход:
    public double[] findA() {
        double[] vectorA = new double[tridiagonal[0].length];

        vectorA[0] = -tridiagonal[0][0] / tridiagonal[1][0];

        for (int i = 1; i < tridiagonal[0].length; i++) {
            vectorA[i] = -tridiagonal[0][i] / (tridiagonal[1][i] + tridiagonal[2][i - 1] * vectorA[i - 1]);
        }

        return vectorA;
    }

    public double[] findB(double[] vectorA) {
        double[] vectorB = new double[tridiagonal[0].length];

        vectorB[0] = vectorF[0] / tridiagonal[1][0];

        for (int i = 1; i < tridiagonal[0].length; i++) {
            double temp = (vectorF[i] - tridiagonal[2][i - 1] * vectorB[i - 1]);
            vectorB[i] = temp / (tridiagonal[1][i] + tridiagonal[2][i - 1] * vectorA[i - 1]);
        }

        return vectorB;
    }

    //обратный ход:
    public double[] findX() {
        double[] A = findA();
        double[] B = findB(A);
        double[] X = new double[vectorF.length];
        //нахождение X[n0-1]:
        int index = tridiagonal[0].length - 1;
        double temp = vectorF[vectorF.length - 1] - tridiagonal[2][index] * B[index ];
        X[index + 1] = temp / (tridiagonal[1][index + 1] + tridiagonal[2][index] * A[index]);
        //в обратном порядке нахождение X[i] i [n-2..0]
        for (int i = tridiagonal[0].length - 1; i >= 0; i--) {
            X[i] = A[i] * X[i + 1] + B[i];
        }
        return X;
    }

    @Override
    public double[] getAnswer() {
        return findX();
    }

    @Override
    public boolean isCompatible() {
        return tridiagonal.length == 3;
    }
}
