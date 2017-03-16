package org.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.RoleDao;
import org.dao.UserDao;
import org.dao.UserDetailDao;
import org.dao.imp.RoleDaoImp;
import org.dao.imp.UserDaoImp;
import org.dao.imp.UserDetailDaoImp;
import org.model.User;
import org.model.UserRole;
import org.util.ChangeTime;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String phone;
	private String username;
	private String password;
	private String role;
	private UserDao userDao;
	private RoleDao roleDao;
	private UserDetailDao uDetailDao;

	public void register() {
		Map<String, String> message = new HashMap<String, String>();
		try {
			if (username != null && phone != null && password != null) {
				request.setCharacterEncoding("utf-8");
				username = new String(username.getBytes("ISO-8859-1"), "utf-8");
				System.out.println(phone);
				System.out.println(username);
				System.out.println(password);

				this.response.setCharacterEncoding("UTF-8");
				userDao = new UserDaoImp();
				roleDao = new RoleDaoImp();
				String current = ChangeTime.timeStamp();
				User user = new User(Long.parseLong(phone), password,
						Integer.parseInt(current));

				User u = userDao.findUserByPhone(phone);
				if (u != null) {
					message.put("message", "error");
					message.put("description", "该手机号已注册，请登录");
				} else {
					Long userid = userDao.Register(user);
					if (userid > 0) {
						if (!Utils.isLong(role)) {
							role = "1";
							message.put("extral1", "需要传入role,默认role=1");
						}
						UserRole userRole = new UserRole(userid,
								Long.parseLong(role));
						roleDao.insert1(userRole);
						uDetailDao = new UserDetailDaoImp();
						if (uDetailDao.insert(username, userid)) {
						} else {
							message.put("extral", "注册成功但未保存详细信息");
						}
						message.put("message", "success");
						message.put("description", "注册成功");
					} else {
						message.put("message", "error");
						message.put("description", "注册失败");
					}
				}
			} else {
				message.put("message", "error");
				message.put("extral", "请检查输入参数，可能缺少必要参数role,role为1/2/3");
				message.put("description", "该版本可能已不适用,请下载最新应用");
			}
			message.put(
					"parms",
					"username=" + Utils.isNull(username) + "&phone="
							+ Utils.isNull(phone) + "&password"
							+ Utils.isNull(password));
			JSONArray JsonArry = JSONArray.fromObject(message);
			JSONObject json = new JSONObject();
			json.element("JsonArry", JsonArry);
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
