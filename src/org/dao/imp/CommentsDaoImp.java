package org.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dao.CommentsDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imodel.VeComments;
import org.imodel.VeCommentsId;
import org.model.Comments;
import org.util.HibernateSessionFactory;

public class CommentsDaoImp implements CommentsDao {
	private final static Integer Max = 15;

	public List<VeCommentsId> getComList1(String topicId, Integer Page,
			Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from ve_comments where topic_id=?";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, topicId);
			sqlQuery.addEntity(VeComments.class);
			if (start != null) {
				sqlQuery.setFirstResult(start);
			} else {
				sqlQuery.setFirstResult((Page - 1) * limit);
			}
			sqlQuery.setMaxResults(limit);
			List<VeComments> list = sqlQuery.list();
			List<VeCommentsId> list2 = new ArrayList<>();
			for (VeComments v : list) {
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

	public Long getCount(String topicId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select count(id) from Comments c where c.topic.id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Long.parseLong(topicId));
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

	public List<Comments> getComments(String topicId, Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "select * from comments where topic_id=?"
					+ " ORDER BY clock ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, Long.parseLong(topicId));
			sqlQuery.addEntity(Comments.class);
			// sqlQuery.setMaxResults(Max);
			sqlQuery.setFirstResult(position);
			List<Comments> comments = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List<Comments> getComments1(String topicId, Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "select * from comments where topic_id=?"
					+ " ORDER BY clock ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, Long.parseLong(topicId));
			sqlQuery.addEntity(Comments.class);

			sqlQuery.setMaxResults(Max);
			sqlQuery.setFirstResult(position);
			List<Comments> comments = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean delComment(String commentId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Comments comment = (Comments) session.load(Comments.class,
					Long.parseLong(commentId));
			comment.getTopic().getComments().remove(comment);
			comment.setTopic(null);
			session.delete(comment);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public Comments getCom(String commentId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Comments where id= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Long.parseLong(commentId));
			query.setMaxResults(1);
			Comments comment = (Comments) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return comment;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public Comments getCom(Long userid, Integer clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Comments where userid= ? and clock=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setParameter(1, clock);
			query.setMaxResults(1);
			Comments comment = (Comments) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return comment;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean insert(Comments comment) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "insert into comments (userid,content,clock,topic_id) values (?,?,?,?)";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, comment.getUserid());
			sqlQuery.setParameter(1, comment.getContent());
			sqlQuery.setParameter(2, comment.getClock());
			sqlQuery.setParameter(3, comment.getTopic().getId());
			sqlQuery.executeUpdate();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

}
