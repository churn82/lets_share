package com.kh.group.model.vo;

import java.sql.Date;

public class Group {

	private int groupId;
	private String memberId;
	private int memberCnt;
	private int groupPayDate;
	private String accountInfo;
	private String shareId;
	private String sharePw;
	private Date regdate;
	private Date autoDate;
	private String serviceCode;
	private int memberCntWish;
	private Date lastDay;
	
	public Group() {
		
	}

	public Group(int groupId, String memberId, int memberCnt, int groupPayDate, String accountInfo, String shareId,
			String sharePw, Date regdate, Date autoDate, String serviceCode, int memberCntWish, Date lastDay) {
		super();
		this.groupId = groupId;
		this.memberId = memberId;
		this.memberCnt = memberCnt;
		this.groupPayDate = groupPayDate;
		this.accountInfo = accountInfo;
		this.shareId = shareId;
		this.sharePw = sharePw;
		this.regdate = regdate;
		this.autoDate = autoDate;
		this.serviceCode = serviceCode;
		this.memberCntWish = memberCntWish;
		this.lastDay = lastDay;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getMemberCnt() {
		return memberCnt;
	}

	public void setMemberCnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}

	public int getGroupPayDate() {
		return groupPayDate;
	}

	public void setGroupPayDate(int groupPayDate) {
		this.groupPayDate = groupPayDate;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getSharePw() {
		return sharePw;
	}

	public void setSharePw(String sharePw) {
		this.sharePw = sharePw;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getAutoDate() {
		return autoDate;
	}

	public void setAutoDate(Date autoDate) {
		this.autoDate = autoDate;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public int getMemberCntWish() {
		return memberCntWish;
	}

	public void setMemberCntWish(int memberCntWish) {
		this.memberCntWish = memberCntWish;
	}

	public Date getLastDay() {
		return lastDay;
	}

	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", memberId=" + memberId + ", memberCnt=" + memberCnt + ", groupPayDate="
				+ groupPayDate + ", accountInfo=" + accountInfo + ", shareId=" + shareId + ", sharePw=" + sharePw
				+ ", regdate=" + regdate + ", autoDate=" + autoDate + ", serviceCode=" + serviceCode
				+ ", memberCntWish=" + memberCntWish + ", lastDay=" + lastDay + "]";
	}

	

	
	
}
