package support;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Assembler {
	public static int index = 0;
    public static void loc(String[] arr, FileWriter output){
        //Method for the LOC instruction
        index = Integer.parseInt(arr[1]);
        String numstr = Integer.toOctalString(index);
        //output.write(index+"         000000;%n");

    }

     public static void data(String[] arr, FileWriter output){
        //Method for Data instruction
        int numstr = Integer.parseInt(arr[1]);
        String value = Integer.toOctalString(numstr);
        String indexstr = Integer.toOctalString(index);
        //output.write("000000       "+value+"%n");
        System.out.println(indexstr+"      "+value);
    }

    public static void hlt(FileWriter output){
        //Method for Halt instruction
        //output.write("000000 000000;%n");
        System.out.println("000000 000000;");
    }

    public static void method_one(String[] arr, int opcode, FileWriter output){
        //Method for isntructions with up to 4 parameters
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int ix = Integer.parseInt(para[1]);
        int adr = Integer.parseInt(para[2]);
        int ind = 0;

        if (para.length == 4){
            ind = Integer.parseInt(para[3]);
        }

        int result = (opcode << 2) | r;
        result = (result << 2) | ix;
        result = (result << 1) | ind;
        result = (result << 5) | adr;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        System.out.println(indexstr+"      "+value);
    }

    public static void method_two(String[] arr, int opcode, FileWriter output){
        //Method for instructions up to 3 parameters
        String [] para = arr[1].split(",");
        int ix = Integer.parseInt(para[0]);
        int adr = Integer.parseInt(para[1]);
        int ind = 0;

        if (para.length == 3){
            ind = Integer.parseInt(para[2]);
        }

        int result = opcode;
        result = (result << 4) | ix;
        result = (result << 1) | ind;
        result = (result << 5) | adr;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        System.out.println(indexstr+"      "+value);
    }

    public static void method_three(String[] arr, int opcode, FileWriter output){
        //Methods for instructions with only two parameter
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int adr = Integer.parseInt(para[1]);
        
        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8) | adr;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        System.out.println(indexstr+"      "+value);
    }
    
    public static void rfs(String[] arr, int opcode, FileWriter output){
        //Method for RFS instrcution
        int imed = Integer.parseInt(arr[1]);

        int result = opcode;
        result = (result << 10) | imed;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        System.out.println(indexstr+"      "+value);
    }

    public static void setcce(String[] arr, int opcode, FileWriter output){
        //Method for setcce instruction
        int r = Integer.parseInt(arr[1]);

        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8);

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        System.out.println(indexstr+"      "+value);
    }
    public static void main(String [] args) throws Exception
    {
        //Listings of load/store instructions
        HashMap<String, Integer> loadStore = new HashMap<String, Integer>();
        //Instructions for four parameters
        loadStore.put("LDR", 1);
        loadStore.put("STR", 2);
        loadStore.put("LDA", 3);
        loadStore.put("STR", 2);
        loadStore.put("JNE", 7);
        loadStore.put("JCC", 10);
        loadStore.put("SOB", 14);
        loadStore.put("JGE", 15);
        loadStore.put("AMR", 16);
        loadStore.put("SMR", 17);
        //Instructions for three parameters
        loadStore.put("JZ", 6);
        loadStore.put("JMA", 11);
        loadStore.put("JSR", 12);
        loadStore.put("LDX", 4);
        loadStore.put("STX", 5);
        
        HashMap<String, Integer> arithLogs = new HashMap<String, Integer>();
        arithLogs.put("AMR", 16);
        arithLogs.put("SMR", 17);
        //Instructions with Intermediates 
        arithLogs.put("AIR", 20);
        arithLogs.put("SIR", 21);
        //Instructions with rx and ry
        arithLogs.put("MLT", 22);
        arithLogs.put("DVD", 23);
        arithLogs.put("TRR", 24);
        arithLogs.put("AND", 25);
        arithLogs.put("ORR", 26);
        arithLogs.put("NOT", 27);
        //Instructions with shift
        arithLogs.put("SRC", 30);
        arithLogs.put("RRC", 31);

        HashMap<String, Integer> inputOutput = new HashMap<String, Integer>();
        inputOutput.put("IN", 32);
        inputOutput.put("OUT", 33);
        inputOutput.put("CHK", 34);
        inputOutput.put("FADD", 35);
        inputOutput.put("FSUB", 36);
        inputOutput.put("VADD", 37);
        inputOutput.put("VSUB", 40);
        inputOutput.put("CNVRT", 41);
        inputOutput.put("LDFR", 42);
        inputOutput.put("STFR", 43);

        //File paths for input and output files respectively 
        File inFile = new File(System.getProperty("user.dir") + "/"+ "input.txt");
        File outFile = new File("output.txt");

        ArithLog arithlog = new ArithLog();

        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(inFile));

        //Creating a FileWriter object
        FileWriter output = new FileWriter(outFile, true);

        //output.write("000000 000000;%n");
        System.out.println("000000 000000;");

        String str;


        while ((str = br.readLine()) != null){
            int opcode = 0;
            String [] arr = str.split("\\s+");
            
            switch(arr[0]){
                case "LOC":
                    loc(arr, output);
                    break;
                case "Data":
                    data(arr,output);
                    index = index + 1;
                    break;
                case "RFS":
                    rfs(arr, 13, output);
                    index = index + 1;
                case "SETCCE":
                    setcce(arr, 44, output);
                    index = index + 1;
                default:
                    break;
                
            }

            if (loadStore.get(arr[0]) != null){
                int op = loadStore.get(arr[0]);
                if(op == 6 || op == 11 || op == 12 || op == 4 || op == 5){
                   method_two(arr, op, output); 
                   index = index + 1;
                } else {
                   method_one(arr, op, output);
                   index = index + 1;
                }
                    
            }

        }
    }
}
