package io.github.crudapp.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Helper {

     static List<Field> getNonNullFields(Object update) {
        Field[] fields = update.getClass().getSuperclass().getDeclaredFields();

        return Arrays.stream(fields)
                .filter(f -> {
                    f.setAccessible(true);
                    try {
                        return f.get(update) != null;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .toList();
    }

    static String getSetClause(List<Field> fields) {
        return fields.stream()
                .map(f -> f.getName() + " = ?")
                .collect(Collectors.joining(", "));
    }

    static List<Object> collectParameters(List<Field> nonNullFields, Object update) {
        List<Object> params = new ArrayList<>();
        for (Field f : nonNullFields) {
            try {
                params.add(f.get(update));
            } catch (IllegalAccessException e) {
                System.err.println("Failed to access field: " + f.getName());
                e.printStackTrace();
            }
        }
        return params;
    }

}
