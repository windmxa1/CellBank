package org.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.RoleDao;
import org.dao.UserDao;
import org.dao.imp.RoleDaoImp;
import org.dao.imp.UserDaoImp;
import org.imodel.VeUserinfoId;
import org.model.User;
import org.model.UserRole;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport implements ServletRequestAware,
ServletResponseAware {
	private static final long serialVersionUID = 1L;

	HttpServletRequest request = null;

	HttpServletResponse response = null;
	
	/**
	 * 返回专家列表
	 */
	public void getExperts(){
		UserDao uDao = new UserDaoImp();
		List<VeUserinfoId> list = uDao.getUserInfo(4L);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("experts", list);
		if(list!=null){
			responseMS(Utils.toJson(100, "", jsonObject));
		}else {
			responseMS(Utils.toJson(101, "当前请求过多，可能会导致部分请求延时", new JSONObject()));
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
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
}
