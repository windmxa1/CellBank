package org.dao.imp;

import java.util.List;

import org.dao.LumpDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.model.Lump;
import org.util.HibernateSessionFactory;

public class LumpDaoImp implements LumpDao {

	@Override
	public boolean insert(Lump lump) {
		return false;
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}

	@Override
	public boolean update(Lump lump) {
		return false;
	}

	@Override
	public Lump findById(Long id) {
		return null;
	}

	@Override
	public List<Lump> getLumps(Long roleid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql;
			if (roleid == 1) {// 说明是普通用户
				sql = "from Lump where state = 1";
			} else {
				sql = "from Lump ";
			}
			Query query = session.createQuery(sql);
			List<Lump> lumps = query.list();
			return lumps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
