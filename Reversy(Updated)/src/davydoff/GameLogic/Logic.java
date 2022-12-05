package davydoff.GameLogic;

import davydoff.Models.GameBoard;
import davydoff.Models.Chip;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Logic {

    // DELTAX и DELTAY это массивы, соответствующие пары элементов которых сдвигают координаты клетки
    // в соответствующем направлении
    private static final int[] DELTAX = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] DELTAY = {-1, -1, -1, 0, 0, 1, 1, 1};

    // Метод для определения возможности хода на соответствующую клетку
    public static boolean isLegalForMove(int x, int y, String color, Chip[][] chips) {
        for (int i = 0; i < DELTAX.length; ++i) {
            boolean sawOther = false;
            int x0 = x;
            int y0 = y;
            for (int j = 0; j < 8; ++j) {
                x0 += DELTAX[i];
                y0 += DELTAY[i];
                if (!inBound(x0, y0)) {
                    break;
                }
                if (Objects.equals(chips[x0][y0].color, " ")) {
                    break;
                } else if (!Objects.equals(chips[x0][y0].color, color)) {
                    sawOther = true;
                } else if (sawOther) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    // Метод, для получения всех возможных ходов
    public static ArrayList<String> getLegalMoves(GameBoard board, String color) {
        ArrayList<String> moves = new ArrayList<>();
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        Chip[][] chips = board.getChips();
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (Objects.equals(chips[i][j].color, " ") && isLegalForMove(i, j, color, chips)) {
                    moves.add(letters[j] + String.valueOf(i + 1));
                }
            }
        }
        return moves;
    }

    // Метод, выполняющий ход на выбранную позицию, и "переворачивающий" соответственные
    // закрытые фишки противника
    public static void makeMove(int x, int y, String color, GameBoard board) {
        Chip[][] chips = board.getChips();
        chips[x][y].color = color;
        List<Chip> chipsToFlip = new ArrayList<>();
        for(int i = 0; i < DELTAX.length; ++i) {
            int x0 = x;
            int y0 = y;
            for(int j = 0; j < 8; ++j) {
                x0 += DELTAX[i];
                y0 += DELTAY[i];
                if(!inBound(x0, y0)) {
                    break;
                }
                if(Objects.equals(chips[x0][y0].color, " ")) {
                    break;
                } else if(!Objects.equals(chips[x0][y0].color, color)) {
                    chipsToFlip.add(chips[x0][y0]);
                } else {
                    for (Chip chip : chipsToFlip) {
                        chips[chip.x][chip.y].color = color;
                    }
                    break;
                }
            }
            chipsToFlip.clear();
        }
    }

    // Метод для перевода хода вида "БукваЦифра" в координаты
    public static int[] translateMove(String move) {
        int[] coordinates = new int[2];
        if(move.charAt(0) == 'A') {
            coordinates[1] = 0;
        } else if(move.charAt(0) == 'B') {
            coordinates[1] = 1;
        } else if(move.charAt(0) == 'C') {
            coordinates[1] = 2;
        } else if(move.charAt(0) == 'D') {
            coordinates[1] = 3;
        } else if(move.charAt(0) == 'E') {
            coordinates[1] = 4;
        } else if(move.charAt(0) == 'F') {
            coordinates[1] = 5;
        } else if(move.charAt(0) == 'G') {
            coordinates[1] = 6;
        } else {
            coordinates[1] = 7;
        }
        coordinates[0] = Integer.parseInt(String.valueOf(move.charAt(1))) - 1;
        return coordinates;
    }

    // Метод, лежат ли координаты клетки внутри поля
    private static boolean inBound(int x, int y) {
        return (x >= 0) && (x < 8) && (y >= 0) && (y < 8);
    }

    // Метод, для осуществления хода Бота (легкий уровень)
    public static void makeBotMove(GameBoard board) {
        ArrayList<String> moves = getLegalMoves(board, "●");
        double bestCellValue = 0;
        int[] bestCellCoordinates = new int[2];
        if(moves.size() == 0) {
            System.out.println("У бота нет ходов\n");
            return;
        }
        for(String move: moves) {
            int[] coordinates = translateMove(move);
            double value = 0;
            if(coordinates[0] == 0 || coordinates[0] == 7) {
                value += 2;
            } else if(coordinates[1] == 0 || coordinates[1] == 7) {
                value += 2;
            } else {
                value += 1;
            }
            if(coordinates[0] == 0 && (coordinates[1] == 0 || coordinates[1] == 7)) {
                value += 0.8;
            } else if(coordinates[0] == 7 && (coordinates[1] == 0 || coordinates[1] == 7)) {
                value += 0.8;
            } else if(value == 2) {
                value += 0.4;
            }
            value += checkCellValue(coordinates[0], coordinates[1], "●", board);
            if(value > bestCellValue) {
                bestCellValue = value;
                bestCellCoordinates[0] = coordinates[0];
                bestCellCoordinates[1] = coordinates[1];
            }
        }
        makeMove(bestCellCoordinates[0], bestCellCoordinates[1], "●", board);
    }

    // Метод для определения ценности клетки, на которую может сходить Бот
    public static int checkCellValue(int x, int y, String color, GameBoard board) {
        Chip[][] chips = board.getChips();
        int cellValue = 0;
        for(int i = 0; i < DELTAX.length; ++i) {
            int x0 = x;
            int y0 = y;
            for(int j = 0; j < 8; ++j) {
                x0 += DELTAX[i];
                y0 += DELTAY[i];
                if(!inBound(x0, y0)) {
                    break;
                }
                if(Objects.equals(chips[x0][y0].color, " ")) {
                    break;
                } else if(!Objects.equals(chips[x0][y0].color, color)) {
                    cellValue += 1;
                } else {
                    break;
                }
            }
        }
        return cellValue;
    }

    // Метод, определяющий заполненность доски
    public static boolean boardFilled(GameBoard board) {
        Chip[][] chips = board.getChips();
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if(Objects.equals(chips[i][j].color, " ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Метод, определяющий есть ли ходы у обоих игроков
    public static boolean NoMoves(GameBoard board) {
        return getLegalMoves(board, "●").size() == 0 && getLegalMoves(board, "◯").size() == 0;
    }
}
