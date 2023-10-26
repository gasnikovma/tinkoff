package edu.hw3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contacts implements Comparable<Contacts> {
    private final String firstName;
    private final String lastName;

    public Contacts(String contact) {
        List<String> contacts = Arrays.stream(contact.split(" ")).toList();
        firstName = contacts.get(0);
        lastName = contacts.size() > 1 ? contacts.get(1) : null;

    }

    @Override public int compareTo(Contacts o) {
        String firstCondition = this.lastName == null ? this.firstName : this.lastName;
        String secondCondition = o.lastName == null ? o.firstName : o.lastName;
        return firstCondition.compareTo(secondCondition);
    }

    public static List<Contacts> parseContacts(List<Contacts> data, String parametr) {
        if (data == null) {
            return List.of();
        }
        if (parametr.equalsIgnoreCase("ASC")) {
            Collections.sort(data);
        } else {
            Collections.sort(data);
            Collections.reverse(data);
        }
        return data;
    }

    @Override public String toString() {
        return "Contacts{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
