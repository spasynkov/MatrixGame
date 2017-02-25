/**
 * Стратегия, основанная на поиске максимального значения в строке или колонке
 */
public class MaxValueGameStrategy implements IGameStrategy {

    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, int direction, Coordinate move) {
        int maxValue = 0;
        Coordinate result;
        // В зависимости от текущего хода (по горизонтали или вертикали), ищем максимум в строке/колонке и выдаем его за ход
        if (direction == Game.DIRECTION_HORIZONTAL) {
            result = new Coordinate(0,lastMove.y);

            for (int x = 0; x < Game.MATRIX_SIZE; x++)
                if (rows[lastMove.y][x] > maxValue) {
                    maxValue = rows[lastMove.y][x];
                    result.x = x;
                }
        } else {
            result = new Coordinate(lastMove.x, 0);

            for (int y = 0; y < Game.MATRIX_SIZE; y++)
                if (columns[lastMove.x][y] > maxValue) {
                    maxValue = columns[lastMove.x][y];
                    result.y = y;
                }
        }
        move.x = result.x;
        move.y = result.y;
    }

    @Override
    public String toString() {
        return "/Максималист/";
    }

}
