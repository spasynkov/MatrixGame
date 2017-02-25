/**
 * Просто целочисленные координаты элемента в матрице (начинаются с 0).
 * JDK класс Point не стал использовать, так как он работает с float
 */
public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
