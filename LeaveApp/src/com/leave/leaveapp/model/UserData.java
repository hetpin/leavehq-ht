package com.leave.leaveapp.model;

import java.util.Date;

public class UserData {
	public UserData() {
		super();
	}
	
	private String mUserName;
	private String mEmail;
	private String mLastName;
	private String mFirstName;
	private String mAvatar;
	private String mId;
	private String mGroupId;
	private String mRole;
	private String mPhone;
	private String mAddress;
	private int mEnabled;
	private double mSalary;
	private String mSex;
	private Date mDOB;
	private String mStatus;
	private Date mValidTo;
	private Date mCreatedTime;
	private int mWarningDeg;
	private Date mLastLeave;
	private String mCertification;
	private String mCityId;
	private String mTimeZoneId;
	private String mToken;
	private String mColor;

	public String getUserName() {
		return mUserName;
	}

	public void setUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getLastName() {
		return mLastName;
	}

	public void setLastName(String mLastName) {
		this.mLastName = mLastName;
	}

	public String getFirstName() {
		return mFirstName;
	}

	public void setFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}

	public String getAvatar() {
		return mAvatar;
	}

	public void setAvatar(String mAvatar) {
		this.mAvatar = mAvatar;
	}

	public String getId() {
		return mId;
	}

	public void setId(String mId) {
		this.mId = mId;
	}

	public String getGroupId() {
		return mGroupId;
	}

	public void setGroupId(String mGroupId) {
		this.mGroupId = mGroupId;
	}

	public String getRole() {
		return mRole;
	}

	public void setRole(String mRole) {
		this.mRole = mRole;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getAddress() {
		return mAddress;
	}

	public void setAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public int getEnabled() {
		return mEnabled;
	}

	public void setEnabled(int mEnabled) {
		this.mEnabled = mEnabled;
	}

	public double getSalary() {
		return mSalary;
	}

	public void setSalary(double mSalary) {
		this.mSalary = mSalary;
	}

	public String getSex() {
		return mSex;
	}

	public void setSex(String mSex) {
		this.mSex = mSex;
	}

	public Date getDOB() {
		return mDOB;
	}

	public void setDOB(Date mDOB) {
		this.mDOB = mDOB;
	}

	public String getStatus() {
		return mStatus;
	}

	public void setStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	public Date getValidTo() {
		return mValidTo;
	}

	public void setValidTo(Date mValidTo) {
		this.mValidTo = mValidTo;
	}

	public Date getCreatedTime() {
		return mCreatedTime;
	}

	public void setCreatedTime(Date mCreatedTime) {
		this.mCreatedTime = mCreatedTime;
	}

	public int getWarningDeg() {
		return mWarningDeg;
	}

	public void setWarningDeg(int mWarningDeg) {
		this.mWarningDeg = mWarningDeg;
	}

	public Date getLastLeave() {
		return mLastLeave;
	}

	public void setLastLeave(Date mLastLeave) {
		this.mLastLeave = mLastLeave;
	}

	public String getCertification() {
		return mCertification;
	}

	public void setCertification(String mCertification) {
		this.mCertification = mCertification;
	}

	public String getCityId() {
		return mCityId;
	}

	public void setCityId(String mCityId) {
		this.mCityId = mCityId;
	}

	public String getTimeZoneId() {
		return mTimeZoneId;
	}

	public void setTimeZoneId(String mTimeZoneId) {
		this.mTimeZoneId = mTimeZoneId;
	}

	public String getToken() {
		return mToken;
	}

	public void setToken(String mToken) {
		this.mToken = mToken;
	}

	public String getColor() {
		return mColor;
	}

	public void setColor(String mColor) {
		this.mColor = mColor;
	}

	public UserData(String mUserName, String mEmail, String mLastName,
			String mFirstName, String mAvatar, String mId, String mGroupId,
			String mRole, String mPhone, String mAddress, int mEnabled,
			double mSalary, String mSex, Date mDOB, String mStatus,
			Date mValidTo, Date mCreatedTime, int mWarningDeg, Date mLastLeave,
			String mCertification, String mCityId, String mTimeZoneId,
			String mToken, String mColor) {
		super();
		this.mUserName = mUserName;
		this.mEmail = mEmail;
		this.mLastName = mLastName;
		this.mFirstName = mFirstName;
		this.mAvatar = mAvatar;
		this.mId = mId;
		this.mGroupId = mGroupId;
		this.mRole = mRole;
		this.mPhone = mPhone;
		this.mAddress = mAddress;
		this.mEnabled = mEnabled;
		this.mSalary = mSalary;
		this.mSex = mSex;
		this.mDOB = mDOB;
		this.mStatus = mStatus;
		this.mValidTo = mValidTo;
		this.mCreatedTime = mCreatedTime;
		this.mWarningDeg = mWarningDeg;
		this.mLastLeave = mLastLeave;
		this.mCertification = mCertification;
		this.mCityId = mCityId;
		this.mTimeZoneId = mTimeZoneId;
		this.mToken = mToken;
		this.mColor = mColor;
	}
}
