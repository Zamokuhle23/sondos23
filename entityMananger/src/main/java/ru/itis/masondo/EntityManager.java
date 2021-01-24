package ru.itis.masondo;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class EntityManager {
    private DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public <T> void createTable(String tableName, Class<T> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        StringBuilder query = new StringBuilder("CREATE TABLE " + tableName + "( ");
        for (int i = 0; i < fields.length; i++) {
            Class<?> type = fields[i].getType();
            String nameOfField = fields[i].getName();
            if (!type.getName().equals("int")&&!type.getName().equals("java.lang.Long")&&!type.getName().equals("boolean")&&!type.getName().equals("java.lang.String")) {
                query.append(nameOfField).append(" ").append("varchar(255)").append(", ");
            }
            if (type.getName().equals("int")) {
                query.append(nameOfField).append(" ").append("integer").append(", ");
            }
            if (type.getName().equals("java.lang.Long")) {
                query.append(nameOfField).append(" ").append("bigint").append(", ");
            }
            if (type.getName().equals("boolean")) {
                query.append(nameOfField).append(" ").append("boolean").append(", ");
            }
            if (type.getName().equals("java.lang.String")) {
                query.append(nameOfField).append(" ").append("varchar(255)").append(", ");
            }
            if (i == fields.length - 1) {
                query.delete(query.length() - 1, query.length());
                query.append(");");
            }
        }
        execute(query.toString());
    }

    public void save(String tableName, Object entity) throws IllegalAccessException {
        Class<?> object = entity.getClass();
        Field[] fields = object.getDeclaredFields();
        StringBuilder query = new StringBuilder("insert into " + tableName + " values ( ");
        for (int i = 0; i < fields.length; i++) {
            Class<?> type = fields[i].getType();
            if (!type.getName().equals("int")&&!type.getName().equals("java.lang.Long")&&!type.getName().equals("boolean")&&!type.getName().equals("java.lang.String")) {
                String value = fields[i].get(entity).toString();
                query.append("'").append(value).append("'").append(", ");
            }
            if (type.getName().equals("int")) {
                int value = fields[i].getInt(entity);
                query.append(value).append(", ");
            }
            if (type.getName().equals("java.lang.Long")) {
                long value = fields[i].getLong(entity);
                query.append(value).append(", ");
            }
            if (type.getName().equals("boolean")) {
                boolean value = fields[i].getBoolean(entity);
                query.append(value).append(", ");
            }
            if (type.getName().equals("java.lang.String")) {
                String value = fields[i].get(entity).toString();
                query.append("'").append(value).append("'").append(", ");
            }
            if (i == fields.length - 1) {
                query.delete(query.length() - 1, query.length());
                query.append(");");
            }
        }
        execute(query.toString());
    }
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        Field[] fields = resultType.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        String query = "select * from " + tableName + " " + "where id = " + idValue + ";";
        ResultSet resultSet;
        try {
            resultSet = this.dataSource.getConnection().prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        T entity;
        try {
            entity = resultType.getConstructor().newInstance();
            for (Field field : fields) {
                String name = field.getName();
                field.set(entity, resultSet.getString(name));
            }
        } catch (IllegalAccessException | InstantiationException | SQLException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
        return entity;
    }

    public void execute(String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(String.valueOf(query));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new IllegalArgumentException(throwables);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {

                }
            }
        }
    }





}
