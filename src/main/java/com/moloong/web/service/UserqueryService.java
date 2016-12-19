package com.moloong.web.service;

import com.moloong.web.common.config.CommonConst;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
public class UserqueryService {

    public static UserqueryService getInstance() {
        return instance;
    }

    private static UserqueryService instance = new UserqueryService();

    private UserqueryService() {
    }


//    public String getRoleId(String gamename, String pf, String account, String serverId) {
//        String urlstring = "http://interface.lhfs.xhhd6.com/interface/userquery_common?pf=" + pf + "&account=" + account + "&serverId=" + serverId;
//        String res = UserDao.getInstance().getRoleId(urlstring);
//        JSONObject jsonObject = JSONObject.parseObject(res);
//        String code = jsonObject.getString("code");
//        String option = "";
//        if (code.equals("0")) {
//            String data = jsonObject.getString("data");
//            JSONObject rolejson = JSONObject.parseObject(data);
//            for (int i = 0; i < rolejson.size(); i++) {
//                option += "<option value=\"" + rolejson.getJSONObject(String.valueOf(i)).getString("roleid") + "\">" + rolejson.getJSONObject(String.valueOf(i)).getString("rolename") + "</option>";
//            }
//        } else {
//            option = "<option value=\"111111\">无此账号</option>";
//        }
//        String[] resArray={urlstring,option};
//        String resjson=JSON.toJSONString(resArray);
//        return resjson;
//    }


    public String getRoleId(Map<String, String> params) throws Exception {
        String className = "com.moloong.web.service.platform.impl." + params.get(CommonConst.gamename) + "_" + params.get(CommonConst.agent);
        Class<?> clz = Class.forName(className);
        Object o = clz.newInstance();
        Method m = clz.getMethod("getRoleId", Map.class);
        String res = (String) m.invoke(o, params);
        return res;
    }


}
