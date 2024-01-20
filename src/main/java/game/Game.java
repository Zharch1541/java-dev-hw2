package game;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private byte input;
    private final char[] board;
    boolean gameOver = false;

    public Game() {
        board = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    }
    public static void printBoard(char[] board) {
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + board[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
                if (i < 6) {
                    System.out.println("-----------");
                }
            } else {
                System.out.print("|");
            }
        }
        System.out.println();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    private void makeComputerMove() {

        // Логіка для ходу комп'ютера
        while (true) {
            int rand = (int) (Math.random() * 9);
            if (board[rand] != 'X' && board[rand] != 'O') {
                board[rand] = 'O';
                break;
            }
        }
    }

    private void makeMove(char playerSymbol) {
        // Логіка для ходу гравця
        if (board[input - 1] == 'X' || board[input - 1] == 'O') {
            System.out.println("That one is already in use. Enter another.");
        } else {
            board[input - 1] = playerSymbol;
        }
    }

    private boolean checkWin(char playerSymbol) {

        for (int i = 0; i < 3; i++) {
            // Перевірка по горизонталі
            if (board[i * 3] == playerSymbol && board[i * 3 + 1] == playerSymbol && board[i * 3 + 2] == playerSymbol) {
                return true;
            }

            // Перевірка по вертикалі
            if (board[i] == playerSymbol && board[i + 3] == playerSymbol && board[i + 6] == playerSymbol) {
                return true;
            }
        }

        // Перевірка по діагоналям
        if (board[0] == playerSymbol && board[4] == playerSymbol && board[8] == playerSymbol) {
            return true;
        }
        return board[2] == playerSymbol && board[4] == playerSymbol && board[6] == playerSymbol;
    }

    private boolean checkAndPrintResult(char playerSymbol) {
        if (checkWin(playerSymbol)) {
            printBoard(board);
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (checkWin('O')) {
            printBoard(board);
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;

        } else if (isBoardFull()) {
            printBoard(board);
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        }

        return false;
    }


    public void startGame() {

        while (true) {
            printBoard(board);
            System.out.print("Enter box number to select. Enjoy!\n");
            input = scanner.nextByte();

                if (input > 0 && input < 10) {
                    // Хід гравця 'X'
                    char currentPlayerSymbol = 'X';
                    makeMove(currentPlayerSymbol);

                    gameOver = checkAndPrintResult(currentPlayerSymbol);

                    // Хід комп'ютера
                    if (!gameOver) {
                        currentPlayerSymbol = 'O';
                        makeComputerMove();
                    }

                    // Перевірка і вивід результату
                    if (checkAndPrintResult(currentPlayerSymbol)) {
                        break;
                    }
                } else {
                    System.out.println("Invalid input. Enter again.");
                }
        }
    }
}
