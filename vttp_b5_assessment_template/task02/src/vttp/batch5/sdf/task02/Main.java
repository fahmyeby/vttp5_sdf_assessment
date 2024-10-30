// Name:Muhammad Fahmy bin Zyenal Ebydean
// Email: fahmyebyyussoff@yahoo.com.sg
// Group A

package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws Exception {

		// prompt user to pass in .txt file name as argument when running
		if (args.length != 1) {
			System.out.println("Add in path to .txt file when running");
			System.out.println("Format: java -cp classes vttp.batch5.sdf.task02.Main <TTT/figure1.txt>");
			System.out.println("Where <> is .txt file name");
		}

		// declare variables
		String txtFile = args[0];
		char[][] board = readBoard(txtFile);
		// output template
		System.out.println("Processing: " + txtFile);
		System.out.println("Board:\n");
		printBoard(board);
		int[][] utilityTable = calculateUtilityTable(board);
		System.out.println("---------------------------");
		printUtilityTable(utilityTable);
	}

	private static char[][] readBoard(String txtFile) throws IOException {
		char[][] board = new char[3][3];

		try {
			BufferedReader reader = new BufferedReader(new FileReader(txtFile));
			String line;
			int row = 0;
			while ((line = reader.readLine()) != null && row < 3) {
				for (int column = 0; column < 3 && column < line.length(); column++) {
					board[row][column] = line.charAt(column);
				}
				row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return board;

	}

	private static int[][] calculateUtilityTable(char[][] board) {
		int[][] utilityTable = new int[3][3]; // utility table 2d array

		// check for row and column for best, worst and draw move
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == '.') {
					if (winningMove(board, i, j, 'x')) {
						utilityTable[i][j] = 1; // best
					} else if (losingMove(board, i, j, 'o')) {
						utilityTable[i][j] = -1; // worst
					} else {
						utilityTable[i][j] = 0; // draw
					}
				}
			}
		}
		return utilityTable;
	}

	private static boolean winningMove(char[][] board, int i, int j, char player) {
		board[i][j] = player;
		boolean win = checkIfWin(board, player);
		return win;
	}

	private static boolean losingMove(char[][] board, int i, int j, char cpu) {
		board[i][j] = cpu;
		boolean lose = checkIfWin(board, cpu);
		return lose;
	}

	private static boolean checkIfWin(char[][] board, char player) {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
				return true;
			}
			if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
				return true;
			}
		}
		return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
				(board[0][2] == player && board[1][1] == player && board[2][0] == player);
	}

	private static void printBoard(char[][] board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void printUtilityTable(int[][] utilityTable) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println("y=" + i + ", x=" + j + ",     utility=" + utilityTable[i][j]);
			}
		}
	}
}
