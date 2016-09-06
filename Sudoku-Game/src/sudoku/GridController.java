package sudoku;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GridController extends JFrame {
	
	
	
	
	private GridObject gridObject = new GridObject(new Generator());
	
	/**
	 * Checks the gridObject to see if the user has won
	 * @return	<code>true</code> if the user won; <code>false</code> otherwise
	 */
	public boolean checkWin(){
		int[][] grid = gridObject.getGrid();
		int[][] gridPairs = { {0,0}, {3,0}, {6,0}, {0, 3}, {0, 6}, {3, 3}, {3, 6}, {6,3}, {6,6}};
		
		for(int i = 0; i < grid.length; i++){
			//Check each row
			if(sumRow(grid, i) != 45){
				return false;
			}
			
			//Check each column
			else if(sumCol(grid, i) != 45){
				return false;
			}
			
			//Check each box
			else if(sumBox(grid, gridPairs[i][0], gridPairs[i][1]) != 45){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Take the summation of a column in a square 2D array
	 * @param grid	The 2D array
	 * @param index	The column index
	 * @return		The summation of all the elements in the column
	 */
	private int sumCol(int[][] grid, int index){
		int sum = 0;
		for(int i = 0; i < grid.length; i++){
			sum+= grid[i][index];
		}
		return sum;
	}
	
	/**
	 * Returns the summation of all the elements a row in a square array
	 * @param array	The array to sum
	 * @param index The row index
	 * @return	The sum of all elements in the row
	 */
	private int sumRow(int[][] array, int index){
		int sum = 0;
		for(int i = 0; i < array.length; i++){
			sum += array[index][i];
		}
		return sum;
	}

	/**
	 * Returns the summation of all elements in a given box
	 * @param array	The array containing boxes
	 * @param upperLeftRow	The row of the upper left square
	 * @param upperLeftCol	The column of the upper left square
	 * @return	The summation of all the elements in the box
	 */
	private int sumBox(int[][] array, int upperLeftRow, int upperLeftCol){
		int sum = 0;
		
		for(int i = upperLeftRow; i < upperLeftRow+3; i++){
			for(int j = upperLeftCol; j < upperLeftCol+3; j++){
				sum += array[i][j];
			}
		}
		
		return sum;
	}
	
	



	/*
	 * Define buttons for the game
	 * reset -- restart the game
	 * gen -- generate numbers at the beginning of the game
	 * solve -- make the application solve for possible solution	
	 */
	private JButton reset = new JButton("Reset");
	private JButton gen = new JButton("Generate");
	private JButton solve = new JButton("Solve");


	/**
	 * Construct a window for displaying the game
	 */
	public GridController(){
		
		
		super("Sudoku Game");
		
		JPanel gamePanel = new JPanel();									// Create a JPanel for the game
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));	// Set the layout of the panel

		JPanel buttonPanel = new JPanel();									// Create a small panel for the buttons
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(reset);												// Add three buttons into the panel
		buttonPanel.add(gen);
		buttonPanel.add(solve);


		GridPanel gridPanel = new GridPanel();								// Create a small panel for the grid
		Generator g = new Generator();
		GridObject go = new GridObject(g.generate());
		drawGrid(go,gridPanel);

		gamePanel.add(buttonPanel);											// Put the button panel into the game panel
		gamePanel.add(gridPanel);											// Put the grid panel into the game panel

		add(gamePanel);														// Add the game panel into the window(JFrame)
		pack();
		setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	private void drawGrid(GridObject gridObject, GridPanel gridPanel){
		int[][] grid = gridObject.getGrid();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] != 0){
					//TODO: Draw the number onto grid
					gridPanel.setNumber(i,j,grid[i][j]);
				}
			}
		}
	}
	


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
