package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.CellDao;
import org.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imodel.VeUserinfo;
import org.imodel.VeUserinfoId;
import org.model.SuperUser;
import org.model.User;
import org.model.UserCell;
import org.util.HibernateSessionFactory;

public class UserDaoImp implements UserDao {

	public Long Register(User user) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			User user2 = new User();
			user2.setPhone(user.getPhone());
			user2.setClock(user.getClock());
			user2.setPassword(user.getPassword());
			Long id = (Long) session.save(user2);
			ts.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return (long) -1;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public User validateUser(Long userid, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from User where id = ? and password= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setParameter(1, password);
			query.setMaxResults(1);

			User user = (User) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}

	}

	public User Login(String phone, String password) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from User where phone = ? and password= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Long.parseLong(phone));
			query.setParameter(1, password);
			query.setMaxResults(1);

			User user = (User) query.uniqueResult();

			HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean update(User user) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.update(user);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return false;
		}

	}

	public User findUserByPhone(String phone) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from User where phone=?");
			query.setParameter(0, Long.parseLong(phone));
			query.setMaxResults(1);
			User user = (User) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public User findUserById(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from User where id=?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			User user = (User) query.uniqueResult();
			// ts.commit();
			HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public Long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from User");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return (long) 0;
		}
	}

	public List<User> getUserList(Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from User");
			query.setMaxResults(15);
			query.setFirstResult(position * 15);
			List<User> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List<VeUserinfoId> getUserList1(Integer Page, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from ve_userinfo";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VeUserinfo.class);
			if (start != null) {
				sqlQuery.setFirstResult(start);
			} else {
				sqlQuery.setFirstResult((Page - 1) * limit);
			}
			sqlQuery.setMaxResults(limit);
			List<VeUserinfo> list = sqlQuery.list();
			List<VeUserinfoId> list2 = new ArrayList<>();
			for (VeUserinfo v : list) {
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

	public List<SuperUser> getSuperList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from SuperUser");
			List<SuperUser> list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean deleteSuper(SuperUser superUser) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SuperUser spUser = (SuperUser) session.load(SuperUser.class,
					superUser.getId());
			session.delete(spUser);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public boolean deleteUser(User user) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			User user2 = (User) session.load(User.class, user.getId());
			session.delete(user2);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public boolean deleteUsers(String ids[]) {
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
			SQLQuery q = session
					.createSQLQuery("delete from user where " + sql);
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

	public boolean editSuper(SuperUser superUser) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.update(superUser);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public SuperUser findSuperByName(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from SuperUser where username=?");
			query.setParameter(0, username);
			query.setMaxResults(1);
			SuperUser superUser = (SuperUser) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return superUser;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public SuperUser validate(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from SuperUser where username=? and password = ?");
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			SuperUser superUser = (SuperUser) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return superUser;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean AddSuper(SuperUser superUser) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SuperUser superUser2 = new SuperUser();

			superUser2.setUsername(superUser.getUsername());
			superUser2.setPassword(superUser.getPassword());
			superUser2.setAvailable(superUser.getAvailable());
			superUser2.setPriority(superUser.getPriority());
			superUser2.setClock(superUser.getClock());
			session.save(superUser2);

			ts.commit();
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public List<Object[]> getIncreaseNum() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			SQLQuery sqlQuery = session
					.createSQLQuery("select from_unixtime(clock,'%Y-%m'),count(id) as clock from user group by from_unixtime(clock,'%Y-%m')");
			List<Object[]> list = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	@Override
	public List<VeUserinfoId> getUserInfo(Long roleid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			SQLQuery sqlQuery = session
					.createSQLQuery("select ui.* from ve_userinfo ui,user_role ur where ur.user_id = ui.id and ur.role_id=?");
			sqlQuery.addEntity(VeUserinfo.class);
			sqlQuery.setParameter(0, roleid);
			List<VeUserinfo> userinfo =  sqlQuery.list();
			List<VeUserinfoId> uIds = new ArrayList<>();
			for(VeUserinfo u : userinfo){
				uIds.add(u.getId());
			}
			return uIds;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
