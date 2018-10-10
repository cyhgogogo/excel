package com.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.excel.pojo.T0Register;
import com.excel.pojo.T0_T3_T4;
import com.excel.pojo.T3_T4;
import com.excel.pojo.UserInfo;
import com.excel.service.SaveService;
import com.excel.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CalculateController {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    SaveService saveService;
    @Autowired
    UserInfoService userInfoService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Object register(@RequestParam(value = "username") String userName,
                           @RequestParam(value = "password") String password
    ) {
        userName = userName.trim();
        password = password.trim();
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(password);
        userInfo.setUserName(userName);
        userInfo.setTime(sdf.format(new Date()));
        HashMap<String, Object> json = new HashMap();
        UserInfo user = userInfoService.selectUserInfo(userName);
        if (userInfoService.selectForbidRegister()) {
            json.put("success", 0);
            json.put("msg", "管理员未开放注册");
        } else if (user != null) {
            json.put("success", 0);
            json.put("msg", "用户已经注册过");
        } else {
            if (!userName.equals("") && !password.equals("")) {
                int num = userInfoService.insertUserInfo(userInfo);
                if (num == 1) {
                    json.put("success", 1);
                    json.put("msg", "注册成功");
                } else {
                    json.put("success", 0);
                    json.put("msg", "服务器出错");
                }
            } else {
                json.put("success", 0);
                json.put("msg", "用户名和密码为空");
            }
        }

        return json;
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@RequestParam(value = "username") String userName,
                        @RequestParam(value = "password") String password,
                        HttpSession session) {
        userName = userName.trim();
        password = password.trim();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        userInfo.setTime(sdf.format(new Date()));
        HashMap<String, Object> json = new HashMap<String, Object>();
        UserInfo user = userInfoService.selectUserInfo(userName);
        if (userInfoService.selectForbidLogin()) {
            json.put("success", 0);
            json.put("msg", "管理员未开放登录");
        } else if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("username", userName);
                json.put("success", 1);
                json.put("msg", "登陆成功！");
            } else {
                json.put("success", 0);
                json.put("msg", "密码错误");
            }
        } else {
            json.put("success", 0);
            json.put("msg", "该用户未注册");
        }
        return json;
    }

    @RequestMapping(value = "calculate", method = RequestMethod.POST)
    @ResponseBody
    public Object calCulate(@RequestBody T0_T3_T4 t0_t3_t4,
                            HttpSession session) {

        String userName = (String) session.getAttribute("username");
        HashMap<String, Object> json = new HashMap<String, Object>();
        if (userName != null) {
            if(t0_t3_t4!=null)
            {
                T0Register t0Register = t0_t3_t4.getT0();
                T3_T4 t3T4 = t0_t3_t4.getT3_t4();
                if(t0Register!=null)
                {
                    String[][][] loadService=t0Register.getLoadService();
                    if(loadService!=null)
                    {
                        for (int x = 0; x < loadService.length; x++) {
                            int temp = 4;
                            if (x == 0) {
                                temp = 2;
                            }
                            for (int i = 0; i < temp; i++) {
                                for (int s = 0; s < 9; s++) {
                                    if(!loadService[x][i][0].equals(""))
                                    {
                                        if(loadService[x][i][1].equals(""))
                                        {
                                            json.put("success", 0);
                                            json.put("msg","请填上对应的期数");
                                            return  json;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                saveService.setExcelXlsx(t0Register, t3T4, userName);
                t0Register = saveService.getExcelT0Register(userName);
                t3T4 = saveService.getExcelT3_T4(userName);
                json.put("success", 1);
                json.put("t0", t0Register);
                json.put("t3_t4", t3T4);
            }


        } else {
            json.put("success", 0);
            json.put("msg", "用户未登录");
        }

        return json;
    }


    @RequestMapping(value = "sheethtml", method = RequestMethod.POST)
    @ResponseBody
    public Object getSheetHtml(@RequestParam(value = "sheet") String sheetName,
                               HttpSession session) {
        HashMap<String, Object> json = new HashMap<String, Object>();
        String userName = (String) session.getAttribute("username");
        String content = "";
        if (userName != null) {
            json.put("success", 1);
            content = saveService.getSheetHtml(userName, sheetName);
            json.put("sheet", content);
        } else {
            json.put("success", 0);
            json.put("msg", "用户未登录");
        }


        return json;
    }
}