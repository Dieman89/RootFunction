package rootfunction;
import java.text.DecimalFormat;

public class Secant {
    static final double EPSILON = 0.001;
    static final int maxIteration = 10;
    static SqrootArray resultArr;
    
    static double Secant(double x0, double x1, int functionNo) throws Exception{
        try{
            // define result array to hold results in an array
            resultArr = new SqrootArray(maxIteration);
            // initialize number of iterations
            int iteration = 0;
            // initialize final value 
            double x2 = 0;
            
            //decimal point object initialization
            DecimalFormat df = Controller.df;
            //get f(x0) [first guess value]
            double fx0 = Function.funcVal(x0, functionNo, "f");
            //get f(x1) [second guess value]
            double fx1 = Function.funcVal(x1, functionNo, "f");
            //check if solution is possible or not
            if((fx0<0 && fx1<0)||(fx0>0 && fx1>0)) throw new Exception("notpossible");
            
            //until difference between guess values is not almost zero
            while(Math.abs(x1-x0) > EPSILON){
                //formula
                x2 = x1 - (((x1-x0) * fx1) / (fx1-fx0));
                //get value following decimal point object
                x2 = Double.parseDouble(df.format(x2));
                //get f(x2)
                double fx2 = Function.funcVal(x2, functionNo, "f");
                //get inverse of f(x2) as gx2 = f'(x2)
                double gx2 = Function.funcVal(x2, functionNo, "g");
                //insert result into arrray
                resultArr.insert(iteration, Double.parseDouble(df.format(x0)), Double.parseDouble(df.format(fx0)), Double.parseDouble(df.format(gx2)), x1, x2, fx1,fx2  );  //as returning x2 as root
                
                //if f(x2) is zero, then, result is zero
                if(fx2==0){
                    if(iteration==0) return 0;
                    //if result is not zero, then format result as decimal point formatter
                    return Double.parseDouble(df.format(resultArr.xVal(iteration-2)));
                }
                //update guess values
                x0 = x1;
                x1 = x2;
                //increament iteration numbers
                iteration++;
                //get f(x0)
                fx0 = Function.funcVal(x0, functionNo, "f");
                //get f(x1)
                fx1 = Function.funcVal(x1, functionNo, "f");
            }
            //loop ends and returns result
            return Double.parseDouble(df.format(x2));
        }catch(Exception exc){
            throw exc;
        }
    }
}