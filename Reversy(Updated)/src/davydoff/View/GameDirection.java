package davydoff.View;

public class GameDirection {
    // Вывод варианта режима игры
    public static void printGameMode() {
        System.out.println("Выберите режим игры:");
        System.out.println("1. Игра с компьютером (легкий)");
        System.out.println("2. Игрок против игрока");
    }

    // При завершении игры
    public static void printEndGame() {
        System.out.println("Для начала новой игры нажмите ENTER, для выхода введите любой символ");
    }
}
