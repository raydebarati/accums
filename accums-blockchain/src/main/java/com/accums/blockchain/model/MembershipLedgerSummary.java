package com.accums.blockchain.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MembershipLedgerSummary {
	
	@Id
	private String id;
	
	private String memberId;	
	private BigDecimal utilizedAmt;
	private BigDecimal limit;
	private BigDecimal amtRemaining;
	private Date benefitStartDate;
	private Date benefitEndDate;
	private String memberName;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public BigDecimal getUtilizedAmt() {
		return utilizedAmt;
	}
	public void setUtilizedAmt(BigDecimal utilizedAmt) {
		this.utilizedAmt = utilizedAmt;
	}
	public BigDecimal getLimit() {
		return limit;
	}
	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}
	public BigDecimal getAmtRemaining() {
		return amtRemaining;
	}
	public void setAmtRemaining(BigDecimal amtRemaining) {
		this.amtRemaining = amtRemaining;
	}
	public Date getBenefitStartDate() {
		return benefitStartDate;
	}
	public void setBenefitStartDate(Date benefitStartDate) {
		this.benefitStartDate = benefitStartDate;
	}
	public Date getBenefitEndDate() {
		return benefitEndDate;
	}
	public void setBenefitEndDate(Date benefitEndDate) {
		this.benefitEndDate = benefitEndDate;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	
}
