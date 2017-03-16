package org.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class IMAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public void getGroupList(){
		IMSDK imsdk = new IMSDK();
//		Map<String,Object> result = new HashMap<String, Object>();
		Object list= imsdk.getGroupList();
		if(list.equals("")){
			responseMS(Utils.toJson(101, "请求获取群组列表失败，请稍后重试", ""));
		}else {
			responseMS(Utils.toJson(100, "", list));
		}
	}
	/**
	 * responseMS 响应请求 返回JsonArray
	 */
	private void responseMS(JSONObject json) {
		try {
			response.setContentType("text/html;charset=utf-8");
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
