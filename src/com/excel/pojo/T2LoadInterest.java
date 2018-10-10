package com.excel.pojo;

//T2贷款利息表
public class T2LoadInterest {
    //T2.1 固定贷款利率
    private String[][][] fixedLoadInterest;
    //T2.1 浮动贷款利率
    private String[][][] floatLoadInterest;
    //T2.1 利率浮动值
    private String[][] interestFloat;
    //T2.2 贷款利息收入
    private String[][][] loadInterestIncome;
    //T2.2 利率变动值
    private String[][] interestChange;

    public String[][][] getFixedLoadInterest() {
        return fixedLoadInterest;
    }

    public void setFixedLoadInterest(String[][][] fixedLoadInterest) {
        this.fixedLoadInterest = fixedLoadInterest;
    }

    public String[][][] getFloatLoadInterest() {
        return floatLoadInterest;
    }

    public void setFloatLoadInterest(String[][][] floatLoadInterest) {
        this.floatLoadInterest = floatLoadInterest;
    }

    public String[][] getInterestFloat() {
        return interestFloat;
    }

    public void setInterestFloat(String[][] interestFloat) {
        this.interestFloat = interestFloat;
    }

    public String[][][] getLoadInterestIncome() {
        return loadInterestIncome;
    }

    public void setLoadInterestIncome(String[][][] loadInterestIncome) {
        this.loadInterestIncome = loadInterestIncome;
    }

    public String[][] getInterestChange() {
        return interestChange;
    }

    public void setInterestChange(String[][] interestChange) {
        this.interestChange = interestChange;
    }
}
