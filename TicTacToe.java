import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe{

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    // Prints out the tic tac toe board on terminal screen
    public static void printTicTacBoard(char[][] tictacBoard){
        for(char[] row: tictacBoard){
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] tictacBoard, int playerPosition, String user){

        char symbol = 'X';
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(playerPosition);
        } else if (user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(playerPosition);
        }

        switch(playerPosition){
            case 1:
                tictacBoard[1][4] = symbol;
                break;
            case 2:
                tictacBoard[1][8] = symbol;
                break;
            case 3:
                tictacBoard[1][12] = symbol;
                break;
            case 4:
                tictacBoard[3][4] = symbol;
                break;
            case 5:
                tictacBoard[3][8] = symbol;
                break;
            case 6:
                tictacBoard[3][12] = symbol;
                break;
            case 7:
                tictacBoard[5][4] = symbol;
                break;
            case 8:
                tictacBoard[5][8] = symbol;
                break;
            case 9:
                tictacBoard[5][12] = symbol;
                break;
            default:
                System.out.println("\n> INVALID ENTRY");
                break;
        }
    }

    public static String tictactoeWin(){

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List crossOne = Arrays.asList(1, 5, 9);
        List crossTwo = Arrays.asList(3, 5, 7);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List<List> winningCondition = new ArrayList<List>();

        winningCondition.add(topRow);
        winningCondition.add(midRow);
        winningCondition.add(botRow);

        winningCondition.add(crossOne);
        winningCondition.add(crossTwo);

        winningCondition.add(leftCol);
        winningCondition.add(midCol);
        winningCondition.add(rightCol);

        if (playerPositions.size() + cpuPositions.size() == 9) {
            return "\n> TIE";
        }

        for (List l : winningCondition){
            if (playerPositions.containsAll(l)){
                return "\n> PLAYER WIN";
            } else if (cpuPositions.containsAll(l)){
                return "\n> CPU WIN";
            }
        }
        return "";
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        /*
         TicTacToe Grid Design
         +---+---+---+
         | X | O | O |
         +---+---+---+
         | X | O | X |
         +---+---+---+
         | X | X | O |
         +---+---+---+
         */

        char [][] tictacBoard = {{' ',' ','+', '-', '-', '-','+', '-', '-', '-','+', '-', '-', '-','+'},
                                 {' ',' ','|', ' ', ' ', ' ','|', ' ', ' ', ' ','|', ' ', ' ', ' ','|'},
                                 {' ',' ','+', '-', '-', '-','+', '-', '-', '-','+', '-', '-', '-','+'},
                                 {' ',' ','|', ' ', ' ', ' ','|', ' ', ' ', ' ','|', ' ', ' ', ' ','|'},
                                 {' ',' ','+', '-', '-', '-','+', '-', '-', '-','+', '-', '-', '-','+'},
                                 {' ',' ','|', ' ', ' ', ' ','|', ' ', ' ', ' ','|', ' ', ' ', ' ','|'},
                                 {' ',' ','+', '-', '-', '-','+', '-', '-', '-','+', '-', '-', '-','+'}};

        System.out.println("");
        printTicTacBoard(tictacBoard);

        while(true){

            Scanner scan = new Scanner(System.in);

            System.out.print("\n> ENTER POSITION (1-9): ");
            int playerPosition = input.nextInt();
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
                System.out.println("\n> POSITION TAKEN! RETRY!");
                playerPosition = scan.nextInt();
            }

            placePiece(tictacBoard, playerPosition, "player");
            String result = tictactoeWin();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                cpuPosition = rand.nextInt(9) + 1;
            }
            placePiece(tictacBoard, cpuPosition, "cpu");

            // Call printTicTacBoard Method
            System.out.println("");
            printTicTacBoard(tictacBoard);

            result = tictactoeWin();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
        System.out.println("");
        printTicTacBoard(tictacBoard);
    }
}