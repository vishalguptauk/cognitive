/**
 * 
 */
package com.hqnRegression.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * @author 607092941
 * 
 */
public class JenkinsTest {

	static Map<String, Map<String, String>> dataMap = null;
	static XSSFWorkbook myWorkBook;
	private static String inputFilePath = "C:/RegressionPackLatest/";
	private static JComboBox categoryBox;
	static String[] environments = new String[] { "Select", "D2", "D3", "D6",
			"D9", "T3" };
	private static JComboBox envBox = new JComboBox(environments);
	private static JTextField tstNumbrs;
	private static int categoryIndex;
	private static String nameofthesheetselected;
	private static InputStream inputStream;
	private static Properties testProps, staticDetailsProp;
	static JFrame jfame;
	private static String envName;
	private static String env;
	private String loggerPath = CommonMethods.getProperty("log4j.properties");
	private static Logger logger = Logger.getLogger("Create_Asset");

	/*
	 * public JenkinsTestSelector(String tcNumber,String testCategory){ try {
	 * 
	 * readInputData(tcNumber,testCategory);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

	/*
	 * public JenkinsTestSelector() {
	 * PropertyConfigurator.configure(loggerPath);
	 * 
	 * jfame = new JFrame("Regression Suite");
	 * 
	 * // create a new panel with GridBagLayout manager JPanel newPanel = new
	 * JPanel(new GridBagLayout());
	 * 
	 * GridBagConstraints constraints = new GridBagConstraints();
	 * constraints.anchor = GridBagConstraints.WEST; constraints.insets = new
	 * Insets(10, 10, 10, 10);
	 * 
	 * Font font = new Font("Verdana", Font.BOLD, 12);
	 * 
	 * // add components to the panel constraints.gridx = 0; constraints.gridy =
	 * 0;
	 * 
	 * // Environment JLabel envLable = new JLabel("Environment:");
	 * newPanel.add(envLable, constraints); constraints.gridx = 1;
	 * newPanel.add(envBox, constraints); // CategoryBox action Listner
	 * envBox.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { int envIndex = (int)
	 * envBox.getSelectedIndex(); if (envIndex > 0) { logger.info("envIndex -->"
	 * + envIndex); categoryBox.setEnabled(true); envName =
	 * envBox.getSelectedItem().toString(); } else {
	 * categoryBox.setEnabled(false); categoryBox.setSelectedIndex(0);
	 * tstNumbrs.setEnabled(false); tstNumbrs.setText("");
	 * logger.info("Please select proper Environment"); } } });
	 * 
	 * constraints.gridx = 0; constraints.gridy = 1; //
	 * categoryLabel.setFont(font); JLabel categoryLabel = new
	 * JLabel("TestCategory: "); newPanel.add(categoryLabel, constraints);
	 * 
	 * constraints.gridx = 1; List<String> categoryList = readWorkSheets(); if
	 * (categoryList != null && categoryList.size() > 0) categoryBox = new
	 * JComboBox( categoryList.toArray(new String[categoryList.size()]));
	 * newPanel.add(categoryBox, constraints); categoryBox.setEnabled(false);
	 * 
	 * // CategoryBox action Listner categoryBox.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent e) {
	 * nameofthesheetselected = categoryBox.getSelectedItem() .toString();
	 * categoryIndex = (int) categoryBox.getSelectedIndex() + 1; if
	 * (categoryIndex > 1) { logger.info("categoryIndex -->" + categoryIndex);
	 * tstNumbrs.setEnabled(true); } else { tstNumbrs.setEnabled(false);
	 * logger.info("Please select proper category"); } } });
	 * 
	 * constraints.gridx = 0; constraints.gridy = 2; JLabel testNumbersLabel =
	 * new JLabel("TestNumbers: "); newPanel.add(testNumbersLabel, constraints);
	 * 
	 * constraints.gridx = 1; tstNumbrs = new JTextField(15);
	 * newPanel.add(tstNumbrs, constraints); tstNumbrs.setEnabled(false);
	 * 
	 * constraints.gridx = 1; constraints.gridy = 3; constraints.gridwidth = 2;
	 * JButton okButton = new JButton("OK"); newPanel.add(okButton,
	 * constraints);
	 * 
	 * // Ok button action listner okButton.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent e) {
	 * readInputData(); jfame.setVisible(false); } });
	 * 
	 * constraints.gridx = 1; constraints.gridy = 3; constraints.gridwidth = 2;
	 * constraints.anchor = GridBagConstraints.LINE_END; JButton cancelButton =
	 * new JButton("Cancel"); newPanel.add(cancelButton, constraints);
	 * 
	 * // Cancel button action listner. cancelButton.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent e) {
	 * jfame.setVisible(false); } });
	 * 
	 * constraints.gridx = 0; constraints.gridy = 4; JLabel noteLabel = new
	 * JLabel( "Note:Please pass test case numbers like 1-5 Or 1,2 Or All");
	 * newPanel.add(noteLabel, constraints);
	 * 
	 * // set border for the panel
	 * newPanel.setBorder(BorderFactory.createTitledBorder(
	 * BorderFactory.createEtchedBorder(), "Regression Execution"));
	 * 
	 * newPanel.setFont(font); // add the panel to this frame
	 * jfame.add(newPanel);
	 * 
	 * jfame.pack();
	 * 
	 * jfame.setLocationRelativeTo(null);
	 * 
	 * jfame.setVisible(true); }
	 */

	private static List<String> readWorkSheets() {
		String latestFileName = getLasteFileName();
		File filePath = new File(inputFilePath + latestFileName);
		FileInputStream fis;
		List<String> categoryList = new ArrayList<String>();
		try {
			fis = new FileInputStream(filePath);
			myWorkBook = new XSSFWorkbook(fis);
			for (int i = 0; i < myWorkBook.getNumberOfSheets(); i++) {
				categoryList.add(myWorkBook.getSheetName(i));
			}
			if (categoryList.size() > 0) {
				categoryList.add("ALL");
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@Test
	public static void testSelector(Method method) throws IOException {
		// set look and feel to the system look and feel
		try {
			env = System.getProperty("environment");
			System.out.println("ENVIRONMENT: " + env);

			String tcNumbers = System.getProperty("tcNumbers");
			System.out.println("TESTCASE NUMBER LIST: " + tcNumbers);

			String testCategory = System.getProperty("testCategory");
			System.out.println("TEST CATEGORY: " + testCategory);

			readWorkSheets();
			readInputData(tcNumbers, testCategory);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		updateATUpropertyFile();

	}

	private static void updateATUpropertyFile() throws IOException {
		moveTheFile();
		inputStream = new FileInputStream(
				"./src/test/resources/com/hqnRegression/atu.properties");
		testProps = new Properties();
		testProps.load(inputStream);
		String atuReportsDir = "reports/ATUTestngReporter"
				+ (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		logger.info("Reports Dir Filename: " + atuReportsDir);
		testProps.put("atu.reports.dir", atuReportsDir);
		FileOutputStream output = new FileOutputStream(
				"./src/test/resources/com/hqnRegression/atu.properties");
		testProps.store(output, "This is atu.properties overwrite file");

	}

	public static void moveTheFile() {
		try {

			File ExeDir = new File("src/test/resources/com/hqnRegression");
			File srcTR = new File("lib/atu.properties");
			File dstTR = new File(
					"src/test/resources/com/hqnRegression/atu.properties");

			if (dstTR.exists()) {
				FileUtils.deleteQuietly(dstTR);
				FileUtils.copyFileToDirectory(srcTR, ExeDir);
			} else {
				FileUtils.copyFileToDirectory(srcTR, ExeDir);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getLasteFileName() {
		String fileName = null;
		try {
			File file = new File(inputFilePath);
			File[] files = file.listFiles();
			files = file.listFiles((FileFilter) new SuffixFileFilter(".xlsx",
					IOCase.SENSITIVE));
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);

			// Just for logger
			for (File f : files) {
				logger.info("File Name : " + f.getName()
						+ ", Last Modified Date: " + new Date(f.lastModified())
						+ "");
			}
			fileName = files[0].getName().replace("~$", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileName;
	}

	private static void readInputData() {

		try {
			dataMap = new HashMap<String, Map<String, String>>();

			// Read All sheets testcases.
			if (nameofthesheetselected != null
					&& nameofthesheetselected.equalsIgnoreCase("ALL")) {
				for (int i = 1; i < myWorkBook.getNumberOfSheets(); i++) {
					XSSFSheet mySheet = myWorkBook.getSheetAt(i);
					logger.info(nameofthesheetselected);
					int j = 0;
					for (Row rowData : mySheet) {
						if (j > 0
								&& rowData.getCell(0) != null
								&& rowData.getCell(20).toString()
										.replaceAll("\\s", "")
										.equalsIgnoreCase("FullyAutomated")) {
							String tcNumber = Math.round(Float
									.parseFloat(rowData.getCell(0).toString()))
									+ "";
							logger.info("tc Number: " + tcNumber);
							dataMap.put(tcNumber, readRowData(rowData));
						} else {
							j++;
						}
					}
				}
			} else if (nameofthesheetselected != null
					&& !nameofthesheetselected.equalsIgnoreCase("ALL")
					&& tstNumbrs.getText() != null) {

				List<Integer> tcNoList = null;
				XSSFSheet mySheet = myWorkBook.getSheet(nameofthesheetselected);
				// Read specific sheet spread sheet data With All combinations
				if (tstNumbrs.getText().equalsIgnoreCase("All")) {
					int j = 0;
					for (Row rowData : mySheet) {
						if (j > 0
								&& rowData.getCell(0) != null
								&& rowData.getCell(20) != null
								&& rowData.getCell(20).toString()
										.replaceAll("\\s", "")
										.equalsIgnoreCase("FullyAutomated")) {
							String tcNumber = Math.round(Float
									.parseFloat(rowData.getCell(0).toString()))
									+ "";
							logger.info("tc Number: " + tcNumber);
							dataMap.put(tcNumber, readRowData(rowData));
						} else {
							j++;
						}
					}
				} else {
					// Read specific sheet spread sheet data except All
					// combinations
					tcNoList = splitTestnumbers();
					if (tcNoList != null && tcNoList.size() > 0) {
						for (int testNo : tcNoList) {
							logger.info("Passed TCNo: " + testNo);

							int noOfRowsCount = mySheet
									.getPhysicalNumberOfRows();

							Row rowData = null;

							// first cell data comparision with given numbers.
							if (noOfRowsCount > 0) {
								for (int i = 1; i <= noOfRowsCount; i++) {
									Row tempRow = mySheet.getRow(i);
									if (tempRow != null
											&& tempRow.getCell(0) != null) {
										int tcNo = Math.round(Float
												.parseFloat(tempRow.getCell(0)
														.toString()));
										if (tcNo == testNo) {
											rowData = mySheet.getRow(i);
											break;
										}
									}
								}
							}

							if (rowData != null
									&& rowData.getCell(20).toString()
											.replaceAll("\\s", "")
											.equalsIgnoreCase("FullyAutomated")) {
								logger.info("DATA AVAILABLE IN SHEET :"
										+ testNo);
								dataMap.put(testNo + "", readRowData(rowData));

								// Logic to add dependent test case details
								if (rowData.getCell(22) != null
										&& !rowData.getCell(22).toString()
												.isEmpty()) {

									System.out.println(rowData.getCell(22)
											.toString());

									Row inneRowData = mySheet
											.getRow((int) rowData.getCell(22)
													.getNumericCellValue());
									String tcNo = Math.round(Float
											.parseFloat(inneRowData.getCell(0)
													.toString()))
											+ "";
									if (inneRowData != null) {
										logger.info("DATA AVAILABLE IN SHEET :"
												+ tcNo);
										dataMap.put(tcNo + "",
												readRowData(inneRowData));
									}
								}
								// Changes ends here

							} else {
								System.out
										.println("DATA NOT AVAILABLE IN SHEET :"
												+ testNo);
							}
						}
					} else {
						logger.info("PLEASE PROVIDE VALID DETAILS");
					}
				}
			} else {
				logger.info("PLEASE PROVIDE VALID DETAILS");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeTestCases();

	}

	// For Jenkins
	private static void readInputData(String testCaseNumberList,
			String testCategory) {

		try {
			String nameofthesheetselected = testCategory;
			dataMap = new HashMap<String, Map<String, String>>();

			// Read All sheets testcases.
			if (nameofthesheetselected != null
					&& nameofthesheetselected.equalsIgnoreCase("ALL")) {
				for (int i = 1; i < myWorkBook.getNumberOfSheets(); i++) {
					XSSFSheet mySheet = myWorkBook.getSheetAt(i);
					logger.info(nameofthesheetselected);
					int j = 0;
					for (Row rowData : mySheet) {
						if (j > 0
								&& rowData.getCell(0) != null
								&& rowData.getCell(20).toString()
										.replaceAll("\\s", "")
										.equalsIgnoreCase("FullyAutomated")) {
							String tcNumber = Math.round(Float
									.parseFloat(rowData.getCell(0).toString()))
									+ "";
							logger.info("tc Number: " + tcNumber);
							dataMap.put(tcNumber, readRowData(rowData));

							// Logic to add dependent test case details
							if (rowData.getCell(22) != null
									&& !rowData.getCell(22).toString()
											.isEmpty()) {

								Row inneRowData = mySheet.getRow((int) rowData
										.getCell(22).getNumericCellValue());
								String testNo = Math.round(Float
										.parseFloat(inneRowData.getCell(0)
												.toString()))
										+ "";
								if (inneRowData != null) {

									logger.info("DATA AVAILABLE IN SHEET :"
											+ testNo);
									dataMap.put(testNo + "",
											readRowData(inneRowData));
								}
							}
							// Changes ends here

						} else {
							j++;
						}
					}
				}
			} else if (nameofthesheetselected != null
					&& !nameofthesheetselected.equalsIgnoreCase("ALL")
					&& testCaseNumberList != null) {

				List<Integer> tcNoList = null;
				XSSFSheet mySheet = myWorkBook.getSheet(nameofthesheetselected);
				// Read specific sheet spread sheet data With All combinations
				if (testCaseNumberList.equalsIgnoreCase("All")) {
					int j = 0;
					for (Row rowData : mySheet) {
						if (j > 0
								&& rowData.getCell(0) != null
								&& rowData.getCell(20) != null
								&& rowData.getCell(20).toString()
										.replaceAll("\\s", "")
										.equalsIgnoreCase("FullyAutomated")) {
							String tcNumber = Math.round(Float
									.parseFloat(rowData.getCell(0).toString()))
									+ "";
							logger.info("tc Number: " + tcNumber);
							dataMap.put(tcNumber, readRowData(rowData));

							// Logic to add dependent test case details
							if (rowData.getCell(22) != null
									&& !rowData.getCell(22).toString()
											.isEmpty()) {

								Row inneRowData = mySheet.getRow((int) rowData
										.getCell(22).getNumericCellValue());
								String testNo = Math.round(Float
										.parseFloat(inneRowData.getCell(0)
												.toString()))
										+ "";
								if (inneRowData != null) {

									logger.info("DATA AVAILABLE IN SHEET :"
											+ testNo);
									dataMap.put(testNo + "",
											readRowData(inneRowData));
								}
							}
							// Changes ends here

						} else {
							j++;
						}
					}
				} else {
					// Read specific sheet spread sheet data except All
					// combinations
					tcNoList = splitTestnumbers(testCaseNumberList);
					if (tcNoList != null && tcNoList.size() > 0) {
						for (int testNo : tcNoList) {
							logger.info("Passed TCNo: " + testNo);

							int noOfRowsCount = mySheet
									.getPhysicalNumberOfRows();

							Row rowData = null;

							// first cell data comparision with given numbers.
							if (noOfRowsCount > 0) {
								for (int i = 1; i <= noOfRowsCount; i++) {
									Row tempRow = mySheet.getRow(i);
									if (tempRow != null
											&& tempRow.getCell(0) != null) {
										int tcNo = Math.round(Float
												.parseFloat(tempRow.getCell(0)
														.toString()));
										if (tcNo == testNo) {
											rowData = mySheet.getRow(i);
											break;
										}
									}
								}
							}

							if (rowData != null
									&& rowData.getCell(20).toString()
											.replaceAll("\\s", "")
											.equalsIgnoreCase("FullyAutomated")) {
								logger.info("DATA AVAILABLE IN SHEET :"
										+ testNo);
								dataMap.put(testNo + "", readRowData(rowData));

								// Logic to add dependent test case details
								if (rowData.getCell(22) != null
										&& !rowData.getCell(22).toString()
												.isEmpty()) {

									System.out.println(rowData.getCell(22)
											.toString());

									Row inneRowData = mySheet
											.getRow((int) rowData.getCell(22)
													.getNumericCellValue());
									String tcNo = Math.round(Float
											.parseFloat(inneRowData.getCell(0)
													.toString()))
											+ "";
									if (inneRowData != null) {
										logger.info("DATA AVAILABLE IN SHEET :"
												+ tcNo);
										dataMap.put(tcNo + "",
												readRowData(inneRowData));
									}
								}
								// Changes ends here

							} else {
								System.out
										.println("DATA NOT AVAILABLE IN SHEET :"
												+ testNo);
							}
						}
					} else {
						logger.info("PLEASE PROVIDE VALID DETAILS");
					}
				}
			} else {
				logger.info("PLEASE PROVIDE VALID DETAILS");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executeTestCases();

	}

	private static List<Integer> splitTestnumbers() {

		String testCaseNos = tstNumbrs.getText();
		List<Integer> tcNoList = new ArrayList<Integer>();
		if (testCaseNos.contains(",")) {
			String[] testNumbersArray = testCaseNos.split(",");
			if (testNumbersArray.length > 1) {
				for (int i = 0; i < testNumbersArray.length; i++) {
					if (testNumbersArray[i].contains("-")) {
						String[] iArray = testNumbersArray[i].split("-");
						for (int j = Integer.parseInt(iArray[0]); j <= Integer
								.parseInt(iArray[iArray.length - 1]); j++) {
							tcNoList.add(j);
						}
					} else {
						tcNoList.add(Integer.parseInt(testNumbersArray[i]));
					}
				}
			}
		} else if (testCaseNos.contains("-")) {
			String[] iArray = testCaseNos.split("-");
			for (int j = Integer.parseInt(iArray[0]); j <= Integer
					.parseInt(iArray[iArray.length - 1]); j++) {
				tcNoList.add(j);
			}
		} else if (!testCaseNos.contains("-") && !testCaseNos.contains(",")) {
			tcNoList.add(Integer.parseInt(testCaseNos));
		}
		return tcNoList;
	}

	// For Jenkins
	private static List<Integer> splitTestnumbers(String testCaseNumberList) {

		// String testCaseNos = tstNumbrs.getText();
		String testCaseNos = testCaseNumberList;
		List<Integer> tcNoList = new ArrayList<Integer>();
		if (testCaseNos.contains(",")) {
			String[] testNumbersArray = testCaseNos.split(",");
			if (testNumbersArray.length > 1) {
				for (int i = 0; i < testNumbersArray.length; i++) {
					if (testNumbersArray[i].contains("-")) {
						String[] iArray = testNumbersArray[i].split("-");
						for (int j = Integer.parseInt(iArray[0]); j <= Integer
								.parseInt(iArray[iArray.length - 1]); j++) {
							tcNoList.add(j);
						}
					} else {
						tcNoList.add(Integer.parseInt(testNumbersArray[i]));
					}
				}
			}
		} else if (testCaseNos.contains("-")) {
			String[] iArray = testCaseNos.split("-");
			for (int j = Integer.parseInt(iArray[0]); j <= Integer
					.parseInt(iArray[iArray.length - 1]); j++) {
				tcNoList.add(j);
			}
		} else if (!testCaseNos.contains("-") && !testCaseNos.contains(",")) {
			tcNoList.add(Integer.parseInt(testCaseNos));
		}
		return tcNoList;
	}

	private static Map<String, String> readRowData(Row rowData) {

		Map<String, String> rowDataMap = new HashMap<String, String>();

		if (rowData.getCell(0) != null) {
			String tcNumber = Math.round(Float.parseFloat(rowData.getCell(0)
					.toString())) + "";
			logger.info("tc Number: " + tcNumber);
			rowDataMap.put("tcNo", tcNumber);
		}

		if (rowData.getCell(13) != null) {
			readCellData(rowDataMap, rowData.getCell(13).toString());
		}

		if (rowData.getCell(14) != null) {
			readCellData(rowDataMap, rowData.getCell(14).toString());
		}

		if (rowData.getCell(15) != null) {
			readCellData(rowDataMap, rowData.getCell(15).toString());
		}

		if (rowData.getCell(16) != null)
			readCellData(rowDataMap, rowData.getCell(16).toString());

		if (rowData.getCell(17) != null) {
			readCellData(rowDataMap, rowData.getCell(17).toString());
		}

		if (rowData.getCell(18) != null)
			readCellData(rowDataMap, rowData.getCell(18).toString());

		if (rowData.getCell(19) != null)
			rowDataMap.put("className", rowData.getCell(19).toString());

		rowDataMap.put("envName", env);

		return rowDataMap;
	}

	public static void readCellData(Map<String, String> dataMap, String cellData) {
		String[] cellDataSplitArr = cellData.split("\n");
		for (String cellAttData : cellDataSplitArr) {
			String[] colonSplitArr = cellAttData.split(":");
			if (colonSplitArr.length > 1) {
				logger.info(colonSplitArr[0].toLowerCase() + "  : "
						+ colonSplitArr[1]);
				dataMap.put(colonSplitArr[0].toLowerCase(), colonSplitArr[1]);
			} else {
				dataMap.put(colonSplitArr[0].toLowerCase(), "");
				logger.info(colonSplitArr[0].toLowerCase() + "  : ");
			}
		}
	}

	private static void executeTestCases() {
		try {
			PropertyAdmin.testSetDBProperties(env, "Primary", "", "");
			PropertyAdmin.testSetSoapuiProperties(env);
			staticDetailsProp = CommonMethods.readPuttyConnectionDetails();
			int tunnelLocalPort = Integer.parseInt(staticDetailsProp
					.getProperty("puttyLocalPort"));

			PuttyConn.killStaleSessions(tunnelLocalPort);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (dataMap != null && dataMap.size() > 0) {

			ArrayList<Integer> sortList = new ArrayList<Integer>();
			for (Map.Entry<String, Map<String, String>> mainEntry : dataMap
					.entrySet()) {
				sortList.add(Integer.parseInt(mainEntry.getKey()));
			}
			Collections.sort(sortList);

			XmlSuite xmlTestSuite = new XmlSuite();
			xmlTestSuite.setName("Regression Suite");
			xmlTestSuite.addListener("com.hqnRegression.util.TestListener");
			xmlTestSuite
					.addListener("atu.testng.reports.listeners.ATUReportsListener");
			xmlTestSuite
					.addListener("atu.testng.reports.listeners.ConfigurationListener");
			xmlTestSuite
					.addListener("atu.testng.reports.listeners.MethodListener");

			for (int keyValue : sortList) {
				Map<String, String> innerMap = dataMap.get("" + keyValue);
				XmlTest xmlTest = new XmlTest(xmlTestSuite);
				xmlTest.setName(innerMap.get("tcNo"));
				xmlTest.setParameters(innerMap);
				logger.info("TestCase NO: " + keyValue
						+ " --Executable ClassName: "
						+ innerMap.get("className"));
				logger.info("XML Test " + innerMap);
				if (innerMap.get("className") != null) {
					List<XmlClass> testClases = new ArrayList<XmlClass>();
					testClases.add(new XmlClass(innerMap.get("className")));
					xmlTest.setXmlClasses(testClases);
				} else {
					System.out
							.println("CLASS NAME DETAILS NOT MENTIONED IN SHEET");
				}
			}
			logger.info("xmlTestSuite:" + xmlTestSuite);

			// OrderAndKCI's Status Check
			//checkOrderAndKCIStatus(xmlTestSuite);

			// TestNG xml Creation at runtime.
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(xmlTestSuite);
			logger.info(suites.size());
			TestNG tng = new TestNG();
			tng.setXmlSuites(suites);
			tng.run();
			try {
				staticDetailsProp = CommonMethods.readPuttyConnectionDetails();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PuttyConn.disconnectSession(staticDetailsProp
					.getProperty("puttyUser"), staticDetailsProp
					.getProperty("puttyHost"), Integer
					.parseInt(staticDetailsProp.getProperty("puttyLocalPort")));

		} else {
			logger.info("NO Data Read from regression pack");
		}

	}

	// OrderAndKCI's Status Check
	private static void checkOrderAndKCIStatus(XmlSuite xmlTestSuite) {

		XmlTest xmlTest = new XmlTest(xmlTestSuite);
		xmlTest.setName("OrderAndKCIStatus");
		List<XmlClass> testClases = new ArrayList<XmlClass>();
		testClases.add(new XmlClass("com.bt.wlms.db.OrderAndKCIStatusCheck"));
		xmlTest.setXmlClasses(testClases);
	}
}
