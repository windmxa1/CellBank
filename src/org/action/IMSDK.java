package org.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.TokenDao;
import org.dao.imp.TokenDaoImp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class IMSDK {
	private TokenDao tDao;

	/**
	 * 获取token并注册用户
	 */
	public void register(String username) {
		tDao = new TokenDaoImp();
		while (true) {
			String access_token = tDao.getToken();
			String code = register(access_token, username, "123456");
			if (code.equals("" + 200)) {
				break;
			} else {
				getToken();
			}
		}
	}

	/**
	 * 
	 */
	public Object getGroupList() {
		Object result = "" ;
		tDao = new TokenDaoImp();
		while (true) {
			String access_token = tDao.getToken();
			String code = getGroupList(access_token, "", "");
			if (code.equals("400")) {
				getToken();
			} else {
				JSONArray jsonArray = JSONObject.fromObject(code).getJSONArray("data");
				result = jsonArray;
				break;
			}
		}
		return result;

	}

	// public static void main(String[] args) {
	// // access_token =
	// // "YWMtSkafzPGIEeaemB8-JDA4qQAAAVtp_OqodhTb1nCvRk-4p-eeUeAEH4sOjlg";
	// IMSDK imsdk = new IMSDK();
	// Long start = System.currentTimeMillis();
	// imsdk.getGroupList();
	// Long end = System.currentTimeMillis();
	// System.out.println("耗时：" + (end - start) + "毫秒");
	// }

	private String getGroupList(String accessToken, String limit, String cursor) {
		String url = "http://a1.easemob.com/1182170210178310/lifebank/chatgroups";
		Map<String, Object> params = new HashMap<>();
		params.put("limit", limit);
		params.put("cursor", cursor);
		return doGet(url, accessToken);
	}

	private String register(String accessToken, String username, String password) {
		String url = "http://a1.easemob.com/1182170210178310/lifebank/users";
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);
		String paramString = JSONObject.fromObject(params).toString();
		return connect(url, paramString, accessToken, 1);
	}

	private String getToken() {
		Map<String, Object> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("client_id", "YXA6ZxVecO9zEeaFaOl-HFwNNw");
		params.put("client_secret", "YXA6wQ-ye-9xG3XCWpEb8_NioFvR19c");
		String paramString = JSONObject.fromObject(params).toString();
		// String requestString =
		// "grant_type=\"client_credentials\"&client_id=\"YXA6ZxVecO9zEeaFaOl-HFwNNw\"&client_secret=\"YXA6wQ-ye-9xG3XCWpEb8_NioFvR19c\"";
		String pathUrl = "http://a1.easemob.com/1182170210178310/lifebank/token";
		return connect(pathUrl, paramString, null, 0);
	}

	/**
	 * @param pathUrl
	 * @param requestString
	 * @param accessToken
	 * @param requestType
	 *            请求类型，0为获取token，1为注册
	 * @return
	 */
	private String connect(String pathUrl, String requestString,
			String accessToken, Integer requestType) {
		// 建立连接
		URL url;
		try {
			url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setRequestMethod("POST");// 设置URL请求方法
			httpConn.setUseCaches(false);// 忽略缓存

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			// requestString = "GRM=20437182687&PASS=123456";
			byte[] requestStringBytes = requestString.getBytes("utf-8");
			httpConn.setRequestProperty("Content-length", ""
					+ requestStringBytes.length);
			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type", "application/json");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "utf-8");
			if (accessToken != null) {
				httpConn.setRequestProperty("Authorization", "Bearer "
						+ accessToken);
			}
			// 建立输出流，并写入数据
			OutputStream outputStream = httpConn.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();
			String result = "";
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "utf-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				System.out.println(sb.toString());
				JSONObject jsonObject = JSONObject.fromObject(sb.toString());
				if (requestType == 0) {
					result = jsonObject.getString("access_token");
					System.out.println(tDao.saveToken(result));
				}
				responseReader.close();
				if (requestType == 1) {
					result = "" + responseCode;
				}
			} else {
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getErrorStream(), "utf-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				System.out.println(responseCode);
				System.out.println("错误信息:" + sb.toString());
				if (requestType == 1) {
					if (sb.toString().contains(
							"DuplicateUniquePropertyExistsException")) {
						result = "" + 200;
						System.out.println("该用户已有im账号");
					}
				}
			}
			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * GET请求
	 */
	private String doGet(String url, String accessToken) {

		URL localURL;
		try {
			localURL = new URL(url);

			URLConnection connection = localURL.openConnection();
			HttpURLConnection urlConnection = (HttpURLConnection) connection;
			urlConnection.setUseCaches(false);// 忽略缓存
			urlConnection.setRequestProperty("Accept-Charset", "utf-8");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection
					.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			urlConnection.setRequestProperty("Charset", "utf-8");
			if (accessToken != null) {
				urlConnection.setRequestProperty("Authorization", "Bearer "
						+ accessToken);
			}
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader reader = null;

			StringBuffer resultBuffer = new StringBuffer();
			String tempLine = null;
			// 响应失败
			if (urlConnection.getResponseCode() == 200) {
				inputStream = urlConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				reader = new BufferedReader(inputStreamReader);

				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine).append("\n");
				}

			} else {
				resultBuffer.append("400");
			}
			if (reader != null) {
				reader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			return resultBuffer.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
