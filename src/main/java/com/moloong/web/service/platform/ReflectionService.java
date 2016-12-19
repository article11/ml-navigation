package com.moloong.web.service.platform;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface ReflectionService {


    public String getLoginUrl(Map<String, String> params);
    public String getRechargeUrl(Map<String, String> params);
    public String getRoleId(Map<String, String> params);


}
