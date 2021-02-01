package com.kh.group.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.vo.Group;

public class GroupDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// 프로시저 PL_UPDATE_MEMBER_CNT 실행하는 함수
	public int procedureMemberCnt(Connection conn, int groupIdx) {
		int res = 0;
		CallableStatement cstm = null;
		String query = "{call PL_UPDATE_MEMBER_CNT(?)}";
		try {
			cstm = conn.prepareCall(query);
			cstm.setInt(1, groupIdx);
			cstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.PR01, e);
		} finally {
			jdt.close(cstm); 
		}
		return res;
	}
	
	
	// 그룹을 만드는 함수 
	public int insertGroup(Connection conn, Group group) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "INSERT INTO SH_GROUP "
				+ "(GROUP_ID, MB_ID, GROUP_PPL_NUMBER, GROUP_PAYDATE, GROUP_ACCOUNT_INFO, GROUP_SHARE_ID, GROUP_SHARE_PASSWORD, SER_CODE)"
				+ "VALUES (SC_GROUP_ID.NEXTVAL, ?, 1, ?, ?, ?, ?, ?)";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "lee5031207");
			pstm.setInt(2, group.getGroupPayDate());
			pstm.setString(3, group.getAccountInfo());
			pstm.setString(4, group.getShareId());
			pstm.setString(5, group.getSharePw());
			pstm.setString(6, group.getServiceCode());
			//res = pstm.executeUpdate();
			procedureMemberCnt(conn, 1020);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.GR01, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	// GROUP_ID를 가져오는 함수
	public ArrayList<Integer> getGroupId(Connection conn, String mbId) {
		ResultSet rset = null;
		ArrayList<Integer> groupIds = new ArrayList();
		PreparedStatement pstm = null;
		String query = "SELECT GROUP_ID FROM SH_GROUP WHERE MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbId);
			rset = pstm.executeQuery();
			while(rset.next()) {
				groupIds.add(rset.getInt("GROUP_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupIds;
	}
}
