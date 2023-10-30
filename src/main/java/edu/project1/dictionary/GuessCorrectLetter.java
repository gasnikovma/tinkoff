package edu.project1.dictionary;

public class GuessCorrectLetter {
    private final Message message;
    private final State state;

    public GuessCorrectLetter(Message message, State state) {
        this.message = message;
        this.state = state;
    }

    public void handleGuessedLetter(final Word word, final char letter) {
        message.printIfGuessLetterCorrectly(word, letter);
        if (word.isGuessed()) {
            state.finishGame(true, message);
        }
    }

}
