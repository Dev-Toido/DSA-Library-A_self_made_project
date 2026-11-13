package data_sturctures.Matrix;

import java.util.ArrayList;

public class MatrixMan<T> {
	protected final int row;
	protected final int col;
	protected final ArrayList<ArrayList<T>> arr;
	public MatrixMan(int row, int col) {
		this.row = row;
		this.col = col;
		arr = new ArrayList<>();
		for(int i = 0; i < row; i++) {
			arr.add(new ArrayList<>());
			for(int j = 0; j < col; j++) {
			arr.get(i).add(null);}
		}

	}
	public MatrixMan(MatrixMan<T> a) {
		this.row = a.row;
		this.col = a.col;
		this.arr = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			// Create a new independent row list and copy elements over
			this.arr.add(new ArrayList<>(a.arr.get(i)));
		}
	}
	int getRow() {return row;}
	int getCol() {return col;}
	public void updateElement(int row, int col, T value){
		this.arr.get(row).set(col, value);
	}
	public void setAllElement(T ele){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				this.arr.get(i).set(j, ele);
			}
		}
	}

	public void displayMatrix(){
		ArrayList<String> display = new ArrayList<>();
		String str;
		int maxEleSize=0;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(maxEleSize<(this.arr.get(i).get(j)+"").trim().length()){
					maxEleSize=(this.arr.get(i).get(j)+"").trim().length();
				}
			}
		}

		for(int i=0;i<row;i++){
			str="";
			for(int j=0;j<col;j++){
				for(int k=0;k<maxEleSize-(this.arr.get(i).get(j)+"").length();k++){str+=" ";}
				str = str.concat(this.arr.get(i).get(j)+" ");
			}
			display.add(str.stripTrailing());
		}

		display.addFirst("+-"+(" ".repeat(display.getFirst().length()-2))+" -+");
		display.add( "+-"+(" ".repeat(display.getLast().length()-2))+" -+");

		for(int i=0;i< display.size();i++){
			if(i==0 || i==display.size()-1){
				System.out.println(display.get(i));
			}
			else{
				System.out.println("|"+display.get(i)+" |");
			}
		}
	}




}
