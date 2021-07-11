
package com.bsc.qa.facets.afa.test;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bsc.bqsa.AutomationStringUtilities;
import com.bsc.qa.facets.afa.dao.DatabaseUtil;
import com.bsc.qa.facets.afa.pojo.BORFile;
import com.bsc.qa.facets.afa.pojo.CVSFile ;
import com.bsc.qa.facets.afa.pojo.Connection;
import com.bsc.qa.facets.bor_file_generator_stt.util.HibernateUtil;
import com.github.ffpojo.exception.FFPojoException;
import com.github.ffpojo.file.writer.FileSystemFlatFileWriter;
import com.github.ffpojo.file.writer.FlatFileWriter;

/**
 * 
 * @author skumar33
 *
 */
public class BorGeneratorTest {
	DatabaseUtil util = new DatabaseUtil();
	AutomationStringUtilities decoder = new AutomationStringUtilities();
	SessionFactory sessionFactory = null;
	Session session;
	Logger logger1 = LoggerFactory.getLogger(BorGeneratorTest.class);
	String oracleUser = System.getenv("FACETS_USER");
	String oraclePassword = System.getenv("FACETS_PASSWORD");
	String oracleServer = System.getenv("FACETS_SERVER");
	String oraclePort = System.getenv("FACETS_PORT");
	String oracleDB = System.getenv("FACETS_DB");
	String oracleUrl = "jdbc:oracle:thin:@" + oracleServer + ":" + oraclePort + ":" + oracleDB ;
	String destFolder = System.getenv("INPUT_BOR_PATH")+"/";
	List<BORFile> list = new ArrayList<BORFile>();
	List<CVSFile> list2 = new ArrayList<CVSFile>();
	
	/**
	 * Setting the connection object for Hibernate session
	 * 
	 */
	@BeforeSuite
	public void getConnection() {
		
		Connection conn = new Connection();
		conn.setUsername(oracleUser);
		conn.setPassword(oraclePassword);
		conn.setUrl(oracleUrl);
		logger1.info("BOR File will be generated here --> "+destFolder);
		
		try {
			logger1.info("Connecting to Database...");
			logger1.info("Establishing DB connection with the URL - "+conn.getUrl());
			sessionFactory = HibernateUtil.createSessionFactory(conn);
		} catch (Exception e) {
			logger1.error( "Provided invalid database environment variables, Unable to Connect to DB");
			logger1.info( "Ending test execution...");
			System.exit(0);
		}
		session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		boolean success = session.isConnected();
		txn.commit();
		if (success == true){
			logger1.info("Succesfully connected to DB!");
		}
	}
	
	/**
	 * Fetching data from DB necessary and creates the BOR File
	 * 
	 * @throws Exception
	 */
	@BeforeMethod
	public void getData() throws Exception {
		//list = util.getBorFileList(session,util.getBorFileListFromDB(session),util.getBorAdjustmentRecords(session), 0);
//		String filename = list.get(0).getFileName();
//		
//		File file = new File(destFolder+filename);
//		logger1.info("BOR File Generated here --> "+file.getAbsolutePath());
//		FlatFileWriter ffWriter = new FileSystemFlatFileWriter(file, true);
//		
//		ffWriter.writeRecordList(list);
//		ffWriter.close();
	}
	
	@Test (enabled= true)
	public void testAll() throws Exception{
		logger1.info("writig the First File");
	list = util.getBorFileList(session,util.getBorFileListFromDB(session),util.getBorAdjustmentRecords(session),1);

		//list = util.getBorCVSFileList(session,util.getBorCVSFileListFromDB(session),util.getBorAdjustmentRecords(session), 0);
        String filename = list.get(0).getFileName();
        
        
		
		File file = new File(destFolder+filename);
		logger1.info("BOR File Generated here --> "+file.getAbsolutePath());
		FlatFileWriter ffWriter = new FileSystemFlatFileWriter(file, true);
		
		ffWriter.writeRecordList(list);
		ffWriter.close();
		
	}
	
	@Test
	public void testAll4() throws Exception{
		logger1.info("writig the CVS File");
		list2 = util.getBorCVSAdjFileList(session,util.getBorCVSFileListFromDB(session), util.getBorCSVAdjustmentRecords(session), 0);
String filename = list2.get(0).getFileName();
		
		File file = new File(destFolder+filename);
		logger1.info("BOR CVS File Generated here --> "+file.getAbsolutePath());
		FlatFileWriter ffWriter = new FileSystemFlatFileWriter(file, true);
		
		ffWriter.writeRecordList(list2);
		ffWriter.close();
	}
	
	

	/**
	 * Changes the Bor File format from windows to unix
	 * 
	 */
	@AfterMethod
	public void changeFileFormatToUnix(){
		try {
			logger1.info("Modifying EOL characters from Windows to UNIX format!");
			logger1.info("Modifying file - " + destFolder+list.get(0).getFileName());
			BorGeneratorTest.modifyFile(destFolder+list.get(0).getFileName());
		} catch (Exception e) {
			logger1.info("Modifying EOL characters from Windows to UNIX format!");
		}
		
	}
	
	/**
	 * Closes hibernate session
	 */
	@AfterSuite
	public void closeConnection() {
		if (session != null) {
		logger1.info( "Closing DB Connection...");
		logger1.info( "DB Connection Succesfully closed!");
		session.close();
		}
		logger1.info( "Completed test execution...");
	}
	
	/**
	 * Helper method to modify file format from windows to unix
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	 private static void modifyFile(String fileName) throws IOException
	    {

	        Path path = Paths.get(fileName);
	        Charset charset = StandardCharsets.UTF_8;

	        String content = new String(Files.readAllBytes(path), charset);
	        content = content.replaceAll("\r\n", "\n");
	        content = content.replaceAll("\r", "\n");
	        Files.write(path, content.getBytes(charset));
	    }
}

