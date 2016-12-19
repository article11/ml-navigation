package com.moloong.web.filter;

import com.moloong.web.bean.Permissions;
import com.moloong.web.common.util.Until;
import com.moloong.web.dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/3.
 */


@WebFilter(filterName = "UserFilter", urlPatterns = "/*")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ip = Until.getIpAddr((HttpServletRequest) servletRequest);
        String gameid = servletRequest.getParameter("gameid");
        int userid = UserDao.getInstance().existIp2(ip);
        int existAdminIp = UserDao.getInstance().existAdminIp2(ip);


        System.out.println("userid"+userid);
        System.out.println("ip"+ip);
        System.out.println("existAdminIp"+existAdminIp);


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String url = req.getRequestURI();
        if (existAdminIp != 0) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (url.contains("fail")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (userid != 0) {
            Permissions user = UserDao.getInstance().getPermissions(userid, gameid);
            if (url.contains("login")) {
                int login = user.getLogin();
                if (login == 1) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    ((HttpServletResponse) servletResponse).sendRedirect("fail.jsp");
                }
            } else if (url.contains("recharge")) {
                int recharge = user.getRecharge();
                if (recharge == 1) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    ((HttpServletResponse) servletResponse).sendRedirect("fail.jsp");
                }
            } else if (url.contains("agentkey")) {
                int agentKey = user.getAgentKey();
                if (agentKey == 1) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    ((HttpServletResponse) servletResponse).sendRedirect("fail.jsp");
                }
            } else if (url.contains("mediacard")) {
                int mediacard = user.getMediaCard();
                if (mediacard == 1) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    ((HttpServletResponse) servletResponse).sendRedirect("fail.jsp");
                }
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("fail.jsp");
            }
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("fail.jsp");
        }
    }

    @Override
    public void destroy() {
    }


}
