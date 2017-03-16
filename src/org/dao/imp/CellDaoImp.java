package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.CellDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.imodel.VeCells;
import org.imodel.VeCellsId;
import org.model.Cell;
import org.model.UserCell;
import org.util.HibernateSessionFactory;

public class CellDaoImp implements CellDao {

	public List<UserCell> getCellList(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("from UserCell where userid = ?");
			query.setParameter(0, userid);
			List<UserCell> list = query.list();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return new ArrayList<UserCell>();
		}
	}

	public UserCell getUserCell(Long cellid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from UserCell where cellid = ?");
			query.setParameter(0, cellid);
			query.setMaxResults(1);
			UserCell userCell = (UserCell) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return userCell;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public Cell getCell(Long cellid) {

		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Cell where id = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, cellid);
			query.setMaxResults(1);
			Cell cell = (Cell) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return cell;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public boolean insert(UserCell userCell) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			UserCell userCell2 = new UserCell();
			userCell2.setCellid(userCell.getCellid());
			userCell2.setUserid(userCell.getUserid());
			session.save(userCell2);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public boolean insert(Cell cell) {

		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Cell cell2 = new Cell();
			cell2.setClock(cell.getClock());
			cell2.setName(cell.getName());
			cell2.setNum(cell.getNum());
			cell2.setSerial(cell.getSerial());
			session.save(cell2);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	public Long getMaxId() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select Max(id) from Cell");
			query.setMaxResults(1);

			Long maxid = (Long) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return maxid;

		} catch (Exception e) {
			HibernateSessionFactory.closeSession();
			return null;
		}

	}

	public boolean deleteUserCell(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query q = session
					.createQuery("delete from UserCell where userid=?");
			q.setParameter(0, userid);
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

	public boolean deleteCells(String[] cellids) {
		String sql = "";
		for (int i = 0; i < cellids.length; i++) {
			if (i == 0) {
				sql = "id=" + cellids[i];
			} else {
				sql = sql + " or id=" + cellids[i];
			}
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query q = session.createQuery("delete from Cell where " + sql);
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
	public List<VeCellsId> getCells(Integer Page, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from ve_cells";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(VeCells.class);
			if (start != null) {
				sqlQuery.setFirstResult(start);
			} else {
				sqlQuery.setFirstResult((Page - 1) * limit);
			}
			sqlQuery.setMaxResults(limit);
			List<VeCells> list = sqlQuery.list();
			List<VeCellsId> list2 = new ArrayList<>();
			for (VeCells v : list) {
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

	@Override
	public Long getCellCount() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("select count(id) from Cell");
			Long count = (Long) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return (long) 0;
		}
	}

	public boolean delete(Cell cell) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.delete(cell);
			ts.commit();
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return false;
		}
	}

	@Override
	public Cell findCell(String name, String clock, String serial) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Cell where name = ? and clock=? and serial=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, name);
			query.setParameter(1, Integer.parseInt(clock));
			query.setParameter(2, serial);
			query.setMaxResults(1);
			Cell cell = (Cell) query.uniqueResult();
			HibernateSessionFactory.closeSession();
			return cell;
		} catch (Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

}
