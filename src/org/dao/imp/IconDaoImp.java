package org.dao.imp;

import org.dao.IconDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.CellinfoIcon;
import org.util.HibernateSessionFactory;

public class IconDaoImp implements IconDao {

	@Override
	public CellinfoIcon findIcon(String name, Boolean isIos) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("from CellinfoIcon where name=? and isIos=?");
			query.setParameter(0, name);
			query.setParameter(1, isIos);
			query.setMaxResults(1);
			CellinfoIcon cIcon = (CellinfoIcon) query.uniqueResult();
			return cIcon;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public CellinfoIcon updateIcon(Long id, String iconUrl) {
		return null;
	}

}
