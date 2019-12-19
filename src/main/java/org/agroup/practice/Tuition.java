package org.agroup.practice;

import java.math.BigDecimal;
import java.util.Date;

public class Tuition {

	private String rejReason;
	private BigDecimal finAsst;
	private BigDecimal studOblg;
	private BigDecimal totalAsstRequested;
	private BigDecimal totalHoursRequested;
	private BigDecimal updatedPreDPHours;
	private BigDecimal updatedUsedAsst;
	private BigDecimal updatedUsedHours;
	private BigDecimal maxAsst;
	private BigDecimal maxHourlyCost;
	private BigDecimal maxHours;
	private BigDecimal maxPreDPHours;
	private boolean autoApproval;
	private Date lastUpdatedDate;

	public String getRejReason() {
		return rejReason;
	}

	public void setRejReason(String rejReason) {
		this.rejReason = rejReason;
	}

	public BigDecimal getFinAsst() {
		return finAsst;
	}

	public void setFinAsst(BigDecimal finAsst) {
		this.finAsst = finAsst;
	}

	public BigDecimal getStudOblg() {
		return studOblg;
	}

	public void setStudOblg(BigDecimal studOblg) {
		this.studOblg = studOblg;
	}

	public BigDecimal getTotalAsstRequested() {
		return totalAsstRequested;
	}

	public void setTotalAsstRequested(BigDecimal totalAsstRequested) {
		this.totalAsstRequested = totalAsstRequested;
	}

	public BigDecimal getTotalHoursRequested() {
		return totalHoursRequested;
	}

	public void setTotalHoursRequested(BigDecimal totalHoursRequested) {
		this.totalHoursRequested = totalHoursRequested;
	}

	public BigDecimal getUpdatedPreDPHours() {
		return updatedPreDPHours;
	}

	public void setUpdatedPreDPHours(BigDecimal updatedPreDPHours) {
		this.updatedPreDPHours = updatedPreDPHours;
	}

	public BigDecimal getUpdatedUsedAsst() {
		return updatedUsedAsst;
	}

	public void setUpdatedUsedAsst(BigDecimal updatedUsedAsst) {
		this.updatedUsedAsst = updatedUsedAsst;
	}

	public BigDecimal getUpdatedUsedHours() {
		return updatedUsedHours;
	}

	public void setUpdatedUsedHours(BigDecimal updatedUsedHours) {
		this.updatedUsedHours = updatedUsedHours;
	}

	public BigDecimal getMaxAsst() {
		return maxAsst;
	}

	public void setMaxAsst(BigDecimal maxAsst) {
		this.maxAsst = maxAsst;
	}

	public BigDecimal getMaxHourlyCost() {
		return maxHourlyCost;
	}

	public void setMaxHourlyCost(BigDecimal maxHourlyCost) {
		this.maxHourlyCost = maxHourlyCost;
	}

	public BigDecimal getMaxHours() {
		return maxHours;
	}

	public void setMaxHours(BigDecimal maxHours) {
		this.maxHours = maxHours;
	}

	public BigDecimal getMaxPreDPHours() {
		return maxPreDPHours;
	}

	public void setMaxPreDPHours(BigDecimal maxPreDPHours) {
		this.maxPreDPHours = maxPreDPHours;
	}

	public boolean isAutoApproval() {
		return autoApproval;
	}

	public void setAutoApproval(boolean autoApproval) {
		this.autoApproval = autoApproval;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
