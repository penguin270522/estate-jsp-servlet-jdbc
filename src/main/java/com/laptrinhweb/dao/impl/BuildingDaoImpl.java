package com.laptrinhweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhweb.constant.SystemConstant;
import com.laptrinhweb.dao.BuildingDao;
import com.laptrinhweb.dao.entity.BuildingEntity;
import com.laptrinhweb.utils.ConnectionUtils;

public class BuildingDaoImpl implements BuildingDao{

	@Override
	public List<BuildingEntity> findBuiling(String name, String street, String ward, String district, Integer floorArea, String type) {
		List<BuildingEntity> results = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder("select * from building " + SystemConstant.WHERE_ONE_EQUAL_ONE + "");
			if(name != null && name != "") {
				sql.append(" and name like '%" + name +"%' ");
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
			rs = stmt.executeQuery(sql.toString());
				while (rs.next()) {
					BuildingEntity buildingCkiuVk = new BuildingEntity();
					buildingCkiuVk.setId(rs.getLong("id"));
					buildingCkiuVk.setName(rs.getString("name"));
					buildingCkiuVk.setStreet(rs.getString("street"));
					buildingCkiuVk.setWard(rs.getString("ward"));
					buildingCkiuVk.setDistrict(rs.getString("district"));
					buildingCkiuVk.setFloorArea(rs.getInt("floorArea"));
					buildingCkiuVk.setType(rs.getString("type"));
					results.add(buildingCkiuVk);
				} 
				return results;
	 		} catch (SQLException | ArithmeticException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if ( conn != null) {
					conn.close();
				}
				if( stmt != null) {
					stmt.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
}
	
	


