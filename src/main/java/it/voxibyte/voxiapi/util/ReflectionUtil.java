package it.voxibyte.voxiapi.util;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Provides a set of useful methods to deal with Reflection
 */
public class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static <T extends Annotation> Method getMethodAnnotated(Object object, Class<T> annotationType) {
        Class<?> objectClass = object.getClass();
        for (Method method : objectClass.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(annotationType)) continue;

            return method;
        }

        return null;
    }

    public static <T extends Annotation> T getAnnotationOnClass(Object object, Class<T> annotationType) {
        Class<?> objectClass = object.getClass();
        if (!objectClass.isAnnotationPresent(annotationType)) return null;

        return objectClass.getDeclaredAnnotation(annotationType);
    }

    public static <T extends Annotation> Map<Field, T> getAnnotatedFields(Class<?> clazz, Class<T> annotationType) {
        Map<Field, T> value = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(annotationType)) {
                continue;
            }

            value.put(field, field.getDeclaredAnnotation(annotationType));
        }

        return value;
    }

    public static <T extends Annotation> Optional<T> getAnnotatedClass(Class<?> clazz, Class<T> annotationType) {
        return Optional.ofNullable(clazz.getDeclaredAnnotation(annotationType));
    }

    public static Set<Class<?>> getClassesAnnotated(ClassLoader classLoader, Class<? extends Annotation> annotationType, String packageName) throws IOException {
        ClassPath classPath = ClassPath.from(classLoader);
        Set<Class<?>> classes = new HashSet<>();

        classPath.getTopLevelClassesRecursive(packageName).forEach(classInfo -> {
            Class<?> clazz = classInfo.load();
            if (clazz.isAnnotationPresent(annotationType)) {
                classes.add(clazz);
            }
        });

        return classes;
    }

    public static <T> T obtainInstance(Class<T> clazz) {
        try {
            Constructor<?> emptyConstructor = clazz.getDeclaredConstructor();

            return (T) emptyConstructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Unable to create instance for " + clazz.getSimpleName());
        }
    }

    public static Class<?> getImplementation(Class<?> abstraction) {
        if (abstraction.equals(List.class)) {
            return ArrayList.class;
        } else if (abstraction.equals(Map.class)) {
            return HashMap.class;
        }
        return abstraction;
    }
}
