package edu.project1.dictionary;

public enum AnswerStatus {
    START("The word was made up"),

    LETTER_IS_GUESSED("Hit! The letter is %c"),
    LETTER_IS_NOT_GUESSED("Wrong!"),
    WIN("You won!"),
    LOSE("You lose"),
    MISTAKES_INFO("Mistakes %d of %d"),

    GIVE_UP("If you want to finish the game, please enter \"give up\" ");

    private final String userMessage;

    AnswerStatus(String userMessage) {
        this.userMessage = userMessage;
    }

    @Override
    public String toString() {
        return this.userMessage;
    }

}
