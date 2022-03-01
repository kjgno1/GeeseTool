package com.ptn.geesetool.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
//                    .configure("hibernate.cfg.xml").build();
//            Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
//
//            sessionFactory = metadata.getSessionFactoryBuilder().build();
//        } catch (Throwable th) {
//            th.printStackTrace();
//            throw new ExceptionInInitializerError(th);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return sessionFactory;
//    }


    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static final SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            //jar driver not found fix
            try{
                Class.forName("org.sqlite.JDBC");
            }catch (ClassNotFoundException e){

            }

            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }
    
}
