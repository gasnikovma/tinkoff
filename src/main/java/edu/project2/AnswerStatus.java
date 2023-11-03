package edu.project2;

public enum AnswerStatus {
    START("This is maze generator and solver"),
    TYPE_OF_GENERATION("Choose the algorithm for generation(Recursive Backtrace/Prim's)"),
    HEIGHT("Input height of maze"),
    WIDTH("Input width of maze"),
    START_ROW("Input row coordinate of start"),
    START_COL("Input col coordinate of start"),
    END_ROW("Input row coordinate of end"),
    END_COL("Input col coordinate of end"),
    ERROR_INPUT("This parametr should be positive"),
    ERROR_COORDINATE("This coordinate should be in range from 0 to %d");

    private final String userMessage;

    AnswerStatus(String userMessage) {
        this.userMessage = userMessage;
    }

    @Override
    public String toString() {
        return this.userMessage;
    }
}
