package edu.hw8.task1.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("UncommentedMain")
public class ClientSecond {
    private ClientSecond() {

    }

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 1700;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BUFFER_CAPACITY = 1024;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                LOGGER.info("Ваня:");
                String message = scanner.nextLine();
                try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                     OutputStream outputStream = socket.getOutputStream();
                     InputStream inputStream = socket.getInputStream()) {
                    outputStream.write(message.getBytes());
                    byte[] buffer = new byte[BUFFER_CAPACITY];
                    int bytesRead = inputStream.read(buffer);
                    String response = new String(buffer, 0, bytesRead);
                    LOGGER.info("Сервер: " + response);
                } catch (IOException e) {
                    throw new RuntimeException("Unable to communicate with server", e);
                }
            }

        }
    }
}
