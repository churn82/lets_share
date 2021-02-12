package com.kh.report.model.vo;

import java.sql.Date;

public class Report {

	private int reportIdx;
	private String mbId;
	private int groupIdx;
	private String reportContent;
	private Date reportDate;
	private String reportReply;
	private String reportClearYn;
	private String reportTitle;
	
	
	public Report() {
		
	}


	public int getReportIdx() {
		return reportIdx;
	}


	public void setReportIdx(int reportIdx) {
		this.reportIdx = reportIdx;
	}


	public String getMbId() {
		return mbId;
	}


	public void setMbId(String mbId) {
		this.mbId = mbId;
	}


	public int getGroupIdx() {
		return groupIdx;
	}


	public void setGroupIdx(int groupIdx) {
		this.groupIdx = groupIdx;
	}


	public String getReportContent() {
		return reportContent;
	}


	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}


	public Date getReportDate() {
		return reportDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	public String getReportReply() {
		return reportReply;
	}


	public void setReportReply(String reportReply) {
		this.reportReply = reportReply;
	}


	public String getReportClearYn() {
		return reportClearYn;
	}


	public void setReportClearYn(String reportClearYn) {
		this.reportClearYn = reportClearYn;
	}


	public String getReportTitle() {
		return reportTitle;
	}


	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}


	@Override
	public String toString() {
		return "Report [reportIdx=" + reportIdx + ", mbId=" + mbId + ", groupIdx=" + groupIdx + ", reportContent="
				+ reportContent + ", reportDate=" + reportDate + ", reportReply=" + reportReply + ", reportClearYn="
				+ reportClearYn + ", reportTitle=" + reportTitle + "]";
	}

	

	
	
	
	
	
}
