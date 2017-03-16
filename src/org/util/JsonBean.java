package org.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dao.CanDao;
import org.dao.IconDao;
import org.dao.ItemsDao;
import org.dao.imp.CanDaoImp;
import org.dao.imp.IconDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.model.Can;
import org.model.Cell;
import org.model.Items;

public class JsonBean {
	public static JSONObject getJson(Cell cell, Boolean isIos) {
		ItemsDao iDao = new ItemsDaoImp();
		IconDao iconDao = new IconDaoImp();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray2 = new JSONArray();
		jsonObject.put("title", "细胞名称");
		jsonObject.put("content", cell.getName());
		jsonArray.add(jsonObject);// 加入数组中
		jsonObject.clear();// 清空jsonObject
		jsonObject.put("title", "存储数量");
		jsonObject.put("content", cell.getNum());
		jsonArray.add(jsonObject);// 加入数组中
		jsonObject.clear();// 清空jsonObject
		jsonObject.put("title", "存储时间");
		jsonObject.put("content", ChangeTime.TimeStamp2Date(
				"" + cell.getClock(), "yyyy-MM-dd HH:mm:ss"));
		jsonArray.add(jsonObject);// 加入数组中
		jsonObject.clear();// 清空jsonObject

		Items item = iDao.getLastItemsBySerial(cell.getSerial());
		CanDao canDao = new CanDaoImp();
		Can can = canDao.getCanBySerial(cell.getSerial());
		jsonObject.put("title", "存放地点");
		if (can != null) {
			jsonObject.put("content", can.getAddress());
		} else {
			jsonObject.put("content", "");
		}
		jsonArray.add(jsonObject);// 加入数组中
		jsonObject.clear();// 清空jsonObject
		if (item != null) {
			iconDao = new IconDaoImp();
			jsonObject.put("title", "记录时间");
			jsonObject.put("content", ChangeTime.TimeStamp2Date(
					"" + item.getClock(), "yyyy-MM-dd HH:mm:ss"));
			jsonArray.add(jsonObject);
			jsonObject.clear();
			jsonObject.put("items", jsonArray);
			jsonObject.put("celltitle", "基本信息");
			jsonObject.put("cellicon", ""
					+ iconDao.findIcon("基本信息", isIos).getIconUrl());
			jsonArray2 = new JSONArray();
			jsonArray2.add(jsonObject);
			jsonObject.clear();
			jsonArray.clear();

			jsonObject.put("title", "液位低报警");
			if (item.getLLowAlarm().equals("0")) {
				jsonObject.put("content", "正常");
			} else {
				jsonObject.put("content", "异常");
			}
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "液位高报警");
			if (item.getLHighAlarm().equals("0")) {
				jsonObject.put("content", "正常");
			} else {
				jsonObject.put("content", "异常");
			}
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "罐内温度高报警");
			if (item.getTHAlarm().equals("0")) {
				jsonObject.put("content", "正常");
			} else {
				jsonObject.put("content", "异常");
			}
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "罐内温度超高报警");
			if (item.getTHhAlarm().equals("0")) {
				jsonObject.put("content", "正常");
			} else {
				jsonObject.put("content", "异常");
			}
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject
			jsonObject.put("items", jsonArray);
			jsonObject.put("celltitle", "报警");
			jsonObject.put("cellicon", ""
					+ iconDao.findIcon("报警", isIos).getIconUrl());
			jsonArray2.add(jsonObject);
			jsonObject.clear();
			jsonArray.clear();

			jsonObject.put("title", "液氮罐液位");
			jsonObject.put("content", item.getLevel());
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject
			jsonObject.put("items", jsonArray);
			jsonObject.put("celltitle", "液位");
			jsonObject.put("cellicon", ""
					+ iconDao.findIcon("液位", isIos).getIconUrl());
			jsonArray2.add(jsonObject);
			jsonObject.clear();
			jsonArray.clear();

			jsonObject.put("title", "底部液氮T1");
			jsonObject.put("content", item.getTemperature1());
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "底部液氮T2");
			jsonObject.put("content", item.getTemperature2());
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "底部气氮T3");
			jsonObject.put("content", item.getTemperature3());
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "底部气氮T4");
			jsonObject.put("content", item.getTemperature4());
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "罐体顶部温度");
			jsonObject.put("content", item.getVesselTemp());
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("title", "样品温度");
			jsonObject.put("content", item.getSampleTemp());
			jsonArray.add(jsonObject);// 加入数组中
			jsonObject.clear();// 清空jsonObject

			jsonObject.put("items", jsonArray);
			jsonObject.put("celltitle", "温度");
			jsonObject.put("cellicon", ""
					+ iconDao.findIcon("温度", isIos).getIconUrl());
			jsonArray2.add(jsonObject);
			jsonObject.clear();
			jsonObject.put("infos", jsonArray2);
			return jsonObject;
		} else {
			jsonObject.put("title", "记录时间");
			jsonObject.put("content", "该细胞暂无数据记录");
			jsonArray.add(jsonObject);
			jsonObject.clear();
			jsonObject.put("items", jsonArray);
			jsonObject.put("celltitle", "基本信息");
			jsonObject.put("cellicon", "");
			jsonArray.clear();
			JSONArray jsonArray3 = Constants.jsonArray;
			jsonArray3.add(jsonObject);
			jsonObject.clear();
			jsonObject.put("infos", jsonArray3);
			return jsonObject;
		}
	}
}
