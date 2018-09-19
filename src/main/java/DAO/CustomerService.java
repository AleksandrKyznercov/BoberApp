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

    public Customer findById(String id) {
        customerDAO.openCurrentSession();
        Customer customer = customerDAO.findById(id);
        customerDAO.closeCurrentSession();
        return customer;
    }

    public void delete(String id) {
        customerDAO.openCurrentSessionwithTransaction();
        Customer customer = customerDAO.findById(id);
        customerDAO.delete(customer);
        customerDAO.closeCurrentSessionwithTransaction();
    }

    public List<Customer> findAll() {
        customerDAO.openCurrentSession();
        List<Customer> books = customerDAO.findAll();
        customerDAO.closeCurrentSession();
        return books;
    }

    public void deleteAll() {
        customerDAO.openCurrentSessionwithTransaction();
        customerDAO.deleteAll();
        customerDAO.closeCurrentSessionwithTransaction();
    }

    public CustomerDAO customerDAO() {
        return customerDAO;
    }

}
