package matrix.game.strategies;

import matrix.game.utils.Coordinate;
import matrix.game.Game;
import matrix.game.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Пример реализации стратегии поиска случайного элемента
 */
public class RandomValueGameStrategy implements IGameStrategy {

    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, Direction direction, Coordinate move) {
        List<Coordinate> elements= new ArrayList<>();
        Random random = new Random();
        elements.clear();

        // В зависимости от текущего хода (по горизонтали или вертикали), добавляем координаты возможных ходов
        if (direction == Direction.HORIZONTAL) {
            for (int x = 0; x < Game.MATRIX_SIZE; x++)
                if (rows[lastMove.getY()][x] > 0)
                    elements.add(new Coordinate(x, lastMove.getY()));
        } else {
            for (int y = 0; y < Game.MATRIX_SIZE; y++)
                if (columns[lastMove.getX()][y] > 0)
                    elements.add(new Coordinate(lastMove.getX(), y));
        }

        // Отдадим случайным образом один из возможных вариантов
        if (elements.size()>0) {
            move.setX(elements.get(random.nextInt(elements.size())).getX());
            move.setY(elements.get(random.nextInt(elements.size())).getY());
        }
    }

    @Override
    public String toString() {
        return "/Фартовый/";
    }
}
