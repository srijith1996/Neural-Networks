package org.util;

/**
 * MatrixIncompatibilityException is thrown when any matrix operation occurs
 * between two mutually incompatible matrices. <br/>
 * 
 * <p>
 * e.g., when a (3,2) matrix adds to a (5,4) matrix, or a (5,6) matrix
 * multiplies with a (1,4) matrix
 * </p>
 * <br/>
 * 
 * @author Srijith Nair
 *
 */
public class MatrixIncompatibilityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Exception that occurs when matrices being added or multiplied are not
	 * compatible
	 */

}
