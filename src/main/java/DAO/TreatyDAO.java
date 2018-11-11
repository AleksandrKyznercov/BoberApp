package DAO;

import hibernate.dao.TreatyEntity;
import hibernate.dao.TreatyEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class TreatyDAO {

    public void persist(TreatyEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().save(entity);
    }

    public void update(TreatyEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().update(entity);
    }

    public TreatyEntity findById(int id) {
        TreatyEntity treaty = (TreatyEntity) ConnectionPool.getInstance().getCurrentSession().get(TreatyEntity.class, id);
        return treaty;
    }

    public int getLastID() {
        TreatyEntity treaty = (TreatyEntity) ConnectionPool.getInstance().getCurrentSession().createQuery("from TreatyEntity ORDER BY id DESC").setMaxResults(1).uniqueResult();
        return treaty.getIdTreaty();
    }

    public void delete(TreatyEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<TreatyEntity> findAll() {
        List<TreatyEntity> treaty = (List<TreatyEntity>) ConnectionPool.getInstance().getCurrentSession().createQuery("from TreatyEntity").list();
        return treaty;
    }

    public void deleteAll() {
        List<TreatyEntity> entityList = findAll();
        for (TreatyEntity entity : entityList) {
            delete(entity);
        }
    }

    public void finalize () {
        /*currentTransaction.commit();
        sessionFactory.close();*/
        System.out.println("TreatyDAO connection close");
        ConnectionPool.getInstance().getSessionFactory().close();
    }

}
