package Swing;

import java.util.HashMap;

import javax.swing.*;
//This class is where the the backend of instruction computatation 
//occurs
public class Compution {

	public JFrame frame;
	//HashMap represents our memory
	HashMap<String, String> steps = new HashMap<String, String>();
	//Load/Store Opcode values
	public Compution(JFrame j) {
		frame = j;
	}
	
	//Loads a value from memory 
	public String load(String input) {
		if (steps.get(input) == null) {
			return "00000000";
		}
		return steps.get(input);
		
	}
	
	//Stores inserted instruction with its address into memory
	public void store(String input, String value) {
		steps.put(input,value);
	}
	
	//Computing the Exact Address and getting its content 
	public String register(String value) {
		int val = Integer.parseInt(value,2);
		int r = (val >> 8) & 3;
		int ix = (val >> 6) & 3;
		int i = (val >> 5) & 1;
		int adr = val & 31;
		
		//Load the index registers
		String xr1 = load("xr1");
		String xr2 = load("xr2");
		String xr3 = load("xr3");
		
		//Adding the index registers into the Address
		if (ix == 1) {
			int x1 = Integer.parseInt(xr1,2);
			adr = adr+x1;
		} else if(ix == 2) {
			int x2 = Integer.parseInt(xr2,2);
			adr = adr+x2;
		} else if(ix == 3) {
			int x3 = Integer.parseInt(xr3,2);
			adr = adr+x3;
		}
		
		
		String address = Integer.toBinaryString(adr);
		String result = load(address);
		
		//Indirect addressing
		if(i == 1) {
			result = load(result); 
		}
		
		return result;
		
	}
}
