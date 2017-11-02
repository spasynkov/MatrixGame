package matrix.game.strategies.tree;

import matrix.game.strategies.tree.TreeStructure.Node;
import matrix.game.utils.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class TreeCache {
    private Map<String, Node> cache = new HashMap<>();

    void put(int x, int y, Node node) {
        String key = String.format("%d:%d", x, y);
        if (cache.put(key, node) != null)
            throw new RuntimeException("Duplicate key found (" + key + ") for node " + node);
    }

    void put(Coordinate coordinate, Node node) {
        put(coordinate.getX(), coordinate.getY(), node);
    }

    Node get(int x, int y) {
        String key = String.format("%d:%d", x, y);
        return cache.get(key);
    }

    Node get(Coordinate coordinate) {
        return get(coordinate.getX(), coordinate.getY());
    }
}