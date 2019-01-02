 package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import bean.FoodBean;


public class FoodDao {
	public List<FoodBean> selectFood() {
		List<FoodBean> foodList = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select food_id,food_price,food_name,food_image,foodtype_id,attendant_id from food";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				FoodBean food = new FoodBean();
				food.setFoodId(rs.getInt("food_id"));
				food.setFoodPrice(rs.getInt("food_price"));
				food.setFoodName(rs.getString("food_name"));
				food.setFoodImageUrl(rs.getString("food_image"));
				food.setFoodTypeId(rs.getInt("foodtype_id"));
				food.setFoodAttendantId(rs.getInt("attendant_id"));
				foodList.add(food);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodList;
	}
	
	
	/*
	 *按类型查找菜品
	 */
	public List<FoodBean> selectFoodByFoodType(int foodTypeId) {
		List<FoodBean> foodList = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select food_id,food_price,food_name,food_image,attendant_id from food"
				+ " where foodtype_id=?";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, foodTypeId);
			rs = prepar.executeQuery();
			while(rs.next()) {
				FoodBean food = new FoodBean();
				food.setFoodId(rs.getInt("food_id"));
				food.setFoodPrice(rs.getInt("food_price"));
				food.setFoodName(rs.getString("food_name"));
				food.setFoodImageUrl(rs.getString("food_image"));
//				food.setFoodTypeId(foodTypeId);
				food.setFoodAttendantId(rs.getInt("attendant_id"));
				foodList.add(food);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodList;
	}
}
