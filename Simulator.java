import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Simulator extends Frame {
	
	public static JButton run, STEP, HLT, load, store;
	public static JButton GR0,GR1,GR2,GR3,XR1,XR2,XR3,PC,MRA,MRB;
	
	  public Simulator() {
		  
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
	    run.addActionListener(e -> runAssembler());
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
	    
	    JLabel octNum = new JLabel("Octal value");
	    JTextField input = new JTextField("000000");
	    input.setPreferredSize(new Dimension(100, 20));
	    panel.add(octNum);
	    panel.add(input);
	      
	    //add JButtons to the Panel2
	    JTextField GRegister0 = new JTextField("00000000");
	    GRegister0.setBackground(Color.WHITE);
	    GR0 = new JButton("GR0");
	    GR0.setBounds(50, 50, 50, 50);
	    GR0.setBackground(Color.WHITE);
	    GR0.addActionListener(e -> GPRprocess());
	    panel2.add(GRegister0);
	    panel2.add(GR0);
	    JTextField GRegister1 = new JTextField("00000000");
	    GRegister1.setBackground(Color.WHITE);
	    GR1 = new JButton("GR1");
	    GR1.setBounds(50, 50, 50, 50);
	    GR1.setBackground(Color.WHITE);
	    GR1.addActionListener(e -> GPRprocess());
	    panel2.add(GRegister1);
	    panel2.add(GR1);
	    JTextField GRegister2 = new JTextField("00000000");
	    GRegister2.setBackground(Color.WHITE);
	    GR2 = new JButton("GR2");
	    GR2.setBounds(50, 50, 50, 50);
	    GR2.setBackground(Color.WHITE);
	    GR2.addActionListener(e -> GPRprocess());
	    panel2.add(GRegister2);
	    panel2.add(GR2);
	    JTextField GRegister3 = new JTextField("00000000");
	    GRegister3.setBackground(Color.WHITE);
	    GR3 = new JButton("GR3");
	    GR3.setBounds(50, 50, 50, 50);
	    GR3.setBackground(Color.WHITE);
	    GR3.addActionListener(e -> GPRprocess());
	    panel2.add(GRegister3);
	    panel2.add(GR3);
	    
	    XR1 = new JButton("XR1");
	    XR1.setBounds(50, 50, 50, 50);
	    XR1.setBackground(Color.WHITE);
	    XR1.addActionListener(e -> XRprocess());
	    panel2.add(XR1);
	    XR2 = new JButton("XR2");
	    XR2.setBounds(50, 50, 50, 50);
	    XR2.setBackground(Color.WHITE);
	    XR2.addActionListener(e -> XRprocess());
	    panel2.add(XR2);
	    XR3 = new JButton("XR3");
	    XR3.setBounds(50, 50, 50, 50);
	    XR3.setBackground(Color.WHITE);
	    XR3.addActionListener(e -> XRprocess());
	    panel2.add(XR3);
	    
	    
	    
	  }

	  public static void main(String args[]){
	    new Simulator();
	  }
	  
	  public static void runAssembler()
	  {
		  System.out.println("Running ...");
		  //run assembler
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
	  
	  public static void GPRprocess()
	  {
		  System.out.println("GRPprocess");
	  }
	  
	  public static void XRprocess()
	  {
		  System.out.println("XRPprocess");
	  }
	  
	  
	}
