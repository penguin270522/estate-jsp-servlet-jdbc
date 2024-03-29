package com.laptrinhweb.repository.impl;

import com.laptrinhweb.annotation.Table;
import com.laptrinhweb.constant.SystemConstant;

import com.laptrinhweb.utils.ConnectionUtils;
import com.laptrinhweb.repository.BuildingRepository;
import com.laptrinhweb.repository.entity.BuildingEntity;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingRepositoryImpl extends SimpleJdbcRepository<BuildingEntity> implements BuildingRepository {

    private Class<BuildingEntity> buildingEntityClass;
    public BuildingRepositoryImpl (){
        buildingEntityClass = (Class<BuildingEntity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Override
    public List<BuildingEntity> findBuiling(String name, String street, String ward, String district, Integer floorArea, String type) {
        StringBuilder sql = new StringBuilder("select * from building where "  + " " + SystemConstant.WHERE_ONE_EQUAL_ONE + "");
        if(name != null && name != "") {
            sql.append(" and name like '%" + name + "%' ");
        }
        if(ward != null && ward != "") {
            sql.append(" and ward like '%" + ward +"%' ");
        }
        if(street != null && street != "") {
            sql.append(" and street like '%" + street +"%' ");
        }
        if(district != null && district != "") {
            sql.append(" and district like '%" + district +"%' ");
        }
        if(floorArea != null) {
            sql.append(" and floorArea like '%" + floorArea +"%' ");
        }
        if(type != null && type != ""){
            sql.append(" and type like '%" + type + "%' ");
        }
        String sqlDebug = sql.toString();
        return findByCondition(sqlDebug);
    }

    @Override
    public void insertBuilding(BuildingEntity buildingEntity, Integer[] rentAreas) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionUtils.getConnection();
//            conn.setAutoCommit(false);
            String sql1 = "INSERT INTO building (name, street) VALUES (?,?)";
            stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, buildingEntity.getName());
            stmt.setString(2, buildingEntity.getStreet());
            //resultset -> return id
            int flag = stmt.executeUpdate();
            //flag luon luon = 1 vi chung ta chi co them toa nha thoi
            rs = stmt.getGeneratedKeys();
            Long id = null;
            if(flag > 0){
                while(rs.next()){
                    id = rs.getLong(1);

                }
            }
            //insert rentarea

            if(rentAreas != null && rentAreas.length > 0){
                for(Integer item : rentAreas){
                    String sql2 = "INSERT INTO rentarea (values, buildingid) VALUES (?,?)";
                    stmt = conn.prepareStatement(sql2);
                    stmt.setInt(1, item);
                    stmt.setLong(2, id);
                    stmt.executeUpdate();
                }
            }
//            conn.commit();
        }catch (SQLException e){
//            try {
//                conn.rollback();
//            }catch (SQLException e1){
                e.printStackTrace();
//            }

        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public BuildingEntity findById(Long id) {
        BuildingEntity results = new BuildingEntity();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionUtils.getConnection();
            String sql = "select id from building where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if(rs.next()){
                return null;
            }

        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




        return null;
    }

}
