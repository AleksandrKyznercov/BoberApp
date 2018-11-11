package DAO;

import Models.Customer;
import hibernate.dao.CustomerEntity;

import java.util.List;

public class CustomerService {

    private static CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void persist(CustomerEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        customerDAO.persist(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public void update(CustomerEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        customerDAO.update(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public CustomerEntity findById(int id) {
        ConnectionPool.getInstance().openCurrentSession();
        CustomerEntity customer = customerDAO.findById(id);
        ConnectionPool.getInstance().closeCurrentSession();
        return customer;
    }

    public CustomerEntity findByNameAndPass(String name, String passSerialNumber, String passNumber) {
        ConnectionPool.getInstance().openCurrentSession();
        CustomerEntity customer = customerDAO.findByNameAndPass(name, passSerialNumber, passNumber);
        ConnectionPool.getInstance().closeCurrentSession();
        return customer;
    }


    public int getLastID() {
        ConnectionPool.getInstance().openCurrentSession();
        int lastID = customerDAO.getLastID();
        ConnectionPool.getInstance().closeCurrentSession();
        return lastID;
    }

    public void delete(int id) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        CustomerEntity customer = customerDAO.findById(id);
        customerDAO.delete(customer);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public List<CustomerEntity> findAll() {
        ConnectionPool.getInstance().openCurrentSession();
        List<CustomerEntity> customers = customerDAO.findAll();
        ConnectionPool.getInstance().closeCurrentSession();
        return customers;
    }

    public void deleteAll() {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        customerDAO.deleteAll();
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public CustomerDAO customerDAO() {
        return customerDAO;
    }

    public void close(){
        customerDAO().finalize();
    }

}
