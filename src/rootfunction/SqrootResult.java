package rootfunction;

/**
 * To save x, f(x) & f'(x) values
 * @author Dieman
 */

//CLASS FOR LINK LIST NODES
public class SqrootResult {
    //VARIABLES
    double x;
    double fx;
    double gx;    
    double x1;
    double fx1;
    double x2;
    double fx2;
    SqrootResult next = null;

    //CONSTRUCTOR
    SqrootResult(double x, double fx, double gx,double x1, double fx1, double x2, double fx2){
        this.x = x;
        this.fx = fx;
        this.gx = gx;        
        this.x1 = x1;
        this.fx1 = fx1;
        this.x2 = x2;
        this.fx2 = fx2;
    }
}
