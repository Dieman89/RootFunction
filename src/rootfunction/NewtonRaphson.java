package rootfunction;
import java.text.DecimalFormat;

public class NewtonRaphson {
    static final double EPSILON = 0.001;
    
    static SqrootLinkList list;
    
    static double NewtonRaphson(double x, int functionNo) throws Exception{
        try{
            //define link list 
            list = new SqrootLinkList();
            // define number of iterations
            int iterations = 0;
            //get fxs)
            double fx = Function.funcVal(x,functionNo,"f");
            //get inverse of f(x) as gx = f'(x)
            double gx = Function.funcVal(x,functionNo,"g");
            //if inverse is zero for first value and no iterations happened, then result is zero; otherwise same as x - throw fxis0 exception
            if(gx==0) throw new Exception("fxis0");
            //calculate h for formula
            double h = fx / gx;
            //decimal point object
            if (fx == 0) throw new Exception();
            DecimalFormat df = Controller.df;
            //until h is not zero or less
            while(Math.abs(h) >= EPSILON && iterations < 10){ 
                iterations++; // increament No of iterations

                fx = Function.funcVal(x,functionNo,"f");  //f(x)
                gx = Function.funcVal(x,functionNo,"g");  // f'(x)
                h = fx/gx;   // To be used in formula
                x = x - h;  // Formula  x(n+1) = x(n) - f(xn)/f'(xn) //same formula
                //add result to linklist
                list.insert(Double.parseDouble(df.format(x)),Double.parseDouble(df.format(fx)) , Double.parseDouble(df.format(gx)),0,0,0,0); // insert x , f(x) , f'(x) into linked list
            }
            //after loop is ended: result is x
            return Double.parseDouble(df.format(x));
        }catch(Exception exc){
            throw exc;
        }
    }
}