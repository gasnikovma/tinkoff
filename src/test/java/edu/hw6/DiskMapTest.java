package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {

    private Path path;

    @BeforeEach
    public void init(){
        path = Paths.get("src/main/java/edu/hw6/task1").resolve("diskMap");
    }

    @Test
    @DisplayName("проверка на вхождение")
    void diskMap_CheckEntry() throws IOException {

        DiskMap diskMap = new DiskMap();
        diskMap.put("Gasnikov", "Matvey");
        diskMap.put("Ivanov", "Sergey");
        assertTrue(diskMap.containsKey("Gasnikov"));
        assertTrue(diskMap.containsValue("Sergey"));
        Path first = path.resolve("Gasnikov");
        Path second = path.resolve("Ivanov");
        assertTrue(Files.exists(first));
        assertTrue(Files.exists(second));
        assertEquals(Files.readString(first),"Matvey");
        assertEquals(Files.readString(second),"Sergey");
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
        Path first = path.resolve("Gasnikov");
        Path second = path.resolve("Ivanov");
        Path third = path.resolve("Andreev");
        assertTrue(Files.exists(first));
        assertTrue(Files.exists(second));
        assertTrue(Files.exists(third));
        assertEquals(Files.readString(first),"Aleksey");
        assertEquals(Files.readString(second),"Sergey");
        assertEquals(Files.readString(third),"Timofey");
        assertEquals(diskMap.size(),3);




    }

}
