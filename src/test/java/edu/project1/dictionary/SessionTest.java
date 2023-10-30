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
    Configuration dictionary = new Configuration();

    @BeforeEach
    public void initEach() {
        State state = new State();
        state.isInProgress = true;
        GuessIncorrectLetter.attempts = 0;
    }

    @Test
    @DisplayName("win")
    void shouldReturnWin() {
        List<Character> inputs = Arrays.asList('v', 'a', 'k', 'd', 'w', 'f', 'j');
        int i = 0;
        Message message = new Message();
        Word word = new Word(new String[] {"java"}, dictionary);
        message.startGame();
        State state = new State();

        while (state.isInProgess()) {

            Character inputLetter = inputs.get(i);

            if (!inputLetter.equals('\u0000')) {
                if (word.isCharInWord(inputLetter)) {
                    GuessCorrectLetter letterGuess = new GuessCorrectLetter(message,state);
                    letterGuess.handleGuessedLetter(word, inputLetter);

                } else {
                    GuessIncorrectLetter letterGuess = new GuessIncorrectLetter(message,state);
                    letterGuess.handleNotGuessedLetter(word);
                }
            }
            i += 1;
        }
        assertFalse(state.isInProgress);
    }

    @Test
    @DisplayName("lose")
    void shouldReturnLose() {
        List<Character> inputs = Arrays.asList('v', 'a', 't', 'l', 'o', 'i', 'z', 'w', 's');
        int i = 0;
        Message message = new Message();
        Word word = new Word(new String[] {"kotlin"}, dictionary);
        message.startGame();
        State state = new State();

        while (state.isInProgess()) {

            Character inputLetter = inputs.get(i);

            if (!inputLetter.equals('\u0000')) {
                if (word.isCharInWord(inputLetter)) {
                    GuessCorrectLetter letterGuess = new GuessCorrectLetter(message,state);
                    letterGuess.handleGuessedLetter(word, inputLetter);

                } else {
                    GuessIncorrectLetter letterGuess = new GuessIncorrectLetter(message,state);
                    letterGuess.handleNotGuessedLetter(word);
                }
            }
            i += 1;
        }
        assertFalse(state.isInProgress);
    }

    @Test
    @DisplayName("give_up")
    void inputLetter_shouldThrowException() {
        State state = new State();
        Session session = new Session(new Configuration(), new Message(), new ReaderImpl(),state);
        session.inputLetter("give up");
        assertFalse(state.isInProgess());

    }
}
