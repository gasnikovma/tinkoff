package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {

    @Test
    @DisplayName("проверка на вхождение")
    void diskMap_CheckEntry() throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("Gasnikov", "Matvey");
        diskMap.put("Ivanov", "Sergey");
        assertTrue(diskMap.containsKey("Gasnikov"));
        assertTrue(diskMap.containsValue("Sergey"));
    }

    @Test
    @DisplayName("проверка на вхождение после удаления и добавление дубликатов")
    void diskMap_CheckEntryAfterDeletionAndChangeValue() throws IOException {
        DiskMap diskMap = new DiskMap();
        diskMap.put("Gasnikov", "Matvey");
        diskMap.put("Ivanov", "Sergey");
        diskMap.put("Gasnikov","Aleksey");
        diskMap.put("Vorobiev","Anton");
        diskMap.put("Andreev","Timofey");
        diskMap.remove("Vorobiev") ;
        assertEquals(diskMap.get("Gasnikov"),"Aleksey");
        assertFalse(diskMap.containsKey("Vorobiev"));
        assertEquals(diskMap.size(),3);




    }

}
