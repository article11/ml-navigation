package com.moloong.web.servlet;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author:yuan-hai
 * date:2016/10/10 9:42
 * description:
 */
public abstract class BaseServlet extends HttpServlet{

    protected Logger logger = Logger.getLogger(this.getClass());

    //异步处理超时时长120s
    private static final long ASYNC_TIMEOUT = 120*1000;

    //线程池核心线程数
    private static int coreThreadSize = 5;

    //线程池最大线程数
    private static int maxThreadSize = 200;

    //线程池线程空闲时间
    private static long keepAliveTime = 1;

    //异步处理线程池
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(coreThreadSize,maxThreadSize,keepAliveTime,TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());

    @Override
    protected void doPost(final HttpServletRequest req,final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        writeRequestLog(req);
        //判断是否开启异步支持
        if(req.isAsyncSupported()){
            //创建AsyncContext，开始异步调用
            final AsyncContext asyncContext = req.startAsync();
            //设置异步调用的超时时长
            asyncContext.setTimeout(ASYNC_TIMEOUT);
            asyncContext.addListener(new myAsyncListener());
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        handleRequest(req, resp);
                    } catch (Exception e) {
                        logger.error("异步Servlet出错：",e);
                    } finally{
                        asyncContext.complete();
                    }
                }
            });
        }else{
            try {
                handleRequest(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void doGet(final HttpServletRequest req,final HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    public boolean checkParameter(String ... params){
        boolean result = true;
        for (String param : params) {
            if(StringUtils.isBlank(param)){
                result  = false;
                logger.info("请求参数存在空值");
                break;
            }
        }
        return result;
    }

    private void writeRequestLog(HttpServletRequest request){
        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuffer sb = new StringBuffer("收到请求：");
        for (String key : paramMap.keySet()) {
            String[] valueArray = paramMap.get(key);
            for (String  value: valueArray) {
                sb.append(key+"="+value+",");
            }
        }
        logger.info(sb.toString());
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    protected abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception;


    public class myAsyncListener implements AsyncListener{

        private Logger logger = Logger.getLogger(myAsyncListener.class);

        @Override
        public void onComplete(AsyncEvent event) throws IOException {

        }

        @Override
        public void onTimeout(AsyncEvent event) throws IOException {
            logger.error("异步Servlet超时");
        }

        @Override
        public void onError(AsyncEvent event) throws IOException {

        }

        @Override
        public void onStartAsync(AsyncEvent event) throws IOException {

        }
    }

    public static void main(String[] args) {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        String location = null;
        for(int i=0;i<stacks.length;i++){
            location = i+"  at "+stacks[i].getClassName() + "." + stacks[i].getMethodName()
                    + "(" + stacks[i].getFileName() + ":"
                    + stacks[i].getLineNumber() + ")";
            System.out.println(location);
        }
    }


}
