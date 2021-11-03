import java.util.*;
import javax.xml.transform.OutputKeys;

public class postfix {

    public static void main(String args[]) {
        //test code: 
        String infix = "( ( AX + ( BY * C ) ) / ( D4 - E ) )";

        System.out.println("Infix : " + infix);
        //first test:
        assert I2P(infix)=="AX   BY  C * +   D4  E - /";
        System.out.println("postfix : " + I2P(infix));
        //second test:
        assert I2P("(AX*C-BY)*D")=="AX C * BY - D *";
        assert I2P("AX/D+BY*C")=="AX D / BY C * +";
        assert I2P("(AX/D+BY)*(C+(D4-E/Y))")=="AX D / BY + C D4 E Y / - + *";
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

    public static String I2P(String infix) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <infix.length() ; i++) {
            char cur = infix.charAt(i);

            if(precedence(cur)>0){
                while(stack.isempty()==false && precedence(stack.peek())>=precedence(cur)){
                    result += stack.peek();
                    stack.pop();
                }
                stack.push(cur);
            }else if(cur==')'){
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
            result += stack.peek();
            stack.pop();
        }
        return result;
    }


}