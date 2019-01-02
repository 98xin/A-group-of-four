package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import bean.CartBean;


public class CartDao {
	/**
	 * 查询
	 * @return
	 */
	public List<CartBean> select() {
		List<CartBean> list = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select student_id,food_id,counts from cart";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				CartBean cart = new CartBean();
				cart.setStudentId(rs.getInt("student_id"));
				cart.setFoodId(rs.getInt("food_id"));
				cart.setCounts(rs.getInt("counts"));
				list.add(cart);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加
	 * @param food
	 */
	public void insert(CartBean cart) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into cart() values(?,?,?)";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, cart.getStudentId());
			prepar.setInt(2, cart.getFoodId());
			prepar.setInt(3, cart.getCounts());
			prepar.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 */
	public void delete(CartBean cart) {
		Connection conn = DataBase.getConnection();
		String sql = "delete from cart where student_id=? and food_id=?";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, cart.getStudentId());
			prepar.setInt(2, cart.getFoodId());
			prepar.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
