package matrix.game.strategies.tree;

import matrix.game.strategies.IGameStrategy;
import matrix.game.utils.Coordinate;
import matrix.game.utils.Direction;

public class TreeStrategy implements IGameStrategy {
    private TreeStructure tree;
    private final Coordinate defaultCoordinate = new Coordinate(0, 0);

    @Override
    public void getTurn(int[][] columns, int[][] rows, Coordinate lastMove, Direction direction, Coordinate move) {
        if (tree == null) {
            tree = new TreeStructure();
            tree.init(columns);
        } else {
            tree.update(columns);
        }

        // real method call for results
        //move = tree.getBestMove(lastMove, direction);

        // fake temporal result for testing
        move = defaultCoordinate;
    }

    @Override
    public String toString() {
        return "/Tree strategy/";
    }
}
