package support;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

//Main class where the main method of the Assembler is housed 
public class Assembler {
	//Common Variables in the Class:
	//r: General purpose register
	//i: indirect addressing
	//ix: index register
	//adr: address
	//opcode: opcode 
	//ind: indirect addressing
	//imed: intermediate 
	//code: code for a Trap instruction
	
	
	//Index variable 
	public static int index = 0;
	
	//Method for the LOC instruction
    public static void loc(String[] arr, FileWriter output){
        index = Integer.parseInt(arr[1]);
        String numstr = Integer.toOctalString(index);
        //output.write(index+"         000000;%n");

    }
    
    //Method for Data instruction
     public static void data(String[] arr, FileWriter output){
        int numstr = Integer.parseInt(arr[1]);
        String value = Integer.toOctalString(numstr);
        String indexstr = Integer.toOctalString(index);
        //output.write("000000       "+value+"%n");
        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        
        try {
			output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);

    }

   //Method for Halt instruction
    public static void hlt(FileWriter output){
        //output.write("000000 000000;%n");
    	try {
			output.write("000000 000000;\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println("000000 000000;");
    }

  //Method for data instructions with up to 4 parameters
    public static void method_one(String[] arr, int opcode, FileWriter output){
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

        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();

        try {
			output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);

    }

  //Method for data instructions up to 3 parameters
    public static void method_two(String[] arr, int opcode, FileWriter output){
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

        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        
        try {
			output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);

    }

    //Methods for data instructions with only two parameter
    public static void method_three(String[] arr, int opcode, FileWriter output){
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int adr = Integer.parseInt(para[1]);
        
        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8) | adr;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        try {
			output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);

    }
    
    //Method for RFS instruction
    public static void rfs(String[] arr, int opcode, FileWriter output){
        int imed = Integer.parseInt(arr[1]);

        int result = opcode;
        result = (result << 10) | imed;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);
        
        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        
        try {
			output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);
    }

    //Method for setcce instruction
    public static void setcce(String[] arr, int opcode, FileWriter output){
        int r = Integer.parseInt(arr[1]);

        int result = opcode;
        result = (result << 2) | r;
        result = (result << 8);

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);
        
        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        try {
			output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value);
    }
    
    //Method for Trap instruction
    public static void trap(String[]arr, int opcode, FileWriter output) {
    	int code = Integer.parseInt(arr[1]);
    	int result = opcode;
    	result = (result << 10)| code;
    	
    	String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);
        
        int indexZero = 6 - indexstr.length();
        int valueZero = 6 - value.length();
        try {
			output.write("0".repeat(indexZero)+indexstr+"      "+ "0".repeat(valueZero)+value+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    //Main method for the Assembler
    public static void main(String [] args) throws Exception
    {
        //Listings of load/store instructions
        HashMap<String, String> loadStore = new HashMap<String, String>();
        //Instructions for four parameters
        loadStore.put("LDR", "01");
        loadStore.put("STR", "02");
        loadStore.put("LDA", "03");
        loadStore.put("STR", "02");
        loadStore.put("JNE", "07");
        loadStore.put("JCC", "10");
        loadStore.put("SOB", "14");
        loadStore.put("JGE", "15");
        loadStore.put("AMR", "16");
        loadStore.put("SMR", "17");
        //Instructions for three parameters
        loadStore.put("JZ", "06");
        loadStore.put("JMA", "11");
        loadStore.put("JSR", "12");
        loadStore.put("LDX", "04");
        loadStore.put("STX", "05");
        
        //Listing of Arithmetic/Logical instructions 
        HashMap<String, String> arithLogs = new HashMap<String, String>();
        //Memory/Register exchange instructions
        arithLogs.put("AMR", "16");
        arithLogs.put("SMR", "17");
        //Instructions with Intermediates 
        arithLogs.put("AIR","20");
        arithLogs.put("SIR", "21");
        //Instructions for register to register operations
        arithLogs.put("MLT", "22");
        arithLogs.put("DVD", "23");
        arithLogs.put("TRR", "24");
        arithLogs.put("AND", "25");
        arithLogs.put("ORR", "26");
        arithLogs.put("NOT", "27");
        //Instructions for shift/rotation operations
        arithLogs.put("SRC", "30");
        arithLogs.put("RRC", "31");

        //Listing of Input/Output instructions 
        HashMap<String, String> inputOutput = new HashMap<String, String>();
        //Methods with 2 parameters(Input/Output Operations)
        inputOutput.put("IN", "32");
        inputOutput.put("OUT", "33");
        inputOutput.put("CHK", "34");
        //Methods for floating points
        HashMap<String, String> floating = new HashMap<String, String>();
        floating.put("FADD", "35");
        floating.put("FSUB", "36");
        floating.put("VADD", "37");
        floating.put("VSUB", "40");
        floating.put("CNVRT", "41");
        floating.put("LDFR", "42");
        floating.put("STFR", "43");

        //File paths for input and output files respectively 
        File inFile = new File(System.getProperty("user.dir") + "/"+ "input.txt");
        File outFile = new File(System.getProperty("user.dir") + "/"+"output.txt");
        
        //Object for Arithmetic and Logical instruction 
        ArithLog arithlog = new ArithLog();
        //Object for Input Output instruction
        InputOutput inputOut = new InputOutput();

        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(inFile));

        //Creating a FileWriter object
        FileWriter output = new FileWriter(outFile, true);
   

        //output.write("000000 000000;%n");
        output.write("000000      000000;\n");

        String str;

        
        //Loop through the lines of an input file
        while ((str = br.readLine()) != null){
            String [] arr = str.split("\\s+");
            
            //If the index is larger than 1024, and error is pushed 
            if(index > 1024) {
            	System.out.println("Error!");
            	break;
            }
            
            switch(arr[0]){
                case "LOC":
                    loc(arr, output);
                    break;
                case "Data":
                    data(arr,output);
                    index = index + 1;
                    break;
                case "RFS":
                    rfs(arr, Integer.parseInt("13", 8), output);
                    index = index + 1;
                case "SETCCE":
                    setcce(arr, Integer.parseInt("44", 8), output);
                    index = index + 1;
                case "TRAP":
                	trap(arr, Integer.parseInt("45", 8), output);
                    index = index + 1;
                default:
                    break;
                
            }

            if (loadStore.get(arr[0]) != null){
                String op = loadStore.get(arr[0]);
                //Load/Store instructions with at most 4 parameters 
                if(op == "06" || op == "11" || op == "12" || op == "04" || op == "05"){
                   method_two(arr, Integer.parseInt(op,8), output); 
                   index = index + 1;
                } else {
                //Load/Store instructions with at most 3 parameters
                   method_one(arr, Integer.parseInt(op, 8), output);
                   index = index + 1;
                }
                    
            }
            
            if (arithLogs.get(arr[0]) != null){
                String op = arithLogs.get(arr[0]);
                //Memory/Register exchange instructions
                if(op == "16" || op == "17"){
                   arithlog.register(arr, Integer.parseInt(op,8), output, index); 
                   index = index + 1;
                 //Intermediate to Register instructions  
                }else if(op == "20" || op == "21") {
                   arithlog.method_three(arr, Integer.parseInt(op, 8), output, index);
                   index = index + 1;
                //Shift operation instructions 
                }else if(op == "30" || op == "31") {
                	arithlog.shift(arr, Integer.parseInt(op, 8), output, index);
                	index = index + 1;
                //Register to Register operation instructions 
                } else {
                	arithlog.operations(arr, Integer.parseInt(op, 8), output, index);
                	index = index + 1;
                }
                    
            }
            
            //Input/Output instructions 
            if (inputOutput.get(arr[0]) != null){
               String op = inputOutput.get(arr[0]);
               inputOut.io_operations(arr, Integer.parseInt(op,8), output, index); 
               index = index + 1;
            //Floating Point instructions 
            }else if(floating.get(arr[0]) != null){
            	String op = floating.get(arr[0]);
            	inputOut.floating_point(arr, Integer.parseInt(op, 8), output, index);
            	index = index + 1;
            	}
                    
            }
          output.close();
        }
    }

