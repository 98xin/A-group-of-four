package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import bean.FoodTypeBean;

public class FoodTypeDao {
	
	/**
	 * 查询
	 * @return
	 */
	public List<FoodTypeBean> select(){
		List<FoodTypeBean> list = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select foodtype_id,foodtype_name from foodtype";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				FoodTypeBean foodType = new FoodTypeBean();
				foodType.setFoodTypeId(rs.getInt("foodtype_id"));
				foodType.setFoodTypeName(rs.getString("foodtype_name"));
				list.add(foodType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加
	 */
	public void insert(FoodTypeBean foodType) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into foodtype() values(?)";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setString(1, foodType.getFoodTypeName());
			prepar.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 */
	public void delete(FoodTypeBean foodType) {
		Connection conn = DataBase.getConnection();
		String sql = "delete from foodtype where foodtype_id=?";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, foodType.getFoodTypeId());
			prepar.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
