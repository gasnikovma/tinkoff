package edu.project1.dictionary;

public class GuessIncorrectLetter {

    private final Message message;
    private final State state;
    public static int attempts = 0;

    public GuessIncorrectLetter(Message message, State state) {
        this.message = message;
        this.state = state;
    }

    @SuppressWarnings("checkstyle:magicnumber")
    public void handleNotGuessedLetter(Word word) {
        message.printIfDidntGuess(word);
        attempts += 1;
        message.printMistakes(attempts);
        if (attempts >= 3) {
            message.printGivingUp();
        }
        if (attempts == Configuration.MAX_ATTEMPTS) {
            state.finishGame(false, message);
        }

    }

}
