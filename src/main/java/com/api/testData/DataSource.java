package com.api.testData;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataSource {
	private static final String filePath = "./src/main/resources/datasource.xlsx";
	// private static final String sheetName = "sheet1";
	private static FileInputStream filestream;
	private static Workbook workbook;
	private static Sheet sheet;
	private static DataSource dataSource;
	private static HashMap<String, Integer> testCase;

	public static DataSource getInstance() {
		if (dataSource == null) {
			synchronized (DataSource.class) {
				if (dataSource == null) {
					dataSource = new DataSource();
					try {
						filestream = new FileInputStream(filePath);
						workbook = WorkbookFactory.create(filestream);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sheet = workbook.getSheetAt(0);
					testCase = new HashMap<String, Integer>();
					loadTestCase(sheet, testCase);
				}
			}
		}
		return dataSource;
	}
	//load all test cases from the excel 
	private static void loadTestCase(Sheet sheet, HashMap<String, Integer> testCase) {
		// getLastRowNUm() is 1-based indexing
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			String tc = sheet.getRow(i).getCell(0).toString();
			testCase.put(tc, i);
		}
	}

	public HashMap<String, String> getTestData(String testCaseName) {
		if (testCase.containsKey(testCaseName) == false)
			throw new IllegalArgumentException(testCaseName + " is not a valid test case name");
		int row = testCase.get(testCaseName);
		HashMap<String, String> testData = new HashMap<>();
		// getLastCellNum() is 0-based indexing
		for (int i = 1; i < sheet.getRow(0).getLastCellNum(); i++) {
			//get key,value pair for the testcase
			String key = getCellValueAsString(sheet.getRow(0).getCell(i));
			String value = getCellValueAsString(sheet.getRow(row).getCell(i));
			testData.put(key, value);
		}
		return testData;
	}

	private static String getCellValueAsString(Cell cell) {
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();

		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString(); // or use a custom date format
			} else {
				return getNumbericValueAsString(cell);
			}

		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());

		case FORMULA:
			// Evaluate formula and return result as string
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
			CellValue cellValue = evaluator.evaluate(cell);
			//find the result is which type once figure out get the value from excel
			//instead of the evaluate cellValue, to maintain consistency with excel data
			switch (cellValue.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				return getNumbericValueAsString(cell);
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			default:
				return "";
			}

		case BLANK:
			return "";

		default:
			throw new RuntimeException("Invalid Cell Type");
		}
	}
	
	
	private static String getNumbericValueAsString(Cell cell) {
		DecimalFormat plainFormat = new DecimalFormat("#"); // For integers (mobile numbers)
		DecimalFormat decimalFormat = new DecimalFormat("#.##"); // For decimals

		// Example: assume you already have cellValue
		    double num = cell.getNumericCellValue();

		    // Check if it's a whole number
		    if (num == Math.floor(num)) {
		        return plainFormat.format(num); // No decimals
		    } else {
		        return decimalFormat.format(num); // Keep 2 decimals
		    }
	}

}
