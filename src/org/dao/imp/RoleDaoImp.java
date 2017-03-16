package org.dao.imp;

import java.util.List;

import org.dao.RoleDao;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Role;
import org.model.UserRole;
import org.util.HibernateSessionFactory;

public class RoleDaoImp implements RoleDao {

	@Override
	public boolean insert(Role role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.save(role);

			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.delete(session.load(Role.class, id));

			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean update(Role role) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.update(role);

			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Role findById(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from Role where id = ?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			Role role = (Role) query.uniqueResult();

			return role;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean insert1(UserRole userRole) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.save(userRole);

			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean update1(UserRole userRole) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.update(userRole);

			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public UserRole findById1(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from UserRole where id = ?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			UserRole userRole = (UserRole) query.uniqueResult();

			return userRole;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public UserRole findByUserId(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from UserRole where userId = ?");
			query.setParameter(0, userid);
			query.setMaxResults(1);
			UserRole userRole = (UserRole) query.uniqueResult();
			return userRole;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	public List<UserRole> findByRoleId(Long roleid){
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from UserRole where roleId = ?");
			query.setParameter(0, roleid);
			List<UserRole> list  = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
