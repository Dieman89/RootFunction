package rootfunction;

// MAIN PURPOSE OF THIS CLASS:
// TO SHOW DATA ON TABLE: TABLE VIEW NEEDS OBJECT AS A PARAMETER
// AND GETTERS TO ACCESS ALL VARIABLES FROM THAT OBJECT

public class Result {
    //variables
    private double x0;
    private double x1;
    private double x2;
    private double fx0;
    private double fx1;
    private double fx2;
    //constructor
    Result(double x0,double x1,double x2,double fx0,double fx1,double fx2){
        // this represents current object: means the variable in this class only  
        this.x0=x0;
        this.x1=x1;
        this.x2=x2;
        this.fx0=fx0;
        this.fx1=fx1;
        this.fx2=fx2;
    }
    
    //GETTERS
    public double getX0() {
        return x0;
    }
    public double getX1() {
        return x1;
    }
    public double getX2() {
        return x2;
    }
    public double getFx0() {
        return fx0;
    }
    public double getFx1() {
        return fx1;
    }
    public double getFx2() {
        return fx2;
    }
}
