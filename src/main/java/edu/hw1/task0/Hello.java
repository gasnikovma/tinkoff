package edu.hw1.task0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Hello {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void greeting() {
        LOGGER.info("Hello World");
    }
}
