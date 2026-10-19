
import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeBoard();

        char player = 'X';
        int moves = 0;

        while (true) {
            printBoard();
            System.out.print("Player " + player
                    + ", enter row and column (1-3): ");

            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position! Try again.");
                continue;
            }

            if (board[row][col] != ' ') {
                System.out.println("Cell occupied! Try again.");
                continue;
            }

            board[row][col] = player;
            moves++;

            if (hasWon(player)) {
                printBoard();
                System.out.println("Player " + player + " wins!");
                break;
            }

            if (moves == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            player = (player == 'X') ? 'O' : 'X';
        }

        sc.close();
    }

    static void initializeBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    static void printBoard() {
        System.out.println("\n  1   2   3");
        for (int r = 0; r < 3; r++) {
            System.out.print((r + 1) + " ");
            for (int c = 0; c < 3; c++) {
                System.out.print(board[r][c]);
                if (c < 2) System.out.print(" | ");
            }
            System.out.println();
            if (r < 2) System.out.println(" ---+---+---");
        }
    }

    static boolean hasWon(char p) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p
                    && board[i][2] == p) return true;

            if (board[0][i] == p && board[1][i] == p
                    && board[2][i] == p) return true;
        }

        return (board[0][0] == p && board[1][1] == p
                    && board[2][2] == p)
                || (board[0][2] == p && board[1][1] == p
                    && board[2][0] == p);
    }
}
