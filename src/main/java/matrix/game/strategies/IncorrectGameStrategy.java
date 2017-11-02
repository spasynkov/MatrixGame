package matrix.game.strategies;

import matrix.game.utils.Coordinate;
import matrix.game.utils.Direction;

/**
 * Пример реализации зацикливания для контроля прерывания поиска в основном цикле игры
 */
public class IncorrectGameStrategy implements IGameStrategy {
    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, Direction direction, Coordinate move) {
        while(true);
    }

    @Override
    public String toString() {
        return "/Вечный цикл/";
    }
}
