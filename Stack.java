package stack;
import java.io.*;
import java.util.*;
s
public class Stack
{
    private class node
    // node class: 
    {
      int data;
      node link;
    }
    
    node top;
    
    Stack()
    // empty top initialization
    {
        this.top=null;
    }
    
    public void push(int x)
    {
        node now= new node();
        
        //if(now==null){
        //    System.out.print("\n ");
         //   return; 
        //}    
        
        now.data=x;
        now.link=top;
        
        top=now;
    }
    
    public boolean isempty()
    {
        return top==null;
    }
    
    public int peek()
    {
        if(!isempty())
        {
            return top.data;
        }
        else
        {
            System.out.print("Empty stack!");
            return -1;
        }
    }
    public void pop()
    {
        if(top==null)
        {
            System.out.printf("\nEmpty stack, cannot pop any more!");
            return;
        }
        
        top=(top).link;
    }
    public void display()
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
                System.out.printf("%d->",temp.data);
                
                temp=temp.link;
            }
        }
    }
    public  static void main(String[] args){
        Stack obj=new Stack();
        assert obj.isempty()==true;
        System.out.printf("push 1\n");

        obj.push(1);
        assert obj.peek() ==1;
        
        assert obj.isempty()==false;
        System.out.printf("push 2\n");

        obj.push(2);
        assert obj.peek()==2;

        obj.display();
        
        System.out.printf("\nTop element is %d\n",obj.peek());
        System.out.printf("pop 2 times");

        obj.pop();
        assert obj.peek()==1;

        obj.pop();
        assert obj.isempty()==true;
    }
}

