package edu.hw10.task1;

public class ClassTest {
    @NotNull
    public String notNullField;

    @Min(10)
    public int minField;

    @Max(50)
    public int maxField;

    public String getNotNullField() {
        return notNullField;
    }

    public int getMinField() {
        return minField;
    }

    public int getMaxField() {
        return maxField;
    }
}
