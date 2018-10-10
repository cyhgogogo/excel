package com.excel.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelXlsx {
    Workbook wb;
    XSSFFormulaEvaluator eval = null;

    public ExcelXlsx(String fileName) {

        InputStream stream = null;
        try {
            stream = new FileInputStream(fileName);
            //这是对于xlsx对象得读取
            wb = new XSSFWorkbook(stream);
            //刚写入的数据无法及时更新，需要人为打开WPS或者Office才能更新
            // 对函数单元格进行强行更新计算
            eval = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Cell getCell(String sheetName, int row, int column) {
        Sheet sheet = wb.getSheet(sheetName);
        sheet.getSheetName();
        Cell cell = sheet.getRow(row).getCell(column);

        if (cell != null)
            try {
                eval.evaluateFormulaCell(cell);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return cell;
    }

    public void setCell(String sheetName, int type, int row, int column, Object value) {
        Sheet sheet = wb.getSheet(sheetName);
        sheet.getSheetName();
        Cell cell = sheet.getRow(row).getCell(column);
        cell.setCellType(type);
        if (type == Cell.CELL_TYPE_STRING) {
            cell.setCellValue((String) value);
        } else if (type == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellValue((double) value);
        } else if (type == Cell.CELL_TYPE_BOOLEAN) {
            cell.setCellValue((boolean) value);
        }
    }

    public void saveExcelXlsx(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();//创建文件夹
        }
        //保存
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void produceXls(String filePathXls, String xlsModer) {
        InputStream stream = null;
        Workbook wb2 = null;

        try {
            stream = new FileInputStream(xlsModer);
            //这是对于xlsx对象得读取
            wb2 = new HSSFWorkbook(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //刚写入的数据无法及时更新，需要人为打开WPS或者Office才能更新
        //使用evaluateFormulaCell对函数单元格进行强行更新计算
        XSSFFormulaEvaluator eval = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            Sheet sheet2 = wb2.getSheetAt(i);
            for (int s = 0; s < sheet.getLastRowNum(); s++) {

                Row row = sheet.getRow(s);
                Row row2 = sheet2.getRow(s);
                if (row != null && row2 != null) {
                    for (int x = 0; x < row.getLastCellNum(); x++) {
                        Cell cell = row.getCell(x);
                        Cell cell2 = row2.getCell(x);
                        if (cell != null && cell2 != null) {
                            try {
                                eval.evaluateFormulaCell(cell);
                            } catch (Exception e) {
                                System.out.println(sheet.getSheetName() + " 存在单元格格式错误");
                            }
                            try {
                                cell2.setCellValue(cell.getStringCellValue());
                            } catch (Exception e) {
                                try {
                                    cell2.setCellValue(cell.getNumericCellValue());
                                } catch (Exception e2) {
                                    cell2.setCellValue("");
                                }
                            }
                        }

                    }
                }

            }
        }
        //保存
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filePathXls);
            wb2.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getHtml(String excelFilePath, String sheetName) throws Exception {

        String path = excelFilePath;

        File sourcefile = new File(path);

        InputStream is = new FileInputStream(sourcefile);

        String html = ExcelToHtml.getHtml(wb, sheetName);
        is.close();
        return html;
    }
}