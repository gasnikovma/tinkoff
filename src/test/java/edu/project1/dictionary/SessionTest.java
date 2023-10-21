package edu.project1.dictionary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SessionTest {
    Dictionary dictionary = new DictionaryImpl();

    @BeforeEach
    public void initEach() {
        State.isInProgress = true;
        GuessIncorrectLetter.attempts = 0;
    }

    @Test
    @DisplayName("win")
    void shouldReturnWin() {
        List<Character> inputs = Arrays.asList('v', 'a', 'k', 'd', 'w', 'f', 'j');
        int i = 0;
        Message message = new MessageImpl();
        Word word = new Word(new String[] {"java"}, dictionary);
        message.startGame();

        while (State.isInProgess()) {

            Character inputLetter = inputs.get(i);

            if (!inputLetter.equals('\u0000')) {
                if (word.isCharInWord(inputLetter)) {
                    GuessCorrectLetter letterGuess = new GuessCorrectLetter(message);
                    letterGuess.handleGuessedLetter(word, inputLetter);

                } else {
                    GuessIncorrectLetter letterGuess = new GuessIncorrectLetter(message);
                    letterGuess.handleNotGuessedLetter(word);
                }
            }
            i += 1;
        }
        assertFalse(State.isInProgress);
    }

    @Test
    @DisplayName("lose")
    void shouldReturnLose() {
        List<Character> inputs = Arrays.asList('v', 'a', 't', 'l', 'o', 'i', 'z', 'w', 's');
        int i = 0;
        Message message = new MessageImpl();
        Word word = new Word(new String[] {"kotlin"}, dictionary);
        message.startGame();

        while (State.isInProgess()) {

            Character inputLetter = inputs.get(i);

            if (!inputLetter.equals('\u0000')) {
                if (word.isCharInWord(inputLetter)) {
                    GuessCorrectLetter letterGuess = new GuessCorrectLetter(message);
                    letterGuess.handleGuessedLetter(word, inputLetter);

                } else {
                    GuessIncorrectLetter letterGuess = new GuessIncorrectLetter(message);
                    letterGuess.handleNotGuessedLetter(word);
                }
            }
            i += 1;
        }
        assertFalse(State.isInProgress);
    }

    @Test
    @DisplayName("give_up")
    void inputLetter_shouldThrowException() {
        Session session = new Session(new DictionaryImpl(), new MessageImpl(), new ReaderImpl());
        String s = "ww";
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            session.inputLetter(s);
        });
        assertEquals("more than one letter", thrown.getMessage());
        assertFalse(State.isInProgess());

    }
}
