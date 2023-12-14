package edu.project1.dictionary;

public class State {

    protected State() {

    }

    public boolean isInProgress = true;

    public boolean isInProgess() {
        return isInProgress;
    }

    public void finishGame(final boolean isWon, Message message) {
        isInProgress = false;
        if (isWon) {
            message.printIfWin();
        } else {
            message.printIfDefeat();
        }

    }

}
