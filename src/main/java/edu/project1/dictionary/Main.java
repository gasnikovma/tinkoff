package edu.project1.dictionary;

public class Main {
    private Main() {

    }

    public static void main(final String[] args) {
        Configuration configuration = new Configuration();
        Message message = new Message();
        Reader reader = new ReaderImpl();
        State state = new State();
        Session session = new Session(configuration, message, reader, state);
        session.playGame();
    }
}
