package Swing;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;

import javax.swing.*;

public class Simulator extends Frame {
	
	public static JButton run, STEP, HLT, load, store, octInput, IPL;
	public static JButton GR0,GR1,GR2,GR3,XR1,XR2,XR3,PCbutton,MRA,MRB;
	public static FileReader output;
	public static int PC;
	public Compution compute;
	
	  public Simulator(){
		  
		  JFrame frame = new JFrame("CSCI6461 Front Panel");
		  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      frame.setSize(800, 800);
	      frame.setVisible(true);
	      JPanel panel = new JPanel();
	      panel.setSize(800, 200);
	      panel.setBackground(Color.cyan);
	      frame.add(panel, BorderLayout.SOUTH);
	      JPanel panel2 = new JPanel();
	      //panel2.setSize(800, 600);
	      panel2.setBackground(Color.RED);
	      frame.add(panel2, BorderLayout.CENTER);
		
		  
	    //add JButtons to the Panel
	    run = new JButton("Run");
	    run.setBounds(50, 50, 50, 50);
	    run.setBackground(Color.WHITE);
	    run.addActionListener(e -> {
	        try {
	            runAssembler();
	        } catch (Exception ex) {
	            ex.printStackTrace(); // or handle the exception appropriately
	        }
	    });
	    panel.add(run);
	    STEP = new JButton("STEP");
	    STEP.setBounds(50, 50, 50, 50);
	    STEP.setBackground(Color.WHITE);
	    STEP.addActionListener(e -> STEPAssembler());
	    panel.add(STEP);
	    HLT = new JButton("HLT");
	    HLT.setBounds(50, 50, 50, 50);
	    HLT.setBackground(Color.WHITE);
	    HLT.addActionListener(e -> HaltAssembler());
	    panel.add(HLT);
	    load = new JButton("Load");
	    load.setBounds(50, 50, 50, 50);
	    load.setBackground(Color.WHITE);
	    load.addActionListener(e -> loadAssembler());
	    panel.add(load);
	    store = new JButton("Store");
	    store.setBounds(50, 50, 50, 50);
	    store.setBackground(Color.WHITE);
	    store.addActionListener(e -> storeAssembler());
	    panel.add(store);
	    
	    octInput = new JButton("Enter");
	    JLabel octNum = new JLabel("Octal value");
	    JTextField input = new JTextField(8);
	    input.setPreferredSize(new Dimension(100, 20));
	    panel.add(octNum);
	    panel.add(input);
	    panel.add(octInput);
	    octInput.addActionListener(e -> processInput(input.getText()));
	    
	    IPL = new JButton("IPL");
	    IPL.addActionListener(e -> IPLaction());
	    panel.add(IPL);
	      
	    //add stuff to the Panel2
	    JTextField GRegister0 = new JTextField(8);
	    GRegister0.setBackground(Color.WHITE);
	    GR0 = new JButton("GR0");
	    GR0.setBounds(50, 50, 50, 50);
	    GR0.setBackground(Color.WHITE);
	    GR0.addActionListener(e -> GPRprocess(GRegister0.getText(), 0));
	    panel2.add(GRegister0);
	    panel2.add(GR0);
	    JTextField GRegister1 = new JTextField(8);
	    GRegister1.setBackground(Color.WHITE);
	    GR1 = new JButton("GR1");
	    GR1.setBounds(50, 50, 50, 50);
	    GR1.setBackground(Color.WHITE);
	    GR1.addActionListener(e -> GPRprocess(GRegister1.getText(), 1));
	    panel2.add(GRegister1);
	    panel2.add(GR1);
	    JTextField GRegister2 = new JTextField(8);
	    GRegister2.setBackground(Color.WHITE);
	    GR2 = new JButton("GR2");
	    GR2.setBounds(50, 50, 50, 50);
	    GR2.setBackground(Color.WHITE);
	    GR2.addActionListener(e -> GPRprocess(GRegister2.getText(), 2));
	    panel2.add(GRegister2);
	    panel2.add(GR2);
	    JTextField GRegister3 = new JTextField(8);
	    GRegister3.setBackground(Color.WHITE);
	    GR3 = new JButton("GR3");
	    GR3.setBounds(50, 50, 50, 50);
	    GR3.setBackground(Color.WHITE);
	    GR3.addActionListener(e -> GPRprocess(GRegister3.getText(), 3));
	    panel2.add(GRegister3);
	    panel2.add(GR3);
	    
	    JTextField XRegister1 = new JTextField(8);
	    XRegister1.setBackground(Color.WHITE);
	    XR1 = new JButton("XR1");
	    XR1.setBounds(50, 50, 50, 50);
	    XR1.setBackground(Color.WHITE);
	    XR1.addActionListener(e -> XRprocess(XRegister1.getText(), 1));
	    panel2.add(XRegister1);
	    panel2.add(XR1);
	    JTextField XRegister2 = new JTextField(8);
	    XRegister2.setBackground(Color.WHITE);
	    XR2 = new JButton("XR2");
	    XR2.setBounds(50, 50, 50, 50);
	    XR2.setBackground(Color.WHITE);
	    XR2.addActionListener(e -> XRprocess(XRegister2.getText(), 2));
	    panel2.add(XRegister2);
	    panel2.add(XR2);
	    JTextField XRegister3 = new JTextField(8);
	    XRegister3.setBackground(Color.WHITE);
	    XR3 = new JButton("XR3");
	    XR3.setBounds(50, 50, 50, 50);
	    XR3.setBackground(Color.WHITE);
	    XR3.addActionListener(e -> XRprocess(XRegister3.getText(), 3));
	    panel2.add(XRegister3);
	    panel2.add(XR3);
	    
	    JTextField PCinput = new JTextField(8);
	    PCinput.setBackground(Color.WHITE);
	    PCbutton = new JButton("PC");
	    PCbutton.setBounds(50, 50, 50, 50);
	    PCbutton.setBackground(Color.WHITE);
	    PCbutton.addActionListener(e -> PCload());
	    panel2.add(PCinput);
	    panel2.add(PCbutton);
	    
	    JTextField MRAinput = new JTextField(8);
	    MRAinput.setBackground(Color.WHITE);
	    MRA = new JButton("MRA");
	    MRA.setBounds(50, 50, 50, 50);
	    MRA.setBackground(Color.WHITE);
	    MRA.addActionListener(e -> MRAload(MRAinput.getText()));
	    panel2.add(MRAinput);
	    panel2.add(MRA);
	    JTextField MRBinput = new JTextField(8);
	    MRBinput.setBackground(Color.WHITE);
	    MRB = new JButton("MRB");
	    MRB.setBounds(50, 50, 50, 50);
	    MRB.setBackground(Color.WHITE);
	    MRB.addActionListener(e -> MRBload(MRBinput.getText()));
	    panel2.add(MRBinput);
	    panel2.add(MRB);
	    
	    compute = new Compution(frame);
	  }

	  public static void main(String args[]){
	    new Simulator();
	  }
	  
	  public static void IPLaction()
	  {
		  System.out.println("IDK");
	  }
	  
	  public static void PCload()
	  {
		  PC++;
		  System.out.println("PC: " + PC);
	  }
	  
	  public static void MRAload(String input)
	  {
		  int in = Integer.parseInt(input);
		  System.out.println("MRA: " + in);
	  }
	  
	  public static void MRBload(String input)
	  {
		  int in = Integer.parseInt(input);
		  System.out.println("MRB: " + in);
	  }
	  
	  public static void runAssembler() throws IOException
	  {
		  output = new FileReader("./output.txt");
		  
		  System.out.println("Running ...");
		  BufferedReader br = new BufferedReader(output);
		  String str;
		  while ((str = br.readLine()) != null){
			  System.out.println(str);
		  }
		  System.out.println("DONE");
	  }
	  
	  public static void HaltAssembler()
	  {
		  System.out.println("HALT");
	  }
	  
	  public static void STEPAssembler()
	  {
		  System.out.println("STEP");
	  }
	  
	  public static void loadAssembler()
	  {
		  System.out.println("load");
	  }
	  
	  public static void storeAssembler()
	  {
		  System.out.println("store");
	  }
	  
	  public static void GPRprocess(String input, int register)
	  {
		  int in = Integer.parseInt(input);
		  System.out.println("GRPprocess: " + in);
	  }
	  
	  public static void XRprocess(String input, int register)
	  {
		  int in = Integer.parseInt(input);
		  System.out.println("XRPprocess: " + in);
	  }
	  
	  public static void processInput(String input)
	  {
		  int in = Integer.parseInt(input);
		  System.out.println("Input: " + in);
	  }
	  
	  public static void loadStore(String index, String value) {
		  
	  }
	  public void step(String index, String value) 
	  {
			int val = Integer.parseInt(value);
			//Have to complete parsing the binary files and adding the to respective spots 
			if() {
				
			}
			
	  }
	  
	  
	}
