package data_sturctures.Matrix;

import java.util.ArrayList;

public class IntMatrix extends MatrixMan<Integer>{
	public IntMatrix(int row, int col) {
		super(row, col);
		for(int i = 0; i < row; i++) {
			arr.add(new ArrayList<>());
			for(int j = 0; j < col; j++) {
				arr.get(i).add(0);
			}
		}
	}
	public IntMatrix add(MatrixMan<Integer> b) {
		if (b == null) return null;

		if (this.row != b.row || this.col != b.col) {
			throw new IllegalArgumentException("The two arrays don't have equal size!!");
		}

		IntMatrix temp = new IntMatrix(this.row, this.col);

		for (int i = 0; i < this.row; i++) {
			// Ensure the temp row exists before setting values
			for (int j = 0; j < this.col; j++) {
				int valA = this.arr.get(i).get(j);
				int valB = b.arr.get(i).get(j);
				temp.updateElement(i,j,valA+valB);
			}
		}
		return temp;
	}

	public IntMatrix subtract(MatrixMan<Integer> b) {
		if (b == null) return null;

		if (this.row != b.row || this.col != b.col) {
			throw new IllegalArgumentException("The two arrays don't have equal size!!");
		}

		IntMatrix temp = new IntMatrix(this.row, this.col);

		for (int i = 0; i < this.row; i++) {
			// Ensure the temp row exists before setting values
			for (int j = 0; j < this.col; j++) {
				int valA = this.arr.get(i).get(j);
				int valB = b.arr.get(i).get(j);
				temp.updateElement(i,j,valA-valB);
			}
		}
		return temp;
	}

	public DoubleMatrix multipleInPlace(MatrixMan<Integer> b) {
		if (b == null) return null;

		if (this.row != b.row || this.col != b.col) {
			throw new IllegalArgumentException("The two arrays don't have equal size!!");
		}

		DoubleMatrix temp = new DoubleMatrix(this.row, this.col);

		for (int i = 0; i < this.row; i++) {
			// Ensure the temp row exists before setting values
			for (int j = 0; j < this.col; j++) {
				int valA = this.arr.get(i).get(j);
				int valB = b.arr.get(i).get(j);
				temp.updateElement(i,j,(double)valA*valB);
			}
		}
		return temp;
	}

}
