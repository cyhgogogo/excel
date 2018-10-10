package com.excel.test;

import com.excel.dao.Impl.UserInfoDaoImpl;
import com.excel.dao.UserInfoDao;
import com.excel.pojo.T0Register;
import com.excel.pojo.T3_T4;
import com.excel.service.SaveService;
import com.excel.service.impl.SaveServiceImpl;
import org.junit.Test;

public class ExcelXlsxTest {

    @Test
    public void test() {
        T0Register t0Register = new T0Register();
        String[][] register = new String[15][8];
        register[0][0] = "8000";
        register[2][0] = "";
        register[6][0] = "√";
        register[7][0] = "√";
        register[8][0] = "250";
        register[9][0] = "120";
        register[10][0] = "130";
        register[11][0] = "140";
        register[12][0] = "150";
        t0Register.setRegister(register);

        String[][][] loadService = new String[8][4][9];
        loadService[0][0][0] = "6000";
        loadService[0][0][1] = "2";

        loadService[0][0][5] = "6000";
        loadService[0][0][6] = "浮动";
        loadService[2][0][0] = "6000";
        loadService[2][0][1] = "2";
        loadService[2][0][2] = "0.05";
        loadService[2][0][3] = "信用";
        loadService[2][0][4] = "AA";
        loadService[2][0][5] = "6000";
        loadService[7][0][6] = "浮动";
        t0Register.setLoadService(loadService);

        String[][] markert = new String[8][5];
        markert[0][0] = "0.01";
        markert[1][1] = "0.04";
        markert[2][2] = "0.04";
        markert[3][3] = "B及B以下";
        markert[4][4] = "C及C以下";
        t0Register.setMarket(markert);

        String[][][] repositService = new String[8][4][6];
        repositService[0][0][0] = "8980";
        repositService[0][0][1] = "1";
        repositService[1][0][2] = "0.05";
        repositService[2][0][3] = "柜台";
        repositService[7][0][4] = "固定";
        t0Register.setRepositService(repositService);


        T3_T4 t3_t4 = new T3_T4();
        String[][] outBank = new String[12][7];
        outBank[0][0] = "1456";
        outBank[0][1] = "2";
        outBank[0][2] = "0.05";
        t3_t4.setOutBank(outBank);

        String[][] nationalDebtTrade = new String[8][14];
        nationalDebtTrade[0][0] = "45754";
        nationalDebtTrade[0][1] = "2";
        nationalDebtTrade[0][2] = "2";
        nationalDebtTrade[0][3] = "0";
        nationalDebtTrade[0][4] = "123";
        nationalDebtTrade[0][5] = "23";
        t3_t4.setNationalDebtTrade(nationalDebtTrade);

        String[][] nationalDebtPurchase = new String[8][12];
        for (int x = 0; x < nationalDebtPurchase.length; x++) {
            for (int i = 0; i < nationalDebtPurchase[x].length; i++) {
                nationalDebtPurchase[x][i] = "";
            }
        }
        nationalDebtPurchase[0][0] = "2345";
        nationalDebtPurchase[4][1] = "1";
        nationalDebtPurchase[5][0] = "5";
        nationalDebtPurchase[6][3] = "2";
        nationalDebtPurchase[7][5] = "5";

        t3_t4.setNationalDebtPurchase(nationalDebtPurchase);

        String[][] investProfit = new String[8][4];
        investProfit[0][0] = "123";
        investProfit[3][1] = "1";
        investProfit[4][2] = "432";
        investProfit[5][3] = "65";
        t3_t4.setInvestProfit(investProfit);


        SaveService saveService = new SaveServiceImpl();
        saveService.setExcelXlsx(t0Register, t3_t4, "1515");

    }

    @Test
    public void test2() {
        SaveService saveService = new SaveServiceImpl();
        T0Register t0Register = saveService.getExcelT0Register("1515");
        T3_T4 t3_t4 = saveService.getExcelT3_T4("1515");

        String[][] register = t0Register.getRegister();
        for (int i = 0; i < register.length; i++) {
            for (int s = 0; s < register[i].length; s++) {
                if (register[i][s].equals("")) {
                    System.out.print(" * ");
                } else {
                    System.out.print(register[i][s] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("========================================================");

        String[][] market = t0Register.getMarket();
        for (int i = 0; i < market.length; i++) {
            for (int s = 0; s < market[i].length; s++) {
                if (market[i][s].equals("")) {
                    System.out.print(" * ");
                } else {
                    System.out.print(market[i][s] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("========================================================");

        String[][][] repositService = t0Register.getRepositService();
        for (int i = 0; i < repositService.length; i++) {
            for (int s = 0; s < repositService[i].length; s++) {
                for (int x = 0; x < repositService[i][s].length; x++) {
                    if (repositService[i][s][x].equals("")) {
                        System.out.print(" * ");
                    } else {
                        System.out.print(repositService[i][s][x] + " ");
                    }
                }
                System.out.println();

            }

        }
        System.out.println("========================================================");

        String[][][] loadService = t0Register.getLoadService();
        for (int i = 0; i < loadService.length; i++) {
            for (int s = 0; s < loadService[i].length; s++) {
                for (int x = 0; x < loadService[i][s].length; x++) {
                    if (loadService[i][s][x].equals("")) {
                        System.out.print(" * ");
                    } else {
                        System.out.print(loadService[i][s][x] + " ");
                    }
                }
                System.out.println();

            }

        }
        System.out.println("========================================================");

        String[][] interBank = t3_t4.getInterBank();
        for (int i = 0; i < interBank.length; i++) {
            for (int x = 0; x < interBank[i].length; x++) {
                if (interBank[i][x].equals("")) {
                    System.out.print(" * ");
                } else {
                    System.out.print(interBank[i][x] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("========================================================");

        String[][] nationalDebtTrade = t3_t4.getNationalDebtTrade();
        for (int i = 0; i < nationalDebtTrade.length; i++) {
            for (int x = 0; x < nationalDebtTrade[i].length; x++) {
                if (nationalDebtTrade[i][x].equals("")) {
                    System.out.print(" * ");
                } else {
                    System.out.print(nationalDebtTrade[i][x] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("========================================================");

        String[][] nationalDebtPurchase = t3_t4.getNationalDebtPurchase();
        for (int i = 0; i < nationalDebtPurchase.length; i++) {
            for (int x = 0; x < nationalDebtPurchase[i].length; x++) {
                if (nationalDebtPurchase[i][x].equals("")) {
                    System.out.print(" * ");
                } else {
                    System.out.print(nationalDebtPurchase[i][x] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("========================================================");

        String[][] investProfit = t3_t4.getInvestProfit();
        for (int i = 0; i < investProfit.length; i++) {
            for (int x = 0; x < investProfit[i].length; x++) {
                if (investProfit[i][x].equals("")) {
                    System.out.print(" * ");
                } else {
                    System.out.print(investProfit[i][x] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("========================================================");


        System.out.println(Float.parseFloat(null));
    }

    @Test
    public void test3() {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        System.out.println(userInfoDao.selectForbidLogin());
        ;
    }

}