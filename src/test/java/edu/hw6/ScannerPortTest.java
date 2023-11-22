package edu.hw6;

import edu.hw6.task6.ScannerPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ScannerPortTest {

    @Test
    @DisplayName("проверка udp протокола")
    void checkPortTest_ShouldReturnUDP() throws IOException {
        String protocol = ScannerPort.checkPort(445);
        assertEquals(protocol, "");

    }
}
