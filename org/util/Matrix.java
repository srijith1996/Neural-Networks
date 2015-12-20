package org.util;

/**
 * Matrix:
 * <p>
 * A user-defined abstraction of a matrix. Matrix manipulations are important
 * throughout the study of neural networks This class abstracts a matrix as a
 * user-defined datatype.
 * </p>
 * <p>
 * This class is used to create objects which can be visualized and manipulated
 * similar to, in general, 2-Dimensional matrices
 * </p>
 * <br/>
 * 
 * @author Srijith Nair
 * 
 */

public class Matrix {

	// A 2D array holds the matrix data
	private double matrix[][];

	// dimension values of matrix
	private int rows, cols;

	/**
	 * Constructor: Creates matrix of given row and column lengths and
	 * initializes all elements to zero <br/>
	 * 
	 * @param rows
	 *            the number of rows desired in the matrix
	 * @param cols
	 *            the number of columns
	 * 
	 */
	public Matrix(final int rows, final int cols) {

		// create new array object
		matrix = new double[rows][cols];

		// set dimensions
		this.rows = rows;
		this.cols = cols;

		// initialize all values to zero
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				matrix[i][j] = 0;

	}

	/**
	 * Constructor: Defines a matrix object using a 2-Dimensional array <br/>
	 * 
	 * @param matData
	 *            the two dimensional array out of which an object of type
	 *            Matrix is desired
	 */
	public Matrix(final double[][] matData) {

		matrix = matData;

		// set dimensions
		rows = matData.length;
		cols = matData[0].length;

	}

	/**
	 * Method creates a column matrix or vector uses a 1D array as input to
	 * create the matrix <br/>
	 * 
	 * @param input
	 *            the array form which a column matrix will be created <br/>
	 * 
	 * @return desired column matrix (object of type Matrix)
	 * 
	 */
	public static Matrix createColumnMatrix(final double input[]) {

		double mat[][] = new double[input.length][1];

		for (int i = 0; i < input.length; i++)
			mat[i][0] = input[i];

		return new Matrix(mat);

	}

	/**
	 * Method creates a row matrix or vector uses a 1D array as input to create
	 * the matrix <br/>
	 * 
	 * @param input
	 *            the input array which is to be converted to a row array <br/>
	 * 
	 * @return desired row matrix
	 */
	public static Matrix createRowMatrix(final double input[]) {

		double mat[][] = new double[1][input.length];

		// convert array into a two dimensional array
		for (int i = 0; i < input.length; i++)
			mat[0][i] = input[i];

		return new Matrix(mat);

	}

	/**
	 * Method creates a random rows x cols matrix with all values between
	 * negative-one and one <br/>
	 * 
	 * @param rows
	 *            the number of rows desired in the random matrix
	 * @param cols
	 *            the number of columns desired in the random matrix <br/>
	 * 
	 * @return A random matrix with elements between <strong>-1</strong> and
	 *         <strong>1</strong>
	 */
	public static Matrix randMatrix(final int rows, final int cols) {

		double mat[][] = new double[rows][cols];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				mat[i][j] = 2 * Math.random() - 1;

		return new Matrix(mat);

	}

	/**
	 * Method to add an element to the row-th row and col-th column in this
	 * matrix indexing from 1 <br/>
	 * 
	 * @param row
	 *            the row number of the element to be added to (index from 1)
	 * @param col
	 *            the column number of the element to be added to (index from 1)
	 * @param value
	 *            the value to be added to the desired element
	 * 
	 * <br/>
	 */
	public void add(final int row, final int col, final double value) {

		matrix[row - 1][col - 1] += value;

	}

	/**
	 * Method to re-initialize the matrix with all elements set to zero <br/>
	 * 
	 */
	public void clear() {

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				matrix[i][j] = 0;

	}

	/**
	 * Method to clone the present object <br/>
	 * 
	 * @return clone of the present matrix
	 */
	public Matrix clone() {

		return new Matrix(matrix);

	}

	/**
	 * Check if calling matrix is equal to the parameter matrix <br/>
	 * 
	 * @param matrix2
	 *            the matrix to which this matrix is compared <br/>
	 * @return the value true if both matrices are identical and false otherwise <br/>
	 */
	public boolean equals(final Matrix matrix2) {

		boolean flag = true;

		// if there is a mismatch in dimensions return false
		if (!(this.rows == matrix2.getRows() && this.cols == matrix2.getCols()))
			return false;

		// check each element for equality
		outer: for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				try {
					if (matrix2.getElement(i + 1, j + 1) != matrix[i][j]) {
						flag = false;
						break outer;
					}
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}

		return flag;

	}

	/**
	 * Method to get the element in the row-th row and col-th column in the
	 * matrix indexing from 1 <br/>
	 * 
	 * @param row
	 *            the row number of the desired element (indexed from 0)
	 * @param col
	 *            the column number of the desired element (indexed from 0) <br/>
	 * 
	 * @return the element at the desired location in this matrix <br/>
	 * 
	 * @throws MatrixIndexOutOfBoundsException
	 *             If the given column and row values are not in the matrix's
	 *             bounds
	 */
	public double getElement(final int row, final int col)
			throws MatrixIndexOutOfBoundsException {

		if (row > this.rows - 1 || col > this.cols - 1)
			throw new MatrixIndexOutOfBoundsException();

		return matrix[row][col];

	}

	/**
	 * Get the total number of Rows in the matrix
	 * 
	 * @return number of rows in this matrix
	 */
	public int getRows() {

		return this.rows;

	}

	/**
	 * Get the total number of Columns in the matrix
	 * 
	 * @return number of columns in a matrix
	 */
	public int getCols() {

		return this.cols;

	}

	/**
	 * Get the specified column as a column Matrix
	 * 
	 * @param col
	 *            the desired column number of this matrix (indexed from 0) <br/>
	 * 
	 * @return A vector containing the elements of the specified column of this
	 *         matrix <br/>
	 * 
	 * @throws MatrixIndexOutOfBoundsException
	 *             If the column value specified is greater than the number of
	 *             columns in the matrix
	 */
	public Matrix getCol(final int col) throws MatrixIndexOutOfBoundsException {

		if (col > this.cols - 1)
			throw new MatrixIndexOutOfBoundsException();

		double[][] column = new double[this.rows][1];

		for (int i = 0; i < rows; i++)
			column[i][0] = matrix[i][col];

		return new Matrix(column);

	}

	/**
	 * Get the specified row as a row Matrix
	 * 
	 * @param row
	 *            the desired row number of this matrix (indexed from 0) <br/>
	 * 
	 * @return A vector containing the elements of the specified row of this
	 *         matrix <br/>
	 * 
	 * @throws MatrixIndexOutOfBoundsException
	 *             If the row value specified is greater than the number of rows
	 *             in the matrix
	 */
	public Matrix getRow(final int row) throws MatrixIndexOutOfBoundsException {

		if (row > this.rows - 1)
			throw new MatrixIndexOutOfBoundsException();

		double[][] rowmat = new double[1][cols];

		for (int i = 0; i < cols; i++)
			rowmat[0][i] = matrix[row][i];

		return new Matrix(rowmat);

	}

	/**
	 * Asserts whether this matrix is a vector or not <br/>
	 * 
	 * @return True if the calling matrix is a vector and false otherwise
	 */
	public boolean isVector() {

		if (rows == 1 || cols == 1)
			return true;
		else
			return false;

	}

	/**
	 * Checks whether the matrix is null or not <br/>
	 * 
	 * @return True if the matrix has all elements equal to zero and false
	 *         otherwise
	 */
	public boolean isZero() {

		boolean flag = true;

		// check each element
		outer: for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				if (matrix[i][j] != 0) {
					flag = false;
					break outer;
				}

		return flag;

	}

	/**
	 * Change the value of the element indexed by 'row' and 'col' to the one
	 * mentioned <br/>
	 * 
	 * @param row
	 *            the row number of the element to be set (indexed from 0)
	 * @param col
	 *            the column number of the element to be set (indexed from 0)
	 * @param value
	 *            the value to which the element has to be changed <br/>
	 * 
	 * @throws MatrixIndexOutOfBoundsException
	 *             If the matrix indices specified by 'row' and 'col' do not
	 *             exist
	 */
	public void set(final int row, final int col, final double value)
			throws MatrixIndexOutOfBoundsException {

		if (row > this.rows - 1 || col > this.cols - 1)
			throw new MatrixIndexOutOfBoundsException();

		matrix[row][col] = value;

	}

	/**
	 * Sum up the values of all the elements in the calling matrix <br/>
	 * 
	 * @return the final sum of all elements in the matrix
	 */
	public double sum() {

		double result = 0;

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				result += matrix[i][j];

		return result;

	}

	/**
	 * Convert the given matrix to a packed 1D array containing all the elements
	 * row after row <br/>
	 * 
	 * @return A 1-Dimensional array of all elements in the matrix row after row
	 */
	public double[] toPackedArray() {

		// create the resultant array to store the matrix data
		double resArray[] = new double[rows * cols];

		int index = 0;
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {

				resArray[index] = matrix[i][j];
				index++; // increment the index of resArray at each iteration

			}

		return resArray;

	}

	/**
	 * Utility function to display matrices to console <br/>
	 * 
	 * @param mat
	 *            the object of type Matrix to be printed to console </br>
	 * 
	 */
	public static void print(Matrix mat) {

		for (int i = 0; i < mat.getRows(); i++) {

			for (int j = 0; j < mat.getCols(); j++)
				try {
					System.out.printf("%10.4f", mat.getElement(i + 1, j + 1));
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			System.out.println();

		}

		System.out.println();

	}

}
