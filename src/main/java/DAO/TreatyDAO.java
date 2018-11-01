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

    private Session currentSession;

    private static SessionFactory sessionFactory = buildSessionFactory();

    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory buildSessionFactory() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );

            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(TreatyEntity entity) {
        getCurrentSession().save(entity);
    }

    public void update(TreatyEntity entity) {
        getCurrentSession().update(entity);
    }

    public TreatyEntity findById(int id) {
        TreatyEntity treaty = (TreatyEntity) getCurrentSession().get(TreatyEntity.class, id);
        return treaty;
    }

    public int getLastID() {
        TreatyEntity treaty = (TreatyEntity) getCurrentSession().createQuery("from TreatyEntity ORDER BY id DESC").setMaxResults(1).uniqueResult();
        return treaty.getIdTreaty();
    }

    public void delete(TreatyEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<TreatyEntity> findAll() {
        List<TreatyEntity> treaty = (List<TreatyEntity>) getCurrentSession().createQuery("from TreatyEntity").list();
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
        sessionFactory.close();
    }

}
