package edu.project2.solver;

import edu.project2.Utilities;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;

public class DfsSolver extends Utilities implements Solver {

    private Coordinate end;

    private boolean[][] used;
    List<Coordinate> path;

    private boolean dfs(Coordinate c) {
        used[c.row()][c.col()] = true;
        if (c.equals(end)) {
            path.add(c);
            return true;
        }
        for (int direction = 0; direction < DIRECTIONS.length; direction++) {
            Coordinate coordinate =
                new Coordinate(c.row() + DIRECTIONS[direction][0], c.col() + DIRECTIONS[direction][1]);
            if (0 <= coordinate.col() && coordinate.col() < width && 0 <= coordinate.row()
                && coordinate.row() < height && !used[coordinate.row()][coordinate.col()]
                && grid[coordinate.row()][coordinate.col()].getType().equals(Cell.Type.PASSAGE)) {
                if (dfs(coordinate)) {
                    path.add(c);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        this.end = end;
        grid = maze.getGrid();
        height = maze.getHeight();
        width = maze.getWidth();
        used = new boolean[height][width];
        path = new ArrayList<>();

        boolean wasFound = dfs(start);
        if (!wasFound) {
            throw new RuntimeException("Нет пути");
        }
        return path;
    }
}
