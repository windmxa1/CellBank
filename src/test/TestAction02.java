package test;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.CommentsDao;
import org.dao.ReplysDao;
import org.dao.TopicsDao;
import org.dao.imp.CommentsDaoImp;
import org.dao.imp.ReplysDaoImp;
import org.dao.imp.TopicsDaoImp;

import org.model.Comments;
import org.model.Replys;
import org.model.Topics;

public class TestAction02 {
	public static void main(String[] args) {
		TopicsDao tDao = new TopicsDaoImp();
//		Integer position = 0;
//		List<Map<String, String>> topics = tDao.getTopList(position);
//		System.out.println(JSONArray.fromObject(topics).toString());
		CommentsDao cDao = new CommentsDaoImp();
//		List comments = cDao.getComments(topicId);
		ReplysDao rDao = new ReplysDaoImp();
		
//		Topics topic = new Topics((long) 1, "title", "content", 1476798983, "测试类型");
//		tDao.insert(topic);
		Topics topic = tDao.getTop("27");
//		Comments comment = new Comments((long) 1, "content", 1476798983, topic);
//		cDao.insert(comment);
		Comments comment = cDao.getCom("26");
		Replys reply = new Replys((long) 1, "content", 1476798983, comment);
		rDao.insert(reply);
	}
}
