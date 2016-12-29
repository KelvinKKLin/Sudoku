# Sudoku
A Java based Sudoku game made for fun in order to experiment with backtracking with most constrained square selection (minimum remaining value heuristic), as described in *The Algorithm Design Manual* by Steven S. Skiena.

## How Does It Work?
The solver algorithm begins by evaluating whether the grid has been solved. If so, then it returns the grid.

Otherwise, it calculates the number of possibilities remaining for each square, and it performs backtracking on the square with the fewest number of options. Backtracking is essentially a brute-force search using all of the different possibilities. It assumes the solution is the first possible number, and then it recursively tries to solve the grid. If it fails, then it goes to the second number, and then the third number, etc.

If it runs out of options to try, then the algorithm assumes that the grid is unsolvable.

## Compiling the Source Code
The source code can be compiled using the provided Makefile.

Type `make` to compile the source code. To run the source code, type `./run.sh`, or double click on the `Main.jar` file.

To clean up the repository, you can use the `make clean` command. The `make clean` command will delete all of the generated .class files, the Manifest file, the .jar file, as well as run.sh.

## Authors
1. Kelvin Lin (Software Engineering and Management, Level 3)
2. Jie Luo (Software Engineering, Level 3)
3. Weilin Hu (Computer Science as a Second Degree, Level 4)
