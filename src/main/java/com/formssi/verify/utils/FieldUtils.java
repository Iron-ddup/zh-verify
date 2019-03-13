package com.formssi.verify.utils;

import jodd.util.ReflectUtil;

import java.lang.reflect.Field;

public final class FieldUtils {
    public static Field[] getDeclaredFields(Class<?> cl, boolean includeParent) {
        if (includeParent) {
            return ReflectUtil.getSupportedFields(cl);
        } else {
            return cl.getDeclaredFields();
        }
    }
}