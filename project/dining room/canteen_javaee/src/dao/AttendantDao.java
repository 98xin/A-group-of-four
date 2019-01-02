package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AttendantBean;

public class AttendantDao {
	/**
	 * 查询cake表中所有数据
	 */
	public List<AttendantBean> getAllAttendant(){
		List<AttendantBean> attendantList = new ArrayList<AttendantBean>();
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select attendant_id,attendant_name,attendant_password from attendant";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AttendantBean attendant = new AttendantBean();
				attendant.setAttendantId(rs.getInt("attendant_id"));
				attendant.setAttendantName(rs.getString("attendant_name"));
				attendant.setAttendantPassword(rs.getString("attendant_password"));
				attendantList.add(attendant);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return attendantList;
	}
	
	/*
	 * 插入数据
	 */
	public void insertAttendant(AttendantBean attendant) {
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt=null;
		String sql="insert into attendant(attendant_id,attendant_name,attendant_password) values(?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, attendant.getAttendantId());
			pstmt.setString(2, attendant.getAttendantName());
			pstmt.setString(3, attendant.getAttendantPassword());
			pstmt.execute();
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 删除数据
	 */
	public void deleteAttendant(int attendantId) {
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt = null;
		String sql="delete from canteen where attendant_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, attendantId);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
