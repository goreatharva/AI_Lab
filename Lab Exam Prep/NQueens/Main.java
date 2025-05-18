import java.util.Scanner;

public class Main {
    static final int BOARD_SIZE = 8; // Fixed 8x8 board
    static int N; // Number of queens (user input)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of queens (N ≤ 8): ");
        N = sc.nextInt();

        if (N > BOARD_SIZE || N < 1) {
            System.out.println("Invalid N. Use 1 ≤ N ≤ 8.");
            return;
        }

        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

        if (solveNQueens(board, 0, 0)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists for N = " + N);
        }
    }

    static boolean solveNQueens(int[][] board, int row, int queensPlaced) {
        if (queensPlaced == N) return true; // All N queens placed
        if (row >= BOARD_SIZE) return false;

        for (int col = 0; col < BOARD_SIZE; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                if (solveNQueens(board, row + 1, queensPlaced + 1)) {
                    return true;
                }
                board[row][col] = 0; // backtrack
            }
        }
        // Skip row if no column works
        return solveNQueens(board, row + 1, queensPlaced);
    }

    static boolean isSafe(int[][] board, int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }
        // Check upper-left diagonal
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        // Check upper-right diagonal
        for (int i = row-1, j = col+1; i >= 0 && j < BOARD_SIZE; i--, j++) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }

    static void printBoard(int[][] board) {
        System.out.println("\nSolution for " + N + " Queens on 8x8 Board:");
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
    }
}

/*Input : 4 */