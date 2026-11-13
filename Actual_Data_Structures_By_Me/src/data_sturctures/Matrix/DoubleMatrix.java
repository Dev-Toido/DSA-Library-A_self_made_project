package data_sturctures.Matrix;

import java.util.ArrayList;

public class DoubleMatrix extends MatrixMan<Double> {

	public DoubleMatrix(int row, int col) {
		super(row, col);
		for(int i = 0; i < row; i++) {
			arr.add(new ArrayList<>());
			for(int j = 0; j < col; j++) {
				arr.get(i).add(0.0);
			}
		}
	}


}
