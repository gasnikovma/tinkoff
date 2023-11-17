package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScannerPort {

    private static final int MAX_PORT = 49151;
    private final static Logger LOGGER = LogManager.getLogger();

    private ScannerPort() {

    }

    public static void printer() throws IOException {
        LOGGER.info("ПРОТОКОЛ\tПОРТ\tСЕРВИС");
        Map<Integer, String> ports = createMapOfPorts();
        for (int i = 0; i < MAX_PORT; i++) {
            String service = ports.getOrDefault(i, "");
            LOGGER.info(checkPort(i) + '\t' + i + '\t' + service);
        }

    }

    public static String checkPort(int port) throws IOException {
        ServerSocket serverSocket = null;
        DatagramSocket datagramSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            return "TCP";
        } catch (IOException e) {
            try {
                datagramSocket = new DatagramSocket(port);
                return "UDP";
            } catch (IOException exception) {
                return "";
            } finally {
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
            }
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }

    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static Map<Integer, String> createMapOfPorts() {
        Map<Integer, String> ports = new HashMap<>();
        ports.put(7, "ECHO - testing communication by sending data to the server and receiving the same from it");
        ports.put(21, "FTP - file transfer protocol");
        ports.put(22, "SSH - secure shell");
        ports.put(25, "SMTP - simple mail transfer protocol");
        ports.put(53, "DNS - domain name system");
        ports.put(80, "HTTP - hyperText transfer protocol");
        ports.put(443, "HTTPS - hyperText transfer protocol secure");
        return ports;
    }

}
