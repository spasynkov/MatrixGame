import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Пример реализации стратегии поиска случайного элемента
 */
public class RandomValueGameStrategy implements IGameStrategy {

    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, int direction, Coordinate move) {
        List<Coordinate> elements= new ArrayList<>();
        Random random = new Random();
        elements.clear();

        // В зависимости от текущего хода (по горизонтали или вертикали), добавляем координаты возможных ходов
        if (direction == Game.DIRECTION_HORIZONTAL) {
            for (int x = 0; x < Game.MATRIX_SIZE; x++)
                if (rows[lastMove.y][x] > 0)
                    elements.add(new Coordinate(x, lastMove.y));
        } else {
            for (int y = 0; y < Game.MATRIX_SIZE; y++)
                if (columns[lastMove.x][y] > 0)
                    elements.add(new Coordinate(lastMove.x, y));
        }

        // Отдадим случайным образом один из возможных вариантов
        if (elements.size()>0) {
            move.x = elements.get(random.nextInt(elements.size())).x;
            move.y = elements.get(random.nextInt(elements.size())).y;
        }
    }

    @Override
    public String toString() {
        return "/Фартовый/";
    }
}
