package com.excel.service;

import com.excel.pojo.T0Register;
import com.excel.pojo.T1RepositInterest;
import com.excel.pojo.T3_T4;

public interface SaveService {
    public void setExcelXlsx(T0Register t0Register,
                             T3_T4 t3_t4, String newFileName);

    public T0Register getExcelT0Register(String fileName);

    public T3_T4 getExcelT3_T4(String fileName);

    public T1RepositInterest getExcelT1RepositInterest(String fileName);

    public String getSheetHtml(String username, String sheetName);
}
