package sudoku;

import java.util.ArrayList;


public class Solver {
	
	/**
	 * The solve() wrapper method
	 * This returns a solved Sudoku grid
	 * @return	The solved Sudoku grid
	 */
	public int[][] solve(GridObject gridObject){
		gridObject.resetGrid();
		int[][] numPossibleValues = updateNumPossibleValues(gridObject);
		int[] nextIndex = chooseNextSquare(gridObject, numPossibleValues);
		return solveRecursive(gridObject, numPossibleValues, 0, nextIndex[0], nextIndex[1]);
	}
	
	/**
	 * This is the recursive implementation of the solve algorithm
	 * @param gridObject	The Sudoku grid
	 * @param numPossibleValues	An array containing the number of possible values
	 * @param value	The number to test
	 * @param oldRow	The previous row
	 * @param oldCol	The previous column
	 * @return	The solved Sudoku grid
	 */
	private int[][] solveRecursive(GridObject gridObject, int[][] numPossibleValues, int value, int oldRow, int oldCol){
		if(isComplete(gridObject)){
			return gridObject.getGrid();
		}

		int[] nextIndex = chooseNextSquare(gridObject, numPossibleValues);
		int[] possibleValues = checkSquare(gridObject, nextIndex[0], nextIndex[1]);
	
		if(value < possibleValues.length && isGridGood(numPossibleValues)){
			gridObject.updateNum(nextIndex[0], nextIndex[1], possibleValues[value]);
			return solveRecursive(gridObject, updateNumPossibleValues(gridObject), 0, nextIndex[0], nextIndex[1]);
		} else{
			gridObject.updateNum(oldRow, oldCol, 0);
			return solveRecursive(gridObject, updateNumPossibleValues(gridObject), ++value, oldRow, oldCol);
		}
	}
	
	public int[][] solveIterative(GridObject gridObject){
		gridObject.resetGrid();
		int[][] numPossibleValues = updateNumPossibleValues(gridObject);
		int[] nextIndex = chooseNextSquare(gridObject, numPossibleValues);
		int oldRow = nextIndex[0];
		int oldCol = nextIndex[1];
		int value = 0;
		int iterations = 0;
		while(!isComplete(gridObject) && iterations < 100000000){
			nextIndex = chooseNextSquare(gridObject, numPossibleValues);
			int[] possibleValues = checkSquare(gridObject, nextIndex[0], nextIndex[1]);
		
			if(value < possibleValues.length && isGridGood(numPossibleValues)){
				gridObject.updateNum(nextIndex[0], nextIndex[1], possibleValues[value]);
				numPossibleValues = updateNumPossibleValues(gridObject);
				value = 0;
				oldRow = nextIndex[0];
				oldCol = nextIndex[1];
				iterations++;
				printArray(gridObject.getGrid());
				System.out.println("____________________________________\n\n");
				continue;
			} else{
				gridObject.updateNum(oldRow, oldCol, 0);
				numPossibleValues = updateNumPossibleValues(gridObject);
				value++;
				iterations++;
				
				printArray(gridObject.getGrid());
				System.out.println("____________________________________\n\n");
				continue;
			}
			
		}
		System.out.println("TERMINATE: " + iterations);
		return gridObject.getGrid();
	}
	
	private boolean isGridGood(int[][] numPossibleValue){
		for(int i = 0; i < numPossibleValue.length; i++){
			for(int j = 0; j < numPossibleValue[0].length; j++){
				if(numPossibleValue[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * This method determines whether a grid is complete
	 * @return	<code>true</code> if the grid is complete; <code>false</code> otherwise
	 */
	private boolean isComplete(GridObject gridObject){
		int[][] grid = gridObject.getGrid();
		for(int i = 0; i < grid.length; i++){
			boolean[] numTaken = new boolean[9];
			for(int j = 0; j < grid.length; j++){
				int[] possibleValues = checkSquare(gridObject,i,j);
				if(possibleValues.length != 0){
					return false;
				}
				if(numTaken[grid[i][j]-1]){
					return false;
				} else{
					numTaken[grid[i][j]-1] = true;
				}
			}
			for(int j = 0; j < numTaken.length; j++){
				if(!numTaken[j]){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * This method returns the index of the first most constrained square (i, j)
	 * @return	The index of the most constrained square (i, j)
	 */
	private int[] chooseNextSquare(GridObject gridObject, int[][] numPossibleValues){
		int[][] grid = gridObject.getGrid();
		int[] index = new int[2];
		int minValue = 10;
		for(int i = 0; i < numPossibleValues.length; i++){
			for(int j = 0; j < numPossibleValues[0].length; j++){
				if(grid[i][j] < minValue){
					minValue = grid[i][j];
					index[0] = i;
					index[1] = j;
				}
			}
		}
		return index;
	}
	
	/**
	 * This method updates the number of possible values in the grid
	 */
	private int[][] updateNumPossibleValues(GridObject gridObject){
		int[][] grid = gridObject.getGrid();
		int[][] numPossibleValues = new int[9][9];
		//Set all filled in values to 10
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] != 0){
					numPossibleValues[i][j] = 10;
				}else{
					int[] possibleValues = checkSquare(gridObject, i, j);
					numPossibleValues[i][j] = possibleValues.length;
				}
			}
		}
		return numPossibleValues;
		
	}
	
	/**
	 * This method returns an array of possible values for a given square at (i,j)
	 * @param row	The row index
	 * @param col	The column index
	 * @return		A list of possible values
	 */
	private int[] checkSquare(GridObject gridObject, int row, int col){
		int[][] grid = gridObject.getGrid();
		ArrayList<Integer> possibleValues = new ArrayList<Integer>();
		boolean[] numbers = new boolean[9];
		
		
		for(int i = 0; i < grid.length; i++){
			
			//Check row
			if(grid[row][i] - 1 >= 0){
				numbers[grid[row][i]-1] = true;
			}
			//Check column
			if(grid[i][col] - 1 >= 0){
				numbers[grid[i][col]-1] = true;
			}
			
			//Check box
			numbers = checkBox(row, col, gridObject, numbers);
			
		}
		
		//Convert all numbers into an array list
		for(int i = 0; i < numbers.length; i++){
			if(!numbers[i]){
				possibleValues.add(i+1);
			}
		}
		
		return possibleValues.stream().mapToInt(i -> i).toArray();
	}
	
	/**
	 * This method checks for possible values for a given box, given the index of a square.
	 * This method is used by checkSquare(int, int).
	 * 
	 * @param row	The row index
	 * @param col	The column index
	 * @param grid	The grid to check
	 * @param numbers	A boolean array containing used values
	 * @return	A boolean array containing used values
	 */
	private boolean[] checkBox(int row, int col, GridObject gridObject, boolean[] numbers){
		int[][] grid = gridObject.getGrid();
		if(row >= 0 && row <= 2){
			if(col >= 0 && col <= 2){
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			} else if(col >= 3 && col <= 5){
				for(int i = 0; i < 3; i++){
					for(int j = 3; j < 6; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			} else if(col >= 6 && col <= 8){
				for(int i = 0; i < 3; i++){
					for(int j = 6; j < 9; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			}
		} else if(row >= 3 && row <= 5){
			if(col >= 0 && col <= 2){
				for(int i = 3; i < 6; i++){
					for(int j = 0; j < 3; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			} else if(col >= 3 && col <= 5){
				for(int i = 3; i < 6; i++){
					for(int j = 3; j < 6; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			} else if(col >= 6 && col <= 8){
				for(int i = 3; i < 6; i++){
					for(int j = 6; j < 9; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			}
		} else if(row >= 6 && row <= 8){
			if(col >= 0 && col <= 2){
				for(int i = 6; i < 9; i++){
					for(int j = 0; j < 3; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			} else if(col >= 3 && col <= 5){
				for(int i = 6; i < 9; i++){
					for(int j = 3; j < 6; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			} else if(col >= 6 && col <= 8){
				for(int i = 6; i < 9; i++){
					for(int j = 6; j < 9; j++){
						if(grid[i][j]-1 > 0){
							numbers[grid[i][j]-1] = true;
						}
					}
				}
			}
		}
		return numbers;
	}
	
	/**
	 * This method is used to print a 2D array
	 * @param array	The 2D array 
	 */
	private void printArray(int[][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[0].length; j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
}
