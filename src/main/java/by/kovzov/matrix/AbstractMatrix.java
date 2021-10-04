package by.kovzov.matrix;

abstract public class AbstractMatrix {
    protected int col;

    protected void setCol(int col){
        if(col<=0){
            throw new IllegalArgumentException("column is negative or equals zero");
        }
        this.col=col;
    }
    abstract public void setElementAt(int i,int j, double element);

    public int getCol(){
        return col;
    }
    abstract public double getElementAt(int i, int j);
}
