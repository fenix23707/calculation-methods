package by.kovzov.algebra.linear.slae;


import java.util.Scanner;

public class MethodGaussChooseElement extends MethodGauss{

    public MethodGaussChooseElement(int dimensionOfMatrix, Scanner scanner) throws Exception {
        super(dimensionOfMatrix, scanner);

    }

    public MethodGaussChooseElement(double[][] matrrixA, double[] vectorF) throws Exception {
        super(matrrixA, vectorF);
    }

    public void makingLookTriangular() throws Exception//метод, который приводит к треугольному виду
    {
        for(int i=0;i<extendedMatrix.length;i++)
        {
            chooseElement(i);//выбор максимальной строки
            subRowFromMatrix(i);//
        }
    }
    private void chooseElement(int indexRow)//метод, который меняет текущею строку на строку с максимальным элементом
    {
        double max=extendedMatrix[indexRow][indexRow];
        int indexMaxRow=indexRow;
        for(int i=indexRow;i<extendedMatrix.length;i++)
            if(extendedMatrix[i][indexRow]>max)
            {
                max = extendedMatrix[i][indexRow];
                indexMaxRow=i;
            }
        if(indexMaxRow!=indexRow)
        {
            double []temp = extendedMatrix[indexRow];
            extendedMatrix[indexRow]=extendedMatrix[indexMaxRow];
            extendedMatrix[indexMaxRow]=temp;
        }
    }
}
