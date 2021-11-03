import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Stack;
public class I2P_parser {

    public static boolean isoperand(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }
    private static int precedence(char i) {

        if (i == '(' || i == ')') return 1;
        else if (i == '-' || i == '+') return 2;
        else if (i == '*' || i == '/') return 3;
        else return 0;
    }
    public static boolean checkparen(String s){
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
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(isoperand(c) == false && precedence(c) == 0 && c != '(' && c != ')')
                return false;
        }
        return true;
    }
    public static boolean checkinput(String[] input) {
        if (input.length>2 | input.length<1) {
            println("please use postfix [input] [output] or postfix [input],\n in which case it will be outputted to the input directory");
            return false;
        }
        if (input.length==1) {
            //then the input directory must exist;
            File file = new File(input[2]);
            if ( !file.exists()) {
                println("file doesnt exist bruh!");
                return false;
            }
            return true;
        }
        else if (input.length==2) {
            File in_dir = new File(input[1]);
            File out_dir = new File(input[2]);
            if (!in_dir.exists()) {
                println("input directory doesnt exist");
                return false;
            }
            if (! out_dir.isDirectory()) {
                println("output folder doesnt exist");
                return false;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        if (! checkinput(args)) {
            return; 
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(args[1]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[2], true));

        PrintStream out = new PrintStream(System.out);
        //String[] posts = new String[];
        while (true) {
            String s = in.readLine();
            
            if (isvalid(s)) {
                if(checkparen(s)){
                    String postfix = I2P(s);
                    
                }
                else{
                    out.println("Syntax Error!");
                }
            } else {
                out.println("Lexical Error!");
            }
        }
    }
}