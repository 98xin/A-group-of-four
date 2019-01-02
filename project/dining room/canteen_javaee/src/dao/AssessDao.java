package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AssessBean;

public class AssessDao {
	/*
	 * 查询所有评论
	 */
	public List<AssessBean> selectAssess() {
		List<AssessBean> assessList = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select assess_id,assess_content,student_id,food_id from assess";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				AssessBean assess = new AssessBean();
				assess.setAssessId(rs.getInt("assess_id"));
				assess.setAssessContent(rs.getString("assess_content"));
				assess.setStudentId(rs.getInt("student_id"));
				assess.setFoodId(rs.getInt("food_id"));
				assessList.add(assess);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assessList;
	}
	
	/*
	 * 添加评论
	 */
	public void insertAssess(AssessBean assess) {
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt=null;
	
		String sql="insert into assess(assess_id,assess_content,student_id,food_id) values(?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, assess.getAssessId());
			pstmt.setString(2, assess.getAssessContent());
			pstmt.setInt(3, assess.getStudentId());
			pstmt.setInt(4, assess.getFoodId());
			pstmt.execute();
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 删除评论
	 */
	public void deleteAssess(int assessId) {
		Connection conn=DataBase.getConnection();
		PreparedStatement pstmt=null;
		String sql="delete from assess where assess_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, assessId);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
