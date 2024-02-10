package support;

import java.io.FileWriter;
import java.io.IOException;

public class ArithLog {
	//Common Variables in the Class:
	//r: General purpose register
	//i: indirect addressing
	//ix: index register
	//adr: address
	//opcode: opcode 
	//ind: intermediate
	//devid: Device ID
	//indexZero/valueZero: Fill out remaining zeros for index and instruction value respectively 
	//rx/ry: regsters(R0-R3)
	//lr: L/R
	//al: A/L
	
	
	//Holds the methods for Arithmetic/Logical Instructions
    public ArithLog(){

    }

    //Method for Memory/Register instructions
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

    //Method for shift operations
    public void shift(String[] arr, int opcode, FileWriter output, int index){
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

    //Method for Memory/Register instructions
    public void register(String[] arr, int opcode, FileWriter output, int index){
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

    //Methods for instructions with only two parameter
    public void method_three(String[] arr, int opcode, FileWriter output, int index){
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
}
