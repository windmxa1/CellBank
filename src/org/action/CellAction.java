package org.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.CanDao;
import org.dao.CellDao;
import org.dao.IconDao;
import org.dao.ItemsDao;
import org.dao.UserDao;
import org.dao.imp.CanDaoImp;
import org.dao.imp.CellDaoImp;
import org.dao.imp.IconDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.UserDaoImp;
import org.imodel.VeCellsId;
import org.imodel.VeItemsId;
import org.model.Can;
import org.model.Cell;
import org.model.CellinfoIcon;
import org.model.Items;
import org.model.User;
import org.model.UserCell;
import org.util.ChangeTime;
import org.util.Constants;
import org.util.JsonBean;
import org.util.MapMessage;
import org.util.ResultUtils;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class CellAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private String phone = "13590440185";
	private Boolean isIos = true;
	private static final long serialVersionUID = 1L;
	private Object result = new ArrayList<>();
	private String start_time; // 显示历史记录的起始时间
	private String end_time; // 显示历史记录的结束时间
	private String timeString; // 显示指定月份的记录
	private String[] itemids; // 多选时传递数组Itemids
	private String itemid;
	private String cellid;// 干细胞id 用于删除干细胞信息
	private String serial; // 序列号
	private String name;
	private String num;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Integer Page = 0;// 当前请求页,因为必须进行-1操作，所以初始化为0
	private Integer start;// 数据起始位置
	private Integer limit;// 每页显示的数据数目
	CellDao cellDao;
	UserDao uDao;
	ItemsDao iDao;
	IconDao iconDao;

	/**
	 * 获取用户的干细胞的最新数据***新接口
	 */
	public void getCellInfo_new() {
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		JSONObject jsonObject;
		JSONArray jsonArray;
		if (phone != null) {
			cellDao = new CellDaoImp();
			uDao = new UserDaoImp();
			iDao = new ItemsDaoImp();
			User user = uDao.findUserByPhone(phone);
			if (user != null) {
				List<UserCell> userCells = cellDao.getCellList(user.getId());
				if (userCells != null && !userCells.isEmpty()) {
					Long cellid = userCells.get(0).getCellid();
					Cell cell = cellDao.getCell(cellid);
					if (cell != null) {
						jsonArray = new JSONArray();
						jsonObject = new JSONObject();
						jsonObject.put("title", "细胞名称");
						jsonObject.put("content", cell.getName());
						jsonArray.add(jsonObject);// 加入数组中
						jsonObject.clear();// 清空jsonObject
						jsonObject.put("title", "存储数量");
						jsonObject.put("content", cell.getNum());
						jsonArray.add(jsonObject);// 加入数组中
						jsonObject.clear();// 清空jsonObject
						jsonObject.put("title", "存储时间");
						jsonObject.put("content", ChangeTime.TimeStamp2Date(""
								+ cell.getClock(), "yyyy-MM-dd HH:mm:ss"));
						jsonArray.add(jsonObject);// 加入数组中
						jsonObject.clear();// 清空jsonObject

						Items item = iDao
								.getLastItemsBySerial(cell.getSerial());
						CanDao canDao = new CanDaoImp();
						Can can = canDao.getCanBySerial(cell.getSerial());
						jsonObject.put("title", "存放地点");
						if (can != null) {
							// message.put("存放地点", can.getAddress());
							jsonObject.put("content", can.getAddress());
						} else {
							jsonObject.put("content", "");
						}
						jsonArray.add(jsonObject);// 加入数组中
						jsonObject.clear();// 清空jsonObject
						if (item != null) {
							responseMS(JsonBean.getJson(cell, isIos));
							return;
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
							responseMS(jsonObject);
							return;
						}
					}
				}
			}
		}
		jsonObject = JSONObject.fromObject(Constants.jsonString);
		responseMS(jsonObject);

	}

	/**
	 * 获取用户的干细胞的最新数据
	 */
	public void getCellInfo() {
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		Map<String, String> message = new HashMap<String, String>();
		message.put("细胞名称", "您没有存储任何干细胞哦");
		message.put("存储数量", "");
		message.put("存储时间", "");
		message.put("记录时间", "");
		message.put("存放地点", "");
		message.put("液位低报警", "");
		message.put("液位高报警", "");
		message.put("罐内温度高报警", "");
		message.put("罐内温度超高报警", "");
		message.put("液氮罐液位", "");
		message.put("底部液氮T1", "");
		message.put("底部液氮T2", "");
		message.put("底部气氮T3", "");
		message.put("底部气氮T4", "");
		message.put("罐体顶部温度", "");
		message.put("样品温度", "");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (phone != null) {
			cellDao = new CellDaoImp();
			uDao = new UserDaoImp();
			iDao = new ItemsDaoImp();
			User user = uDao.findUserByPhone(phone);
			if (user != null) {
				List<UserCell> userCells = cellDao.getCellList(user.getId());
				if (userCells != null && !userCells.isEmpty()) {
					Long cellid = userCells.get(0).getCellid();
					Cell cell = cellDao.getCell(cellid);
					if (cell != null) {
						message.put("细胞名称", cell.getName());
						message.put("存储数量", "" + cell.getNum());
						message.put("存储时间", ChangeTime.TimeStamp2Date(
								"" + cell.getClock(), "yyyy-MM-dd HH:mm:ss"));
						Items item = iDao
								.getLastItemsBySerial(cell.getSerial());
						if (item != null) {
							message.put("记录时间", ChangeTime.TimeStamp2Date(""
									+ item.getClock(), "yyyy-MM-dd HH:mm:ss"));
							message.put("液位低报警", item.getLLowAlarm());
							message.put("液位高报警", item.getLHighAlarm());
							message.put("罐内温度高报警", item.getTHAlarm());
							message.put("罐内温度超高报警", item.getTHhAlarm());
							message.put("液氮罐液位", item.getLevel());
							message.put("底部液氮T1", item.getTemperature1());
							message.put("底部液氮T2", item.getTemperature2());
							message.put("底部气氮T3", item.getTemperature3());
							message.put("底部气氮T4", item.getTemperature4());
							message.put("罐体顶部温度", item.getVesselTemp());
							message.put("样品温度", item.getSampleTemp());

							CanDao canDao = new CanDaoImp();
							Can can = canDao.getCanBySerial(cell.getSerial());
							if (can != null) {
								message.put("存放地点", can.getAddress());
							}
						}
					}
				}
			}
		}
		message.put("parms", "phone=" + Utils.isNull(phone));
		list.add(message);
		JSONObject json = new JSONObject();
		JSONArray JsonArry = JSONArray.fromObject(list);
		// System.out.println(JsonArry.toString());
		try {
			json.element("JsonArry", JsonArry);
			response.setContentType("text/html;charset=utf-8");
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			e.printStackTrace();
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

	// ///////////////////////////我是分割线。。。上面是APP交互接口///////////////////////////
	/**
	 * 获取所有罐体数据总页数
	 */
	public String getPageCount() {
		List<Long> list = new ArrayList<>();
		iDao = new ItemsDaoImp();
		Long count = (long) 0;
		if (end_time == null || start_time == null || end_time.equals("")
				|| start_time.equals("")) {
			count = iDao.getCount();
		} else {
			Integer start = Integer.parseInt(ChangeTime.date2TimeStamp(
					start_time, "yyyy-MM-dd"));
			Integer end = Integer.parseInt(ChangeTime.date2TimeStamp(end_time,
					"yyyy-MM-dd"));
			if (start < end) {
				count = iDao.getCount(start, end);
			}
		}
		Long pageCount = (long) 0;
		if (count % 15 > 0) {
			pageCount = count / 15 + 1;
		} else {
			pageCount = count / 15;
		}
		if (pageCount == 0) {
			pageCount = (long) 1;
		}
		list.add(pageCount);
		result = list;
		return SUCCESS;
	}

	/**
	 * 获取所有罐体数据的总数
	 */
	public String getCount() {
		List<Long> list = new ArrayList<>();
		iDao = new ItemsDaoImp();
		Long count = (long) 0;
		if (end_time == null || start_time == null || end_time.equals("")
				|| start_time.equals("")) {
			count = iDao.getCount();
		} else {
			Integer start = Integer.parseInt(ChangeTime.date2TimeStamp(
					start_time, "yyyy-MM-dd"));
			Integer end = Integer.parseInt(ChangeTime.date2TimeStamp(end_time,
					"yyyy-MM-dd"));
			System.out.println(start);
			System.out.println(end);
			if (start < end) {
				count = iDao.getCount(start, end);
			}
		}
		list.add(count);
		result = list;
		return SUCCESS;
	}

	/**
	 * 获取罐体数据的历史记录,如果接收到参数start_time和end_time则查询指定时间段的数据
	 */
	public String getCellHistory() {
		System.out.println(Page);
		iDao = new ItemsDaoImp();
		if ((Page != 0 || start != null) && limit != null) {
			List<VeItemsId> list = new ArrayList<>();
			if (end_time == null || start_time == null || end_time.equals("")
					|| start_time.equals("")) {
				list = iDao.getItemList(Page - 1, start, limit);
				if (list != null && list.size() > 0) {
					result = Utils.setToResult(list, iDao.getCount());
				} else {
					result = ResultUtils.toResult(1);
				}
			} else {
				Integer startclock = Integer.parseInt(ChangeTime
						.date2TimeStamp(start_time, "yyyy-MM-dd"));
				Integer endclock = Integer.parseInt(ChangeTime.date2TimeStamp(
						end_time, "yyyy-MM-dd"));
				if (startclock < endclock) {
					list = iDao.getItemsByPeriod(start_time, end_time,
							Page - 1, start, limit);
					if (list != null && list.size() > 0) {
						result = Utils.setToResult(list,
								iDao.getCount(startclock, endclock));
					} else {
						result = ResultUtils.toResult(1);
					}
				}
			}
		} else {
			Map<String, String> message = new HashMap<>();
			message.put("message", "error");
			message.put("description", "参数错误");
			message.put("接收到的参数", "Page=" + Utils.isNull(Page) + "&start="
					+ Utils.isNull(start) + "&limit=" + Utils.isNull(limit));
			result = message;
		}
		return SUCCESS;
	}

	/**
	 * 删除某段时间的历史记录,时间需要转换成Integer类型方便数据库进行查找
	 */
	public String deleteHistoryByPeriod() {
		System.out.println("enter");
		iDao = new ItemsDaoImp();

		if (end_time == null || start_time == null || end_time.equals("")
				|| start_time.equals("")) {
			MapMessage message = new MapMessage(2);
			result = message;
			return SUCCESS;
		} else {
			Integer start = Integer.parseInt(ChangeTime.date2TimeStamp(
					start_time, "yyyy-MM-dd"));
			Integer end = Integer.parseInt(ChangeTime.date2TimeStamp(end_time,
					"yyyy-MM-dd"));
			if (start < end) {
				Map<String, String> message = new HashMap<>();
				if (iDao.delete(start, end)) {
					message.put("message", "success");
					message.put("description", "删除成功!");
					result = message;
					return SUCCESS;
				} else {
					message.put("message", "error");
					message.put("description", "删除失败!");
					result = message;
					return SUCCESS;
				}
			} else {
				MapMessage message = new MapMessage(1);
				result = message;
				return SUCCESS;
			}
		}

	}

	/**
	 * 根据提供的列表项的id删除指定的罐体数据
	 */
	public String deleteItem() {
		iDao = new ItemsDaoImp();
		Map<String, String> message = new HashMap<>();

		if (itemid != null) {
			if (iDao.deleteItem("" + itemid)) {
				message.put("message", "success");
				message.put("description", "删除成功!");
				result = message;
				return SUCCESS;
			} else {
				message.put("message", "error");
				message.put("description", "删除失败!");
				result = message;
				return SUCCESS;
			}
		}
		message.put("message", "error");
		message.put("description", "失败,可能缺少必要参数!");
		message.put(
				"params",
				"phone=" + Utils.isNull(phone) + "&name=" + Utils.isNull(name)
						+ "&num=" + Utils.isNull(num) + "&serial="
						+ Utils.isNull(serial));
		result = message;
		return SUCCESS;
	}

	/**
	 * 批量删除,复选框
	 */
	public String deleteItems() {
		iDao = new ItemsDaoImp();
		Map<String, String> message = new HashMap<>();

		if (itemids != null) {
			if (iDao.delete(itemids)) {
				message.put("message", "error");
				message.put("description", "您并未选择需要删除的数据!");
				result = message;
				return SUCCESS;
			} else if (iDao.deleteItem("" + itemid)) {
				message.put("message", "success");
				message.put("description", "删除成功!");
				result = message;
				return SUCCESS;
			} else {
				message.put("message", "error");
				message.put("description", "删除失败!");
				result = message;
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	/**
	 * 备注:维护细胞与用户关系属于增值服务 查看用户与其对应的细胞的存储信息
	 */
	/**
	 * 新增细胞,设计成用户名，手机号，细胞名，以及对应的罐体的序列号 需要先声明以上全局变量
	 */
	public String addCell() {
		// 原理：根据提供的手机号找到Userid，先插入细胞记录，再从细胞记录里找到cellid
		// 把cellid与userid在user_cell表进行绑定
		String clock = ChangeTime.timeStamp();
		uDao = new UserDaoImp();
		Map<String, String> message = new HashMap<>();

		try {
			request.setCharacterEncoding("UTF-8");
			name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			System.out.println(name);
			if (phone != null && name != null && num != null && serial != null) {
				if (Utils.isInteger(num) && phone.length() == 11
						&& Utils.isLong(phone) && serial.length() < 11
						&& serial.length() > 0) {
					User user = uDao.findUserByPhone(phone);
					if (user != null) {
						CellDao cellDao = new CellDaoImp();
						Cell cell = new Cell(name, Integer.parseInt(num),
								serial, Integer.parseInt(clock));
						if (cellDao.insert(cell)) {
							Cell cell2 = cellDao.findCell(name, clock, serial);
							UserCell userCell = new UserCell(user.getId(),
									cell2.getId());
							if (cellDao.insert(userCell)) {
								message.put("message", "success");
								message.put("description", "成功!");
								result = message;
								return SUCCESS;
							}
						}
					} else {
						message.put("description", "找不到手机号对应的用户!");
					}
				} else {
					message.put("description", "失败,输入的参数不合法，请重新输入!");
				}
			}
			message.put("message", "error");
			if (message.get("description") == null) {
				message.put("description", "失败,可能缺少必要参数!");
			}
			message.put("params", "phone=" + Utils.isNull(phone) + "&name="
					+ Utils.isNull(name) + "&num=" + Utils.isNull(num)
					+ "&serial=" + Utils.isNull(serial));
			result = message;
			return SUCCESS;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message.put("message", "error");
			message.put("description", "失败,服务器异常请稍后重试");
			result = message;
			return SUCCESS;
		}
	}

	/**
	 * 获取存储干细胞的总数
	 */
	public String getCellCount() {
		cellDao = new CellDaoImp();
		Long count = cellDao.getCellCount();
		List<Long> list = new ArrayList<>();
		list.add(count);
		result = list;
		return SUCCESS;
	}

	/**
	 * 获取存储的干细胞列表
	 */
	public String getCellList() {
		cellDao = new CellDaoImp();
		if ((Page != null || start != null) && limit != null) {
			List<VeCellsId> list = cellDao.getCells(Page - 1, start, limit);
			if (list != null && list.size() > 0) {
				result = Utils.setToResult(list, cellDao.getCellCount());
			} else {
				result = ResultUtils.toResult(1);
			}
		} else {
			Map<String, String> message = new HashMap<>();
			message.put("message", "error");
			message.put("description", "参数错误");
			message.put("接收到的参数", "Page=" + Utils.isNull(Page) + "&start="
					+ Utils.isNull(start) + "&limit=" + Utils.isNull(limit));
			result = message;
		}
		return SUCCESS;
	}

	/**
	 * 删除干细胞信息
	 */
	public String delCell() {
		cellDao = new CellDaoImp();
		// Map<String, String> message = new HashMap<>();

		if (cellid == null) {
			MapMessage message = new MapMessage(2);
			result = message;
			return SUCCESS;
		}
		Cell cell = null;
		cell = cellDao.getCell(Long.parseLong(cellid));
		if (cell != null) {
			if (cellDao.delete(cell)) {
				MapMessage message = new MapMessage(0);
				result = message;
				return SUCCESS;
			} else {
				MapMessage message = new MapMessage(3);
				result = message;
				return SUCCESS;
			}
		} else {
			Map<String, String> message = new HashMap<>();
			message.put("message", "error");
			message.put("description", "该细胞不存在!");
			result = message;
			return SUCCESS;
		}
	}

	public String[] getItemids() {
		return itemids;
	}

	public void setItemids(String[] itemids) {
		this.itemids = itemids;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getCellid() {
		return cellid;
	}

	public void setCellid(String cellid) {
		this.cellid = cellid;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getPage() {
		return Page;
	}

	public void setPage(Integer Page) {
		this.Page = Page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
