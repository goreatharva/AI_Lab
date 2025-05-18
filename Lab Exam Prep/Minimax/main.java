import java.util.*;

public class Main {
    static char[][] board = new char[3][3];
    static final char HUMAN = 'X';
    static final char AI = 'O';
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        System.out.println("You are X. AI is O (unbeatable using Minimax).");
        playGame();
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            Arrays.fill(board[i], ' ');
    }

    static void playGame() {
        printBoard();
        while (true) {
            humanTurn();
            printBoard();
            if (checkWin(HUMAN)) {
                System.out.println("You win!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            System.out.println("AI is making a move...");
            aiMinimaxTurn();
            printBoard();
            if (checkWin(AI)) {
                System.out.println("AI wins!");
                break;
            }
            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    static void humanTurn() {
        while (true) {
            System.out.println("Enter your move (row and column: 0 1 2):");
            System.out.print("Row: ");
            int row = sc.nextInt();
            System.out.print("Col: ");
            int col = sc.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = HUMAN;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    static void aiMinimaxTurn() {
        int bestScore = Integer.MIN_VALUE;
        int moveRow = -1, moveCol = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = AI;
                    int score = minimax(false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        moveRow = i;
                        moveCol = j;
                    }
                }
            }
        }

        board[moveRow][moveCol] = AI;
    }

    static int minimax(boolean isMaximizing) {
        if (checkWin(AI)) return 10;
        if (checkWin(HUMAN)) return -10;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? AI : HUMAN;
                    int score = minimax(!isMaximizing);
                    board[i][j] = ' ';
                    if (isMaximizing) {
                        bestScore = Math.max(score, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }

        return bestScore;
    }

    static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player &&
                 board[i][1] == player &&
                 board[i][2] == player) ||
                (board[0][i] == player &&
                 board[1][i] == player &&
                 board[2][i] == player))
                return true;
        }
        return (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) ||
               (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player);
    }

    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
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
}
