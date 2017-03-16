package org.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 通用工具类
 * 
 * @author marshall
 */
public class Utils {
	/**
	 * 保存的文件路径 根目录
	 */
	// public final static String BASESRC =
	// "d:\\Tomcat 7.0\\webapps\\CellBank\\upload\\";
	// public final static String BASEURL =
	// "http://192.168.1.102:8080/CellBank/upload/";
	public final static String BASESRC = "/opt/apache-tomcat-7.0.70/webapps/CellBank/upload/";
	public final static String BASEURL = "http://120.76.22.150:8080/CellBank/upload/";

	/**
	 * 将数据封装成目标格式
	 * 
	 * @param entityList
	 *            实体类的数组
	 * @param total
	 *            数据总和
	 * @return
	 */
	public static Object setToResult(List entityList, long total) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("data", entityList);
		map2.put("total", total);
		map1.put("value", map2);
		return map1;
	}

	public static Object setToResult1(List entityList) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("data", entityList);
		map1.put("value", map2);
		return map1;
	}

	/**
	 * 将结果按格式输出
	 * 
	 * @param code
	 *            提示码
	 * @param msg
	 *            给用户的提示信息，没有则为空串
	 * @param data
	 *            返回给客户端的数据
	 * @return
	 */
	public static JSONObject toJson(int code, String msg, Object data) {
		JSONObject json = new JSONObject();
		json.element("code", code);
		json.element("msg", msg);
		json.element("data", data);
		return json;
	}

	/**
	 * 构成items的JsonObject
	 */
	public JSONObject toJson(){
		JSONObject json = new JSONObject();
		json.element("items", this.jsonArray);
		return json;
	}

	public void add(JSONObject JsonObj) {
		this.jsonArray.add(JsonObj);
	}

	public Utils() {
		this.jsonArray = new JSONArray();
	}
	private JSONArray jsonArray;

	/**
	 * 删除文件
	 */
	public static boolean delFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 判空之后转成空串
	 */
	public static Object isNull(Object a) {
		if (a == null) {
			return "";
		} else {
			return a;
		}
	}

	/**
	 * 判断是否为Integer
	 */
	public static boolean isInteger(String a) {
		try {
			Integer.parseInt((String) a);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 转换Integer
	 */
	public static Integer parseInt(String a) {
		try {
			return Integer.parseInt((String) a);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 判断是否为Long
	 */
	public static boolean isLong(String a) {
		try {
			Long.parseLong((String) a);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * String转Long,异常输出0
	 */
	public static Long parseLong(String a) {
		try {
			return Long.parseLong((String) a);
		} catch (Exception e) {
			e.printStackTrace();
			return (long) 0;
		}
	}

	/**
	 * 批量删除文件
	 */
	public static boolean delFile(List<String> urlList) {
		boolean a = true;
		if (urlList != null && urlList.size() > 0) {
			for (String url : urlList) {
				if (!Utils.delFile(url.replace(Utils.BASEURL, Utils.BASESRC))) {
					System.out.println("删除图片失败了");
					a = false;
				}
			}
			return a;
		} else {
			System.out.println("删除的话题或评论中没有包含任何图片");
			return true;
		}
	}
}
