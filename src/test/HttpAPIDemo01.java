package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpAPIDemo01 {
	public static void main(String[] args) {
		String SID = "E534FF960FAA10FE";
		String pathUrl = "http://www.yunplc.com:7080/exdata?SID=" + SID
				+ "&OP=R";
		String requestString = "11\r\nL_LOW_ALARM\r\nL_HIGH_ALARM\r\nT_H_ALARM\r\nT_HH_ALARM\r\nLEVEL\r\nTEMPERATURE1\r\ntemperature2\r\ntemperature3\r\ntemperature4\r\nvessel_temp\r\nSample_temp\r\n"; 
//		String requestString = "GRM=20437182687&PASS=123456";
		String requestType ="readData"; 
		String result = connect(pathUrl, requestString, requestType);
		System.out.println(result);
//		if(result.equals("ERROR")){
////			pathUrl = ""
//			requestType ="login"; 
//			connect(pathUrl,requestString,requestType);
//		}
	}

	public static String connect(String pathUrl, String requestString,String requestType) {
//		pathUrl = "http://www.yunplc.com:7080/exlog";
		// 建立连接
		URL url;
		try {
			url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
//			requestString = "GRM=20437182687&PASS=123456";
			byte[] requestStringBytes = requestString.getBytes("utf-8");
			httpConn.setRequestProperty("Content-length", ""
					+ requestStringBytes.length);
			httpConn.setRequestProperty("Content-Type",
					"application/octet-stream");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "utf-8");
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
				Integer Count = 0;
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
					Count++;
					if(Count==3&&requestType.equals("login")){
//						SID = readLine.replace("SID=", "");
						result = readLine.replace("SID", "");
						System.out.println("SID = " + result);
					}
				}
				responseReader.close();
				System.out.println(sb);
				// tv.setText(sb.toString());
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
}
