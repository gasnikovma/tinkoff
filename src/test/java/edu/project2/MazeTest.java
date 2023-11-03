package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.RecursiveBacktracerGenerator;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.renderer.PrettyRender;
import edu.project2.renderer.Renderer;
import edu.project2.solver.DfsSolver;
import edu.project2.solver.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeTest {
    @Test
    @DisplayName("example of finding solution")
    void MazeTest_ShouldFindPath() {
        Cell[][] cells = new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL), new Cell(0, 5, Cell.Type.WALL),
                new Cell(0, 6,
                    Cell.Type.WALL
                )},

            new Cell[] {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.PASSAGE), new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL),
                new Cell(1, 5,
                    Cell.Type.PASSAGE
                ), new Cell(1, 6, Cell.Type.WALL)},

            new Cell[] {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE),
                new Cell(2, 2, Cell.Type.WALL), new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL),
                new Cell(2, 5,
                    Cell.Type.PASSAGE
                ), new Cell(2, 6, Cell.Type.WALL)},

            new Cell[] {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.WALL), new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.PASSAGE),
                new Cell(3, 5,
                    Cell.Type.PASSAGE
                ), new Cell(3, 6, Cell.Type.WALL)},
            new Cell[] {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.WALL),
                new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL), new Cell(4, 5, Cell.Type.WALL),
                new Cell(4, 6,
                    Cell.Type.WALL
                )}
        };
        Maze maze = new Maze(5, 7, cells);
        Coordinate start = new Coordinate(1, 2);
        Coordinate end = new Coordinate(3, 3);
        Solver solver = new DfsSolver();
        List<Coordinate> coordinateList = solver.solve(maze, start, end);
        assertThat(coordinateList.contains(new Coordinate(1, 2))).isEqualTo(true);
        assertThat(coordinateList.contains(new Coordinate(1, 3))).isEqualTo(true);
        assertThat(coordinateList.contains(new Coordinate(2, 3))).isEqualTo(true);
        assertThat(coordinateList.contains(new Coordinate(3, 3))).isEqualTo(true);
        assertThat(coordinateList.contains(new Coordinate(2, 1))).isEqualTo(false);
    }

    @Test
    @DisplayName("example of throw exception")
    void MazeTest_shouldThrowException() {
        Cell[][] cells = new Cell[][] {
            new Cell[] {new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.WALL), new Cell(0, 2, Cell.Type.WALL),
                new Cell(0, 3, Cell.Type.WALL), new Cell(0, 4, Cell.Type.WALL), new Cell(0, 5, Cell.Type.WALL),
                new Cell(0, 6,
                    Cell.Type.WALL
                )},

            new Cell[] {new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.PASSAGE), new Cell(1, 3, Cell.Type.PASSAGE), new Cell(1, 4, Cell.Type.WALL),
                new Cell(1, 5,
                    Cell.Type.PASSAGE
                ), new Cell(1, 6, Cell.Type.WALL)},

            new Cell[] {new Cell(2, 0, Cell.Type.WALL), new Cell(2, 1, Cell.Type.PASSAGE),
                new Cell(2, 2, Cell.Type.WALL), new Cell(2, 3, Cell.Type.PASSAGE), new Cell(2, 4, Cell.Type.WALL),
                new Cell(2, 5,
                    Cell.Type.PASSAGE
                ), new Cell(2, 6, Cell.Type.WALL)},

            new Cell[] {new Cell(3, 0, Cell.Type.WALL), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.WALL), new Cell(3, 3, Cell.Type.PASSAGE), new Cell(3, 4, Cell.Type.PASSAGE),
                new Cell(3, 5,
                    Cell.Type.PASSAGE
                ), new Cell(3, 6, Cell.Type.WALL)},
            new Cell[] {new Cell(4, 0, Cell.Type.WALL), new Cell(4, 1, Cell.Type.WALL), new Cell(4, 2, Cell.Type.WALL),
                new Cell(4, 3, Cell.Type.WALL), new Cell(4, 4, Cell.Type.WALL), new Cell(4, 5, Cell.Type.WALL),
                new Cell(4, 6,
                    Cell.Type.WALL
                )}
        };
        Maze maze = new Maze(5, 7, cells);
        Coordinate start = new Coordinate(1, 2);
        Coordinate end = new Coordinate(2, 4);
        Solver solver = new DfsSolver();
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> {
            solver.solve(maze, start, end);
        });
        Assertions.assertEquals(runtimeException.getMessage(), "Нет пути");

    }

}
