package edu.project1.dictionary;

import java.util.List;
import java.util.Set;

public class Word {
    private static String hiddenWord;
    private static Set<Character> letterInWord;
    private final StringBuilder guessedString;
    private final Configuration configuration;


    public StringBuilder getGuessedString() {
        return guessedString;
    }

    public Word(final String[] words, Configuration configuration) {
        this.configuration = configuration;
        hiddenWord = configuration.getRandomWord(words);
        letterInWord = configuration.getUniqueChars(hiddenWord);
        this.guessedString = new StringBuilder("*".repeat(hiddenWord.length()));
    }

    public boolean isCharInWord(final char letter) {
        if (letterInWord.contains(letter)) {
            List<Integer> positions = configuration.getLetterPositions(hiddenWord, letter);
            for (int i = 0; i < positions.size(); i++) {
                guessedString.setCharAt(positions.get(i), hiddenWord.charAt(positions.get(i)));
            }
            letterInWord.remove(letter);
            return true;
        }
        return false;
    }

    public boolean isGuessed() {
        return letterInWord.size() == 0;
    }

}
