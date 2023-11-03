package edu.project2;

import edu.project2.model.Cell;
import java.util.Random;

public class Utilities {
    protected Cell[][] grid;
    protected Random random;
    protected int width;
    protected int height;
    protected static final int[][] DIRECTIONS = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    protected void fillGridWithWalls(int height, int width) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
    }

    protected void initialize(int height, int width) {
        this.height = height;
        this.width = width;
        random = new Random();
        grid = new Cell[height][width];
        fillGridWithWalls(height, width);
    }

}
