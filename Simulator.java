import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Simulator extends Frame {
	
	public static JButton run, STEP, HLT, load, store;
	
	  public Simulator() {
		  
		  JFrame frame = new JFrame("CSCI6461 Front Panel");
	      JPanel panel = new JPanel();
	      GridBagLayout layout = new GridBagLayout();
	      panel.setLayout(layout);
	      GridBagConstraints gbc = new GridBagConstraints();
		
		  
	    //add JButtons to the buttom
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
	    
	      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      frame.add(panel);
	      frame.setSize(800, 800);
	      frame.setVisible(true);
	      
	      
	    
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
	  
	  
	}
