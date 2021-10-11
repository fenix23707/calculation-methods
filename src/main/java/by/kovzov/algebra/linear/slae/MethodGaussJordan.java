package by.kovzov.algebra.linear.slae;

import by.kovzov.matrix.MatrixOperations;

import java.util.Scanner;

public class MethodGaussJordan extends MethodGauss{
    public MethodGaussJordan(int dimensionOfMatrix, Scanner scanner) throws Exception {
        super(dimensionOfMatrix, scanner);
    }

    protected void findAnswer()//нахождение ответа
    {
        MatrixOperations.makeUnitMatrix(extendedMatrix);//приведение матрицы к треугольному виду
        answer = new double[extendedMatrix.length];//массив в котором будет хранится результат
        for(int i=extendedMatrix.length-1;i>=0;i--)
            answer[i]=extendedMatrix[i][extendedMatrix.length];
    }
}
