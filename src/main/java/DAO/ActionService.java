package DAO;

import hibernate.dao.ActionEntity;

import java.util.List;

public class ActionService {

    private static ActionDAO actionDAO;

    public ActionService() {
        actionDAO = new ActionDAO();
    }

    public void persist(ActionEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        actionDAO.persist(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public void update(ActionEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        actionDAO.update(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public ActionEntity findById(int id) {
        ConnectionPool.getInstance().openCurrentSession();
        ActionEntity customer = actionDAO.findById(id);
        ConnectionPool.getInstance().closeCurrentSession();
        return customer;
    }

    public void delete(int id) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        ActionEntity customer = actionDAO.findById(id);
        actionDAO.delete(customer);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public List<ActionEntity> findAll() {
        ConnectionPool.getInstance().openCurrentSession();
        List<ActionEntity> customers = actionDAO.findAll();
        ConnectionPool.getInstance().closeCurrentSession();
        return customers;
    }

    public List<ActionEntity> findAllByIDTreaty(int idTreaty) {
        ConnectionPool.getInstance().openCurrentSession();
        List<ActionEntity> customers = actionDAO.findAllByIDTreaty(idTreaty);
        ConnectionPool.getInstance().closeCurrentSession();
        return customers;
    }


    public void deleteAll() {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        actionDAO.deleteAll();
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public ActionDAO actionDAO() {
        return actionDAO;
    }

    public void close(){
        actionDAO().finalize();
    }

}
