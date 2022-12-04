package davydoff.Models;

import davydoff.Models.Chip;

public class GameBoard {
    private final Chip[][] chips;

    public GameBoard() {
        chips = new Chip[8][8];
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                chips[i][j] = new Chip(i, j, " ");
            }
        }
        chips[4][3].color = "◯";
        chips[3][4].color = "◯";
        chips[3][3].color = "●";
        chips[4][4].color = "●";
    }

    // Получение фишек
    public Chip[][] getChips() {
        return chips;
    }
}
