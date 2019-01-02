package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import bean.StudentBean;

public class StudentDao {
	
	/**
	 * 查询
	 * @return
	 */
	public List<StudentBean> select(){
		List<StudentBean> list = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select student_id,student_name,student_password from student";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				StudentBean student = new StudentBean();
				student.setStudentId(rs.getInt("student_id"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentPassword(rs.getString("student_password"));
				list.add(student);
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
	 */
	public void insert(StudentBean student) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into student() values(?,?)";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setString(1, student.getStudentName());
			prepar.setString(2, student.getStudentPassword());
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
	public void delete(StudentBean student) {
		Connection conn = DataBase.getConnection();
		String sql = "delete from student where student_id=?";
		PreparedStatement prepar = null;
		try {
			prepar = conn.prepareStatement(sql);
			prepar.setInt(1, student.getStudentId());
			prepar.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
