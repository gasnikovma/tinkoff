package edu.hw3.task5;

public record Contacts(String firstName, String lastName) implements Comparable<Contacts> {
    public Contacts(String contact) {
        this(contact.split(" ")[0], contact.split(" ").length > 1 ? contact.split(" ")[1] : null);

    }

    @Override
    public int compareTo(Contacts o) {
        String firstCondition = this.lastName == null ? this.firstName : this.lastName;
        String secondCondition = this.firstName;
        if (this.lastName != null) {
            secondCondition = o.lastName;
        }
        return firstCondition.compareTo(secondCondition);
    }

}
