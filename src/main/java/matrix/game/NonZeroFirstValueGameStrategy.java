package matrix.game;

/**
 * Пример реализации стратегии поиска первого доступного элемента
 */
public class NonZeroFirstValueGameStrategy implements IGameStrategy {
    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, int direction, Coordinate move) {
        Coordinate result;

        // В зависимости от текущего хода (по горизонтали или вертикали), ищем первый доступный ход
        if (direction == Game.DIRECTION_HORIZONTAL) {
            result = new Coordinate(0,lastMove.y);

            System.out.println();
            System.out.print("Ищем в строке (" +lastMove.y +")" );
            for (int i : rows[lastMove.y])
                System.out.print(String.format("%-4s",i));
            System.out.println();

            for (int x = 0; x < Game.MATRIX_SIZE; x++)
                if (rows[lastMove.y][x] > 0) {
                    result.x = x;
                    break;
                }
        } else {
            result = new Coordinate(lastMove.x, 0);

            System.out.print("Ищем в колонке (" +lastMove.x +")");
            for (int i : columns[lastMove.x])
                System.out.print(String.format("%-4s",i));


            for (int y = 0; y < Game.MATRIX_SIZE; y++)
                if (columns[lastMove.x][y] > 0) {
                    result.y = y;
                    break;
                }
        }
        System.out.println("нашли (" + result.x + "," + result.y +")");
        move.x = result.x;
        move.y = result.y;
    }

    @Override
    public String toString() {
        return "/Первый встречный/";
    }
}
