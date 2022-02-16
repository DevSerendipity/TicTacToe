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

enum State {
    WINNER_HUMAN, WINNER_COMPUTER, TIE
}

public class TicTacToe extends Winner {

    private final Scanner input = new Scanner(System.in);

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
                System.out.println(State.TIE);
                return;
            }
            if (hasWinner()) {
                break;
            }
            runComputerTurn();
            if (hasWinner()) {
                break;
            }
            displayGrid();
        }
        displayGrid();
        getWinner();
    }

    private boolean isDraw() {
        for (Cell[] players : getGrid()) {
            for (int i = 0; i < getGrid().length; i++) {
                if (players[i] == null || players[i].getSymbol().equals(" ") || isWinner(Cell.HUMAN)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void runHumansTurn() {
        System.out.println("Pick a number from 1 - 9 ");
        int humanSelectedPosition = input.nextInt();
        while (isCellTaken(getCellPositioning(humanSelectedPosition)) || isRightInput(humanSelectedPosition)) {
            System.out.println("Try again ");
            humanSelectedPosition = input.nextInt();
        }
        getGrid()[(humanSelectedPosition - 1) / 3][(humanSelectedPosition - 1) % 3] = Cell.HUMAN;
    }

    private Cell getCellPositioning(int humanSelectedPosition) {
        return getGrid()[(humanSelectedPosition - 1) / 3][(humanSelectedPosition - 1) % 3];
    }

    private boolean isRightInput(int humanSelectedPosition) {
        return humanSelectedPosition > 9 || humanSelectedPosition < 1;
    }

    private void displayGrid() {
        for (Cell[] players : getGrid()) {
            for (int index = 0; index < getGrid().length; index++) {
                String symbolText = players[index] == null ? " " : players[index].getSymbol();

                String delimiter = isLastRowIndex(index % getCols()) ? "\n" : "|";

                System.out.print(symbolText + delimiter);
            }
        }
    }

    private void runComputerTurn() {
        int computerSelectedPosition = (int) (Math.random() * 9 + 1);
        while (isCellTaken(getCellPositioning(computerSelectedPosition))) {
            computerSelectedPosition = (int) (Math.random() * 9 + 1);
        }
        getGrid()[(computerSelectedPosition - 1) / 3][(computerSelectedPosition - 1) % 3] = Cell.COMPUTER;
    }

    private boolean isCellTaken(Cell playerCell) {
        return playerCell != null;
    }

    private boolean isLastRowIndex(int col) {
        return col == 2;
    }
}