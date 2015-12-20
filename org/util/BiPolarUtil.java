package org.util;

/**
 * Bipolar Utility: This class makes conversions from bipolar to decimal and
 * vice versa easier to perform. This class is used quite often in the
 * construction of Neural Networks, in particular Hopfield Networks.
 * <p>
 * <br/>
 * <strong>Bipolar Representation</strong>: -1 for false, and, 1 for true
 * </p>
 * <br/>
 * <p>
 * <strong>Decimal Representation</strong>: 0 for false, and, 1 for true
 * </p>
 * <br/>
 * <p>
 * Bipolar representation of boolean values is useful mathematically, since -1
 * is a mathematical inverse of 1 and makes its analogy with boolean values
 * stronger mathematically, although the traditional method of representing
 * booleans is 0 and 1. This class abstract the conversion of one representation
 * to another so that the programmer can focus on the problem at hand.
 * </p>
 * <br/>
 * 
 * @author Srijith Nair
 */
public class BiPolarUtil {

	/**
	 * Compute the decimal equivalent of a boolean b <br/>
	 * 
	 * @param b
	 *            boolean value to be converted to decimal <br/>
	 * 
	 * @return -1 if b is false, 1 otherwise
	 */
	public static double bipolar2double(final boolean b) {

		double dec = -1;

		if (b)
			dec = 1;

		return dec;

	}

	/**
	 * Compute the double equivalent for 1-Dimensional array of booleans <br/>
	 * 
	 * @param b
	 *            array of boolean values <br/>
	 * 
	 * @return array of equivalent bipolar(-1/1) values
	 */
	public static double[] bipolar2double(final boolean b[]) {

		double dec[] = new double[b.length];

		for (int i = 0; i < b.length; i++)
			dec[i] = BiPolarUtil.bipolar2double(b[i]);

		return dec;

	}

	/**
	 * Compute the double equivalent for 2-Dimensional array of booleans <br/>
	 * 
	 * @param b
	 *            2-Dimensional array of booleans <br/>
	 * 
	 * @return 2-Dimensional array of equivalent bipolar(-1/1) values
	 */
	public static double[][] bipolar2double(final boolean b[][]) {

		double dec[][] = new double[b.length][b[0].length];

		for (int i = 0; i < b.length; i++)
			for (int j = 0; j < b[0].length; j++)
				dec[i][j] = BiPolarUtil.bipolar2double(b[i][j]);

		return dec;

	}

	/**
	 * Method to convert from double to bipolar boolean <br/>
	 * 
	 * @param d
	 *            double value (either -1 or 1) for which equivalent boolean is
	 *            desired <br/>
	 * 
	 * @return equivalent boolean for d <br/>
	 * 
	 * @throws NotBipolarDecimalException
	 *             If d is neither 1 nor -1
	 */
	public static boolean double2bipolar(final double d)
			throws NotBipolarDecimalException {

		if (d != -1.0 && d != 1.0)
			throw new NotBipolarDecimalException();

		boolean bip = false;

		if (d == 1)
			bip = true;

		return bip;

	}

	/**
	 * Compute the boolean equivalent for 1d array of doubles <br/>
	 * 
	 * @param d
	 *            1-Dimensional array of doubles <br/>
	 * 
	 * @return boolean 1-dimensional array corresponding to d <br/>
	 * 
	 * @throws NotBipolarDecimalException
	 *             If even a single value in d is anything other than -1 or 1
	 */
	public static boolean[] double2bipolar(final double d[])
			throws NotBipolarDecimalException {

		boolean bip[] = new boolean[d.length];

		for (int i = 0; i < d.length; i++)
			bip[i] = BiPolarUtil.double2bipolar(d[i]);

		return bip;

	}

	/**
	 * Compute the boolean equivalent for 2d array of doubles <br/>
	 * 
	 * @param d
	 *            2-dimensional array of bipolar double values <br/>
	 * 
	 * @return boolean equivalent of each element of d <br/>
	 * 
	 * @throws NotBipolarDecimalException
	 *             If any element in d is anything other than -1 or 1
	 */
	public static boolean[][] double2bipolar(final double d[][])
			throws NotBipolarDecimalException {

		boolean bip[][] = new boolean[d.length][d[0].length];

		for (int i = 0; i < d.length; i++)
			for (int j = 0; j < d[0].length; j++)
				bip[i][j] = BiPolarUtil.double2bipolar(d[i][j]);

		return bip;

	}

}
