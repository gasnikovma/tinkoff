package edu.project1.dictionary;

import java.util.Scanner;

public class Session {
    private static final Dictionary Dictionary = new DictionaryImpl();
    private static final Message Message = new MessageImpl();

    private Session() {

    }

    public static void playGame() {
        Word word = new Word(Configuration.words, Dictionary);
        Message.startGame();

        while (State.isInProgess()) {

            Character inputLetter = inputLetter();

            if (!inputLetter.equals('\u0000')) {
                if (word.isCharInWord(inputLetter)) {
                    GuessCorrectLetter letterGuess = new GuessCorrectLetter(Message);
                    letterGuess.handleGuessedLetter(word, inputLetter);

                } else {
                    GuessIncorrectLetter letterGuess = new GuessIncorrectLetter(Message);
                    letterGuess.handleNotGuessedLetter(word);
                }
            }

        }
    }

    private static char inputLetter() {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.length() == 1) {
            return input.charAt(0);
        } else if (input.equals("give up") || input.equals("GIVE UP")) {
            State.finishGame(false, Message);
            return 0;
        } else {
            return 0;
        }
    }
}
