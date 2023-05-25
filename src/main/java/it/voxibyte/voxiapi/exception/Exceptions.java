package it.voxibyte.voxiapi.exception;

import java.lang.reflect.InvocationTargetException;

public class Exceptions {

    public static <T extends RuntimeException> void raiseException(Class<T> exceptionType, String message) {
        try {
            throw exceptionType.getDeclaredConstructor(String.class).newInstance(message);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

}
