package edu.hw10.task1;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomObjectGeneratorTest {
    @Test
    void testGenerateTestClass()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        RandomObjectGenerator rog = new RandomObjectGenerator();
        ClassTest testObject = rog.nextObject(ClassTest.class);
        assertNotNull(testObject.getNotNullField());
        assertTrue(testObject.getMinField() >= 10);
        assertTrue(testObject.getMaxField() <= 50);
    }
    @Test
    void testGenerateRecordClass()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        RecordTest testRecord = rog.nextObject(RecordTest.class);
        assertNotNull(testRecord.name());
        assertTrue(testRecord.age() <= 33);

    }

}
