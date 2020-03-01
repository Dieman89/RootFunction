package rootfunction;

public class Function {
    
    //  returns double value of f(x) = x - x^2
    public static double f1(double x){
        return x - Math.pow(x,2);
    }
    
    //    returns double value of f'(x): f(x) = x - x^2   =>  1-2x
    public static double g1(double x){
        return 1 - (2 * x);
    }
    
    //  returns double value of f(x) = ln(x+1) + 1
    public static double f2(double x){
        //if(x<=0) return 0;
        
        return Math.log(x+1) + 1;
    }
    
    //    returns double value of f'(x): f(x) = ln(x+1) + 1   =>  1/(x+1)
    public static double g2(double x){
        //if(x==0) return 0;
        return 1 / (x + 1);
    }
    
    //  returns double value of f(x) = e^x - 3x
    public static double f3(double x){
        return Math.exp(x) - (3 * x);
    }
    
    //    returns double value of f'(x): f(x) = e^x - 3x   =>  e^x - 3
    public static double g3(double x){
        return Math.exp(x) - 3;
    }
    
    
    // x: value
    // functionNo: to determine which function be needed from [ {f1,g1} {f2,g2} {f3,g3} ]  
        // =>  In which: 1 represents {f1,g1}
        //               2 represents {f2,g2}
        //               3 represents {f3,g3}
    // type: Whether to choose f or g   =>   f means normal function , and, g means differentiation of normal function
    public static double funcVal(double x, int functionNo, String type) throws Exception{
        
        // Can also be written as : 
        // return functionNo == 1 ? type == "f" ? f1(x) : g1(x) : functionNo == 2 ? type == "f" ? f2(x) : g2(x) : type == "f" ? f3(x) : g3(x);
        
        
        double val = 0;  // define var
        
        //if 1 then
        if(functionNo == 1){
            // if function number is 1, call f1() to get value
            /* this ? : is equivalent to : 
                if(type == "f"){
                    val = f1(x)
                }else{
                    val = f2(x)
                } 
            */
            
            val = type == "f" ? f1(x) : g1(x); 
        }
        else if(functionNo == 2){
            // if function number is 2, call f2() to get value
            val = type == "f" ? f2(x) : g2(x);
        }
        else{
            // call f3() to get value
            val =  type == "f" ? f3(x) : g3(x);
        }
        
        //if value is not a number , throw exception
        if(Double.isNaN(val)) throw new Exception("NaN");

        //if value is infinite, throw exception
        if(Double.isInfinite(val)) throw new Exception("Infinite");
        
        return val;
    }
}
