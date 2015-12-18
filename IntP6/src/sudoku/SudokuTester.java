package sudoku;

import java.io.IOException;

/**
 * A test script that will be used to test your code.
 * 
 * @author Mark Crowley (original)
 * 
 * @author Jacek Kisynski (updates)
 *
 * @author Ines Gonzalez (adaptacion a ISI, Unican)
 * 
 * @version 2015.11.*
 */
public class SudokuTester {

	/**
	 * @param argv not used.
	 * 
	 * @throws java.io.IOException
	 */
	public static void main(String[] argv) throws java.io.IOException {
		SudokuSolver acSolver = new SudokuSolver();
		System.out.println(acSolver.authors() + "\n");

		/** Easy Sudokus */
		// This Sudoku is already solved, obviously it can be "solved" by AC alone.
		test(acSolver, "solved", true, true); // Time: 0s (bonus 0s)
		// This Sudoku can be solved by AC alone
		test(acSolver, "easy", true, true); // Time: 0s (bonus 0s)
		// This Sudoku can not be solved by AC alone, it requires search.
		test(acSolver, "hard", true, true); // Time: 0s (bonus 0s)
		// Evil level Sudoku from www.websudoku.com, it is difficult for people, easy for MAC
		test(acSolver, "evil", true, false); // Time: 0s (bonus 0s)
		// Sudoku from Sudoku enthusiast (http://www.flickr.com/photos/npcomplete/2384354604/), requires MAC
		test(acSolver, "starBurstLeo", true, false); // Time: 0s (bonus 0s)

		/**
		 * Somewhat harder Sudokus, supposedly they pose a challenge to Sudokus solvers. They all require AC+search. Taken from
		 * http://en.wikipedia.org/wiki/Algorithmics_of_sudoku . Do not pay much attention to analysis presented in this Wikipedia article, it is not
		 * very scientific.
		 */
		test(acSolver, "easterMonster", true, false); // Time: 1s (bonus 4s)
		test(acSolver, "tarek071223170000-052", true, false); // Time: 3s (bonus 5s)
		test(acSolver, "goldenNugget", true, false); // Time: 6s (bonus 7s)

		/**
		 * Two Sudokus with only 17 givens from http://people.csse.uwa.edu.au/gordon/sudokumin.php, which is the smallest known number of givens
		 * yielding valid Sudokus. Specialized Sudoku solvers (for
		 * example http://www.sudokusolver.co.uk/) can solve them immediately.
		 */
		test(acSolver, "minimum1", true, false); // Time: 47s (bonus 166s)
		test(acSolver, "minimum50", true, false); // Time: 59s (bonus 90s)

		/**
		 * A Sudoku that is exceptionally hard for brute-force algorithms.
		 * To see if MAC can solve it, uncomment the following line
		 * Source: http://en.wikipedia.org/wiki/Algorithmics_of_sudoku
		 */
		//test(solver, "nearWorstCase", true, false); // Time: 1275s (bonus 1275s)
		System.out.println("FIN. Si has conseguido resolverlos todos, !enhorabuena!");
	}

	/**
	 * @param acSolver Sudoku solver;
	 * @param boardName name of the Sudoku board;
	 * @param hasSolution if true, then the board has a solution and it will be compared to one obtained by the solver;
	 * @param verbose if true, problem board and the solution board will be printed on the screen.
	 * 
	 * @throws IOException
	 */
	private static void test(SudokuSolver acSolver, String boardName, boolean hasSolution, boolean verbose) throws IOException {
		long time = System.currentTimeMillis();
		try {
			System.out.println("Board '" + boardName + "': ");
			int[][] problem = SudokuUtil.readInBoard(boardName + ".sud", 9);
			if (verbose)
				System.out.println(SudokuUtil.formatBoard(problem));
			System.out.print("Solution: ");
			int[][] solution = acSolver.solve(problem);
			if (verbose)
				System.out.println("\n" + SudokuUtil.formatBoard(solution));
			if (hasSolution)
				System.out.println(match(solution, SudokuUtil.readInBoard(boardName + "Solution.sud", 9)) ? "CORRECT" : "INCORRECT");
			else
				System.out.println("ERROR: board '" + boardName + "' is not a valid Sudoku. Exception should have been thrown.");
		} catch (Exception e) {
			System.out.println("Board '" + boardName + "': crashed " + e);
		}
		System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000 + " seconds\n");
	}

	/**
	 * @param board1 Sudoku board;
	 * @param board2 Sudoku board.
	 * 
	 * @return true if given boards are identical, false otherwise.
	 */
	private static boolean match(int[][] board1, int[][] board2) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (board1[i][j] != board2[i][j])
					return false;
		return true;
	}
}