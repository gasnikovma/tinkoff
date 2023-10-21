package edu.project1.dictionary;

public interface Message {
    void startGame();

    void printMistakes(int attempt);

    void printIfGuessLetterCorrectly(Word guessedWord, char letter);

    void printIfDidntGuess(Word guessedWord);

    void printGivingUp();

    void printIfWin();

    void printIfDefeat();

}
