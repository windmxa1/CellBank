package test1;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.dao.PhotoDao;
import org.dao.UserDao;
import org.dao.imp.PhotoDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Photo;
import org.model.User;
import org.util.HibernateSessionFactory;
import org.util.MD5;

public class Dtest4 {
	public static void main(String[] args) {
		PhotoDao pDao = new PhotoDaoImp();
		System.out.println(pDao.delByUrl(""));
	}
}
