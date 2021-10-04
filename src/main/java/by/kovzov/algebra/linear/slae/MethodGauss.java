package by.kovzov.algebra.linear.slae;

import by.kovzov.matrix.MatrixOperations;

import java.util.Scanner;

public class MethodGauss {
    //поля:
    protected double[][] extendedMatrix = null;
    protected double[]answer = null;
    //методы:
    public MethodGauss(int dimensionOfMatrix, Scanner scanner)throws Exception{
        extendedMatrix = new double[dimensionOfMatrix][dimensionOfMatrix+1];
        extendedMatrix= MatrixOperations.readMatrix(scanner,dimensionOfMatrix,dimensionOfMatrix+1);
    }

    public MethodGauss(double[][] matrrixA, double[] vectorF)throws Exception{
        extendedMatrix = new double[vectorF.length][matrrixA[0].length + 1];
        for(int i = 0; i < vectorF.length; i++)
        {
            extendedMatrix[i][matrrixA[0].length] = vectorF[i];
            for(int j = 0; j < matrrixA[0].length; j++) {
                extendedMatrix[i][j] = matrrixA[i][j];
            }

        }
    }

    //сетеры:

    //гетеры:
    public double[]getAnswer() throws Exception
    {
        this.findAnswer();
        return answer;
    }

    public boolean isCompatible()//метод, который проверяет имеет ли матрица решение
    {
        return 0!= MatrixOperations.determinant(extendedMatrix);
    }
    //прямой ход метода Гаусса:
    public void makingLookTriangular() throws Exception//метод, который приводит к треугольному виду
    {
        for(int i=0;i<extendedMatrix.length;i++)
        {
            if(extendedMatrix[i][i]==0)//если нужный элемент равен 0,
                makeElementNonZero(i);//то нужно поменять строчки местами так чтобы новый элемент на этой позиции перестал быть 0
            subRowFromMatrix(i);
        }
    }

    protected void makeElementNonZero(int index) throws Exception//метод, который меняет местами срочки матрицы, чтобы нужный элемент стал не 0
    {
        System.out.println();
        for(int i=0;i<extendedMatrix.length;i++)
        {
            if (extendedMatrix[i][index] != 0)//если нашли строку, в которой нужный элемент не равен 0
            {
                double[] temp = extendedMatrix[index];//то меняем местами
                extendedMatrix[index] = extendedMatrix[i];
                extendedMatrix[i] = temp;
                return;//и завершаем метод
            }
        }
        throw new Exception("it is not possible to reduce the matrix to a triangular form");
    }
    protected void subRowFromMatrix(int indexMainDiag)//метод, который отнимает нужную строку от всех строк в матрицы
    {
        for(int i=indexMainDiag+1;i<extendedMatrix.length;i++)
        {
            double firstNumOfRow = extendedMatrix[i][indexMainDiag]/extendedMatrix[indexMainDiag][indexMainDiag];
            for(int j=0;j<extendedMatrix[0].length;j++)//цикл, который отнимает от i-ой строки 0-ую строку домноженную на первый элемент строки
                extendedMatrix[i][j]-=extendedMatrix[indexMainDiag][j]*firstNumOfRow;
        }
    }
    //обратный ход метода Гаусса:
    protected void findAnswer() throws Exception//нахождение ответа
    {
        this.makingLookTriangular();//приведение матрицы к треугольному виду
        answer = new double[extendedMatrix.length];//массив в котором будет хранится результат
        for(int i=extendedMatrix.length-1;i>=0;i--)//цикл, который начиная с конца находит xi
        {
            double sum=extendedMatrix[i][extendedMatrix.length];
            for(int j=extendedMatrix.length-1;j>=i;j--)
            {
                sum-=answer[j]*extendedMatrix[i][j];
            }
            answer[i]=sum/extendedMatrix[i][i];
        }

    }
}
