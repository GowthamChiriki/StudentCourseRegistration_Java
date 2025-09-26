package com.example.registration.config;

import com.example.registration.entity.Course;
import com.example.registration.entity.Registration;
import com.example.registration.entity.Student;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static{
        try{
            Configuration configuration = new Configuration();
            // It reads the hibernate.cfg.xml file
            configuration.configure("hibernate.cfg.xml");

            //Annotated classes -> tells which class maps DB
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Registration.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Sessions creation failed " + e);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void shutDown(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }
}
