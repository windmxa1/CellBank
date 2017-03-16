package org.dao.imp;

import org.dao.TokenDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.util.HibernateSessionFactory;

public class TokenDaoImp implements TokenDao {

	@Override
	public String getToken() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select token from Token");
			query.setMaxResults(1);
			String token = (String) query.uniqueResult();
			return token;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Integer saveToken(String token) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("update Token set token=? where id=1");
			query.setParameter(0, token);
			Integer count = query.executeUpdate();
			ts.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}

}
