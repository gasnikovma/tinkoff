package edu.project1.dictionary;

public class State {

    Message message;

    private State(Message message) {
        this.message = message;

    }

    public static boolean isInProgress = true;

    public static boolean isInProgess() {
        return isInProgress;
    }

    public static void finishGame(final boolean isWon, Message message) {
        isInProgress = false;
        if (isWon) {
            message.printIfWin();
        } else {
            message.printIfDefeat();
        }

    }

}
