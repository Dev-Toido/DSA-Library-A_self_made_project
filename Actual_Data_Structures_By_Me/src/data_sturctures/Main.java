package data_sturctures;

import data_sturctures.Matrix.DoubleMatrix;
import data_sturctures.Matrix.IntMatrix;

public class Main {
	static void main() {
		IntMatrix a = new IntMatrix(3, 3);
		IntMatrix b = new IntMatrix(3, 3);

		a.setAllElement(5);
		b.setAllElement(2);

		a.updateElement(2,2,10);
		b.updateElement(2,2,100);

		a.displayMatrix();
		b.displayMatrix();

		IntMatrix c = a.add(b) ;
		IntMatrix d = a.subtract(b) ;
		DoubleMatrix e = a.multipleInPlace(b);

		c.displayMatrix();
		d.displayMatrix();
		e.displayMatrix();
	}
}
