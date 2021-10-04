package by.kovzov.matrix;

public class SquareMatrix extends AbstractMatrix{
    private double[][] elements;

    public static double[][] getE(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size is negative or equals zero");
        }
        double[][] e = new double[size][size];
        for(int i = 0; i < size; i++) {
            e[i][i] = 1;
        }
        return e;
    }
    @Override
    public void setElementAt(int i, int j, double element) {

    }

    @Override
    public double getElementAt(int i, int j) {
        return 0;
    }
}
