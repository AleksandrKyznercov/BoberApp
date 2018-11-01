package DAO;

import Models.Customer;
import hibernate.dao.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class CustomerDAO {

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

    public void persist(CustomerEntity entity) {
        getCurrentSession().save(entity);
    }

    public void update(CustomerEntity entity) {
        getCurrentSession().update(entity);
    }

    public CustomerEntity findById(int id) {
        CustomerEntity customer = (CustomerEntity) getCurrentSession().get(CustomerEntity.class, id);
        return customer;
    }

    public int getLastID() {
        CustomerEntity customer = (CustomerEntity) getCurrentSession().createQuery("from CustomerEntity ORDER BY id DESC").setMaxResults(1).uniqueResult();
        return customer.getIdCustomer();
    }

    public void delete(CustomerEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customer = (List<CustomerEntity>) getCurrentSession().createQuery("from CustomerEntity").list();
        return customer;
    }

    public void deleteAll() {
        List<CustomerEntity> entityList = findAll();
        for (CustomerEntity entity : entityList) {
            delete(entity);
        }
    }

    public void finalize () {
        /*currentTransaction.commit();
        sessionFactory.close();*/
        System.out.println("CoustomerDAO connection close");
        sessionFactory.close();
    }

}
