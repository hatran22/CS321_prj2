import java.io.IOException;
import java.util.*;

import javax.print.DocFlavor.STRING;
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


public class assembler extends Postfix {

    public static void main(String[] args) throws IOException {
        to_assemblies( args);
    }
    public static boolean checkinput(String[] input) {

        if (input.length>2 ) {
            System.out.print("please use postfix [input] [output] or postfix [input],\n in which case it will be outputted to the terminal \n");
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
    public static void to_assemblies(String[] args) throws IOException {
        if (! checkinput(args)) {
            System.out.print("input error!");

            return; 
        }
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        int n=0;
        String s;
        while ((s=in.readLine())!=null) {
            n=n+1;
        
            
            //line must end with semicolon:
            if (!s.trim().endsWith(";")) {
                System.out.println("incorrect format");
                return;
            }
            String splitted = s.split(";")[0]; 

            System.out.println(splitted);
                    String assembly = I2P(splitted);

                    if (args.length>1) {
                        File file = new File(args[1]+"/out."+n+".txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                        file.createNewFile();
                        writer.write(assembly);
                        writer.close();
                    }
                    else {
                        System.out.print(assembly);
                    }
                
            
            
        }
    }
}