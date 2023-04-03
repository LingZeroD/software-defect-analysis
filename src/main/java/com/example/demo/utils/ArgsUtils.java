package com.example.demo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ArgsUtils<T> {
    public String[] convertToPythonArgs(T params) {
        List<String> args = new ArrayList<>();
        try {
            Class<?> cls = params.getClass();
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                String argName = "-" + field.getName();
                Object argValue = field.get(params);
                args.add(argName);
                args.add(argValue.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return args.toArray(new String[0]);
    }



}
