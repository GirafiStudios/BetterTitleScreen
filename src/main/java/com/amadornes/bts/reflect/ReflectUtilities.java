package com.amadornes.bts.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtilities {

    public static <T> T getFieldValue(Object object, String name) {

        if (object == null) return null;

        return getFieldValue(object, object.getClass(), name);
    }


    @SuppressWarnings("unchecked")
    private static <T> T getFieldValue(Object object, Class<?> c, String name) {

        try {
            Field f = object.getClass().getDeclaredField(name);

            boolean accessible = f.isAccessible();
            if(!accessible)
                f.setAccessible(true);

            T val = (T) f.get(object);

            f.setAccessible(accessible);

            return val;
        } catch (Exception e) {
            e.printStackTrace();

        }
        try {
            return getFieldValue(object, c.getSuperclass(), name);
        } catch (Exception ex) {
        }
        System.out.println("ERR");
        return null;
    }

    public static Method getMethod(Object object, String name, Class<?>[] params) {
        if (object == null) return null;
        return getMethod(object, object.getClass(), name, params);
    }
    private static Method getMethod(Object object, Class<?> c, String name, Class<?>[] params) {

        try {
            return object.getClass().getDeclaredMethod(name, params);
        } catch (Exception ex) {
        }
        try {
            return getMethod(object, c.getSuperclass(), name, params);
        } catch (Exception ex) {
        }
        return null;
    }
    public static Field getField(Object object, String name) {
        if (object == null) return null;
        return getField(object, object.getClass(), name);
    }
    private static Field getField(Object object, Class<?> c, String name) {
        try {
            return object.getClass().getDeclaredField(name);
        } catch (Exception ex) {
        }
        try {
            return getField(object, c.getSuperclass(), name);
        } catch (Exception ex) {
        }
        return null;
    }

}