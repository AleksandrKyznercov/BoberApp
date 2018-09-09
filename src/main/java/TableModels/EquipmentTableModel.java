package TableModels;

import hibernate.dao.EquipmentEntity;

public class EquipmentTableModel {

    private int idEquipment;
    private String name;
    private String serialNumber;
    private Integer cost;
    private Integer deposit;
    private String defenition;
    private Integer priceFor4;
    private Integer priceFor8;
    private Integer priceFor24;
    private String remark;
    private String status;

    public EquipmentTableModel() {
    }

    public EquipmentTableModel(EquipmentEntity equipment) {
        this.idEquipment = equipment.getIdEquipment();
        this.name = equipment.getName();
        this.serialNumber = equipment.getSerialNumber();
        this.cost = equipment.getCost();
        this.deposit = equipment.getDeposit();
        this.defenition = equipment.getDefenition();
        this.priceFor4 = equipment.getPriceFor4();
        this.priceFor8 = equipment.getPriceFor8();
        this.priceFor24 = equipment.getPriceFor24();
        this.remark = equipment.getRemark();
        this.status = equipment.getStatus();
    }

    public EquipmentTableModel(int idEquipment, String name, String serialNumber, Integer cost, Integer deposit, String defenition, Integer priceFor4, Integer priceFor8, Integer priceFor24, String remark, String status) {
        this.idEquipment = idEquipment;
        this.name = name;
        this.serialNumber = serialNumber;
        this.cost = cost;
        this.deposit = deposit;
        this.defenition = defenition;
        this.priceFor4 = priceFor4;
        this.priceFor8 = priceFor8;
        this.priceFor24 = priceFor24;
        this.remark = remark;
        this.status = status;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getDefenition() {
        return defenition;
    }

    public void setDefenition(String defenition) {
        this.defenition = defenition;
    }

    public Integer getPriceFor4() {
        return priceFor4;
    }

    public void setPriceFor4(Integer priceFor4) {
        this.priceFor4 = priceFor4;
    }

    public Integer getPriceFor8() {
        return priceFor8;
    }

    public void setPriceFor8(Integer priceFor8) {
        this.priceFor8 = priceFor8;
    }

    public Integer getPriceFor24() {
        return priceFor24;
    }

    public void setPriceFor24(Integer priceFor24) {
        this.priceFor24 = priceFor24;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
