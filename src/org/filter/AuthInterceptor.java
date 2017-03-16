package org.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.model.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getInvocationContext().getName();
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		System.out.println("action name :" + actionName);
		User user = (User) session1.getAttribute("user");
		if (user != null) {
//			System.out.println(user.getUsername());
			return invocation.invoke();
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("description", "账号登录信息过时，请重新登录!");
		result.put("message", "error");
//		ActionContext.getContext().put("result", result);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			JSONObject json = new JSONObject();
			json.element("JsonArry", result);
			response.setContentType("text/html;charset=utf-8");
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// session.put("result", result);
		return Action.ERROR;
		// return invocation.invoke(); // 放行

	}
}
