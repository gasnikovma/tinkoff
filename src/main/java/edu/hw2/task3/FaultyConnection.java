package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static double CEIL = 0.9;

    @Override
    public void execute(String command) {
        if (Math.random() < CEIL) {
            throw new ConnectionException();
        }
        LOGGER.info(String.format("{%s} is executed\n", command));

    }

    @Override
    public void close() {
        LOGGER.info("Connection is closed\n");
    }
}
