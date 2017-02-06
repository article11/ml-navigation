package com.moloong.web.servlet;

import com.moloong.web.common.config.CommonConst;
import com.moloong.web.service.LoginMkerService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/3.
 */

//@WebServlet("loginmaker")
@WebServlet(displayName ="loginmaker",urlPatterns = "/loginmaker")
public class LoginMakerServlet extends BaseServlet {

    protected Logger logger = Logger.getLogger(this.getClass());


    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameid = request.getParameter("gameid");
        String gamename = request.getParameter("gamename");
        String agent = request.getParameter("agent");
        String username = request.getParameter("username");
        String serverid = request.getParameter("serverid");
        String test = request.getParameter("test");
        Map<String, String> params = new HashMap<String, String>();
        params.put(CommonConst.gameid, gameid);
        params.put(CommonConst.gamename, gamename);
        params.put(CommonConst.agent, agent);
        params.put(CommonConst.username, username);
        params.put(CommonConst.serverid, serverid);
        params.put("test", test);
//        String loginurl = LoginMkerService.getInterstance().getLoginUrl(params);

        String loginurl = null;
        try {
            loginurl = LoginMkerService.getInterstance().getLoginUrl(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/text;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(loginurl);
        pw.flush();
    }


}



