import java.util.*;

public class Main {
    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);
    static final char HUMAN = 'X';
    static final char AI = 'O';
    static Random rand = new Random();

    public static void main(String[] args) {
        initializeBoard();
        System.out.println("You are X. AI is O (random moves).");
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
            aiRandomTurn();
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
            break;  // valid move, exit loop
        }
        // else silently retry without any error message
    }
}

    static void aiRandomTurn() {
        List<int[]> available = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    available.add(new int[] { i, j });

        if (!available.isEmpty()) {
            int[] move = available.get(rand.nextInt(available.size()));
            board[move[0]][move[1]] = AI;
        }
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
        if ((board[0][0] == player &&
             board[1][1] == player &&
             board[2][2] == player) ||
            (board[0][2] == player &&
             board[1][1] == player &&
             board[2][0] == player))
            return true;
        return false;
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

/*Input : 
0 0
0 1
0 2
1 0
1 1
1 2
2 0
2 1
2 2
 */