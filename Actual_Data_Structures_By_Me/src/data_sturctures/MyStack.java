package data_sturctures;

import java.util.ArrayList;

public class MyStack<T>{
	ArrayList<T> arr;
	int top;
	public MyStack(){
		top = -1;
		arr = new ArrayList<>();
	}
	public boolean isEmpty(){
		return top == -1;
	}
	public int size(){
		return arr.size();
	}

}
