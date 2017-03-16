package test;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.dao.CommentsDao;
import org.dao.imp.CommentsDaoImp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Comments;
import org.model.Replys;
import org.model.Topics;
import org.util.ChangeTime;
import org.util.HibernateSessionFactory;

public class TestAction {
	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			
			Integer clock = Integer.parseInt(ChangeTime.timeStamp());
			Topics topic = new Topics();
			topic.setClock(clock);
			topic.setContent("测试留言");
			topic.setTitle("测试标题");
			topic.setType("测试话题类型");
			topic.setUserid((long)1);
			
			Set<Comments> comments = new HashSet<Comments>();
			Comments comment = new Comments();
			comment.setClock(clock);
			comment.setContent("测试评论");
			comment.setUserid((long)2);
			comment.setTopic(topic);
			
			Set<Replys> replys = new HashSet<Replys>();
			Replys reply = new Replys();
			reply.setClock(clock);
			reply.setComment(comment);
			reply.setContent("测试回复");
			reply.setUserid((long)3);
			replys.add(reply);
			
			comment.setReplys(replys);
			comments.add(comment);
			topic.setComments(comments);
			System.out.println(111);
			
//		String JsonArry = JSONArray.fromObject(topic).toString();
//		System.out.println(JsonArry);
			try {
				Session session = HibernateSessionFactory.getSession();
				Transaction ts = session.beginTransaction();
				
				session.save(topic);
//			CommentsDao cDao = new CommentsDaoImp();
//			Comments comment2 = cDao.getCom("10");
//			System.out.println(comment2.getReplys());
//			reply = (Replys) session.load(Replys.class, Long.parseLong("19"));
//			session.delete(reply);
//			comment = (Comments) session.load(Comments.class,Long.parseLong("20"));
//			session.delete(comment);
//			topic = (Topics) session.load(Topics.class, Long.parseLong("21"));
//			session.delete(topic);
				ts.commit();
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		JSONObject json =JSONObject.fromObject(topic, jsonConfig);
//		System.out.println(json.toString());
	}
}
