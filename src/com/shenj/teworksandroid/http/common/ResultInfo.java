package com.shenj.teworksandroid.http.common;

/**
 * 返回结果
 * 
 * @author 王文路
 * @date 2015-7-25
 * @param <T>
 */
public class ResultInfo<T> {

	/**
	 * 
	 */
	private int errorCode;
	
	/**
	 * 错误信息
	 */
	private String errorMsg;
	
	/**
	 * 没有错误，返回结果
	 * 有错误，结果为null
	 */
	private T result;
	
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getResult() {
		
		if (result instanceof String) {
			return (String)result;
		} 
		
		return JsonBinder.getInstance().toJson(result);
	}
	public void setResult(T result) {
		this.result = result;
	}
	
}
