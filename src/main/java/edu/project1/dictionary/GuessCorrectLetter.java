package edu.project1.dictionary;

public class GuessCorrectLetter {
    private final Message message;

    public GuessCorrectLetter(Message message) {
        this.message = message;
    }

    public void handleGuessedLetter(final Word word, final char letter) {
        message.printIfGuessLetterCorrectly(word, letter);
        if (word.isGuessed()) {
            State.finishGame(true, message);
        }
    }

}
