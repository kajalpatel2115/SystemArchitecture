package Swing;

import java.util.Stack;

public class Cache {
	int tag;
	String data;
	Stack<Cache> cache = new Stack<Cache>();
	
	public Cache(int tag, String data) {
		this.tag = tag;
		this.data = data;
	}
	
	public int  getTag() {
		return tag;
	}
	
	public String getData() {
		return data;
	}
}
