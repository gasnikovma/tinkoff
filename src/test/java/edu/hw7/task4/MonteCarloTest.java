package edu.hw7.task4;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

class MonteCarloTest {

    @Test
    void countPI() {
        MultiThreadMonteCarlo monteCarlo = new MultiThreadMonteCarlo();
        monteCarlo.countPI(100000);
        monteCarlo.getError();

    }

    @Test
    void OridnalCountPI() {
        MonteCarlo monteCarlo = new MonteCarlo();
        monteCarlo.countPI(100000);
        monteCarlo.getError();
    }
}
