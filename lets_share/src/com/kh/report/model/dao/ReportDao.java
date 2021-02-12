package com.kh.report.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	
	
}
