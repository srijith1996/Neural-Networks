package org.util;

/**
 * This class is more like a utility class with methods that abstract the basic
 * arithmetic operations on matrices. All methods in this class are static as
 * they are not associated with any particular object. All major methods are on
 * general matrices, and some are on vectors. If the suitable parameters are not
 * provided, (for example, if a non vector is provided for dot product
 * calculation, or if the two matrices provided are not compatible to add or
 * multiply), various Exceptions are thrown. <br/>
 * 
 * @author Srijith Nair
 */

public class MatrixMath {

	/**
	 * Add two matrices. <br/>
	 * 
	 * @param a
	 *            first matrix to be added
	 * @param b
	 *            second matrix to be added (of the same order as a) <br/>
	 * 
	 * @return sum of two matrices if compatible <br/>
	 * 
	 * @throws MatrixIncompatibilityException
	 *             If the two matrices are not of the same order.
	 * 
	 */
	public static Matrix add(final Matrix a, final Matrix b)
			throws MatrixIncompatibilityException {

		// throw an exception if matrices are incompatible
		if ((a.getRows() != b.getRows()) || (a.getCols() != b.getCols()))
			throw new MatrixIncompatibilityException();

		double[][] res = new double[a.getRows()][a.getCols()];

		for (int i = 0; i < a.getRows(); i++)
			for (int j = 0; j < a.getCols(); j++)
				try {
					res[i][j] = a.getElement(i, j)
							+ b.getElement(i, j);
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
		return new Matrix(res);

	}

	/**
	 * Subtract the second matrix from the first <br/>
	 * 
	 * @param a
	 *            the matrix from which the other will be subtracted
	 * @param b
	 *            the matrix which subtracts the other <br/>
	 * 
	 * @return the resultant subtracted matrix <br/>
	 * 
	 * @throws MatrixIncompatibilityException
	 *             If matrix a and b are not of the same order
	 */
	public static Matrix subtract(final Matrix a, final Matrix b)
			throws MatrixIncompatibilityException {

		// throw an exception if matrices are incompatible
		if ((a.getRows() != b.getRows()) || (a.getCols() != b.getCols()))
			throw new MatrixIncompatibilityException();

		double[][] res = new double[a.getRows()][a.getCols()];

		for (int i = 0; i < a.getRows(); i++)
			for (int j = 0; j < a.getCols(); j++)
				try {
					res[i][j] = a.getElement(i, j)
							- b.getElement(i, j);
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
		return new Matrix(res);

	}

	/**
	 * Divide a matrix by a constant double value <br/>
	 * 
	 * @param mat
	 *            the matrix to be modified
	 * @param divisor
	 *            the value used for division<br/>
	 * 
	 * @return A matrix with each element equal to the corresponding element in
	 *         mat divided by the divisor.
	 */
	public static Matrix divide(final Matrix mat, final double divisor) {

		double[][] res = new double[mat.getRows()][mat.getCols()];

		for (int i = 0; i < mat.getRows(); i++)
			for (int j = 0; j < mat.getCols(); j++)
				try {
					res[i][j] = mat.getElement(i, j) / divisor;
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
		return new Matrix(res);

	}

	/**
	 * Compute the dot product of two vectors<br/>
	 * 
	 * @param vector1
	 *            the 1st vector in dot product
	 * @param vector2
	 *            the 2nd vector in dot product<br/>
	 * 
	 * @return the dot product of the two vectors<br/>
	 * 
	 * @throws MatrixIncompatibilityException
	 *             if the vectors do not possess the same number of dimensions
	 * @throws NotVectorException
	 *             if the matrices passed as arguments are not vectors.
	 */
	public static double dotProduct(final Matrix vector1, final Matrix vector2)
			throws MatrixIncompatibilityException, NotVectorException {

		// ensure that given matrices are indeed vectors
		if (!(vector1.isVector() && vector2.isVector()))
			throw new NotVectorException();

		double[] vecArray1 = vector1.toPackedArray();
		double[] vecArray2 = vector2.toPackedArray();

		// ensure that their lengths match
		if (vecArray1.length != vecArray2.length)
			throw new MatrixIncompatibilityException();

		double result = 0;

		for (int i = 0; i < vecArray1.length; i++)
			result += vecArray1[i] * vecArray2[i];

		return result;

	}

	/**
	 * Create an identity matrix of a given size.<br/>
	 * 
	 * @param size
	 *            the edge length of the given identity matrix<br/>
	 * 
	 * @return An object of type Matrix, which is an abstraction of an identity
	 *         matrix of the given size.
	 */
	public static Matrix identity(final int size) {

		final Matrix id = new Matrix(size, size);

		for (int i = 0; i < size; i++)
			try {
				id.set(i, i, 1);
			} catch (MatrixIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		return id;

	}

	/**
	 * Multiply the given matrix with a scalar<br/>
	 * 
	 * @param mat
	 *            the matrix on which the multiplication is to be carried out
	 * @param multiplier
	 *            the scalar multiplied to the matrix <br/>
	 * 
	 * @return A matrix with each element as the corresponding element in mat
	 *         multiplied with the multiplier
	 */
	public static Matrix multiply(final Matrix mat, final double multiplier) {

		double[][] res = new double[mat.getRows()][mat.getCols()];

		for (int i = 0; i < mat.getRows(); i++)
			for (int j = 0; j < mat.getCols(); j++)
				try {
					res[i][j] = mat.getElement(i, j) * multiplier;
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}

		return new Matrix(res);

	}

	/**
	 * Multiply the two matrices if compatible<br/>
	 * 
	 * @param a
	 *            pre-multiplication matrix
	 * @param b
	 *            post-multiplication matrix<br/>
	 * 
	 * @return product matrix of a*b<br/>
	 * 
	 * @throws MatrixIncompatibilityException
	 *             if number of columns in matrix a is unequal to the number of
	 *             rows in matrix b.
	 */
	public static Matrix multiply(final Matrix a, final Matrix b)
			throws MatrixIncompatibilityException {

		if (a.getCols() != b.getRows())
			throw new MatrixIncompatibilityException();

		double[][] res = new double[a.getRows()][b.getCols()];

		for (int i = 0; i < a.getRows(); i++)
			for (int j = 0; j < b.getCols(); j++)
				try {
					res[i][j] = MatrixMath.dotProduct(a.getRow(i),
							b.getCol(j));
				} catch (NotVectorException | MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}

		return new Matrix(res);

	}

	/**
	 * Return the transpose of a given matrix<br/>
	 * 
	 * @param a
	 *            matrix whose transpose is desired<br/>
	 * 
	 * @return transpose of the matrix a
	 */
	public static Matrix transpose(final Matrix a) {

		double res[][] = new double[a.getCols()][a.getRows()];

		for (int i = 0; i < a.getRows(); i++)
			for (int j = 0; j < a.getCols(); j++)
				try {
					res[j][i] = a.getElement(i, j);
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}

		return new Matrix(res);

	}

	/**
	 * Find the length (magnitude) of a given vector </br>
	 * 
	 * @param a
	 *            object of Matrix which is a vector.<br/>
	 * 
	 * @return magnitude of the vector, i.e., sqrt(a1^2 + a2^2 + ... + an^2)<br/>
	 * 
	 * @throws NotVectorException
	 *             if the matrix a is not a vector
	 */
	public static double vectorLength(final Matrix a) throws NotVectorException {

		if (!a.isVector())
			throw new NotVectorException();

		double length = 0;
		double array[] = a.toPackedArray();

		for (int i = 0; i < array.length; i++)
			length += array[i] * array[i];

		length = Math.sqrt(length);

		return length;

	}

}
