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
import java.util.Objects;

public class ResultsetMapper <T>{
    public List<T> mapRow(ResultSet rs , Class<T> tClass){
        List<T> results = new ArrayList<>();
        try{
            // lấy field của lớp hiện tại không lấy field của lớp super class.
            Field[] fields = tClass.getDeclaredFields();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            while (rs.next()){
                // thuật toán dưới đây set value trong subclass.
                // nên super class lúc này vẫn chưa được set ( cụ thể ở đây là id nên bây giờ id là null).
                T object = tClass.newInstance();
                for(int i = 0; i < resultSetMetaData.getColumnCount(); i++){
                    String colummName = resultSetMetaData.getColumnName(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    for(Field field : fields){
                        if(field.isAnnotationPresent(Column.class)){
                            Column column = field.getAnnotation(Column.class);
                            if(column.name().equals(colummName)&& columnValue != null){
                                BeanUtils.setProperty(object, field.getName(), columnValue);
                                break;
                            }
                        }
                    }

                    // set value trong super class  ( cụ thể ở trong trường hợp này là field id của class BaseEntity).
                    Class<?> parentClass = tClass.getSuperclass();
                    while (parentClass!= null || parentClass instanceof Object){
                        for(Field field : parentClass.getDeclaredFields()){
                            if(field.isAnnotationPresent(Column.class)){
                                Column column = field.getAnnotation(Column.class);
                                if(column.name().equals(colummName)&& columnValue != null){
                                    BeanUtils.setProperty(object, field.getName(), columnValue);
                                    break;
                                }
                            }
                        }
                        parentClass = parentClass.getSuperclass();
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
