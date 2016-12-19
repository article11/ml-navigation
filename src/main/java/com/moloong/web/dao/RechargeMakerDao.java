package com.moloong.web.dao;

import com.moloong.web.bean.Platform;
import com.moloong.web.common.util.DbUtil;

/**
 * Created by Administrator on 2016/11/3.
 */
public class RechargeMakerDao {


    public static RechargeMakerDao getInstance() {
        return instance;
    }


    private static RechargeMakerDao instance = new RechargeMakerDao();

    private RechargeMakerDao() {
    }




    public String getrechargekey(String ds1,String gameid, String agent) {
        String sql = "select rechargeKey from platform  where  gameId=? and platformAlias=?";
        Platform res = DbUtil.getBean(ds1,sql, Platform.class, gameid,agent);
        return res.getRechargeKey();
    }

}
