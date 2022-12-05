package davydoff;

import davydoff.View.BoardView;
import davydoff.Models.GameBoard;
import davydoff.GameLogic.Logic;
import davydoff.Controller.InputControl;
import java.util.ArrayList;

public class Game {
    // Метод, для игры игрока с компьютером
    public static void P2C() {
        GameBoard board = new GameBoard();
        while (!Logic.boardFilled(board) && !Logic.NoMoves(board)) {
            ArrayList<String> moves = Logic.getLegalMoves(board, "◯");
            BoardView.printBoardWithMoves(board, moves);
            BoardView.printScore(board);
            if(moves.size() != 0) {
                BoardView.printMoves(moves);
                int move = InputControl.getMoveFromConsole(moves.size());
                int[] coordinates = Logic.translateMove(moves.get(move - 1));
                Logic.makeMove(coordinates[0], coordinates[1], "◯", board);
            } else {
                System.out.println("У вас нет ходов\n");
            }
            Logic.makeBotMove(board);
        }
        System.out.println();
        BoardView.printScore(board);
        System.out.println();
        BoardView.printWinner(board);
        System.out.println();
    }

    // Метод, для режима игрок против игрока
    public static void P2P() {
        GameBoard board = new GameBoard();
        ArrayList<String> moves = new ArrayList<>();
        while (!Logic.boardFilled(board) && !Logic.NoMoves(board)) {
            moves = Logic.getLegalMoves(board, "◯");
            BoardView.printBoardWithMoves(board, moves);
            BoardView.printScore(board);
            System.out.println("Ход Player1\n");
            if(moves.size() != 0) {
                BoardView.printMoves(moves);
                int move = InputControl.getMoveFromConsole(moves.size());
                int[] coordinates = Logic.translateMove(moves.get(move - 1));
                Logic.makeMove(coordinates[0], coordinates[1], "◯", board);
            } else {
                System.out.println("У Player1 нет ходов\n");
            }
            moves = Logic.getLegalMoves(board, "●");
            BoardView.printBoardWithMoves(board, moves);
            BoardView.printScore(board);
            System.out.println("Ход Player2\n");
            if(moves.size() != 0) {
                BoardView.printMoves(moves);
                int move = InputControl.getMoveFromConsole(moves.size());
                int[] coordinates = Logic.translateMove(moves.get(move - 1));
                Logic.makeMove(coordinates[0], coordinates[1], "●", board);
            } else {
                System.out.println("У Player2 нет ходов\n");
            }
        }
        System.out.println();
        BoardView.printScore(board);
        System.out.println();
        BoardView.printWinner(board);
        System.out.println();
    }
}
