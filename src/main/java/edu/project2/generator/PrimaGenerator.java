package edu.project2.generator;

import edu.project2.Utilities;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;

public class PrimaGenerator extends Utilities implements Generator {

    @Override
    public Maze generate(int height, int width) {
        initialize(height, width);
        int x = random.nextInt(0, height / 2) * 2 + 1;
        int y = random.nextInt(0, width / 2) * 2 + 1;
        grid[x][y].setType(Cell.Type.PASSAGE);
        List<Coordinate> points = new ArrayList<>();
        if (y - 2 >= 0) {
            points.add(new Coordinate(x, y - 2));
        }
        if (y + 2 < width) {
            points.add(new Coordinate(x, y + 2));
        }
        if (x - 2 >= 0) {
            points.add(new Coordinate(x - 2, y));
        }
        if (x + 2 < height) {
            points.add(new Coordinate(x + 2, y));
        }
        while (points.size() > 0) {
            int index = random.nextInt(0, points.size());
            Coordinate cell = points.get(index);
            x = cell.row();
            y = cell.col();
            grid[x][y].setType(Cell.Type.PASSAGE);
            points.remove(index);
            updateGrid(x, y);

            if (y - 2 >= 0 && grid[x][y - 2].getType().equals(Cell.Type.WALL)) {
                points.add(new Coordinate(x, y - 2));
            }
            if (y + 2 < width && grid[x][y + 2].getType().equals(Cell.Type.WALL)) {
                points.add(new Coordinate(x, y + 2));
            }
            if (x - 2 >= 0 && grid[x - 2][y].getType().equals(Cell.Type.WALL)) {
                points.add(new Coordinate(x - 2, y));
            }

            if (x + 2 < height && grid[x + 2][y].getType().equals(Cell.Type.WALL)) {
                points.add(new Coordinate(x + 2, y));
            }
        }
        for (int i = 0; i < height; i++) {
            grid[i][width - 1].setType(Cell.Type.WALL);
        }
        for (int i = 0; i < width; i++) {
            grid[height - 1][i].setType(Cell.Type.WALL);
        }
        return new Maze(height, width, grid);
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public void updateGrid(int x, int y) {
        List<Integer> d = new ArrayList<>(List.of(0, 1, 2, 3));
        while (d.size() > 0) {
            int dirIndex = random.nextInt(0, d.size());
            switch (d.get(dirIndex)) {
                case 0:
                    if (y - 2 >= 0 && grid[x][y - 2].getType().equals(Cell.Type.PASSAGE)) {
                        grid[x][y - 1].setType(Cell.Type.PASSAGE);
                        d.clear();
                    }
                    break;
                case 1:
                    if (y + 2 < width && grid[x][y + 2].getType().equals(Cell.Type.PASSAGE)) {
                        grid[x][y + 1].setType(Cell.Type.PASSAGE);
                        d.clear();
                    }
                    break;
                case 2:
                    if (x - 2 >= 0 && grid[x - 2][y].getType().equals(Cell.Type.PASSAGE)) {
                        grid[x - 1][y].setType(Cell.Type.PASSAGE);
                        d.clear();
                    }
                    break;
                case 3:
                    if (x + 2 < height && grid[x + 2][y].getType().equals(Cell.Type.PASSAGE)) {
                        grid[x + 1][y].setType(Cell.Type.PASSAGE);
                        d.clear();
                    }
                    break;
                default:
            }
            if (d.size() != 0) {
                d.remove(dirIndex);
            }
        }
    }
}
