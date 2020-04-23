import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//  ============== Variables ==============
        Scanner scanner = new Scanner(System.in);

        char[][] field = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        char nextSign = 'X';

//        Coordinates
        int x;
        int y;

//      ============== Game ==============

        displayField(field);

        do {
            System.out.print("Enter the coordinates:");

            String[] xy = scanner.nextLine().split(" ");

            if (xy.length == 2) {

                Scanner scanX = new Scanner(xy[0]);
                Scanner scanY = new Scanner(xy[1]);

                if (scanX.hasNextInt() && scanY.hasNextInt()) {

//                  ~~~~~ Checking for a range of 1 to 3 ~~~~~
                    if (3 >= Integer.parseInt(xy[0]) && Integer.parseInt(xy[0]) >= 1 &&
                            3 >= Integer.parseInt(xy[1]) && Integer.parseInt(xy[1]) >= 1) {

                        x = Integer.parseInt(xy[0]);
                        y = Integer.parseInt(xy[1]);

                        if (field[3 - y][x - 1] == ' ') {
                            x = Integer.parseInt(xy[0]);
                            y = Integer.parseInt(xy[1]);

                            field[3 - y][x - 1] = nextSign;

                            displayField(field);

                            nextSign = (nextSign == 'X') ? 'O' : 'X';
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }

                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }

                } else {
                    System.out.println("You should enter numbers!");
                }

            } else {
                System.out.println("Enter two numbers!");
            }
        } while (isWinAndGameState(field)[0].equals("true"));

        System.out.println(isWinAndGameState(field)[1]);

    }

    public static void displayField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %s %s %s |\n", field[i][0], field[i][1], field[i][2]);
        }
        System.out.println("---------");
    }

//    return array of Strings, where
//      first element is which define ending of the game and
//      the second element is the game state
    public static String[] isWinAndGameState(char[][] field) {
        boolean Xwins = false;
        boolean Owins = false;

        boolean isEmptyCellExists = false;

        int countX = 0;
        int countO = 0;

        String gameState = "";

        for (char[] arr : field) {
            for (char val : arr) {
                if (val == 'X') {
                    countX++;
                } else if (val == 'O') {
                    countO++;
                } else if (val == ' ') {
                    isEmptyCellExists = true;
                }
            }
        }

        for (int i = 0; i < field.length; i++) {
            if (field[i][0] != ' ' &&
                    field[i][0] == field[i][1] &&
                    field[i][0] == field[i][2]) {

                gameState = String.format("%s wins", field[i][0]);

                if (field[i][0] == 'X') Xwins = true;
                else if (field[i][0] == 'O') Owins = true;
            }
            if (field[0][i] != ' ' &&
                    field[0][i] == field[1][i] &&
                    field[0][i] == field[2][i]) {

                gameState = String.format("%s wins", field[0][i]);

                if (field[0][i] == 'X') Xwins = true;
                else if (field[0][i] == 'O') Owins = true;
            }
        }

        if (field[0][0] != ' ' &&
                field[0][0] == field[1][1] &&
                field[0][0] == field[2][2]) {

            gameState = String.format("%s wins", field[0][0]);

            if (field[0][0] == 'X') Xwins = true;
            else if (field[0][0] == 'O') Owins = true;
        } else if (field[0][2] != ' ' &&
                field[0][2] == field[1][1] &&
                field[0][2] == field[2][0]) {

            gameState = String.format("%s wins", field[0][2]);

            if (field[0][2] == 'X') Xwins = true;
            else if (field[0][2] == 'O') Owins = true;
        }

        if (!Owins && !Xwins && isEmptyCellExists) gameState = "Game not finished";
        if (!Owins && !Xwins && !isEmptyCellExists) {
            gameState = "Draw";
            Xwins = true; // For setting game over
        }
        if (Math.abs(countO - countX) > 1 || (Owins && Xwins)) {
            gameState = "Impossible";
            Xwins = true; // For setting game over
        }

        return new String[] {String.valueOf(!Owins && !Xwins), gameState};
    }
}
