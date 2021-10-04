package by.kovzov.eigenvalues;

import by.kovzov.matrix.MatrixOperations;
import by.kovzov.matrix.SquareMatrix;
import by.kovzov.matrix.VectorOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MethodDanilevsky {
    private double[][] matrixA;
    private double[][]matrixS;

    public MethodDanilevsky(double[][]matrixA) {
        this.matrixA = matrixA;
    }

    public double[][] getFrobeniusMatrix() {
        return findFrobeniusMatrix(matrixA);
    }

    private double[][] findFrobeniusMatrix(double[][] A) {

        for(int i = 1;i < A.length; i++) {
            double[][] matrixM = getMatrixM(A.length - i - 1, A);

            if (i == 1) {//нахождение матрицы S
                matrixS = matrixM;
            } else {
                matrixS = MatrixOperations.mull(matrixS,matrixM);
            }

            double[][]temp = MatrixOperations.mull(A,matrixM);
            A = MatrixOperations.mull(getInverseMatrixM(A.length - i - 1,A), temp);
        }

        return A;
    }

    private double[][] getMatrixM(int row, double[][] A) {
        double[][] M = SquareMatrix.getE(A.length);
        for(int i = 0; i < M.length; i++) {
            if (i == row) {
                M[row][i] = 1/A[row + 1][row];
            } else {
                M[row][i] = -A[row + 1][i]/A[row + 1][row];
            }
        }
        return M;
    }

    private double[][] getInverseMatrixM(int row , double[][] A) {
        double[][] inverseM = SquareMatrix.getE(A.length);
        for(int i = 0; i < inverseM.length; i++) {
            inverseM[row][i] = A[row + 1][i];
        }
        return inverseM;
    }

    private double[][] findY(double[] eigenvalues) {
        double[][] y = new double[eigenvalues.length][matrixA.length];
        for(int i = 0; i < eigenvalues.length; i++) {
            for(int j = 0; j < matrixA.length; j++) {
                y[i][j] = Math.pow(eigenvalues[i],matrixA.length - j - 1);
            }
        }
        return y;
    }

    private double[][] findX(double[] eigenvalues) {
        double[][] y = findY(eigenvalues);
        double[][]x = new double[y.length][y.length];

        //для всех векторов
        for(int i = 0; i < y.length; i++) {
            //для одного вектора
            for(int j = 0; j < y.length; j++) {
                //нахождение одного элемента
                double element = 0;
                for(int k = 0; k < y.length; k++) {
                    element += matrixS[j][k]*y[i][k];
                }
                x[i][j] = element;
            }
        }
        return x;
    }

    public double[] getEigenvalues() {
        try {
            File file = new File("Lab9\\eigenvalues.txt");
            Scanner scanner = new Scanner(file);
            return VectorOperations.readVector(scanner, matrixA.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double[][]getEigenvectors() {
        return findX(getEigenvalues());
    }
}
