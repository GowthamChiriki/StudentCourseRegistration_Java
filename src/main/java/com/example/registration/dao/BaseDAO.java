package com.example.registration.dao;

import com.example.registration.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseDAO<T>{
    // Execute operations that return a result
    protected <R> R execute(Function<Session, R> function){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            R result = function.apply(session);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    //Execute operations that do not return a result
    protected void executeVoid(Consumer<Session> consumer){
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            consumer.accept(session);
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            throw e;
        }
    }

}
