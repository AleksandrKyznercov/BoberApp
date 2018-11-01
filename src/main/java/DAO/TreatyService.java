package DAO;

import hibernate.dao.TreatyEntity;

import java.util.List;

public class TreatyService {

    private static TreatyDAO treatyDAO;

    public TreatyService() {
        treatyDAO = new TreatyDAO();
    }

    public void persist(TreatyEntity entity) {
        treatyDAO.openCurrentSessionwithTransaction();
        treatyDAO.persist(entity);
        treatyDAO.closeCurrentSessionwithTransaction();
    }

    public void update(TreatyEntity entity) {
        treatyDAO.openCurrentSessionwithTransaction();
        treatyDAO.update(entity);
        treatyDAO.closeCurrentSessionwithTransaction();
    }

    public TreatyEntity findById(int id) {
        treatyDAO.openCurrentSession();
        TreatyEntity customer = treatyDAO.findById(id);
        treatyDAO.closeCurrentSession();
        return customer;
    }

    public int getLastID() {
        treatyDAO().openCurrentSession();
        int lastID = treatyDAO.getLastID();
        treatyDAO.closeCurrentSession();
        return lastID;
    }

    public void delete(int id) {
        treatyDAO.openCurrentSessionwithTransaction();
        TreatyEntity customer = treatyDAO.findById(id);
        treatyDAO.delete(customer);
        treatyDAO.closeCurrentSessionwithTransaction();
    }

    public List<TreatyEntity> findAll() {
        treatyDAO.openCurrentSession();
        List<TreatyEntity> customers = treatyDAO.findAll();
        treatyDAO.closeCurrentSession();
        return customers;
    }

    public void deleteAll() {
        treatyDAO.openCurrentSessionwithTransaction();
        treatyDAO.deleteAll();
        treatyDAO.closeCurrentSessionwithTransaction();
    }

    public TreatyDAO treatyDAO() {
        return treatyDAO;
    }

    public void close(){
        treatyDAO().finalize();
    }

}
