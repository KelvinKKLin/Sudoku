package sudoku;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Generator {
	
	public Generator(){
	};
	
	public int[][] generate(){
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

					Check(grid, row, col, value, i,j,0);
			}	
		}
<<<<<<< HEAD
		
		while(CheckValid(grid) != true){
=======
		//System.out.println("step1");
		for (int i=0; i<9;i++){
			for (int j=0;j<9;j++){
			//System.out.print(grid[i][j]+",");
				}
			//System.out.println();
			}
		/*System.out.println("row:");
		for (int i=0; i<9;i++){
			for (int j=0;j<9;j++){
			System.out.print(row[i][j]+",");
				}
			System.out.println();
			}*/
		int count =0;
		
		while(CheckValid(grid) != true){
			//System.out.println("the count:" + count);
>>>>>>> origin/master
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

						Check(grid, row, col, value, i,j,0);
					}
				}	
			}
<<<<<<< HEAD
		}
=======
			count++;
			//System.out.println("grid:");
			for (int i=0; i<9;i++){
				for (int j=0;j<9;j++){
				//System.out.print(grid[i][j]+",");
					}
				//System.out.println();
				}
			}
		/*System.out.println("row:");
		for (int i=0; i<9;i++){
			for (int j=0;j<9;j++){
			System.out.print(row[i][j]+",");
				}
			System.out.println();
			}*/
>>>>>>> origin/master
		
		
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				Random percentage = new Random();
					int value = percentage.nextInt(9)+1;
					if (value >3){
						grid[i][j] = 0;
					}
			}	
		}
		return grid;		
	}
	
	private boolean CheckValid(int[][] grid){
		for (int i=0;i<9;i++){
			for (int j=0;j<9;j++){
				if (grid[i][j]==0){
					return false;
				}
			}
		}
		return true;
	}
	

	private void Check(int[][] grid, boolean[][] row, boolean[][] col, int value,int i, int j, int count){
		if (count<1000){
			if (row[i][value-1] == true && col[j][value-1] == true &&blockCheck(grid,value,i,j) ){
				grid[i][j] = value;
				col[j][value-1]= false;
				row[i][value-1] = false;
			}
			else {
				if (value<9){
				Check(grid, row, col, value+1,i,j,count+1);
				}
				else{
					Check(grid, row, col, 1,i,j,count+1);
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
<<<<<<< HEAD
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
=======
	
>>>>>>> origin/master
}
