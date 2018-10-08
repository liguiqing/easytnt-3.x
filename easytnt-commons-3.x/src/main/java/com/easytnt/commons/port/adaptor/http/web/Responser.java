/** 
 * Project Name:easytnt-commons 
 * File Name:Responser.java 
 * Package Name:com.easytnt.commons.controller 
 * Date:2016年3月25日下午2:51:10 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.easytnt.commons.port.adaptor.http.web;

/**
 * ClassName: Responser <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年3月25日 下午2:51:10 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class Responser {
	public final static String ModelName = "status";

	private boolean success;

	private String code;

	private String msg;

	public static class Builder {
		private Responser responser;

		public Builder() {
			this.responser = new Responser();
		}

		public Builder success() {
			this.responser.success = Boolean.TRUE;
			return this;
		}

		public Builder failure() {
			this.responser.success = Boolean.FALSE;
			return this;
		}

		public Builder msg(String msg) {
			this.responser.msg = msg;
			return this;
		}

		public Builder code(String code) {
			this.responser.code = code;
			return this;
		}

		public Responser create() {
			return this.responser;
		}
	}

	public boolean isSuccess() {
		return success;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
