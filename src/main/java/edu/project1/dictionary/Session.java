package edu.project1.dictionary;

public class Session {
    private final Configuration configuration;
    private final Message message;
    private final Reader reader;
    private final State state;

    public Session(Configuration configuration, Message message, Reader reader,State state) {
        this.configuration = configuration;
        this.message = message;
        this.reader = reader;
        this.state=state;

    }

    public void playGame() {
        Word word = new Word(Configuration.words, configuration);
        message.startGame();
        while (state.isInProgess()) {
            String in = input();
            Character inputLetter = inputLetter(in);
            if (!inputLetter.equals('\u0000')) {
                if (word.isCharInWord(inputLetter)) {
                    GuessCorrectLetter letterGuess = new GuessCorrectLetter(message,state);
                    letterGuess.handleGuessedLetter(word, inputLetter);

                } else {
                    GuessIncorrectLetter letterGuess = new GuessIncorrectLetter(message,state);
                    letterGuess.handleNotGuessedLetter(word);
                }
            }

        }
    }

    private String input() {
        return reader.getLine();
    }

    public char inputLetter(String input) {
        if (input.length() == 1) {
            return input.charAt(0);
        } else if (input.equalsIgnoreCase("give up")) {
            state.finishGame(false, message);
            return 0;
        } else {
            state.finishGame(false, message);
            throw new IllegalArgumentException("more than one letter");

        }
    }
}
