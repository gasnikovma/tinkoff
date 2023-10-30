package edu.hw1;

import java.util.ArrayList;
import java.util.List;

public final class Task8 {
    private static final int BOARD_SIZE = 8;

    private Task8() {

    }

    public static boolean canKnightsAttack(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    public static boolean knightBoardCapture(int[][] a) {
        if (a == null || a.length != BOARD_SIZE || a[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException("Invalid array size");
        }
        List<Integer> xcoord = new ArrayList<>();
        List<Integer> ycoord = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != 0 && a[i][j] != 1) {
                    throw new IllegalArgumentException("Incorrect data");
                }
                if (a[i][j] == 1) {
                    xcoord.add(i);
                    ycoord.add(j);
                }
            }
        }

        for (int i = 0; i < xcoord.size(); i++) {
            for (int j = i + 1; j < xcoord.size(); j++) {
                if (canKnightsAttack(xcoord.get(i), ycoord.get(i), xcoord.get(j), ycoord.get(j))) {
                    return false;
                }
            }
        }
        return true;

    }

}
