package com.kh.common.scheduler.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.vo.GroupMatching;
import com.kh.member.model.vo.Member;

public class SchedulerDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public ArrayList<GroupMatching> getThreeDaysLeft(Connection conn){
		ArrayList<GroupMatching> threeDaysLeft = new ArrayList<GroupMatching>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_MATCHING WHERE TO_CHAR( EX_DATE, 'yyyymmdd' ) = TO_CHAR( SYSDATE+3, 'yyyymmdd')";
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			while(rset.next()) {
				GroupMatching groupMatching = new GroupMatching();
				groupMatching.setMemberId(rset.getString(1));
				groupMatching.setGroupId(rset.getInt(2));
				groupMatching.setPaymentYN(rset.getString(3).charAt(0));
				groupMatching.setPaymentConfirm(rset.getString(4).charAt(0));
				groupMatching.setRegDate(rset.getDate(5));
				groupMatching.setStDate(rset.getDate(6));
				groupMatching.setExDate(rset.getDate(7));
				groupMatching.setPayDate(rset.getInt(8));
				threeDaysLeft.add(groupMatching);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR05, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return threeDaysLeft;
	}
	
	public ArrayList<GroupMatching> getExpiration(Connection conn){
		ArrayList<GroupMatching> Expiration = new ArrayList<GroupMatching>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_MATCHING WHERE TO_CHAR( EX_DATE, 'yyyymmdd' ) = TO_CHAR( SYSDATE-1, 'yyyymmdd')";
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			while(rset.next()) {
				GroupMatching groupMatching = new GroupMatching();
				groupMatching.setMemberId(rset.getString(1));
				groupMatching.setGroupId(rset.getInt(2));
				groupMatching.setPaymentYN(rset.getString(3).charAt(0));
				groupMatching.setPaymentConfirm(rset.getString(4).charAt(0));
				groupMatching.setRegDate(rset.getDate(5));
				groupMatching.setStDate(rset.getDate(6));
				groupMatching.setExDate(rset.getDate(7));
				groupMatching.setPayDate(rset.getInt(8));
				Expiration.add(groupMatching);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR05, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return Expiration;
	}
	
	public ArrayList<Integer> getExpirationGroupIds(Connection conn){
		ArrayList<Integer> expirationGroupId = new ArrayList<Integer>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT DISTINCT GROUP_ID FROM SH_MATCHING WHERE TO_CHAR( EX_DATE, 'yyyymmdd' ) = TO_CHAR( SYSDATE-1, 'yyyymmdd')";
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			while(rset.next()) {
				expirationGroupId.add(rset.getInt(1));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR05, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return expirationGroupId;
	}
	
	public int updateStExDateNull(Connection conn, int groupId, String memberId) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_MATCHING SET ST_DATE = NULL, EX_DATE = NULL WHERE GROUP_ID = ? AND MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			pstm.setString(2, memberId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR06, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}

}
