package tictactoe;

import java.util.*;

// TODO Naming conventions | Work on naming not to name them regarding the topic that they cover, name them regarding what the player can expect them to do
/*
IMPROVEMENTS TO BE MADE LATER
enum Cell {
  NONE(' '), X('X'), O('O');
  private final char c;
  Side(char c){
    this.c = c;
  }

  public char getChar(){
    return c;
  }
}

Change your enum Player to this.
enum State {
  WINNER_X, WINNER_O, TIE, RUNNING
}
record Coord(int row, int col){}
interface Player {
  Coord play(Cell[][] board);
}
class TicTacToe {
  private final Player p1, p2;
  private final Cell[][] board;
  ...
  TicTacToe(Player p1, Player p2){
    this.p1 = p1;
    this.p2 = p2;
    this.board = ...;
  }

  Cell[][] getCurrentBoard(){};
  boolean playOnce(){};
  State getState();
}
 */
enum Player {
    HUMAN("X"),
    COMPUTER("O");

    private final String symbol;

    Player(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }

}

public class TicTacToe {

    private final Player[][] grid = new Player[ROWS][COLS];
    private final Scanner input = new Scanner(System.in);
    private static final int COLS = 3;
    private static final int ROWS = 3;

    public TicTacToe(){
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
            if(hasWinner()){
                displayGrid();
                break;
            }
            runComputerTurn();
            if(hasWinner()){
                displayGrid();
                break;
            }
            displayGrid();
        }
        getWinner();
    }

    private boolean hasWinner() {
        if (isWinner(Player.HUMAN)) {
            return true;
        } else return isWinner(Player.COMPUTER);
    }

    private void runHumansTurn() {
        System.out.println("Pick a number from 1 - 9 ");
        int humanSelectedPosition = input.nextInt();
        while (isCellTaken(getTilePositioning(humanSelectedPosition)) || isRightInput(humanSelectedPosition)) {
            System.out.println("Try again ");
            humanSelectedPosition = input.nextInt();
        }
        grid[(humanSelectedPosition - 1 )/3][(humanSelectedPosition - 1) % 3] = Player.HUMAN;
    }

    private Player getTilePositioning(int humanSelectedPosition) {
        return grid[(humanSelectedPosition - 1) / 3][(humanSelectedPosition - 1) % 3];
    }

    private boolean isRightInput(int humanSelectedPosition) {
        return humanSelectedPosition > 9 || humanSelectedPosition < 1;
    }

    private boolean isDraw() {
        for (Player[] players : grid) {
            for (int i = 0; i < grid.length; i++) {
                if (players[i] == null || players[i].getSymbol().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
    private void displayGrid() {
        for (Player[] players : grid) {
            for (int index = 0; index < grid.length; index++) {
                String symbolText = players[index] == null ? " " : players[index].getSymbol();

                String delimiter = isLastRowIndex(index % COLS) ? "\n" : "|";

                System.out.print(symbolText + delimiter);
            }
        }
    }


    private void runComputerTurn() {
        int computerSelectedPosition = (int) (Math.random() * 9 + 1);
        while (isCellTaken(getTilePositioning(computerSelectedPosition))) {
            computerSelectedPosition = (int) (Math.random() * 9 + 1);
        }
        grid[(computerSelectedPosition - 1 )/3][(computerSelectedPosition - 1) % 3] = Player.COMPUTER;
    }

    private void getWinner() {
        if (isWinner(Player.HUMAN)) {
            System.out.println("Player won");
        } else {
            System.out.println("Computer won");
        }
    }

    private boolean isCellTaken(Player playerTile) {
        return playerTile != null;
    }

    private boolean isLastRowIndex(int col) {
        return col == 2;
    }

    private boolean isWinner(Player userCharacter) {
        return hasWonHorizontally(userCharacter)
                || hasWonVertically(userCharacter)
                || hasWonDiagonally(userCharacter);
    }

    private boolean hasWonDiagonally(Player userCharacter) {
        return leftToRightDiagonalWin(userCharacter) || rightToLeftDiagonalWin(userCharacter);
    }

    private boolean rightToLeftDiagonalWin(Player userCharacter) {
        for (int rows = 0; rows < ROWS; rows++) {
            Player tile = grid[COLS - rows -1] [rows];
            if (tile != userCharacter) {
                return false;
            }
        }
        return true;
    }

    private boolean leftToRightDiagonalWin(Player userCharacter) {
        for (int i = 0; i < ROWS; i++) {
            Player tile = grid[i][i];
            if (tile != userCharacter) {
                return false;
            }
        }
        return true;
    }

    private boolean hasWonVertically(Player userCharacter) {
        for (int col = 0; col < COLS; col++) {
            boolean isColWon = true;
            for (int row = 0; row < ROWS; row++) {
                Player tileIndex = grid[row] [col];
                if (tileIndex != userCharacter) {
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

    private boolean hasWonHorizontally(Player userCharacter) {
        for (int row = 0; row < ROWS; row++) {
            boolean isRowWon = true;
            for (int col = 0; col < COLS; col++) {
                Player tileIndex = grid[row][col];
                if (tileIndex != userCharacter) {
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