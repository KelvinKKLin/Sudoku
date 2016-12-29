package sudoku;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class generates Sudoku grids.
 *
 * @author Weilin Hu, Kelvin Lin
 */
public class Generator {

	/**
	 * This class generates Sudoku grids.
	 *
	 * It guarantees that the generated grid is solvable
	 * by solving it first with the Solver.
	 *
	 * @return The generated grid
	 */
	public int[][] generate(){
		int[][] grid = generateGrid();
		int[][] gridClone = new int[grid.length][grid[0].length];
		boolean gridGenerated = false;
		while(!gridGenerated){
			try{
				for(int i = 0; i < gridClone.length; i++){
					for(int j = 0; j < gridClone[0].length; j++){
						gridClone[i][j] = grid[i][j];
					}
				}
				Solver s = new Solver();
				GridObject go = new GridObject(grid);
				s.solve(go);
				gridGenerated = true;
			}catch(StackOverflowError e){
				grid = generateGrid();
			}
		}

		return gridClone;
	}

	/**
	 * This method prints out the Sudoku grid.
	 *
	 * It is used for testing.
	 *
	 * @param array 	The Sudoku grid
	 */
	private static void printArray(int[][] array){
		System.out.print("GENERATOR");
		for(int i = 0; i < array.length; i++){
			if(i%3 == 0) System.out.println("\n-----------------------------");
			else System.out.println();
			for(int j = 0; j < array[0].length; j++){
				if (j % 3 == 0) System.out.print(" | ");
				System.out.print(array[i][j] + " ");

			}
		}
		System.out.println("\n\nEOF\n");
	}

	/**
	 * This method generates a Sudoku grid
	 *
	 * @return	The Sudoku grid
	 */
	private int[][] generateGrid(){
		int[][] grid = new int[9][9];
		boolean [][] row = new boolean[9][9];
		boolean [][] col = new boolean[9][9];
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				row[i][j]=true;
				col[i][j]=true;
			}
		}
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				Random percentage = new Random();
					int value = percentage.nextInt(9)+1;

					check(grid, row, col, value, i,j,0);
			}
		}



		while(checkValid(grid) != true){

			ArrayList<Integer> rowclean = new ArrayList<Integer>();
			ArrayList<Integer> colclean = new ArrayList<Integer>();

			for (int i=0;i<9;i++){
				for (int j=0;j<9;j++){
					if (grid[i][j]==0){
						rowclean.add(i);
						colclean.add(j);
					}
				}
			}
			Iterator<Integer>cleanrow = rowclean.iterator();
			Iterator<Integer> cleancol = colclean.iterator();
			while(cleanrow.hasNext()){
				int number = cleanrow.next();
				for (int i=0;i<9;i++){
					grid[number][i]=0;
				}
			}
			while(cleancol.hasNext()){
				int number = cleancol.next();
				for (int i=0;i<9;i++){
					grid[i][number]=0;
				}
			}
			for (int i=0;i<9;i++){
				for (int j=0;j<9;j++){
					row[i][j]=true;
					col[i][j]=true;
				}
			}
			for (int i=0;i<9;i++){
				for (int j=0;j<9;j++){
					if (grid[i][j]>00){
						row[i][(grid[i][j])-1]=false;
						col[j][(grid[i][j])-1]=false;
					}
				}
			}
			for (int i=0;i<9;i++){
				for (int j=0;j<9;j++){
					if (grid[i][j]==0){
					Random percentage = new Random();
						int value = percentage.nextInt(9)+1;

						check(grid, row, col, value, i,j,0);
					}
				}
			}
		}

		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				Random percentage = new Random();
					int value = percentage.nextInt(9)+1;
					if (value <4){
						grid[i][j] = 0;
					}
			}
		}
		return grid;
	}

	/**
	 * This method checks to see that the generated grid is valid.
	 *
	 * A valid grid is a grid with at least one potential solution.
	 *
	 * @param grid 	The Sudoku grid
	 * @return <code>true</code> if the grid is valid, <code>false</code> otherwise.
	 */
	private boolean checkValid(int[][] grid){
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				if (grid[i][j]==0){
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * This method checks to see that there is exactly one of each number
	 * in every row and column of the grid.
	 *
	 * @param grid 		The Sudoku grid
	 * @param row 		The row to check
	 * @param col 		The column to check
	 * @param value 	The value to look for
	 * @param i 		The current row checking
	 * @param j 		The current column checking
	 * @param count 	The total number of times checked
	 */
	private void check(int[][] grid, boolean[][] row, boolean[][] col, int value,int i, int j, int count){
		if (count<1000){
			if (row[i][value-1] == true && col[j][value-1] == true &&blockCheck(grid,value,i,j) ){
				grid[i][j] = value;
				col[j][value-1]= false;
				row[i][value-1] = false;
			}
			else {
				if (value<9){
				check(grid, row, col, value+1,i,j,count+1);
				}
				else{
					check(grid, row, col, 1,i,j,count+1);
				}
			}
		}
		else{
			return;
		}
	}

	/**
	 * This method checks to see that every block contains exactly one of
	 * each number.
	 *
	 * @param grid 		The Sudoku grid
	 * @param value 	The value to search for
	 * @param i 		The row to search in
	 * @param j 		The column to search in
	 * @return 			<code>true</code> if the block is valid, <code>false</code> otherwise.
	 */
	private boolean blockCheck(int[][] grid,int value,int i, int j){
		int lowerrow= 0;
		int upperrow= 8;
		int lowercol =0;
		int uppercol =8;
		if (i<3){
			upperrow =2;
		}
		else if (i<6){
			lowerrow =3;
			upperrow=5;
		}
		else {
			lowerrow =6;
		}

		if (j<3){
			uppercol =2;
		}
		else if (j<6){
			lowercol =3;
			uppercol=5;
		}
		else {
			lowercol =6;
		}

		for (int k= lowerrow;k<upperrow+1;k++){
			for (int n= lowercol;n<uppercol+1;n++){
				if (grid[k][n] == value){
					return false;
				}
			}
		}
		return true;
	}



}