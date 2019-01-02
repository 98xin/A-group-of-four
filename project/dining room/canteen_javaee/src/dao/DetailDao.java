package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import bean.DetailBean;


public class DetailDao {

	/**
	 * 查询
	 * @return
	 */
	public List<DetailBean> select() {
		List<DetailBean> list = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select detail_id,student_id,food_id,food_name,order_id from detail";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				DetailBean detail = new DetailBean();
				detail.setDetailId(rs.getInt("detail_id"));
				detail.setStudentId(rs.getInt("student_id"));
				detail.setFoodId(rs.getInt("food_id"));
				detail.setFoodName(rs.getString("food_name"));
				detail.setOrderId(rs.getInt("order_id"));
				list.add(detail);
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
	public void insert(DetailBean detail) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into detail() values(?,?,?,?)";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, detail.getStudentId());
			prepar.setInt(2, detail.getFoodId());
			prepar.setString(3, detail.getFoodName());
			prepar.setInt(4, detail.getOrderId());
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
	public void delete(DetailBean detail) {
		Connection conn = DataBase.getConnection();
		String sql = "delete from detail where detail_id=?";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, detail.getDetailId());
			prepar.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
