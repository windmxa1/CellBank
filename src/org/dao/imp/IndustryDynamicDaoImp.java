package org.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.IndustryDynamicDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.model.IndustryDynamic;
import org.util.HibernateSessionFactory;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class IndustryDynamicDaoImp implements IndustryDynamicDao {

	private Session session = null;

	/**
	 * app根据状态查询数据
	 */
	public List query(Integer states) {

		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from industry_dynamic where  states = "
					+ "?";// sql语句查询
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, states);
			sqlQuery.addEntity(IndustryDynamic.class);
			List<IndustryDynamic> list = sqlQuery.list();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	/**
	 * 6.行业新闻动态
	 * 
	 * */
	public List industryNews() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from industry_dynamic where states = 6 ORDER BY clock desc LIMIT 0,3  ";// sql语句查询
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(IndustryDynamic.class);
			List<IndustryDynamic> list = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	/*
	 * 
	 * industry_dynamic表中添加数据
	 */
	public Integer insert(IndustryDynamic industryDynamic) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.save(industryDynamic);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return 0;
		}
	}

	/*
	 * 
	 * industry_dynamic表中删除数据
	 */
	public int delete(int id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "delete from industry_dynamic where id = " + "?";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter(0, id);
			query.executeUpdate();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return 0;
		}
	}

	/*
	 * 
	 * industry_dynamic表中修改数据
	 */
	public int update(IndustryDynamic industryDynamic) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session = HibernateSessionFactory.getSession();
			session.update(industryDynamic);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return 0;
		}
	}

	public int count(Integer states) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			int count = 0;
			SQLQuery sqlQuery = session
					.createSQLQuery("select * from industry_dynamic where states = "
							+ "?");
			sqlQuery.setParameter(0, states);
			sqlQuery.addEntity(IndustryDynamic.class);
			List cats = sqlQuery.list();
			count = cats.size();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return 0;
		}
	}

	public IndustryDynamic getDataById(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from IndustryDynamic where id = ?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			IndustryDynamic idDynamic = (IndustryDynamic) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return idDynamic;

		} catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List queryId(Integer id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from industry_dynamic where id = " + "?";// sql语句查询
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, id);
			sqlQuery.addEntity(IndustryDynamic.class);
			List<IndustryDynamic> list = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}
}
