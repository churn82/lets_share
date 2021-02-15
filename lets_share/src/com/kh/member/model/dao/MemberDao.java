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
import oracle.security.crypto.core.RSA;

public class MemberDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public MemberDao() {}
	
	//로그인시 아이디 패스워드 확인
	public Member memberAuthenticate(Connection conn, String mbId,String mbpassword) {
		Member member = new Member();
		//쿼리 실행용 객체
		PreparedStatement pstm = null;
		//Select쿼리의 결과로 반환된 데이터를 저장하는 객체
		ResultSet rset = null;
		
		try {
			//ID,패스워드 확인 , 탈퇴여부 확인
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
	
	//아이디 체크
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
	//멤버 정보를 Arraylist에 담아 가져오는 메서드
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
	
	//SH_MEMBER 테이블 Update 메서드
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
	//SH_MEMBER 테이블 삭제 메서드
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
	//회원 가입일 between 메서드 
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
	//컨트롤러 테스트
	public static MemberDao getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
public  ArrayList<Member> MemberList(Connection conn){
		
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

	//멤버 개수 가져오는 함수
	public int getMemberCnt(Connection conn) {
		int allMemberCnt = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_MEMBER WHERE MB_LEVEL != 'MB10'"; 
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			if(rset.next()) {
				allMemberCnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allMemberCnt;
	}
	
	//검색한 멤버 개수 가져오는 함수
	public int getMemberCnt(Connection conn, String id) {
		int allMemberCnt = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_MEMBER WHERE MB_LEVEL != 'MB10' AND MB_ID LIKE ?"; 
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			rset = pstm.executeQuery();
			if(rset.next()) {
				allMemberCnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allMemberCnt;
	}
	
	//페이징해서 memberList를 가져온다 
	public ArrayList<Member> getMemberList(Connection conn, int start, int end) {
		ArrayList<Member> memberList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, M.*"
				+ "        FROM (SELECT * FROM SH_MEMBER ORDER BY MB_REGISTER_DATE DESC) M"
				+ ") WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, start);
			pstm.setInt(2, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Member member = new Member();
				member.setMbId(rset.getString(2));
				member.setMbpassword(rset.getString(3));
				member.setMbnick(rset.getString(4));
				member.setMbtel(rset.getString(5));
				member.setMbemail(rset.getString(6));
				member.setMbpoint(rset.getInt(7));
				member.setMblevel(rset.getString(8));
				member.setMbRegisterDate(rset.getDate(9));
				member.setMbLeaveDate(rset.getDate(10));
				member.setMbName(rset.getString(11));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdt.close(rset, pstm);
		}
		return memberList;
	}
	// [검색]한 애들을 페이징해서 memberList를 가져온다 
	public ArrayList<Member> getMemberList(Connection conn, String id, int start, int end) {
		ArrayList<Member> memberList = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, M.*"
				+ "        FROM (SELECT * FROM SH_MEMBER WHERE MB_ID LIKE ? ORDER BY MB_REGISTER_DATE DESC) M"
				+ ") WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			pstm.setInt(2, start);
			pstm.setInt(3, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Member member = new Member();
				member.setMbId(rset.getString(2));
				member.setMbpassword(rset.getString(3));
				member.setMbnick(rset.getString(4));
				member.setMbtel(rset.getString(5));
				member.setMbemail(rset.getString(6));
				member.setMbpoint(rset.getInt(7));
				member.setMblevel(rset.getString(8));
				member.setMbRegisterDate(rset.getDate(9));
				member.setMbLeaveDate(rset.getDate(10));
				member.setMbName(rset.getString(11));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdt.close(rset, pstm);
		}
		return memberList;
	}
	


}