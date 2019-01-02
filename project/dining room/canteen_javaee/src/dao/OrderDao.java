package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import bean.OrderBean;


public class OrderDao {
	/**
	 * 查询
	 * @return
	 */
	public List<OrderBean> select() {
		List<OrderBean> list = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select order_id,order_tp,order_statement,order_time,student_id from `order`";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				OrderBean order = new OrderBean();
				order.setOrderId(rs.getInt("order_id"));
				order.setOrderTp(rs.getInt("order_tp"));
				order.setOrderStatement(rs.getString("order_statement"));
				order.setOrderTime(rs.getString("order_time"));
				order.setStudentId(rs.getInt("student_id"));
				list.add(order);
			}
			conn.close();
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
	public void insert(OrderBean order) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into `order`() values(?,?,?,?)";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, order.getOrderTp());
			prepar.setString(2, order.getOrderStatement());
			prepar.setString(3, order.getOrderTime());
			prepar.setInt(4, order.getStudentId());
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
	public void delete(OrderBean order) {
		Connection conn = DataBase.getConnection();
		String sql = "delete from `order` where order_id=?";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, order.getOrderId());
			prepar.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
