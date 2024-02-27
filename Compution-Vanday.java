package Swing;

import java.util.HashMap;

import javax.swing.*;

public class Compution {

	public JFrame frame;
	//HashMap that will hold all of the instructions 
	HashMap<String, String> steps = new HashMap<String, String>();
	//Load/Store Opcode values
	public Compution(JFrame j) {
		frame = j;
	}
	
	public void input(String input) {
		 int value = Integer.parseInt(input,8); 
	}
	public void load(JTextField input, JButton button) {
		
	}
	public void store(JTextField input, JButton button) {
		
	}
	
	public void insert(String index, String value) {
		steps.put(index, value);
	}
	
	public void step(String index, String value) {
		
		if
	}
}
