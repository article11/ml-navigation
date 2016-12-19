/**
 * Copyright (c) 2015, http://www.moloong.com/ All Rights Reserved. 
 * 
 */
package com.moloong.web.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);
	/* http,https* */
	private static CloseableHttpClient httpClient = null;
	/* 使用https，但没证书的（需要绕过证书）* */
	private static CloseableHttpClient httpClientWithoutAuth = null;

	public static String get(String host, String path, List<NameValuePair> params) {
		URI uri = null;
		try {
			uri = new URIBuilder().setScheme("http").setHost(host).setPath(path).setParameters(params).build();
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(new HttpGet(uri));
			// 获取返回数据
			return EntityUtils.toString(httpresponse.getEntity(), "UTF8");
		} catch (Exception e) {
			log.error("验证url: " + uri == null ? "" : uri);
			log.error(e, e);
		}
		return "";
	}

	public static String get(String URL, String cookies) {
		try {
			HttpGet httpGet = new HttpGet(URL);
			if (cookies != null && !cookies.isEmpty()) {
				httpGet.setHeader("Cookie", cookies);
			}
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(httpGet);
			// 获取返回数据
			return EntityUtils.toString(httpresponse.getEntity(), "UTF8");
		} catch (Exception e) {
			log.error("验证url: " + URL);
			log.error(e, e);
		}
		return "";
	}

	public static String post(String host, List<NameValuePair> params) {
		try {
			HttpPost httpPost = new HttpPost(host);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			CloseableHttpResponse execute = httpClient.execute(httpPost);
			return EntityUtils.toString(execute.getEntity(), "UTF8");
		} catch (Exception e) {
			log.error(e, e);
		}
		return "";
	}

	public static String postEx(String host, String urlParam, String params) {
		try {
			HttpPost httpPost = new HttpPost(host + "?" + urlParam);
			httpPost.setEntity(new StringEntity(params, "UTF8"));
			CloseableHttpResponse execute = httpClient.execute(httpPost);
			return EntityUtils.toString(execute.getEntity(), "UTF8");
		} catch (Exception e) {
			log.error(e, e);
		}
		return "";
	}

	public static String getHttps(String host, Map<String, String> params) {
		String fullUrl = host + "?" + buildHttpsParameter(params);
		try {
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(new HttpGet(fullUrl));
			// 获取返回数据
			return EntityUtils.toString(httpresponse.getEntity(), "UTF8");
		} catch (Exception e) {
			log.error(e, e);
		}
		return "";
	}

	public static String getHttps(String url) {
		try {
			// 发送请求
			HttpResponse httpresponse = httpClient.execute(new HttpGet(url));
			// 获取返回数据
			return EntityUtils.toString(httpresponse.getEntity(), "UTF8");
		} catch (Exception e) {
			log.error(e, e);
		}
		return "";
	}

	public static String postJson(String host, String content) {
		HttpPost httpPost = new HttpPost(host);
		StringEntity entity = new StringEntity(content, "utf-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		try {
			return EntityUtils.toString(httpClient.execute(httpPost).getEntity());
		} catch (IOException e) {
			log.error(e, e);
		}
		return "";
	}

	public static String httpsGetWithoutAuth(String url) {
		try {
			// 发送请求
			HttpResponse httpresponse = httpClientWithoutAuth.execute(new HttpGet(url));
			// 获取返回数据
			return EntityUtils.toString(httpresponse.getEntity(), "UTF8");
		} catch (Exception e) {
			log.error(e, e);
		}
		return "";
	}

	static{
		RequestConfig requestCfg = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(Runtime.getRuntime().availableProcessors() * 2);
		httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestCfg).build();

		// 初始化需要绕过https证书的连接池
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
			// 初始化连接管理器
			PoolingHttpClientConnectionManager cmHttps = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			cmHttps.setMaxTotal(Runtime.getRuntime().availableProcessors());
			httpClientWithoutAuth = HttpClients.custom().setConnectionManager(cmHttps).setDefaultRequestConfig(requestCfg).build();
		} catch (Exception e) {
			log.error(e, e);
		}
        log.error("平台对接HTTPCLIENT初始化完成...");
	}

	/**
	 * https参数编码
	 */
	public static String buildHttpsParameter(Map<String, String> data) {
		String ret = "";
		String k, v;
		Iterator<String> iterator = data.keySet().iterator();
		while (iterator.hasNext()) {
			k = iterator.next();
			v = data.get(k);
			try {
				ret += URLEncoder.encode(k, "utf8") + "=" + URLEncoder.encode(v, "utf8");
			} catch (UnsupportedEncodingException e) {
			}
			ret += "&";
		}
		return ret.substring(0, ret.length() - 1);
	}
}