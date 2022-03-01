package com.ptn.geesetool.dao;

import com.ptn.geesetool.config.HibernateUtil;
import com.ptn.geesetool.model.ImageInfo;
import com.ptn.geesetool.model.TypeCrawl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DataCrudImpl implements IDataCrud {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public <T> void saveOrUpdate(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(t);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<ImageInfo> findAll() {
        Session session = sessionFactory.openSession();
        List<ImageInfo> rs = new ArrayList<>();
        try {
            String sql = "from ImageInfo";
            Query query = session.createQuery(sql, ImageInfo.class);
            rs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }

    @Override
    public ImageInfo findImgById(String id) {
        Session session = sessionFactory.openSession();
        ImageInfo rs = new ImageInfo();
        try {
            String sql = "from ImageInfo where id=:id";
            Query query = session.createQuery(sql, ImageInfo.class).setParameter("id", id);
            rs = (ImageInfo) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }

    @Override
    public TypeCrawl findTypeById(int id) {
        Session session = sessionFactory.openSession();
        TypeCrawl rs = new TypeCrawl();
        try {
            String sql = "from TypeCrawl where id=:id";
            Query query = session.createQuery(sql, TypeCrawl.class).setParameter("id", id);
            rs = (TypeCrawl) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rs;
    }
}
