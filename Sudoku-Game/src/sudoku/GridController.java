package sudoku;

public class GridController {
	
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
}
