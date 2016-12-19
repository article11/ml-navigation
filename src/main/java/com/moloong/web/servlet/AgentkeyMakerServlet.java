package com.moloong.web.servlet;

import com.moloong.web.service.PlatFormService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */

//@WebServlet("agentkeymaker")
@WebServlet(displayName = "agentkeymaker", urlPatterns = "/agentkeymaker")
public class AgentkeyMakerServlet extends BaseServlet {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
        }
        map.remove("agent");
        map.remove("gamename");
        int res=0;
        String data;
        res = PlatFormService.getInstance().insertPlat(request.getParameter("gamename"),map);
        if (res == 1) {
            data = "添加成功";
        } else {
            data = "添加失败";
        }
        response.setContentType("application/text;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(data);
        pw.flush();
    }
}
