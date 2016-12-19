package com.moloong.web.servlet;

import com.moloong.web.common.config.CommonConst;
import com.moloong.web.service.UserqueryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/8.
 */

//@WebServlet("userqueryServlet")
@WebServlet(displayName ="userqueryServlet",urlPatterns = "/userqueryServlet")
public class UserqueryServlet extends BaseServlet {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String gameid = request.getParameter("gameid");
        String gamename = request.getParameter("gamename");
        String agent = request.getParameter("agent");
        String username = request.getParameter("username");
        String serverid = request.getParameter("serverid");

        Map<String, String> params = new HashMap<String, String>();
        params.put(CommonConst.gameid, gameid);
        params.put(CommonConst.gamename, gamename);
        params.put(CommonConst.agent, agent);
        params.put(CommonConst.username, username);
        params.put(CommonConst.serverid, serverid);


        String res = UserqueryService.getInstance().getRoleId(params);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write(res);
        pw.flush();

    }
}
