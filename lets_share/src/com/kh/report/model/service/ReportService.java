package com.kh.report.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.report.model.dao.ReportDao;
import com.kh.report.model.vo.Report;

public class ReportService {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	ReportDao reportDao = new ReportDao();
	
	
	// 모든 신고내역 개수 가져오는 메서드
	public int getreportCnt() {
		Connection conn = jdt.getConnection();
		int allReportCnt = 0;
		try {
			allReportCnt = reportDao.getreportCnt(conn);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return allReportCnt;
	}
	// [검색]한 모든 신고내역 개수 가져오는 메서드
	public int getreportCnt(String select, String searchText) {
		Connection conn = jdt.getConnection();
		int allReportCnt = 0;
		try {
			allReportCnt = reportDao.getreportCnt(conn, select, searchText);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return allReportCnt;
	}
	
	
	// 처리된 신고내역 개수 가져오는 메서드
	public int getreportCnt(int clear) {
		Connection conn = jdt.getConnection();
		int allReportCnt = 0;
		try {
			allReportCnt = reportDao.getreportCnt(conn, clear);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return allReportCnt;
	}
	
	// [검색]처리된 신고내역 개수 가져오는 메서드
	public int getreportCnt(int clear, String select, String searchText) {
		Connection conn = jdt.getConnection();
		int allReportCnt = 0;
		try {
			allReportCnt = reportDao.getreportCnt(conn, clear, select, searchText);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return allReportCnt;
	}
	
	
	//모든 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(int start, int end){
		
		Connection conn = jdt.getConnection();
		ArrayList<Report> reportList = null;
		
		try {
			reportList = reportDao.getReportList(conn, start, end);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return reportList;
	}
	//[검색]한 모든 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(int start, int end, String select, String searchText){
		Connection conn = jdt.getConnection();
		ArrayList<Report> reportList = null;
		try {
			reportList = reportDao.getReportList(conn, start, end, select, searchText);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return reportList;
	}
	
	//처리 완료 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(int start, int end, int clear){
		Connection conn = jdt.getConnection();
		ArrayList<Report> reportList = null;
		
		try {
			reportList = reportDao.getReportList(conn, start, end, clear);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return reportList;
	}
	//[검색] 처리 완료 모든 신고 내역을 (페이징해서) 가져오는 메서드
	public ArrayList<Report> getReportList(int start, int end, int clear, String select, String searchText){
		Connection conn = jdt.getConnection();
		ArrayList<Report> reportList = null;
		try {
			reportList = reportDao.getReportList(conn, start, end, clear, select, searchText);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return reportList;
	}
	
	
	// detail에서 선택한 reportIdx로 report정보 가져오는 메서드
	public Report getReport(int reportIdx) {
		Connection conn = jdt.getConnection();
		Report report = null;
		try {
			report = reportDao.getReport(conn, reportIdx);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return report;
	}

	
	//관리자가 신고 처리상태 및 댓글 update하는 메서드
	public int replyReport(String reply, int clear, int reportIdx) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = reportDao.replyReport(conn, reply, clear, reportIdx);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//신고글을 작성하는 메서드
	public int writeReport(String memberId, String title, int gropuId, String contents) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			res = reportDao.writeReport(conn, memberId, title, gropuId, contents);
			jdt.commit(conn);
		} catch (DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error, e);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
}
