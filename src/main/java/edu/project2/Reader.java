package edu.project2;

import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String getLine() {
        return scanner.nextLine();
    }
}
