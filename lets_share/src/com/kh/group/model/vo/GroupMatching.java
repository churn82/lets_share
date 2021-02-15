package com.kh.group.model.vo;

import java.sql.Date;

public class GroupMatching {

	private String memberId;
	private int groupId;
	private char paymentYN;
	private char paymentConfirm;
	private Date regDate;
	private Date stDate;
	private Date exDate;
	private int payDate;
	private String memberName;
	
	public GroupMatching() {
		
	}
	
	public GroupMatching(String memberId, int groupId, char paymentYN, char paymentConfirm, Date regDate, Date stDate,
			Date exDate, int payDate, String memberName) {
		super();
		this.memberId = memberId;
		this.groupId = groupId;
		this.paymentYN = paymentYN;
		this.paymentConfirm = paymentConfirm;
		this.regDate = regDate;
		this.stDate = stDate;
		this.exDate = exDate;
		this.payDate = payDate;
		this.memberName = memberName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public char getPaymentYN() {
		return paymentYN;
	}

	public void setPaymentYN(char paymentYN) {
		this.paymentYN = paymentYN;
	}

	public char getPaymentConfirm() {
		return paymentConfirm;
	}

	public void setPaymentConfirm(char paymentConfirm) {
		this.paymentConfirm = paymentConfirm;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public Date getExDate() {
		return exDate;
	}

	public void setExDate(Date exDate) {
		this.exDate = exDate;
	}

	public int getPayDate() {
		return payDate;
	}

	public void setPayDate(int payDate) {
		this.payDate = payDate;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "GroupMatching [memberId=" + memberId + ", groupId=" + groupId + ", paymentYN=" + paymentYN
				+ ", paymentConfirm=" + paymentConfirm + ", regDate=" + regDate + ", stDate=" + stDate + ", exDate="
				+ exDate + ", payDate=" + payDate + ", memberName=" + memberName + "]";
	}

	
}
