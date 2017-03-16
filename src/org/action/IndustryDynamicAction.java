package org.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.IndustryDynamicDao;
import org.dao.imp.IndustryDynamicDaoImp;
import org.model.IndustryDynamic;
import org.util.ChangeTime;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({ "unused", "unchecked" })
public class IndustryDynamicAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request = null;

	HttpServletResponse response = null;

	/**
	 * 1.APP首页上的几张轮换图片替换
	 */
	public void replaceApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(1);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("LoopImages", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 2.引导页图片替换
	 */
	public void guideReplaceApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(2);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("GuideImage", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 4.专家团队： 图片头像替换，内容替换
	 */
	public void expertTeamApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(4);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("ExpertTeams", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 5.产品介绍： 内容替换，允许插入图片
	 */
	public void productIntroductionApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(5);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("Products", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 6.动态读取行业动态
	 */
	public void industryNewsApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.industryNews();
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("News", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 7.滚动条
	 */
	public void theScrollBarApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(7);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("ScrollBar", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 9.临床案例
	 */
	public void clinicalCaseApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(9);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("Cases", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 10.公开课
	 */
	public void publicClassApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(10);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("Lessions", list);
		responseMS(Utils.toJson(100, "", jsonObject));
	}

	/**
	 * 11.健康小知识
	 */
	public void healthKnowledgeApp() {
		Map<String, String> message = new HashMap<String, String>();
		IndustryDynamicDao industryDynamicDao = new IndustryDynamicDaoImp();
		List<IndustryDynamic> list = industryDynamicDao.query(11);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("HealthTips", list);
		responseMS(Utils.toJson(100, "", jsonObject));
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
