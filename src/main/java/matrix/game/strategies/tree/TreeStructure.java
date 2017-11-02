package matrix.game.strategies.tree;

import matrix.game.utils.Coordinate;
import matrix.game.utils.Direction;

import java.util.LinkedList;
import java.util.List;

public class TreeStructure {
    private final TreeCache cache;
    private final List<Node> leafs;

    public TreeStructure() {
        cache = new TreeCache();
        leafs = new LinkedList<>();
    }

    void init(int[][] matrix) {

    }

    void update(int[][] matrix) {

    }

    Coordinate getBestMove(int currentX, int currentY, Direction direction) {
        Coordinate result = null;

        return result;
    }

    Coordinate getBestMove(Coordinate coordinate, Direction direction) {
        return getBestMove(coordinate.getX(), coordinate.getY(), direction);
    }

    class Node {
        private final List<Node> horizontalChildren;
        private final List<Node> verticalChildren;

        private final int x;
        private final int y;
        private int value;

        public Node(int x, int y, int value) {
            horizontalChildren = new LinkedList<>();
            verticalChildren = new LinkedList<>();
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + x + ":" + y + ") = " + value;
        }
    }
}
