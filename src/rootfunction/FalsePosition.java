package rootfunction;

import java.text.DecimalFormat;

public class FalsePosition {
    static final double EPSILON = 0.001;
    static int maxIteration = 10;
    
    static SqrootLinkList list;
    
    public static double FalsePosition(double a, double b, int functionNo) throws Exception{
        //define link list to store results
        list = new SqrootLinkList();
        //define iteration number
        int iteration = 0;
        //define initial values of result variables
        double x = 0,fx=0,gx=0, fan,fbn;
        //get f(a) [first guess value]
        
       fan = Function.funcVal(a, functionNo, "f");
        //get f(b) [second guess value]
       fbn = Function.funcVal(b, functionNo, "f");
        //check whether solution is possible or not
        if ((fan == 0)||(fbn==0)) throw new Exception("itsroot");
        if((fan<0 && fbn<0)||(fan>0 && fbn>0)) throw new Exception("notpossible");
        //define decimal point object
        DecimalFormat df = Controller.df;
        //until iteration number reaches maximum number
        while(iteration < maxIteration){
            //formula
            x = (a * fbn - b * fan) / (fbn - fan); 
            //get f(x)
            fx = Function.funcVal(x, functionNo, "f");
            //get inverse of f(x) ; gx = f'(x)
            gx = Function.funcVal(x, functionNo, "g");
            //insert result into link list
            list.insert(Double.parseDouble(df.format(a)), Double.parseDouble(df.format(fan)), Double.parseDouble(df.format(gx)), b, x, fbn, fx  );
            //increament number of iteration
            iteration++;
            //update guess values based on conditions
           if(fan*fx >= 0){
               a = x;
           }else{
               b = x;
           }
           //get f(a)
            fan = Function.funcVal(a, functionNo, "f");
           //get f(b)
            fbn = Function.funcVal(b, functionNo, "f");
        }
        //after loop: result is x
        return Double.parseDouble(df.format(x));
    }
}