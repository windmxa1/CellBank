package org.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Constants {
	public final static String jsonString = "{'data':{'infos':[{'items':[{'content':'','title':'液氮罐液位'}],'celltitle':'液位','cellicon':'http://120.76.22.150:8080/CellBank/icon/liquid_level.png'},{'items':[{'content':'您还没有存储任何细胞哦','title':'细胞名称'},{'content':'','title':'存储数量'},{'content':'','title':'存放地点'},{'content':'','title':'记录时间'},{'content':'','title':'存储时间'}],'celltitle':'基本信息','cellicon':'http://120.76.22.150:8080/CellBank/icon/cellname.png'},{'items':[{'content':'','title':'底部液氮T1'},{'content':'','title':'底部液氮T2'},{'content':'','title':'底部液氮T3'},{'content':'','title':'底部液氮T4'},{'content':'','title':'罐体顶部温度'},{'content':'','title':'样品温度'}],'celltitle':'温度','cellicon':'http://120.76.22.150:8080/CellBank/icon/temperature.png'},{'items':[{'content':'','title':'液位低报警'},{'content':'','title':'罐内温度高报警'},{'content':'','title':'液位低报警'},{'content':'','title':'液位高报警'}],'celltitle':'报警','cellicon':'http://120.76.22.150:8080/CellBank/icon/alarm.png'}]},'code':100,'msg':''}";
	public final static String testString = "{'data':{'infos':[{'items':[{'content':'8','title':'液氮罐液位'}],'celltitle':'液位','cellicon':'http://120.76.22.150:8080/CellBank/icon/liquid_level.png'},{'items':[{'content':'造血干细胞','title':'细胞名称'},{'content':'1000','title':'存储数量'},{'content':'深圳','title':'存放地点'},{'content':'2016-10-2712:35:03','title':'记录时间'},{'content':'2016-10-1311:45:47','title':'存储时间'}],'celltitle':'基本信息','cellicon':'http://120.76.22.150:8080/CellBank/icon/cellname.png'},{'items':[{'content':'-197','title':'底部液氮T1'},{'content':'-197','title':'底部液氮T2'},{'content':'-187.699996948242','title':'底部液氮T3'},{'content':'-170.600006103516','title':'底部液氮T4'},{'content':'-180.800003051758','title':'罐体顶部温度'},{'content':'-184.300003051758','title':'样品温度'}],'celltitle':'温度','cellicon':'http://120.76.22.150:8080/CellBank/icon/temperature.png'},{'items':[{'content':'正常','title':'液位低报警'},{'content':'正常','title':'罐内温度高报警'},{'content':'正常','title':'液位低报警'},{'content':'正常','title':'液位高报警'}],'celltitle':'报警','cellicon':'http://120.76.22.150:8080/CellBank/icon/alarm.png'}]},'code':100,'msg':''}";
	public final static String nodataString = "[{'items':[{'content':'','title':'液氮罐液位'}],'celltitle':'液位','cellicon':'http://120.76.22.150:8080/CellBank/icon/liquid_level.png'},{'items':[{'content':'','title':'底部液氮T1'},{'content':'','title':'底部液氮T2'},{'content':'','title':'底部液氮T3'},{'content':'','title':'底部液氮T4'},{'content':'','title':'罐体顶部温度'},{'content':'','title':'样品温度'}],'celltitle':'温度','cellicon':'http://120.76.22.150:8080/CellBank/icon/temperature.png'},{'items':[{'content':'','title':'液位低报警'},{'content':'','title':'罐内温度高报警'},{'content':'','title':'液位低报警'},{'content':'','title':'液位高报警'}],'celltitle':'报警','cellicon':'http://120.76.22.150:8080/CellBank/icon/alarm.png'}]";
	public final static JSONObject jsonObject = JSONObject
			.fromObject(jsonString);
	public final static JSONArray jsonArray = JSONArray.fromObject(nodataString);

	// public static void main(String[] args) {
	// // alarm.png cellname.png liquid_level.png temperature.png
	// // System.out.println(jsonObject.toString());
	// // System.out.println(JSONArray.fromObject(nodataString).toString());
	// }
}