package matrix.game.strategies;

import matrix.game.utils.Coordinate;
import matrix.game.Game;
import matrix.game.utils.Direction;

/**
 * Пример реализации стратегии поиска первого доступного элемента
 */
public class NonZeroFirstValueGameStrategy implements IGameStrategy {
    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, Direction direction, Coordinate move) {
        Coordinate result;

        // В зависимости от текущего хода (по горизонтали или вертикали), ищем первый доступный ход
        if (direction == Direction.HORIZONTAL) {
            result = new Coordinate(0,lastMove.getY());

            System.out.println();
            System.out.print("Ищем в строке (" +lastMove.getY() +")" );
            for (int i : rows[lastMove.getY()])
                System.out.print(String.format("%-4s",i));
            System.out.println();

            for (int x = 0; x < Game.MATRIX_SIZE; x++)
                if (rows[lastMove.getY()][x] > 0) {
                    result.setX(x);
                    break;
                }
        } else {
            result = new Coordinate(lastMove.getX(), 0);

            System.out.print("Ищем в колонке (" +lastMove.getX() +")");
            for (int i : columns[lastMove.getX()])
                System.out.print(String.format("%-4s",i));


            for (int y = 0; y < Game.MATRIX_SIZE; y++)
                if (columns[lastMove.getX()][y] > 0) {
                    result.setY(y);
                    break;
                }
        }
        System.out.println("нашли (" + result.getX() + "," + result.getY() +")");
        move.setX(result.getX());
        move.setY(result.getY());
    }

    @Override
    public String toString() {
        return "/Первый встречный/";
    }
}
