package edu.project1.dictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Configuration {

    private final Random random = new Random();
    public static final int MAX_ATTEMPTS = 5;
    public static String[] words = new String[] {"java", "kotlin", "programming"};

    protected Configuration() {
    }

    public String getRandomWord(final String[] words) {
        int randomIndex = random.nextInt(words.length);
        return words[randomIndex];
    }

    public Set<Character> getUniqueChars(String word) {
        Set<Character> uniq = new HashSet<>();
        for (char c : word.toCharArray()) {
            uniq.add(c);
        }
        return uniq;
    }

    public List<Integer> getLetterPositions(String word, char letter) {
        List<Integer> charArray = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                charArray.add(i);
            }
        }
        return charArray;
    }

}
