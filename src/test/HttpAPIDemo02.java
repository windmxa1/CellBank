package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONArray;

import org.dao.ItemsDao;
import org.dao.imp.ItemsDaoImp;
import org.util.ChangeTime;

public class HttpAPIDemo02 implements ServletContextListener {
	private String SID = "B00F55D1932CB775";// 设置一个默认值
	private String GRM = "20437182687";
	private String ErrorNum = "";

	// public static void main(String[] args) {
	// while(1>0){

	// }
	// }
	public void function() throws InterruptedException {
		String pathUrl = "http://www.yunplc.com:7080/exdata?SID=" + this.SID
				+ "&OP=R";
		String requestString = "11\r\nL_LOW_ALARM\r\nL_HIGH_ALARM\r\nT_H_ALARM\r\nT_HH_ALARM\r\nLEVEL\r\nTEMPERATURE1\r\ntemperature2\r\ntemperature3\r\ntemperature4\r\nvessel_temp\r\nSample_temp\r\n";

		String requestType = "readData";
		String result = connect(pathUrl, requestString, requestType);
		if (result.equals("ERROR")) {
			if (ErrorNum.equals("8")) {
				pathUrl = "http://www.yunplc.com:7080/exlog";
				requestType = "login";
				requestString = "GRM=" + GRM + "&PASS=123456";
				this.SID = connect(pathUrl, requestString, requestType);
				function();
			}
			if(ErrorNum.equals("7")||ErrorNum.equals("9")||ErrorNum.equals("10")||ErrorNum.equals("14")){
				Thread.sleep(30*1000);
				function();
			}
		}
	}

	public String connect(String pathUrl, String requestString,
			String requestType) {
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
			// requestString = "GRM=20437182687&PASS=123456";
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
				List<String> resultList = new ArrayList<String>();
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
					Count++;
					if (Count == 3 && requestType.equals("login")) {// 因为登录不会出错所以不做多余判断
						// SID = readLine.replace("SID=", "");
						result = readLine.replace("SID=", "");
						System.out.println("SID = " + result);
					}
					if (requestType.equals("readData")) {
						if (Count == 1) {
							result = readLine;
						}
						if (result.equals("OK")) {
							resultList.add(readLine);
						}
						if (result.equals("ERROR") && Count == 2) {
							ErrorNum = readLine;
						}
					}

				}
				String jsonString = JSONArray.fromObject(resultList).toString();
//				System.out.println(jsonString);
				responseReader.close();
				ItemsDao iDao = new ItemsDaoImp();
				resultList.add(this.GRM);
				resultList.add(ChangeTime.timeStamp());
				if (iDao.insert(resultList)) {
//					System.out.println("插入数据成功");
				} else {
//					System.out.println("插入数据失败");
				}
//				System.out.println(sb);
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

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		try {
			myThread.running = false;
			thread.join();
			System.out.println("线程关闭");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("异常中断!!");
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		thread.start();
	}

	private MyThread myThread = new MyThread();
	private Thread thread = new Thread(myThread);

	class MyThread implements Runnable {
		private boolean running = true;

		public void run() {
			while (running) {
				try {
					function();
					Thread.sleep(30 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("异常中断");
				}
			}
		}
	}
}
