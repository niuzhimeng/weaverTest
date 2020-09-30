package com.weavernorth.voucher.entity;
/**
 * @description: EAS集成   凭证信息 表头 实体类
 * @author: DJC
 * @date: 2019.11.19
 * @version: 1.0
 */
public class VoucherHeaderEntity {
	private String companyNumber;//公司编码
	private String bookedDate;//记账日期
	private String bizDate;//业务日期
	private String periodYear;//会计期间-年
	private String periodNumber;//会计期间-编码
	private String voucherType;//凭证字（凭证类型）
	private String voucherNumber;//凭证号
	
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getBizDate() {
		return bizDate;
	}
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	public String getPeriodYear() {
		return periodYear;
	}
	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}
	public String getPeriodNumber() {
		return periodNumber;
	}
	public void setPeriodNumber(String periodNumber) {
		this.periodNumber = periodNumber;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bizDate == null) ? 0 : bizDate.hashCode());
		result = prime * result + ((bookedDate == null) ? 0 : bookedDate.hashCode());
		result = prime * result + ((companyNumber == null) ? 0 : companyNumber.hashCode());
		result = prime * result + ((periodNumber == null) ? 0 : periodNumber.hashCode());
		result = prime * result + ((periodYear == null) ? 0 : periodYear.hashCode());
		result = prime * result + ((voucherNumber == null) ? 0 : voucherNumber.hashCode());
		result = prime * result + ((voucherType == null) ? 0 : voucherType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoucherHeaderEntity other = (VoucherHeaderEntity) obj;
		if (bizDate == null) {
			if (other.bizDate != null)
				return false;
		} else if (!bizDate.equals(other.bizDate))
			return false;
		if (bookedDate == null) {
			if (other.bookedDate != null)
				return false;
		} else if (!bookedDate.equals(other.bookedDate))
			return false;
		if (companyNumber == null) {
			if (other.companyNumber != null)
				return false;
		} else if (!companyNumber.equals(other.companyNumber))
			return false;
		if (periodNumber == null) {
			if (other.periodNumber != null)
				return false;
		} else if (!periodNumber.equals(other.periodNumber))
			return false;
		if (periodYear == null) {
			if (other.periodYear != null)
				return false;
		} else if (!periodYear.equals(other.periodYear))
			return false;
		if (voucherNumber == null) {
			if (other.voucherNumber != null)
				return false;
		} else if (!voucherNumber.equals(other.voucherNumber))
			return false;
		if (voucherType == null) {
			if (other.voucherType != null)
				return false;
		} else if (!voucherType.equals(other.voucherType))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "VoucherHeaderEntity [companyNumber=" + companyNumber + ", bookedDate=" + bookedDate + ", bizDate="
				+ bizDate + ", periodYear=" + periodYear + ", periodNumber=" + periodNumber + ", voucherType="
				+ voucherType + ", voucherNumber=" + voucherNumber + "]";
	}
	
	
	
}
