package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    private static final String PLAYER_CHARACTER = "X";
    private static final String COMPUTER_CHARACTER = "O";
    private static final String[] grid = new String[9];

    static {
        for (int i = 0; i < grid.length; i++) {
            grid[i] = String.valueOf(i + 1);
        }
    }

    public TicTacToe(){
        gameLoop();
    }

    private void gameLoop() {
        Scanner input = new Scanner(System.in);
        int player;
        int computer;
        while (Arrays.stream(grid).distinct().count() > 2) {
            System.out.println("Pick a number from 1 - 9 ");
            player = input.nextInt();
            computer = (int) (Math.random() * 9 + 1);

            while(hasTiles(grid[player - 1])){
                System.out.println("Try again that tile is taken");
                player = input.nextInt();
            }
            grid[player - 1] = PLAYER_CHARACTER;

            while(hasTiles(grid[computer-1]) || computer == player){
                if(Arrays.stream(grid).distinct().count() == 2){
                    gridView();
                    System.exit(200);
                }
                computer = (int) (Math.random() * 9 + 1);
            }

            grid[computer - 1] = COMPUTER_CHARACTER;
            gridView();

            winner();
        }
    }

    private boolean hasTiles(String tile) {
        return tile.equals("X") || tile.equals("O");
    }

    private void gridView() {
        System.out.println(grid[0] + "|" + grid[1] + "|" + grid[2]);
        System.out.println("-----");
        System.out.println(grid[3] + "|" + grid[4] + "|" + grid[5]);
        System.out.println("-----");
        System.out.println(grid[6] + "|" + grid[7] + "|" + grid[8]);
    }

    private void winner() {
        if(isWinner(PLAYER_CHARACTER)){
            System.out.println("Player Won\nGame Over");
            System.exit(200);
        }else if(isWinner(COMPUTER_CHARACTER)){
            System.out.println("Computer Won\nGame Over");
            System.exit(205);
        }
    }

    private boolean isWinner(String userCharacter) {
        return hasWonHorizontally(userCharacter)
                || hasWonVertically(userCharacter)
                || hasWonDiagonally(userCharacter);
    }

    private boolean hasWonDiagonally(String userCharacter) {
        return grid[0].equals(userCharacter) && grid[4].equals(userCharacter) && grid[8].equals(userCharacter)
                || grid[2].equals(userCharacter) && grid[4].equals(userCharacter) && grid[6].equals(userCharacter);
    }

    private boolean hasWonVertically(String userCharacter) {
        return grid[0].equals(userCharacter) && grid[3].equals(userCharacter) && grid[6].equals(userCharacter)
                || grid[1].equals(userCharacter) && grid[4].equals(userCharacter) && grid[7].equals(userCharacter)
                || grid[2].equals(userCharacter) && grid[5].equals(userCharacter) && grid[8].equals(userCharacter);
    }

    private boolean hasWonHorizontally(String userCharacter) {
        return grid[0].equals(userCharacter) && grid[1].equals(userCharacter) && grid[2].equals(userCharacter)
                || grid[3].equals(userCharacter) && grid[4].equals(userCharacter) && grid[5].equals(userCharacter)
                || grid[6].equals(userCharacter) && grid[7].equals(userCharacter) && grid[8].equals(userCharacter);
    }
}