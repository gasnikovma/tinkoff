package edu.hw8.task1.server;

import edu.hw8.task1.Storage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("UncommentedMain")
public class ServerSecond {

    private ServerSecond() {

    }

    private static final int PORT = 1700;
    private static final int MAX_CONNECTIONS = 10;
    private static final int BUFFER_CAPACITY = 1024;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Server started on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.submit(() -> handleRequest(socket));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void handleRequest(Socket serverSocket) {
        try (InputStream inputStream = serverSocket.getInputStream();
             OutputStream outputStream = serverSocket.getOutputStream()) {
            byte[] buffer = new byte[BUFFER_CAPACITY];
            int bytesRead = inputStream.read(buffer);
            String request = new String(buffer, 0, bytesRead);

            String response = Storage.getReply(request.toLowerCase());
            outputStream.write(response.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
