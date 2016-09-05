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
			else if(sumBox(grid) != 45){
				return false;
			}
			
			return true;
		}
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
	 * @param upperRightRow	The row of the upper left square
	 * @param upperRightCol	The column of the upper left square
	 * @return	The summation of all the elements in the box
	 */
	private int sumBox(int[][] array, int upperRightRow, int upperRightCol){
		int sum = 0;
		
		for(int i = upperRightRow; i < upperRightRow+3; i++){
			for(int j = upperRightCol; j < upperRightCol+3; j++){
				sum += array[i][j];
			}
		}
		
		return sum;
	}
	
	



		
		private JButton reset = new JButton("Reset");
		private JButton gen = new JButton("Generate");
		private JButton solve = new JButton("Solve");
		
		private int columnCount = 9;
	    private int rowCount = 9;
	    private List<Rectangle> cells;
	    private Point selectedCell;
	    
	    
		public GridController(){
			super("Sudoku Game");
			
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
			buttonPanel.add(reset);
			buttonPanel.add(gen);
			buttonPanel.add(solve);
			
		
			GridPanel gridPanel = new GridPanel();
			
			
			gamePanel.add(buttonPanel);
			gamePanel.add(gridPanel);
			
			
			
			add(gamePanel);
			pack();
	        setLocationRelativeTo(null);
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
	                GridController g = new GridController();
	                g.setDefaultCloseOperation(EXIT_ON_CLOSE);
	                g.setVisible(true);
	            }
	        });
			
		}
	
}
