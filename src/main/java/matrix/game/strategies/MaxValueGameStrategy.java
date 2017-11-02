package matrix.game.strategies;

import matrix.game.utils.Coordinate;
import matrix.game.Game;
import matrix.game.utils.Direction;

/**
 * Стратегия, основанная на поиске максимального значения в строке или колонке
 */
public class MaxValueGameStrategy implements IGameStrategy {

    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, Direction direction, Coordinate move) {
        int maxValue = 0;
        Coordinate result;
        // В зависимости от текущего хода (по горизонтали или вертикали), ищем максимум в строке/колонке и выдаем его за ход
        if (direction == Direction.HORIZONTAL) {
            result = new Coordinate(0,lastMove.getY());

            for (int x = 0; x < Game.MATRIX_SIZE; x++)
                if (rows[lastMove.getY()][x] > maxValue) {
                    maxValue = rows[lastMove.getY()][x];
                    result.setX(x);
                }
        } else {
            result = new Coordinate(lastMove.getX(), 0);

            for (int y = 0; y < Game.MATRIX_SIZE; y++)
                if (columns[lastMove.getX()][y] > maxValue) {
                    maxValue = columns[lastMove.getX()][y];
                    result.setY(y);
                }
        }
        move.setX(result.getX());
        move.setY(result.getY());
    }

    @Override
    public String toString() {
        return "/Максималист/";
    }

}
