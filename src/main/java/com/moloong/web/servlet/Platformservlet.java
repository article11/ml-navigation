package com.moloong.web.servlet;

import com.alibaba.fastjson.JSON;
import com.moloong.web.bean.Platform;
import com.moloong.web.service.PlatFormService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */

//@WebServlet("platform")
@WebServlet(displayName ="platform",urlPatterns = "/platform")
public class Platformservlet extends BaseServlet {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameId = request.getParameter("gameid");
        String gamename = request.getParameter("gamename");
        String agentkey="0";
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            if(paraName.equals("agentkey")){
                agentkey="1";
            }
        }
        if (agentkey.equals("1")) {
            String agent = request.getParameter("agent");
            Platform platform=PlatFormService.getInstance().getPlatform(gamename,gameId,agent);
            String[] resArray={platform.getPlatformLoginKey(),platform.getRechargeKey(),platform.getNewsCardKey()};
            String resjson= JSON.toJSONString(resArray);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(resjson);
            pw.flush();
        } else {
            List<Platform> platformList = PlatFormService.getInstance().getPlatformList(gamename,gameId);
            String option = "";
            for (Platform platform : platformList) {
                option += "<option value=\"" + platform.getPlatformAlias() + "\">" + platform.getPlatformAlias() + "&nbsp;&nbsp;(" + platform.getPlatformName() + ")</option>";
            }
            response.setContentType("application/text;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(option);
            pw.flush();
        }
    }
}
