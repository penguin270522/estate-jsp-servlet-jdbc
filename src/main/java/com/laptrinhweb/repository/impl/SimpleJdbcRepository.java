package com.laptrinhweb.repository.impl;

import com.laptrinhweb.annotation.Table;
import com.laptrinhweb.constant.SystemConstant;
import com.laptrinhweb.repository.JdbcRepository;
import com.laptrinhweb.repository.entity.BuildingEntity;
import com.laptrinhweb.utils.ConnectionUtils;

import java.lang.reflect.Field;
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
            if(tClass.isAnnotationPresent(Table.class)){
                Table table = tClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            String sql = "select * from " + tableName + "";
            String sqlDebug = sql.toString();
            rs = stmt.executeQuery(sqlDebug);
            Field[] fields = tClass.getDeclaredFields();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            while (rs.next()){
                T object = tClass.newInstance();
                for(int i = 0; i < resultSetMetaData.getColumnCount(); i++){
                    String colummName = resultSetMetaData.getColumnName(i + 1);
                    Objects columnValue = (Objects) rs.getObject(i + 1);
                    //loop in fields
                    for(Field field : fields){

                    }
                }
                //get column Name
                //get column value
                //loop in fields

            }
        }catch(SQLException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        } finally {
            try {
                if(conn != null){
                    conn.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void insert(Objects objects) {

    }


}

