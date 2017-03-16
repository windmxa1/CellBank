package test1;

import java.util.List;

import org.dao.TokenDao;
import org.dao.UserDao;
import org.dao.imp.TokenDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.model.User;
import org.util.HibernateSessionFactory;


public class Test1 {
	public List getList(Integer start , Integer limit){
		try {
			
			Session session = HibernateSessionFactory.getSession();
			String sql = "select * from user";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setMaxResults(limit);
			sqlQuery.setFirstResult(start);
			sqlQuery.addEntity(User.class);
			List<User> users = sqlQuery.list();
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
	}
	public static void main(String[] args) {
		Integer start = 0;  //这个就相当于接收start参数，如果用Int类型接收就不用写39行的判空，如果用 Integer接收就必须写判空
		Integer limit  = 0;
		if(start==null){
			start=0;
		}
		new Test1().getList(start, limit);
	}
}
