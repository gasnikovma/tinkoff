package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MultiThreadingDfsSolver extends RecursiveTask<Boolean> {

    private final Maze maze;
    private final Coordinate end;
    private final Coordinate start;
    private final boolean[][] isVisited;

    protected static final int[][] DIRECTIONS = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    protected static List<Coordinate> path = new ArrayList<>();

    public MultiThreadingDfsSolver(
        Maze maze, Coordinate start, Coordinate end, boolean[][] isVisited
    ) {
        this.maze = maze;
        this.end = end;
        this.start = start;
        this.isVisited = isVisited;
    }

    @Override protected Boolean compute() {

        if (start.equals(end)) {
            return true;
        }
        for (int direction = 0; direction < DIRECTIONS.length; direction++) {
            Coordinate coordinate =
                new Coordinate(start.row() + DIRECTIONS[direction][0], start.col() + DIRECTIONS[direction][1]);
            if (0 <= coordinate.col() && coordinate.col() < maze.getWidth() && 0 <= coordinate.row()
                && coordinate.row() < maze.getHeight() && !isVisited[coordinate.row()][coordinate.col()]
                && maze.getGrid()[coordinate.row()][coordinate.col()].getType().equals(Cell.Type.PASSAGE)) {
                isVisited[start.row()][start.col()] = true;
                MultiThreadingDfsSolver task = new MultiThreadingDfsSolver(maze, coordinate, end, isVisited);
                if (task.compute()) {
                    path.add(coordinate);
                    return true;
                }

            }

        }
        return false;
    }
}
