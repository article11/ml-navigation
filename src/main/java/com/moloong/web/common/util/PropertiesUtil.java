package com.moloong.web.common.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * author:yuan-hai
 * date:2016/9/25 20:01
 * description:properties工具类
 */
public class PropertiesUtil {

    private static Logger logger = Logger.getLogger(PropertiesUtil.class);

    /**
     * 加载properties文件
     * @param propertiesName
     * @return
     */
    public static Properties getProperties(String propertiesName){
        Properties properties = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesName);
        try {
            properties.load(in);
            logger.info("加载properties文件"+propertiesName+"完成");
        } catch (Exception e) {
            logger.error("加载properties文件"+propertiesName+"错误：",e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("关闭流错误",e);
            }
        }
        return properties;
    }
}
