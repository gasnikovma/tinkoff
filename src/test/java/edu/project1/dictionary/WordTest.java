package edu.project1.dictionary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    Configuration configuration = new Configuration();

    @Test void isCharInWord_ShouldReturnTrue() {
        Word word = new Word(new String[] {"java", "programming"}, configuration);
        char letter = 'a';
        assertTrue(word.isCharInWord(letter));
    }

}
