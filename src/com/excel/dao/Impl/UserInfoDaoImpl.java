package com.excel.dao.Impl;

import com.alibaba.fastjson.JSONObject;
import com.excel.dao.UserInfoDao;
import com.excel.pojo.UserInfo;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;

@Component(value = "userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {
    static String userPath = "config/userInfo.txt";
    static String configPath = "config/setting.txt";

    @Override
    public UserInfo selectUserInfo(String userName) {
        File file = new File(userPath);
        if (!file.exists()) {
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                System.out.println("创建" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(userPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = "";
        int tempchar;
        try {
            while ((tempchar = fileReader.read()) != -1) {
                content += (char) tempchar;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = null;
        if (!content.equals("")) {
            HashMap json = JSONObject.parseObject(content, HashMap.class);
            if (json.get(userName) != null)
                userInfo = JSONObject.parseObject(json.get(userName).toString(), UserInfo.class);
        }

        return userInfo;
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        File file = new File(userPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("创建" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader fileReader = null;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        FileInputStream fileInputStream=null;
        InputStreamReader inputStreamReader=null;
        try {

            fileInputStream=new FileInputStream(file);
            inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
        } catch (IOException e) {
        }
        String content = "";
        try {
            int s;
            while ((s =inputStreamReader.read()) != -1) {//逐行读取文件内容，不读取换行符和末尾的空格
                content+=(char)s;
            }
            inputStreamReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");//指定以UTF-8格式写入文件
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (content.equals("")) {
            content = "{}";
        }
        HashMap json=JSONObject.parseObject(content,HashMap.class);
        String str = (String) json.get(userInfo.getUserName());
        userInfo.setUserName(userInfo.getUserName().replace("\"","\\\""));
        UserInfo userInfo2 = JSONObject.parseObject(str, UserInfo.class);
        if (userInfo2 == null) {
            try {
                String temp = JSONObject.toJSONString(userInfo);
                if (!content.equals("{}")) {
                    outputStreamWriter.write(content.substring(0, content.length() - 1) + ",\"" + userInfo.getUserName() + "\":" + temp+"}");
                } else {
                    outputStreamWriter.write("{\""+userInfo.getUserName()+"\":"+temp+"}");
                }
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public Boolean selectForbidRegister() {

        File file = new File(configPath);
        if (!file.exists()) {
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                System.out.println("创建" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(configPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = "";
        int tempchar;
        try {
            while ((tempchar = fileReader.read()) != -1) {
                content += (char) tempchar;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean result = false;
        if (!content.equals("")) {
            HashMap json = JSONObject.parseObject(content, HashMap.class);
            if (json.get("forbidRegister") != null) {
                result = Boolean.parseBoolean(json.get("forbidRegister").toString());
            } else {
                json.put("forbidRegister", result);
                FileOutputStream fileOutputStream = null;
                OutputStreamWriter outputStreamWriter = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");//指定以UTF-8格式写入文件
                    String temp = JSONObject.toJSONString(json);
                    outputStreamWriter.write(temp);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else {
            HashMap json = new HashMap();
            json.put("forbidLogin", result);
            json.put("forbidRegister", result);
            FileOutputStream fileOutputStream = null;
            OutputStreamWriter outputStreamWriter = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");//指定以UTF-8格式写入文件
                String temp = JSONObject.toJSONString(json);
                outputStreamWriter.write(temp);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    @Override
    public Boolean selectForbidLogin() {
        File file = new File(configPath);
        if (!file.exists()) {
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                System.out.println("创建" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(configPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = "";
        int tempchar;
        try {
            while ((tempchar = fileReader.read()) != -1) {
                content += (char) tempchar;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean result = false;
        if (!content.equals("")) {
            HashMap json = JSONObject.parseObject(content, HashMap.class);
            if (json.get("forbidLogin") != null) {
                result = Boolean.parseBoolean(json.get("forbidLogin").toString());
            } else {

                json.put("forbidLogin", result);
                FileOutputStream fileOutputStream = null;
                OutputStreamWriter outputStreamWriter = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");//指定以UTF-8格式写入文件
                    String temp = JSONObject.toJSONString(json);
                    outputStreamWriter.write(temp);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            HashMap json = new HashMap();
            json.put("forbidLogin", result);
            json.put("forbidRegister", result);
            FileOutputStream fileOutputStream = null;
            OutputStreamWriter outputStreamWriter = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");//指定以UTF-8格式写入文件
                String temp = JSONObject.toJSONString(json);
                outputStreamWriter.write(temp);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return result;
    }
}
