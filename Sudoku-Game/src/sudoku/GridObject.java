package sudoku;

public class GridObject {
	
	private int[][] grid;
	private int[][] defaultGrid;
	
	public GridObject(Generator g){
		grid = g.generate();
		defaultGrid = grid;
	}
	
	/**
	 * The checkRep() method checks the representation of the GridObject.
	 * @return <code>true</code> if the representation is valid; <code>false</code> otherwise
	 */
	public boolean checkRep(){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] < 1 || grid[i][j] > 9){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns the grid representation
	 * @return	The grid representation
	 */
	public int[][] getGrid(){
		return grid;
	}
	
	/**
	 * Resets the grid to the default state.
	 * @return	<code>true</code> if operation is sucessful; <code>false</code> otherwise
	 */
	public boolean resetGrid(){
		try{
			grid = defaultGrid;
			return true;
		} catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Updates the grid representation with a given number
	 * @param row	The row to update
	 * @param col	The column to update
	 * @param num	The number to add
	 * @return		<code>true</code> if the representation is successful; <code>false</code> otherwise.
	 */
	public boolean updateNum(int row, int col, int num){
		try{
			if(num < 1 || num > 9){
				new IndexOutOfBoundsException("Number must be between 1 and 9");
			}
			grid[row][col] = num;
			return true;
		} catch (Exception e){
			return false;
		}
	}

	
}
