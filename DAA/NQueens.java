import java.util.Scanner;

public class NQueens {
    static int N = 8; // Fixed board size of 8 for 8-Queens problem

    // Function to print the chessboard
    static void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Function to check if a Queen can be placed at a given position (row, col)
    static boolean isSafe(int board[][], int row, int col) {
        int i, j;

        // Check this row on the left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left side
        for (i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Function to solve the N Queens problem using backtracking
    static boolean solveNQueens(int board[][], int col) {
        if (col >= N) {
            return true;
        }

        // Try placing a Queen in all rows one by one for the current column
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQueens(board, col + 1)) {
                    return true;
                }

                board[i][col] = 0; // Backtrack
            }
        }

        return false;
    }

    // Main function to solve the 8 Queens problem with the first Queen placed
    static void nQueensWithFirstQueenPlaced(int row, int col) {
        int[][] board = new int[N][N];
        board[row][col] = 1; // Place the first Queen at the specified position

        if (solveNQueens(board, col + 1)) { // Start solving from the next column
            System.out.println("Solution exists:");
            printSolution(board);
        } else {
            System.out.println("Solution does not exist.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the row position for the first Queen (0 to 7): ");
        int row = scanner.nextInt();
        System.out.print("Enter the column position for the first Queen (0 to 7): ");
        int col = scanner.nextInt();

        if (row < 0 || row >= N || col < 0 || col >= N) {
            System.out.println("Invalid position. Please enter values between 0 and 7.");
        } else {
            nQueensWithFirstQueenPlaced(row, col);
        }

        scanner.close();
    }
}
