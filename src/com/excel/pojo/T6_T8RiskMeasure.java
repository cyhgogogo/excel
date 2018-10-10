package com.excel.pojo;

//T6-T8 XX风险计量表
public class T6_T8RiskMeasure {
    //T6:操作风险计量表
    private String[][][] operationRisk;
    //T7:信用风险计量表
    private String[][][] creditRisk;
    private String[][][] marketRisk;

    public String[][][] getOperationRisk() {
        return operationRisk;
    }

    public void setOperationRisk(String[][][] operationRisk) {
        this.operationRisk = operationRisk;
    }

    public String[][][] getCreditRisk() {
        return creditRisk;
    }

    public void setCreditRisk(String[][][] creditRisk) {
        this.creditRisk = creditRisk;
    }

    public String[][][] getMarketRisk() {
        return marketRisk;
    }

    public void setMarketRisk(String[][][] marketRisk) {
        this.marketRisk = marketRisk;
    }
}
