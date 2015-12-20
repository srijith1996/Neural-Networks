package org.util;

/**
 * NotBipolarDecimalException occurs when a non bipolar decimal, i.e., neither a
 * -1 or 1, is computed on for its boolean equivalent <br/>
 * <p>
 * The use of -1 and 1 is purely an arbitrary choice. Any two mathematically
 * 'opposite' numbers would do just fine. But one convention has to be followed,
 * once decided upon.
 * </p>
 * <br/>
 * 
 * @author Srijith Nair
 *
 */
public class NotBipolarDecimalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * This Exception will be thrown if the decimal parameter turns out to be a
	 * non bipolar one. Here, neither a -1.0 or a +1.0
	 */

}
