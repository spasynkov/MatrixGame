package matrix.game.strategies.tree;

import matrix.game.utils.Coordinate;
import matrix.game.utils.Direction;

import java.util.*;

public class TreeStructure {
    private final TreeCache cache;
    private final Map<Node, Integer> leafs;

    TreeStructure() {
        cache = new TreeCache();
        leafs = new HashMap<>();
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

        private Node(int x, int y, int value) {
            horizontalChildren = new LinkedList<>();
            verticalChildren = new LinkedList<>();
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ":" + y + ") = " + value;
        }
    }
}
