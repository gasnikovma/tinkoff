package edu.hw1.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    public static void main(String[] args) {
       LOGGER.info(Length_Of_Video.length("10:60"));
    }
}
