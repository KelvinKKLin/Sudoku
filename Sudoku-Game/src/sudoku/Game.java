package sudoku;

public class Game {

	public static void main(String[] args) {
		//GridController gc = new GridController();
		
		int[][] sudokuEasy = { {0,0,3,9,0,0,0,5,1},
						   {5,4,6,0,1,8,3,0,0},
						   {0,0,0,0,0,7,4,2,0},
						   {0,0,9,0,5,0,0,3,0},
						   {2,0,0,6,0,3,0,0,4},
						   {0,8,0,0,7,0,2,0,0},	   
						   {0,9,7,3,0,0,0,0,0},
						   {0,0,1,8,2,0,9,4,7},
						   {8,5,0,0,0,4,6,0,0}
						 };
		
		int[][] sudokuHard = { {0,0,0,0,0,0,0,1,2},
				   {0,0,0,0,3,5,0,0,0},
				   {0,0,0,6,0,0,0,7,0},
				   {7,0,0,0,0,0,3,0,0},
				   {0,0,0,4,0,0,8,0,0},
				   {1,0,0,0,0,0,0,0,0},	   
				   {0,0,0,1,2,0,0,0,0},
				   {0,8,0,0,0,0,0,4,0},
				   {0,5,0,0,0,0,6,0,0}
				 };
		
		Generator g = new Generator();
		Solver s = new Solver();
		while(true){
			GridObject go = new GridObject(g.generate());
			try{
				int[][] grid = s.solve(go);
				printArray(grid);
			} catch(StackOverflowError e){
				System.out.println("Solution does not exist");
			}
		}
		
		
	}
	
	private static void printArray(int[][] array){
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

}
