package tictactoe;

public class Winner {
    private static final int COLS = 3;
    private static final int ROWS = 3;
    private final Cell[][] grid = new Cell[ROWS][COLS];

    Cell[][] getGrid() {
        return grid;
    }

    boolean hasWinner() {
        if ( isWinner(Cell.HUMAN) ) {
            return true;
        } else {
            return isWinner(Cell.COMPUTER);
        }
    }

    int getCols() {
        return COLS;
    }

    void getWinner() {
        if ( isWinner(Cell.HUMAN) ) {
            System.out.println(State.HUMAN_WINNER);
        } else {
            System.out.println(State.COMPUTER_WINNER);
        }
    }

    boolean isWinner(Cell userCharacter) {
        return hasWonHorizontally(userCharacter) || hasWonVertically(userCharacter) || hasWonDiagonally(userCharacter);
    }

    private boolean hasWonDiagonally(Cell userCharacter) {
        return leftToRightDiagonalWin(userCharacter) || rightToLeftDiagonalWin(userCharacter);
    }

    private boolean rightToLeftDiagonalWin(Cell userCharacter) {
        for ( int rows = 0; rows < ROWS; rows++ ) {
            Cell cell = grid[COLS - rows - 1][rows];
            if ( cell != userCharacter ) {
                return false;
            }
        }
        return true;
    }

    private boolean leftToRightDiagonalWin(Cell userCharacter) {
        for ( int i = 0; i < ROWS; i++ ) {
            Cell cell = grid[i][i];
            if ( cell != userCharacter ) {
                return false;
            }
        }
        return true;
    }

    private boolean hasWonVertically(Cell userCharacter) {
        for ( int col = 0; col < COLS; col++ ) {
            boolean isColWon = true;
            for ( int row = 0; row < ROWS; row++ ) {
                Cell cellIndex = grid[row][col];
                if ( cellIndex != userCharacter ) {
                    isColWon = false;
                    break;
                }
            }

            if ( isColWon ) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWonHorizontally(Cell userCharacter) {
        for ( int row = 0; row < ROWS; row++ ) {
            boolean isRowWon = true;
            for ( int col = 0; col < COLS; col++ ) {
                Cell cellIndex = grid[row][col];
                if ( cellIndex != userCharacter ) {
                    isRowWon = false;
                    break;
                }
            }
            if ( isRowWon ) {
                return true;
            }
        }
        return false;
    }
}
