package com.excel.dao;

import com.excel.pojo.UserInfo;

public interface UserInfoDao {
    public UserInfo selectUserInfo(String userName);

    public int insertUserInfo(UserInfo userInfo);

    public Boolean selectForbidRegister();

    public Boolean selectForbidLogin();
}
