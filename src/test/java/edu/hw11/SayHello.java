package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SayHello {
    @Test
    @DisplayName("SayHello")
    public void shouldCreateClassWithToStringReturnsHello()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String helloString = "Hello, ByteBuddy!";
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString"))
            .intercept(FixedValue.value(helloString))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        assertEquals(helloString, dynamicType.getDeclaredConstructor().newInstance().toString());
    }
}
