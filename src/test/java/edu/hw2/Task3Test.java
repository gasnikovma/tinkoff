package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    public final int MAX_ATTEMPTS_1 = 30;
    public final int MAX_ATTEMPTS_2 = 1;

    @Test
    @DisplayName("получить стабильное соединение через DefaultConnectionManager")
    void Connection_ShouldGetStableConnection() {
        boolean isExecuted;

        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(), MAX_ATTEMPTS_1);
        try {
            popularCommandExecutor.updatePackages();
            isExecuted = true;
        } catch (ConnectionException exception) {
            isExecuted = false;
        }

        assertThat(isExecuted).isTrue();
    }

   /* @Test
    @DisplayName("получить ошибку через DefaultConnectionManager")
    void Connection_ShouldGetFaulty() {
        boolean isExecuted;
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(), MAX_ATTEMPTS_2);
        try {
            popularCommandExecutor.updatePackages();
            isExecuted = true;
        } catch (ConnectionException exception) {
            isExecuted = false;
        }

        assertThat(isExecuted).isFalse();
    }

   /* @Test
    @DisplayName("получить ошибку через FaultyConnectionManager")
    void Connection_ShouldGetFaultyWithinFaultyManager() {
        boolean isExecuted;
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new FaultyConnectionManager(), MAX_ATTEMPTS_2);
        try {
            popularCommandExecutor.updatePackages();
            isExecuted = true;
        } catch (ConnectionException exception) {
            isExecuted = false;
        }

        assertThat(isExecuted).isFalse();
    }*/

}
