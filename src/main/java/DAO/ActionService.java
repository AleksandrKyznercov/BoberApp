package DAO;

import hibernate.dao.ActionEntity;

import java.util.List;

public class ActionService {

    private static ActionDAO actionDAO;

    public ActionService() {
        actionDAO = new ActionDAO();
    }

    public void persist(ActionEntity entity) {
        actionDAO.openCurrentSessionwithTransaction();
        actionDAO.persist(entity);
        actionDAO.closeCurrentSessionwithTransaction();
    }

    public void update(ActionEntity entity) {
        actionDAO.openCurrentSessionwithTransaction();
        actionDAO.update(entity);
        actionDAO.closeCurrentSessionwithTransaction();
    }

    public ActionEntity findById(int id) {
        actionDAO.openCurrentSession();
        ActionEntity customer = actionDAO.findById(id);
        actionDAO.closeCurrentSession();
        return customer;
    }

    public void delete(int id) {
        actionDAO.openCurrentSessionwithTransaction();
        ActionEntity customer = actionDAO.findById(id);
        actionDAO.delete(customer);
        actionDAO.closeCurrentSessionwithTransaction();
    }

    public List<ActionEntity> findAll() {
        actionDAO.openCurrentSession();
        List<ActionEntity> customers = actionDAO.findAll();
        actionDAO.closeCurrentSession();
        return customers;
    }

    public List<ActionEntity> findAllByIDTreaty(int idTreaty) {
        actionDAO.openCurrentSession();
        List<ActionEntity> customers = actionDAO.findAllByIDTreaty(idTreaty);
        actionDAO.closeCurrentSession();
        return customers;
    }


    public void deleteAll() {
        actionDAO.openCurrentSessionwithTransaction();
        actionDAO.deleteAll();
        actionDAO.closeCurrentSessionwithTransaction();
    }

    public ActionDAO actionDAO() {
        return actionDAO;
    }

    public void close(){
        actionDAO().finalize();
    }

}
