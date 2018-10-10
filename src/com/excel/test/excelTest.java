package com.excel.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class excelTest {
    /**
     * Excel文件的存放位置。注意是正斜线
     */
    public static String fileToBeRead = "C:/Users/C_hao/Desktop/2.xlsx";

    public static void main(String[] args) throws IOException {
        InputStream stream = new FileInputStream(fileToBeRead);
        //这是对于xlsx对象得读取
        Workbook wb = new XSSFWorkbook(stream);
//        Sheet sheet=wb.getSheet("T0登记表");
//        sheet.getSheetName();
//        //这样的构造方法可以直接拿到对应行和列下标
//        CellAddress address = new CellAddress("D11");
//        Cell cell=sheet.getRow(address.getRow()).getCell(address.getColumn());
//        //设置单元格类型
//        cell.setCellType(Cell.CELL_TYPE_STRING);
//        //对某个单元格进行修改
//        cell.setCellValue("");
//        address = new CellAddress("B25");
//        cell=sheet.getRow(address.getRow()).getCell(address.getColumn());
//        //设置单元格类型
//        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//        //对某个单元格进行修改
//        cell.setCellValue(9000);
//        //保存
//        FileOutputStream fileOut = new FileOutputStream(fileToBeRead);
//        //设置强制重新计算，可能会失效
//        wb.setForceFormulaRecalculation(true);
//        wb.write(fileOut);
//

        stream = new FileInputStream(fileToBeRead);
        wb = new XSSFWorkbook(stream);
        Sheet sheet = wb.getSheet("T10损益表");
        CellAddress address2 = new CellAddress("C27");
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int s = 0; s < sheet.getRow(i).getLastCellNum(); s++) {
                Cell cell2 = sheet.getRow(i).getCell(address2.getColumn());
                //刚写入的数据无法及时更新，需要人为打开WPS或者Office才能更新
                //使用evaluateFormulaCell对函数单元格进行强行更新计算
                XSSFFormulaEvaluator eval = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                eval.evaluateFormulaCell(cell2);
                cell2.setCellType(Cell.CELL_TYPE_STRING);
                System.out.println(cell2.getStringCellValue());
            }
        }
        Row row2 = sheet.getRow(address2.getRow());
        Cell cell2 = row2.getCell(address2.getColumn());
        //刚写入的数据无法及时更新，需要人为打开WPS或者Office才能更新
        //使用evaluateFormulaCell对函数单元格进行强行更新计算
        XSSFFormulaEvaluator eval = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
        eval.evaluateFormulaCell(cell2);
        cell2.setCellType(Cell.CELL_TYPE_STRING);
        System.out.println(cell2.getStringCellValue());
    }


}