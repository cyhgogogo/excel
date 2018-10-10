package com.excel.pojo;

//T1存款利息表
public class T1RepositInterest {
    //T1.1 固定存款利息支出损益
    private String[][][] fixedrepositInterestOutput;
    //T1.1 浮动存款利息支出损益
    private String[][][] floatrepositInterestOutput;
    //T1.1 利率浮动值
    private String[][] interestFloat1;
    //T1.2 利率浮动值
    private String[][] interestFloat2;
    //T1.2  存款利息支出现金流 固定部分
    private String[][][] repositInterestOutput1;
    //T1.2  存款利息支出现金流 浮动部分
    private String[][][] repositInterestOutput2;

    public String[][][] getFixedrepositInterestOutput() {
        return fixedrepositInterestOutput;
    }

    public void setFixedrepositInterestOutput(String[][][] fixedrepositInterestOutput) {
        this.fixedrepositInterestOutput = fixedrepositInterestOutput;
    }

    public String[][][] getFloatrepositInterestOutput() {
        return floatrepositInterestOutput;
    }

    public void setFloatrepositInterestOutput(String[][][] floatrepositInterestOutput) {
        this.floatrepositInterestOutput = floatrepositInterestOutput;
    }

    public String[][] getInterestFloat1() {
        return interestFloat1;
    }

    public void setInterestFloat1(String[][] interestFloat1) {
        this.interestFloat1 = interestFloat1;
    }

    public String[][] getInterestFloat2() {
        return interestFloat2;
    }

    public void setInterestFloat2(String[][] interestFloat2) {
        this.interestFloat2 = interestFloat2;
    }

    public String[][][] getRepositInterestOutput1() {
        return repositInterestOutput1;
    }

    public void setRepositInterestOutput1(String[][][] repositInterestOutput1) {
        this.repositInterestOutput1 = repositInterestOutput1;
    }

    public String[][][] getRepositInterestOutput2() {
        return repositInterestOutput2;
    }

    public void setRepositInterestOutput2(String[][][] repositInterestOutput2) {
        this.repositInterestOutput2 = repositInterestOutput2;
    }
}
