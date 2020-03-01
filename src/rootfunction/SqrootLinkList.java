package rootfunction;

public class SqrootLinkList {
    SqrootResult head;
    
    //function to insert values to link list
    public void insert(double x, double fx, double gx,double x1, double x2, double fx1, double fx2){
        
        // create a new object
        SqrootResult res = new SqrootResult(x,fx,gx,x1,fx1,x2,fx2);
        
        // If it is first node then assign as head
        if(head == null){
            head = res; //assign new node as head node
        }else{
            SqrootResult r = head; //keep head in a variable
            while(r.next != null){ // loop till last node
                r = r.next; //assign to temp node
            }
            r.next = res; //assign new node into last 
        }
    }
}
