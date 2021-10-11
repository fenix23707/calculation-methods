package by.kovzov.matrix;

import java.util.Scanner;

public class MatrixOperations {
    //чтение матрицы:
    public static double[][]readMatrix(Scanner scanner,int N,int M){
        double[][] matrix = new double[N][M];
        for(int i =0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
                matrix[i][j]=Double.parseDouble(scanner.next());
        }
        return matrix;
    }

    public static void printMatrix(double [][]matrix){
        for(int i=0;i<matrix.length;i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.printf("%-8.4f\t",matrix[i][j]);
            System.out.println();
        }
    }

    //приведение матрицы к еденичной:
    public static void makeUnitMatrix(double[][]matrix){
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[i][i]==0)//если нужный элемент равен 0,
                makeElementNonZero(matrix,i);//то нужно поменять строчки местами так чтобы новый элемент на этой позиции перестал быть 0

            divRowByElement(matrix,i,matrix[i][i]);
            subRowFromMatrix(matrix,i);
        }
    }

    //нахождение обратной матрицы
    public static double[][] inverseMatrix(double [][]matrix) throws Exception {
        double [][]newMatrix = addUnitMatrix(matrix);//создание новой матрицы, прибалвнием к старой еденичной матрицы
        makeUnitMatrix(newMatrix);//приведение половины матрицы к еденичной матрицы(вмести с ней приводится и вторая половина, тем самым находится обратная матрица)
        double [][]inverseMatrix = new double[matrix[0].length][matrix[0].length];
        copyMatrix(newMatrix,inverseMatrix,inverseMatrix[0].length);//выделение обратной матрицы
        return inverseMatrix;
    }

    //метод, который находит отпределитель:
    public static double determinant(double[][] matrix){
        double det=0;
        if(matrix.length==2)
        {
            return matrix[0][0]*matrix[1][1]- matrix[0][1]*matrix[1][0];//нахождение определителя двумерной матрицы
        }
        else
            for(int i=0;i<matrix.length;i++)//разложение по 0-ой строке
            {
                if(i%2==0)//если i - четное число то -1 в степени i будет поллож
                    det+= matrix[0][i]*determinant(subMatrix(i,matrix));
                else//и наоборот
                    det-= matrix[0][i]*determinant(subMatrix(i,matrix));
            }
        return det;
    }

    //нахождение нормы:
    public static double norm(double[][] matrix) {
        double norm = 0;
        for(int i = 0; i < matrix.length; i++ ) {
            double sumRow = sumAbsRow(matrix, i);
            if(norm < sumRow) {
                norm = sumRow;
            }
        }
        return norm;
    }

    //произведение матриц:
    public static double[][]mull(double[][] m1, double[][] m2) {
        double[][] mull = new double[m1.length][m1.length];
        for(int i= 0; i < m1.length; i++) {
            for(int j = 0; j < m1[0].length; j++) {
                mull[i][j] = findElementForMull(m1,m2,i,j);
            }
        }
        return mull;
    }

    private static double findElementForMull(double[][] m1, double[][] m2,int row, int col) {
        double sum = 0;
        for(int i = 0; i < m1.length; i++) {
            sum += m1[row][i]*m2[i][col];
        }
        return sum;
    }

    public static double sumAbsRow(double[][]matrix, int row) {
        if ((row < 0) || (row >= matrix.length)) {
            throw new IllegalArgumentException("row is incorrect");
        }
        double sum = 0;
        for(int i = 0; i < matrix[0].length; i++) {
            sum += Math.abs(matrix[row][i]);
        }
        return sum;
    }

    //вспомогательные методы:
    private static double[][] addUnitMatrix(double [][]matrix) throws Exception {//метод, который дописывае еденичную матрицу
        if(matrix.length!=matrix[0].length)//если матрица не квадратная, то дальше выполнение метода бесползено
            throw new Exception("the matrix is not square");

        double [][]matrixWithOneMatrix = new double[matrix.length][2*matrix.length];
        copyMatrix(matrix,matrixWithOneMatrix,0);//копирует первую матрицу во вторую начиная с 0 столбца
        //заполнение главной диагонали оставшейся матрицы 1:
        int row = 0;
        for(int i=matrix.length;i<matrixWithOneMatrix[0].length;i++)//цикл, который заполняет главную диагональ оставшейся матрицы еденицами
            matrixWithOneMatrix[row++][i]=1;
        return matrixWithOneMatrix;
    }

    private static void copyMatrix(double[][] firstMatrix, double[][]secondMatrix,int column){//метод, который копирует первую матрицу во вторую начиная со столбца column
        for(int i=0;i<firstMatrix.length;i++)
            for(int j=0;j<firstMatrix.length;j++)
                secondMatrix[i][j]=firstMatrix[i][j+column];
    }

    private static double[][] subMatrix(int column,double[][]matrix){//метод, который возващает подматрицу убирая первую строку и выбранную диагональ
        double[][] newMatrix = new double[matrix.length-1][matrix.length-1];
        for(int i=0;i<newMatrix.length;i++)
        {
            int k = 0;
            for (int j = 0; j < newMatrix.length;j++ ) {
                if (j == column)
                    k++;
                newMatrix[i][j] = matrix[i+1][k++];
            }
        }
        return newMatrix;
    }

    //метод, который меняет местами срочки матрицы, чтобы нужный элемент стал не 0
    private static void makeElementNonZero(double[][]matrix,int index){
        System.out.println();
        for(int i=0;i<matrix.length;i++)
        {
            if (matrix[i][index] != 0)//если нашли строку, в которой нужный элемент не равен 0
            {
                double[] temp = matrix[index];//то меняем местами
                matrix[index] = matrix[i];
                matrix[i] = temp;
                return;//и завершаем метод
            }
        }
        throw new RuntimeException("it is not possible to reduce the matrix to a triangular form");
    }

    //метод, который делит всю строку на переданный элемент
    private static void divRowByElement(double[][] matrix,int indexRow,double element){
        for(int i=0;i<matrix[0].length;i++)
            matrix[indexRow][i]/=element;
    }

    //метод, который отнимает нужную строку от всех строк в матрицы
    private static void subRowFromMatrix(double[][]matrix,int indexMainDiag){
        for(int i=0;i<matrix.length;i++)
        {
            if(i!=indexMainDiag)
            {
                double firstNumOfRow = matrix[i][indexMainDiag]/matrix[indexMainDiag][indexMainDiag];
                for(int j=0;j<matrix[0].length;j++)//цикл, который отнимает от i-ой строки 0-ую строку домноженную на первый элемент строки
                    matrix[i][j]-=matrix[indexMainDiag][j]*firstNumOfRow;
            }
        }
    }
}
