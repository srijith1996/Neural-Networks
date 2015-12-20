package org.neural;

import org.util.*;

/**
 * HopfieldNetwork: this class implements a Hopfield neural network. A Hopfield
 * neural network consists of a fully connected single layer network of neurons.
 * They are usually used for pattern recognition. <br/>
 * 
 * @author Srijith Nair
 * @version 1.0<br/>
 *
 */
public class HopfieldNetwork {

	/**
	 * The weight matrix of the neural network. Cannot be directly accessed,
	 * since it is private. The weight matrix stores the values of weights of
	 * each connection in the network.
	 * 
	 * The inputs and outputs to the Hopfield network are always boolean values.
	 */
	private Matrix weightMatrix;

	public HopfieldNetwork(final int size) {
		weightMatrix = new Matrix(size, size);
	}

	/**
	 * Get the weight matrix of this neural network <br/>
	 * 
	 * @return weight matrix of this Hopfield network
	 */
	public Matrix getWeight() {
		return this.weightMatrix;
	}

	/**
	 * Get the size of this neural network <br/>
	 * 
	 * @return size of the neural network
	 */
	public int getSize() {

		// get the number of rows in weightMatrix
		return weightMatrix.getRows();
	}

	/**
	 * Present a boolean array pattern to the neural network and get back the
	 * resulting output from the network. <br/>
	 * 
	 * @param pattern
	 *            the boolean pattern presented to the network <br/>
	 * @return The output of the network (response to pattern) <br/>
	 * 
	 * @throws HopfieldNetworkException
	 *             if the pattern length is lesser than the number of neurons in
	 *             the network
	 */
	public boolean[] present(final boolean[] pattern)
			throws HopfieldNetworkException {

		if (pattern.length != this.weightMatrix.getRows())
			throw new HopfieldNetworkException();

		final boolean output[] = new boolean[pattern.length];

		// convert the array of input patterns into bipolar form
		// store it in a row matrix
		Matrix inputMatrix = Matrix.createRowMatrix(BiPolarUtil
				.bipolar2double(pattern));

		/*
		 * process the output at each individual neuron in the network the ouput
		 * of a neuron is equal to the dot product of the pattern with each
		 * column in the weight matrix of this neural network
		 */
		for (int col = 0; col < pattern.length; col++) {

			Matrix columnMatrix = null;

			// MatrixIndexOutOfBoundsException cannot occur if there is no
			// HopfieldNetworkException
			try {
				columnMatrix = this.weightMatrix.getCol(col);
			} catch (MatrixIndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			double dotProduct = 0;

			// NotVectorException cannot occur here
			// nor can MatrixIncompatibility occur if there is no
			// HopfieldNetworkException
			try {
				dotProduct = MatrixMath.dotProduct(inputMatrix, columnMatrix);
			} catch (MatrixIncompatibilityException | NotVectorException e) {
				e.printStackTrace();
			}

			if (dotProduct > 0)
				output[col] = true;
			else
				output[col] = false;

		}

		return output;

	}

	/**
	 * Train the Hopfield network for remembering a given pattern. Training for
	 * multiple values is achieved by calling the train function multiple times. <br/>
	 * 
	 * @param pattern
	 *            the pattern which the network has to remember. <br/>
	 * 
	 * @throws HopfieldNetworkException
	 *             if the size of the neural network does not match the size of
	 *             the input pattern
	 */
	public void train(final boolean[] pattern) throws HopfieldNetworkException {

		if (pattern.length != weightMatrix.getRows())
			throw new HopfieldNetworkException();

		Matrix contributionMatrix = new Matrix(weightMatrix.getRows(),
				weightMatrix.getRows());

		final Matrix rowMatrix = Matrix.createRowMatrix(BiPolarUtil
				.bipolar2double(pattern));
		final Matrix columnMatrix = MatrixMath.transpose(rowMatrix);

		try {
			contributionMatrix = MatrixMath.multiply(columnMatrix, rowMatrix);

			/*
			 * subtract identity matrix from the resultant square contribution
			 * matrix because no neuron in the network is connected to itself
			 */
			contributionMatrix = MatrixMath.subtract(contributionMatrix,
					MatrixMath.identity(contributionMatrix.getRows()));

			// add the contribution matrix to this weightMatrix
			this.weightMatrix = MatrixMath.add(contributionMatrix,
					this.weightMatrix);
		} catch (MatrixIncompatibilityException e) {
			e.printStackTrace();
		}

	}

}
