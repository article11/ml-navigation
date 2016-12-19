package com.moloong.web.service.platform.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moloong.web.common.config.CommonConst;
import com.moloong.web.common.util.MD5Util;
import com.moloong.web.common.util.Until;
import com.moloong.web.dao.LoginmakerDao;
import com.moloong.web.dao.RechargeMakerDao;
import com.moloong.web.dao.UserDao;
import com.moloong.web.service.platform.ReflectionService;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public class lhfs_49you implements ReflectionService {
    @Override
    public String getLoginUrl(Map<String, String> params) {
        String gamename = params.get(CommonConst.gamename);
        String adult = "1";
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String username = params.get(CommonConst.username);
        String serverid = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis());
        String loginkey = LoginmakerDao.getInstance().getLoginKey(gamename, gameid, agent);
        String sign = MD5Util.getMD5String(username + serverid + time + adult + loginkey);
        String loginurl = "http://interface.lhfs.xhhd6.com/interface/login_49you?pf=" + agent + "&username=" + username + "&serverId=" + serverid + "&time=" + time + "&sign=" + sign + "&showlogin=0&adult=" + adult;
        return loginurl;
    }

    @Override
    public String getRechargeUrl(Map<String, String> params) {
        String gamename = params.get(CommonConst.gamename);
        String gameid = params.get(CommonConst.gameid);
        String pf = params.get(CommonConst.agent);
        String account = params.get(CommonConst.username);
        String serverId = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis());
        String roleid = params.get(CommonConst.roleid);
        String orderId = time + "_" + Until.getRoundNum(9);
        String money = "1";
        String gold = "100";
        String type = "1";
        String reason = "测试充值测试";
        String rechargekey = RechargeMakerDao.getInstance().getrechargekey(gamename, gameid, pf);
        String sign = MD5Util.getMD5String(account + serverId +orderId + money + gold + time + rechargekey);
        String rechargeurl = "http://interface.lhfs.xhhd6.com/interface/recharge_"+pf+"?pf=" + pf + "&username=" + account + "&serverId=" + serverId + "&orderId=" + orderId + "&money=" + money + "&gold=" + gold + "&type=" + type + "&reason=" + reason + "&sign=" + sign + "&time=" + time + "&roleId=" + roleid;
        return rechargeurl;
    }



    public String getRoleId(Map<String, String> params) {
        String urlstring = "http://interface.lhfs.xhhd6.com/interface/userquery_common?pf=" + params.get("agent") + "&account=" + params.get("username") + "&serverId=" + params.get("serverid");
        String res = UserDao.getInstance().getRoleId(urlstring);
        String option = "";
        if (res != null) {
            JSONObject jsonObject = JSONObject.parseObject(res);
            String code = jsonObject.getString("code");
            if (code.equals("0")) {
                String data = jsonObject.getString("data");
                JSONObject rolejson = JSONObject.parseObject(data);
                for (int i = 0; i < rolejson.size(); i++) {
                    option += "<option value=\"" + rolejson.getJSONObject(String.valueOf(i)).getString("roleid") + "\">" + rolejson.getJSONObject(String.valueOf(i)).getString("rolename") + "</option>";
                }
            } else {
                option = "<option value=\"111111\">无此账号</option>";
            }
        } else {
            option = "<option value=\"111111\">无此账号</option>";
        }
        String[] resArray = {urlstring, option};
        String resjson = JSON.toJSONString(resArray);
        return resjson;
    }



}
