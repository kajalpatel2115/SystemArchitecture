package Swing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.*;
//Purpose: Supporter class to the Simulator where memory is handled,
//and addressing is performed.
public class Compution {

	//public JFrame frame;
	//HashMap represents our memory
	HashMap<String, String> steps = new HashMap<String, String>();
	//Cache variable 
	Stack<Cache> cache = new Stack<Cache>();
	public Compution() {
		//frame = j;
		steps.put("cc","0000");
	}
	
	//Loads a value from memory 
	public String load(String input) {
		if (steps.get(input) == null) {
			if(input == "xr1" || input == "xr2" || input == "xr3") {
				return "00000";
			}
			return input;
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
	
	public HashMap<String, String> memory(){
		return steps;
	}
	
	public void cacheProcessor(String input, String value) {
		
		int octal = Integer.parseInt(input, 8);
		int tag = octal >> 2;
		int blockNum = octal & 3;
		Iterator<Cache> cacheIt = cache.iterator();
		String cacheBlock = "";
		
		
		while (cacheIt.hasNext()) { 
			Cache currCache = cacheIt.next();
            cacheBlock = currCache.getTag();
            if(cacheBlock == Integer.toString(tag)) {
            	String[]blocks = currCache.getBlockNum();
            	if(blocks[blockNum] == "") {
            		blocks[blockNum] = value;
            	} else {
            		System.out.println("Data Found in Cache!!");
            	}
            	return;
            }
        } 
		if (cache.size() >= 16) {
			cache.pop();
		}
		
		cache.push(new Cache(input));
	}
}
