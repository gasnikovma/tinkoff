package edu.project2.generator;

import edu.project2.Utilities;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RecursiveBacktracerGenerator extends Utilities implements Generator {

    @Override
    public Maze generate(int height, int width) {
        initialize(height, width);
        Stack<Coordinate> passages = new Stack<>();
        Coordinate start = new Coordinate(random.nextInt(height / 2) * 2 + 1, random.nextInt(width / 2) * 2 + 1);
        passages.add(start);
        while (!passages.isEmpty()) {
            Coordinate coordinate = passages.peek();
            int x = coordinate.row();
            int y = coordinate.col();
            grid[coordinate.row()][coordinate.col()].setType(Cell.Type.PASSAGE);
            List<Coordinate> neighbors = new ArrayList<>();
            for (int[] direction : DIRECTIONS) {
                int nx = coordinate.row() + 2 * direction[0];
                int ny = coordinate.col() + 2 * direction[1];

                if (nx >= 0 && nx < height && ny >= 0 && ny < width && grid[nx][ny].getType().equals(Cell.Type.WALL)) {
                    neighbors.add(new Coordinate(nx, ny));
                }
            }

            if (!neighbors.isEmpty()) {
                Coordinate randomNeighbor = neighbors.get(random.nextInt(neighbors.size())); // Случайный сосед
                int nx = randomNeighbor.row();
                int ny = randomNeighbor.col();

                grid[(x + nx) / 2][(y + ny) / 2].setType(Cell.Type.PASSAGE);
                passages.push(randomNeighbor);
            } else {
                passages.pop();
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
}
