package matrix.game;

import matrix.game.strategies.IGameStrategy;
import matrix.game.strategies.MaxValueGameStrategy;
import matrix.game.strategies.RandomValueGameStrategy;
import matrix.game.utils.Coordinate;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Основной класс
 */
public class Game {

    // Размер матрицы
    public static final int MATRIX_SIZE = 10;

    // Количество перестановок элементов матрицы (для генерации случайной матрицы)
    private static final int NUMBER_OF_SHAKING = 1000;

    // Максимальное время, отведенное для каждого игрока
    private static final int MAX_SECONDS_FOR_RUN = 1;

    // Требования выполнить следующий ход по вертикали или горизонтали для текущего игрока
    static final int DIRECTION_VERTICAL   = -1;
    public static final int DIRECTION_HORIZONTAL =  1;

    // Матрица состояния игры, разбитая отдельно на массив строк и колонок для удобства
    private static int[][] rows    = new int[MATRIX_SIZE][MATRIX_SIZE]; // первая координата - номер строки,  вторая - номер элемента в ней
    private static int[][] columns = new int[MATRIX_SIZE][MATRIX_SIZE]; // первая координата - номер колонки, вторая - номер элемента в ней

    private static Random random = new Random();
    private static Coordinate newTurn = null;

    /**
     * Пример запуска игры 2х игроков
     * @param args
     */
    public static void main(String[] args) {
        // Создадим игроков
        IGameStrategy[] players = new IGameStrategy[2];
        players[0] = new MaxValueGameStrategy();
        players[1] = new RandomValueGameStrategy();

        // Сгенерируем матрицу игры
        createRandomMatrix();

        // Запустим игру
        runGame(players);
    }

    /**
     * Проверка доступности следующего хода
     * @param columns  - текущий на данный ход массив колонок матрицы игры
     * @param rows     - текущий на данный ход массив строк матрицы игры
     * @param lastMove - последний сделанный ход
     * @param direction- направление очередного хода (по вертикали или горизонтали)
     * @return true    - если очередной ход возможен, false - иначе
     */
    static boolean isMoveExists(int[][] columns, int[][] rows, Coordinate lastMove, int direction) {

        int nonZeroHorizontalElements = 0;
        int nonZeroVerticalElements = 0;

        for (int i:rows[lastMove.getY()])
            if (i>0)
                nonZeroHorizontalElements++;

        for (int i:columns[lastMove.getX()])
            if (i>0)
                nonZeroVerticalElements++;

        // Если в строке предполагаемого хода все клетки, кроме текущей были выбраны до этого (выбранные клетки = 0) или
        // в колонке предполагаемого хода все клетки, кроме текущей были выбраны до этого (выбранные клетки = 0) - то больше ходов нет
        return (direction == DIRECTION_VERTICAL && nonZeroVerticalElements > 0 ||
                direction == DIRECTION_HORIZONTAL && nonZeroHorizontalElements > 0);
    }


    /**
     * Создаем случайную матрицу игры
     */
    public static void createRandomMatrix() {
        // Заполняем матрицу числами от 1 до 100 в возрастающем порядке
        for (int i = 0; i < MATRIX_SIZE*MATRIX_SIZE; i++)
            rows[i / MATRIX_SIZE][i % MATRIX_SIZE] = columns[i % MATRIX_SIZE][i / MATRIX_SIZE] = i + 1;

        // Будем выполнять перестановки элементов
        for  (int i = 0; i < NUMBER_OF_SHAKING; i++) {
            // Выберем точку (x1,y1)
            int x1 = random.nextInt(MATRIX_SIZE);
            int y1 = random.nextInt(MATRIX_SIZE);

            // Выберем точку (x2,y2)
            int x2 = random.nextInt(MATRIX_SIZE);
            int y2 = random.nextInt(MATRIX_SIZE);

            int a = columns[x1][y1];
            int b = columns[x2][y2];

            // Переставим местами их значения
            rows[y2][x2] = a;
            rows[y1][x1] = b;
            columns[x2][y2] = a;
            columns[x1][y1] = b;
        }

        System.out.println("Сгенерирована новая матрица:");
        for (int[] row:rows) {
            for (int num : row)
                System.out.print(String.format("%-4s", num));
            System.out.println();
        }
    }

    /**
     * Вывод на экран очередного хода
     * @param rows      - матрица игры в виде набора строк
     * @param lastMove  - последний ход
     * @param cntTurn   - номер хода в игре
     * @param player    - сделавшеий ход игрок
     * @param numPlayer - номер сделавшего ход игрока
     * @param element   - выбранный элемент
     * @param score     - общее количество очков игрока
     */
    static void printTurn(int[][] rows, Coordinate lastMove, int cntTurn,
                          IGameStrategy player, int numPlayer, int element, int score, int direction) {
        String direct = " ход: вертикаль ";
        if (direction == DIRECTION_HORIZONTAL)
            direct = " ход: горизонталь ";

        System.out.println("### Номер хода: " + cntTurn + ", игрок: " +player + numPlayer + ", ход: (" +
                lastMove.getX() + "," + lastMove.getY() + ")=" + element + " Счет игрока: " + score + direct + " ###");
        for (int[] row:rows) {
            for (int num : row)
                if (num != element)
                    System.out.print(String.format("%-4s", num));
                else
                    System.out.print(String.format(">%-3s", element));
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }


    /**
     * Собственно игра
     * @param players участвующие в игре стратегии
     */
    public static void runGame(IGameStrategy[] players) {
        // Стартовое направление
        int currentDirection = DIRECTION_HORIZONTAL;

        // Создадим счет (результаты) игроков
        int[] scores = new int[players.length];

        // Первый игрок автоматически ходит в левый верхний угол + обновим его счет и учтем (занулим элемент матрицы игры) ход
        int currentPlayer = 0;
        Coordinate turn   = new Coordinate(0,0);
        scores[0] = columns[0][0];
        printTurn(rows, turn, 1, players[currentPlayer], currentPlayer, scores[0], scores[currentPlayer], currentDirection );
        columns[0][0] = 0;
        rows   [0][0] = 0;

        // Число некорректных ходов
        int incorrectTurn = 0;

        // Текущий ход
        int currentTurn = 1;

        // Делаем поочередно ходы (пока они есть или число некорректных сходивших игроков меньше их количества
        while (isMoveExists(columns, rows, turn, currentDirection) && incorrectTurn <= players.length) {
            // переход хода к очередному игроку
            currentPlayer = currentPlayer < (players.length-1) ? ++currentPlayer : 0;
            ExecutorService executor = Executors.newFixedThreadPool(1  );
            Future<?> future   = null;

            // получаем очередной ход
            try {
                // Сделаем для стратегий копию данных матрицы игры (чтобы стратегия не "мухлевала" на основной матрице
                int[][] rowsCopy = new int[MATRIX_SIZE][MATRIX_SIZE];
                for (int i = 0; i < MATRIX_SIZE; i++)
                    System.arraycopy(rows[i], 0, rowsCopy[i], 0, MATRIX_SIZE);

                int[][] columnsCopy = new int[MATRIX_SIZE][MATRIX_SIZE];
                for (int i = 0; i < MATRIX_SIZE; i++)
                    System.arraycopy(columns[i], 0, columnsCopy[i], 0, MATRIX_SIZE);

                int playerNum  = currentPlayer;
                int directionStrat = currentDirection;
                Coordinate lastTurn = turn;
                Coordinate generateTurn  = new Coordinate(0,0);

                // Оформим вызов хода очередной стратегии (+совместим с JDK 7)
                future = executor.submit( new Runnable() {
                    @Override
                    public void run() {
                        players[playerNum].getTurn(columnsCopy, rowsCopy, lastTurn, directionStrat, generateTurn);
                    }
                });

                // Ограничим принудительно время работы стратегии
                future.get(MAX_SECONDS_FOR_RUN, TimeUnit.SECONDS);
                executor.shutdown(); // Новых джобов не ждем
                newTurn = generateTurn;

            } catch (TimeoutException e1) {
                future.cancel(true);
                //executor.shutdownNow();
                System.out.println("Превышено время работы стратегии");

            } catch( Exception e2) {
                future.cancel(true);
                //executor.shutdownNow();
                System.out.println("Некорректная работа стратегии");
            }
            executor.shutdownNow();

            // Увеличиваем общий счетчик ходов (даже, если ход был некорректный)
            currentTurn++;

            // Проверка допустимости сгенерированного стратегией решения
            // Валидация хода, если неправильный - переходим к следующему игроку
            // Ход правильный, если:
                    // 1) стратегия его сгенерировала
            if     (newTurn != null &&
                    // 2) если он не выходит за границы матрицы игры
                    newTurn.getX() < MATRIX_SIZE
                    && newTurn.getX()>=0
                    && newTurn.getY() < MATRIX_SIZE
                    && newTurn.getY()>=0 &&
                    // 3) если он не был сыгран ранее
                    columns[newTurn.getX()][newTurn.getY()] > 0 &&
                    // 4) если не было "мухлежа" - был выполнен в соответствие с направлением (по вертикали или горизонтали)
                    (currentDirection==DIRECTION_HORIZONTAL && turn.getY()==newTurn.getY() ||
                     currentDirection==DIRECTION_VERTICAL  && turn.getX()==newTurn.getX())) {
                // Сбрасываем счетчик некорректных ходов, так как текущий ход - корректен
                incorrectTurn = 0;

                turn = newTurn;
                // Начисляем очки
                int currentElement = columns[turn.getX()][turn.getY()];
                scores[currentPlayer] += currentElement;
                // отобразим ход
                printTurn(rows, turn, currentTurn, players[currentPlayer], currentPlayer, currentElement,
                        scores[currentPlayer], currentDirection);

                // Отмечаем что данный элемент использован
                columns [turn.getX()][turn.getY()] = 0;
                rows    [turn.getY()][turn.getX()] = 0;

                // смена направления хода
                currentDirection = -currentDirection;
            } else { // Ход некорректный или пустой
                incorrectTurn++;
                System.out.println("Игрок " + (currentPlayer+1) + " сходил некорректно, ход переходит к следующему игроку");
            }
        }

        System.out.println("########### Итого ###########");
        for(int i=0; i<players.length; i++)
            System.out.println(players[i] + "" + i +" набрал " + scores[i] + " очков ");

        System.exit(0); // Подчистим за собой все порожденные процессы

    }

}
