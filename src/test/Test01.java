package test;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.action.CellAction;
import org.dao.ItemsDao;
import org.dao.TopicsDao;
import org.dao.UserDao;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.TopicsDaoImp;
import org.dao.imp.UserDaoImp;
import org.util.ChangeTime;

public class Test01 {
	public static void main(String[] args) throws Exception {
		Long phone = Long.parseLong("13590440184");
		String username="444";
		String password = "123";
		int available =1;
		int priority =2;
		Integer clock = Integer.parseInt(ChangeTime.timeStamp());
		UserDao uDao = new UserDaoImp();
//		SuperUser superUser = new SuperUser(username, password, available,
//				priority, clock);
//		uDao.AddSuper(superUser);
//		superUser= uDao.findSuperByName(username);
//		superUser.setAvailable(0);
//		superUser.setPriority(1);
//		uDao.editSuper(superUser);
//		uDao.deleteSuper(superUser);
		
//		User user = new User(phone, username, password, clock);
//		user = uDao.findUserByPhone(""+phone);
//		uDao.deleteUser(user);
		
//		CellAction cellAction = new CellAction();
//		cellAction.getCellHistory();
		
		
		CellAction cellAction = new CellAction();
//		cellAction.getHistoryByPeriod();
		ItemsDao iDao = new ItemsDaoImp();
		Integer start = 0;
//		Integer end = Integer.parseInt(ChangeTime.timeStamp());
//		List<Items> items = iDao.getItemsByPeriod(start, end);
//		System.out.println(end);
//		System.out.println(JSONArray.fromObject(items).toString());
		TopicsDao tDao = new TopicsDaoImp();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig
				.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray JsonArry = JSONArray.fromObject(tDao.searchKeyWord("title"), jsonConfig);
		System.out.println(JsonArry.toString());
		}
}
