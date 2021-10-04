package by.kovzov.matrix;

public class Vector {
    protected double[]elements;
    protected int col;
    Vector(double[] elements){
        this.elements=elements;
        this.col = elements.length;
    }
    Vector(int col){
        setCol(col);
        elements = new double[col];
    }

    public int getCol(){
        return this.col;
    }
    public double getElementAt(int i){
        return elements[i];
    }

    public void setElementAt(int i, double element) {
        elements[i]=element;
    }
    private void setCol(int col){
        if(col<=0){
            throw new IllegalArgumentException("column is negative or equals zero");
        }
        this.col=col;
    }

    @Override
    public String toString(){
        return elements.toString();
    }
}
