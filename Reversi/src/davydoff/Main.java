package davydoff;

import davydoff.Controller.InputControl;
import davydoff.View.BoardView;
import davydoff.View.GameDirection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Чтобы начать игру нажмите Enter, для выхода введите любой символ");
        int maxScore = 0;
        String input = in.nextLine();
        while ("".equals(input)) {
            GameDirection.printGameMode();
            int gameMode = InputControl.getGameMode();
            if (gameMode == 1) {
                Game.P2C();
                if (BoardView.getWinnerScore() > maxScore) {
                    maxScore = BoardView.getWinnerScore();
                    System.out.println("Поставлен рекорд по очкам, новый рекорд: " + maxScore);
                }
            } else {
                Game.P2P();
                if (BoardView.getWinnerScore() > maxScore) {
                    maxScore = BoardView.getWinnerScore();
                    System.out.println("Поставлен рекорд по очкам, новый рекорд: " + maxScore);
                }
                System.out.println("Чтобы сыграть еще раз нажмите ENTER, для выхода введите любой символ.");
                input = in.nextLine();
            }
        }
    }
}
