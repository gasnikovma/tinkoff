package edu.project1.dictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DictionaryImpl implements Dictionary {
    private final Random random = new Random();

    @Override
    public String randomWord(final String[] words) {
        int randomIndex = random.nextInt(words.length);
        return words[randomIndex];
    }

    @Override
    public Set<Character> getUniqueChar(String word) {
        Set<Character> uniq = new HashSet<>();
        for (char c : word.toCharArray()) {
            uniq.add(c);
        }
        return uniq;
    }

    @Override
    public List<Integer> getPositions(String word, char letter) {
        List<Integer> charArray = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                charArray.add(i);
            }
        }
        return charArray;
    }
}
