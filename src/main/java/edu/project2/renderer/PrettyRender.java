package edu.project2.renderer;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public class PrettyRender implements Renderer {
    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();
        Cell[][] mazeGrid = maze.getGrid();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                sb.append(mazeGrid[row][col].getType().equals(Cell.Type.WALL) ? "▉" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder s = new StringBuilder(render(maze));
        int i = path.size() - 1;
        while (i > 0) {
            if (Math.abs(path.get(i).row() - path.get(i - 1).row()) == 1) {
                if (path.get(i).row() < path.get(i - 1).row()) {
                    s.setCharAt(maze.getWidth() * path.get(i).row() + path.get(i).row() + path.get(i).col(), '↓');
                } else if (path.get(i).row() > path.get(i - 1).row()) {
                    s.setCharAt(maze.getWidth() * path.get(i).row() + path.get(i).row() + path.get(i).col(), '↑');
                }
            } else if (Math.abs(path.get(i).col() - path.get(i - 1).col()) == 1) {
                if (path.get(i).col() < path.get(i - 1).col()) {
                    s.setCharAt(maze.getWidth() * path.get(i).row() + path.get(i).row() + path.get(i).col(), '→');
                } else if (path.get(i).col() > path.get(i - 1).col()) {
                    s.setCharAt(maze.getWidth() * path.get(i).row() + path.get(i).row() + path.get(i).col(), '←');
                }
            }
            i -= 1;
        }
        s.setCharAt(maze.getWidth() * path.get(0).row() + path.get(0).row() + path.get(0).col(), '⚑');
        return String.valueOf(s);

    }
}
