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

    public void persist(EquipmentEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().save(entity);
    }

    public void update(EquipmentEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().update(entity);
    }

    public EquipmentEntity findById(int id) {
        EquipmentEntity equipment = (EquipmentEntity) ConnectionPool.getInstance().getCurrentSession().get(EquipmentEntity.class, id);
        return equipment;
    }

    public EquipmentEntity findByName(String name) {
        Query query = ConnectionPool.getInstance().getCurrentSession().createQuery("from EquipmentEntity where name=:name");
        query.setParameter("name", name);
        EquipmentEntity equipmentEntity = (EquipmentEntity) ((org.hibernate.query.Query) query).uniqueResult();
        return equipmentEntity;
    }

    public void delete(EquipmentEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<EquipmentEntity> findAll() {
        List<EquipmentEntity> customer = (List<EquipmentEntity>) ConnectionPool.getInstance().getCurrentSession().createQuery("from EquipmentEntity").list();
        return customer;
    }

    public List<EquipmentEntity> findAllNames() {
        CriteriaBuilder builder = ConnectionPool.getInstance().getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<EquipmentEntity> query = builder.createQuery(EquipmentEntity.class);
        Root<EquipmentEntity> root = query.from(EquipmentEntity.class);
        query.select(root.get("name"));
        org.hibernate.Query<EquipmentEntity> q = ConnectionPool.getInstance().getCurrentSession().createQuery(query);
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
        ConnectionPool.getInstance().getSessionFactory().close();
    }

}
