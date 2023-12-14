package edu.hw8.task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Utils {
    private Utils() {

    }

    public static String getAns(ByteBuffer buffer, SocketChannel socketChannel) throws IOException {
        StringBuilder message = new StringBuilder();
        while (message.isEmpty() || message.charAt(message.length() - 1) != '\n') {
            buffer.flip();
            message.append(StandardCharsets.UTF_16.decode(buffer));
            buffer.clear();
            socketChannel.read(buffer);
        }
        buffer.clear();
        return message.toString();
    }

}
