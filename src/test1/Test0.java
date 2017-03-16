package test1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dao.CellDao;
import org.dao.CommentsDao;
import org.dao.IconDao;
import org.dao.LumpDao;
import org.dao.RoleDao;
import org.dao.TopicsDao;
import org.dao.UserDao;
import org.dao.imp.CellDaoImp;
import org.dao.imp.CommentsDaoImp;
import org.dao.imp.IconDaoImp;
import org.dao.imp.LumpDaoImp;
import org.dao.imp.RoleDaoImp;
import org.dao.imp.TopicsDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imodel.VeUserinfoId;
import org.model.Cell;
import org.model.Comments;
import org.model.Items;
import org.model.Lump;
import org.model.Topics;
import org.model.User;
import org.model.UserRole;
import org.util.ChangeTime;
import org.util.HibernateSessionFactory;
import org.util.Utils;

public class Test0 {
	public static void main(String[] args) {
		// File file = new File("g:\\sd.txt");
		// System.out.println(Utils.delFile(file.toString()));
//		String topicId =""+87;
//		Long userid = (long) 44;
//		String content = "sadsdd";
//		String clock = ChangeTime.timeStamp();
//		
//		TopicsDao tDao = new TopicsDaoImp();
//		Topics topic = tDao.getTop(topicId);
//		Comments comment = new Comments(userid, content,
//				Integer.parseInt(clock), topic);
//		CommentsDao cDao = new CommentsDaoImp();
//		cDao.insert(comment);
		
		IconDao iconDao = new IconDaoImp();
		System.out.println(iconDao.findIcon("基本信息", true));
	}
}