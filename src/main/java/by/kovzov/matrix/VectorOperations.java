package by.kovzov.matrix;

import java.util.Scanner;

public class VectorOperations {
    public static double[]readVector(Scanner scanner, int N){
        double[] vector = new double[N];
        for(int i =0;i<vector.length;i++){
                vector[i]=Double.parseDouble(scanner.next());
        }
        return vector;
    }
    public static void printVector(double[] vector){
        for(int i=0;i<vector.length;i++){
            System.out.printf("%-8.8f\t",vector[i]);
        }
    }
    public static double norm(double[] vector) {
        double max = 0;
        for(int i = 0; i < vector.length; i++) {
            if (max < Math.abs(vector[i])) {
                max = Math.abs(vector[i]);
            }
        }
        return max;
    }
}
