package com.excel.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.excel.pojo.MemoryData;
import com.excel.pojo.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author C_hao
 * @date 2018/10/22 10:11
 */
public class SingleUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        PrintWriter writer =null;
        httpServletResponse.setCharacterEncoding("utf-8");
        //如果拦截到的是登录的页面的话放行
        if (url.indexOf("login") >= 0 || url.indexOf("register") >= 0) {
            return true;
        }
        //如果是其他请求地址，进行拦截
        String userName = (String) httpServletRequest.getSession().getAttribute("username");
        if (userName!= null) {
            String sessionid = MemoryData.getSessionIDMap().get(userName);

        //如果用户名存在放心（即登录放行）

            if (sessionid.equals(httpServletRequest.getSession().getId())) {
                return true;
            } else { //如果请求的sessionID和此账号Map中存放的sessionID不一致，跳转到登陆页
                writer = httpServletResponse.getWriter();
                if (url.indexOf("gethtml") >= 0 ){
                    String content="该账号已在其它地方登录,请重新登录";
                    writer.print(content);
                }else
                {
                    HashMap map=new HashMap();
                    map.put("success",0);
                    map.put("msg","该账号已在其它地方登录,请重新登录");
                    writer.print(JSONObject.toJSONString(map));
                }
                writer.flush();
                writer.close();
                httpServletResponse.flushBuffer();
                return false;
            }
        }

        writer = httpServletResponse.getWriter();
        if (url.indexOf("gethtml") >= 0 ){
            String content="用户未登录";
            writer.print(content);
        }
        else
        {
            HashMap map=new HashMap();
            map.put("success",0);
            map.put("msg","用户未登录");
            writer.print(JSONObject.toJSONString(map));
        }

        writer.flush();
        writer.close();
        httpServletResponse.flushBuffer();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
