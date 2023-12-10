package edu.project4;

import edu.project4.render.MultiThreadRenderImpl;
import edu.project4.render.Render;
import edu.project4.transformation.Diamond;
import edu.project4.transformation.Transformation;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

    private Main() {

    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Render render = new MultiThreadRenderImpl();
        Transformation transformation = new Diamond();
        Session session = new Session();
        session.run(render, transformation, "diamond.png");
    }
}
