package org.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.IconDao;
import org.dao.LumpDao;
import org.dao.RoleDao;
import org.dao.imp.IconDaoImp;
import org.dao.imp.LumpDaoImp;
import org.dao.imp.RoleDaoImp;
import org.model.Lump;
import org.model.UserRole;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class ActivityAction extends ActionSupport implements ServletResponseAware,
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String userid;
	private RoleDao roleDao;
	private LumpDao lumpDao;
	private IconDao iconDao;
	/**
	 * 根据用户role获取功能菜单
	 */
	public void getLumps() {
		Long id= Utils.parseLong(userid);
		if (id != 0) {
			roleDao = new RoleDaoImp();
			lumpDao = new LumpDaoImp();
			UserRole userRole = roleDao.findByUserId(id);
			List<Lump> list = lumpDao.getLumps(userRole.getRoleId());
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("lumps", list);
			responseMS(Utils.toJson(100, "", jsonObject));
		}else {//数据异常
			responseMS(Utils.toJson(101, "", new JSONObject()));
		}
	}
	/**
	 * 插入Icon
	 */
	public String addIcon(){
		iconDao = new IconDaoImp();
		
		return SUCCESS;
	}
	/**
	 * 删除Icon
	 */
	public String delIcon(){
		iconDao = new IconDaoImp();
		
		return SUCCESS;
	}
	/**
	 * 修改Icon
	 */
	public String updateIcon(){
		iconDao = new IconDaoImp();
		
		return SUCCESS;
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
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
