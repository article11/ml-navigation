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
public class lhfs_ld_lhfs implements ReflectionService {
    @Override
    public String getLoginUrl(Map<String, String> params) {
        String test = params.get("test").equals("test")?"test":"formal";
        String gamename = params.get(CommonConst.gamename);
        String adult = "1";
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String username = params.get(CommonConst.username);
        String serverid = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis());
        String reload = "1";
        String loginkey = LoginmakerDao.getInstance().getLoginKey(gamename, gameid, agent);
        String sign = MD5Util.getMD5String(username + serverid + time + adult + loginkey);
        String loginurl = "http://interface.lhfs.xhhd6.com/interface/login_49you?pf=" + agent + "&username=" + username + "&serverId=" + serverid + "&time=" + time + "&sign=" + sign + "&showlogin=0&adult=" + adult;
        String logintesturl = "http://interface.test.xhhd6.com/interface/login_common?pf=" + agent + "&username=" + username + "&serverId=" + serverid + "&time=" + time + "&sign=" + sign + "&showlogin=0&adult=" + adult;
        String url=loginurl;
        if(test.equals("test")){
            url=logintesturl;
        }
        return url;
    }

    @Override
    public String getRechargeUrl(Map<String, String> params) {
        String test = params.get("test").equals("test")?"test":"formal";
        String gamename = params.get(CommonConst.gamename);
        String gameid = params.get(CommonConst.gameid);
        String pf = params.get(CommonConst.agent);
        String account = params.get(CommonConst.username);
        String serverId = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis());
        String roleid = params.get(CommonConst.roleid);
        String orderId = time + "_" + Until.getRoundNum(9);
        String money = "100";
        String gold = "100";
//        String type = "2";
        String type = "3";
//        String reason = "补单用户充值";
        String reason = "用户测试充值";
        String rechargekey = RechargeMakerDao.getInstance().getrechargekey(gamename, gameid, pf);
        String sign = MD5Util.getMD5String(account + serverId +orderId + money + gold + time + rechargekey);
        String rechargeurl = "http://interface.lhfs.xhhd6.com/interface/recharge_49you?pf=" + pf + "&username=" + account + "&serverId=" + serverId + "&orderId=" + orderId + "&money=" + money + "&gold=" + gold + "&type=" + type + "&reason=" + reason + "&sign=" + sign + "&time=" + time + "&roleId=" + roleid;
        String rechargetesturl = "http://interface.test.xhhd6.com/interface/recharge_49you?pf=" + pf + "&username=" + account + "&serverId=" + serverId + "&orderId=" + orderId + "&money=" + money + "&gold=" + gold + "&type=" + type + "&reason=" + reason + "&sign=" + sign + "&time=" + time + "&roleId=" + roleid;
        String url=rechargeurl;
        if(test.equals("test")){
            url=rechargetesturl;
        }
        return url;
    }



    public String getRoleId(Map<String, String> params) {
        String test = params.get("test").equals("test")?"test":"formal";
        String urlstring = "http://interface.lhfs.xhhd6.com/interface/userquery_common?pf=" + params.get("agent") + "&account=" + params.get("username") + "&serverId=" + params.get("serverid");
        String urlstringtest = "http://interface.test.xhhd6.com/interface/userquery_common?pf=" + params.get("agent") + "&account=" + params.get("username") + "&serverId=" + params.get("serverid");
        String url=urlstring;
        if(test.equals("test")){
            url=urlstringtest;
        }
        String res = UserDao.getInstance().getRoleId(url);
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
        String[] resArray = {url, option};
        String resjson = JSON.toJSONString(resArray);
        return resjson;
    }



}
