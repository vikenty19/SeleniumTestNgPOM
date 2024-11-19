package com.tutorialsninja.qa.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

public class Utilities {
 //public static    XSSFWorkbook workbook;
    public static String generateRandomEmail(){
        Date date = new Date();
        String addedTimeStamp = date.toString().replace(":","_").substring(11,19);
        return "amotoori" + addedTimeStamp+"@gmail.com";

    }
public static Object[][] getTestDataFromExcel(String sheetName) {
     XSSFWorkbook workbook = null;

    File file = new File(System.getProperty("user.dir") +
            "/src/main/java/testData/TutorialNinjaTestData.xlsx");



    try{
        FileInputStream fisData = new FileInputStream(file);
        workbook = new XSSFWorkbook(fisData);
    }catch (Throwable e){
        e.printStackTrace();
    }
    XSSFSheet sheet = workbook.getSheet(sheetName);
    // define number of rows and columns
    int rows= sheet.getLastRowNum();
    int columns = sheet.getRow(0).getLastCellNum();
    //create object of returning data
    Object[][] dataKit = new Object[rows][columns];
    for (int i = 0; i <rows ; i++) {
        //pick the row
        XSSFRow row =sheet.getRow(i+1);//i+1 because 1=0 => title of table
        for (int j = 0; j <columns ; j++) {
            XSSFCell cell = row.getCell(j);
            //define which type of data is in the cell
            CellType cellType = cell.getCellType();
            switch (cellType){
                case STRING:
                    dataKit[i][j] = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    dataKit[i][j] =Integer.toString((int)cell.getNumericCellValue());//(int) needs to get integer not float
                    break;            // integer.tpString - handle with String format
                case BOOLEAN:
                    dataKit[i][j] = cell.getBooleanCellValue();
                    break;
            }

        }

    }
            return dataKit;

}

}
