package by.kovzov.matrix;

public class FactorizationAlgorithms {//37
    public  static double[][] STDS_Decomposition(double[][]matrixA, double[]vectorD)
    {
        if(matrixA.length!=matrixA[0].length)
            throw new IllegalArgumentException("matrixA is not square");
        if(vectorD.length!=matrixA.length)
            throw new IllegalArgumentException("length of vectorD not equals length of matrixA");

        double[][] matrixS = new double[matrixA.length][matrixA.length];
        //нахождение матрицы S и D
        for(int i=0;i<matrixA.length;i++){
            //остальные элементы:
            for(int j=0;j<i;j++){
                double temp = matrixA[i][j];
                for(int k=0;k<j;k++){
                    temp-=matrixS[k][i]*matrixS[k][j]*vectorD[k];
                }
                matrixS[j][i]=temp/matrixS[j][j];
            }
            //главная диагональ
            double sub= matrixA[i][i];
            for(int k=0;k<i;k++){
                sub-=matrixS[k][i]*matrixS[k][i];
            }
            vectorD[i] = Math.signum(sub);
            matrixS[i][i] = Math.sqrt(Math.abs(sub));
        }
        return matrixS;
    }

}
