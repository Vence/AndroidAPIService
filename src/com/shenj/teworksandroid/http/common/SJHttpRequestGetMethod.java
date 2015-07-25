package com.shenj.teworksandroid.http.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.shenj.teworks.pojo.domain.Domain;

/**
 * Http请求： Get
 * 
 * @author 王文路
 * @date 2015-7-25
 */
public class SJHttpRequestGetMethod extends SJHttpRequest{

	private String url;
	private Map<String,Object> params;
	
	public SJHttpRequestGetMethod(String url, Map<String, Object> params) {
		super();
		
		this.url = url;
		this.params = params;
	}



	@Override
	public HttpMethod getMethod() {  
		
        GetMethod get = new GetMethod(url + getQueryParams(params));  
     
        return get;  
    }  
	
	
	
	public static void main(String[] args){
		
		Map params = new HashMap();
		params.put("user" , "wanghuiyi@expogroup.sh.cn");
		
		String url = HttpAPIUtil.getRequestAPI(HttpAPIUtil.DOMAIN_GETBYUSER_API);
		
		SJHttpRequestGetMethod getRequest = new SJHttpRequestGetMethod(url ,
				params);
		
		ResultInfo info = getRequest.request();
		
		System.out.println(info.getErrorCode());
		System.out.println(info.getErrorMsg());
		System.out.println(info.getResult());
		
		Domain[] domains = 
				JsonBinder.getInstance().fromJson(info.getResult(), Domain[].class);
		
		if (domains != null){
			for (Domain domain : domains) {
				System.out.println(domain.getName());
			}
		}
	}
}
