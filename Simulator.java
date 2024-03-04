package Swing;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Simulator extends Frame {
	//Initialized public variables
	public static JButton run, STEP, HLT, load, store, octInput, IPL;
	public static JButton GR0,GR1,GR2,GR3,XR1,XR2,XR3,PCbutton,MAR,MBR;
	public static JTextField GRegister0, GRegister1, GRegister2, GRegister3;
	public static JTextField XRegister1, XRegister2, XRegister3;
	public static JTextField MARinput, MBRinput, PCinput;
	public static FileReader output;
	public static int PC;
	public static Compution compute;
	public File selected;
	public BufferedReader buffer;
	public boolean halt = false;
	public Timer timer;
	public JFrame frame;
	public JPanel panel;
	public JPanel panelr0;
	public JPanel panelr1;
	public JPanel panelr2;
	public JPanel panelr3;
	public JPanel panel_pc;
	public JPanel panel_mar;
	public JPanel panel_mbr;
	 
	  //Constructor
	  public Simulator(){
		  ActionListener actionListener;
		  
		  //JFrame and JPanel set up
		  frame = new JFrame("CSCI6461 Front Panel");
		  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      frame.setSize(800, 800);
	      frame.setVisible(true);
	      frame.setBackground(Color.lightGray);
	      panel = new JPanel();
	      panel.setSize(800, 100);
	      panel.setBackground(Color.cyan);
	      frame.add(panel, BorderLayout.SOUTH);
	      //JPanel panel2 = new JPanel();
	      //panel2.setSize(800, 600);
	      //panel2.setBackground(Color.RED);
	      //frame.add(panel2, BorderLayout.CENTER);
		
		  
	    //Adding Run button to the JPanel
	    run = new JButton("Run");
	    run.setBounds(50, 50, 50, 50);
	    run.setBackground(Color.WHITE);
	    panel.add(run);
	    
	    //Adding STEP button to the JPanel
	    STEP = new JButton("STEP");
	    STEP.setBounds(50, 50, 50, 50);
	    STEP.setBackground(Color.WHITE);
	    panel.add(STEP);
	    
	    //Adding HLT button to the JPanel 
	    HLT = new JButton("HLT");
	    HLT.setBounds(50, 50, 50, 50);
	    HLT.setBackground(Color.WHITE);
	    panel.add(HLT);
	    
	    //Adding load button to the JPanel
	    load = new JButton("Load");
	    load.setBounds(50, 50, 50, 50);
	    load.setBackground(Color.WHITE);
	    panel.add(load);
	    
	    //Adding store button to the JPanel
	    store = new JButton("Store");
	    store.setBounds(50, 50, 50, 50);
	    store.setBackground(Color.WHITE);
	    panel.add(store);
	    
	    //Adding Octal Input Button to the JPanel
	    octInput = new JButton("Enter");
	    JLabel octNum = new JLabel("Octal value");
	    JTextField input = new JTextField(8);
	    input.setPreferredSize(new Dimension(100, 20));
	    panel.add(octNum);
	    panel.add(input);
	    panel.add(octInput);
	    
	    //Adding the Binary input field into the JPanel
	    JLabel binIn = new JLabel("Binary");
	    JTextField binInput = new JTextField("00000000");
	    binInput.setPreferredSize(new Dimension(100, 20));
	    panel.add(binIn);
	    panel.add(binInput);
	    
	    //Adding the IPL Button for the JPanel
	    IPL = new JButton("IPL");
	    panel.add(IPL);
	      
	    //Adding Registers to the Panel
	    Box rBox = Box.createVerticalBox();
        panelr0 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelr0.setBackground(Color.lightGray);
        frame.add(panelr0, BorderLayout.LINE_START);
        
	    //Adding the General Purpose Registers Displays into the GPanel
	    //General Purpose Register 0
	    GRegister0 = new JTextField("000000000000");
	    GRegister0.setBackground(Color.WHITE);
	    GR0 = new JButton("GR0");
	    GR0.setBounds(50, 50, 50, 50);
	    GR0.setBackground(Color.WHITE);
	    panelr0.add(GRegister0);
	    panelr0.add(GR0);
	    rBox.add(panelr0);
	    
	    //General Purpose Register 1
	    panelr1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelr1.setBackground(Color.lightGray);
	    GRegister1 = new JTextField("000000000000");
	    GRegister1.setBackground(Color.WHITE);
	    GR1 = new JButton("GR1");
	    GR1.setBounds(50, 50, 50, 50);
	    GR1.setBackground(Color.WHITE);
	    panelr1.add(GRegister1);
	    panelr1.add(GR1);
	    rBox.add(panelr1);
	    
	    //General Purpose Register 2
	    panelr2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelr2.setBackground(Color.lightGray);
	    GRegister2 = new JTextField("000000000000");
	    GRegister2.setBackground(Color.WHITE);
	    GR2 = new JButton("GR2");
	    GR2.setBounds(50, 50, 50, 50);
	    GR2.setBackground(Color.WHITE);
	    panelr2.add(GRegister2);
	    panelr2.add(GR2);
	    rBox.add(panelr2);
	    
	    //General Purpose Register 3
	    panelr3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelr3.setBackground(Color.lightGray);
	    GRegister3 = new JTextField("00000000");
	    GRegister3.setBackground(Color.WHITE);
	    GR3 = new JButton("GR3");
	    GR3.setBounds(50, 50, 50, 50);
	    GR3.setBackground(Color.WHITE);
	    panelr3.add(GRegister3);
        panelr3.add(GR3);
        rBox.add(panelr3);
	    
	    //Adding the Index Registers Displays into the GPanel 
	    //Index Register 1
	    XRegister1 = new JTextField("00000000");
	    XRegister1.setBackground(Color.WHITE);
	    XR1 = new JButton("XR1");
	    XR1.setBounds(50, 50, 50, 50);
	    XR1.setBackground(Color.WHITE);
	    panelr1.add(XRegister1);
        panelr1.add(XR1);
	    
	    //Index Register 2
	    XRegister2 = new JTextField("00000000");
	    XRegister2.setBackground(Color.WHITE);
	    XR2 = new JButton("XR2");
	    XR2.setBounds(50, 50, 50, 50);
	    XR2.setBackground(Color.WHITE);
	    panelr2.add(XRegister2);
        panelr2.add(XR2);
	    
	    //Index Register 3
	    XRegister3 = new JTextField("00000000");
	    XRegister3.setBackground(Color.WHITE);
	    XR3 = new JButton("XR3");
	    XR3.setBounds(50, 50, 50, 50);
	    XR3.setBackground(Color.WHITE);
	    panelr3.add(XRegister3);
        panelr3.add(XR3);
	    
	    //Adding the PC Input Display
        Box pBox = Box.createVerticalBox();
        panel_pc = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel_pc.setBackground(Color.lightGray);
	    PCinput = new JTextField("00000000");
	    PCinput.setBackground(Color.WHITE);
	    PCbutton = new JButton("PC");
	    PCbutton.setBounds(50, 50, 50, 50);
	    PCbutton.setBackground(Color.WHITE);
	    panel_pc.add(PCinput);
        panel_pc.add(PCbutton);
        pBox.add(panel_pc);
	    
	    //Adding the MAR Input Display
        panel_mar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel_mar.setBackground(Color.lightGray);
	    MARinput = new JTextField("00000000");
	    MARinput.setBackground(Color.WHITE);
	    MAR = new JButton("MAR");
	    MAR.setBounds(50, 50, 50, 50);
	    MAR.setBackground(Color.WHITE);
	    panel_mar.add(MARinput);
        panel_mar.add(MAR);
        pBox.add(panel_mar);
	    
	    //Adding the MBR Input Display
        panel_mbr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel_mbr.setBackground(Color.lightGray);
	    MBRinput = new JTextField("0000000000000000");
	    MBRinput.setBackground(Color.WHITE);
	    MBR = new JButton("MBR");
	    MBR.setBounds(50, 50, 50, 50);
	    MBR.setBackground(Color.WHITE);
	    panel_mbr.add(MBRinput);
        panel_mbr.add(MBR);
        pBox.add(panel_mbr);
        
        Box baseBox = Box.createHorizontalBox();
        baseBox.setBackground(Color.lightGray);
        baseBox.add(rBox);
        baseBox.add(pBox);
        frame.add(baseBox, BorderLayout.NORTH);
	    
	    //Action Listener for every register(memory, general, index) and PC Buttons
	    actionListener = new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent ae) {
	           Object obj = ae.getSource();
	           String value = binInput.getText();
	           
	           if(obj == MBR) {
	        	   MBRinput.setText(value);
	           }
	           else if(obj == MAR) {
	        	   MARinput.setText(value);
	           }
	           else if(obj == PCbutton) {
	        	   PCinput.setText(value);
	           }
	           else if (obj == XR1) {
	        	   XRegister1.setText(value);
	           }
	           else if (obj == XR2) {
	        	   XRegister2.setText(value);
	           }
	           else if (obj == XR3) {
	        	   XRegister3.setText(value);
	           }
	           else if (obj == GR0) {
	        	   GRegister0.setText(value);
	           }
	           else if (obj == GR1) {
	        	   GRegister1.setText(value);
	           }
	           else if (obj == GR2) {
	        	   GRegister2.setText(value);
	           }
	           else if (obj == GR3) {
	        	   GRegister3.setText(value);
	           }
	           else if (obj == PCbutton) {
	        	   PCinput.setText(value);
	           } else {
	        	   System.out.println("Didn't Work");
	           }
	           
	        }
	    };
	    
	    //Adding the Action Listener to each register and PC button
	    GR0.addActionListener(actionListener);
	    GR1.addActionListener(actionListener);
	    GR2.addActionListener(actionListener);
	    GR3.addActionListener(actionListener);
	    XR1.addActionListener(actionListener);
	    XR2.addActionListener(actionListener);
	    XR3.addActionListener(actionListener);
	    PCbutton.addActionListener(actionListener);
	    MAR.addActionListener(actionListener);
	    MBR.addActionListener(actionListener);
	    
	    //Compute Object 
	    compute = new Compution(frame);
	    
	    //Adding Action Listener for the Enter Button
	    octInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//Adding the Octal Input into the Binary TextField
				String binText = input.getText();
				int octal = Integer.parseInt(binText);
				
				binText = Integer.toBinaryString(octal);
				
				binInput.setText(binText);
			}
	    	
	    });
	    
	    
	    //Adding Action Listener for the Store Button
	    store.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//Adding instruction address and the instruction 
				//itself into the MAR and MBR Text Field
				String mar = MARinput.getText();
				String mbr = MBRinput.getText();
				
				//Storing the address and instruction into Memory
				compute.store(mar, mbr);
				
				
			}
	    	
	    });
	    
	    //Adding Action Listener for the Load Button
	    load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//Adding content from the MAR input into MBR
				String mar = MARinput.getText();
				String mbr = MBRinput.getText();
				MBRinput.setText(compute.load(mar));
				
				
			}
	    	
	    });
	    
	    //Adding the Action Listener for IPL Button
	    IPL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					//Code for User Selected Input txt file
					JFileChooser fileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Instruction File", "txt");
					fileChooser.setFileFilter(filter);
					fileChooser.setCurrentDirectory(new File("."));
					
					int result = fileChooser.showOpenDialog(null);
					
					//Buffer Reader for the Input File
					if(result == JFileChooser.APPROVE_OPTION) {
						selected = fileChooser.getSelectedFile();
						buffer = new BufferedReader(new FileReader(selected));
					}
				}catch(Exception exception) {
					
				}
				
				String str = null;
				try {
					str = buffer.readLine();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Done");
					return;
				}
				
				//Performing the Step Action for the first line
				str = str.trim();
				String [] arr = str.split("\\s+");
				//int oct = Integer.parseInt(arr[0],8);
				//String bin = Integer.toBinaryString(oct);
				//step(arr[0],arr[1]);
				
				while(str != null) {
					arr = str.split("\\s+");
					//oct = Integer.parseInt(arr[0],8);
					//bin = Integer.toBinaryString(oct);
					compute.store(arr[0],arr[1]);
				}
				
			}
	    	
	    });
	    
	    //Adding the Action Listener for the HLT button
	    HLT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Boolean will cause the run function to stop
				halt = true;
				
			}
	    	
	    });
	    
	    //Adding the Action Listener for the Run Button
	    run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					run();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	    	
	    });
	    
	    //Adding the Action Listener for the Step button
	    STEP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = null;
				String pc = PCinput.getText();
				step(pc, compute.load(pc));
				/*
				try {
					str = buffer.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Done");
				}
				
				//If there is an unprocessed line, the step function
				//is called
				if(str != null) {
					str = str.trim();
					String [] arr = str.split("\\s+");
					step(arr[0],arr[1]);
				}
				
				while(str != null) {
					str = str.trim();
					String [] arr = str.split("\\s+");
					compute.store(arr[0],arr[1]);
					try {
						str = buffer.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				*/
				
				
				
			}
	    	
	    });
	    
	    
	    
	    
	    
	    
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
	  
	  public static void MARload(String input)
	  {
		  int in = Integer.parseInt(input);
		  System.out.println("MAR: " + in);
	  }
	  
	  public static void MBRload(String input)
	  {
		  int in = Integer.parseInt(input);
		  System.out.println("MBR: " + in);
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
	  
	  //Load instruction for General Purpose Register
	  public void ldr(String value) {
		  int val = Integer.parseInt(value,2);
		  int r = (val >> 8) & 3;
		  String result = compute.register(value);
		  
		  if (r == 0) {
			  GRegister0.setText(result);
		  } else if(r == 1) {
			  GRegister1.setText(result);
		  } else if(r == 2) {
			  GRegister2.setText(result);
		  } else if(r == 3) {
			  GRegister3.setText(result);
		  }
		  
	  }
	  
	 //Store Instruction for General Purpose Register
	  public void str(String value) {
		  int val = Integer.parseInt(value,2);
		  int r = (val >> 8) & 3;
		  String result = compute.register(value);
		  
		  if (r == 0) {
			  compute.store("gr0",result);
		  } else if(r == 1) {
			  compute.store("gr1", result);
		  } else if(r == 2) {
			  compute.store("gr2", result);
		  } else if(r == 3) {
			  compute.store("gr3", result);
		  }
		  
	  }
	  
	  //Load instruction for Index Register
	  public void ldx(String value) {
		  int val = Integer.parseInt(value,2);
		  int ix = (val >> 6) & 3;
		  String result = compute.register(value);
		  
		  if(ix == 1) {
			  XRegister1.setText(result);
		  } else if(ix == 2) {
			  XRegister2.setText(result);
		  } else if(ix == 3) {
			  XRegister3.setText(result);
		  }
		  
	  }
	  
	  //Store instruction for Index Register 
	  public void stx(String value) {
		  int val = Integer.parseInt(value,2);
		  int ix = (val >> 6) & 3;
		  String result = compute.register(value);
		  
		  if(ix == 1) {
			  compute.store("xr1", result);
		  } else if(ix == 2) {
			  compute.store("xr2", result);
		  } else if(ix == 3) {
			  compute.store("xr3",result);
		  }
		  
	  }
	  
	  //Load exact address to General Purpose Register
	  public void lda(String value) {
		  int val = Integer.parseInt(value,2);
		  int adr = val & 31;
		  int ix = (val >> 6) & 3;
		  String result = Integer.toBinaryString(adr);
		  
		  if(ix == 1) {
			  XRegister1.setText(result);
		  } else if(ix == 2) {
			  XRegister2.setText(result);
		  } else if(ix == 3) {
			  XRegister3.setText(result);
		  }
		  
	  }
	  
	  //Step Function for Input Files
	  public void step(String index, String value) 
	  {
		  int octInd = Integer.parseInt(index, 8);
		  int octVal = Integer.parseInt(value, 8);
		  index = Integer.toBinaryString(octInd);
		  value = Integer.toBinaryString(octVal);
		  
		  //Stores address and instruction into Memory
		  compute.store(index, value);
		  
		  //PCinput.setText(index);
		  MARinput.setText(index);
		  MBRinput.setText(value);
		  
		  //Running an instruction based on its opcode 
		  int val = Integer.parseInt(value,2);
		  int opcode = val >> 10;
		  String op = Integer.toString(opcode);
		  opcode = Integer.parseInt(op,8);
		  switch(opcode) {
		  	case 0:
		  		halt = true;
		  	case 1:
		  		ldr(value);
		  		break;
		  	case 2:
		  		str(value);
		  		break;
		  	case 3:
		  		lda(value);
		  		break;
		  	case 4:
		  		ldx(value);
		  		break;
		  	case 5:
		  		stx(value);
		  		break;
		  }
		  
			
	  }
	  
	  public void run() throws Exception {
		  String pc = PCinput.getText();
		  step(pc, compute.load(pc));
		  int pcNum = Integer.parseInt(pc,2);	
		  while(halt != true) {
			  pcNum += 1;
			  pcNum = Integer.parseInt(pc,2);
			  pc = Integer.toBinaryString(pcNum);
			  PCinput.setText(pc);
			  step(pc, compute.load(pc));
		  }
	  /*
	  try {
			str = buffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		while ((str != null) && (halt == false)) {
          	str = str.trim();
  			String [] arr = str.split("\\s+");
  			step(arr[0], arr[1]);
  			str = buffer.readLine();
  	        repaint();
  			//Thread.sleep(1000);
  			
  		}
		*/
		
	  }
	  
	  
	}
