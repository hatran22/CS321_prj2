import Stack.Stack;
 
public class test_stack {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Stack obj=new Stack();
        assert obj.isempty()==true;
        obj.push(1);
        assert obj.peek() ==1;
        
        assert obj.isempty()==false;
        obj.push(2);
        assert obj.peek()==2;

        obj.display();
        
        System.out.printf("\nTop element is %d\n",obj.peek());
        
        obj.pop();
        assert obj.peek()==1;

        obj.pop();
        assert obj.isempty()==true;
    }
    
}