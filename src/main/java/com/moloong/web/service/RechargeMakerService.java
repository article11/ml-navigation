package com.moloong.web.service;

import com.moloong.web.common.config.CommonConst;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/3.
 */
public class RechargeMakerService {


    public static RechargeMakerService getInstance() {
        return instance;
    }

    private static RechargeMakerService instance = new RechargeMakerService();


    private RechargeMakerService() {
    }


    public String getRechargeUrl(Map<String, String> params) throws Exception {
        String agent="49you";
        if("37".equals(params.get(CommonConst.agent))){
            agent="37";
        }

//        String className = "com.moloong.web.service.platform.impl." + params.get(CommonConst.gamename) + "_" + params.get(CommonConst.agent);
        String className = "com.moloong.web.service.platform.impl." + params.get(CommonConst.gamename) + "_" + agent;
        Class<?> clz = Class.forName(className);
        Object o = clz.newInstance();
        Method m = clz.getMethod("getRechargeUrl", Map.class);
        String res= (String) m.invoke(o, params);
        return res;
    }

//    public String getRechargeUrl(Map<String, String> params) {
//        String gameid = params.get("gameid");
//        String pf = params.get("agent");
//        if (gameid.equals("1")) {
//            if (pf.equals("cdcq1")) {
//                return lhfs_cdcq1_rul(params);
//            } else if (pf.equals("49you")) {
//                return lhfs_49you_rul(params);
//            }else {
//                return "";
//            }
//        }
//        return "";
//    }
//
//    private String lhfs_cdcq1_rul(Map<String, String> params) {
//        String gamename = params.get(CommonConst.gamename);
//        String gameid = params.get(CommonConst.gameid);
//        String pf = params.get(CommonConst.agent);
//        String account = params.get(CommonConst.username);
//        String serverId = params.get(CommonConst.serverid);
//        String time = String.valueOf(System.currentTimeMillis());
//        String roleid = params.get(CommonConst.roleid);
//        String orderId = time + "_" + Until.getRoundNum(9);
//        String money = "1";
//        String gold = "100";
//        String type = "1";
//        String reason = "测试充值测试";
//        String rechargekey = RechargeMakerDao.getInstance().getrechargekey(SystemConfig.getProperty("game.Interface"), gameid, pf);
//        String sign = MD5Util.getMD5String(orderId + gold + money + type + rechargekey);
//        String rechargeurl = "http://s" + serverId + "." + pf + "lhfs.xhhd6.com:8080/interface/recharge_common?pf=" + pf + "&account=" + account + "&serverId=" + serverId + "&orderId=" + orderId + "&money=" + money + "&gold=" + gold + "&type=" + type + "&reason=" + reason + "&sign=" + sign + "&time=" + time + "&roleid=" + roleid;
//        return rechargeurl;
//    }
//
//
//    private String lhfs_49you_rul(Map<String, String> params) {
//        String gamename = params.get(CommonConst.gamename);
//        String gameid = params.get(CommonConst.gameid);
//        String pf = params.get(CommonConst.agent);
//        String account = params.get(CommonConst.username);
//        String serverId = params.get(CommonConst.serverid);
//        String time = String.valueOf(System.currentTimeMillis());
//        String roleid = params.get(CommonConst.roleid);
//        String orderId = time + "_" + Until.getRoundNum(9);
//        String money = "1";
//        String gold = "100";
//        String type = "1";
//        String reason = "测试充值测试";
//        String rechargekey = RechargeMakerDao.getInstance().getrechargekey(SystemConfig.getProperty("game.Interface"), gameid, pf);
//        String sign = MD5Util.getMD5String(account + serverId +orderId + money + gold + time + rechargekey);
//        String rechargeurl = "http://interface.lhfs.xhhd6.com/interface/recharge_"+pf+"?pf=" + pf + "&username=" + account + "&serverId=" + serverId + "&orderId=" + orderId + "&money=" + money + "&gold=" + gold + "&type=" + type + "&reason=" + reason + "&sign=" + sign + "&time=" + time + "&roleId=" + roleid;
//        return rechargeurl;
//    }






}
