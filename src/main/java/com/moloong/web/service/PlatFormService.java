package com.moloong.web.service;

import com.moloong.web.bean.Platform;
import com.moloong.web.dao.PlatformDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */
public class PlatFormService {
    public static PlatFormService getInstance() {
        return instance;
    }

    private static PlatFormService instance = new PlatFormService();

    private PlatFormService() {
    }


    public List<Platform> getPlatformList(String gamename,String gameId) {
        List<Platform> result = null;
        try {
            result = PlatformDao.getInstance().getPlatformList(gamename, gameId);
        } catch (Exception e) {
            return result;
        }
        return result;
    }


    public Platform getPlatform(String gamename,String gameId, String platformName) {
        Platform result = null;
        try {
            result = PlatformDao.getInstance().getPlatform(gamename, gameId, platformName);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    public int insertPlat(String gamename,Map<String,String> map) {
        int result = 0;
        try {
            result = PlatformDao.getInstance().insertPlat(gamename,map);
        } catch (Exception e) {
            return result;
        }
        return result;
    }
}
