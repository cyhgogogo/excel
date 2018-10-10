package com.excel.service;

import com.excel.pojo.UserInfo;

public interface UserInfoService {
    public UserInfo selectUserInfo(String username);

    public int insertUserInfo(UserInfo userInfo);

    public Boolean selectForbidRegister();

    public Boolean selectForbidLogin();
}
