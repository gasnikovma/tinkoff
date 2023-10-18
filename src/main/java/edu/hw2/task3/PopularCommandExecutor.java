package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int currAttempt = 1;
        while (currAttempt <= maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (Exception e) {
                if (currAttempt == maxAttempts) {
                    throw new ConnectionException(e);
                }
            }
            currAttempt += 1;
        }
    }
}
