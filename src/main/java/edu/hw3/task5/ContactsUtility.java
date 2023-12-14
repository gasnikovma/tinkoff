package edu.hw3.task5;

import java.util.Collections;
import java.util.List;

public class ContactsUtility {
    private ContactsUtility() {

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
}
