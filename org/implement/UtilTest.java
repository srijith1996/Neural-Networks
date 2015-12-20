package org.implement;

import org.util.Matrix;
import org.util.MatrixIncompatibilityException;
import org.util.MatrixIndexOutOfBoundsException;
import org.util.MatrixMath;
import org.util.NotBipolarDecimalException;
import org.util.NotVectorException;

public class UtilTest {

	public static void main(String[] args) throws NotBipolarDecimalException,
			MatrixIndexOutOfBoundsException, MatrixIncompatibilityException, NotVectorException {

		// boolean array2[] = { false, false, true, true, false, true };
		//
		// System.out
		// .println("Decimal of Boolean array [false, false, true, true, false, true]: "
		// + BiPolarUtil.bipolar2double(array2)[0]
		// + BiPolarUtil.bipolar2double(array2)[1]
		// + BiPolarUtil.bipolar2double(array2)[2]
		// + BiPolarUtil.bipolar2double(array2)[3]
		// + BiPolarUtil.bipolar2double(array2)[4]
		// + BiPolarUtil.bipolar2double(array2)[5]);
		//
		// double array[] = { -1, 1, 1, -1, -1, -1 };
		//
		// System.out.println("Boolean of Decimal array [-1, 1, 1, -1, -1, -1]: "
		// + BiPolarUtil.double2bipolar(array)[0]
		// + BiPolarUtil.double2bipolar(array)[1]
		// + BiPolarUtil.double2bipolar(array)[2]
		// + BiPolarUtil.double2bipolar(array)[3]
		// + BiPolarUtil.double2bipolar(array)[4]
		// + BiPolarUtil.double2bipolar(array)[5]);

		Matrix mat = Matrix.randMatrix(3, 2);
		// Matrix.print(mat);
		//
		// mat.add(1, 2, 2.5);
		// Matrix.print(mat);
		//
		// mat.set(2, 1, 4.5);
		// Matrix.print(mat);
		//
		// Matrix secrow = mat.getRow(2);
		// Matrix.print(secrow);
		//
		// System.out.println(mat.equals(secrow));
		//
		// System.out.println(mat.getElement(3, 2));
		//
		// System.out.println(mat.getCols());
		// System.out.println(mat.getRows());
		//
		// System.out.println(mat.isVector());
		// System.out.println(secrow.isVector());
		// System.out.println(mat.getCol(1).isVector());
		// System.out.println(mat.isZero());
		//
		// System.out.println(mat.sum());
		//
		// double array[] = mat.toPackedArray();
		// System.out.println("\nPacked Array: ");
		//
		// for(int i = 0; i < array.length; i++)
		// System.out.print(array[i] + "\t");
		//
		 Matrix m = mat.clone();
		// Matrix.print(m);
		//
		// mat.clear();
		// Matrix.print(mat);
		
		Matrix mat2 = Matrix.randMatrix(2, 3);
		
		Matrix.print(mat2);
		Matrix.print(mat);
		
		Matrix sum = MatrixMath.add(mat, m);
		Matrix.print(sum);
		Matrix.print(MatrixMath.multiply(mat2, 3.5));
		
		Matrix mult = MatrixMath.multiply(mat, mat2);
		Matrix.print(mult);
		
		// Matrix.print(MatrixMath.multiply(mat, mat.getCol(1)));
		
		Matrix.print(MatrixMath.subtract(mat2, mat2));
		
		Matrix.print(MatrixMath.divide(mat2, 4));
		
		Matrix.print(MatrixMath.identity(5));
		
		Matrix.print(MatrixMath.transpose(mat2));
		
		System.out.println(MatrixMath.dotProduct(mat2.getCol(2), mat2.getCol(1)));
		System.out.println(MatrixMath.vectorLength(mat2.getRow(1)));
	}

}
