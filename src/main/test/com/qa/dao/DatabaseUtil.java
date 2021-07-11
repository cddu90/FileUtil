
package com.bsc.qa.facets.afa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bsc.qa.facets.afa.pojo.BORDatabase;
import com.bsc.qa.facets.afa.pojo.BORFile;
import com.bsc.qa.facets.afa.pojo.BorAdjustmentDB;
import com.bsc.qa.facets.afa.pojo.CVSFile;
import com.bsc.qa.facets.bor_file_generator_stt.scenarios.TestScenarios;
import com.bsc.qa.facets.bor_file_generator_stt.util.HelperClass;

public class DatabaseUtil {

	List<BORFile>  borFileList ;
	List<CVSFile>  borCVSFileList ;//skatta05
	List<BORDatabase> borDatabaseList;
	List <BORDatabase> borCVSDatabaseList;//skatta05
	List <BORDatabase> borCVSAllDatabaseList;
	List<BorAdjustmentDB> borAdjustmentDbList;
	Map<String, String> queriesMap;
	QueriesUtil queryUtil = new QueriesUtil();
	Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);
	public List<BORDatabase> getBorFileListFromDB(Session session){
		borDatabaseList = new ArrayList<BORDatabase>();
		queriesMap =  queryUtil.queriesMap();
		SQLQuery query = session.createSQLQuery(queriesMap.get("BorFileListFromDB"));
		/*
		 * GRPID SUBGRPID SUBID MEMSFX PLANID PRDID PRDCAT CLASSID PRDBUSCAT
		 * PRDVALCD LOBDID LOBDTYPE
		 */
		List<Object[]> list = (List<Object[]>) query.list();
		for (Object[] objects : list) {
			BORDatabase borDB = new BORDatabase();
			borDB.setGrpId((String) objects[0]);
			borDB.setSubgrpId((String) objects[1]);
			borDB.setSubId((String) objects[2]);
			borDB.setMemSfx((BigDecimal) objects[3]);
			borDB.setPlanId((String) objects[4]);
			borDB.setPrdId((String) objects[5]);
			borDB.setPrdCat((String) objects[6]);
			borDB.setClassId((String) objects[7]);
			borDB.setPrdBusCat((String) objects[8]);
			borDB.setPrdValCD((String) objects[9]);
			borDB.setLobdId((String) objects[10]);
			borDB.setLobType((String) objects[11]);
			borDatabaseList.add(borDB);
			
		}

		return borDatabaseList;
	}
	
	//skatta05
	
	public List<BORDatabase> getBorCVSFileListFromDB(Session session){
		borCVSDatabaseList = new ArrayList<BORDatabase>();
		
		queriesMap =  queryUtil.queriesMap();
		SQLQuery query = session.createSQLQuery(queriesMap.get("paidclaim"));
		
		//, String type
		/*
		 * GRPID SUBGRPID SUBID MEMSFX PLANID PRDID PRDCAT CLASSID PRDBUSCAT
		 * PRDVALCD LOBDID LOBDTYPE
		 */
		List<Object[]> list = (List<Object[]>) query.list();
		for (Object[] objects : list) {
			BORDatabase borDB1 = new BORDatabase();
			borDB1.setGrpId((String) objects[0]);
			borDB1.setSubgrpId((String) objects[1]);
			borDB1.setSubId((String) objects[2]);
			borDB1.setMemSfx((BigDecimal) objects[3]);
			borDB1.setPlanId((String) objects[4]);
			borDB1.setPrdId((String) objects[5]);
			borDB1.setPrdCat((String) objects[6]);
			borDB1.setClassId((String) objects[7]);
			borDB1.setPrdBusCat((String) objects[8]);
			borDB1.setPrdValCD((String) objects[9]);
			borDB1.setLobdId((String) objects[10]);
			borDB1.setLobType((String) objects[11]);
			borCVSDatabaseList.add(borDB1);
			
		}

		return borCVSDatabaseList;
	}
	
	
	public Map<String,List<BORDatabase>> getBorCVSFileAllListFromDB(Session session){
		borCVSAllDatabaseList = new ArrayList<BORDatabase>();
		HashMap<String, List<BORDatabase>> BORDatabaseListComplete= new HashMap<String, List<BORDatabase>>();	
		
		//String[]  CVSQueriesData= {"paidclaim"};
		
		queriesMap =  queryUtil.queriesMap();
	//	for (String s1 : CVSQueriesData) {
		
		SQLQuery query = session.createSQLQuery(queriesMap.get("paidclaim"));
		
		
		
		//, String type
		/*
		 * GRPID SUBGRPID SUBID MEMSFX PLANID PRDID PRDCAT CLASSID PRDBUSCAT
		 * PRDVALCD LOBDID LOBDTYPE
		 */
		List<Object[]> list = (List<Object[]>) query.list();
		for (Object[] objects : list) {
			BORDatabase borDB = new BORDatabase();
			borDB.setGrpId((String) objects[0]);
			borDB.setSubgrpId((String) objects[1]);
			borDB.setSubId((String) objects[2]);
			borDB.setMemSfx((BigDecimal) objects[3]);
			borDB.setPlanId((String) objects[4]);
			borDB.setPrdId((String) objects[5]);
			borDB.setPrdCat((String) objects[6]);
			borDB.setClassId((String) objects[7]);
			borDB.setPrdBusCat((String) objects[8]);
			borDB.setPrdValCD((String) objects[9]);
			borDB.setLobdId((String) objects[10]);
			borDB.setLobType((String) objects[11]);
			borCVSAllDatabaseList.add(borDB);
			
		//}
		}
		
		BORDatabaseListComplete.put( "paidclaim", borCVSAllDatabaseList);

		//}
		//return borCVSDatabaseList;
		
		return BORDatabaseListComplete;
	}
	
	
	
	
	
	public List<BorAdjustmentDB> getBorAdjustmentRecords(Session session){
		borAdjustmentDbList = new ArrayList<BorAdjustmentDB>();
		queriesMap =  queryUtil.queriesMap();
		SQLQuery query = session.createSQLQuery(queriesMap.get("BorAdjustmentRecords"));
		List<Object[]> list = (List<Object[]>) query.list();
		for (Object[] objects : list) {
			BorAdjustmentDB borAdjustmentDB = new BorAdjustmentDB();
		
			borAdjustmentDB.setFICT_CLM_ID((String)objects[1]);
			borAdjustmentDB.setFIL_NM("");
			borAdjustmentDB.setVEND_NM((String)objects[2]);
			borAdjustmentDB.setGRP_NBR((String)objects[3]);
			borAdjustmentDB.setSBGRP_ID((String)objects[4]);
			borAdjustmentDB.setSBSCR_ID((String)objects[5]);
			borAdjustmentDB.setPERS_NBR((String)objects[6]);
			borAdjustmentDB.setCLM_NBR((String)objects[7]);
			borAdjustmentDB.setCLM_VER_NBR((String)objects[8]);
			borAdjustmentDB.setCLM_AMT((BigDecimal)objects[9]);
			borAdjustmentDB.setCLI_PRC_AMT((BigDecimal)objects[10]);
			borAdjustmentDB.setBSC_RVNU_AMT((BigDecimal)objects[11]);
			borAdjustmentDB.setCHK_NBR((String)objects[12]);
			borAdjustmentDB.setCHK_DT((Date)objects[13]);
			borAdjustmentDB.setSVC_DT((Date)objects[14]);
			borAdjustmentDB.setPAYE_ID((String)objects[15]);
			borAdjustmentDB.setPAYE_NM((String)objects[16]);
			borAdjustmentDB.setPLN_ID((String)objects[17]);
			borAdjustmentDB.setPRDCT_ID((String)objects[18]);
			borAdjustmentDB.setPRDCT_CATEG_CD((String)objects[19]);
			borAdjustmentDB.setCLS_ID((String)objects[20]);
			borAdjustmentDB.setPRDCT_BUS_CATEG_CD((String)objects[21]);
			borAdjustmentDB.setPRDCT_VAL1_CD((String)objects[22]);
			borAdjustmentDB.setLOB_ID((String)objects[23]);
			borAdjustmentDB.setLGL_ENTY_CD((String)objects[24]);
			borAdjustmentDB.setBIL_AMT((BigDecimal)objects[25]);
			borAdjustmentDB.setALLOW_AMT((BigDecimal)objects[26]);
			borAdjustmentDB.setDED_AMT((BigDecimal)objects[27]);
			borAdjustmentDB.setCOINS_AMT((BigDecimal)objects[28]);
			borAdjustmentDB.setCOPAY_AMT((BigDecimal)objects[29]);
			borAdjustmentDB.setDIAG_CD((String)objects[30]);
			borAdjustmentDB.setDIAG_TYP_CD((String)objects[31]);
			borAdjustmentDB.setPROC_CD((String)objects[32]);
			borAdjustmentDB.setHCPCS_ID((String)objects[33]);
			borAdjustmentDB.setCLM_TRANS_TYP_CD((String)objects[34]);
			borAdjustmentDB.setROW_NUM1(new BigDecimal("00"));
			borAdjustmentDbList.add(borAdjustmentDB);
		}
		
		return borAdjustmentDbList;
	}
	
	public List<BorAdjustmentDB> getBorCSVAdjustmentRecords(Session session){
		borAdjustmentDbList = new ArrayList<BorAdjustmentDB>();
		queriesMap =  queryUtil.queriesMap();
		SQLQuery query = session.createSQLQuery(queriesMap.get("BorCSVAdjustmentRecords"));
		List<Object[]> list = (List<Object[]>) query.list();
		for (Object[] objects : list) {
			BorAdjustmentDB borAdjustmentDB = new BorAdjustmentDB();
		
			borAdjustmentDB.setFICT_CLM_ID((String)objects[1]);
			borAdjustmentDB.setFIL_NM("");
			borAdjustmentDB.setVEND_NM((String)objects[2]);
			borAdjustmentDB.setGRP_NBR((String)objects[3]);
			borAdjustmentDB.setSBGRP_ID((String)objects[4]);
			borAdjustmentDB.setSBSCR_ID((String)objects[5]);
			borAdjustmentDB.setPERS_NBR((String)objects[6]);
			borAdjustmentDB.setCLM_NBR((String)objects[7]);
			borAdjustmentDB.setCLM_VER_NBR((String)objects[8]);
			borAdjustmentDB.setCLM_AMT((BigDecimal)objects[9]);
			borAdjustmentDB.setCLI_PRC_AMT((BigDecimal)objects[10]);
			borAdjustmentDB.setBSC_RVNU_AMT((BigDecimal)objects[11]);
			borAdjustmentDB.setCHK_NBR((String)objects[12]);
			borAdjustmentDB.setCHK_DT((Date)objects[13]);
			borAdjustmentDB.setSVC_DT((Date)objects[14]);
			borAdjustmentDB.setPAYE_ID((String)objects[15]);
			borAdjustmentDB.setPAYE_NM((String)objects[16]);
			borAdjustmentDB.setPLN_ID((String)objects[17]);
			borAdjustmentDB.setPRDCT_ID((String)objects[18]);
			borAdjustmentDB.setPRDCT_CATEG_CD((String)objects[19]);
			borAdjustmentDB.setCLS_ID((String)objects[20]);
			borAdjustmentDB.setPRDCT_BUS_CATEG_CD((String)objects[21]);
			borAdjustmentDB.setPRDCT_VAL1_CD((String)objects[22]);
			borAdjustmentDB.setLOB_ID((String)objects[23]);
			borAdjustmentDB.setLGL_ENTY_CD((String)objects[24]);
			borAdjustmentDB.setBIL_AMT((BigDecimal)objects[25]);
			borAdjustmentDB.setALLOW_AMT((BigDecimal)objects[26]);
			borAdjustmentDB.setDED_AMT((BigDecimal)objects[27]);
			borAdjustmentDB.setCOINS_AMT((BigDecimal)objects[28]);
			borAdjustmentDB.setCOPAY_AMT((BigDecimal)objects[29]);
			borAdjustmentDB.setDIAG_CD((String)objects[30]);
			borAdjustmentDB.setDIAG_TYP_CD((String)objects[31]);
			borAdjustmentDB.setPROC_CD((String)objects[32]);
			borAdjustmentDB.setHCPCS_ID((String)objects[33]);
			borAdjustmentDB.setCLM_TRANS_TYP_CD((String)objects[34]);
			borAdjustmentDB.setROW_NUM1(new BigDecimal("00"));
			borAdjustmentDbList.add(borAdjustmentDB);
		}
		
		return borAdjustmentDbList;
	}
	
	
	
	public List<BORFile> getBorFileList(Session session,List<BORDatabase> borDatabaseList,List<BorAdjustmentDB> borAdjustmentDbList, int i){
		borFileList = new ArrayList<BORFile>();
	//skatta05
		String[] FileName = HelperClass.getBorFileName();
		String borFileName = FileName[i];
		
		
		TestScenarios getBorRecords = new TestScenarios();
		//Paid Scenario 1
		BORFile borfilePaidScenario1 = getBorRecords.getPaidScenario1(session, borDatabaseList,borFileName);
		borFileList.add(borfilePaidScenario1);
		borFileList.add(borfilePaidScenario1);
		//Paid Scenario 2
		borFileList.add(getBorRecords.getPaidScenario2(session, borDatabaseList,borFileName));
		//Paid Scenario 3
		borFileList.add(getBorRecords.getPaidScenario3(session, borDatabaseList,borFileName));
		//Paid Scenario 4
		borFileList.add(getBorRecords.getPaidScenario4(session, borDatabaseList,borFileName));
		//Paid Scenario 5
		borFileList.add(getBorRecords.getPaidScenario5(session, borDatabaseList, borFileName));
		
		//Adjustment scenario 1
		try {
			borFileList.addAll(getBorRecords.getAdjustmentScenario1(session, borAdjustmentDbList, borFileName));
		} catch (Exception e) {
			logger.error("Data not present for Adjustment Scenario 1");
			
		}
		//Adjustment scenario 2
		try {
			borFileList.addAll(getBorRecords.getAdjustmentScenario2(session, borAdjustmentDbList, borFileName));
		} catch (Exception e) {
			logger.error("Data not present for Adjustment Scenario 2");
		}
		//Adjustment scenario 3
		try {
			borFileList.addAll(getBorRecords.getAdjustmentScenario3(session, borAdjustmentDbList, borFileName));
		} catch (Exception e) {
			logger.error("Data not present for Adjustment Scenario 3");
		}
		//Adjustment scenario 4
		try {
			borFileList.addAll(getBorRecords.getAdjustmentScenario4(session, borAdjustmentDbList, borFileName));
		} catch (Exception e) {
			logger.error("Data not present for Adjustment Scenario 4");
		}
		//Adjustment scenario 5
		try {
			borFileList.addAll(getBorRecords.getAdjustmentScenario5(session, borAdjustmentDbList, borFileName));
		} catch (Exception e) {
			logger.error("Data not present for Adjustment Scenario 5");
		}
		//Adjustment scenario 6
		try {
			borFileList.addAll(getBorRecords.getAdjustmentScenario6(session, borAdjustmentDbList, borFileName));
		} catch (Exception e) {
			logger.error("Data not present for Adjustment Scenario 6");
		}
		
		return borFileList;
	}
	
	
	//skatta05
	
	public List<CVSFile> getBorCVSFileList(Session session,List<BORDatabase> borDatabaseList, int i){
		
		borCVSFileList = new ArrayList<CVSFile>();

		String[] FileName = HelperClass.getBorFileName();
		String borFileName = FileName[i];
		
		
		TestScenarios getBorRecords = new TestScenarios();
		
	//	CVSFile borfilePaidScenario1 = getBorRecords.getCVSPaidScenario1(session, borDatabaseList.get(0),borFileName);
		borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session, borDatabaseList.get(0),borFileName, "nonZero"));
		borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session, borDatabaseList.get(1),borFileName, "Zero"));

		
		return borCVSFileList;
	}
	
	
public List<CVSFile> getBorCVSAdjFileList(Session session,List<BORDatabase> borDatabaseList, List<BorAdjustmentDB> borCVSAdjustmentDbList ,  int i){
		
		borCVSFileList = new ArrayList<CVSFile>();

		String[] FileName = HelperClass.getBorFileName();
		String borFileName = FileName[i];
		
		
		TestScenarios getBorRecords = new TestScenarios();
		
	
		borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session, borDatabaseList.get(0),borFileName, "nonZero"));
		borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session, borDatabaseList.get(1),borFileName, "nonZero"));
		borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session, borDatabaseList.get(2),borFileName, "nonZero"));
		borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session, borDatabaseList.get(3),borFileName, "Zero"));
		
		
		borCVSFileList.add(getBorRecords.getCVSAdjustmentScenario1(session, borCVSAdjustmentDbList.get(0), borFileName , "neg", "0"));
		borCVSFileList.add(getBorRecords.getCVSAdjustmentScenario1(session, borCVSAdjustmentDbList.get(0), borFileName, "pos", "1.20"));
		
		borCVSFileList.add(getBorRecords.getCVSAdjustmentScenario1(session, borCVSAdjustmentDbList.get(2), borFileName,"neg", "0"));
		borCVSFileList.add(getBorRecords.getCVSAdjustmentScenario1(session, borCVSAdjustmentDbList.get(2), borFileName, "pos", "0.80"));
		
		borCVSFileList.add(getBorRecords.getCVSAdjustmentScenario1(session, borCVSAdjustmentDbList.get(4), borFileName,"neg", "0"));
		
		borCVSFileList.addAll(getBorRecords.getCVSPaidScenario2(session, borDatabaseList.get(6),borFileName, "sc2"));
		borCVSFileList.addAll(getBorRecords.getCVSPaidScenario2(session, borDatabaseList.get(7),borFileName, "sc3"));
	
		borCVSFileList.add(getBorRecords.getCVSDuplicateScenario(session, borCVSAdjustmentDbList.get(5),borFileName));
		
			
		
		return borCVSFileList;
	}
	
	
	
	
	
	//TBD
	
//	  public List<CVSFile> getBorAllCVSFileList(Session session, Map< String,
//	  List<BORDatabase>>borDatabaseAllList, int i){
//	  
//	  borCVSFileList = new ArrayList<CVSFile>(); //skatta05 String[] FileName =
//	  HelperClass.getBorFileName(); String borFileName = FileName[i];
//	  
//	  borCVSAllDatabaseList = borDatabaseAllList.get("paidclaim");
//	  
//	  
//	  
//	  TestScenarios getBorRecords = new TestScenarios(); //Paid Scenario 1 CVSFile
//	  borfilePaidScenario1 = getBorRecords.getCVSPaidScenario1(session,
//	  borCVSAllDatabaseList,borFileName); borCVSFileList.add(borfilePaidScenario1);
//	  borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session,
//	  borCVSAllDatabaseList,borFileName));
//	  borCVSFileList.add(getBorRecords.getCVSPaidScenario1(session,
//	  borCVSAllDatabaseList,borFileName));
//	  
//	  
//	  
//	  return borCVSFileList; }
	 
	
	
	
	
}

