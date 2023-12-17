package edu.hw10.task1;

public record RecordTest(@NotNull String name, @Max(33) int age) {
}
