package matrix.game.utils;

/**
 * Просто целочисленные координаты элемента в матрице (начинаются с 0).
 * JDK класс Point не стал использовать, так как он работает с float
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
