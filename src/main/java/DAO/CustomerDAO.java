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

import javax.persistence.Query;
import java.util.List;

public class CustomerDAO {

    public void persist(CustomerEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().save(entity);
    }

    public void update(CustomerEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().update(entity);
    }

    public CustomerEntity findById(int id) {
        CustomerEntity customer = (CustomerEntity) ConnectionPool.getInstance().getCurrentSession().get(CustomerEntity.class, id);
        return customer;
    }

    public int getLastID() {
        CustomerEntity customer = (CustomerEntity) ConnectionPool.getInstance().getCurrentSession().createQuery("from CustomerEntity ORDER BY id DESC").setMaxResults(1).uniqueResult();
        return customer.getIdCustomer();
    }

    public void delete(CustomerEntity entity) {
        ConnectionPool.getInstance().getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customer = (List<CustomerEntity>) ConnectionPool.getInstance().getCurrentSession().createQuery("from CustomerEntity").list();
        return customer;
    }

    public CustomerEntity findByNameAndPass(String fio, String passSerial, String passNumber) {
        try {
            Query query = ConnectionPool.getInstance().getCurrentSession().createQuery("from CustomerEntity where fio=:fio and passSerialNumber=:passSerial and passNumber=:passNumber").setMaxResults(1);
            query.setParameter("fio", fio);
            query.setParameter("passSerial", passSerial);
            query.setParameter("passNumber", passNumber);
            CustomerEntity customerEntity = (CustomerEntity) ((org.hibernate.query.Query) query).uniqueResult();

            return customerEntity;
        }catch (NullPointerException e){
            System.out.println("findByNameAndPass NullPointerException: " + e);
            return null;
        }catch (Exception e) {
            System.out.println("findByNameAndPass exception: " + e);
            return null;
        }
    }

    public void deleteAll() {
        List<CustomerEntity> entityList = findAll();
        for (CustomerEntity entity : entityList) {
            delete(entity);
        }
    }

    public void finalize() {
        /*currentTransaction.commit();
        sessionFactory.close();*/
        System.out.println("CoustomerDAO connection close");
        ConnectionPool.getInstance().getSessionFactory().close();
    }

}
