package edu.hw8;

import edu.hw8.task1.Storage;
import edu.hw8.task1.client.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {

    @Test
    void testStorage(){
        String keyword = "личности";
        String response = Storage.getReply(keyword);
        String expected = "Не переходи на личности там, где их нет.\n";
        assertEquals(expected,response);
    }


}
