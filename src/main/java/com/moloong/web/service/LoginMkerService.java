package com.moloong.web.service;

import com.moloong.web.common.config.CommonConst;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/3.
 */
public class LoginMkerService {

    private static LoginMkerService instance = new LoginMkerService();

    public static LoginMkerService getInterstance() {
        return instance;
    }

    private LoginMkerService() {
    }


    public String getLoginUrl(Map<String, String> params) throws Exception {
        String agent=params.get(CommonConst.agent);
//        if("lhfs".equals(params.get(CommonConst.gamename))){
             agent="49you";

//        }
        if("37".equals(params.get(CommonConst.agent))){
              agent="37";
        }

        if("37_lwtz".equals(params.get(CommonConst.agent))){
            agent="37_lwtz";
        }
        if("59wanyx_lwtz".equals(params.get(CommonConst.agent))){
            agent="59wanyx_lwtz";
        }


        String className = "com.moloong.web.service.platform.impl." + params.get(CommonConst.gamename) + "_" + agent;
//        String className = "com.moloong.web.service.platform.impl." + params.get(CommonConst.gamename) + "_" + params.get(CommonConst.agent);
        Class<?> clz = Class.forName(className);
        Object o = clz.newInstance();
        Method m = clz.getMethod("getLoginUrl", Map.class);
        String res= (String) m.invoke(o, params);
        return res;
    }




//    public String getLoginUrl(Map<String, String> params) {
//        String gameid = params.get("gameid");
//        String pf = params.get("agent");
//        if (gameid.equals("1")) {
//            if (pf.equals("cdcq1")) {
//                return lhfs_cdcq1_rul(params);
//            } else if (pf.equals("49you")) {
//                return lhfs_49you_rul(params);
//            } else {
//                return "";
//            }
//        }
//        return "";
//    }
//
//    private String lhfs_cdcq1_rul(Map<String, String> params) {
//        String gamename = params.get(CommonConst.gamename);
//        String gameid = params.get(CommonConst.gameid);
//        String agent = params.get(CommonConst.agent);
//        String username = params.get(CommonConst.username);
//        String serverid = params.get(CommonConst.serverid);
//        String time = String.valueOf(System.currentTimeMillis());
//        String loginkey = LoginmakerDao.getInstance().getLoginKey(SystemConfig.getProperty("game.Interface"), gameid, agent);
//        String sign = MD5Util.getMD5String(agent + username + serverid + time + loginkey);
//        String loginurl = "http://s" + serverid + "." + agent + "lhfs.xhhd6.com:8080/interface/login_common?pf=" + agent + "&account=" + username + "&serverId=" + serverid + "&time=" + time + "&sign=" + sign + "&showlogin=0&fcm=1";
//        System.out.println(loginurl);
//        return loginurl;
//    }
//
////    private String lhfs_49you_rul(Map<String, String> params) {
////        String gamename = params.get(CommonConst.gamename);
////        String gameid = params.get(CommonConst.gameid);
////        String agent = params.get(CommonConst.agent);
//////        String agent = "cdcq1";
////        String username = params.get(CommonConst.username);
////        String serverid = params.get(CommonConst.serverid);
////        String time = String.valueOf(System.currentTimeMillis());
////        String adult = "0";
////        String loginkey = LoginmakerDao.getInstance().getLoginKey(SystemConfig.getProperty("game.Interface"), gameid, agent);
////        String sign = MD5Util.getMD5String(username + serverid + time + adult + loginkey);
////        String loginurl = "http://s" + serverid + "." + agent + "lhfs.xhhd6.com:8080/interface/login_" + agent + "?pf=" + agent + "&username=" + username + "&serverId=" + serverid + "&time=" + time + "&sign=" + sign + "&showlogin=0&adult=" + adult;
////        return loginurl;
////    }
//
//
//    private String lhfs_49you_rul(Map<String, String> params) {
//        String gamename = params.get(CommonConst.gamename);
//        String adult = "1";
//        String gameid = params.get(CommonConst.gameid);
//        String agent = params.get(CommonConst.agent);
//        String username = params.get(CommonConst.username);
//        String serverid = params.get(CommonConst.serverid);
//        String time = String.valueOf(System.currentTimeMillis());
//        String loginkey = LoginmakerDao.getInstance().getLoginKey(SystemConfig.getProperty("game.Interface"), gameid, agent);
//        String sign = MD5Util.getMD5String(username + serverid + time + adult + loginkey);
//        String loginurl = "http://interface.lhfs.xhhd6.com/interface/login_49you?pf=" + agent + "&username=" + username + "&serverId=" + serverid + "&time=" + time + "&sign=" + sign + "&showlogin=0&adult=" + adult;
//        return loginurl;
//    }





}
