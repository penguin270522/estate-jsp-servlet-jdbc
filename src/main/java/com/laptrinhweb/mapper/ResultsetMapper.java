package com.laptrinhweb.mapper;

import com.laptrinhweb.annotation.Column;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultsetMapper <T>{
    public List<T> mapRow(ResultSet rs , Class<T> tClass){
        List<T> results = new ArrayList<>();
        try{
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
            }
            return results;
        }catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return null;
    }
}
