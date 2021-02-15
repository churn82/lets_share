package com.kh.report.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.report.model.vo.Report;

public class ReportDao {

	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// 모든 신고내역 개수 가져오는 메서드
	public int getreportCnt(Connection conn) {
		int allReportCnt = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_REPORT";
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			if(rset.next()) {
				allReportCnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allReportCnt;
	}
	// [검색]한 모든 신고내역 개수 가져오는 메서드
	public int getreportCnt(Connection conn, String select, String searchText) {
		int allReportCnt = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_REPORT WHERE "+select+" LIKE ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, searchText);
			rset = pstm.executeQuery();
			if(rset.next()) {
				allReportCnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allReportCnt;
	}
	
	
	// 처리된 신고내역 개수 가져오는 메서드
	public int getreportCnt(Connection conn, int clear) {
		int allReportCnt = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_REPORT WHERE REPORT_CLEAR = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, clear);
			rset = pstm.executeQuery();
			if(rset.next()) {
				allReportCnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allReportCnt;
	}
	
	// [검색] 처리된 신고내역 개수 가져오는 메서드
	public int getreportCnt(Connection conn, int clear, String select, String searchText) {
		int allReportCnt = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT COUNT(*) FROM SH_REPORT WHERE REPORT_CLEAR = ? AND "+select+" LIKE ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, clear);
			pstm.setString(2, searchText);
			rset = pstm.executeQuery();
			if(rset.next()) {
				allReportCnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allReportCnt;
	}
	
	//모든 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(Connection conn, int start, int end){
		ArrayList<Report> reportList = new ArrayList<Report>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = 
				  "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, R.*"
				+ "        FROM (SELECT * FROM SH_REPORT ORDER BY REPORT_DATE DESC) R"
				+ ") WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, start);
			pstm.setInt(2, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Report report = new Report();
				report.setReportIdx(rset.getInt("REPORT_IDX"));
				report.setMemberId(rset.getString("MB_ID"));
				report.setGroupId(rset.getInt("GROUP_IDX"));
				report.setContent(rset.getString("REPORT_CONTENT"));
				report.setRegdate(rset.getDate("REPORT_DATE"));
				report.setReply(rset.getString("REPORT_REPLY"));
				report.setClear(rset.getInt("REPORT_CLEAR"));
				report.setTitle(rset.getString("REPORT_TITLE"));
				reportList.add(report);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(rset, pstm);
		}
		return reportList;
	}
	//[검색]한 모든 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(Connection conn, int start, int end, String select, String searchText){
		ArrayList<Report> reportList = new ArrayList<Report>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = 
				  "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, R.*"
				+ "        FROM (SELECT * FROM SH_REPORT WHERE "+select+" LIKE ? ORDER BY REPORT_DATE DESC) R"
				+ ") WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, searchText);
			pstm.setInt(2, start);
			pstm.setInt(3, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Report report = new Report();
				report.setReportIdx(rset.getInt("REPORT_IDX"));
				report.setMemberId(rset.getString("MB_ID"));
				report.setGroupId(rset.getInt("GROUP_IDX"));
				report.setContent(rset.getString("REPORT_CONTENT"));
				report.setRegdate(rset.getDate("REPORT_DATE"));
				report.setReply(rset.getString("REPORT_REPLY"));
				report.setClear(rset.getInt("REPORT_CLEAR"));
				report.setTitle(rset.getString("REPORT_TITLE"));
				reportList.add(report);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(rset, pstm);
		}
		return reportList;
	}
	
	//처리된 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(Connection conn, int start, int end, int clear){
		ArrayList<Report> reportList = new ArrayList<Report>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = 
				  "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, R.*"
				+ "        FROM (SELECT * FROM SH_REPORT WHERE REPORT_CLEAR = ? ORDER BY REPORT_DATE DESC) R"
				+ ") WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, clear);
			pstm.setInt(2, start);
			pstm.setInt(3, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Report report = new Report();
				report.setReportIdx(rset.getInt("REPORT_IDX"));
				report.setMemberId(rset.getString("MB_ID"));
				report.setGroupId(rset.getInt("GROUP_IDX"));
				report.setContent(rset.getString("REPORT_CONTENT"));
				report.setRegdate(rset.getDate("REPORT_DATE"));
				report.setReply(rset.getString("REPORT_REPLY"));
				report.setClear(rset.getInt("REPORT_CLEAR"));
				report.setTitle(rset.getString("REPORT_TITLE"));
				reportList.add(report);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(rset, pstm);
		}
		return reportList;
	}
	
	//[검색]처리된 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(Connection conn, int start, int end, int clear, String select, String searchText){
		ArrayList<Report> reportList = new ArrayList<Report>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = 
				  "SELECT * FROM ("
				+ "    SELECT ROWNUM NUM, R.*"
				+ "        FROM (SELECT * FROM SH_REPORT WHERE REPORT_CLEAR = ? AND "+select+" LIKE ? ORDER BY REPORT_DATE DESC) R"
				+ ") WHERE NUM BETWEEN ? AND ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, clear);
			pstm.setString(2, searchText);
			pstm.setInt(3, start);
			pstm.setInt(4, end);
			rset = pstm.executeQuery();
			while(rset.next()) {
				Report report = new Report();
				report.setReportIdx(rset.getInt("REPORT_IDX"));
				report.setMemberId(rset.getString("MB_ID"));
				report.setGroupId(rset.getInt("GROUP_IDX"));
				report.setContent(rset.getString("REPORT_CONTENT"));
				report.setRegdate(rset.getDate("REPORT_DATE"));
				report.setReply(rset.getString("REPORT_REPLY"));
				report.setClear(rset.getInt("REPORT_CLEAR"));
				report.setTitle(rset.getString("REPORT_TITLE"));
				reportList.add(report);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(rset, pstm);
		}
		return reportList;
	}
	
	
	
	// detail에서 선택한 reportIdx로 report정보 가져오는 메서드
	public Report getReport(Connection conn, int reportIdx) {
		Report repoert = new Report();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SH_REPORT WHERE REPORT_IDX = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, reportIdx);
			rset = pstm.executeQuery();
			if(rset.next()) {
				repoert.setReportIdx(rset.getInt("REPORT_IDX"));
				repoert.setMemberId(rset.getString("MB_ID"));
				repoert.setGroupId(rset.getInt("GROUP_IDX"));
				repoert.setContent(rset.getString("REPORT_CONTENT"));
				repoert.setRegdate(rset.getDate("REPORT_DATE"));
				repoert.setReply(rset.getString("REPORT_REPLY"));
				repoert.setClear(rset.getInt("REPORT_CLEAR"));
				repoert.setTitle(rset.getString("REPORT_TITLE"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		} finally {
			jdt.close(rset, pstm);
		}
		return repoert;
	}
	
	//관리자가 신고 처리상태 및 댓글 update하는 메서드
	public int replyReport(Connection conn, String reply, int clear, int reportIdx) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "UPDATE SH_REPORT SET REPORT_REPLY = ?, REPORT_CLEAR = ? WHERE REPORT_IDX = ?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, reply);
			pstm.setInt(2, clear);
			pstm.setInt(3, reportIdx);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UR01, e);
		} finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	//신고글을 작성하는 메서드
	public int writeReport(Connection conn, String memberId, String title, int gropuId, String contents) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "INSERT INTO SH_REPORT (REPORT_IDX, MB_ID, GROUP_IDX, REPORT_CONTENT, REPORT_TITLE)"
				+ "VALUES (SC_REPORT_IDX.nextval, ?, ?, ?, ?)";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, memberId);
			pstm.setInt(2, gropuId);
			pstm.setString(3, contents);
			pstm.setString(4, title);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IR01, e);
 		} finally {
 			jdt.close(pstm);
 		}
		return res;
	}
	
}
