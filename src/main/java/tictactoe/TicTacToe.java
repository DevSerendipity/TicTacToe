package tictactoe;
import java.util.Arrays;
import java.util.Scanner;
public class TicTacToe {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] grid = new String[9];
        int human;
        int computer;
        for (int i = 0; i < 9; i++) {
            grid[i] = String.valueOf(i + 1);
        }
        while (!(grid[0].equals("X") && grid[1].equals("X") && grid[2].equals("X") && grid[3].equals("X") && grid[4].equals("X") && grid[5].equals("X") && grid[6].equals("X") && grid[7].equals("X") && grid[8].equals("X"))) {
            System.out.println("Pick a number from 1 - 9 ");
            human = input.nextInt();
            computer = (int) (Math.random() * 9 + 1);
            while(grid[human-1].equals("X") || grid[human-1].equals("O") ){
                System.out.println("Try again that tile is taken");
                human = input.nextInt();
            }
            grid[human - 1] = "X";
            while(grid[computer-1].equals("X") || grid[computer-1].equals("O") || computer == human){
                if(Arrays.stream(grid).distinct().count() == 2){
                    System.out.println("Unique " + Arrays.stream(grid).distinct().count());
                    System.out.println(grid[0] + "|" + grid[1] + "|" + grid[2]);
                    System.out.println("-----");
                    System.out.println(grid[3] + "|" + grid[4] + "|" + grid[5]);
                    System.out.println("-----");
                    System.out.println(grid[6] + "|" + grid[7] + "|" + grid[8]);
                    System.exit(200);
                }
                System.out.println("Bot will try again that tile is taken");
                computer = (int) (Math.random() * 9 + 1);
            }
            grid[computer - 1] = "O";
            System.out.println(grid[0] + "|" + grid[1] + "|" + grid[2]);
            System.out.println("-----");
            System.out.println(grid[3] + "|" + grid[4] + "|" + grid[5]);
            System.out.println("-----");
            System.out.println(grid[6] + "|" + grid[7] + "|" + grid[8]);
            if(grid[0].equals("X") && grid[1].equals("X") && grid[2].equals("X")
            || grid[3].equals("X") && grid[4].equals("X") && grid[5].equals("X")
            || grid[6].equals("X") && grid[7].equals("X") && grid[8].equals("X")
            || grid[0].equals("X") && grid[3].equals("X") && grid[6].equals("X")
                    || grid[1].equals("X") && grid[4].equals("X") && grid[7].equals("X")
                    || grid[2].equals("X") && grid[5].equals("X") && grid[8].equals("X")
            || grid[0].equals("X") && grid[4].equals("X") && grid[8].equals("X")
                    || grid[2].equals("X") && grid[4].equals("X") && grid[6].equals("X")
            ){
                System.out.println("Human Won\nGame Over");
                System.exit(200);
            }else if(grid[0].equals("O") && grid[1].equals("O") && grid[2].equals("O")
                    || grid[3].equals("O") && grid[4].equals("O") && grid[5].equals("O")
                    || grid[6].equals("O") && grid[7].equals("O") && grid[8].equals("O")
                    || grid[0].equals("O") && grid[3].equals("O") && grid[6].equals("O")
                    || grid[1].equals("O") && grid[4].equals("O") && grid[7].equals("O")
                    || grid[2].equals("O") && grid[5].equals("O") && grid[8].equals("O")
                    || grid[0].equals("O") && grid[4].equals("O") && grid[8].equals("O")
                    || grid[2].equals("O") && grid[4].equals("O") && grid[6].equals("O")){
                System.out.println("Computer Won\nGame Over");
                System.exit(205);
            }
        }
    }
}