package com.excel.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public class ExcelToHtml {
    public static String getHtml(Workbook wb, String sheetName) throws Exception {
//		Workbook wb = WorkbookFactory.create(is);

		if (wb instanceof XSSFWorkbook) {
			XSSFWorkbook xWb = (XSSFWorkbook) wb;
			return POIReadExcelToHtml07.getExcelToHtml(xWb,sheetName);
		}else if(wb instanceof HSSFWorkbook){
			HSSFWorkbook hWb = (HSSFWorkbook) wb;
			return POIReadExcelToHtml03.getHtmlExcel(hWb,sheetName);
		}
//        Workbook wb = new HSSFWorkbook(is);
//        return POIReadExcelToHtml03.getHtmlExcel((HSSFWorkbook) wb, sheetName);
		return null;
    }
}
