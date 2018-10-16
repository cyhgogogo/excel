package com.excel.service.impl;
import com.excel.pojo.T0Register;
import com.excel.pojo.T1RepositInterest;
import com.excel.pojo.T3_T4;
import com.excel.service.SaveService;
import com.excel.util.ExcelXlsx;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.*;

@Service(value = "saveService")
public class SaveServiceImpl implements SaveService {
    //模板
    static String fileModel = "config/model.xlsx";
    static String XlsModel = "config/xlsmodel.xls";
    DecimalFormat format = new DecimalFormat("#0.####");

    public void setExcelXlsx(T0Register t0Register, T3_T4 t3_t4, String userName) {
        String fileName = "data/" + userName + "/erp.xlsx";
        ExcelXlsx excelXlsx=null;
        File file = new File(fileName);
        if (!file.exists()) {
            excelXlsx = new ExcelXlsx(fileModel);
        }
        else
        {
            excelXlsx = new ExcelXlsx(fileName);
        }


        CountDownLatch c=new CountDownLatch(9);//创建线程计数器
        ExcelXlsx finalExcelXlsx = excelXlsx;//防止引用更改
        if (t0Register != null) {
            //登记表
            String[][] register = t0Register.getRegister();

            //市场信息
            String[][] market = t0Register.getMarket();
            //存款业务信息登记
            String[][][] repositService = t0Register.getRepositService();
            //贷款业务信息登记表
            String[][][] loadService = t0Register.getLoadService();



            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (register != null) {
                        int row = 3;
                        for (int i = 0; i < register.length; i++) {
                            int column = 3;
                            for (int s = 0; s < register[0].length; s++) {
                                Cell cell = finalExcelXlsx.getCell("T0登记表", row + i, column + s);
                                //设置单元格类型
                                if (register[i][s].equals("")) {
                                    register[i][s] = null;
                                }
                                try {
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);

                                    cell.setCellValue(Float.parseFloat(register[i][s]));
                                } catch (Exception e) {
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(register[i][s]);
                                }


                            }
                        }
                    }
                    c.countDown();//计数器减1
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (market != null) {
                        int row = 5;
                        for (int i = 0; i < market.length; i++) {
                            int column = 13;
                            for (int s = 0; s < market[0].length; s++) {
                                Cell cell = finalExcelXlsx.getCell("T0登记表", row + i, column + s);
                                //设置单元格类型
                                if (market[i][s].equals("")) {
                                    market[i][s] = null;
                                }
                                try {
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Float.parseFloat(market[i][s]));
                                } catch (Exception e) {
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(market[i][s]);
                                }

                            }
                        }
                    }
                    c.countDown();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (repositService != null) {
                        int row = 24;
                        for (int x = 0; x < repositService.length; x++) {
                            int temp = 4;
                            if (x == 0) {
                                temp = 2;
                            }

                            for (int i = 0; i < temp; i++) {
                                int column = 1;
                                for (int s = 0; s < repositService[0][0].length; s++) {
                                    Cell cell = null;
                                    if (x == 0) {
                                        cell = finalExcelXlsx.getCell("T0登记表", row + i + 2 * x, column + s);
                                    } else if (x == 1) {
                                        cell = finalExcelXlsx.getCell("T0登记表", row + i + 2 * x + 2, column + s);
                                    } else {
                                        cell = finalExcelXlsx.getCell("T0登记表", row + i + 2 * x + 2 + 4 * (x - 1), column + s);
                                    }
                                    if (repositService[x][i][s].equals("")) {
                                        repositService[x][i][s] = null;
                                    }
                                    //设置单元格类型
                                    try {
                                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                        cell.setCellValue(Float.parseFloat(repositService[x][i][s]));
                                    } catch (Exception e) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cell.setCellValue(repositService[x][i][s]);
                                    }

                                }
                            }
                        }
                    }
                    c.countDown();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (loadService != null) {
                        int row = 24;
                        for (int x = 0; x < loadService.length; x++) {
                            int temp = 4;
                            if (x == 0) {
                                temp = 2;
                            }

                            for (int i = 0; i < temp; i++) {
                                int column = 8;
                                for (int s = 0; s < 9; s++) {
                                    if (s == 7) {
                                        continue;
                                    }
                                    Cell cell = null;
                                    if (x == 0) {
                                        cell = finalExcelXlsx.getCell("T0登记表", row + i + 2 * x, column + s);

                                    } else if (x == 1) {
                                        cell = finalExcelXlsx.getCell("T0登记表", row + i + 2 * x + 2, column + s);
                                    } else {
                                        cell = finalExcelXlsx.getCell("T0登记表", row + i + 2 * x + 2 + 4 * (x - 1), column + s);
                                    }
                                    //设置单元格类型
                                    if (loadService[x][i][s].equals("")) {
                                        loadService[x][i][s] = null;
                                    }
                                    try {
                                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                        cell.setCellValue(Float.parseFloat(loadService[x][i][s]));
                                    } catch (Exception e) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cell.setCellValue(loadService[x][i][s]);
                                    }

                                }
                            }

                        }
                    }
                    c.countDown();
                }
            }).start();


        }
        else
        {
            c.countDown();
            c.countDown();
            c.countDown();
            c.countDown();
        }

        if (t3_t4 != null) {
            String[][] interBank = t3_t4.getInterBank();
            String[][] investProfit = t3_t4.getInvestProfit();
            String[][] nationalDebtPurchase = t3_t4.getNationalDebtPurchase();
            String[][] nationalDebtTrade = t3_t4.getNationalDebtTrade();
            String[][] outBank = t3_t4.getOutBank();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (nationalDebtPurchase != null) {
                        int row = 4;
                        for (int i = 0; i < nationalDebtPurchase.length; i++) {
                            int column = 2;
                            for (int s = 0; s < 2; s++) {
                                Cell cell = finalExcelXlsx.getCell("T3-T4", row + i, column + s);
                                //设置单元格类型
                                if (nationalDebtPurchase[i][s].equals("")) {
                                    nationalDebtPurchase[i][s] = null;
                                }
                                try {
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Float.parseFloat(nationalDebtPurchase[i][s]));
                                } catch (Exception e) {
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(nationalDebtPurchase[i][s]);
                                }
                            }
                        }
                    }
                    c.countDown();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (nationalDebtTrade != null) {
                        int row = 16;
                        for (int i = 0; i < nationalDebtTrade.length; i++) {
                            int column = 1;
                            for (int s = 0; s < nationalDebtTrade[0].length; s++) {
                                if (s < 4 || s == 12) {
                                    Cell cell = finalExcelXlsx.getCell("T3-T4", row + i, column + s);
                                    if (nationalDebtTrade[i][s].equals("")) {
                                        nationalDebtTrade[i][s] = null;
                                    }
                                    //设置单元格类型
                                    try {
                                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                        cell.setCellValue(Float.parseFloat(nationalDebtTrade[i][s]));
                                    } catch (Exception e) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cell.setCellValue(nationalDebtTrade[i][s]);
                                    }
                                }
                            }

                        }
                    }
                    c.countDown();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (interBank != null) {
                        int row = 5;
                        for (int i = 0; i < interBank.length; i++) {
                            int column = 16;
                            for (int s = 0; s < interBank[0].length; s++) {

                                if (s == 21) {
                                    continue;
                                }
                                Cell cell = finalExcelXlsx.getCell("T3-T4", row + i, column + s);
                                if (interBank[i][s].equals("")) {
                                    interBank[i][s] = null;
                                }
                                //设置单元格类型
                                try {
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Float.parseFloat(interBank[i][s]));
                                } catch (Exception e) {
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(interBank[i][s]);
                                }


                            }
                        }
                    }
                    c.countDown();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (outBank != null) {
                        int row = 19;
                        for (int i = 0; i < outBank.length; i++) {
                            int column = 16;
                            for (int s = 0; s < outBank[0].length; s++) {
                                if (s == 21) {
                                    continue;
                                }
                                Cell cell = finalExcelXlsx.getCell("T3-T4", row + i, column + s);
                                if (outBank[i][s].equals("")) {
                                    outBank[i][s] = null;
                                }
                                //设置单元格类型
                                try {
                                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(Float.parseFloat(outBank[i][s]));
                                } catch (Exception e) {
                                    cell.setCellType(Cell.CELL_TYPE_STRING);
                                    cell.setCellValue(outBank[i][s]);
                                }
                            }
                        }
                    }
                    c.countDown();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (investProfit != null) {
                        int row = 4;
                        for (int i = 0; i < investProfit.length; i++) {
                            int column = 25;
                            for (int s = 0; s < 2; s++) {
                                Cell cell = finalExcelXlsx.getCell("T3-T4", row + i, column + s);
                                if (investProfit[i][s].equals("")) {
                                    investProfit[i][s] = null;
                                }
                                //设置单元格类型
                                if (cell != null) {
                                    try {
                                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                        cell.setCellValue(Float.parseFloat(investProfit[i][s]));
                                    } catch (Exception e) {
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        cell.setCellValue(investProfit[i][s]);
                                    }
                                }
                            }
                        }
                    }
                    c.countDown();
                }
            }).start();


        }
        else
        {
            c.countDown();
            c.countDown();
            c.countDown();
            c.countDown();
            c.countDown();
        }
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        excelXlsx.saveExcelXlsx(fileName);
    }

    @Override
    public T3_T4 getExcelT3_T4(String userName) {
        String fileName = "data/" + userName + "/erp.xlsx";
        File file = new File(fileName);
        if (!file.exists()) {
            setExcelXlsx(null, null, fileName);//模板
        }
        ExcelXlsx excelXlsx = new ExcelXlsx(fileName);
        T3_T4 t3_t4 = new T3_T4();
        String[][] interBank = new String[12][7];
        String[][] investProfit = new String[8][4];
        String[][] nationalDebtPurchase = new String[8][12];
        String[][] nationalDebtTrade = new String[8][14];
        String[][] outBank = new String[12][7];
        CountDownLatch c=new CountDownLatch(5);//创建线程计数器

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 4;
                for (int i = 0; i < nationalDebtPurchase.length; i++) {
                    int column = 2;
                    for (int s = 0; s < nationalDebtPurchase[0].length; s++) {
                        Cell cell = excelXlsx.getCell("T3-T4", row + i, column + s);
                        try {
                            nationalDebtPurchase[i][s] = cell.getStringCellValue();
                        } catch (Exception e) {
                            nationalDebtPurchase[i][s] = format.format(cell.getNumericCellValue()) ;
                        }
                        if (nationalDebtPurchase[i][s] == null)
                            nationalDebtPurchase[i][s] = "";
                        if(s==2&&!nationalDebtPurchase[i][s].equals(""))
                        {
                            Double temp1=Double.parseDouble(nationalDebtPurchase[i][s]);
                            DecimalFormat df = new DecimalFormat("0.00");
                            nationalDebtPurchase[i][s]= df.format(temp1);
                        }

                    }
                }
                t3_t4.setNationalDebtPurchase(nationalDebtPurchase);
                c.countDown();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 16;
                for (int i = 0; i < nationalDebtTrade.length; i++) {
                    int column = 1;
                    for (int s = 0; s < nationalDebtTrade[0].length; s++) {
                        Cell cell = excelXlsx.getCell("T3-T4", row + i, column + s);
                        try {
                            nationalDebtTrade[i][s] = cell.getStringCellValue();

                        } catch (Exception e) {
                            nationalDebtTrade[i][s] = format.format(cell.getNumericCellValue()) + "";
                        }
                        if (nationalDebtTrade[i][s] == null)
                            nationalDebtTrade[i][s] = "";
               /* if(s==3&&!nationalDebtTrade[i][s].equals(""))
                {
                    Double temp1=Double.parseDouble(nationalDebtTrade[i][s]);
                    DecimalFormat df = new DecimalFormat("0.00");
                    nationalDebtTrade[i][s]= df.format(temp1);
                }*/


                    }
                }
                t3_t4.setNationalDebtTrade(nationalDebtTrade);
                c.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 5;
                for (int i = 0; i < interBank.length; i++) {
                    int column = 16;
                    for (int s = 0; s < interBank[0].length; s++) {
                        Cell cell = excelXlsx.getCell("T3-T4", row + i, column + s);
                        try {
                            interBank[i][s] = cell.getStringCellValue();
                        } catch (Exception e) {

                            interBank[i][s] = format.format(cell.getNumericCellValue()) + "";
                        }
                        if (interBank[i][s] == null)
                            interBank[i][s] = "";
                /*if(s>=2&&s<=3&&!interBank[i][s].equals(""))
                {
                    Double temp1=Double.parseDouble(interBank[i][s]);
                    DecimalFormat df = new DecimalFormat("0.0000");
                    interBank[i][s]= df.format(temp1);
                }*/

                    }
                }
                t3_t4.setInterBank(interBank);
                c.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 19;
                for (int i = 0; i < outBank.length; i++) {
                    int column = 16;
                    for (int s = 0; s < outBank[0].length; s++) {
                        Cell cell = excelXlsx.getCell("T3-T4", row + i, column + s);
                        try {
                            outBank[i][s] = cell.getStringCellValue();
                        } catch (Exception e) {

                            outBank[i][s] = format.format(cell.getNumericCellValue()) + "";
                        }
                        if (outBank[i][s] == null)
                            outBank[i][s] = "";
               /* if(s>=2&&s<=3&&!outBank[i][s].equals(""))
                {
                    Double temp1=Double.parseDouble(outBank[i][s]);
                    DecimalFormat df = new DecimalFormat("0.0000");
                    outBank[i][s]= df.format(temp1);
                }*/
                    }
                }
                t3_t4.setOutBank(outBank);
                c.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 4;
                for (int i = 0; i < investProfit.length; i++) {
                    int column = 25;
                    for (int s = 0; s < investProfit[0].length; s++) {
                        Cell cell = excelXlsx.getCell("T3-T4", row + i, column + s);
                        try {
                            investProfit[i][s] = cell.getStringCellValue();
                        } catch (Exception e) {

                            investProfit[i][s] = format.format(cell.getNumericCellValue()) + "";
                        }
                        if (investProfit[i][s] == null)
                            investProfit[i][s] = "";
              /*  if(s==2&&!investProfit[i][s].equals(""))
                {
                    Double temp1=Double.parseDouble(investProfit[i][s]);
                    DecimalFormat df = new DecimalFormat("0.00");
                    investProfit[i][s]= df.format(temp1);
                }*/
                    }
                }
                t3_t4.setInvestProfit(investProfit);
                c.countDown();
            }
        }).start();
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return t3_t4;
    }

    @Override
    public T0Register getExcelT0Register(String userName) {
        String fileName = "data/" + userName + "/erp.xlsx";
        File file = new File(fileName);
        if (!file.exists()) {
            setExcelXlsx(null, null, userName);//模板
        }
        ExcelXlsx excelXlsx = new ExcelXlsx(fileName);
        CountDownLatch c=new CountDownLatch(4);//创建线程计数器
        T0Register t0Register = new T0Register();
        //登记表
        String[][] register = new String[15][8];

        //市场信息
        String[][] market = new String[8][5];
        //存款业务信息登记
//        String[][][] repositService = new String[8][4][6];
//        //贷款业务信息登记表
//        String[][][] loadService = new String[8][4][14];
        String[][][] repositService = new String[8][][];
        //贷款业务信息登记表
        String[][][] loadService = new String[8][][];

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 3;
                for (int i = 0; i < register.length; i++) {
                    int column = 3;
                    for (int s = 0; s < register[0].length; s++) {
                        Cell cell = excelXlsx.getCell("T0登记表", row + i, column + s);
                        try {
                            register[i][s] = cell.getStringCellValue();
                        } catch (Exception e) {
                            register[i][s] = format.format(cell.getNumericCellValue()) + "";
                        }
                        if (register[i][s] == null)
                            register[i][s] = "";
                    }
                }
                t0Register.setRegister(register);
                c.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 5;

                for (int i = 0; i < market.length; i++) {
                    int column = 13;
                    for (int s = 0; s < market[0].length; s++) {
                        Cell cell = excelXlsx.getCell("T0登记表", row + i, column + s);
                        try {
                            market[i][s] = cell.getStringCellValue();

                        } catch (Exception e) {
                            market[i][s] = format.format(cell.getNumericCellValue()) + "";

                        }
                        if (market[i][s] == null)
                            market[i][s] = "";
              /*  if(s<=2&&!market[i][s].equals(""))
                {
                    Double temp1=Double.parseDouble(market[i][s]);
                    DecimalFormat df = new DecimalFormat("0.00");
                    market[i][s]= df.format(temp1);
                }*/
                    }
                }
                t0Register.setMarket(market);
                c.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 24;
                for (int x = 0; x < repositService.length; x++) {
                    int temp = 4;
                    if (x == 0) {
                        repositService[x] = new String[2][6];
                        temp = 2;
                    } else {
                        repositService[x] = new String[4][6];
                        temp = 4;
                    }

                    for (int i = 0; i < temp; i++) {
                        int column = 1;
                        for (int s = 0; s < repositService[0][0].length; s++) {
                            Cell cell = null;
                            if (x == 0) {
                                cell = excelXlsx.getCell("T0登记表", row + i + 2 * x, column + s);
                                try {
                                    repositService[x][i][s] = cell.getStringCellValue();

                                } catch (Exception e) {
                                    repositService[x][i][s] = format.format(cell.getNumericCellValue()) + "";
                                }

                            } else if (x == 1) {
                                cell = excelXlsx.getCell("T0登记表", row + i + 2 * x + 2, column + s);
                                try {
                                    repositService[x][i][s] = cell.getStringCellValue();
                                } catch (Exception e) {
                                    repositService[x][i][s] = format.format(cell.getNumericCellValue()) + "";
                                }
                            } else {
                                cell = excelXlsx.getCell("T0登记表", row + i + 2 * x + 2 + 4 * (x - 1), column + s);
                                try {
                                    repositService[x][i][s] = cell.getStringCellValue();
                                } catch (Exception e) {
                                    repositService[x][i][s] = format.format(cell.getNumericCellValue()) + "";
                                }
                            }
                            if (repositService[x][i][s] == null)
                                repositService[x][i][s] = "";
                   /* if(s==2&&!repositService[x][i][s].equals(""))
                    {
                        Double temp1=Double.parseDouble(repositService[x][i][s]);
                        DecimalFormat df = new DecimalFormat("0.00");
                        repositService[x][i][s]= df.format(temp1);
                    }*/
                        }
                    }
                }
                t0Register.setRepositService(repositService);
                c.countDown();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 24;
                for (int x = 0; x < loadService.length; x++) {
                    int temp = 4;
                    if (x == 0) {
                        loadService[x] = new String[2][12];
                        temp = 2;
                    } else {
                        loadService[x] = new String[4][12];
                        temp = 4;
                    }
                    for (int i = 0; i < temp; i++) {
                        int column = 8;
                        for (int s = 0; s < loadService[0][0].length; s++) {
                            Cell cell = null;
                            if (x == 0) {
                                if (s == 11) {
                                    cell = excelXlsx.getCell("T0登记表", row + i + 2 * x, column + s + 2);
                                    try {
                                        loadService[x][i][s] = cell.getStringCellValue();
                                    } catch (Exception e) {
                                        loadService[x][i][s] = format.format(cell.getNumericCellValue())+ "";
                                    }
                                } else {
                                    cell = excelXlsx.getCell("T0登记表", row + i + 2 * x, column + s);
                                    try {
                                        loadService[x][i][s] = cell.getStringCellValue();
                                    } catch (Exception e) {
                                        loadService[x][i][s] = format.format(cell.getNumericCellValue()) + "";
                                    }
                                }
                            } else if (x == 1) {
                                if (s == 11) {
                                    cell = excelXlsx.getCell("T0登记表", row + i + 2 * x + 2, column + s + 2);
                                    try {
                                        loadService[x][i][s] = cell.getStringCellValue();
                                    } catch (Exception e) {
                                        loadService[x][i][s] = format.format(cell.getNumericCellValue()) + "";
                                    }
                                } else {
                                    cell = excelXlsx.getCell("T0登记表", row + i + 2 * x + 2, column + s);
                                    try {
                                        loadService[x][i][s] = cell.getStringCellValue();
                                    } catch (Exception e) {
                                        loadService[x][i][s] = format.format(cell.getNumericCellValue())+ "";
                                    }
                                }
                            } else {
                                if (s == 11) {
                                    cell = excelXlsx.getCell("T0登记表", row + i + 2 * x + 2 + 4 * (x - 1), column + s + 2);
                                    try {
                                        loadService[x][i][s] = cell.getStringCellValue();
                                    } catch (Exception e) {
                                        loadService[x][i][s] = format.format(cell.getNumericCellValue())+ "";
                                    }
                                } else {
                                    cell = excelXlsx.getCell("T0登记表", row + i + 2 * x + 2 + 4 * (x - 1), column + s);
                                    try {
                                        loadService[x][i][s] = cell.getStringCellValue();
                                    } catch (Exception e) {
                                        loadService[x][i][s] = format.format(cell.getNumericCellValue()) + "";
                                    }
                                }
                            }
                            if (loadService[x][i][s] == null)
                                loadService[x][i][s] = "";
                  /*  if(s==1&&!loadService[x][i][s].equals(""))
                    {
                        Double temp1=Double.parseDouble(loadService[x][i][s]);
                        DecimalFormat df = new DecimalFormat("0");
                        loadService[x][i][s]= df.format(temp1);
                    }*/
                    /*if(s==2&&!loadService[x][i][s].equals(""))
                    {
                        Double temp1=Double.parseDouble(loadService[x][i][s]);
                        DecimalFormat df = new DecimalFormat("0.00");
                        loadService[x][i][s]= df.format(temp1);
                    }*/

                        }
                    }

                }
                t0Register.setLoadService(loadService);
                c.countDown();
            }
        }).start();
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return t0Register;
    }

//    public T1RepositInterest getExcelT1RepositInterest(String userName) {
//        String fileName = "data/" + userName + "/erp.xlsx";
//        File file = new File(fileName);
//        if (!file.exists()) {
//            setExcelXlsx(null, null, fileName);//模板
//        }
//        ExcelXlsx excelXlsx = new ExcelXlsx(fileName);
//        //T1.1 固定存款利息支出损益
//        String[][][] fixedrepositInterestOutput = new String[8][][];
//        //T1.1 浮动存款利息支出损益
//        String[][][] floatrepositInterestOutput = new String[8][][];
//        //T1.1 利率浮动值
//        String[][] interestFloat1 = new String[8][8];
//        //T1.2 利率浮动值
//        String[][] interestFloat2 = new String[7][7];
//        //T1.2  存款利息支出现金流 固定部分
//        String[][][] repositInterestOutput1 = new String[8][][];
//        //T1.2  存款利息支出现金流 浮动部分
//        String[][][] repositInterestOutput2 = new String[8][][];
//
//
//        int row = 6;
//        for (int x = 0; x < fixedrepositInterestOutput.length; x++) {
//            int temp;
//            if (x == 0) {
//                fixedrepositInterestOutput[x] = new String[2][13];
//                temp = 2;
//            } else {
//                fixedrepositInterestOutput[x] = new String[4][13];
//                temp = 4;
//            }
//            for (int i = 0; i < temp; i++) {
//                int column = 2;
//                for (int s = 0; s < 13; s++) {
//                    Cell cell;
//                    if (x == 0) {
//                        cell = excelXlsx.getCell("T1存款利息表", row + i + 2 * x, column + s);
//                        fixedrepositInterestOutput[x][i][s] = cell.getStringCellValue();
//                    } else if (x == 1) {
//                        cell = excelXlsx.getCell("T1存款利息表", row + i + 3 * x + 4, column + s);
//                        fixedrepositInterestOutput[x][i][s] = cell.getStringCellValue();
//                    } else {
//                        cell = excelXlsx.getCell("T1存款利息表", row + i + 3 * x + 4 + 8 * (x - 1), column + s);
//                        fixedrepositInterestOutput[x][i][s] = cell.getStringCellValue();
//                    }
//                }
//            }
//        }
//        t1RepositInterest.setFixedrepositInterestOutput(fixedrepositInterestOutput);
//
//        row = 9;
//        for (int x = 0; x < floatrepositInterestOutput.length; x++) {
//            int temp;
//            if (x == 0) {
//                floatrepositInterestOutput[x] = new String[2][13];
//                temp = 2;
//            } else {
//                floatrepositInterestOutput[x] = new String[4][13];
//                temp = 4;
//            }
//            for (int i = 0; i < temp; i++) {
//                int column = 2;
//                for (int s = 0; s < 13; s++) {
//                    Cell cell;
//                    if (x == 0) {
//                        cell = excelXlsx.getCell("T1存款利息表", row + i + 2 * x, column + s);
//                        floatrepositInterestOutput[x][i][s] = cell.getStringCellValue();
//                    } else if (x == 1) {
//                        cell = excelXlsx.getCell("T1存款利息表", row + i + 3 * x + 6, column + s);
//                        floatrepositInterestOutput[x][i][s] = cell.getStringCellValue();
//                    } else {
//                        cell = excelXlsx.getCell("T1存款利息表", row + i + 3 * x + 6 + 8 * (x - 1), column + s);
//                        floatrepositInterestOutput[x][i][s] = cell.getStringCellValue();
//                    }
//                    if (floatrepositInterestOutput[x][i][s] == null)
//                        floatrepositInterestOutput[x][i][s] = "";
//                }
//            }
//        }
//        t1RepositInterest.setFloatrepositInterestOutput(floatrepositInterestOutput);
//
//        return t1RepositInterest;
//    }

    @Override
    public String getSheetHtml(String userName, String sheetName) {
        String fileName = "data/" + userName + "/erp.xlsx";
        ExcelXlsx excelXlsx=null;
        File file = new File(fileName);
        if (!file.exists()) {
            excelXlsx = new ExcelXlsx(fileModel);
            excelXlsx.saveExcelXlsx(fileName);
        }
        else
        {
            excelXlsx = new ExcelXlsx(fileName);
        }

        //生成一个清除公式的xls文件
//        excelXlsx.produceXls("data/" + userName + "/erp.xls", XlsModel);
        String content = null;
        try {
            //从xls文件中得到内容
            //content = excelXlsx.getHtml("data/" + userName + "/erp.xls", sheetName);

            //从xlsx文件中得到内容
            content = excelXlsx.getHtml("data/" + userName + "/erp.xlsx", sheetName);

            String path = "data/" + userName + "/" + sheetName + ".html";
            FileWriter fw = new FileWriter(path);
            fw.write(content);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}

