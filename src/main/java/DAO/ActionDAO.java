package DAO;

import hibernate.dao.ActionEntity;
import hibernate.dao.EquipmentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class ActionDAO {

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

    public void persist(ActionEntity entity) {
        getCurrentSession().save(entity);
    }

    public void update(ActionEntity entity) {
        getCurrentSession().update(entity);
    }

    public ActionEntity findById(int id) {
        ActionEntity customer = (ActionEntity) getCurrentSession().get(ActionEntity.class, id);
        return customer;
    }

    public void delete(ActionEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ActionEntity> findAll() {
        List<ActionEntity> action = (List<ActionEntity>) getCurrentSession().createQuery("from ActionEntity").list();
        return action;
    }

    public List<ActionEntity> findAllByIDTreaty(int ID_Treaty) {
        Query query = currentSession.createQuery("from ActionEntity where idTreaty=:idTreaty");
        query.setParameter("idTreaty", ID_Treaty);
        List<ActionEntity> action = (List<ActionEntity>) ((org.hibernate.query.Query) query).list();
        return action;
    }

    public void deleteAll() {
        List<ActionEntity> entityList = findAll();
        for (ActionEntity entity : entityList) {
            delete(entity);
        }
    }

    public void finalize () {
        /*currentTransaction.commit();
        sessionFactory.close();*/
        System.out.println("АctionDAO connection close");
        sessionFactory.close();
    }

}
