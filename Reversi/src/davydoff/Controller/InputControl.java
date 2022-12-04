package davydoff.Controller;

import java.util.Objects;
import java.util.Scanner;

public class InputControl {
    private static final Scanner in = new Scanner(System.in);

    // Метод, проверяющий корректное значение ввода режима игры
    public static int getGameMode() {
        while (true) {
            String input = in.nextLine();
            if(!Objects.equals(input, "1") && !Objects.equals(input, "2")) {
                System.out.println("Некорректный ввод, введите число 1 или 2");
            } else {
                return Integer.parseInt(input);
            }
        }
    }

    // Метод, проверяющий корректный ввод хода
    public static int getMoveFromConsole(int movesCount) {
        while (true) {
            try {
                int move = Integer.parseInt(in.nextLine());
                if(move < 1 || move > movesCount) {
                    System.out.println();
                    System.out.println("Некорректный ввод, введите номер хода от 1 до " + movesCount);
                    System.out.println();
                } else {
                    return move;
                }
            } catch (NumberFormatException ex) {
                System.out.println();
                System.out.println("Некорректный ввод, введите номер хода от 1 до " + movesCount);
                System.out.println();
            }
        }
    }
}
