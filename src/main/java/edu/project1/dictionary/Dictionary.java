package edu.project1.dictionary;

import java.util.List;
import java.util.Set;

interface Dictionary {
    String randomWord(String[] words);

    Set<Character> getUniqueChar(String word);

    List<Integer> getPositions(String word, char letter);

}
