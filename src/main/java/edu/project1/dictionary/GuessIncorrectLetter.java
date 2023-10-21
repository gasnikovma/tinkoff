package edu.project1.dictionary;

public class GuessIncorrectLetter {

    private final Message message;
    private static int attempts = 0;

    public GuessIncorrectLetter(Message message) {
        this.message = message;
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
            State.finishGame(false, message);
        }

    }

}
