import java.util.*;

public class Main {
    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            Arrays.fill(board[i], ' ');
    }

    static void playGame() {
        char currentPlayer = 'X';
        int moves = 0;

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter row and column (0, 1, or 2):");

            int row, col;

            while (true) {
                System.out.print("Row: ");
                row = sc.nextInt();
                System.out.print("Col: ");
                col = sc.nextInt();

                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    if (board[row][col] == ' ') {
                        board[row][col] = currentPlayer;
                        moves++;
                        break;
                    } else {
                        System.out.println("Cell already taken. Try again.");
                    }
                } else {
                    System.out.println("Invalid input. Try again.");
                }
            }

            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (moves == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    static boolean checkWin(char player) {
        // Rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player &&
                 board[i][1] == player &&
                 board[i][2] == player) ||
                (board[0][i] == player &&
                 board[1][i] == player &&
                 board[2][i] == player))
                return true;
        }

        // Diagonals
        if ((board[0][0] == player &&
             board[1][1] == player &&
             board[2][2] == player) ||
            (board[0][2] == player &&
             board[1][1] == player &&
             board[2][0] == player))
            return true;

        return false;
    }
}


/*Input:
0 0
1 1
1 0
2 1
2 0
 */