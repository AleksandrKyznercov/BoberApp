package DAO;

import hibernate.dao.EquipmentEntity;

import java.util.List;

public class EquipmentService {

    private static EquipmentDAO equipmentDAO;

    public EquipmentService() {
        equipmentDAO = new EquipmentDAO();
    }

    public void persist(EquipmentEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        equipmentDAO.persist(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public void update(EquipmentEntity entity) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        equipmentDAO.update(entity);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public EquipmentEntity findById(int id) {
        ConnectionPool.getInstance().openCurrentSession();
        EquipmentEntity customer = equipmentDAO.findById(id);
        ConnectionPool.getInstance().closeCurrentSession();
        return customer;
    }

    public void delete(int id) {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        EquipmentEntity customer = equipmentDAO.findById(id);
        equipmentDAO.delete(customer);
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public List<EquipmentEntity> findAll() {
        ConnectionPool.getInstance().openCurrentSession();
        List<EquipmentEntity> customers = equipmentDAO.findAll();
        ConnectionPool.getInstance().closeCurrentSession();
        return customers;
    }

    public EquipmentEntity findByName(String name) {
        ConnectionPool.getInstance().openCurrentSession();
        EquipmentEntity equipmentEntity = equipmentDAO.findByName(name);
        ConnectionPool.getInstance().closeCurrentSession();
        return equipmentEntity;
    }

    public List<EquipmentEntity> findAllNames() {
        ConnectionPool.getInstance().openCurrentSession();
        List<EquipmentEntity> customers = equipmentDAO.findAllNames();
        ConnectionPool.getInstance().closeCurrentSession();
        return customers;
    }

    public void deleteAll() {
        ConnectionPool.getInstance().openCurrentSessionwithTransaction();
        equipmentDAO.deleteAll();
        ConnectionPool.getInstance().closeCurrentSessionwithTransaction();
    }

    public EquipmentDAO equipmentDAO() {
        return equipmentDAO;
    }

    public void close(){
        equipmentDAO().finalize();
    }

}
