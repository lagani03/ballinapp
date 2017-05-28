package demo;

import org.hibernate.Session;

import com.ballinapp.util.HibernateUtil;

public class HibernateTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.close();
	}

}
