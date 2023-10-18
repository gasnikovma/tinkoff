package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {

    @Override @SuppressWarnings("MagicNumber") public Connection getConnection() {
        if (Math.random() < 0.9) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
