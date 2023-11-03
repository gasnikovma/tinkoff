package edu.project2;

import edu.project2.renderer.PrettyRender;
import edu.project2.solver.DfsSolver;

public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        Session session = new Session(new Reader(), new PrettyRender(), new DfsSolver());
        session.run();
    }

}
