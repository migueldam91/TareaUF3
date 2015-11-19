package model;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
   	
   	private static SessionFactory sessionFactory;
 
   	static  {
   	    Configuration configuration = new Configuration();
   	    configuration.configure();
 	    sessionFactory = configuration.buildSessionFactory();
   	}  	
   	
   	
   	public static SessionFactory getSessionFactory(){
         	return sessionFactory;
   	}
 
}
