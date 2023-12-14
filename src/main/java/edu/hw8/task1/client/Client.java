package edu.hw8.task1.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.task1.Utils.getAns;

@SuppressWarnings("UncommentedMain")
public class Client {

    private Client() {

    }

    private static final String HOST = "localhost";
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int PORT = 8080;

    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(HOST, PORT);
        try (SocketChannel socketChannel = SocketChannel.open(inetSocketAddress)) {
            socketChannel.configureBlocking(false);

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    LOGGER.info("Ваня: ");
                    String keyWord = scanner.nextLine();
                    if ("finish".equalsIgnoreCase(keyWord)) {
                        LOGGER.info("Disconnected");
                        break;
                    }
                    ByteBuffer buffer = ByteBuffer.wrap((keyWord + "\n").getBytes(StandardCharsets.UTF_16));
                    while (buffer.hasRemaining()) {
                        socketChannel.write(buffer);
                    }
                    buffer.clear();
                    String answer = getAns(buffer, socketChannel);
                    LOGGER.info("Сервер:" + answer);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Unable to communicate with server", e);
        }
    }
}




