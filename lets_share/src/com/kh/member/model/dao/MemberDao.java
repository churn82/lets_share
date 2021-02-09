package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.member.model.vo.Member;

import oracle.jdbc.proxy.annotation.Pre;

public class MemberDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public MemberDao() {}
	
	public Member memberAuthenticate(Connection conn, String mbId,String mbpassword) {
		Member member = new Member();
		//쿼리 실행용 객체
		PreparedStatement pstm = null;
		//Select쿼리의 결과로 반환된 데이터를 저장하는 객체
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM SH_MEMBER WHERE MB_ID = ? AND MB_PASSWORD = ? AND MB_LEAVE_DATE IS NULL";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbId);
			pstm.setString(2, mbpassword);
			rset = pstm.executeQuery();
			if(rset.next()) {
				member.setMbId(rset.getString(1));
				member.setMbpassword(rset.getString(2));
				member.setMbnick(rset.getString(3));
				member.setMbtel(rset.getString(4));
				member.setMbemail(rset.getString(5));
				member.setMbpoint(rset.getInt(6));
				member.setMblevel(rset.getString(7));
				member.setMbRegisterDate(rset.getDate(8));
				member.setMbLeaveDate(rset.getDate(9));
				member.setMbName(rset.getString(10));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01,e);	
		}finally {
			jdt.close(rset,pstm);
		}
		return member;
	}
	
	//카카오
	public Member memberAuthenticatekakao(Connection conn, String mbkakaoId) {
		Member member = new Member();
		//쿼리 실행용 객체
		PreparedStatement pstm = null;
		//Select쿼리의 결과로 반환된 데이터를 저장하는 객체
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM SH_MEMBER WHERE MB_KAKAO_ID = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbkakaoId);
			
			rset = pstm.executeQuery();
			if(rset.next()) {
//				member.setMbkakaoId(rset.getString(1));
//				member.setMbkakaonick(rset.getString(2));
//				member.setMbkakaoemail(rset.getString(3));
			
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01,e);	
		}finally {
			jdt.close(rset,pstm);
		}
		return member;
	}
	
	public Member selectMemberById(Connection conn, String mbId) {
		Member member = new Member();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM SH_MEMBER WHERE MB_ID = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				member.setMbId(rset.getString(1));
				member.setMbpassword(rset.getString(2));
				member.setMbnick(rset.getString(3));
				member.setMbtel(rset.getString(4));
				member.setMbemail(rset.getString(5));
				member.setMbpoint(rset.getInt(6));
				member.setMblevel(rset.getString(7));
				member.setMbRegisterDate(rset.getDate(8));
				member.setMbLeaveDate(rset.getDate(9));
				member.setMbName(rset.getString(10));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01,e);	
		} finally {
			jdt.close(rset,pstm);
		}
		return member;
	}
	
	public Member selectMemberBylevel(Connection conn, String mblevel) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
	try {
		String query = "select sh_grade.mb_gr_name from sh_member\r\n"
				+ "inner join sh_grade on sh_grade.mb_level = sh_member.mb_level";
		pstm = conn.prepareStatement(query);
		pstm.setString(1, mblevel);
		
		rset = pstm.executeQuery();
		
		if(rset.next()) {
			member = new Member();
			member.setMbId(rset.getString(1));
			member.setMbpassword(rset.getString(2));
			member.setMbnick(rset.getString(3));
			member.setMbtel(rset.getString(4));
			member.setMbemail(rset.getString(5));
			member.setMbpoint(rset.getInt(6));
			member.setMblevel(rset.getString(7));
			member.setMbRegisterDate(rset.getDate(8));
			member.setMbLeaveDate(rset.getDate(9));
			member.setMbName(rset.getString(10));
		}
	} catch (SQLException e) {
		throw new DataAccessException(ErrorCode.SM01,e);	
	} finally {
		jdt.close(rset,pstm);
	}
	
	return member;
}
	
	//닉네임 체크
	public Member selectMemberBynick(Connection conn, String mbnick) {
		Member member = new Member();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM SH_MEMBER WHERE MB_NICK = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbnick);
			rset = pstm.executeQuery();
			if(rset.next()) {
				member.setMbId(rset.getString(1));
				member.setMbpassword(rset.getString(2));
				member.setMbnick(rset.getString(3));
				member.setMbtel(rset.getString(4));
				member.setMbemail(rset.getString(5));
				member.setMbpoint(rset.getInt(6));
				member.setMblevel(rset.getString(7));
				member.setMbRegisterDate(rset.getDate(8));
				member.setMbLeaveDate(rset.getDate(9));
				member.setMbName(rset.getString(10));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01,e);	
		} finally {
			jdt.close(rset,pstm);
		}
		return member;
	}
	
	public ArrayList<Member> selectMemberList(Connection conn){
		
		ArrayList<Member> memberList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from sh_member";
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setMbId(rset.getString(1));
				member.setMbpassword(rset.getString(2));
				member.setMbnick(rset.getString(3));
				member.setMbtel(rset.getString(4));
				member.setMbemail(rset.getString(5));
				member.setMbpoint(rset.getInt(6));
				member.setMblevel(rset.getString(7));
				member.setMbRegisterDate(rset.getDate(8));
				member.setMbLeaveDate(rset.getDate(9));
				member.setMbName(rset.getString(10));
			
				memberList.add(member);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01,e);	
		}finally {
			jdt.close(rset,pstm);
		}
		
		return memberList;
	}

	//SH_MEMBER 테이블에 INSERT해주는 메서드
	public int insertMember(Connection conn, Member member){
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "INSERT INTO SH_MEMBER(MB_ID, MB_PASSWORD, MB_NICK, MB_EMAIL, MB_TEL, MB_NAME) "
					+"VALUES(?,?,?,?,?,?)";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getMbId());
			pstm.setString(2,member.getMbpassword());
			pstm.setString(3, member.getMbnick());
			pstm.setString(4, member.getMbemail());
			pstm.setString(5, member.getMbtel());
			pstm.setString(6, member.getMbName());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IM01,e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	
//카카오
/*
public int insertMemberkakao(Connection conn, Member member){
	
	int res = 0;
	PreparedStatement pstm = null;
	
	try {
		String query = "INSERT INTO SH_MEMBER(MB_KAKAO_ID,MB_KAKAO_NICK , MB_KAKAO_EMAIL) "
				+"values(?,?,?)";
		pstm = conn.prepareStatement(query);
		pstm.setString(1, member.getMbkakaoId());
		pstm.setString(2,member.getMbkakaonick());
		pstm.setString(3, member.getMbkakaoemail());
		
		
		res = pstm.executeUpdate();
	} catch (SQLException e) {
		throw new DataAccessException(ErrorCode.IM01,e);
	}finally {
		jdt.close(pstm);
	}
	
	return res;
}
*/

/*
 * 이메일 join MB_ID null
public int insertMember(Connection conn, Member member){
	
	int res = 0;
	PreparedStatement pstm = null;
	
	try {
		String query = "INSERT INTO SH_MEMBER(MB_ID, MB_PASSWORD,MB_NICK, MB_TEL ,MB_EMAIL,MB_POINT,MB_LEVEL,MB_REGISTER_DATE,MB_LEAVE_DATE) "
				+"values(?,?,?,?,?,default,default,sysdate,sysdate)";
		pstm = conn.prepareStatement(query);
		pstm.setString(1, "rladydwns10");
		pstm.setString(2,"rladydwns!@#1");
		pstm.setString(3, "rladydwns10");
		pstm.setString(4, "01012344123");
		pstm.setString(5, "dnfheh@naver.com");
		
		
		res = pstm.executeUpdate();
	} catch (SQLException e) {
		throw new DataAccessException(ErrorCode.IM01,e);
	}finally {
		jdt.close(pstm);
	}
	
	return res;
}
*/
	public int updateMember(Connection conn, Member member){
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "update sh_member set mb_password = ? where mb_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getMbpassword());
			pstm.setString(2, member.getMbId());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException( ErrorCode.UM01,e);
		}finally {
			jdt.close(pstm);
		}
		
		return res;
	}

	public int deleteMember(Connection conn, String mbId){
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			String query = "delete from sh_member where mb_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, mbId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DM01,e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}

	public List<Member> selectMemberByRegdate(Connection conn, Date begin, Date end){
	
		List<Member> memberList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from sh_member where mb_register_date between ? and ?";
			pstm = conn.prepareStatement(query);
			pstm.setDate(1, begin);
			pstm.setDate(2, end);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setMbId(rset.getString(1));
				member.setMbpassword(rset.getString(2));
				member.setMbnick(rset.getString(3));
				member.setMbtel(rset.getString(4));
				member.setMbemail(rset.getString(5));
				member.setMbpoint(rset.getInt(6));
				member.setMblevel(rset.getString(7));
				member.setMbRegisterDate(rset.getDate(8));
				member.setMbLeaveDate(rset.getDate(9));
				member.setMbName(rset.getString(10));
				memberList.add(member);
			}
		} catch (SQLException e){
			throw new DataAccessException(ErrorCode.SM01,e);
		} finally {
			jdt.close(rset, pstm);
		}
		
		return memberList;
	}


	//회원등급에 맞는 코드명 가져오기
	public String getGradeName(Connection conn, String memberRank) {
		String gradeName = "";
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT MB_GR_NAME FROM SH_GRADE WHERE MB_LEVEL = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberRank);
			rset = pstm.executeQuery();
			if(rset.next()) {
				gradeName = rset.getString(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return gradeName;
	}

	//비밀번호, 닉네임 수정
	public int updatePwNick(Connection conn, String memberId, String updatePw, String updateNickName) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_MEMBER SET MB_PASSWORD = ?, MB_NICK = ? WHERE MB_ID = ? ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, updatePw);
			pstm.setString(2, updateNickName);
			pstm.setString(3, memberId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	//회원 탈퇴
	public int updateLeaveDate(Connection conn, String memberId) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_MEMBER SET MB_LEAVE_DATE = SYSDATE WHERE MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}


}