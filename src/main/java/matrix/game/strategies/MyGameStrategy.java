package matrix.game.strategies;

import matrix.game.utils.Coordinate;

/**
 * Болванка для реализации
 */
public class MyGameStrategy implements IGameStrategy {
    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, int direction, Coordinate move) {
        // Напишите здесь вашу реализацию
    }

    @Override
    public String toString() {
        return "/Моя стратегия/";
    }
}
