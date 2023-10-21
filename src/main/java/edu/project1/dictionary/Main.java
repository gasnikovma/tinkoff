package edu.project1.dictionary;

public class Main {
    private Main() {

    }

    public static void main(final String[] args) {
        Dictionary dictionary = new DictionaryImpl();
        Message message = new MessageImpl();
        Reader reader = new ReaderImpl();
        Session session = new Session(dictionary, message, reader);
        session.playGame();
    }
}
