package edu.project2.solver;

import edu.project2.generator.PrimaGenerator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiThreadingDfsSolverTest {
    @Test
    void DfsTest() {
        boolean[][] isVisited = new boolean[5][5];
        PrimaGenerator primaGenerator = new PrimaGenerator();
        Maze maze = primaGenerator.generate(5, 5);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        MultiThreadingDfsSolver multiThreadingDfsSolver =
            new MultiThreadingDfsSolver(maze, new Coordinate(1, 1), new Coordinate(3, 3), isVisited);
        Boolean path = forkJoinPool.invoke(multiThreadingDfsSolver);
        if (path) {
            assertNotNull(MultiThreadingDfsSolver.path);
            assertFalse(MultiThreadingDfsSolver.path.isEmpty());
        }

    }
}


