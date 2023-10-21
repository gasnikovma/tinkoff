package edu.project1.dictionary;

import java.util.Scanner;

public class Session {
    private final Dictionary dictionary;
    private final Message message;
    private final Reader reader;

    public Session(Dictionary dictionary, Message message, Reader reader) {
        this.dictionary = dictionary;
        this.message = message;
        this.reader = reader;

    }

    public void playGame() {
        Word word = new Word(Configuration.words, dictionary);
        message.startGame();

        while (State.isInProgess()) {
            String in = input();
            Character inputLetter = inputLetter(in);
            if (!inputLetter.equals('\u0000')) {
                if (word.isCharInWord(inputLetter)) {
                    GuessCorrectLetter letterGuess = new GuessCorrectLetter(message);
                    letterGuess.handleGuessedLetter(word, inputLetter);

                } else {
                    GuessIncorrectLetter letterGuess = new GuessIncorrectLetter(message);
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
            State.finishGame(false, message);
            return 0;
        } else {
            State.finishGame(false, message);
            throw new IllegalArgumentException("more than one letter");

        }
    }
}
