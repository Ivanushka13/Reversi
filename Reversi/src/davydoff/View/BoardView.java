package davydoff.View;

import davydoff.GameLogic.Logic;
import davydoff.Models.GameBoard;
import davydoff.Models.Chip;

import java.util.ArrayList;
import java.util.Objects;

public class BoardView {

    private static int winnerScore;

    // Метод, рисующий доску с возможными ходами игрока в консоль
    public static void printBoardWithMoves(GameBoard board, ArrayList<String> moves) {
        Chip[][] chips = board.getChips();
        System.out.println("  ---------------------------------");
        for (int i = 0; i < 8; ++i) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; ++j) {
                boolean flag = false;
                for (String s : moves) {
                    int[] move = Logic.translateMove(s);
                    if (move[0] == i && move[1] == j) {
                        System.out.print("| " + "◌" + " ");
                        flag = true;
                    }
                }
                if(!flag) {
                    System.out.print("| " + chips[i][j].color + " ");
                }
            }
            System.out.print("|");
            System.out.println();
            System.out.println("  ---------------------------------");
        }
        System.out.print(" ");
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for (char letter : letters) {
            System.out.print("\t" + letter);
        }
        System.out.println();
    }

    // Метод, выводящий количество очков каждого игрока
    public static void printScore(GameBoard board) {
        Chip[][] chips = board.getChips();
        System.out.println();
        int whiteScore = 0;
        int blackScore = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (Objects.equals(chips[i][j].color, "●")) {
                    whiteScore += 1;
                } else if (Objects.equals(chips[i][j].color, "◯")) {
                    blackScore += 1;
                }
            }
        }
        System.out.println("White: " + whiteScore);
        System.out.println("Black: " + blackScore);
        System.out.println();
    }

    // Метод, выводящий все возможные ходы игрока
    public static void printMoves(ArrayList<String> moves) {
        System.out.println("Выберите ход:");
        for(int i = 0; i < moves.size(); ++i) {
            System.out.println((i + 1) + "." + " " + moves.get(i));
        }
        System.out.println();
    }

    // Метод, выводящий победителя
    public static void printWinner(GameBoard board) {
        Chip[][] chips = board.getChips();
        System.out.println();
        int whiteScore = 0;
        int blackScore = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (Objects.equals(chips[i][j].color, "●")) {
                    whiteScore += 1;
                } else if (Objects.equals(chips[i][j].color, "◯")) {
                    blackScore += 1;
                }
            }
        }
        if(whiteScore > blackScore) {
            winnerScore = whiteScore;
            System.out.println("Winner: White");
        } else if(blackScore > whiteScore) {
            winnerScore = blackScore;
            System.out.println("Winner: Black");
        } else {
            System.out.println("Draw!");
        }
    }

    public static int getWinnerScore() {
        return winnerScore;
    }
}
