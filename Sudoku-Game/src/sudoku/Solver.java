package sudoku;

public class Solver {

	private GridObject gridObject;
	private int[][] possibleValues = new int[9][9];
	
	public Solver(GridObject g){
		gridObject = g;
		gridObject.resetGrid();
		initPossibleValues();
	}
	
	/**
	 * This method returns the index of the first most constrained square (i, j)
	 * @return	The index of the most constrained square (i, j)
	 */
	private int[] chooseNextSquare(){
		int[][] grid = gridObject.getGrid();
		int[] index = new int[2];
		int minValue = 10;
		for(int i = 0; i < possibleValues.length; i++){
			for(int j = 0; j < possibleValues[0].length; j++){
				if(grid[i][j] < minValue){
					//TODO: Update variables
				}
			}
		}
		return index;
	}
	
	private void initPossibleValues(){
		int[][] grid = gridObject.getGrid();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] != 0){
					possibleValues[i][j] = 10;
				}
			}
		}
	}
	
	private void printArray(int[][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[0].length; j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
}
