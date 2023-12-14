package edu.project3.printer;

public class PrinterFactory {
    public Printer createPrinter(String type) {
        Printer printer = null;

        switch (type) {
            case "markdown":
                printer = new MarkdownPrinter();
                break;
            case "adoc":
                printer = new ASCIIDocPrinter();
                break;
            default:
                break;

        }

        return printer;

    }
}
