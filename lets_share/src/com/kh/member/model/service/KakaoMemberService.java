package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.member.model.dao.KakaoMemberDao;
import com.kh.member.model.vo.Member;

public class KakaoMemberService {

	KakaoMemberDao kakaoMemberDao = new KakaoMemberDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// DB에 카카오로 가입한 사람인지 확인하는 메서드
	public boolean confirmKakaoUser(String kakaoId) {
		boolean flag = true;
		Connection conn = jdt.getConnection();	
		try {
			flag = kakaoMemberDao.confirmKakaoUser(conn, kakaoId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return flag;
	}
	
//	// DB에 카카오로 가입한 멤버 멤버에 담아서 가져옴
//	public Member getKakaoMember(String kakaoId) {
//		Member member = null;
//		Connection conn = jdt.getConnection();	
//		try {
//			member = kakaoMemberDao.getKakaoMember(conn, kakaoId);
//			jdt.commit(conn);
//		} catch (DataAccessException e) {
//			jdt.rollback(conn);
//			throw new ToAlertException(e.error);
//		}finally {
//			jdt.close(conn);
//		}
//		return member;
//	}
	
}
