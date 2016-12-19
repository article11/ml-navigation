package com.moloong.web.dao;

import com.moloong.web.bean.Platform;
import com.moloong.web.common.util.DbUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
public class PlatformDao {
    public static PlatformDao getInstance() {
        return instance;
    }

    private static PlatformDao instance = new PlatformDao();

    private PlatformDao() {
    }

    public List<Platform> getPlatformList(String ds1, String gameId) throws Exception {
        String sql = "select platformName,platformAlias from platform where isDeleted = 0 and gameId=?";
        List<Platform> result = DbUtil.getListBean(ds1, sql, Platform.class, gameId);
        return result;
    }


    public Platform getPlatform(String ds1, String gameId, String platformName) throws Exception {
        String sql = "select platformLoginKey,rechargeKey,newsCardKey from platform where gameId=?  and platformAlias=? and isDeleted = 0";
        Platform result = DbUtil.getBean(ds1, sql, Platform.class, gameId, platformName);
        return result;
    }


    public int insertPlat(String ds1, Map<String, String> map) {
        String name = "";
        String value = "";
        int res = 0;
        for (String key : map.keySet()) {
            name += key + ",";
            value += "'" + map.get(key) + "',";
        }
        name = name.substring(0, name.length() - 1);
        value = value.substring(0, value.length() - 1);
        String sql = "INSERT INTO platform (" + name + ") VALUES(" + value + ") ";
        System.out.println("sql:" + sql);
        System.out.println("value:" + value);
        res = DbUtil.save(ds1, sql);
        System.out.println("res:" + res);
        return res;
    }

}
