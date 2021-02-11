package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class KakaoMemberDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public KakaoMemberDao() {
		
	}
	
	// DB에 카카오로 가입한 사람인지 확인하는 메서드
	public boolean confirmKakaoUser(Connection conn, String kakaoId) {
		boolean flag= true;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT MB_ID FROM SH_MEMBER WHERE MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, kakaoId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				flag = false;
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return flag;
	}
	
//	public Member getKakaoMember(Connection conn, String kakaoId) {
//		Member member = new Member();
//		PreparedStatement pstm = null;
//		ResultSet rset = null;
//	}
	
}
