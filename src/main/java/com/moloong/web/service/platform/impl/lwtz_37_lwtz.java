package com.moloong.web.service.platform.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moloong.web.bean.Platform;
import com.moloong.web.common.config.CommonConst;
import com.moloong.web.common.util.MD5Util;
import com.moloong.web.common.util.Until;
import com.moloong.web.dao.LoginmakerDao;
import com.moloong.web.dao.PlatformDao;
import com.moloong.web.dao.RechargeMakerDao;
import com.moloong.web.dao.UserDao;
import com.moloong.web.service.platform.ReflectionService;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public class lwtz_37_lwtz implements ReflectionService {
    @Override
    public String getLoginUrl(Map<String, String> params) {
        String test = params.get("test");
        String gamename = params.get(CommonConst.gamename);
        String adult = "0";
        String pt_vip = "0";
        String lingpai = "1";
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String username = params.get(CommonConst.username);
        String serverid = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis() / 1000);
//        String time = String.valueOf((System.currentTimeMillis()-5*60*1000)/1000);
        String loginkey = LoginmakerDao.getInstance().getLoginKey(gamename, gameid, agent);
        String sign = MD5Util.getMD5String(username + serverid + adult + pt_vip + time + loginkey).toLowerCase();
//        String loginurl = "http://s"+serverid+".37wannjws.17wan7.com/interface/login_37wan?agent=" + agent + "&user_name=" + username + "&server_id=" + serverid + "&time=" + time + "&pt_vip=" + pt_vip + "&lingpai=" + lingpai + "&sign=" + sign + "&client=1&is_adult=" + adult;
        String loginurl = "http://interface.xhhd6.com/interface/lwtz/lwtzlogin/login_37?agent=" + agent + "&user_name=" + username + "&server_id=" + serverid + "&time=" + time + "&pt_vip=" + pt_vip + "&lingpai=" + lingpai + "&sign=" + sign + "&client=1&is_adult=" + adult;
//        String logintesturl = "http://interface.test.xhhd6.com/interface/login_"+agent+"?agent=" + agent + "&user_name=" + username + "&server_id=" + serverid + "&time=" + time + "&pt_vip=" + pt_vip + "&lingpai=" + lingpai + "&sign=" + sign + "&client=1&is_adult=" + adult;
        String logintesturl = "http://118.178.192.142:8082/interface/lwtz/lwtzlogin/login_37_test?agent=37_lwtz&user_name=" + username + "&server_id=" + serverid + "&time=" + time + "&pt_vip=" + pt_vip + "&lingpai=" + lingpai + "&sign=" + sign + "&client=1&is_adult=" + adult;
        String url=loginurl;
        if(test.equals("test")){
            url=logintesturl;
        }
        return url;
    }

    @Override
    public String getRechargeUrl(Map<String, String> params) {
        String test = params.get("test");
        String gamename = params.get(CommonConst.gamename);
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String user_name = params.get(CommonConst.username);
        String server_id = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String order_id = time + "_" + Until.getRoundNum(9);
        String role_id = params.get(CommonConst.roleid);
        String money = "1.00";
        String coin = "100";
        String type = "3";
        String rechargekey = RechargeMakerDao.getInstance().getrechargekey(gamename, gameid, agent);
        String sign = MD5Util.getMD5String(order_id + user_name + server_id + coin + money + time + rechargekey);
//        String rechargeurl = "http://s"+server_id+".37wannjws.17wan7.com/interface/recharge_" + agent + "?agent=" + agent + "&user_name=" + user_name + "&server_id=" + server_id + "&order_id=" + order_id + "&money=" + money + "&coin=" + coin + "&type=" + type +  "&sign=" + sign + "&time=" + time + "&order_id=" + order_id;
        String rechargeurl = "http://interface.xhhd6.com/interface/lwtz/recharge_37?agent=" + agent + "&user_name=" + user_name + "&server_id=" + server_id + "&role_id=" + role_id + "&money=" + money + "&coin=" + coin + "&type=" + type +  "&sign=" + sign + "&time=" + time + "&order_id=" + order_id;
        String rechargetesturl = "http://interface.test.xhhd6.com/interface/recharge_" + agent + "?agent=" + agent + "&user_name=" + user_name + "&server_id=" + server_id + "&role_id=" + role_id + "&money=" + money + "&coin=" + coin + "&type=" + type +  "&sign=" + sign + "&time=" + time + "&order_id=" + order_id;
        String url=rechargeurl;
        if(test.equals("test")){
            url=rechargetesturl;
        }
        return url;
    }



    @Override
    public String getRoleId(Map<String, String> params)  {
        String test = params.get("test");
        String gamename = params.get(CommonConst.gamename);
        String gameid = params.get(CommonConst.gameid);
        String agent = params.get(CommonConst.agent);
        String user_name = params.get(CommonConst.username);
        String server_id = params.get(CommonConst.serverid);
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        Platform platFormDao = null;
        try {
            platFormDao = PlatformDao.getInstance().getPlatform(gamename, gameid, agent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sign = MD5Util.getMD5String(server_id + user_name + time + platFormDao.getPlatformLoginKey()).toLowerCase();
        String urlstring = "http://interface.xhhd6.com/interface/lwtz/userquery_37?agent=" + agent + "&user_name=" + user_name + "&server_id=" + server_id+ "&sign=" + sign+ "&time=" + time;
        String urlstringtest = "http://interface.test.xhhd6.com/interface/userquery_37?agent=" + agent + "&user_name=" + user_name + "&server_id=" + server_id+ "&sign=" + sign+ "&time=" + time;
        String url=urlstring;
        if(test.equals("test")){
            url=urlstringtest;
        }
        String res = UserDao.getInstance().getRoleId(url);
        String option = "";
        if (res != null) {
            JSONObject jsonObject = JSONObject.parseObject(res);
            String code = jsonObject.getString("code");
            if (code.equals("1")) {
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
        String[] resArray={url,option};
        String resjson= JSON.toJSONString(resArray);
        return resjson;
    }
}
