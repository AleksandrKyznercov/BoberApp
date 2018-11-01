package DAO;

import hibernate.dao.EquipmentEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Property;
import org.hibernate.type.EntityType;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EquipmentDAO {

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

    public void persist(EquipmentEntity entity) {
        getCurrentSession().save(entity);
    }

    public void update(EquipmentEntity entity) {
        getCurrentSession().update(entity);
    }

    public EquipmentEntity findById(int id) {
        EquipmentEntity equipment = (EquipmentEntity) getCurrentSession().get(EquipmentEntity.class, id);
        return equipment;
    }

    public EquipmentEntity findByName(String name) {
        Query query = currentSession.
                createQuery("from EquipmentEntity where name=:name");
        query.setParameter("name", name);
        EquipmentEntity equipmentEntity = (EquipmentEntity) ((org.hibernate.query.Query) query).uniqueResult();
        return equipmentEntity;
    }

    public void delete(EquipmentEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<EquipmentEntity> findAll() {
        List<EquipmentEntity> customer = (List<EquipmentEntity>) getCurrentSession().createQuery("from EquipmentEntity").list();
        return customer;
    }

    public List<EquipmentEntity> findAllNames() {
        CriteriaBuilder builder = currentSession.getCriteriaBuilder();
        CriteriaQuery<EquipmentEntity> query = builder.createQuery(EquipmentEntity.class);
        Root<EquipmentEntity> root = query.from(EquipmentEntity.class);
        query.select(root.get("name"));
        org.hibernate.Query<EquipmentEntity> q = currentSession.createQuery(query);
        List<EquipmentEntity> employees = q.getResultList();
        return employees;
    }

    public void deleteAll() {
        List<EquipmentEntity> entityList = findAll();
        for (EquipmentEntity entity : entityList) {
            delete(entity);
        }
    }

    public void finalize () {
        /*currentTransaction.commit();
        sessionFactory.close();*/
        System.out.println("EquipmentDAO connection close");
        sessionFactory.close();
    }

}
