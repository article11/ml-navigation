package com.moloong.web.service.platform.impl;

import com.alibaba.fastjson.JSON;
import com.moloong.web.bean.Platform;
import com.moloong.web.common.config.CommonConst;
import com.moloong.web.common.util.MD5Util;
import com.moloong.web.common.util.Until;
import com.moloong.web.dao.LoginmakerDao;
import com.moloong.web.dao.PlatformDao;
import com.moloong.web.dao.RechargeMakerDao;
import com.moloong.web.service.platform.ReflectionService;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public class njws_37wan implements ReflectionService {
    @Override
    public String getLoginUrl(Map<String, String> params) {
        String gamename = params.get(CommonConst.gamename);
        String adult = "1";
        String pt_vip = "0";
        String lingpai = "1";
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String username = params.get(CommonConst.username);
        String serverid = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String loginkey = LoginmakerDao.getInstance().getLoginKey(gamename, gameid, agent);
        String sign = MD5Util.getMD5String(username + serverid +adult+pt_vip+ time +  loginkey).toLowerCase();
        String loginurl = "http://s"+serverid+".37wannjws.17wan7.com/interface/login_37wan?agent=" + agent + "&user_name=" + username + "&server_id=" + serverid + "&time=" + time + "&pt_vip=" + pt_vip + "&lingpai=" + lingpai + "&sign=" + sign + "&client=1&is_adult=" + adult;
        return loginurl;
    }

    @Override
    public String getRechargeUrl(Map<String, String> params) {
        String gamename = params.get(CommonConst.gamename);
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String user_name = params.get(CommonConst.username);
        String server_id = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String order_id = time + "_" + Until.getRoundNum(9);
        String money = "1";
        String coin = "100";
        String type = "0";
        String rechargekey = RechargeMakerDao.getInstance().getrechargekey(gamename, gameid, agent);
        String sign = MD5Util.getMD5String(order_id + user_name + server_id + coin + money + time + rechargekey);
        String rechargeurl = "http://s"+server_id+".37wannjws.17wan7.com/interface/recharge_" + agent + "?agent=" + agent + "&user_name=" + user_name + "&server_id=" + server_id + "&order_id=" + order_id + "&money=" + money + "&coin=" + coin + "&type=" + type +  "&sign=" + sign + "&time=" + time + "&order_id=" + order_id;
        return rechargeurl;
    }



    @Override
    public String getRoleId(Map<String, String> params) throws Exception {
        String gamename = params.get(CommonConst.gamename);
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String user_name = params.get(CommonConst.username);
        String server_id = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        Platform platFormDao = PlatformDao.getInstance().getPlatform(gamename, gameid, agent);
        String sign = MD5Util.getMD5String(server_id + user_name + time + platFormDao.getPlatformLoginKey()).toLowerCase();
        String urlstring = "http://s"+server_id+".37wannjws.17wan7.com/interface/userquery_common?agent=" + agent + "&user_name=" + user_name + "&server_id=" + server_id+ "&sign=" + sign+ "&time=" + time;
        String option = "";
        String[] resArray={urlstring,option};
        String resjson= JSON.toJSONString(resArray);
        return resjson;
    }
}
