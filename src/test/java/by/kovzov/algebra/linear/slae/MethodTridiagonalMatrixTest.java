package by.kovzov.algebra.linear.slae;

import org.junit.Test;

import static org.junit.Assert.*;

public class MethodTridiagonalMatrixTest {

    @Test
    public void getAnswer() {
        double[] A = {2, 1, 1};
        double[] B = {2, 3, -1, -1};
        double[] C = {1, -1, 3};
        double[] F = {4, 9, 12, -4};

        double[][] matrix = new double[3][];
        matrix[0] = C;
        matrix[1] = B;
        matrix[2] = A;
        MethodTridiagonalMatrix tridiagonalMatrix = new MethodTridiagonalMatrix(matrix, F);
        double[] actual = tridiagonalMatrix.getAnswer();
        double[] expected = {1, 2, -1, 3};
        assertArrayEquals(expected, actual, 0.001);

    }
}