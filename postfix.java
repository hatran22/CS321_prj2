import java.util.*;
import javax.xml.transform.OutputKeys;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

public class postfix {

    public static void main(String args[]) throws IOException{
        //test code: 
        postfix(args);
    }
    static int precedence(char c){
        // the order of the operations:
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
    public static boolean isoperand(char c) {
        //check operand
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z' ) || c==' ';
    }

    public static boolean checkparen(String s){
        //check for valid parenthsization
        int stack = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(c == '(')
                ++stack;
            else if(c == ')'){
                --stack;
                if(stack < 0)
                    return false;
            }
        }
        return stack == 0;
    }

    public static boolean isvalid(String s){
        //chek for valid syntax
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // go through each operand  
            if(isoperand(c) == false && precedence(c) < 0 && c != '(' && c != ')')
                return false;
        }
        return true;
    }
    public static boolean checkinput(String[] input) {

        if (input.length>2 | input.length<1) {
            System.out.print("please use postfix [input] [output] or postfix [input],\n in which case it will be outputted to the input directory \n");
            return false;
        }
        if (input.length==1) {
            //then the input directory must exist;
            File file = new File(input[2]);
            if ( !file.exists()) {
                System.out.print("file doesnt exist!");
                return false;
            }
            return true;
        }
        else if (input.length==2) {
            File in_dir = new File(input[0]);
            File out_dir = new File(input[1]);
            if (!in_dir.exists()) {
                System.out.print("input directory doesnt exist \n");
                return false;
            }
            if (! out_dir.isDirectory()) {
                System.out.print("output folder doesnt exist \n"); 

                return false;
            }
        }
        return true;
    }
    public static String I2P(String infix) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <infix.length() ; i++) {
            char cur = infix.charAt(i);
            
            if(precedence(cur)>0){
                //if we have an operation
                while(stack.isempty()==false && precedence(stack.peek())>=precedence(cur)){
                    result += stack.peek();
                    stack.pop();
                }
                stack.push(cur);
            }else if(cur==')'){
                // else if we have a closed parenthesis
                char x = stack.peek();
                stack.pop();
                while(x!='('){
                    result += x;
                    x = stack.peek();
                    stack.pop();
                }
            }else if(cur=='('){
                stack.push(cur);
            }else{
                result += cur;
            }
        }
        while (!stack.isempty()) {
            //unwire everything
            result += stack.peek();
            stack.pop();
        }
        return result;
    }
    
    public static void postfix(String[] args) throws IOException {
        if (! checkinput(args)) {
            System.out.print("input error!");

            return; 
        }
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        File file = new File(args[1]+"/out.txt");
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String s;
        while ((s=in.readLine())!=null) {
            //line must end with semicolon:
            if (!s.trim().endsWith(";")) {
                System.out.println("incorrect format");
                return;
            }
            String splitted = s.split(";")[0]; 
            System.out.println(splitted);
            if (isvalid(splitted)) {
                if(checkparen(splitted)){
                    String postfix = I2P(splitted);
                    System.out.println(postfix+";");

                    writer.write(postfix+";");
		            writer.newLine();
                }
                else{
                    System.out.print("invalid syntax");
                    return;
                }
            } else {
                System.out.print("invalid syntax");
                return;    
            }
        }
        writer.close();
    }


}