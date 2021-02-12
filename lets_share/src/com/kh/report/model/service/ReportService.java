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
	
	
	
	
}
