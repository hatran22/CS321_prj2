import java.io.*;
import java.util.*;

public class Stack <obj> {
    private class node<obj>{
      obj data;
      node<obj> link;
    }
    node<obj> top;
    
    public Stack(){
        this.top=null;
    }
    
    public void push(obj x){
        node<obj> now= new node();  
        
        now.data=x;
        now.link=top;
        
        top=now;
    }
    
    public boolean isempty()
    {
        return top==null;
    }
    
    public obj peek()
    {
        if(!isempty())
        {
            return top.data;
        }
        else
        {
            System.out.print("Empty stack!");
            return null;
        }
    }
    public void pop()
    {
        if(top==null)
        {
            System.out.printf("\nEmpty stack, cannot pop any more!");
            return;
        }
        top=top.link;
    }
    public void print()
    {
        if(top==null)
        {
            System.out.printf("\nStack underflow");
        }
        else
        {
            node temp=top;
            while(temp!=null)
            {
                System.out.printf("%s->",temp.data);
                
                temp=temp.link;
            }
        }
    }
    public  static void main(String[] args){
        Stack<String> stack=new Stack<String>();
        assert stack.isempty()==true;
        System.out.printf("push a\n");

        stack.push("a");
        assert stack.peek() =="a";
        
        assert stack.isempty()==false;
        System.out.printf("push b\n");

        stack.push("b");
        assert stack.peek()=="b";

        stack.print();
        
        System.out.printf("\nTop element is %s\n",stack.peek());
        System.out.printf("pop 2 times \n");

        stack.pop();
        assert stack.peek()=="a";

        stack.pop();
        assert stack.isempty()==true;
        System.out.printf("\nTop element is %s\n",stack.peek());

    }
}

