package com.moloong.web.common.config;

import com.moloong.web.common.util.PropertiesUtil;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * author:yuan-hai
 * date:2016/10/17 16:43
 * description:
 */
public class SystemConfig extends TimerTask{

    private static Logger logger = Logger.getLogger(SystemConfig.class);

    private static Properties properties = new Properties();

    private static final String CONFIG_FILE_NAME = "ApplicationConfig.properties";

    private Timer systemConfigTimer = new Timer("SystemConfigTimer");

    public SystemConfig(){
        loadConfig();
        // 每5分钟重载一次配置文件
        systemConfigTimer.schedule(this, 5 * 60 * 1000, 5 * 60 * 1000);
    }

    private void loadConfig(){
        properties = PropertiesUtil.getProperties(CONFIG_FILE_NAME);
    }

    @Override
    public void run() {
        loadConfig();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key,String defaultvalue) {
        return properties.getProperty(key,defaultvalue);
    }
}
