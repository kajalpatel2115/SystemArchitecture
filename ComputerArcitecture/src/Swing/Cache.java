package Swing;

import java.util.Stack;

public class Cache {
	String tag;
	String[] blockNum = {"0","0","0","0"};
	
	
	public Cache(String tag) {
		this.tag = tag;
	}
	
	public String  getTag() {
		return tag;
	}
	
	public String[] getBlockNum() {
		return blockNum;
	}
	
	public void setBlockNum(String [] blockNum) {
		this.blockNum =  blockNum;
	}
}
