package com.amadornes.bts.reflect;

import java.lang.reflect.Field;

public class ReflectUtilities {

    public static Field getField(Object object, String name) {
        if (object == null) return null;
        return getField(object, object.getClass(), name);
    }

    private static Field getField(Object object, Class<?> c, String name) {
        try {
            return object.getClass().getDeclaredField(name);
        } catch (Exception ignored) {
        }
        try {
            return getField(object, c.getSuperclass(), name);
        } catch (Exception ignored) {
        }
        return null;
    }

}