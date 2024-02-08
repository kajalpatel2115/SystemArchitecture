import java.io.*;
import java.util.*;
import java.nio.*;

public class Assembler2 {

    public static int index = 0;
    public static int[][] registers = new int[3][1];
    public static int CC = 0;
    public static int location = 0;

    public static void loc(String[] arr, FileWriter output) throws IOException {
        index = Integer.parseInt(arr[2]);
        String numstr = Integer.toOctalString(index);
        location = Integer.parseInt(numstr);
        
       /* File inFile = new File("./input.txt");
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        String str = "";

        return str;*/
    }

    public static void data(String[] arr, FileWriter output) throws IOException {
        //need to fix
        if(arr[2].equals("End"))
        {
            return;
        }

        int numstr = Integer.parseInt(arr[2]);
        String value = Integer.toOctalString(numstr);
        String indexstr = Integer.toOctalString(index);
        output.write(indexstr + "       " + value + "\n");
        System.out.println(indexstr + "      " + value);
    }

    public static void HLT(FileWriter output) throws IOException {
        output.write("002000 000000;\n");
       // System.out.println("002000 000000;");
    }

    public static void TRAP(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void LDR(String[] arr, FileWriter output) throws IOException {
       // System.out.println("this is what it says " + arr[2]);
        String[] info = arr[2].split(",");
        //System.out.println("this is what it says " + info[0]);
        int register = Integer.parseInt(info[0]) - 1;
        registers[register][0] = Integer.parseInt(info[2]);

        String value = Integer.toOctalString(Integer.parseInt(info[2]));
        String indexstr = Integer.toOctalString(index);
        output.write(indexstr + "       " + value + "\n");
        System.out.println(indexstr + "      " + value);
    }

    public static void LDX(String[] arr, FileWriter output) throws IOException {
       // System.out.println("this is what it says " + arr[2]);
        String[] info = arr[2].split(",");
        //System.out.println("this is what it says " + info[0]);
        int index = Integer.parseInt(info[0]);

        String value = Integer.toOctalString(Integer.parseInt(info[1]));
        String indexstr = Integer.toOctalString(index);
        output.write(indexstr + "       " + value + "\n");
        System.out.println(indexstr + "      " + value);
    }

    public static void SETCCE(String[] arr, FileWriter output) throws IOException {
        if(CC == 0)
        {
            CC = 1;
        }
        else
        {
            CC = 0;
        }
        String value = Integer.toOctalString(Integer.parseInt(arr[2]));
        String indexstr = Integer.toOctalString(index);
        output.write(indexstr + "       " + value + "\n");
        System.out.println(indexstr + "      " + value);
    }

    public static void JZ(String[] arr, FileWriter output) throws IOException {
        if(CC == 1)
        {
            System.out.println("need to jump");
        }
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    public static void LDA(String[] arr, FileWriter output) throws IOException {
        output.write("000000 000000;\n");
        System.out.println("000000 000000;");
    }

    //printing statements are commented out they are use to double check information processing correctly 
    public static void main(String[] args) throws IOException {
        // File paths for input and output files respectively
        File inFile = new File("./input.txt");
        File outFile = new File("output.txt");

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(inFile));

        // Creating a FileWriter object
        FileWriter output = new FileWriter(outFile, true);

        output.write("000000 000000;\n");
        System.out.println("000000 000000;");

        String str;

        while ((str = br.readLine()) != null) {
            // manual checks
            System.out.println(str);
            //System.out.println(str.split("\\s+"));

            String[] arr = str.split("\\s+");
            //System.out.println(arr[2]);
           
            switch (arr[1]) {
                case "LOC":
                    loc(arr, output);
                    index = index + 1;
                    break;
                case "Data":
                    data(arr, output);
                    index = index + 1;
                    break;
                case "TRAP":
                    System.out.println("TRAP");
                    TRAP(arr, output);
                    break;
                case "HLT":
                    HLT(output);
                    return;
                case "LDX":
                    System.out.println("LDX");
                    LDX(arr, output);
                    break;
                case "LDR":
                    System.out.println("LDR");
                    LDR(arr, output);
                    break;
                case "JZ":
                    System.out.println("JZ");
                    JZ(arr, output);
                    break;
                case "LDA":
                    System.out.println("LDA");
                    LDA(arr, output);
                    break;
                case "SETCCE":
                    System.out.println("SETCCE");
                    SETCCE(arr, output);
                    break;
                default:
                    System.out.println("Unknown instruction: " + arr[1]);
                    break;
            }
        }

        // Closing 
        br.close();
        output.close();
    }
}
