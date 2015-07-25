package com.shenj.teworksandroid.http.common;

/**
 * TEWorksAPI 工具类
 * 
 * @author 王文路
 * @date 2015-7-25
 */
public class HttpAPIUtil {
	
	public static String HOST = "127.0.0.1";
	public static int PORT = 80;
	public static String PROTOCOL = "http";
	private static String CONTEXT_PATH = "/test-web";
	
	/**
	 * 组织API
	 */
	public static String DOMAIN_GETBYUSER_API = "/api/domains";
	public static String DOMAIN_GETCURRENT_API = "/api/%s/domains/current";
	
	/**
	 * 登陆API
	 */
	public static String LOGIN_API =  "/api/%s/loginnocaptcha";
	
	
	
	/**
	 * 获取api，拼接contextPath, 如果api路径中包含参数，替换掉
	 * 
	 * @author 王文路
	 * @date 2015-7-25
	 * @param api
	 * @param args
	 * 			api中的pathVariable变量
	 * @return
	 */
	public static String getRequestAPI (String api , Object... args){
		
		api = CONTEXT_PATH + api;
		
		if (args == null || args.length == 0)
			return api;
		
		return String.format(api, args);
	}

}
