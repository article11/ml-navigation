package com.moloong.web.dao;

import com.moloong.web.bean.Admin;
import com.moloong.web.bean.Permissions;
import com.moloong.web.bean.User;
import com.moloong.web.common.util.DbUtil;
import com.moloong.web.common.util.HttpClientUtil;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
public class UserDao {

    public static UserDao getInstance() {
        return instance;
    }

    private static UserDao instance = new UserDao();

    private UserDao() {
    }


    public List<User> getUser() {
        String sql = "SELECT * from user ";
        List<User> res = DbUtil.getListBean(sql, User.class);
        return res;
    }


    public Permissions getPermissions(int uid, String gid) {
        String sql = "SELECT * from permissions WHERE uid= ? and gid=?";
        Permissions res = DbUtil.getBean(sql, Permissions.class, uid, gid);
        return res;
    }


    public int getPermissions2(int uid, String gid, String field) {
        String sql = "SELECT " + field + " from permissions WHERE uid= ? and gid=?";
        int res = DbUtil.getInt(sql, uid, gid);
        return res;
    }


    //返回用户id
    public String existIp(String ip) {
        String sql = "SELECT id from user WHERE ip=?";
        String res = DbUtil.getBean(sql, String.class, ip);
        return res;
    }

    //返回用户id
    public int existIp2(String ip) {
        String sql = "SELECT id from user WHERE ip=?";
        User userId = DbUtil.getBean(sql, User.class, ip);
        int res = 0;
        if (userId != null) {
            res = userId.getId();
        }
        return res;
    }


    //返回管理员id
    public String existAdminIp(String ip) {
        String sql = "SELECT id from admin WHERE ip=?";
        String res = DbUtil.getBean(sql, String.class);
        return res;
    }


    //返回管理员id
    public int existAdminIp2(String ip) {
        String sql = "SELECT id from admin WHERE ip=?";
        Admin adminId = DbUtil.getBean(sql, Admin.class, ip);
        int res = 0;
        if (adminId != null) {
            res = adminId.getId();
        }
        return res;
    }

    public String getRoleId(String urlstring) {
        return HttpClientUtil.getHttps(urlstring);
    }


    public String getRoleId2(String urlstring) {
        URL url = null;
        String res = "";
        try {
            url = new URL(urlstring);
            Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
            int c;
            while ((c = reader.read()) != -1) {
                res+=(char) c;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            res=new String(res.getBytes(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return res;
    }

}
