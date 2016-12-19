package com.moloong.web.common.listener;

import com.moloong.web.common.config.SystemConfig;
import com.moloong.web.common.util.PathUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * author:yuan-hai
 * date:2016/10/17 16:37
 * description:应用启动监听器
 */
@WebListener
public class ApplicationInitListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //设置log4j相对路径
        try {
            String path = PathUtil.getWebProjectPath();
            System.setProperty ("webapp.root", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new SystemConfig();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
