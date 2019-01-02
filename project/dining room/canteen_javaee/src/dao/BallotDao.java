package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BallotBean;

public class BallotDao {
	/*
	 * 查询所有评论
	 */
	public List<BallotBean> selectBallot() {
		List<BallotBean> ballotList = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "select ballot_id,ballot_num,food_name,food_id,ballot_time from ballot";
		PreparedStatement prepar = null;
		ResultSet rs = null;
		try {
			prepar = conn.prepareStatement(sql);
			rs = prepar.executeQuery();
			while(rs.next()) {
				BallotBean ballot = new BallotBean();
				ballot.setBallotId(rs.getInt("ballot_id"));
				ballot.setBallotNum(rs.getInt("ballot_num"));
				ballot.setBallotTime(rs.getString("ballot_time"));
				ballot.setFoodId(rs.getInt("food_id"));
				ballot.setFoodName(rs.getString("food_name"));
				ballotList.add(ballot);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ballotList;
	}
	
	/*
	 * 添加投票
	 */
	public void insertBallot(BallotBean ballot) {
		Connection conn = DataBase.getConnection();
		PreparedStatement pstmt=null;
		//ResultSet rs=null;
		String sql="insert into ballot(ballot_id,ballot_num,food_name,food_id,ballot_time) values(?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ballot.getBallotId());
			pstmt.setInt(2, ballot.getBallotNum());
			pstmt.setString(3, ballot.getFoodName());
			pstmt.setInt(4, ballot.getFoodId());
			pstmt.setString(5, ballot.getBallotTime());
			pstmt.execute();
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 删除投票
	 */
	public void deleteBallot(int ballotId) {
		Connection conn=DataBase.getConnection();
		PreparedStatement pstmt=null;
		String sql="delete from ballot where ballot_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ballotId);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 更新投票
	 */
	public int updateBallot(int foodId) {
		Connection conn=DataBase.getConnection();
		PreparedStatement pstmt=null;
		String sql = "update ballot set ballot_num=ballot_num+1 where food_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, foodId);
			pstmt.executeUpdate();
			conn.close();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
		
	}
}
