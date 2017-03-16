package org.dao.imp;

import java.util.List;

import org.dao.CanDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Can;
import org.util.HibernateSessionFactory;

public class CanDaoImp implements CanDao {

	public Long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select count(id) from Can";
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return (long) 0;
		}
	}

	public List<Can> getList(Integer Page, Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from Can";
			Query query = session.createQuery(sql);
			if (start != null) {
				query.setFirstResult(start);
			} else {
				query.setFirstResult((Page - 1) * limit);
			}
			query.setMaxResults(limit);
			List<Can> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public Can getCanBySerial(String Serial) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Can where serial=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Serial);
			query.setMaxResults(1);
			Can can = (Can) query.uniqueResult();

			HibernateSessionFactory.closeSession();
			return can;

		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean insert(Can can) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Can can2 = new Can();
			can2.setAddress(can.getAddress());
			can2.setSerial(can.getSerial());
			session.save(can2);

			ts.commit();
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public boolean update(Can can) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.update(can);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public Can findCanById(Long Id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Can where id=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Id);
			query.setMaxResults(1);
			Can can = (Can) query.uniqueResult();

			HibernateSessionFactory.closeSession();
			return can;

		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean delCan(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Can can = (Can) session.load(Can.class, id);
			session.delete(can);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public boolean delCans(String[] ids) {
		String sql = "";
		for (int i = 0; i < ids.length; i++) {
			if (i == 0) {
				sql = "id=?";
			} else {
				sql = sql + " or id=?";
			}
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SQLQuery q = session.createSQLQuery("delete from can where " + sql);
			for (int i = 0; i < ids.length; i++) {
				q.setParameter(i, Long.parseLong(ids[i]));
			}
			q.executeUpdate();
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
