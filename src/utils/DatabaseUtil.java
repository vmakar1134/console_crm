package utils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

    public static Long getGeneratedKey(PreparedStatement statement) throws SQLException {
        Long id = null;
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = (generatedKeys.getLong(1));
            }
        }
        return id;
    }

    public static <T> Long getId(T entity) {
        if (entity == null) {
            return null;
        }
        Class<?> entityClass = entity.getClass();
        return getIdField(entityClass);
    }

    private static Long getIdField(Class<?> aClass) {
        Long id = null;
        try {
            Field field = aClass.getDeclaredField("id");
            field.setAccessible(true);
            Object object = null;
            id = (Long) field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return id;
    }

}
