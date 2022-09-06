package proxy;

import annotations.Cacheable;
import services.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {

    private final Service original;
    private final String folder;
    private final String fileName;

    public MyHandler(Service original, String folder, String fileName) {
        this.original = original;
        this.folder = folder;
        this.fileName = fileName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isAnnotationPresent(Cacheable.class)) {
            return cacheHandlingOn(method, args);
        } else {
            return cacheHandlingOff(method, args);
        }
    }

    private Object cacheHandlingOn(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

        System.out.println("Обнаружена аннотация Cacheable. Ищем сохраненный результат.");

        Object obj;

        String prefix = method.getDeclaredAnnotation(Cacheable.class).filePrefix();
        StringBuilder builderForName = new StringBuilder(prefix + fileName);
        for (Object arg : (Object[]) args[0]) {
            builderForName.append("_").append(arg);
        }
        String fileNameWithArgs = builderForName.toString();

        boolean isZip = method.getDeclaredAnnotation(Cacheable.class).zip();

        if (Search.searchInFolder(isZip, folder, fileNameWithArgs)) {
            System.out.println("Возвращаем КЭШированное значение:");
            obj = DeserializeObject.deserialization(isZip, folder, fileNameWithArgs);
        } else {
            System.out.println("КЭШируем результат и возвращаем его:");

            obj = method.invoke(original, args);

            SerializeObject.serialization(isZip, obj, folder, fileNameWithArgs);
        }
        System.out.println(obj.toString());
        return obj;
    }

    private Object cacheHandlingOff(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Аннотация Cacheable не обнаружена. Обычное выполнение метода.");

        StringBuilder builderForName = new StringBuilder(fileName);
        for (Object arg : (Object[]) args[0]) {
            builderForName.append("_").append(arg);
        }
        String fileNameWithArgs = builderForName.toString();

        Object obj = method.invoke(original, args);

        SerializeObject.serialization(false, obj, folder, fileNameWithArgs);

        System.out.println(obj.toString());
        return obj;
    }
}