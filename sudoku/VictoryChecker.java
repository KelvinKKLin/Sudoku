package sudoku;

/**
 * This class checks to see whether the user has won the game of Sudoku.
 *
 * @author Kelvin Lin
 */
public class VictoryChecker {

	/**
	 * Checks the gridObject to see if the user has won
	 * @return	<code>true</code> if the user won; <code>false</code> otherwise
	 */
	public static boolean checkWin(GridObject gridObject){
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
	private static int sumCol(int[][] grid, int index){
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
	private static int sumRow(int[][] array, int index){
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
	private static int sumBox(int[][] array, int upperLeftRow, int upperLeftCol){
		int sum = 0;

		for(int i = upperLeftRow; i < upperLeftRow+3; i++){
			for(int j = upperLeftCol; j < upperLeftCol+3; j++){
				sum += array[i][j];
			}
		}

		return sum;
	}

}
