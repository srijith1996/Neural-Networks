package org.implement;

import org.neural.HopfieldNetwork;
import org.neural.HopfieldNetworkException;
import org.util.Matrix;

public class ConsoleHopfield {

	/**
	 * convert a boolean array to the form [T, T, F, F]
	 * 
	 * @param b
	 *            A boolean array
	 * 
	 * @return the boolean array in string form
	 */
	public static String formatBoolean(final boolean[] b) {

		final StringBuilder result = new StringBuilder();
		result.append('[');

		for (int i = 0; i < b.length; i++) {
			if (b[i])
				result.append("T,");
			else
				result.append("F,");
		}

		result.append(']');

		return result.toString();

	}

	public static void main(String[] args) throws HopfieldNetworkException {

		final HopfieldNetwork network = new HopfieldNetwork(4);
		// pattern will be trained
		final boolean pattern1[] = { true, true, false, false };
		// pattern will be presented
		final boolean pattern2[] = { true, false, false, false };
		boolean result[];

		// train the network with pattern 1
		System.out.println("Training the system with pattern: "
				+ formatBoolean(pattern1));
		network.train(pattern1);

		// weight matrix of network
		Matrix.print(network.getWeight());

		// present pattern1 and see if it is recognized
		System.out.print("Input of pattern, " + formatBoolean(pattern1)
				+ ", to the network gives result: ");
		result = network.present(pattern1);
		System.out.println(formatBoolean(result));

		// present pattern2 and check if it is recognized
		System.out.print("Input of pattern, " + formatBoolean(pattern2)
				+ ", to the network gives result: ");
		result = network.present(pattern2);
		System.out.println(formatBoolean(result));

	}

}
