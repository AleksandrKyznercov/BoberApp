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
        customerDAO.openCurrentSessionwithTransaction();
        customerDAO.persist(entity);
        customerDAO.closeCurrentSessionwithTransaction();
    }

    public void update(CustomerEntity entity) {
        customerDAO.openCurrentSessionwithTransaction();
        customerDAO.update(entity);
        customerDAO.closeCurrentSessionwithTransaction();
    }

    public CustomerEntity findById(int id) {
        customerDAO.openCurrentSession();
        CustomerEntity customer = customerDAO.findById(id);
        customerDAO.closeCurrentSession();
        return customer;
    }


    public int getLastID() {
        customerDAO.openCurrentSession();
        int lastID = customerDAO.getLastID();
        customerDAO.closeCurrentSession();
        return lastID;
    }

    public void delete(int id) {
        customerDAO.openCurrentSessionwithTransaction();
        CustomerEntity customer = customerDAO.findById(id);
        customerDAO.delete(customer);
        customerDAO.closeCurrentSessionwithTransaction();
    }

    public List<CustomerEntity> findAll() {
        customerDAO.openCurrentSession();
        List<CustomerEntity> customers = customerDAO.findAll();
        customerDAO.closeCurrentSession();
        return customers;
    }

    public void deleteAll() {
        customerDAO.openCurrentSessionwithTransaction();
        customerDAO.deleteAll();
        customerDAO.closeCurrentSessionwithTransaction();
    }

    public CustomerDAO customerDAO() {
        return customerDAO;
    }

    public void close(){
        customerDAO().finalize();
    }

}
