package sudoku;

import java.util.Random;

public class Generator {
	
	public Generator(){
		
	};
	
	public int[][] generate(){
		int[][] grid = new int[9][9];
		boolean [] row = new boolean[9];
		boolean [][] col = new boolean[9][9];
		for (int i=0;i<9;i++){
			row[i]=true;
			for (int j=0;j<9;j++){
				col[i][j]=true;
			}
		}
		boolean [] rowcheck = new boolean[9];
		for (int i=0;i<9;i++){
			System.arraycopy(row, 0, rowcheck, 0, 9);
			for (int j=0;j<9;j++){
				Random percentage = new Random();
				if ((percentage.nextInt(9)+1)<4){
					int value = percentage.nextInt(9)+1;

					Check(grid, rowcheck, col, value, i,j,0);
				}
				else{
					
				}
			}
		}
		
		return grid;
		
	}
	
	private void Check(int[][] grid, boolean[] rowcheck, boolean[][] col, int value,int i, int j, int count){
		if (count<10){
			if (rowcheck[value-1] == true && col[j][value-1] == true && blockCheck(grid,value,i,j)){
				grid[i][j] = value;
				col[j][value-1]= false;
				rowcheck[value-1] = false;
			}
			else {
				if (value<9){
				Check(grid, rowcheck, col, value+1,i,j,count+1);
				}
				else{
					Check(grid, rowcheck, col, 1,i,j,count+1);
				}
			}
		}
		else{
			return;
		}
	}
	
	private boolean blockCheck(int[][] grid,int value,int i, int j){
		int lowerrow= 0;
		int upperrow= 8;
		int lowercol =0;
		int uppercol =8;
		if (i<3){
			upperrow =2;
		}
		else if (i<7){
			lowerrow =3;
			upperrow=5;
		}
		else {
			lowerrow =6;
		}
		
		if (j<3){
			uppercol =2;
		}
		else if (j<7){
			lowercol =3;
			uppercol=5;
		}
		else {
			lowercol =6;
		}
		
		for (int k= lowerrow;k<upperrow;k++){
			for (int n= lowercol;n<uppercol;n++){
				if (grid[k][n] == value){
					return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
	Generator test = new Generator();
	int[][] grid = test.generate();
	for (int i=0; i<9;i++){
		for (int j=0;j<9;j++){
		System.out.print(grid[i][j]+",");
			}
		System.out.println();
		}
	}
}
