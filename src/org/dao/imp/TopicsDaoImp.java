package org.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.TopicsDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imodel.VeTopics;
import org.imodel.VeTopicsId;
import org.model.Topics;
import org.util.ChangeTime;
import org.util.HibernateSessionFactory;

public class TopicsDaoImp implements TopicsDao {
	private final static Integer Max = 15;

	public List<VeTopicsId> getTopList1(Integer Page, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from ve_topics ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VeTopics.class);
			if (start != null) {
				sqlQuery.setFirstResult(start);
			} else {
				sqlQuery.setFirstResult((Page - 1) * limit);
			}
			sqlQuery.setMaxResults(limit);
			List<VeTopics> list = sqlQuery.list();
			List<VeTopicsId> list2 = new ArrayList<>();
			for (VeTopics v : list) {
				list2.add(v.getId());
			}
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list2;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public Long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select count(id) from Topics ";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List<Map<String, String>> getTopList(Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select id,userid,title,content,clock,type from Topics ORDER BY clock DESC";
			Query query = session.createQuery(sql);
			query.setFirstResult(position);
			query.setMaxResults(15);
			List<Object[]> topics = query.list();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (Object[] o : topics) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("topicId", "" + o[0]);
				map.put("userid", "" + o[1]);
				map.put("title", "" + o[2]);
				map.put("content", "" + o[3]);
				String time = ChangeTime.TimeStamp2Date("" + o[4],
						"yyyy-MM-dd HH:mm:ss ");
				map.put("clock", "" + time);
				map.put("type", "" + o[5]);
				list.add(map);
			}
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List<Topics> getTopics(Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from Topics ORDER BY clock DESC";
			Query query = session.createQuery(sql);
			query.setFirstResult(position);
			query.setMaxResults(Max);

			List<Topics> topics = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return topics;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public Topics getTop(String topicId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Topics where id= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Long.parseLong(topicId));
			query.setMaxResults(1);
			Topics topic = (Topics) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return topic;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public Topics getTop(Long userid, Integer clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Topics where userid= ? and clock=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setParameter(1, clock);
			query.setMaxResults(1);
			Topics topic = (Topics) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return topic;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean delTop(String topicId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Topics topic = (Topics) session.load(Topics.class,
					Long.parseLong(topicId));
			session.delete(topic);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public boolean insert(Topics topic) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Topics topic1 = new Topics();
			topic1.setUserid(topic.getUserid());
			topic1.setTitle(topic.getTitle());
			topic1.setContent(topic.getContent());
			topic1.setType(topic.getType());
			topic1.setClock(topic.getClock());

			session.save(topic1);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public List<Topics> searchKeyWord(String keyword) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SQLQuery sqlquery = session
					.createSQLQuery("select * from topics where title like ? or content like ?");
			sqlquery.setParameter(0, "%" + keyword + "%");
			sqlquery.setParameter(1, "%" + keyword + "%");
			sqlquery.addEntity(Topics.class);
			List<Topics> topics = sqlquery.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return topics;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

}
