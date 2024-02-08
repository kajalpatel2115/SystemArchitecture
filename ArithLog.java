package support;

import java.io.FileWriter;

public class ArithLog {
	//Holds the methods for Arithmetic/Logical Instructions
    public ArithLog(){

    }

    public void operations(String[] arr, int opcode, FileWriter output, int index){
        int rx = 0;
        int ry = 0;
        String [] para = arr[1].split(",");
        rx = Integer.parseInt(para[0]);
        if (para.length == 2){
            ry = Integer.parseInt(para[1]);
        }
        int result = opcode;
        result = (result << 2) | rx;
        result = (result << 2) | ry;
        result = (result << 6); 

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        System.out.println(indexstr+"      "+value);

    }

    public void shift(String[] arr, int opcode, FileWriter output, int index){
        //Method for shift instructions
        String [] para = arr[1].split(",");
        int r = Integer.parseInt(para[0]);
        int count = Integer.parseInt(para[1]);
        int lr = Integer.parseInt(para[2]);
        int al = Integer.parseInt(para[3]);
        

        int result = opcode;
        result = (result << 2) | r;
        result = (result << 1) | al;
        result = (result << 1) | lr; 
        result = (result << 6) | count;

        String value = Integer.toOctalString(result);
        String indexstr = Integer.toOctalString(index);

        System.out.println(indexstr+"      "+value);

    }

    public void register(String[] arr, int opcode, FileWriter output, int index){
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

    public static void method_three(String[] arr, int opcode, FileWriter output, int index){
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
}
