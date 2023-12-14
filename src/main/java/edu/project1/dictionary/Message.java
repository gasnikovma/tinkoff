package edu.project1.dictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Message {
    private final static Logger LOGGER = LogManager.getFormatterLogger();

    public void startGame() {
        LOGGER.info(AnswerStatus.START);
    }

    public void printMistakes(int attempt) {
        LOGGER.info(AnswerStatus.MISTAKES_INFO.toString(), attempt, Configuration.MAX_ATTEMPTS);
    }

    public void printIfGuessLetterCorrectly(Word guessedWord, char letter) {
        LOGGER.info(guessedWord.getGuessedString());
        LOGGER.info(AnswerStatus.LETTER_IS_GUESSED.toString(), letter);
    }

    public void printIfDidntGuess(Word word) {
        LOGGER.info(word.getGuessedString());
        LOGGER.info(AnswerStatus.LETTER_IS_NOT_GUESSED);

    }

    public void printGivingUp() {
        LOGGER.info(AnswerStatus.GIVE_UP);
    }

    public void printIfWin() {
        LOGGER.info(AnswerStatus.WIN);
    }

    public void printIfDefeat() {
        LOGGER.info(AnswerStatus.LOSE);
    }

}
