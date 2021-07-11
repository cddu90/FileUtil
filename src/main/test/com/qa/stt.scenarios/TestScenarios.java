
package com.bsc.qa.facets.bor_file_generator_stt.scenarios;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bsc.qa.facets.afa.pojo.AmountFields;
import com.bsc.qa.facets.afa.pojo.BORDatabase;
import com.bsc.qa.facets.afa.pojo.BORFile;
import com.bsc.qa.facets.afa.pojo.BorAdjustmentDB;
import com.bsc.qa.facets.afa.pojo.CVSFile;
import com.bsc.qa.facets.bor_file_generator_stt.util.DatabaseValidation;
import com.bsc.qa.facets.bor_file_generator_stt.util.HelperClass;

public class TestScenarios {
	String[] pharmacies = {"0107777,EXPO PHARMACY",                         
			"5600742,CVS PHARMACY 16748",                         
			"5664316,LOYALTY PHARMACY 2",                         
			"5621188,CVS PHARMACY 08895",                         
			"0107777,EXPO PHARMACY",                         
			"1167976,ROCKDALE PHARMACY",                         
			"1023768,NORTH FLORIDA PHARMA",                         
			"5660584,OMNI FAMILY HEALTH 3",                         
			"0517459,RITE AID PHARMACY 05",                         
			"5619943,CVS PHARMACY 09627",                         
			"2591990,PEOPLE'S PHARMACY",                         
			"5600665,CVS PHARMACY 16040",                         
			"5619690,CVS PHARMACY 09774",                         
			"4408705,LOWE'S DRUGS",                         
			"5633448,RITE AID PHARMACY 06",                         
			"5916258,MANOR PHARMACY 1",                         
			"2803509,WANEK PHARMACY"  };
	
	
	String[] CVSPayee= {"5620201,CVS PHARMACY 08881",	
			"0515467,RITE AID PHARMACY 5471",	
			"0555031,WALGREENS 05182",	
			"5635252,SAN GABRIEL MEDICAL PHCY",
			"0121917, WALMART PHARMACY 10-1284",
			"1476806,WALGREENS 06294",
			"0524581,COSTCO PHARMACY 445",
			"0582949,VONS PHARMACY 2111",
			"0510354,NOB HILL PHARMACY 605",
			"5620201,CVS PHARMACY 08881",
			"0520610,OMNICARE OF SOUTHER 48210",
			"0567478,SAFEWAY PHARMACY 1932",
			"0579372,ONE COMMUNITY HEALTH PHA",
			"5615743,WALGREENS 01995",
			"5638955,WALGREENS 12913",
			"5614195,SAFEWAY PHARMACY 1724",};
	
	String todayDate = HelperClass.getTodaysDate();
	Logger logger = LoggerFactory.getLogger(TestScenarios.class);
	public BORFile getPaidScenario1(Session session,
			List<BORDatabase> borDatabaseList, String borFileName) {
		// Paid Scenario 1
		Random random = new Random();
		int payeeIndex = random.nextInt(16);
		String[] payee = pharmacies[payeeIndex].split(",");
		String payeeId = payee[0];
		String payeeName = payee[1];
		
		String todayDate = HelperClass.getTodaysDate();
		BORDatabase borDatabase = borDatabaseList.get(0);
		BORFile borfile = new BORFile();
		String claimID = DatabaseValidation.generateUniqueClaimNumber(session);
		String checkNumber = DatabaseValidation.generateCheckNumber(session);
		String fictClmId = claimID.replace("R", "2");
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(400, 999 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields = HelperClass.getAmountFields(billedAmount);
		String personNumber = "0" + String.valueOf(borDatabase.getMemSfx());
		borfile.setClaimId(claimID);
		borfile.setFileName(borFileName);
		borfile.setVendorName("ARGS");
		borfile.setGroupNumber(borDatabase.getGrpId());
		borfile.setSubgroupId(borDatabase.getSubgrpId());
		borfile.setSubscriberId(borDatabase.getSubId());
		borfile.setPersonNumber(personNumber);
		borfile.setClaimNumber(fictClmId);
		borfile.setClaimVersionNumber("00");
		borfile.setClaimAmount(amountFields.getClaimAmount());
		borfile.setClientPrice(amountFields.getClientPrice());
		borfile.setBscRevenueAmount(amountFields.getBscRevenueAmount());
		borfile.setCheckNumber(checkNumber);
		borfile.setCheckDate(todayDate);
		borfile.setServiceDate(todayDate);
		borfile.setPayeeId(payeeId);
		borfile.setPayeeName(payeeName);
		borfile.setPlanId(borDatabase.getPlanId());
		borfile.setProductId(borDatabase.getPrdId());
		borfile.setProductCategory(borDatabase.getPrdCat());
		borfile.setClassId(borDatabase.getClassId());
		borfile.setProductBusinessCategory(borDatabase.getPrdBusCat());
		borfile.setProductValueCode(borDatabase.getPrdValCD());
		borfile.setLineOfBusinessId(borDatabase.getLobdId());
		borfile.setLegalEntity(borDatabase.getLobType());
		borfile.setBilledAmount(amountFields.getBilledAmount());
		borfile.setAllowedAmount(amountFields.getAllowedAmount());
		borfile.setDeductibleAmount(amountFields.getDeductible());
		borfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		borfile.setCopayAmount(amountFields.getCopay());
		borfile.setDiagnosisCode("9999");
		borfile.setDiagnosisCodeType(" ");
		borfile.setProcedureCode("99199");
		borfile.setHcpcs_id(" ");
		borfile.setClaimTransactionType("P");
		logger.info("Scenario 1 written in BOR File");
		return borfile;
	}

	public CVSFile getCVSPaidScenario1(Session session,	BORDatabase borDatabaseList, String borFileName, String sc1) {
		// Paid Scenario 1   List<BORDatabase> borDatabaseList, String borFileName) {
		Random random = new Random();
		int payeeIndex = random.nextInt(16);
		String[] payee = CVSPayee[payeeIndex].split(",");
		String payeeId = payee[0];
		String payeeName = payee[1];
		//String Versionnum= "10";
				
		String todayDate = HelperClass.getTodaysDate();
		String todayprior3MDate = HelperClass.getTodaysPrior1MDate();
		//BORDatabase borDatabase = borDatabaseList.get(0);
		BORDatabase borDatabase = borDatabaseList;
	CVSFile cvsfile = new CVSFile();
		String claimID = DatabaseValidation.generateLargeUniqueClaimNumber(session);
		String checkNumber = DatabaseValidation.generateCheckNumber(session);
		String fictClmId = claimID.substring(4);
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(400, 999 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields = HelperClass.getAmountFields(billedAmount);
		BigDecimal b= new BigDecimal("1.2");
		String allowedAmount=   new BigDecimal(amountFields.getAllowedAmount()).multiply(b).toString();
		
		BigDecimal calcBilledAmount =billedAmount.multiply(new BigDecimal(1.5)).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		calcBilledAmount.multiply(b).toString();
		
		String personNumber = "0" + String.valueOf(borDatabase.getMemSfx());
		cvsfile.setClaimId(claimID);
		cvsfile.setFileName(borFileName);
		cvsfile.setVendorName("CVS");
		cvsfile.setGroupNumber(borDatabase.getGrpId());
		cvsfile.setSubgroupId(borDatabase.getSubgrpId());
		cvsfile.setSubscriberId(borDatabase.getSubId());
		cvsfile.setPersonNumber(personNumber);
		cvsfile.setClaimNumber(fictClmId);
		cvsfile.setClaimVersionNumber("10");
       
		
		if( sc1.equalsIgnoreCase("Zero")) {
			 cvsfile.setClaimAmount("0.00");
				cvsfile.setClientPrice("0.00");
				cvsfile.setBilledAmount  ( "0.00");
				cvsfile.setAllowedAmount("0.00");
		}
	//	else if (sc1.equalsIgnoreCase("nonZeroNeg")) {
//		 cvsfile.setClaimAmount("-"+amountFields.getClaimAmount());
//			cvsfile.setClientPrice("-"+amountFields.getClaimAmount());
//			cvsfile.setBilledAmount  ( "-"+calcBilledAmount.toString());
//			cvsfile.setAllowedAmount("-"+amountFields.getAllowedAmount());
//			cvsfile.setClaimVersionNumber("11");
	//	}
		else if (sc1.equalsIgnoreCase("nonZeroDiff")) {
			 cvsfile.setClaimAmount(new BigDecimal(amountFields.getClaimAmount()).multiply(b).toString());
				cvsfile.setClientPrice(new BigDecimal(amountFields.getClaimAmount()).multiply(b).toString());
				cvsfile.setBilledAmount(  calcBilledAmount.multiply(b).toString());
				cvsfile.setAllowedAmount(new BigDecimal( amountFields.getAllowedAmount()).multiply(b).toString());
				cvsfile.setClaimVersionNumber("20");
		}
		
		
		
		else {
			
			 cvsfile.setClaimAmount(amountFields.getClaimAmount());
				cvsfile.setClientPrice(amountFields.getClaimAmount());
				cvsfile.setBilledAmount  (  calcBilledAmount.toString());
				cvsfile.setAllowedAmount(amountFields.getAllowedAmount());
			
		}
		
		//cvsfile.setClientPrice(amountFields.getClientPrice());
		cvsfile.setBscRevenueAmount("0.00");
		cvsfile.setCheckNumber("");
		cvsfile.setCheckDate(todayDate);
		cvsfile.setServiceDate(todayprior3MDate);
		cvsfile.setPayeeId(payeeId);
		cvsfile.setPayeeName(payeeName);
		cvsfile.setPlanId(borDatabase.getPlanId());
		cvsfile.setProductId(borDatabase.getPrdId());
		cvsfile.setProductCategory(borDatabase.getPrdCat());
		cvsfile.setClassId(borDatabase.getClassId());
		cvsfile.setProductBusinessCategory(borDatabase.getPrdBusCat());
		cvsfile.setProductValueCode(borDatabase.getPrdValCD());
		cvsfile.setLineOfBusinessId(borDatabase.getLobdId());
		cvsfile.setLegalEntity("");
		cvsfile.setDeductibleAmount(amountFields.getDeductible());
		cvsfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		cvsfile.setCopayAmount(amountFields.getCopay());
		cvsfile.setDiagnosisCode("9999");
		cvsfile.setDiagnosisCodeType("D");
		cvsfile.setProcedureCode("99199");
		cvsfile.setHcpcs_id(" ");
		cvsfile.setClaimTransactionType("P");
		
		logger.info("Paid Claim Scenario written in CVS File");
		return cvsfile;
	}

	//skatta05
	
	
	public List<CVSFile> getCVSPaidScenario2(Session session,	BORDatabase borDatabaseList, String borFileName, String sc1) {
	
		
		Random random = new Random();
		int payeeIndex = random.nextInt(16);
		String[] payee = CVSPayee[payeeIndex].split(",");
		String payeeId = payee[0];
		String payeeName = payee[1];
		//String Versionnum= "10";
		List<CVSFile> cvsfileList = new ArrayList<CVSFile> ();
				
		String todayDate = HelperClass.getTodaysDate();
		String todayprior3MDate = HelperClass.getTodaysPrior1MDate();
		//BORDatabase borDatabase = borDatabaseList.get(0);
		BORDatabase borDatabase = borDatabaseList;
		String borCVSFileName = borFileName;
	CVSFile cvsfile = new CVSFile();
		String claimID = DatabaseValidation.generateLargeUniqueClaimNumber(session);
		String checkNumber = DatabaseValidation.generateCheckNumber(session);
		String fictClmId = claimID.substring(4);
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(400, 999 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields = HelperClass.getAmountFields(billedAmount);
		BigDecimal b= new BigDecimal("-1");
		BigDecimal b1= new BigDecimal("1.2");
		String allowedAmount=   new BigDecimal(amountFields.getAllowedAmount()).multiply(b).toString();
		
		BigDecimal calcBilledAmount =billedAmount.multiply(new BigDecimal(1.5)).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		calcBilledAmount.multiply(b).toString();
		
		String personNumber = "0" + String.valueOf(borDatabase.getMemSfx());
		cvsfile.setClaimId(claimID);
		cvsfile.setFileName(borCVSFileName);
		cvsfile.setVendorName("CVS");
		cvsfile.setGroupNumber(borDatabase.getGrpId());
		cvsfile.setSubgroupId(borDatabase.getSubgrpId());
		cvsfile.setSubscriberId(borDatabase.getSubId());
		cvsfile.setPersonNumber(personNumber);
		cvsfile.setClaimNumber(fictClmId);
		cvsfile.setClaimVersionNumber("10");
		cvsfile.setClaimAmount(amountFields.getClaimAmount());
		cvsfile.setClientPrice(amountFields.getClaimAmount());
		cvsfile.setBilledAmount  (  calcBilledAmount.toString());
		cvsfile.setAllowedAmount(amountFields.getAllowedAmount());
//cvsfile.setClientPrice(amountFields.getClientPrice());
		cvsfile.setBscRevenueAmount("0.00");
		cvsfile.setCheckNumber("");
		cvsfile.setCheckDate(todayDate);
		cvsfile.setServiceDate(todayprior3MDate);
		cvsfile.setPayeeId(payeeId);
		cvsfile.setPayeeName(payeeName);
		cvsfile.setPlanId(borDatabase.getPlanId());
		cvsfile.setProductId(borDatabase.getPrdId());
		cvsfile.setProductCategory(borDatabase.getPrdCat());
		cvsfile.setClassId(borDatabase.getClassId());
		cvsfile.setProductBusinessCategory(borDatabase.getPrdBusCat());
		cvsfile.setProductValueCode(borDatabase.getPrdValCD());
		cvsfile.setLineOfBusinessId(borDatabase.getLobdId());
		cvsfile.setLegalEntity("");
		cvsfile.setDeductibleAmount(amountFields.getDeductible());
		cvsfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		cvsfile.setCopayAmount(amountFields.getCopay());
		cvsfile.setDiagnosisCode("9999");
		cvsfile.setDiagnosisCodeType("D");
		cvsfile.setProcedureCode("99199");
		cvsfile.setHcpcs_id(" ");
		cvsfile.setClaimTransactionType("P");
		cvsfileList.add(0, cvsfile);
		
		if (sc1.equalsIgnoreCase("SC3") || sc1.equalsIgnoreCase("SC2") ){
		
		CVSFile cvsfile1 = new CVSFile();
		cvsfile1 = setDefaultValues( cvsfile, cvsfile1);
		
		cvsfile1.setClaimVersionNumber("11");
		 cvsfile1.setClaimAmount( new BigDecimal(cvsfile1.getClaimAmount()).multiply(b).toString());
		 cvsfile1.setClientPrice( new BigDecimal(cvsfile1.getClientPrice()).multiply(b).toString());
		 cvsfile1.setBilledAmount( new BigDecimal(cvsfile1.getBilledAmount()).multiply(b).toString());
		 cvsfile1.setAllowedAmount( new BigDecimal(cvsfile1.getAllowedAmount()).multiply(b).toString());
		cvsfileList.add(1, cvsfile1);
		}
	
		if (sc1.equalsIgnoreCase("SC3")){
	
		CVSFile cvsfile2 = new CVSFile();
		cvsfile2 = setDefaultValues( cvsfile, cvsfile2);
		cvsfile2.setClaimVersionNumber("20");
		 cvsfile2.setClaimAmount( new BigDecimal(cvsfile.getClaimAmount()).multiply(b1).toString());
		 cvsfile2.setClientPrice( new BigDecimal(cvsfile.getClientPrice()).multiply(b1).toString());
		 cvsfile2.setBilledAmount( new BigDecimal(cvsfile.getBilledAmount()).multiply(b1).toString());
		 cvsfile2.setAllowedAmount( new BigDecimal(cvsfile.getAllowedAmount()).multiply(b1).toString());
		
		cvsfileList.add(2, cvsfile2);
		
		}
	
		logger.info("Paid Claim Scenario written in CVS File");
		
		return cvsfileList;
		
	}
	
	
	
private CVSFile setDefaultValues(CVSFile cvsfile, CVSFile cvsfilecopy) {
		// TODO Auto-generated method stub
	cvsfilecopy.setClaimId(cvsfile.getClaimId());
	cvsfilecopy.setFileName(cvsfile.getFileName());
	cvsfilecopy.setVendorName(cvsfile.getVendorName());
	cvsfilecopy.setGroupNumber(cvsfile.getGroupNumber());
	cvsfilecopy.setSubgroupId(cvsfile.getSubgroupId());
	cvsfilecopy.setSubscriberId(cvsfile.getSubscriberId());
	cvsfilecopy.setPersonNumber(cvsfile.getPersonNumber());
	cvsfilecopy.setClaimNumber(cvsfile.getClaimNumber());
	 	cvsfilecopy.setClaimVersionNumber("10");
    cvsfilecopy.setClaimAmount(cvsfile.getClaimAmount());
	cvsfilecopy.setClientPrice(cvsfile.getClientPrice());
	cvsfilecopy.setBilledAmount  ( cvsfile.getBilledAmount());
	cvsfilecopy.setAllowedAmount(cvsfile.getAllowedAmount());
	cvsfilecopy.setBscRevenueAmount(cvsfile.getBscRevenueAmount());
	cvsfilecopy.setCheckNumber("");
	cvsfilecopy.setCheckDate(cvsfile.getCheckDate());
	cvsfilecopy.setServiceDate(cvsfile.getServiceDate());
	cvsfilecopy.setPayeeId(cvsfile.getPayeeId());
	cvsfilecopy.setPayeeName(cvsfile.getPayeeName());
	cvsfilecopy.setPlanId(cvsfile.getPlanId());
	cvsfilecopy.setProductId(cvsfile.getProductId());
	cvsfilecopy.setProductCategory(cvsfile.getProductCategory());
	cvsfilecopy.setClassId(cvsfile.getClassId());
	cvsfilecopy.setProductBusinessCategory(cvsfile.getProductBusinessCategory());
	cvsfilecopy.setProductValueCode(cvsfile.getProductValueCode());
	cvsfilecopy.setLineOfBusinessId(cvsfile.getLineOfBusinessId());
	cvsfilecopy.setLegalEntity("");
	cvsfilecopy.setDeductibleAmount(cvsfile.getDeductibleAmount());
	cvsfilecopy.setCoinsuranceAmount(cvsfile.getCoinsuranceAmount());
	cvsfilecopy.setCopayAmount(cvsfile.getCopayAmount());
	cvsfilecopy.setDiagnosisCode("9999");
	cvsfilecopy.setDiagnosisCodeType("D");
	cvsfilecopy.setProcedureCode("99199");
	cvsfilecopy.setHcpcs_id(" ");
	cvsfilecopy.setClaimTransactionType("P");
	
	
		return cvsfilecopy;
	}

public CVSFile getCVSAdjustmentScenario1(Session session,BorAdjustmentDB adjustmentRecord,String borFileName, String SCtype, String newperc){
		
		//BorAdjustmentDB adjustmentRecord = borCSVAdjustmentDbList;
		int claim_version_num = Integer.parseInt(adjustmentRecord.getCLM_VER_NBR().trim());
		BigDecimal b1 = new BigDecimal(newperc); 
		BigDecimal neg = new BigDecimal("-1"); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date checkDate = adjustmentRecord.getCHK_DT();
		String todayDateFake = HelperClass.getTodaysDateFake(checkDate);
		Date svcDate = adjustmentRecord.getSVC_DT();
		CVSFile borCVSAdjustmentFile = new CVSFile();
		borCVSAdjustmentFile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borCVSAdjustmentFile.setFileName(borFileName);
		borCVSAdjustmentFile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borCVSAdjustmentFile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borCVSAdjustmentFile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borCVSAdjustmentFile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borCVSAdjustmentFile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borCVSAdjustmentFile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		
		if( SCtype.equalsIgnoreCase("neg"))
		{
			
		borCVSAdjustmentFile.setClaimVersionNumber(Integer.toString(claim_version_num + 1));
		borCVSAdjustmentFile.setClaimAmount(String.valueOf(adjustmentRecord.getCLM_AMT().multiply(neg).setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setClientPrice(String.valueOf(adjustmentRecord.getCLI_PRC_AMT().multiply(neg).setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setBscRevenueAmount(String.valueOf(adjustmentRecord.getBSC_RVNU_AMT().multiply(neg).setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setBilledAmount(String.valueOf(adjustmentRecord.getBIL_AMT().multiply(neg).setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setAllowedAmount(String.valueOf(adjustmentRecord.getALLOW_AMT().multiply(neg).setScale(2, BigDecimal.ROUND_HALF_UP)));
		
		}
		else 
		{
		borCVSAdjustmentFile.setClaimVersionNumber(Integer.toString(claim_version_num + 10));
		borCVSAdjustmentFile.setClaimAmount(String.valueOf(adjustmentRecord.getCLM_AMT().multiply(b1).setScale(2, BigDecimal.ROUND_HALF_UP) ));
		borCVSAdjustmentFile.setClientPrice(String.valueOf(adjustmentRecord.getCLI_PRC_AMT().multiply(b1).setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setBscRevenueAmount(String.valueOf(adjustmentRecord.getBSC_RVNU_AMT().multiply(b1).setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setBilledAmount(String.valueOf(adjustmentRecord.getBIL_AMT().multiply(b1).setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setAllowedAmount(String.valueOf(adjustmentRecord.getALLOW_AMT().multiply(b1).setScale(2, BigDecimal.ROUND_HALF_UP)));
		}
		
		
		borCVSAdjustmentFile.setCheckNumber(adjustmentRecord.getCHK_NBR());
		borCVSAdjustmentFile.setCheckDate(todayDateFake);
		borCVSAdjustmentFile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
		borCVSAdjustmentFile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borCVSAdjustmentFile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borCVSAdjustmentFile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borCVSAdjustmentFile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borCVSAdjustmentFile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borCVSAdjustmentFile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borCVSAdjustmentFile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borCVSAdjustmentFile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borCVSAdjustmentFile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borCVSAdjustmentFile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borCVSAdjustmentFile.setDeductibleAmount("-"+String.valueOf(adjustmentRecord.getDED_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setCoinsuranceAmount("-"+String.valueOf(adjustmentRecord.getCOINS_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setCopayAmount("-"+String.valueOf(adjustmentRecord.getCOPAY_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borCVSAdjustmentFile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borCVSAdjustmentFile.setDiagnosisCodeType(" ");
		borCVSAdjustmentFile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borCVSAdjustmentFile.setHcpcs_id(" ");
		borCVSAdjustmentFile.setClaimTransactionType("A");
		//borCVSFileList.add(borCVSAdjustmentFile);
		logger.info("Adj Scenario  written in CVS  File");

		
		return borCVSAdjustmentFile;
	}
	
public CVSFile getCVSDuplicateScenario(Session session,BorAdjustmentDB adjustmentRecord,String borFileName){
	
	//BorAdjustmentDB adjustmentRecord = borCSVAdjustmentDbList;
	//int claim_version_num = Integer.parseInt(adjustmentRecord.getCLM_VER_NBR().trim());
	//BigDecimal b1 = new BigDecimal(newperc); 
	BigDecimal neg = new BigDecimal("-1"); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
	Date checkDate = adjustmentRecord.getCHK_DT();
	String todayDateFake = HelperClass.getTodaysDateFake(checkDate);
	Date svcDate = adjustmentRecord.getSVC_DT();
	CVSFile borCVSAdjustmentFile = new CVSFile();
	borCVSAdjustmentFile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
	borCVSAdjustmentFile.setFileName(borFileName);
	borCVSAdjustmentFile.setVendorName(adjustmentRecord.getVEND_NM().trim());
	borCVSAdjustmentFile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
	borCVSAdjustmentFile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
	borCVSAdjustmentFile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
	borCVSAdjustmentFile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
	borCVSAdjustmentFile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
	
	borCVSAdjustmentFile.setClaimVersionNumber(adjustmentRecord.getCLM_VER_NBR().trim());
	borCVSAdjustmentFile.setClaimAmount(String.valueOf(adjustmentRecord.getCLM_AMT().setScale(2, BigDecimal.ROUND_HALF_UP) ));
	borCVSAdjustmentFile.setClientPrice(String.valueOf(adjustmentRecord.getCLI_PRC_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
	borCVSAdjustmentFile.setBscRevenueAmount(String.valueOf(adjustmentRecord.getBSC_RVNU_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
	borCVSAdjustmentFile.setBilledAmount(String.valueOf(adjustmentRecord.getBIL_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
	borCVSAdjustmentFile.setAllowedAmount(String.valueOf(adjustmentRecord.getALLOW_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));


	
	borCVSAdjustmentFile.setCheckNumber(adjustmentRecord.getCHK_NBR());
	borCVSAdjustmentFile.setCheckDate(todayDateFake);
	borCVSAdjustmentFile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
	borCVSAdjustmentFile.setPayeeId(adjustmentRecord.getPAYE_ID());
	borCVSAdjustmentFile.setPayeeName(adjustmentRecord.getPAYE_NM());
	borCVSAdjustmentFile.setPlanId(adjustmentRecord.getPLN_ID().trim());
	borCVSAdjustmentFile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
	borCVSAdjustmentFile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
	borCVSAdjustmentFile.setClassId(adjustmentRecord.getCLS_ID().trim());
	borCVSAdjustmentFile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
	borCVSAdjustmentFile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
	borCVSAdjustmentFile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
	borCVSAdjustmentFile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
	borCVSAdjustmentFile.setDeductibleAmount(String.valueOf(adjustmentRecord.getDED_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
	borCVSAdjustmentFile.setCoinsuranceAmount(String.valueOf(adjustmentRecord.getCOINS_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
	borCVSAdjustmentFile.setCopayAmount(String.valueOf(adjustmentRecord.getCOPAY_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
	borCVSAdjustmentFile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
	borCVSAdjustmentFile.setDiagnosisCodeType(" ");
	borCVSAdjustmentFile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
	borCVSAdjustmentFile.setHcpcs_id(" ");
	borCVSAdjustmentFile.setClaimTransactionType("P");
	//borCVSFileList.add(borCVSAdjustmentFile);
	logger.info("Duplicate Scenario  written in CVS File");

	
	return borCVSAdjustmentFile;
}
	
	
	
	
	
	
	
	
	public BORFile getPaidScenario2(Session session,
		List<BORDatabase> borDatabaseList, String borFileName) {
		
		Random random = new Random();
		int payeeIndex = random.nextInt(16);
		String[] payee = pharmacies[payeeIndex].split(",");
		String payeeId = payee[0];
		String payeeName = payee[1];
		String todayDate = HelperClass.getTodaysDate();

		BORDatabase borDatabase1 = borDatabaseList.get(1);
		BORFile borfile1 = new BORFile();
		String claimID1 = DatabaseValidation.generateUniqueClaimNumber(session);
		String checkNumber1 = DatabaseValidation.generateCheckNumber(session);
		String fictClmId1 = claimID1.replace("R", "2");
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(400, 999 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields1 = HelperClass.getAmountFields(billedAmount);
		String personNumber1 = "0" + String.valueOf(borDatabase1.getMemSfx());
		borfile1.setClaimId(claimID1);
		borfile1.setFileName(borFileName);
		borfile1.setVendorName("ARGS");
		borfile1.setGroupNumber(borDatabase1.getGrpId());
		borfile1.setSubgroupId(borDatabase1.getSubgrpId());
		borfile1.setSubscriberId(borDatabase1.getSubId());
		borfile1.setPersonNumber(personNumber1);
		borfile1.setClaimNumber(fictClmId1);
		borfile1.setClaimVersionNumber("00");
		borfile1.setClaimAmount(amountFields1.getClientPrice());
		borfile1.setClientPrice(amountFields1.getClientPrice());
		borfile1.setBscRevenueAmount(String.valueOf(new BigDecimal("00")
				.setScale(2, BigDecimal.ROUND_HALF_UP)));
		borfile1.setCheckNumber(checkNumber1);
		borfile1.setCheckDate(todayDate);
		borfile1.setServiceDate(todayDate);
		borfile1.setPayeeId(payeeId);
		borfile1.setPayeeName(payeeName);
		borfile1.setPlanId(borDatabase1.getPlanId());
		borfile1.setProductId(borDatabase1.getPrdId());
		borfile1.setProductCategory(borDatabase1.getPrdCat());
		borfile1.setClassId(borDatabase1.getClassId());
		borfile1.setProductBusinessCategory(borDatabase1.getPrdBusCat());
		borfile1.setProductValueCode(borDatabase1.getPrdValCD());
		borfile1.setLineOfBusinessId(borDatabase1.getLobdId());
		borfile1.setLegalEntity(borDatabase1.getLobType());
		borfile1.setBilledAmount(amountFields1.getBilledAmount());
		borfile1.setAllowedAmount(amountFields1.getAllowedAmount());
		borfile1.setDeductibleAmount(amountFields1.getDeductible());
		borfile1.setCoinsuranceAmount(amountFields1.getCoInsurance());
		borfile1.setCopayAmount(amountFields1.getCopay());
		borfile1.setDiagnosisCode("9999");
		borfile1.setDiagnosisCodeType(" ");
		borfile1.setProcedureCode("99199");
		borfile1.setHcpcs_id(" ");
		borfile1.setClaimTransactionType("P");
		logger.info("Scenario 2 written in BOR File");
		return borfile1;
	}
	
	public BORFile getPaidScenario3(Session session,
			List<BORDatabase> borDatabaseList, String borFileName){
		Random random = new Random();
		int payeeIndex = random.nextInt(16);
		String[] payee = pharmacies[payeeIndex].split(",");
		String payeeId = payee[0];
		String payeeName = payee[1];
		String todayDate = HelperClass.getTodaysDate();

		BORDatabase borDatabase1 = borDatabaseList.get(2);
		BORFile borfile1 = new BORFile();
		String claimID1 = DatabaseValidation.generateUniqueClaimNumber(session);
		String checkNumber1 = DatabaseValidation.generateCheckNumber(session);
		String fictClmId1 = claimID1.replace("R", "2");
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(400, 999 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields1 = HelperClass.getAmountFields(billedAmount);
		String personNumber1 = "0" + String.valueOf(borDatabase1.getMemSfx());
		borfile1.setClaimId(claimID1);
		borfile1.setFileName(borFileName);
		borfile1.setVendorName("ARGS");
		borfile1.setGroupNumber(borDatabase1.getGrpId());
		borfile1.setSubgroupId(borDatabase1.getSubgrpId());
		borfile1.setSubscriberId(borDatabase1.getSubId());
		borfile1.setPersonNumber(personNumber1);
		borfile1.setClaimNumber(fictClmId1);
		borfile1.setClaimVersionNumber("00");
		borfile1.setClaimAmount(String.valueOf(new BigDecimal("00")
		.setScale(2, BigDecimal.ROUND_HALF_UP)));
		borfile1.setClientPrice(amountFields1.getClientPrice());
		borfile1.setBscRevenueAmount(amountFields1.getClientPrice());
		borfile1.setCheckNumber(checkNumber1);
		borfile1.setCheckDate(todayDate);
		borfile1.setServiceDate(todayDate);
		borfile1.setPayeeId(payeeId);
		borfile1.setPayeeName(payeeName);
		borfile1.setPlanId(borDatabase1.getPlanId());
		borfile1.setProductId(borDatabase1.getPrdId());
		borfile1.setProductCategory(borDatabase1.getPrdCat());
		borfile1.setClassId(borDatabase1.getClassId());
		borfile1.setProductBusinessCategory(borDatabase1.getPrdBusCat());
		borfile1.setProductValueCode(borDatabase1.getPrdValCD());
		borfile1.setLineOfBusinessId(borDatabase1.getLobdId());
		borfile1.setLegalEntity(borDatabase1.getLobType());
		borfile1.setBilledAmount(amountFields1.getBilledAmount());
		borfile1.setAllowedAmount(amountFields1.getAllowedAmount());
		borfile1.setDeductibleAmount(amountFields1.getDeductible());
		borfile1.setCoinsuranceAmount(amountFields1.getCoInsurance());
		borfile1.setCopayAmount(amountFields1.getCopay());
		borfile1.setDiagnosisCode("9999");
		borfile1.setDiagnosisCodeType(" ");
		borfile1.setProcedureCode("99199");
		borfile1.setHcpcs_id(" ");
		borfile1.setClaimTransactionType("P");
		logger.info("Scenario 3 written in BOR File");
		return borfile1;
	}
	
	public BORFile getPaidScenario4(Session session,
			List<BORDatabase> borDatabaseList, String borFileName) {
		// Paid Scenario 1
		Random random = new Random();
		int payeeIndex = random.nextInt(16);
		String[] payee = pharmacies[payeeIndex].split(",");
		String payeeId = payee[0];
		String payeeName = payee[1];
		String lastYearsDate = HelperClass.getLastYearsDate();
		BORDatabase borDatabase = borDatabaseList.get(3);
		BORFile borfile = new BORFile();
		String claimID = DatabaseValidation.generateUniqueClaimNumber(session);
		String checkNumber = DatabaseValidation.generateCheckNumber(session);
		String fictClmId = claimID.replace("R", "2");
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(400, 999 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields = HelperClass.getAmountFields(billedAmount);
		String personNumber = "0" + String.valueOf(borDatabase.getMemSfx());
		borfile.setClaimId(claimID);
		borfile.setFileName(borFileName);
		borfile.setVendorName("ARGS");
		borfile.setGroupNumber(borDatabase.getGrpId());
		borfile.setSubgroupId(borDatabase.getSubgrpId());
		borfile.setSubscriberId(borDatabase.getSubId());
		borfile.setPersonNumber(personNumber);
		borfile.setClaimNumber(fictClmId);
		borfile.setClaimVersionNumber("00");
		borfile.setClaimAmount(amountFields.getClaimAmount());
		borfile.setClientPrice(amountFields.getClientPrice());
		borfile.setBscRevenueAmount(amountFields.getBscRevenueAmount());
		borfile.setCheckNumber(checkNumber);
		borfile.setCheckDate(lastYearsDate);
		borfile.setServiceDate(lastYearsDate);
		borfile.setPayeeId(payeeId);
		borfile.setPayeeName(payeeName);
		borfile.setPlanId(borDatabase.getPlanId());
		borfile.setProductId(borDatabase.getPrdId());
		borfile.setProductCategory(borDatabase.getPrdCat());
		borfile.setClassId(borDatabase.getClassId());
		borfile.setProductBusinessCategory(borDatabase.getPrdBusCat());
		borfile.setProductValueCode(borDatabase.getPrdValCD());
		borfile.setLineOfBusinessId(borDatabase.getLobdId());
		borfile.setLegalEntity(borDatabase.getLobType());
		borfile.setBilledAmount(amountFields.getBilledAmount());
		borfile.setAllowedAmount(amountFields.getAllowedAmount());
		borfile.setDeductibleAmount(amountFields.getDeductible());
		borfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		borfile.setCopayAmount(amountFields.getCopay());
		borfile.setDiagnosisCode("9999");
		borfile.setDiagnosisCodeType(" ");
		borfile.setProcedureCode("99199");
		borfile.setHcpcs_id(" ");
		borfile.setClaimTransactionType("P");
		logger.info("Scenario 4 written in BOR File");
		return borfile;
	}

	public BORFile getPaidScenario5(Session session,
			List<BORDatabase> borDatabaseList, String borFileName) {
		// Paid Scenario 1
		Random random = new Random();
		int payeeIndex = random.nextInt(16);
		String[] payee = pharmacies[payeeIndex].split(",");
		String payeeId = payee[0];
		String payeeName = payee[1];
		
		String todayDate = HelperClass.getTodaysDate();
		BORDatabase borDatabase = borDatabaseList.get(4);
		BORFile borfile = new BORFile();
		String claimID = DatabaseValidation.generateUniqueClaimNumber(session);
		String checkNumber = DatabaseValidation.generateCheckNumber(session);
		String fictClmId = claimID.replace("R", "2");
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(400, 999 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields = HelperClass.getAmountFields(billedAmount);
		String personNumber = "0" + String.valueOf(borDatabase.getMemSfx());
		borfile.setClaimId(claimID);
		borfile.setFileName(borFileName);
		borfile.setVendorName("ARGS");
		borfile.setGroupNumber(borDatabase.getGrpId());
		borfile.setSubgroupId(borDatabase.getSubgrpId());
		borfile.setSubscriberId(borDatabase.getSubId());
		borfile.setPersonNumber(personNumber);
		borfile.setClaimNumber(fictClmId);
		borfile.setClaimVersionNumber("00");
		borfile.setClaimAmount(amountFields.getClaimAmount());
		borfile.setClientPrice(amountFields.getClientPrice());
		borfile.setBscRevenueAmount(amountFields.getBscRevenueAmount());
		borfile.setCheckNumber(checkNumber);
		borfile.setCheckDate(todayDate);
		borfile.setServiceDate(todayDate);
		borfile.setPayeeId(payeeId);
		borfile.setPayeeName(payeeName);
		borfile.setPlanId(borDatabase.getPlanId());
		borfile.setProductId(borDatabase.getPrdId());
		borfile.setProductCategory(borDatabase.getPrdCat());
		borfile.setClassId(borDatabase.getClassId());
		borfile.setProductBusinessCategory(borDatabase.getPrdBusCat());
		borfile.setProductValueCode(borDatabase.getPrdValCD());
		borfile.setLineOfBusinessId(borDatabase.getLobdId());
		borfile.setLegalEntity(borDatabase.getLobType());
		borfile.setBilledAmount(amountFields.getBilledAmount());
		borfile.setAllowedAmount(amountFields.getAllowedAmount());
		borfile.setDeductibleAmount(amountFields.getDeductible());
		borfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		borfile.setCopayAmount(amountFields.getCopay());
		borfile.setDiagnosisCode("9999");
		borfile.setDiagnosisCodeType(" ");
		borfile.setProcedureCode("99199");
		borfile.setHcpcs_id(" ");
		borfile.setClaimTransactionType("P");
		logger.info("Scenario 5 written in BOR File");
		return borfile;
	}

	public List<BORFile> getAdjustmentScenario1(Session session,List<BorAdjustmentDB> borAdjustmentDbList,String borFileName){
		
		BorAdjustmentDB adjustmentRecord = borAdjustmentDbList.get(0);
//		adjustmentRecord.getCHK_DT();
		List<BORFile> borFileList = new ArrayList<BORFile>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date checkDate = adjustmentRecord.getCHK_DT();
		String todayDateFake = HelperClass.getTodaysDateFake(checkDate);
		Date svcDate = adjustmentRecord.getSVC_DT();
		BORFile borAdjustmentFile = new BORFile();
		borAdjustmentFile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borAdjustmentFile.setFileName(borFileName);
		borAdjustmentFile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borAdjustmentFile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borAdjustmentFile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borAdjustmentFile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borAdjustmentFile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borAdjustmentFile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borAdjustmentFile.setClaimVersionNumber("01");
		borAdjustmentFile.setClaimAmount("-"+String.valueOf(adjustmentRecord.getCLM_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setClientPrice("-"+String.valueOf(adjustmentRecord.getCLI_PRC_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setBscRevenueAmount("-"+String.valueOf(adjustmentRecord.getBSC_RVNU_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCheckNumber(adjustmentRecord.getCHK_NBR());
		borAdjustmentFile.setCheckDate(todayDateFake);
		borAdjustmentFile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
		borAdjustmentFile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borAdjustmentFile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borAdjustmentFile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borAdjustmentFile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borAdjustmentFile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borAdjustmentFile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borAdjustmentFile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borAdjustmentFile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borAdjustmentFile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borAdjustmentFile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borAdjustmentFile.setBilledAmount("-"+String.valueOf(adjustmentRecord.getBIL_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setAllowedAmount("-"+String.valueOf(adjustmentRecord.getALLOW_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setDeductibleAmount("-"+String.valueOf(adjustmentRecord.getDED_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCoinsuranceAmount("-"+String.valueOf(adjustmentRecord.getCOINS_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCopayAmount("-"+String.valueOf(adjustmentRecord.getCOPAY_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borAdjustmentFile.setDiagnosisCodeType(" ");
		borAdjustmentFile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borAdjustmentFile.setHcpcs_id(" ");
		borAdjustmentFile.setClaimTransactionType("A");
		borFileList.add(borAdjustmentFile);
		logger.info("Adj Scenario 1 written in BOR File - A");
//		logger.info(borAdjustmentFile);
		BORFile borfile = new BORFile();
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(10, 29 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		AmountFields amountFields = HelperClass.getAmountFields(billedAmount.add(adjustmentRecord.getBIL_AMT()));
		String checkNumber11 = DatabaseValidation.generateCheckNumber(session);
		borfile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borfile.setFileName(borFileName);
		borfile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borfile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borfile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borfile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borfile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borfile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borfile.setClaimVersionNumber("00");
		borfile.setClaimAmount(amountFields.getClaimAmount());
		borfile.setClientPrice(amountFields.getClientPrice());
		borfile.setBscRevenueAmount(amountFields.getBscRevenueAmount());
		borfile.setCheckNumber(checkNumber11);
		borfile.setCheckDate(todayDateFake);
		borfile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
		borfile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borfile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borfile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borfile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borfile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borfile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borfile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borfile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borfile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borfile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borfile.setBilledAmount(amountFields.getBilledAmount());
		borfile.setAllowedAmount(amountFields.getAllowedAmount());
		borfile.setDeductibleAmount(amountFields.getDeductible());
		borfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		borfile.setCopayAmount(amountFields.getCopay());
		borfile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borfile.setDiagnosisCodeType(" ");
		borfile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borfile.setHcpcs_id(" ");
		borfile.setClaimTransactionType("P");
//		logger.info(borfile);
		logger.info("Adj Scenario 1 written in BOR File - P");
		borFileList.add(borfile);
		
		return borFileList;
	}
	
	public List<BORFile> getAdjustmentScenario2(Session session,List<BorAdjustmentDB> borAdjustmentDbList,String borFileName){
		BorAdjustmentDB adjustmentRecord = borAdjustmentDbList.get(1);
		List<BORFile> borFileList = new ArrayList<BORFile>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date checkDate = adjustmentRecord.getCHK_DT();
		String todayDateFake = HelperClass.getTodaysDateFake(checkDate);
		Date svcDate = adjustmentRecord.getSVC_DT();
		BORFile borAdjustmentFile = new BORFile();
		borAdjustmentFile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borAdjustmentFile.setFileName(borFileName);
		borAdjustmentFile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borAdjustmentFile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borAdjustmentFile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borAdjustmentFile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borAdjustmentFile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borAdjustmentFile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borAdjustmentFile.setClaimVersionNumber("01");
		borAdjustmentFile.setClaimAmount("-"+String.valueOf(adjustmentRecord.getCLM_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setClientPrice("-"+String.valueOf(adjustmentRecord.getCLI_PRC_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setBscRevenueAmount("-"+String.valueOf(adjustmentRecord.getBSC_RVNU_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCheckNumber(adjustmentRecord.getCHK_NBR());
		borAdjustmentFile.setCheckDate(todayDateFake);
		borAdjustmentFile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
		borAdjustmentFile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borAdjustmentFile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borAdjustmentFile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borAdjustmentFile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borAdjustmentFile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borAdjustmentFile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borAdjustmentFile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borAdjustmentFile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borAdjustmentFile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borAdjustmentFile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borAdjustmentFile.setBilledAmount("-"+String.valueOf(adjustmentRecord.getBIL_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setAllowedAmount("-"+String.valueOf(adjustmentRecord.getALLOW_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setDeductibleAmount("-"+String.valueOf(adjustmentRecord.getDED_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCoinsuranceAmount("-"+String.valueOf(adjustmentRecord.getCOINS_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCopayAmount("-"+String.valueOf(adjustmentRecord.getCOPAY_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borAdjustmentFile.setDiagnosisCodeType(" ");
		borAdjustmentFile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borAdjustmentFile.setHcpcs_id(" ");
		borAdjustmentFile.setClaimTransactionType("A");
		borFileList.add(borAdjustmentFile);
		logger.info("Adj Scenario 2 written in BOR File - A");
//		logger.info(borAdjustmentFile);
		BORFile borfile = new BORFile();
		BigDecimal billedAmount =(new BigDecimal(ThreadLocalRandom.current().nextInt(10, 29 + 1))).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal amountPassed;
		if(billedAmount.compareTo(adjustmentRecord.getBIL_AMT())==1){
		 amountPassed = billedAmount.subtract(adjustmentRecord.getBIL_AMT());
		}else{
			amountPassed = adjustmentRecord.getBIL_AMT().subtract(billedAmount);
		}
		AmountFields amountFields = HelperClass.getAmountFields(amountPassed);
		String checkNumber11 = DatabaseValidation.generateCheckNumber(session);
		borfile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borfile.setFileName(borFileName);
		borfile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borfile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borfile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borfile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borfile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borfile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borfile.setClaimVersionNumber("00");
		borfile.setClaimAmount(amountFields.getClaimAmount());
		borfile.setClientPrice(amountFields.getClientPrice());
		borfile.setBscRevenueAmount(amountFields.getBscRevenueAmount());
		borfile.setCheckNumber(checkNumber11);
		borfile.setCheckDate(todayDateFake);
		borfile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
		borfile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borfile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borfile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borfile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borfile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borfile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borfile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borfile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borfile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borfile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borfile.setBilledAmount(amountFields.getBilledAmount());
		borfile.setAllowedAmount(amountFields.getAllowedAmount());
		borfile.setDeductibleAmount(amountFields.getDeductible());
		borfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		borfile.setCopayAmount(amountFields.getCopay());
		borfile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borfile.setDiagnosisCodeType(" ");
		borfile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borfile.setHcpcs_id(" ");
		borfile.setClaimTransactionType("P");
//		logger.info(borfile);
		logger.info("Adj Scenario 2 written in BOR File - P");
		borFileList.add(borfile);
		
		
		return borFileList;
	}
	
	public List<BORFile> getAdjustmentScenario3(Session session,List<BorAdjustmentDB> borAdjustmentDbList,String borFileName){
		BorAdjustmentDB adjustmentRecord = borAdjustmentDbList.get(2);
		List<BORFile> borFileList = new ArrayList<BORFile>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date checkDate = adjustmentRecord.getCHK_DT();
		String todayDateFake = HelperClass.getTodaysDateFake(checkDate);
		BigDecimal billedAmount = new BigDecimal(ThreadLocalRandom.current().nextInt(10	, 29 + 1)).setScale(2, BigDecimal.ROUND_HALF_UP);
		HelperClass  helperClass= new HelperClass();
		AmountFields amountFields = helperClass.getAdjustmentAmountFieldsScenario3(billedAmount, adjustmentRecord);
		Date svcDate = adjustmentRecord.getSVC_DT();
		BORFile borAdjustmentFile = new BORFile();
		borAdjustmentFile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borAdjustmentFile.setFileName(borFileName);
		borAdjustmentFile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borAdjustmentFile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borAdjustmentFile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borAdjustmentFile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borAdjustmentFile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borAdjustmentFile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borAdjustmentFile.setClaimVersionNumber("01");
		borAdjustmentFile.setClaimAmount("-"+amountFields.getClaimAmount());
		borAdjustmentFile.setClientPrice("-"+amountFields.getClientPrice());
		borAdjustmentFile.setBscRevenueAmount("-"+amountFields.getBscRevenueAmount());
		borAdjustmentFile.setCheckNumber(adjustmentRecord.getCHK_NBR());
		borAdjustmentFile.setCheckDate(todayDateFake);
		borAdjustmentFile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
		borAdjustmentFile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borAdjustmentFile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borAdjustmentFile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borAdjustmentFile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borAdjustmentFile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borAdjustmentFile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borAdjustmentFile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borAdjustmentFile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borAdjustmentFile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borAdjustmentFile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borAdjustmentFile.setBilledAmount("-"+amountFields.getBilledAmount());
		borAdjustmentFile.setAllowedAmount("-"+amountFields.getAllowedAmount());
		borAdjustmentFile.setDeductibleAmount("-"+amountFields.getDeductible());
		borAdjustmentFile.setCoinsuranceAmount("-"+amountFields.getCoInsurance());
		borAdjustmentFile.setCopayAmount("-"+amountFields.getCopay());
		borAdjustmentFile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borAdjustmentFile.setDiagnosisCodeType(" ");
		borAdjustmentFile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borAdjustmentFile.setHcpcs_id(" ");
		borAdjustmentFile.setClaimTransactionType("A");
		borFileList.add(borAdjustmentFile);
//		logger.info(borAdjustmentFile);
		logger.info("Adj Scenario 3 written in BOR File - A");
		return borFileList;
	}
	
	public List<BORFile> getAdjustmentScenario4(Session session,List<BorAdjustmentDB> borAdjustmentDbList,String borFileName){
		BorAdjustmentDB adjustmentRecord = borAdjustmentDbList.get(3);
		List<BORFile> borFileList = new ArrayList<BORFile>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		BigDecimal billedAmount = new BigDecimal(ThreadLocalRandom.current().nextInt(10	, 29 + 1)).setScale(2, BigDecimal.ROUND_HALF_UP);
		Date checkDate = adjustmentRecord.getCHK_DT();
		String todayDateFake = HelperClass.getTodaysDateFake(checkDate);
		Date svcDate = adjustmentRecord.getSVC_DT();
		billedAmount.multiply(new BigDecimal(-1));
		BORFile borfile = new BORFile();
		AmountFields amountFields = HelperClass.getAdjustmentAmountFields(billedAmount);
//		String checkNumber11 = DatabaseValidation.generateCheckNumber(session);
		borfile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borfile.setFileName(borFileName);
		borfile.setVendorName("ARGS");
		borfile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borfile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borfile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borfile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borfile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borfile.setClaimVersionNumber("00");
		borfile.setClaimAmount(amountFields.getClaimAmount());
		borfile.setClientPrice(amountFields.getClientPrice());
		borfile.setBscRevenueAmount(amountFields.getBscRevenueAmount());
		borfile.setCheckNumber(adjustmentRecord.getCHK_NBR());
		borfile.setCheckDate(todayDateFake);
		borfile.setServiceDate(dateFormat.format(svcDate).toUpperCase().trim());
		borfile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borfile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borfile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borfile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borfile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borfile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borfile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borfile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borfile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borfile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borfile.setBilledAmount(amountFields.getBilledAmount());
		borfile.setAllowedAmount(amountFields.getAllowedAmount());
		borfile.setDeductibleAmount(amountFields.getDeductible());
		borfile.setCoinsuranceAmount(amountFields.getCoInsurance());
		borfile.setCopayAmount(amountFields.getCopay());
		borfile.setDiagnosisCode("9999");
		borfile.setDiagnosisCodeType(" ");
		borfile.setProcedureCode("99199");
		borfile.setHcpcs_id(" ");
		borfile.setClaimTransactionType("P");
//		logger.info(borfile);
		logger.info("Adj Scenario 4 written in BOR File - A");
		borFileList.add(borfile);
		
		return borFileList;
	}
	
	public List<BORFile> getAdjustmentScenario5(Session session,List<BorAdjustmentDB> borAdjustmentDbList,String borFileName){
		BorAdjustmentDB adjustmentRecord = borAdjustmentDbList.get(4);
		List<BORFile> borFileList = new ArrayList<BORFile>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date checkDate = adjustmentRecord.getCHK_DT();
		String todayDateFake = HelperClass.getTodaysDateFake(checkDate);
//		Date svcDate = adjustmentRecord.getSVC_DT();
		BORFile borAdjustmentFile = new BORFile();
		borAdjustmentFile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borAdjustmentFile.setFileName(borFileName);
		borAdjustmentFile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borAdjustmentFile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borAdjustmentFile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borAdjustmentFile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borAdjustmentFile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borAdjustmentFile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borAdjustmentFile.setClaimVersionNumber("01");
		borAdjustmentFile.setClaimAmount("-"+String.valueOf(adjustmentRecord.getCLM_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setClientPrice("-"+String.valueOf(adjustmentRecord.getCLI_PRC_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setBscRevenueAmount("-"+String.valueOf(adjustmentRecord.getBSC_RVNU_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCheckNumber(adjustmentRecord.getCHK_NBR());
		borAdjustmentFile.setCheckDate(todayDateFake);
		borAdjustmentFile.setServiceDate(dateFormat.format(adjustmentRecord.getSVC_DT()).toUpperCase().trim());
		borAdjustmentFile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borAdjustmentFile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borAdjustmentFile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borAdjustmentFile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borAdjustmentFile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borAdjustmentFile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borAdjustmentFile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borAdjustmentFile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borAdjustmentFile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borAdjustmentFile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borAdjustmentFile.setBilledAmount("-"+String.valueOf(adjustmentRecord.getBIL_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setAllowedAmount("-"+String.valueOf(adjustmentRecord.getALLOW_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setDeductibleAmount("-"+String.valueOf(adjustmentRecord.getDED_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCoinsuranceAmount("-"+String.valueOf(adjustmentRecord.getCOINS_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setCopayAmount("-"+String.valueOf(adjustmentRecord.getCOPAY_AMT().setScale(2, BigDecimal.ROUND_HALF_UP)));
		borAdjustmentFile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borAdjustmentFile.setDiagnosisCodeType(" ");
		borAdjustmentFile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borAdjustmentFile.setHcpcs_id(" ");
		borAdjustmentFile.setClaimTransactionType("A");
		borFileList.add(borAdjustmentFile);
//		logger.info(borAdjustmentFile);
		logger.info("Adj Scenario 5 written in BOR File - A");
		return borFileList;
	}
	
	public List<BORFile> getAdjustmentScenario6(Session session,List<BorAdjustmentDB> borAdjustmentDbList,String borFileName){
		BorAdjustmentDB adjustmentRecord = borAdjustmentDbList.get(5);
		List<BORFile> borFileList = new ArrayList<BORFile>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date checkDate = adjustmentRecord.getCHK_DT();
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date newCheckDate = new Date(checkDate.getTime() - (2 * DAY_IN_MS));
		AmountFields amountFields = new AmountFields();
		amountFields = HelperClass.getAdjustmentAmountFields(adjustmentRecord.getBIL_AMT().add(new BigDecimal(3)));
//		Date svcDate = adjustmentRecord.getSVC_DT();
		BORFile borAdjustmentFile = new BORFile();
		borAdjustmentFile.setClaimId(adjustmentRecord.getFICT_CLM_ID().trim());
		borAdjustmentFile.setFileName(borFileName);
		borAdjustmentFile.setVendorName(adjustmentRecord.getVEND_NM().trim());
		borAdjustmentFile.setGroupNumber(adjustmentRecord.getGRP_NBR().trim());
		borAdjustmentFile.setSubgroupId(adjustmentRecord.getSBGRP_ID().trim());
		borAdjustmentFile.setSubscriberId(adjustmentRecord.getSBSCR_ID().trim());
		borAdjustmentFile.setPersonNumber(adjustmentRecord.getPERS_NBR().trim());
		borAdjustmentFile.setClaimNumber(adjustmentRecord.getCLM_NBR().trim());
		borAdjustmentFile.setClaimVersionNumber("00");
		borAdjustmentFile.setClaimAmount(amountFields.getClaimAmount());
		borAdjustmentFile.setClientPrice(amountFields.getClientPrice());
		borAdjustmentFile.setBscRevenueAmount(amountFields.getBscRevenueAmount());
		borAdjustmentFile.setCheckNumber(adjustmentRecord.getCHK_NBR());
		borAdjustmentFile.setCheckDate(dateFormat.format(newCheckDate).toUpperCase().trim());
		borAdjustmentFile.setServiceDate(dateFormat.format(adjustmentRecord.getSVC_DT()).toUpperCase().trim());
		borAdjustmentFile.setPayeeId(adjustmentRecord.getPAYE_ID());
		borAdjustmentFile.setPayeeName(adjustmentRecord.getPAYE_NM());
		borAdjustmentFile.setPlanId(adjustmentRecord.getPLN_ID().trim());
		borAdjustmentFile.setProductId(adjustmentRecord.getPRDCT_ID().trim());
		borAdjustmentFile.setProductCategory(adjustmentRecord.getPRDCT_CATEG_CD().trim());
		borAdjustmentFile.setClassId(adjustmentRecord.getCLS_ID().trim());
		borAdjustmentFile.setProductBusinessCategory(adjustmentRecord.getPRDCT_BUS_CATEG_CD().trim());
		borAdjustmentFile.setProductValueCode(adjustmentRecord.getPRDCT_VAL1_CD().trim());
		borAdjustmentFile.setLineOfBusinessId(adjustmentRecord.getLOB_ID().trim());
		borAdjustmentFile.setLegalEntity(adjustmentRecord.getLGL_ENTY_CD().trim());
		borAdjustmentFile.setBilledAmount(amountFields.getBilledAmount());
		borAdjustmentFile.setAllowedAmount(amountFields.getAllowedAmount());
		borAdjustmentFile.setDeductibleAmount(amountFields.getDeductible());
		borAdjustmentFile.setCoinsuranceAmount(amountFields.getCoInsurance());
		borAdjustmentFile.setCopayAmount(amountFields.getCopay());
		borAdjustmentFile.setDiagnosisCode(adjustmentRecord.getDIAG_CD().trim());
		borAdjustmentFile.setDiagnosisCodeType(" ");
		borAdjustmentFile.setProcedureCode(adjustmentRecord.getPROC_CD().trim());
		borAdjustmentFile.setHcpcs_id(" ");
		borAdjustmentFile.setClaimTransactionType("P");
		borFileList.add(borAdjustmentFile);
//		logger.info(borAdjustmentFile);
		logger.info("Adj Scenario 6 written in BOR File - P");
		return borFileList;
	}
}

