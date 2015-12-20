package org.implement;

import java.applet.Applet;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;

import org.neural.HopfieldNetworkException;
import org.util.BiPolarUtil;
import org.util.MatrixIndexOutOfBoundsException;

/**
 * HopfieldApplet creates an applet which helps visualize how the network is
 * trained and used for recalling patterns. The applet will have textfields to
 * show the weight matrix of a network and the input and output patterns.<br/>
 * The three buttons on the applet will help either present the pattern or train
 * the network based on the pattern at the input. Clear button clears the weight
 * matrix <br/>
 * 
 * 
 * @author Srijith Nair
 * @version 1.0<br/>
 *
 */
public class HopfieldApplet extends Applet implements
		java.awt.event.ActionListener, java.awt.event.FocusListener {

	private static final long serialVersionUID = 129385092867865L;

	private java.awt.Button buttonPresent;
	private java.awt.Button buttonTrain;
	private java.awt.Button buttonClear;

	private java.awt.Label matrixLabel;
	private java.awt.Label inputLabel;
	private java.awt.Label outputLabel;

	private java.awt.TextField grid[][] = new java.awt.TextField[4][4];
	private java.awt.Choice inputChoice[] = new java.awt.Choice[4];
	private java.awt.TextField outputDisplay[] = new java.awt.TextField[4];

	private org.neural.HopfieldNetwork network = new org.neural.HopfieldNetwork(
			4);

	@Override
	public void actionPerformed(ActionEvent e) {

		// get the source of the action
		Object actionSource = e.getSource();

		if (actionSource == this.buttonPresent)
			runNetwork();
		else if (actionSource == this.buttonTrain)
			trainNetwork();
		else if (actionSource == this.buttonClear) {
			this.network.getWeight().clear();
			updateGrid();
			for (int i = 0; i < 4; i++) {
				this.inputChoice[i].select("0");
				this.outputDisplay[i].setText("" + 0);
				this.outputDisplay[i].setBackground(Color.white);
			}
		}

	}

	public void runNetwork() {

		boolean inputPattern[] = new boolean[4];
		boolean result[] = new boolean[4];

		for (int i = 0; i < 4; i++) {

			if (this.inputChoice[i].getSelectedIndex() == 0)
				inputPattern[i] = false;
			else
				inputPattern[i] = true;

		}

		try {
			result = this.network.present(inputPattern);
		} catch (HopfieldNetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.updateGrid();

		for (int i = 0; i < 4; i++) {
			this.outputDisplay[i].setText(""
					+ (int) BiPolarUtil.bipolar2double(result[i]));

			// coloring for error in result
			if (result[i] != inputPattern[i])
				this.outputDisplay[i].setBackground(Color.yellow);
			else
				this.outputDisplay[i].setBackground(Color.white);

		}

	}

	public void trainNetwork() {

		boolean inputPattern[] = new boolean[4];

		for (int i = 0; i < 4; i++) {

			if (this.inputChoice[i].getSelectedIndex() == 0)
				inputPattern[i] = false;
			else
				inputPattern[i] = true;

		}

		try {
			this.network.train(inputPattern);
		} catch (HopfieldNetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.updateGrid();

	}

	@Override
	public void init() {

		setLayout(null);
		setBackground(Color.lightGray);

		// set the applet labels and arrange them
		this.matrixLabel = new java.awt.Label();
		this.matrixLabel.setText("Weight Matrix: ");
		add(this.matrixLabel);
		this.matrixLabel.setBounds(24, 12, 192, 12);

		this.inputLabel = new java.awt.Label();
		this.inputLabel.setText("Input pattern: ");
		add(this.inputLabel);
		this.inputLabel.setBounds(24, 180, 192, 12);

		this.outputLabel = new java.awt.Label();
		this.outputLabel.setText("Output of Network: ");
		add(this.outputLabel);
		this.outputLabel.setBounds(24, 276, 192, 12);

		// create and arrange the weight matrix
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {

				this.grid[i][j] = new java.awt.TextField();
				add(this.grid[i][j]);
				this.grid[i][j].setBounds((24 + (j * 60)), (36 + (i * 38)), 48,
						24);
				this.grid[i][j].setText("0");

				// add a focus listener to the grid
				this.grid[i][j].addFocusListener(this);

				// leading diagonal elements are not to be tampered
				if (i == j)
					this.grid[i][j].setEditable(false);

			}

		for (int i = 0; i < 4; i++) {

			this.inputChoice[i] = new java.awt.Choice();
			this.inputChoice[i].add("0");
			this.inputChoice[i].add("1");
			this.inputChoice[i].select(0);
			add(this.inputChoice[i]);
			this.inputChoice[i].setBounds(24 + i * 60, 200, 48, 24);

			this.outputDisplay[i] = new java.awt.TextField();
			add(this.outputDisplay[i]);
			this.outputDisplay[i].setBounds(24 + i * 60, 300, 48, 24);
			this.outputDisplay[i].setEditable(false);

		}

		this.buttonClear = new java.awt.Button("Clear");
		add(this.buttonClear);
		this.buttonClear.setBounds(210, 240, 50, 24);
		buttonClear.addActionListener(this);

		this.buttonPresent = new java.awt.Button("Go");
		add(this.buttonPresent);
		this.buttonPresent.setBounds(10, 240, 50, 24);
		buttonPresent.addActionListener(this);

		this.buttonTrain = new java.awt.Button("Train");
		add(this.buttonTrain);
		this.buttonTrain.setBounds(110, 240, 50, 24);
		buttonTrain.addActionListener(this);

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// do nothing
	}

	@Override
	public void focusLost(FocusEvent arg0) {

		updateNetworkMatrix();
		updateGrid();

	}

	public void updateGrid() {

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				try {
					this.grid[i][j].setText(""
							+ (int) this.network.getWeight().getElement(i, j));
				} catch (MatrixIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}

	}

	public void updateNetworkMatrix() {

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				try {
					// prevent neuron self connection
					if (i == j)
						this.network.getWeight().set(i, j, 0);
					else
						this.network.getWeight().set(i, j,
								Integer.parseInt(this.grid[i][j].getText()));
				} catch (NumberFormatException
						| MatrixIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	}

}
