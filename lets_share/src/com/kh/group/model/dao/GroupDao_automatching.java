package com.kh.group.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.vo.Group;

public class GroupDao_automatching {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//사용자의 조건과 맞는 그룹의 그룹id를 반환해주는 메서드
	public int findGroup(Connection conn, int ser_code, Date groupLastDay) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "select * from sh_group where ser_code = ? and group_last_day = ?"
				+ "and rnum = 1";//나중에 orderby 넣어서 인원수가 빈지 오래된 그룹부터 
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, ser_code);
			pstm.setDate(2, groupLastDay);
			
			res = pstm.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.GR01, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	
	
	
	
}
