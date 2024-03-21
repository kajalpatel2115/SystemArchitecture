package simulator;

import javax.swing.JTextField;

public class Instructions {
	Registers registers;
	Memory memory;
	
	public Instructions() {
		registers = new Registers();
		memory = new Memory();
	}
	
	public int exactAddress(int value) {
		int r = (value >> 8) & 3;
		int ix = (value >> 6) & 3;
		int i = (value >> 5) & 1;
		int adr = value & 31;
		
		//will hold the correct register value 
		int currReg = registers.getRnByNum(ix);
		
		adr = adr + currReg;
		
		if(i == 1) {
			adr = memory.getMemory(adr);
		}
		
		return adr;
	}
	
	 public void ldr(int value) {
		  int r = (value >> 8) & 3;
		  int result = exactAddress(value);
		  
		  registers.setRnByNum(result,r);
		  	  
	  }
	 
	//Store Instruction for General Purpose Register
	  public void str(int value) {
		  int r = (value >> 8) & 3;
		  int result = exactAddress(value);
		  
		  memory.addMemory(result, registers.getRnByNum(r));
		  
	  }
	  
	  public void ldx(int value) {
		  int r = (value >> 6) & 3;
		  int result = exactAddress(value);
		  
		  registers.setXnByNum(result,r);
		  	  
	  }
	  
	//Store Instruction for General Purpose Register
	  public void stx(int value) {
		  int r = (value >> 6) & 3;
		  int result = exactAddress(value);
		  
		  memory.addMemory(result, registers.getXnByNum(r));
		  
	  }
	  
	  public void lda(int value) {
		  int r = (value >> 6) & 3;
		  int adr = value & 31;
		  
		  registers.setXnByNum(adr,r);
		  	  
	  }
	  
	  public void jz(int value) {
		  int result = exactAddress(value);
		  
		  if(registers.getCCElementByBit(0) == true) {
			  registers.increasePCByOne();
		  }else {
			  registers.setCC(result);
		  }
	  }
	 
	  public void jne(int value) {
		  int result = exactAddress(value);
		  
		  if(registers.getCCElementByBit(0) == true) {
			  registers.setCC(result);
		  }else {
			  registers.increasePCByOne();
		  }
	  }
	  
	  public void jcc(int value) {
		  int r = (value >> 8) & 3;
		  r = 3 - r;
		  int result = exactAddress(value);
		  
		  if(registers.getCCElementByBit(r) == true) {
			  registers.setPC(result);
		  } else {
			  registers.increasePCByOne();
		  }
	  }
	  public void jma(int value) {
		  int result = exactAddress(value);
		  registers.setPC(result);
	  }
	  public void setcce(int value) {
		  int r = (value >> 8) & 3;
		  int register = registers.getRnByNum(r);
		  int cc = registers.getCC();
		  
		  if(register == 0) {
			registers.setCC(cc | 1);  
		  }else {
			  registers.setCC(cc & 14);   
		  }
	  }
}
