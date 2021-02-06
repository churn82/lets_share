package com.kh.group.model.dao;

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
	public int findGroup(Connection conn, String userSerCode, int userPeriod) {
		int res = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select group_id from sh_group where ser_code = ? and sysdate + ? < group_last_day"
				+ "and rownum = 1";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userSerCode);
			pstm.setInt(2, userPeriod);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				res = rset.getInt(1);
				System.out.println(res);
			}else {
				System.out.println("실패");
			}
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.GR01, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	
	
	
	
}
