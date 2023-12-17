package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class Benchmark {
    private Student student;
    private Method directMethod;
    private static final String LAMBDA_METHOD = "get";
    private static final String RECORD_GETTER = "name";
    private MethodHandle methodHandle;
    private Supplier<String> supplier;

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:MagicNumber"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(Benchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();
        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexandr", "Biryukov");

        directMethod = Student.class.getMethod(RECORD_GETTER);

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, RECORD_GETTER, MethodType.methodType(String.class));

        Method lambdaMethod = Supplier.class.getDeclaredMethod(LAMBDA_METHOD);
        MethodType lambdaMethodType =
            MethodType.methodType(lambdaMethod.getReturnType(), lambdaMethod.getParameterTypes());
        MethodType methodType = MethodType.methodType(directMethod.getReturnType(), directMethod.getParameterTypes());
        MethodHandle implMethodHandle = lookup.findVirtual(Student.class, directMethod.getName(), methodType);

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            //The name of the calling interface of the interface specified below
            LAMBDA_METHOD,
            // site.target.invokeExact()The interface class that appears when you do
            MethodType.methodType(Supplier.class, Student.class),
            //Argument information, generic()By doing so
            lambdaMethodType,
            //MethodHandle of the called function
            implMethodHandle,
            //Argument information
            methodType
        );
        supplier = (Supplier<String>) callSite.getTarget().invoke(student);

    }

    @org.openjdk.jmh.annotations.Benchmark
    public void directAccess(Blackhole bh) throws Exception {
        bh.consume(student.name());
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void reflectMethod(Blackhole bh) throws Exception {
        bh.consume(directMethod.invoke(student));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void methodHandleAccess(Blackhole bh) throws Throwable {
        bh.consume((String) methodHandle.invoke(student));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void testLambdaFactory(Blackhole bh) {
        String name = supplier.get();
        bh.consume(name);
    }

}
