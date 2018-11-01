package DAO;

import hibernate.dao.EquipmentEntity;

import java.util.List;

public class EquipmentService {

    private static EquipmentDAO equipmentDAO;

    public EquipmentService() {
        equipmentDAO = new EquipmentDAO();
    }

    public void persist(EquipmentEntity entity) {
        equipmentDAO.openCurrentSessionwithTransaction();
        equipmentDAO.persist(entity);
        equipmentDAO.closeCurrentSessionwithTransaction();
    }

    public void update(EquipmentEntity entity) {
        equipmentDAO.openCurrentSessionwithTransaction();
        equipmentDAO.update(entity);
        equipmentDAO.closeCurrentSessionwithTransaction();
    }

    public EquipmentEntity findById(int id) {
        equipmentDAO.openCurrentSession();
        EquipmentEntity customer = equipmentDAO.findById(id);
        equipmentDAO.closeCurrentSession();
        return customer;
    }

    public void delete(int id) {
        equipmentDAO.openCurrentSessionwithTransaction();
        EquipmentEntity customer = equipmentDAO.findById(id);
        equipmentDAO.delete(customer);
        equipmentDAO.closeCurrentSessionwithTransaction();
    }

    public List<EquipmentEntity> findAll() {
        equipmentDAO.openCurrentSession();
        List<EquipmentEntity> customers = equipmentDAO.findAll();
        equipmentDAO.closeCurrentSession();
        return customers;
    }

    public EquipmentEntity findByName(String name) {
        equipmentDAO.openCurrentSession();
        EquipmentEntity equipmentEntity = equipmentDAO.findByName(name);
        equipmentDAO.closeCurrentSession();
        return equipmentEntity;
    }

    public List<EquipmentEntity> findAllNames() {
        equipmentDAO.openCurrentSession();
        List<EquipmentEntity> customers = equipmentDAO.findAllNames();
        equipmentDAO.closeCurrentSession();
        return customers;
    }

    public void deleteAll() {
        equipmentDAO.openCurrentSessionwithTransaction();
        equipmentDAO.deleteAll();
        equipmentDAO.closeCurrentSessionwithTransaction();
    }

    public EquipmentDAO equipmentDAO() {
        return equipmentDAO;
    }

    public void close(){
        equipmentDAO().finalize();
    }

}
