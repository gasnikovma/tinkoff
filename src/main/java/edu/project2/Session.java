package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.PrimaGenerator;
import edu.project2.generator.RecursiveBacktracerGenerator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.renderer.Renderer;
import edu.project2.solver.Solver;
import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public class Session {

    private final Reader reader;
    private static final String PRIMS = "Prim's";
    private static final String RB = "Recursive Backtrace";
    private final Renderer renderer;
    private final Solver dfsSolver;
    private Maze maze;

    public Session(Reader reader, Renderer renderer, Solver dfsSolver) {
        this.reader = reader;
        this.renderer = renderer;
        this.dfsSolver = dfsSolver;
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public void run() {
        System.out.println(AnswerStatus.START);
        Generator generator;
        System.out.println(AnswerStatus.HEIGHT);
        int height = Integer.parseInt(reader.getLine());
        while (height <= 0) {
            System.out.println(AnswerStatus.ERROR_INPUT);
            height = Integer.parseInt(reader.getLine());
        }
        System.out.println(AnswerStatus.WIDTH);
        int width = Integer.parseInt(reader.getLine());
        while (width <= 0) {
            System.out.println(AnswerStatus.ERROR_INPUT);
            width = Integer.parseInt(reader.getLine());
        }
        generator = chooseGenerator();
        maze = generator.generate(height, width);
        System.out.println(renderer.render(maze));
        List<Coordinate> coordinateList;
        int startRow = inputCoordinates(true, 0);
        int startCol = inputCoordinates(false, 2);
        int endRow = inputCoordinates(true, 1);
        int endCol = inputCoordinates(false, 3);
        coordinateList = dfsSolver.solve(maze, new Coordinate(startRow, startCol), new Coordinate(endRow, endCol));
        System.out.print(renderer.render(maze, coordinateList));

    }

    @SuppressWarnings("checkstyle:magicnumber")
    private int inputCoordinates(boolean isRowCoord, int typeInput) {
        if (typeInput == 0) {
            System.out.println(AnswerStatus.START_ROW);
        }
        if (typeInput == 1) {
            System.out.println(AnswerStatus.END_ROW);
        }
        if (typeInput == 2) {
            System.out.println(AnswerStatus.START_COL);
        }
        if (typeInput == 3) {
            System.out.println(AnswerStatus.END_COL);
        }

        int restriction;
        if (isRowCoord) {
            restriction = maze.getHeight();
        } else {
            restriction = maze.getWidth();
        }
        int coordinate = Integer.parseInt(reader.getLine());
        while (!(coordinate > 0 && coordinate < restriction)) {
            System.out.printf(AnswerStatus.ERROR_COORDINATE + "\n", restriction - 1);
            coordinate = Integer.parseInt(reader.getLine());
        }
        return coordinate;
    }

    private Generator chooseGenerator() {
        String type;
        do {
            System.out.println(AnswerStatus.TYPE_OF_GENERATION);
            type = reader.getLine();
        } while (!RB.equalsIgnoreCase(type) && !PRIMS.equalsIgnoreCase(type));
        if (PRIMS.equalsIgnoreCase(type)) {
            return new PrimaGenerator();
        }
        return new RecursiveBacktracerGenerator();
    }

}
