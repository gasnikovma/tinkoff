package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.Test;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodEditor {
    @Test
    public void shouldCallOurFunction() throws InstantiationException, IllegalAccessException {
        int expected = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum")
                .and(isDeclaredBy(ArithmeticUtils.class)
                    .and(returns(int.class))))
            .intercept(MethodDelegation.to(MultiplicationInterceptor.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .newInstance()
            .sum(4, 5);

        assertEquals(20, expected);
    }
}
