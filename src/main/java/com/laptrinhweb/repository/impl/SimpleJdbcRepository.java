package com.laptrinhweb.repository.impl;

import com.laptrinhweb.annotation.Table;
import com.laptrinhweb.constant.SystemConstant;
import com.laptrinhweb.repository.JdbcRepository;
import com.laptrinhweb.repository.entity.BuildingEntity;
import com.laptrinhweb.utils.ConnectionUtils;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            while (rs.next()){
               /* BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setDistrict(rs.getString("district"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setFloorArea(rs.getInt("floorArea"));
                buildingEntity.setType(rs.getString("type"));
                results.add(buildingEntity);*/
            }

        }catch(SQLException e){
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

