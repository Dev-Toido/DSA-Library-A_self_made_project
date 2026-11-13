package data_sturctures;

import java.util.Scanner;

public class Input {
	Scanner sc=new Scanner(System.in);
	public String inputLine(){
		return sc.nextLine().trim();
	}
	public String input(){
		return sc.next().trim();
	}
	public char inputChar(){
		return input().charAt(0);
	}
	public int inputInt(){
		return Integer.parseInt(input());
	}
	public long inputLong(){
		return Long.parseLong(input());
	}
	public float inputFloat(){
		return Float.parseFloat(input());
	}
	public double inputDouble(){
		return Double.parseDouble(input());
	}
}
