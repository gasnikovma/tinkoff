package edu.project1.dictionary;

import java.util.Scanner;

public class ReaderImpl implements Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String getLine() {
        return scanner.nextLine();
    }
}
