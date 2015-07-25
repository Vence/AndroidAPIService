package com.shenj.teworksandroid.http.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Http请求抽象类
 * 
 * @author 王文路
 * @date 2015-7-25
 */
public abstract class SJHttpRequest {
	
	protected static Log log = LogFactory.getLog(SJHttpRequest.class);  
	
	private static  String httpHost = HttpAPIUtil.HOST;
	private static int httpPort = HttpAPIUtil.PORT;
	private static String protocol = HttpAPIUtil.PROTOCOL;
	
	public SJHttpRequest() {
		super();
	}


	/**
	 * 发送请求
	 * 
	 * @author 王文路
	 * @date 2015-7-21
	 * @param httpUrl :请求接口
	 * @param httpArg  :参数
	 * @return
	 */
	public ResultInfo request() {
		
		ResultInfo info = null;
		
		HttpClient httpClient= new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");
		
		HttpMethod method = getMethod();
		
		try {
			
			httpClient.getHostConfiguration().setHost(httpHost, httpPort,protocol);

			httpClient.executeMethod(method);

			//执行Method,调用http接口
			int statusCode = httpClient.executeMethod(method);
			
			System.out.println(method.getURI());
			if(statusCode != HttpStatus.SC_OK){                
	                log.error("Method failed: " + method.getStatusLine());  
	                return info;  
	        } 
			 
			//读取内容
			String res = method.getResponseBodyAsString().trim();
			res = res.replaceAll("'", "\"");
			
			info = JsonBinder.getInstance().fromJson(res, ResultInfo.class);
			
		} catch (Exception e) {

		}
		finally{
			//释放连接
			method.releaseConnection();
		}
		
		return info;
	}
	
	
	public abstract HttpMethod getMethod() ;
	
	/**
	 * 拼接查询字符串
	 * 
	 * @author 王文路
	 * @date 2015-7-25
	 * @param params
	 * @return
	 */
	protected String getQueryParams(Map<String,Object> params) {
		
		String ret = null;
		
		if (params == null)
			return "";
		
		Set<String> keys = params.keySet();
		if (keys.size() == 0)
			return "";
		
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			
			String key = (String) it.next();
			
			if (ret == null) {
				
				ret = "?" + key + "=" + params.get(key);
			
			} else {
				ret += "&" + key +"=" + params.get(key);
			}
			
		}
		
		return ret;
	}
	
	/**
	 * 请求参数，封装成NameValuePair
	 * 
	 * @author 王文路
	 * @date 2015-7-25
	 * @param params
	 * @return
	 */
	protected NameValuePair[] getParams(Map<String,Object> params) {
		
		if (params == null)
			return null ;
		
		Set<String> keys = params.keySet();
		if (keys.size() == 0)
			return null;
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			
			String key = (String) it.next();
			Object obj = params.get(key);
			
			if (key == null || obj == null )
				continue;
			
			NameValuePair pair = null;
			
			/**
			 * 如果是自定义类，就进行json转换成字符串
			 */
			if (obj.getClass().getPackage().getName().matches(".*teworks.*")){
				
				pair = new NameValuePair(key , JsonBinder.getInstance().toJson(obj));
			} else {
				pair = new NameValuePair(key , (String)obj);
			}
			
			pairs.add(pair);
		}
		
		NameValuePair[] rets  = new NameValuePair[pairs.size()];
		pairs.toArray(rets);
		
		return rets;
	}
	
	public static void main(String[] args){
		Map<String,Object> map = new HashMap();
		
		map.put("user", "wanghuiyi@expogroup.sh.cn");
		map.put("name", "王慧艺");
		
	}

}
