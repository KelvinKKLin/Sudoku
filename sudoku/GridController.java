package sudoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GridController extends JFrame {

	/*
	 * Define buttons for the game
	 * reset -- restart the game
	 * gen -- generate numbers at the beginning of the game
	 * solve -- make the application solve for possible solution
	 */
	private JButton reset = new JButton("Reset");
	private JButton gen = new JButton("Generate");
	private JButton solve = new JButton("Solve");
	private GridObject gridObject = new GridObject(new Generator());
	private GridPanel gridPanel = new GridPanel();								// Create a small panel for the grid
	private Generator g = new Generator();
	private GridObject go;


	/**
	 * The Grid Controller Constructor
	 *
	 * Construct a window for displaying the game
	 */
	public GridController(){

		super("Sudoku");

		gen.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				generateButtonActionPreformed(evt);
			}
		});

		reset.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				resetButtonActionPreformed(evt);
			}
		});

		solve.addActionListener(new java.awt.event.ActionListener(){

			@Override
			public void actionPerformed(ActionEvent evt) {
				solvedButtonActionPreformed(evt);
			}
		});

		JPanel gamePanel = new JPanel();									// Create a JPanel for the game
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));	// Set the layout of the panel

		JPanel buttonPanel = new JPanel();									// Create a small panel for the buttons
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(reset);												// Add three buttons into the panel
		buttonPanel.add(gen);
		buttonPanel.add(solve);

		int[][] grid = g.generate();
		go = new GridObject(grid);
		drawGrid(go,gridPanel);

		gamePanel.add(buttonPanel);											// Put the button panel into the game panel
		gamePanel.add(gridPanel);											// Put the grid panel into the game panel

		add(gamePanel);														// Add the game panel into the window(JFrame)
		pack();
		setLocationRelativeTo(null);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	/**
	 * This method defines the behaviour of the generate button.
	 *
	 * When the generate button is pressed, a new grid object should be created
	 * and displayed to the screen.
	 *
	 * @param evt  An action event
	 */
	private void generateButtonActionPreformed(java.awt.event.ActionEvent evt){
		int[][] grid = g.generate();
		go = new GridObject(grid);
		drawGrid(go,gridPanel);
		gridPanel.repaint();
		reset.setEnabled(true);
	}

	/**
	 * This method defines the behaviour of the reset button.
	 *
	 * When the reset button is pressed, the grid should be cleared and displayed
	 * to the user.
	 *
	 * @param evt  An action event
	 */
	private void resetButtonActionPreformed(java.awt.event.ActionEvent evt){
		go.resetGrid();
		drawGrid(go,gridPanel);
		gridPanel.repaint();
	}

	/**
	 * This method defines the behaviour of the solved button.
	 *
	 * When the user presses the solve button, it should display the answers to
	 * the user.
	 *
	 * @param evt  An action event
	 */
	private void solvedButtonActionPreformed(java.awt.event.ActionEvent evt){
		go.resetGrid();
		drawGrid(new GridObject(new Solver().solve(go)), gridPanel);
		gridPanel.repaint();
		reset.setEnabled(false);
	}

	/**
	 * This method draws the grid to the screen.
	 *
	 * @param gridObject 	A data representation of the grid
	 * @param gridPanel 	A representation of the grid UI
	 */
	private void drawGrid(GridObject gridObject, GridPanel gridPanel){
		int[][] grid = gridObject.getGrid();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] != 0){
					//TODO: Draw the number onto grid
					gridPanel.setNumber(i,j,grid[i][j]);
				} else{
					gridPanel.setNumber(i,j);
				}
			}
		}
	}


	/**
	 * The main entry point to the program
	 *
	 * @param args Command line arguments
	 */
	public static void main(String[] args){

		// set look and feel to the system look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GridController g = new GridController();						// Invoke the constructor to create the game display
			}
		});

	}


}
