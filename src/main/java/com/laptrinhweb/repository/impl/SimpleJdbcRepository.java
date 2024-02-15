package com.laptrinhweb.repository.impl;

import com.laptrinhweb.annotation.Column;
import com.laptrinhweb.annotation.Entity;
import com.laptrinhweb.annotation.Table;
import com.laptrinhweb.mapper.ResultsetMapper;
import com.laptrinhweb.repository.JdbcRepository;
import com.laptrinhweb.utils.ConnectionUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleJdbcRepository <T> implements JdbcRepository <T>{

    private Class<T> tClass;


    public SimpleJdbcRepository(){
        tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Override
    public List<T> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<T> results = new ArrayList<>();
        try{
            conn = ConnectionUtils.getConnection();
            stmt = conn.createStatement();

            String tableName = null;
            //isAnnotationPresent kểm tra xem buildingEntity có Annotation @Entity không.
            //isAnnotationPresent kiểm tra xem buildingEntity có @Table không.
            if(tClass.isAnnotationPresent(Entity.class) &&tClass.isAnnotationPresent(Table.class)){
                Table table = tClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            String sql = "select * from " + tableName + "";
            String sqlDebug = sql.toString();
            rs = stmt.executeQuery(sqlDebug);
            //tclass = buildingEntity(do truyền từ buildingrepository vào nên biết).
            //

            Field[] fields = tClass.getDeclaredFields();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            while (rs.next()){
                T object = tClass.newInstance();
                for(int i = 0; i < resultSetMetaData.getColumnCount(); i++){
                    String colummName = resultSetMetaData.getColumnName(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    //loop in fields
                    for(Field field : fields){
                        if(field.isAnnotationPresent(Column.class)){
                            Column column = field.getAnnotation(Column.class);
                            if(column.name().equals(colummName)&& columnValue != null){
                                BeanUtils.setProperty(object, field.getName(), columnValue);
                                break;
                            }
                        }
                    }
                }
                results.add(object);
                //get column Name
                //get column value
                //loop in fields

            }
        }catch(SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        } finally {
            try {
               conn.close();
               stmt.close();
               rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void insert(Objects objects) {
       Connection conn = null;
       PreparedStatement stmt = null;
       try{
           conn = ConnectionUtils.getConnection();
           StringBuilder sql = createSQLInsert();
           stmt = conn.prepareStatement(sql.toString());
            Class<?> zClass = objects.getClass();
            int paramentIndex = 1;
            for(Field field : zClass.getDeclaredFields()){
                field.setAccessible(true);
                stmt.setObject(paramentIndex,field.get(objects));
                paramentIndex++;
            }
            Class<?> parentClass = zClass.getSuperclass();
            while (parentClass != null && parentClass !=  Objects.class){
                for(Field field : zClass.getDeclaredFields()){
                    field.setAccessible(true);
                    stmt.setObject(paramentIndex,field.get(objects));
                    paramentIndex++;
                }
                parentClass = parentClass.getSuperclass();
            }
            stmt.executeUpdate();
       }catch (SQLException |IllegalAccessException e){
            e.printStackTrace();
       }
    }

    private StringBuilder createSQLInsert() {
        String tableName = "";
        StringBuilder fields = new StringBuilder("");
        StringBuilder value = new StringBuilder("");

        //ham lay name
        if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)){
            Table table  = tClass.getAnnotation(Table.class);
            tableName = table.name();
        }
        //ham lay fields

        for(Field field : tClass.getDeclaredFields()){
            if(fields.length() > 1){
                fields.append(",");
                value.append(",");
            }
            if(field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                fields.append(column.name());
                value.append("?");
            }
        }

        Class<?> parentClass = tClass.getSuperclass();
        while (parentClass != null && parentClass != Objects.class){
            for (Field field : parentClass.getDeclaredFields()){
                if(fields.length() > 1){
                    fields.append(",");
                    value.append(",");
                }
                if(field.isAnnotationPresent(Column.class)){
                    Column column = field.getAnnotation(Column.class);
                    fields.append(column.name());
                    value.append("?");
                }
            }
            parentClass = parentClass.getSuperclass();
        }
        StringBuilder sql = new StringBuilder(
                "insert into " + tableName + "(" + fields.toString() + ") value (" + value.toString() + ")"
        );
        return sql;
    }

    @Override
    public List<T> findByCondition(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = ConnectionUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
            return resultsetMapper.mapRow(rs,tClass);

        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public T findById(Long id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<T> results = new ArrayList<>();
        try{
            conn = ConnectionUtils.getConnection();
            stmt = conn.createStatement();

            String tableName = null;
            if(tClass.isAnnotationPresent(Table.class)){
                Table table = tClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            String sql = "select * from " + tableName + "";
            String sqlDebug = sql.toString();
            rs = stmt.executeQuery(sqlDebug);
            ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
            return resultsetMapper.mapRow(rs,tClass).size() > 0 ? results.get(0) : null;

        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }




}

