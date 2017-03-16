package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.dao.CommentsDao;
import org.dao.ReplysDao;
import org.dao.TopicsDao;
import org.dao.imp.CommentsDaoImp;
import org.dao.imp.ReplysDaoImp;
import org.dao.imp.TopicsDaoImp;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Comments;
import org.model.Replys;
import org.model.Topics;
import org.util.HibernateSessionFactory;

public class TestAction01 {
	public static void main(String[] args) {
//		 TopicsDao tDao = new TopicsDaoImp();
//		  List<Map<String, String>> topics = tDao.getTopList();
//		  List<Topics> topics = tDao.getTopics(0);
//		 Topics topic = tDao.getTop("2");
		  
		 CommentsDao cDao = new CommentsDaoImp();
//		 cDao.getComments("26", 0);
		 List<Comments> comments = cDao.getComments1("27", 0);
//		 List<Map<String,String>> comments = cDao.getComList1("2");
//		 // JSONArray JsonArry = JSONArray.fromObject(topics);
		 JsonConfig jsonConfig = new JsonConfig();
		 jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		 JSONArray json =JSONArray.fromObject(comments, jsonConfig);
		 System.out.println(json.toString());
//		 System.out.println(JsonArry.toString());
		// String topicId = ""+(long)2;

//		TopicsDao tDao = new TopicsDaoImp();
//		CommentsDao cDao = new CommentsDaoImp();
//		ReplysDao rDao = new ReplysDaoImp();
//		List<Map<String, String>> Topmap = tDao.getTopList1();
//		int count = 0;
//		for (Map<String, String> map : Topmap) {
//			count++;
////			System.out.println(count);
//			System.out.println("topid"+map.get("id"));
//			System.out.println(map.get("content"));
//			List<Map<String, String>> Commap = cDao.getComList1(map.get("id"));
//			for (Map<String, String> map1 : Commap) {
//				System.out.println(map1.get("content"));
//				List<Map<String, String>> Repmap = rDao.getRepList1(map
//						.get("id"));
//				for (Map<String, String> map2 : Repmap) {
//					System.out.println(map2.get("content"));
//				}
//			}
//		}
//		System.out.println(count);
//		List<Map<String, String>> list =rDao.getRepList1("6");
//		List<Map<String, String>> list =tDao.getTopList1();
//		List<Map<String, String>> list =cDao.getComList1("2");
//		System.out.println(JSONArray.fromObject(list).toString());
		
//		System.out.println(tDao.getTop("1").getContent());
//		System.out.println(tDao.delTop1("1"));
//		System.out.println(cDao.getCom("3").getContent());
//		cDao.delComment1("3");
//		rDao.delReply1("1");
//		 System.out.println(getR(0).get(0).getId());
//		 System.out.println(JSONArray.fromObject(getR(0),jsonConfig).toString());
//		 System.out.println(getR(1).get(0).getId());
//		 System.out.println(JSONArray.fromObject(getR(1),jsonConfig).toString());
//		 System.out.println(getR(1).getId());
//		 System.out.println(getR(2).getId());
	}
	
	public static List<Comments> getR(int a){
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("From Comments");
			query.setMaxResults(2);
			query.setFirstResult(a*2);
			ts.commit();
			List<Comments> comments =  query.list();
			List<Comments> list =  new ArrayList<Comments>();
			for(Comments comment:comments){
				Comments comment2 = new Comments();
				comment2.setClock(comment.getClock());
				comment2.setContent(comment.getContent());
				comment2.setId(comment.getId());
				comment2.setUserid(comment.getUserid());
				Set<Replys> replys = new HashSet<>();
				for(Replys reply:comment.getReplys()){
					Replys reply2 = new Replys();
					reply2.setClock(reply.getClock());
					reply2.setContent(reply.getContent());
					reply2.setId(reply.getId());
					reply2.setUserid(reply.getUserid());
					replys.add(reply2);
				}
				comment2.setReplys(replys);
				list.add(comment2);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
