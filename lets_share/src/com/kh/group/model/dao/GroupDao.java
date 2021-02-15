package com.kh.group.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.group.model.vo.Group;
import com.kh.group.model.vo.GroupMatching;
import com.kh.group.model.vo.GroupStandBy;

import oracle.jdbc.proxy.annotation.Pre;

public class GroupDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// =========================프로시저 PL_UPDATE_MEMBER_CNT 실행하는 함수========================= 
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
	
	
	// =========================그룹을 생성하는 함수=========================  
	public int insertGroup(Connection conn, Group group, String memeberName) {
		int res = 0;
		int res2 = 0;
		int res3 = 0;
		PreparedStatement pstm = null;
		PreparedStatement pstm2 = null;
		CallableStatement cstm = null;
		
		String query = "INSERT INTO SH_GROUP "
				+ "(GROUP_ID, MB_ID, GROUP_PPL_NUMBER, GROUP_PAYDATE, GROUP_ACCOUNT_INFO, GROUP_SHARE_ID, GROUP_SHARE_PASSWORD, SER_CODE, GROUP_PPL_WISH_NUMBER)"
				+ "VALUES (SC_GROUP_ID.NEXTVAL, ?, 0, ?, ?, ?, ?, ?, ?)";
		
		String query2 = "INSERT INTO SH_MATCHING (MB_ID, GROUP_ID, MB_NAME) VALUES (?, SC_GROUP_ID.CURRVAL, ?)";
		
		String query3 = "{call PL_UPDATE_MEMBER_CNT(SC_GROUP_ID.CURRVAL)}";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, group.getMemberId());
			pstm.setInt(2, group.getGroupPayDate());
			pstm.setString(3, group.getAccountInfo());
			pstm.setString(4, group.getShareId());
			pstm.setString(5, group.getSharePw());
			pstm.setString(6, group.getServiceCode());
			pstm.setInt(7, group.getMemberCntWish());
			
			pstm2 = conn.prepareStatement(query2);
			pstm2.setString(1, group.getMemberId());
			pstm2.setString(2, memeberName);
			
			cstm = conn.prepareCall(query3);
			
			res = pstm.executeUpdate();
			res2 = pstm2.executeUpdate();
			res3 = cstm.executeUpdate();
			
			System.out.println("res1 : "+res+" / res2 :"+res2+" / res3 : "+res3);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.GR01, e);
		} finally {
			jdt.close(pstm);
			jdt.close(pstm2);
			jdt.close(cstm);
		}
		return res;
	}
	
	// =========================그룹 정보를 (페이징해서) Arraylist에 담아 가져오는 함수=========================
	public ArrayList<Group> getGroupList(Connection conn, int start, int end){
		ArrayList<Group> groupList = new ArrayList<Group>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, G.*"
				+ "        FROM (SELECT * FROM SH_GROUP ORDER BY GROUP_DATE ASC) G"
				+ "    ) WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, start);
			pstm.setInt(2, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Group group = new Group();
				group.setGroupId(rset.getInt(2));
				group.setMemberId(rset.getString(3));
				group.setMemberCnt(rset.getInt(4));
				group.setGroupPayDate(rset.getInt(5));
				group.setAccountInfo(rset.getString(6));
				group.setShareId(rset.getString(7));
				group.setSharePw(rset.getString(8));
				group.setRegdate(rset.getDate(9));
				group.setAutoDate(rset.getDate(10));
				group.setServiceCode(rset.getString(11));
				group.setMemberCntWish(rset.getInt(12));
				group.setLastDay(rset.getDate(13));
				groupList.add(group);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupList;		
	}
	public ArrayList<Group> getGroupList(Connection conn, String service, int start, int end){
		ArrayList<Group> groupList = new ArrayList<Group>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, G.*"
				+ "        FROM (SELECT * FROM SH_GROUP WHERE SER_CODE = ? ORDER BY GROUP_DATE ASC) G"
				+ "    ) WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, service);
			pstm.setInt(2, start);
			pstm.setInt(3, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Group group = new Group();
				group.setGroupId(rset.getInt(2));
				group.setMemberId(rset.getString(3));
				group.setMemberCnt(rset.getInt(4));
				group.setGroupPayDate(rset.getInt(5));
				group.setAccountInfo(rset.getString(6));
				group.setShareId(rset.getString(7));
				group.setSharePw(rset.getString(8));
				group.setRegdate(rset.getDate(9));
				group.setAutoDate(rset.getDate(10));
				group.setServiceCode(rset.getString(11));
				group.setMemberCntWish(rset.getInt(12));
				group.setLastDay(rset.getDate(13));
				groupList.add(group);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupList;		
	}

	
	// =========================그룹 개수를 int로 가져오는 함수=========================
	public int getGroupCnt(Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_GROUP";
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			if(rset.next()) {
				res = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return res;
	}
	public int getGroupCnt(Connection conn, String service) {
		int res = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_GROUP WHERE SER_CODE = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, service);
			rset = pstm.executeQuery();
			if(rset.next()) {
				res = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return res;
	}
	
	
	//=========================그룹 대기 테이블에 유저 입력 함수=========================
	public int insertStandBy(Connection conn, int groupId, String userId) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "INSERT INTO SH_STAND_BY (GROUP_ID, MB_ID) VALUES (?,?)";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			pstm.setString(2, userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.GR02, e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	//=========================매칭테이블의 group_id를 가져오는 함수=========================
	public int getGroupId(Connection conn, String userId) {
		int groupId = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT GROUP_ID FROM SH_MATCHING WHERE MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				groupId = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupId;
	}
	
	//=========================그룹 정보를 group에 담아 가져오는 함수=========================
	public Group getGroup(Connection conn, int groupId) {
		Group group = new Group();
		ResultSet rset = null;
		PreparedStatement pstm = null;
		String query = "SELECT * FROM SH_GROUP WHERE GROUP_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				group.setGroupId(rset.getInt(1));
				group.setMemberId(rset.getString(2));
				group.setMemberCnt(rset.getInt(3));
				group.setGroupPayDate(rset.getInt(4));
				group.setAccountInfo(rset.getString(5));
				group.setShareId(rset.getString(6));
				group.setSharePw(rset.getString(7));
				group.setRegdate(rset.getDate(8));
				group.setAutoDate(rset.getDate(9));
				group.setServiceCode(rset.getString(10));
				group.setMemberCntWish(rset.getInt(11));
				group.setLastDay(rset.getDate(12));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return group;
	}
	
	//=========================그룹 대기 정보를 Arraylist에 담아 가져오는 함수=========================
	public ArrayList<GroupStandBy> getStandByList(Connection conn, int groupId){
		ArrayList<GroupStandBy> standByList = new ArrayList<GroupStandBy>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_STAND_BY WHERE GROUP_ID = ? AND APPROVAL = 'wait'";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			rset = pstm.executeQuery();
			while(rset.next()) {
				GroupStandBy standBy = new GroupStandBy();
				standBy.setMemberId(rset.getString(1));
				standBy.setGroupId(rset.getInt(2));
				standBy.setApproval(rset.getString(3));
				standByList.add(standBy);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG02, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return standByList;
	}
		
	//=========================Approval 승인 프로시저 실행하는 함수=========================
	public int approval(Connection conn, int groupId, String memberId, String memberName) {
		int res = 0;
		CallableStatement cstm = null;
		String query3 = "{call PL_APPROVAL_GROUP(?,?,?)}";
		try {
			cstm = conn.prepareCall(query3);
			cstm.setInt(1, groupId);
			cstm.setString(2, memberId);
			cstm.setString(3, memberName);
			res = cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.PR01, e);
		} finally {
			jdt.close(cstm);
		}
		return res;
	}
	
	//=========================Refuse 가입 거절 프로시저 실행하는 함수=========================
	public int refuse(Connection conn, int groupId, String memberId) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_STAND_BY SET APPROVAL = 'refuse' WHERE GROUP_ID = ? AND MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			pstm.setString(2, memberId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.GR03, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	//=========================그룹ID 소속의 매칭데이터를 가져오는 함수=========================
	public ArrayList<GroupMatching> getMatching(Connection conn, int groupId){
		ArrayList<GroupMatching> matchingList = new ArrayList<GroupMatching>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_MATCHING WHERE GROUP_ID = ? ORDER BY REG_DATE";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
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
				groupMatching.setMemberName(rset.getString(9));
				matchingList.add(groupMatching);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR02, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return matchingList;
	}

	//=========================서비스 하루 당 가격을 가져오는 함수=========================
	public int getServicePerDay(Connection conn, String serviceCode) {
		int servicePerDay = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT SER_PER_DAY FROM SH_SER_CODE WHERE SER_CODE = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, serviceCode);
			rset = pstm.executeQuery();
			if(rset.next()) {
				servicePerDay = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SSC01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return servicePerDay;
	}

	//=========================매칭 테이블의 PAY_DATE정보를 수정하는 함수=========================
	public int updatePayDate(Connection conn, String memberId, int groupId, int payDate) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_MATCHING SET PAY_DATE = ? WHERE MB_ID = ? AND GROUP_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, payDate);
			pstm.setString(2, memberId);
			pstm.setInt(3, groupId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR03, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}

	//=========================매칭 테이블 전체를 가져오는 함수=========================
	public GroupMatching getMatching(Connection conn, int groupId, String memberId) {
		GroupMatching groupMatching = new GroupMatching();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_MATCHING WHERE GROUP_ID = ? AND MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			pstm.setString(2, memberId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				groupMatching.setMemberId(rset.getString(1));
				groupMatching.setGroupId(rset.getInt(2));
				groupMatching.setPaymentYN(rset.getString(3).charAt(0));
				groupMatching.setPaymentConfirm(rset.getString(4).charAt(0));
				groupMatching.setRegDate(rset.getDate(5));
				groupMatching.setStDate(rset.getDate(6));
				groupMatching.setExDate(rset.getDate(7));
				groupMatching.setPayDate(rset.getInt(8));
				groupMatching.setMemberName(rset.getString(9));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR02, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return groupMatching;
	}

	//=========================ST_DATE를 sysdate로 바꾸는 함수=========================
	public int updateStDate(Connection conn, int groupId, String memberId) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_MATCHING SET ST_DATE = sysdate WHERE GROUP_ID = ? AND MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			pstm.setString(2, memberId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR03, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}

	//=========================PL_SET_EXDATE_FROM_STDATE실행 함수=========================
	public int execProcedureSEFS(Connection conn, int groupId, String memberId) {
		int res = 0;
		CallableStatement cstm = null;
		String query = "{call PL_SET_EXDATE_FROM_STDATE(?,?)}";		
		try {
			cstm = conn.prepareCall(query);
			cstm.setInt(1, groupId);
			cstm.setString(2, memberId);
			res =cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.PR01, e);
		} finally {
			jdt.close(cstm); 
		}
		return res;
	}
	
	//=========================PL_SET_EXDATE_FROM_EXDATE실행 함수=========================
	public int execProcedureSEFE(Connection conn, int groupId, String memberId) {
		int res = 0;
		CallableStatement cstm = null;
		String query = "{call PL_SET_EXDATE_FROM_EXDATE(?,?)}";		
		try {
			cstm = conn.prepareCall(query);
			cstm.setInt(1, groupId);
			cstm.setString(2, memberId);
			res = cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.PR01, e);
		} finally {
			jdt.close(cstm); 
		}
		return res;
	}
	
	//=========================그룹 비밀번호를 변경하는 함수=========================
	public int updateServicePw(Connection conn, int groupId, String servicePw) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_GROUP SET GROUP_SHARE_PASSWORD = ? WHERE GROUP_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, servicePw);
			pstm.setInt(2, groupId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.GR04, e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	//=========================PL_OUT_GROUP실행 함수=========================
	public int execProcedureOG(Connection conn, int groupId, String memberId) {
		int res = 0;
		CallableStatement cstm = null;
		String query = "{call PL_OUT_GROUP(?,?)}";		
		try {
			cstm = conn.prepareCall(query);
			cstm.setInt(1, groupId);
			cstm.setString(2, memberId);
			res = cstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(ErrorCode.PR01, e);
		} finally {
			jdt.close(cstm); 
		}
		return res;
	}

	//=========================그룹별 매칭 테이블 데이터를 가져오는 함수=========================
	public ArrayList<GroupMatching> getGroupMember(Connection conn, int groupId){
		ArrayList<GroupMatching> groupMatchings = new ArrayList<GroupMatching>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_MATCHING WHERE GROUP_ID = ?";
		try {
			pstm  = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
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
				groupMatching.setMemberName(rset.getString(9));
				groupMatchings.add(groupMatching);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR05, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return groupMatchings;
	}

	//=========================그룹 해지 시 그룹원들 만기일 중 가장 늦는거 가져오기=========================
	public Date getMaxExDate(Connection conn, int groupId) {
		Date maxExDate = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT MAX(EX_DATE) FROM SH_MATCHING WHERE GROUP_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				maxExDate = rset.getDate(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR05, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return maxExDate;
	}

	//=========================그룹 해지 날짜 update해주기=========================
	public int updateCloseDate(Connection conn, int groupId, Date closeDate) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_GROUP SET GROUP_LAST_DAY = ? WHERE GROUP_ID = ? ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setDate(1, closeDate);
			pstm.setInt(2, groupId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.GR04, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}

	//=========================그룹에 등록된 사람 있는지 확인=========================
	public boolean checkGroup(Connection conn, String memberId) {
		boolean flag = true;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_MATCHING WHERE MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberId);
			rset = pstm.executeQuery();
			while(rset.next()) {
				flag = false;
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MR05, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return flag;
	}
		
	//=========================소속 그룹 아이디 리스트 형태로 가져오기=========================
	public ArrayList<Integer> getgroupIdList(Connection conn, String userId){
		ArrayList<Integer> groupIdList = new ArrayList<Integer>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT GROUP_ID FROM SH_MATCHING WHERE MB_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			while(rset.next()) {
				int groupId = rset.getInt(1);
				groupIdList.add(groupId);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SG01, e);
		} finally {
			jdt.close(rset,pstm);
		}
		return groupIdList;
	}

	//자동 매칭 등록, 해지
	public int updateAutoDate(Connection conn, int groupId, String text) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE  SH_GROUP SET GROUP_AUTO_DATE = "+text+" WHERE GROUP_ID = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, groupId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.GR04, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
}
