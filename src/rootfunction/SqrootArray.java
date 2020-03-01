package rootfunction;

public class SqrootArray {
    // VARIABLES
    double[] xVals;
    double[] x1Vals;
    double[] x2Vals;
    double[] fxVals;
    double[] fx1Vals;
    double[] fx2Vals;
    double[] gxVals;
    
    /*
    CONSTRUCTOR:
    parameter: array-size
    initialize array with length 
    */
    SqrootArray(int arrSize){
        this.xVals = new double[arrSize];
        this.x1Vals = new double[arrSize];
        this.x2Vals = new double[arrSize];
        this.fxVals = new double[arrSize];
        this.fx1Vals = new double[arrSize];
        this.fx2Vals = new double[arrSize];
        this.gxVals = new double[arrSize];
    }
    
    //insert data into array
    public void insert(int index, double x, double fx, double gx, double x1, double x2, double fx1, double fx2){
        System.out.println(x+" "+fx+" "+gx);
        this.xVals[index] = x;        
        this.x1Vals[index] = x1;
        this.x2Vals[index] = x2;

        this.fxVals[index] = fx;
        this.fx1Vals[index] = fx1;
        this.fx2Vals[index] = fx2;
        this.gxVals[index] = gx;
    }
    
    //get value of specific index from xVals array 
    public double xVal(int i){
        return this.xVals[i];
    }
    //get value of specific index from fxVals array 
    public double fxVal(int i){
        return this.fxVals[i];
    }
    //get value of specific index from gxVals array 
    public double gxVal(int i){
        return this.gxVals[i];
    }
    
}
