package rootfunction;

import java.text.DecimalFormat;

public class BiSection {
    static final double EPSILON = 0.001; 
    static final int maxIteration = 10;
    static SqrootArray resultArr;
    
    static double BiSection(double a, double b, int functionNo) throws Exception{
        try{
            // initialize result array with max-iteration number
            resultArr = new SqrootArray(maxIteration);
            //define iterations as zero
            int iteration = 0;
            //define final value
            double c = 0;
            //get value as f(x) for a & b respectively
            double fa = Function.funcVal(a, functionNo, "f");
            double fb = Function.funcVal(b, functionNo, "f");
            //check if solution exists [if not, then throw exception]
            if((fa<0 && fb<0)||(fa>0 && fb>0)) throw new Exception("notpossible");
            //define f(c)
            double fc = 0;
            //define object for decimal place
            DecimalFormat df = Controller.df;
            //until iteration number exceeds maximum-itearation number
            while(iteration < maxIteration){ 
                //formula
               c = (a+b)/2; //   0.5+0.5=1/2=0.5 same as a & b
               //get f(c)
               fc = Function.funcVal(c, functionNo, "f"); // ln2+1

               //insert result to array for storing
               resultArr.insert(iteration, Double.parseDouble(df.format(a)), Double.parseDouble(df.format(Function.funcVal(a, functionNo, "f"))), 
                       Double.parseDouble(df.format(Function.funcVal(a, functionNo, "g"))), b,c,fb,fc  );
                              
               //check whether a ==b, if so then, result is a
               if(a==b && iteration == 0) return a;
               //increament number of iteration
               iteration++;
               //if(value is less than almost zero
               if(Math.abs(fc)<=EPSILON){
                   // return result as c
                   return Double.parseDouble(df.format(c));
               }
               
               //check for changing a & b value
               if(fa * fc >= 0){
                   a = c;
               }else if(fb*fc>=0){
                   b = c;
               }
               
               //get f(a) & f(b)
               fa = Function.funcVal(a, functionNo, "f");
               fb = Function.funcVal(b, functionNo, "f");
            }
            //after loop ends: result is c
            return Double.parseDouble(df.format(c));
        }catch(Exception exc){
            System.out.println(exc);
            throw exc;
        }
    }
}