package org.agroup.practice;

import java.math.BigDecimal;
import java.util.Date;

public class Loan {

	private BigDecimal amount = BigDecimal.ZERO;
	private BigDecimal interestRate = BigDecimal.ZERO;
	private int term;
	private Date expireDate;
	
	public Loan() {}

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
