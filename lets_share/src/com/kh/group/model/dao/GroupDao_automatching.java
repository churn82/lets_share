package com.kh.group.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;

public class GroupDao_automatching {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//사용자의 조건과 맞는 그룹의 그룹id를 반환해주는 메서드
	public int findGroup(Connection conn, String userSerCode, int userPeriod, String userId) {
		int res = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		//사용자가 원하는 서비스 코드와 같고, 원하는 기간의 마지막 날이 그룹이 해산되기 이전이면서
		//위 조건을 만족하는 그룹 중 가장 먼저 매칭에 등록된 그룹을 검색한다
		
		//!!수정완료)사용자가 이미 그룹원인 그룹을 빼야함.
		String query = "select GROUP_ID from "
				+ "(select GROUP_ID from SH_GROUP "
				+ "where SER_CODE = ? and (GROUP_LAST_DAY is null or GROUP_LAST_DAY > sysdate + ?) "
				+ "and GROUP_AUTO_DATE is not null "
				+ "and mb_id != ? "
				+ "and group_id not in(select group_id from sh_matching where mb_id = ?) "
				+ "order by GROUP_AUTO_DATE asc) "
				+ "where rownum = 1";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userSerCode);
			pstm.setInt(2, userPeriod);
			pstm.setString(3, userId);
			pstm.setString(4, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				res = rset.getInt(1);
			}
		} catch(SQLException e){
			throw new DataAccessException(ErrorCode.MR01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return res;
	}
	
	//매칭에 성공한 그룹 아이디와 사용자의 아이디, 사용 기간을 받아서, 그룹에 바로 등록해주는 메서드
	public int addGroup(Connection conn, String userId, int groupId, int userPeriod, String userName) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "insert into SH_MATCHING (MB_ID, GROUP_ID, PAY_DATE, MB_NAME) "
				+ "values( ? , ? , ? , ? )";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setInt(2, groupId);
			pstm.setInt(3, userPeriod);
			pstm.setString(4, userName);
			res = pstm.executeUpdate();
		}catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR02, e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	//그룹의 인원수를 갱신하고
	//그룹의 GROUP_PPL_NUMBER가 GROUP_PPL_WISH_NUMBER에 도달하였는지를 반환하는 메서드
	public boolean isMax(Connection conn, int groupId) {
		boolean occupied = false;
		PreparedStatement pstm = null;
		CallableStatement cstm = null;
		ResultSet rset = null;
		String procedure = "{call PL_UPDATE_MEMBER_CNT(?)}";
		String query = "select * from SH_GROUP where GROUP_ID = ?";
		try {
			cstm = conn.prepareCall(procedure);
			cstm.setInt(1, groupId);
			cstm.execute();
			
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				int pNum = rset.getInt("GROUP_PPL_NUMBER");
				int wNum = rset.getInt("GROUP_PPL_WISH_NUMBER");
				if(pNum >= wNum) {
					occupied = true;
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		}finally {
			jdt.close(rset);
			jdt.close(pstm);
			jdt.close(cstm);
		}
		return occupied;
	}
	//그룹 인원이 가득 차거나, 그룹장이 매칭등록을 해제한 경우 
	//그룹을 매칭불가 상태로 변경하는 메서드
	public int exitQueue(Connection conn, int groupId) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "update SH_MEMBER set GROUP_AUTO_DATE = null "
				+ "where GROUP_ID = ? ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR03,e);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	
}
