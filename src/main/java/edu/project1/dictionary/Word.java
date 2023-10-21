package edu.project1.dictionary;

import java.util.List;
import java.util.Set;

public class Word {
    private static String hiddenWord;
    private static Set<Character> letterInWord;
    private final StringBuilder guessedString;
    private final Dictionary dictionary;


    public StringBuilder getGuessedString() {
        return guessedString;
    }

    public Word(final String[] words, Dictionary dictionary) {
        this.dictionary = dictionary;
        hiddenWord = dictionary.randomWord(words);
        letterInWord = dictionary.getUniqueChar(hiddenWord);
        this.guessedString = new StringBuilder("*".repeat(hiddenWord.length()));
    }

    public boolean isCharInWord(final char letter) {
        if (letterInWord.contains(letter)) {
            List<Integer> positions = dictionary.getPositions(hiddenWord, letter);
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
