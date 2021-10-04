package by.kovzov.algebra.linear.slae;


import by.kovzov.matrix.FactorizationAlgorithms;

public class MethodSquareRoot extends SolvingSLE{
    protected double[][] matrixA;
    protected double[] vectorF;
    protected double[] answer;
    private double[] vectorD;


    public MethodSquareRoot(double[][]matrixA, double[]vectorF){
        if(matrixA.length!=vectorF.length)
            throw new IllegalArgumentException("rows matrix don't equal rows vector");
        this.matrixA = matrixA;
        this.vectorF = vectorF;
        this.vectorD = new double[matrixA.length];
    }
    @Override
    public double[] getAnswer() {
        findAnswer();
        return answer;
    }

    @Override
    public boolean isCompatible() {
        //проверка выполения условия диагонального преобладания:
        for(int i=0;i<matrixA.length;i++){
            //нахождение суммы на строке всех элементов, кроме элемента главной диагонали
            double sum=0;
            for(int j=0;j<matrixA.length;j++){
                if(i!=j){
                    sum+= Math.abs(matrixA[i][j]);
                }
            }
            if (Math.abs(matrixA[i][i]) < sum) {
                return false;
            }
        }
        return true;
    }

    public double[] findY( double[][]matrixS){
        double[] vectorY = new double[matrixS.length];
        for(int k =0;k<matrixS.length;k++){
            double  temp = vectorF[k];
            for(int s=0;s<k;s++){
                temp-=matrixS[s][k]*vectorY[s]*vectorD[s];
            }
            vectorY[k]= temp/matrixS[k][k]*vectorD[k];
        }
        return vectorY;
    }
    public double[] findX(double [][] matrixS){
        double[] vectorY = findY(matrixS);
        double [] vectorX = new double[matrixS.length];
        for(int k=matrixS.length-1;k>=0;k--){
            double temp = vectorY[k];
            for(int s=k+1;s<matrixS.length;s++){
                temp-=matrixS[k][s]*vectorX[s];
            }
            vectorX[k] = temp/matrixS[k][k];
        }
        return vectorX;
    }
    public void findAnswer(){
       answer =  findX(FactorizationAlgorithms.STDS_Decomposition(matrixA,vectorD));
    }
}
