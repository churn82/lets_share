package com.kh.member.model.vo;

import java.sql.Date;

public class Member {

	private String mbId;
	private String mbpassword;
	private String mbnick;
	private String mbtel;
	private String mbemail;
	private int mbpoint;
	private String mblevel;
	private Date mbRegisterDate;
	private Date mbLeaveDate;
	//카카오
	private String mbkakaoId;
	private String mbkakaopw;
	private String mbkakaonick;
	private String mbkakaoemail;
	
	public String getMbkakaoId() {
		return mbkakaoId;
	}

	public void setMbkakaoId(String mbkakaoId) {
		this.mbkakaoId = mbkakaoId;
	}

	public String getMbkakaopw() {
		return mbkakaopw;
	}

	public void setMbkakaopw(String mbkakaopw) {
		this.mbkakaopw = mbkakaopw;
	}

	public String getMbkakaonick() {
		return mbkakaonick;
	}

	public void setMbkakaonick(String mbkakaonick) {
		this.mbkakaonick = mbkakaonick;
	}

	public String getMbkakaoemail() {
		return mbkakaoemail;
	}

	public void setMbkakaoemail(String mbkakaoemail) {
		this.mbkakaoemail = mbkakaoemail;
	}

	public Member() {
		
	}
	/*
	public Member(String mbId, String mbpassword, String mbnick, String mbtel, String mbemail, int mbpoint,
			String mblevel, Date mbRegisterDate, Date mbLeaveDate) {
		
		super();
		this.mbId = mbId;
		this.mbpassword = mbpassword;
		this.mbnick = mbnick;
		this.mbtel = mbtel;
		this.mbemail = mbemail;
		this.mbpoint = mbpoint;
		this.mblevel = mblevel;
		this.mbRegisterDate = mbRegisterDate;
		this.mbLeaveDate = mbLeaveDate;
	}
	*/
	
	public Member(String mbId, String mbpassword, String mbnick, String mbtel, String mbemail, int mbpoint,
			String mblevel, Date mbRegisterDate, Date mbLeaveDate, String mbkakaoId, String mbkakaopw,
			String mbkakaonick, String mbkakaoemail) {
		super();
		this.mbId = mbId;
		this.mbpassword = mbpassword;
		this.mbnick = mbnick;
		this.mbtel = mbtel;
		this.mbemail = mbemail;
		this.mbpoint = mbpoint;
		this.mblevel = mblevel;
		this.mbRegisterDate = mbRegisterDate;
		this.mbLeaveDate = mbLeaveDate;
		this.mbkakaoId = mbkakaoId;
		this.mbkakaopw = mbkakaopw;
		this.mbkakaonick = mbkakaonick;
		this.mbkakaoemail = mbkakaoemail;
	}

	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getMbPassword() {
		return mbpassword;
	}
	public void setMbPassword(String mbPassword) {
		this.mbpassword = mbPassword;
	}
	public String getMbNick() {
		return mbnick;
	}
	public void setMbNick(String mbnick) {
		this.mbnick = mbnick;
	}
	public String getMbtel() {
		return mbtel;
	}
	public void setMbtel(String mbtel) {
		this.mbtel = mbtel;
	}
	public String getMbemail() {
		return mbemail;
	}
	public void setMbemail(String mbemail) {
		this.mbemail = mbemail;
	}
	public int getMbpoint() {
		return mbpoint;
	}
	public void setMbpoint(int mbpoint) {
		this.mbpoint = mbpoint;
	}
	public String getMblevel() {
		return mblevel;
	}
	public void setMblevel(String mblevel) {
		this.mblevel = mblevel;
	}
	public Date getMbRegisterDate() {
		return mbRegisterDate;
	}
	public void setMbRegisterDate(Date mbRegisterDate) {
		this.mbRegisterDate = mbRegisterDate;
	}
	public Date getMbLeaveDate() {
		return mbLeaveDate;
	}
	public void setMbLeaveDate(Date mbLeaveDate) {
		this.mbLeaveDate = mbLeaveDate;
	}
	@Override
	public String toString() {
		return "Member [mbId=" + mbId + ", mbpassword=" + mbpassword + ", mbnick=" + mbnick + ", mbtel=" + mbtel
				+ ", mbemail=" + mbemail + ", mbpoint=" + mbpoint + ", mblevel=" + mblevel + ", mbRegisterDate="
				+ mbRegisterDate + ", mbLeaveDate=" + mbLeaveDate + ", mbkakaoId=" + mbkakaoId + ", mbkakaopw="
				+ mbkakaopw + ", mbkakaonick=" + mbkakaonick + ", mbkakaoemail=" + mbkakaoemail + "]";
	}
	
}
