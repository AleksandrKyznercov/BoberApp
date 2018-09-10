package hibernate.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment", schema = "bober_app_db", catalog = "")
public class EquipmentEntity {
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

    @Id
    @Column(name = "ID_Equipment", nullable = false)
    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    @Basic
    @Column(name = "Name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SerialNumber", nullable = true, length = 200)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "Cost", nullable = true)
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "Deposit", nullable = true)
    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "Defenition", nullable = true, length = -1)
    public String getDefenition() {
        return defenition;
    }

    public void setDefenition(String defenition) {
        this.defenition = defenition;
    }

    @Basic
    @Column(name = "PriceFor4", nullable = true)
    public Integer getPriceFor4() {
        return priceFor4;
    }

    public void setPriceFor4(Integer priceFor4) {
        this.priceFor4 = priceFor4;
    }

    @Basic
    @Column(name = "PriceFor8", nullable = true)
    public Integer getPriceFor8() {
        return priceFor8;
    }

    public void setPriceFor8(Integer priceFor8) {
        this.priceFor8 = priceFor8;
    }

    @Basic
    @Column(name = "PriceFor24", nullable = true)
    public Integer getPriceFor24() {
        return priceFor24;
    }

    public void setPriceFor24(Integer priceFor24) {
        this.priceFor24 = priceFor24;
    }

    @Basic
    @Column(name = "Remark", nullable = true, length = -1)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntity that = (EquipmentEntity) o;
        return idEquipment == that.idEquipment &&
                Objects.equals(name, that.name) &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(deposit, that.deposit) &&
                Objects.equals(defenition, that.defenition) &&
                Objects.equals(priceFor4, that.priceFor4) &&
                Objects.equals(priceFor8, that.priceFor8) &&
                Objects.equals(priceFor24, that.priceFor24) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idEquipment, name, serialNumber, cost, deposit, defenition, priceFor4, priceFor8, priceFor24, remark, status);
    }
}
