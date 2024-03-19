package Swing;

import javax.swing.*;

public class Instructions {
	Compution compute = new Compution();
	Simulator simulate = new Simulator();
	//Load instruction for General Purpose Register
	  public void ldr(String value, JTextField GR0, JTextField GR1, JTextField GR2, JTextField GR3) {
		  int val = Integer.parseInt(value,2);
		  int r = (val >> 8) & 3;
		  String result = compute.register(value);
		  
		  if (r == 0) {
			  GR0.setText(result);
		  } else if(r == 1) {
			  GR1.setText(result);
		  } else if(r == 2) {
			  GR2.setText(result);
		  } else if(r == 3) {
			  GR3.setText(result);
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
	  public void ldx(String value, JTextField XR1, JTextField XR2, JTextField XR3) {
		  int val = Integer.parseInt(value,2);
		  int ix = (val >> 6) & 3;
		  String result = compute.register(value);
		  
		  if(ix == 1) {
			  XR1.setText(result);
		  } else if(ix == 2) {
			  XR2.setText(result);
		  } else if(ix == 3) {
			  XR3.setText(result);
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
	  
	  //Load exact address to Index Register
	  public void lda(String value, JTextField XR1, JTextField XR2, JTextField XR3) {
		  int val = Integer.parseInt(value,2);
		  int adr = val & 31;
		  int ix = (val >> 6) & 3;
		  String result = Integer.toBinaryString(adr);
		  
		  if(ix == 1) {
			  XR1.setText(result);
		  } else if(ix == 2) {
			  XR2.setText(result);
		  } else if(ix == 3) {
			  XR3.setText(result);
		  }
		  
	  }
	  
	  public void jz(String value, JTextField PCinput) {
		  String result = compute.register(value);
		  String cc = compute.load("cc");
		  int cond = Integer.parseInt(cc, 2);
		  cond = cond & 1;
		  
		  if(cond == 0) {
			  String pc = PCinput.getText();
			  int pcBin = Integer.parseInt(pc, 2);
			  pcBin += 1;
			  pc = Integer.toBinaryString(pcBin);
			  simulate.step(pc, compute.load(pc));
			  
		  } else if(cond == 1) {
			  simulate.step(result, compute.load(result));
		  }
	  }
	  
	  public void jne(String value, JTextField PCinput) {
		  String result = compute.register(value);
		  String cc = compute.load("cc");
		  int cond = Integer.parseInt(cc, 2);
		  cond = cond & 1;
		  
		  if(cond == 1) {
			  String pc = PCinput.getText();
			  int pcBin = Integer.parseInt(pc, 2);
			  pcBin += 1;
			  pc = Integer.toBinaryString(pcBin);
			  simulate.step(pc, compute.load(pc));
			  
		  } else if(cond == 1) {
			  simulate.step(result, compute.load(result));
		  }
	  }
	  
	  public void jcc(String value, JTextField PCinput) {
		  String result = compute.register(value);
		  
		  int val = Integer.parseInt(value,2);
		  int r = (val >> 8) & 3;
		  
		  String cc = compute.load("cc");
		  int ccBin = Integer.parseInt(cc,2);
		  int[]ccBit = {ccBin & 8, ccBin & 4, ccBin & 2, ccBin & 1};
		  
		  if(ccBit[r] != 0) {
			  simulate.step(result, compute.load(result));
		  } else {
			  String pc = PCinput.getText();
			  int pcBin = Integer.parseInt(pc, 2);
			  pcBin += 1;
			  pc = Integer.toBinaryString(pcBin);
			  simulate.step(pc, compute.load(pc));
		  }
	  }
	  
	  public void jma(String value) {
		  String result = compute.register(value);
		  simulate.step(result, compute.load(result));
	  }
	  //Set CCE
	  public void setcee(String value) {
		  int val = Integer.parseInt(value,2);
		  int r = (val >> 8) & 3;
		  //General Register
		  String[] gr = {"gr0","gr1", "gr2", "gr3"};
		  
		  String regBin = compute.memory().get(gr[r]);
		  //Convert String binary into Decimal
		  int regDec = Integer.parseInt(regBin, 2);
		  
		  String cc = compute.load("cc");
		  int cond = Integer.parseInt(cc, 2);
		  //Setting CCE
		  if(regDec == 0) {
			  int result = cond | 1;
			  cc = Integer.toBinaryString(result);
			  compute.store("cc", cc);
		  }else {
			  int result = cond & 0;
			  cc = Integer.toBinaryString(result);
			  compute.store("cc", cc);
		  }
	  }
}
