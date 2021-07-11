package com.bsc.qa.facets.afa.pojo;

import com.github.ffpojo.metadata.positional.annotation.PositionalField;
import com.github.ffpojo.metadata.positional.annotation.PositionalRecord;

@PositionalRecord
public class CVSFile {
	
	private String claimId;
	private String fileName;
	private String vendorName;
	private String groupNumber;
	private String subgroupId;
	private String subscriberId;
	private String personNumber;
	private String claimNumber;
	private String claimVersionNumber;
	private String claimAmount;
	private String clientPrice;
	private String bscRevenueAmount;
	private String checkNumber;
	private String checkDate;
	private String serviceDate;
	private String payeeId;
	private String payeeName;
	private String planId;
	private String productId;
	private String productCategory;
	private String classId;
	private String productBusinessCategory;
	private String productValueCode;
	private String lineOfBusinessId;
	private String legalEntity;
	private String billedAmount;
	private String allowedAmount;
	private String deductibleAmount;
	private String coinsuranceAmount;
	private String copayAmount;
	private String diagnosisCode;
	private String diagnosisCodeType;
	private String procedureCode;
	private String hcpcs_id;
	private String claimTransactionType;
	
	@PositionalField(initialPosition = 1,finalPosition=15)
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	@PositionalField(initialPosition = 16,finalPosition=50)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@PositionalField(initialPosition =51 ,finalPosition=54)
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	@PositionalField(initialPosition =55,finalPosition=62)
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	@PositionalField(initialPosition =63,finalPosition=68)
	public String getSubgroupId() {
		return subgroupId;
	}
	public void setSubgroupId(String subgroupId) {
		this.subgroupId = subgroupId;
	}
	@PositionalField(initialPosition =69,finalPosition=77)
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	@PositionalField(initialPosition = 78,finalPosition=79)
	public String getPersonNumber() {
		return personNumber;
	}
	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}
	@PositionalField(initialPosition = 80,finalPosition=91)
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	@PositionalField(initialPosition = 92,finalPosition=96)
	public String getClaimVersionNumber() {
		return claimVersionNumber;
	}
	public void setClaimVersionNumber(String claimVersionNumber) {
		this.claimVersionNumber = claimVersionNumber;
	}
	@PositionalField(initialPosition = 97,finalPosition=107)
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
	@PositionalField(initialPosition = 108,finalPosition=116)
	public String getClientPrice() {
		return clientPrice;
	}
	public void setClientPrice(String clientPrice) {
		this.clientPrice = clientPrice;
	}
	@PositionalField(initialPosition = 117,finalPosition=126)
	public String getBscRevenueAmount() {
		return bscRevenueAmount;
	}
	public void setBscRevenueAmount(String bscRevenueAmount) {
		this.bscRevenueAmount = bscRevenueAmount;
	}
	@PositionalField(initialPosition = 127,finalPosition=136)
	public String getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	@PositionalField(initialPosition = 137,finalPosition=145)
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	@PositionalField(initialPosition = 146,finalPosition=154)
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	@PositionalField(initialPosition = 155,finalPosition=163)
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	@PositionalField(initialPosition = 164,finalPosition=213)
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	@PositionalField(initialPosition = 214,finalPosition=221)
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	@PositionalField(initialPosition = 222,finalPosition=229)
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@PositionalField(initialPosition = 230,finalPosition=230)
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	@PositionalField(initialPosition = 231,finalPosition=234)
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	@PositionalField(initialPosition = 235,finalPosition=238)
	public String getProductBusinessCategory() {
		return productBusinessCategory;
	}
	public void setProductBusinessCategory(String productBusinessCategory) {
		this.productBusinessCategory = productBusinessCategory;
	}
	@PositionalField(initialPosition = 239,finalPosition=242)
	public String getProductValueCode() {
		return productValueCode;
	}
	public void setProductValueCode(String productValueCode) {
		this.productValueCode = productValueCode;
	}
	@PositionalField(initialPosition = 243,finalPosition=246)
	public String getLineOfBusinessId() {
		return lineOfBusinessId;
	}
	public void setLineOfBusinessId(String lineOfBusinessId) {
		this.lineOfBusinessId = lineOfBusinessId;
	}
	@PositionalField(initialPosition = 247,finalPosition=250)
	public String getLegalEntity() {
		return legalEntity;
	}
	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}
	@PositionalField(initialPosition = 251,finalPosition=260)
	public String getBilledAmount() {
		return billedAmount;
	}
	public void setBilledAmount(String billedAmount) {
		this.billedAmount = billedAmount;
	}
	@PositionalField(initialPosition = 261,finalPosition=270)
	public String getAllowedAmount() {
		return allowedAmount;
	}
	public void setAllowedAmount(String allowedAmount) {
		this.allowedAmount = allowedAmount;
	}
	@PositionalField(initialPosition = 271,finalPosition=280)
	public String getDeductibleAmount() {
		return deductibleAmount;
	}
	public void setDeductibleAmount(String deductibleAmount) {
		this.deductibleAmount = deductibleAmount;
	}
	@PositionalField(initialPosition = 281,finalPosition=290)
	public String getCoinsuranceAmount() {
		return coinsuranceAmount;
	}
	public void setCoinsuranceAmount(String coinsuranceAmount) {
		this.coinsuranceAmount = coinsuranceAmount;
	}
	@PositionalField(initialPosition = 291,finalPosition=300)
	public String getCopayAmount() {
		return copayAmount;
	}
	public void setCopayAmount(String copayAmount) {
		this.copayAmount = copayAmount;
	}
	@PositionalField(initialPosition = 301,finalPosition=310)
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	@PositionalField(initialPosition = 311,finalPosition=311)
	public String getDiagnosisCodeType() {
		return diagnosisCodeType;
	}
	public void setDiagnosisCodeType(String diagnosisCodeType) {
		this.diagnosisCodeType = diagnosisCodeType;
	}
	@PositionalField(initialPosition = 312,finalPosition=318)
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	@PositionalField(initialPosition = 319,finalPosition=337)
	public String getHcpcs_id() {
		return hcpcs_id;
	}
	public void setHcpcs_id(String hcpcs_id) {
		this.hcpcs_id = hcpcs_id;
	}
	@PositionalField(initialPosition = 338,finalPosition=338)
	public String getClaimTransactionType() {
		return claimTransactionType;
	}
	public void setClaimTransactionType(String claimTransactionType) {
		this.claimTransactionType = claimTransactionType;
	}
	@Override
	public String toString() {
		return "CVSFile [claimId=" + claimId + ", fileName=" + fileName
				+ ", vendorName=" + vendorName + ", groupNumber=" + groupNumber
				+ ", subgroupId=" + subgroupId + ", subscriberId="
				+ subscriberId + ", personNumber=" + personNumber
				+ ", claimNumber=" + claimNumber + ", claimVersionNumber="
				+ claimVersionNumber + ", claimAmount=" + claimAmount
				+ ", clientPrice=" + clientPrice + ", bscRevenueAmount="
				+ bscRevenueAmount + ", checkNumber=" + checkNumber
				+ ", checkDate=" + checkDate + ", serviceDate=" + serviceDate
				+ ", payeeId=" + payeeId + ", payeeName=" + payeeName
				+ ", planId=" + planId + ", productId=" + productId
				+ ", productCategory=" + productCategory + ", classId="
				+ classId + ", productBusinessCategory="
				+ productBusinessCategory + ", productValueCode="
				+ productValueCode + ", lineOfBusinessId=" + lineOfBusinessId
				+ ", legalEntity=" + legalEntity + ", billedAmount="
				+ billedAmount + ", allowedAmount=" + allowedAmount
				+ ", deductibleAmount=" + deductibleAmount
				+ ", coinsuranceAmount=" + coinsuranceAmount + ", copayAmount="
				+ copayAmount + ", diagnosisCode=" + diagnosisCode
				+ ", diagnosisCodeType=" + diagnosisCodeType
				+ ", procedureCode=" + procedureCode + ", hcpcs_id=" + hcpcs_id
				+ ", claimTransactionType=" + claimTransactionType + "]";
	}
	
	
}

