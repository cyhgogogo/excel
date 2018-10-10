package com.excel.pojo;

//T3-T4
public class T3_T4 {
    //T3:国债购买记录
    private String[][] nationalDebtPurchase;
    //T3:国债交易记录表
    private String[][] nationalDebtTrade;
    //T4：投融资收益表
    private String[][] investProfit;
    //T5:同业拆入
    private String[][] interBank;
    //T5:同业拆出
    private String[][] outBank;

    public String[][] getOutBank() {
        return outBank;
    }

    public void setOutBank(String[][] outBank) {
        this.outBank = outBank;
    }

    public String[][] getNationalDebtPurchase() {
        return nationalDebtPurchase;
    }

    public void setNationalDebtPurchase(String[][] nationalDebtPurchase) {
        this.nationalDebtPurchase = nationalDebtPurchase;
    }

    public String[][] getNationalDebtTrade() {
        return nationalDebtTrade;
    }

    public void setNationalDebtTrade(String[][] nationalDebtTrade) {
        this.nationalDebtTrade = nationalDebtTrade;
    }

    public String[][] getInvestProfit() {
        return investProfit;
    }

    public void setInvestProfit(String[][] investProfit) {
        this.investProfit = investProfit;
    }

    public String[][] getInterBank() {
        return interBank;
    }

    public void setInterBank(String[][] interBank) {
        this.interBank = interBank;
    }
}
