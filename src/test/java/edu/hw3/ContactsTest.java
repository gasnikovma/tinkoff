package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ContactsTest {

    @ParameterizedTest
    @MethodSource("provideContacts")
    void parseContacts_Ascending(List<Contacts> data, String parametr, List<Contacts> expected) {
        List<Contacts> parsed = Contacts.parseContacts(data, parametr);
        for (int i = 0; i < parsed.size(); i++) {
            assertEquals(parsed.get(i).compareTo(expected.get(i)), 0);
        }

    }

    private static Stream<Arguments> provideContacts() {
        return Stream.of(
            Arguments.of(
                Arrays.asList(
                    new Contacts("John Locke"),
                    new Contacts("Thomas Aquinas"),
                    new Contacts("David Hume"),
                    new Contacts("Rene Descartes")
                ),
                "ASc",
                Arrays.asList(
                    new Contacts("Thomas Aquinas"),
                    new Contacts("Rene Descartes"),
                    new Contacts("David Hume"),
                    new Contacts("John Locke")
                )
            ),
            Arguments.of(
                Arrays.asList(), "Desc", Arrays.asList()
            ),
            Arguments.of(null, "AsC", Arrays.asList())
        );

    }

}
