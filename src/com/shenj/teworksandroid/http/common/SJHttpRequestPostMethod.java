package com.shenj.teworksandroid.http.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Http请求：POST
 * 
 * @author 王文路
 * @date 2015-7-25
 */
public class SJHttpRequestPostMethod extends SJHttpRequest{
	
	private String url;
	private Map<String,Object> params;
	
	public SJHttpRequestPostMethod(String url, Map<String, Object> params) {
		super();
		
		this.url = url;
		this.params = params;
	}

	@Override
	public HttpMethod getMethod() {
		
		PostMethod post = new PostMethod(url);
		
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
        
        post.setRequestBody(getParams(params));
        return post;
	}
	
	
	
	public static void main(String[] args){
		
		Map params = new HashMap();
		params.put("username" , "wangwenlu@qq.com");
		params.put("password" , "123456");
		params.put("res" , "2");
		
		String domainID = "3A1CE923-EC4C-4307-BBAB-297B5015C864";
		
		String url = HttpAPIUtil.getRequestAPI(HttpAPIUtil.LOGIN_API, domainID);
		
		SJHttpRequestPostMethod postRequest = new SJHttpRequestPostMethod(url , params);
		ResultInfo info = postRequest.request();
		
		System.out.println(info.getErrorCode());
		System.out.println(info.getErrorMsg());
		System.out.println(info.getResult());
	}

}
