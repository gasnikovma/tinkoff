package edu.project3;

import edu.project3.printer.PrinterFactory;
import java.io.IOException;

public class Main {
    private Main() {

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Parser parser = new Parser();
        PrinterFactory printer = new PrinterFactory();
        Session session = new Session(parser, printer);
        session.startSession(args);


    }
}
