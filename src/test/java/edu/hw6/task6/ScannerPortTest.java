package edu.hw6.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ScannerPortTest {

    private static final int PORT = 445;

    @Test
    @DisplayName("проверка udp протокола")
    void checkPortTest_ShouldReturnUDP() throws IOException {
        int port = PORT;
        String protocol = ScannerPort.checkPort(port);
        assertEquals(protocol, "UDP");

    }
}
