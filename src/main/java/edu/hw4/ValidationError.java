package edu.hw4;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ValidationError {

    private final String errorType;

    private ValidationError(String error) {
        this.errorType = error;
    }

    public static Set<ValidationError> getErrors(Animal animal) {
        Set<ValidationError> error = new HashSet<>();
        if (animal.age() < 0) {
            error.add(new ValidationError("Negative age"));
        }
        if (animal.weight() < 0) {
            error.add(new ValidationError("Negative weight"));
        }
        if (animal.height() < 0) {
            error.add(new ValidationError("Negative height"));
        }
        return error;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ValidationError that)) {
            return false;
        }
        return Objects.equals(errorType, that.errorType);
    }

    @Override public int hashCode() {
        return Objects.hash(errorType);
    }

    @Override public String toString() {

        return errorType;

    }
}
