package com.excel.listener;

import com.excel.pojo.MemoryData;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author C_hao
 * @date 2018/10/22 12:56
 */
public class ClearSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        synchronized(this){
            HttpSession session=httpSessionEvent.getSession();
            String userName= (String) session.getAttribute("username");
            if(userName!=null)
            {
                if(MemoryData.getSessionIDMap().get(userName)!=null)
                {
                    MemoryData.getSessionIDMap().remove(userName);
                }
            }

        }
    }
}
