package com.excel.pojo;

//T0登记表
public class T0Register {
    //登记表
    private String[][] register = null;
    //市场信息
    private String[][] market = null;
    //存款业务信息登记
    private String[][][] repositService = null;
    //贷款业务信息登记表
    private String[][][] loadService = null;

    public String[][] getRegister() {
        return register;
    }

    public void setRegister(String[][] register) {
        this.register = register;
    }

    public String[][] getMarket() {
        return market;
    }

    public void setMarket(String[][] market) {
        this.market = market;
    }

    public String[][][] getRepositService() {
        return repositService;
    }

    public void setRepositService(String[][][] repositService) {
        this.repositService = repositService;
    }

    public String[][][] getLoadService() {
        return loadService;
    }

    public void setLoadService(String[][][] loadService) {
        this.loadService = loadService;
    }
}
