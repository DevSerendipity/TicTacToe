package tictactoe;

import java.util.*;

// TODO Naming conventions | Work on naming not to name them regarding the topic that they cover, name them regarding what the player can expect them to do
enum Cell {
    HUMAN("X"),
    COMPUTER("O");

    private final String symbol;

    Cell(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }

}

public class TicTacToe {

    private final Cell[][] grid = new Cell[ROWS][COLS];
    private final Scanner input = new Scanner(System.in);
    private static final int COLS = 3;
    private static final int ROWS = 3;

    public TicTacToe() {
        displayGrid();
    }

    public void start() {
        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            runHumansTurn();
            if (isDraw()) {
                displayGrid();
                System.out.println("DRAW");
                return;
            }
            if (hasWinner()) {
                displayGrid();
                break;
            }
            runComputerTurn();
            if (hasWinner()) {
                displayGrid();
                break;
            }
            displayGrid();
        }
        getWinner();
    }

    private boolean hasWinner() {
        if (isWinner(Cell.HUMAN)) {
            return true;
        } else return isWinner(Cell.COMPUTER);
    }

    private void runHumansTurn() {
        System.out.println("Pick a number from 1 - 9 ");
        int humanSelectedPosition = input.nextInt();
        while (isCellTaken(getCellPositioning(humanSelectedPosition)) || isRightInput(humanSelectedPosition)) {
            System.out.println("Try again ");
            humanSelectedPosition = input.nextInt();
        }
        grid[(humanSelectedPosition - 1) / 3][(humanSelectedPosition - 1) % 3] = Cell.HUMAN;
    }

    private Cell getCellPositioning(int humanSelectedPosition) {
        return grid[(humanSelectedPosition - 1) / 3][(humanSelectedPosition - 1) % 3];
    }

    private boolean isRightInput(int humanSelectedPosition) {
        return humanSelectedPosition > 9 || humanSelectedPosition < 1;
    }

    private boolean isDraw() {
        for (Cell[] players : grid) {
            for (int i = 0; i < grid.length; i++) {
                if (players[i] == null || players[i].getSymbol().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void displayGrid() {
        for (Cell[] players : grid) {
            for (int index = 0; index < grid.length; index++) {
                String symbolText = players[index] == null ? " " : players[index].getSymbol();

                String delimiter = isLastRowIndex(index % COLS) ? "\n" : "|";

                System.out.print(symbolText + delimiter);
            }
        }
    }


    private void runComputerTurn() {
        int computerSelectedPosition = (int) (Math.random() * 9 + 1);
        while (isCellTaken(getCellPositioning(computerSelectedPosition))) {
            computerSelectedPosition = (int) (Math.random() * 9 + 1);
        }
        grid[(computerSelectedPosition - 1) / 3][(computerSelectedPosition - 1) % 3] = Cell.COMPUTER;
    }

    private void getWinner() {
        if (isWinner(Cell.HUMAN)) {
            System.out.println("Player won");
        } else {
            System.out.println("Computer won");
        }
    }

    private boolean isCellTaken(Cell playerCell) {
        return playerCell != null;
    }

    private boolean isLastRowIndex(int col) {
        return col == 2;
    }

    private boolean isWinner(Cell userCharacter) {
        return hasWonHorizontally(userCharacter)
                || hasWonVertically(userCharacter)
                || hasWonDiagonally(userCharacter);
    }

    private boolean hasWonDiagonally(Cell userCharacter) {
        return leftToRightDiagonalWin(userCharacter) || rightToLeftDiagonalWin(userCharacter);
    }

    private boolean rightToLeftDiagonalWin(Cell userCharacter) {
        for (int rows = 0; rows < ROWS; rows++) {
            Cell cell = grid[COLS - rows - 1][rows];
            if (cell != userCharacter) {
                return false;
            }
        }
        return true;
    }

    private boolean leftToRightDiagonalWin(Cell userCharacter) {
        for (int i = 0; i < ROWS; i++) {
            Cell cell = grid[i][i];
            if (cell != userCharacter) {
                return false;
            }
        }
        return true;
    }

    private boolean hasWonVertically(Cell userCharacter) {
        for (int col = 0; col < COLS; col++) {
            boolean isColWon = true;
            for (int row = 0; row < ROWS; row++) {
                Cell cellIndex = grid[row][col];
                if (cellIndex != userCharacter) {
                    isColWon = false;
                    break;
                }
            }

            if (isColWon) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWonHorizontally(Cell userCharacter) {
        for (int row = 0; row < ROWS; row++) {
            boolean isRowWon = true;
            for (int col = 0; col < COLS; col++) {
                Cell cellIndex = grid[row][col];
                if (cellIndex != userCharacter) {
                    isRowWon = false;
                    break;
                }
            }
            if (isRowWon) {
                return true;
            }
        }
        return false;
    }
}