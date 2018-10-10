package com.excel.service.impl;

import com.excel.dao.UserInfoDao;
import com.excel.pojo.UserInfo;
import com.excel.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public UserInfo selectUserInfo(String username) {
        return userInfoDao.selectUserInfo(username);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return userInfoDao.insertUserInfo(userInfo);
    }

    @Override
    public Boolean selectForbidRegister() {
        return userInfoDao.selectForbidRegister();
    }

    @Override
    public Boolean selectForbidLogin() {
        return userInfoDao.selectForbidLogin();
    }
}
