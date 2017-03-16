package org.dao.imp;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dao.ItemsDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imodel.VeItems;
import org.imodel.VeItemsId;
import org.imodel.VeUserinfo;
import org.imodel.VeUserinfoId;
import org.model.Can;
import org.model.Items;
import org.util.HibernateSessionFactory;

public class ItemsDaoImp implements ItemsDao {

	public boolean insert(List<String> r) {
		if (r.size() < 11) {
			return false;
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Items i = new Items(r.get(2), r.get(3), r.get(4), r.get(5),
					r.get(6), r.get(7), r.get(8), r.get(9), r.get(10),
					r.get(11), r.get(12), r.get(13),
					Integer.parseInt(r.get(14)));
			session.save(i);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return false;
		}

	}

	public Items getLastItemsBySerial(String Serial) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from Items where serial = ? ORDER BY clock DESC";
			Query query = session.createQuery(sql);
			query.setParameter(0, Serial);
			query.setMaxResults(1);
			Items items = (Items) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return items;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List<Items> getItemListBySerial(String Serial) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from Items where serial = ? ORDER BY clock DESC";
			Query query = session.createQuery(sql);
			query.setParameter(0, Serial);
			List<Items> items = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return items;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List<VeItemsId> getItemList(Integer Page, Integer start,
			Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from ve_items";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VeItems.class);
			if (start != null) {
				sqlQuery.setFirstResult(start);
			} else {
				sqlQuery.setFirstResult((Page - 1) * limit);
			}
			sqlQuery.setMaxResults(limit);
			List<VeItems> list = sqlQuery.list();
			List<VeItemsId> list2 = new ArrayList<>();
			for (VeItems v : list) {
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

	public List getItemList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "select i.*,c.address from items i,can c where c.serial=i.serial ORDER BY i.clock DESC; ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity("i", Items.class).addEntity("c", Can.class);
			List list = sqlQuery.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean deleteItem(String id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Items item = (Items) session.load(Items.class, Long.parseLong(id));
			session.delete(item);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public List<VeItemsId> getItemsByPeriod(String start_time, String end_time,
			Integer Page, Integer start, Integer limit) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from ve_items where clock>? and clock<?";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, start_time);
			sqlQuery.setParameter(1, end_time);
			sqlQuery.addEntity(VeItems.class);
			if (start != null) {
				sqlQuery.setFirstResult(start);
			} else {
				sqlQuery.setFirstResult(Page * limit);
			}
			sqlQuery.setMaxResults(limit);
			List<VeItems> list = sqlQuery.list();
			List<VeItemsId> list2 = new ArrayList<>();
			for (VeItems v : list) {
				list2.add(v.getId());
			}
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list2;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean delete(String[] itemids) {
		String sql = "";
		for (int i = 0; i < itemids.length; i++) {
			if (i == 0) {
				sql = "id=?";
			} else {
				sql = sql + " or id=?";
			}
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SQLQuery q = session.createSQLQuery("delete from items where "
					+ sql);
			for (int i = 0; i < itemids.length; i++) {
				q.setParameter(i, Long.parseLong(itemids[i]));
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

	public boolean delete(Integer start_clock, Integer end_clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query q = session
					.createQuery("delete from Items where clock>? and clock<?");
			q.setParameter(0, start_clock);
			q.setParameter(1, end_clock);
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

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from Items");
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

	@Override
	public Long getCount(Integer start_clock, Integer end_clock) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select count(id) from Items where clock>? and clock <?");
			query.setParameter(0, start_clock);
			query.setParameter(1, end_clock);
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

}
