package matrix.game;

/**
 * Created by 886 on 13.02.2017.
 */
public interface IGameStrategy {
    /**
     * Генерация очередного хода стратегией
     * @param columns   - матрица состояния на текущий ход в виде массива колонок
     * @param rows      - матрица состояния на текущий ход в виде массива строк
     * @param lastMove  - координаты последнего хода
     * @param direction - направление текущего хода (по вертикали - game.DIRECTION_VERTICAL или
     *                    горизонтали - DIRECTION_HORIZONTAL)
     * @param move      - координаты очередного, которые сгенировала стратегия
     * @return          - координаты очередного хода
     */
    void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, int direction, Coordinate move );

}
