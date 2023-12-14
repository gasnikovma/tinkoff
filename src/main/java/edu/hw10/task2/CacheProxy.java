package edu.hw10.task2;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class CacheProxy {
    public static <T> T create(T obj,Class<T> tClass) throws IOException {
        return (T)Proxy.newProxyInstance(tClass.getClassLoader(),new Class[]{tClass},new DynamicInvocationHandler(obj));
    }
}
