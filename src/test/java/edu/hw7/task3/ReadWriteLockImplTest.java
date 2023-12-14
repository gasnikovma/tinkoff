package edu.hw7.task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.*;

class ReadWriteLockImplTest {
    private ReadWriteLockImpl database;
    private ExecutorService executorService;

    @BeforeEach
    void init() throws ExecutionException, InterruptedException {
        executorService = Executors.newFixedThreadPool(2);
        database = new ReadWriteLockImpl();
        Runnable task1 = () -> {
            database.add(new Person(1, null, "Mytischi", "7953"));
            database.add(new Person(2, "Matvey", "Kolomna", "7911"));
            database.add(new Person(3, "Elena", "Moscow", "7953"));
            database.add(new Person(4, "Aleksey", "Mytischi", "7623"));
            database.add(new Person(5, "Nikolay", "Kolomna", null));
        };
        Runnable task2 = () -> {
            database.add(new Person(6, null, "Moscow", "7915"));
            database.add(new Person(7, "Matvey", "Balashiha", "7932"));
            database.add(new Person(8, "Elena", null, "7911"));
            database.add(new Person(9, "Sergey", "Mytischi", "7934"));
            database.delete(5);
            database.delete(7);
        };
        executorService.submit(task1).get();
        executorService.submit(task2).get();
    }

    @Test
    void FindByNameTest() {
        List<Person> byName = database.findByName("Elena");
        assertEquals(byName.size(), 1);
        assertEquals(byName.get(0).id(), 3);
    }

    @Test
    void FindByPhoneNumberTest() {
        List<Person> byPhone = database.findByPhone("7911");
        assertEquals(byPhone.size(), 1);
        assertEquals(byPhone.get(0).id(), 2);
    }

    @AfterEach
    void destroy() {
        executorService.shutdown();
    }

}
