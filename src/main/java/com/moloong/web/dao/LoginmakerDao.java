package com.moloong.web.dao;

import com.moloong.web.bean.Platform;
import com.moloong.web.common.util.DbUtil;

/**
 * Created by Administrator on 2016/11/3.
 */
public class LoginmakerDao {

    public static LoginmakerDao getInstance() {

        return instance;
    }


    private static LoginmakerDao instance = new LoginmakerDao();

    private LoginmakerDao() {
    }


//    public String getLoginKey(String gamename, String agent) {
//        String sql = "select login_key from " + gamename + "agentkey where  agent=?";
//        AgentKey res = DbUtil.getBean(sql, AgentKey.class, agent);
//        return res.getLogin_key();
//    }

    public String getLoginKey(String ds1,String gameid, String agent) {
        String sql = "select platformLoginKey from platform  where  gameId=? and platformAlias=?";
        Platform res = DbUtil.getBean(ds1,sql, Platform.class, gameid,agent);
        return res.getPlatformLoginKey();
    }

}
