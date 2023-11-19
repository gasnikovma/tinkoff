package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        String string = "asdf";
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,()->{
            Parser parser = new Parser();
            parser.parse(string);
        });
        Assertions.assertEquals("Invalid Path or URI",illegalArgumentException.getMessage());
    }
}
