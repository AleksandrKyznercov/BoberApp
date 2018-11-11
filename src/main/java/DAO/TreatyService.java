package DAO;

import hibernate.dao.TreatyEntity;

import java.util.List;

public class TreatyService {

    private static TreatyDAO treatyDAO;

    public TreatyService() {
        treatyDAO = new TreatyDAO();
    }

    public void persist(TreatyEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        treatyDAO.persist(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public void update(TreatyEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        treatyDAO.update(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public TreatyEntity findById(int id) {
        ConnectionPool.getInstance().openCurrentSession();
        TreatyEntity customer = treatyDAO.findById(id);
        ConnectionPool.getInstance().closeCurrentSession();
        return customer;
    }

    public int getLastID() {
        ConnectionPool.getInstance().openCurrentSession();
        int lastID = treatyDAO.getLastID();
        ConnectionPool.getInstance().closeCurrentSession();
        return lastID;
    }

    public void delete(int id) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        TreatyEntity customer = treatyDAO.findById(id);
        treatyDAO.delete(customer);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public List<TreatyEntity> findAll() {
        ConnectionPool.getInstance().openCurrentSession();
        List<TreatyEntity> customers = treatyDAO.findAll();
        ConnectionPool.getInstance().closeCurrentSession();
        return customers;
    }

    public void deleteAll() {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        treatyDAO.deleteAll();
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public TreatyDAO treatyDAO() {
        return treatyDAO;
    }

    public void close(){
        treatyDAO().finalize();
    }

}
