package hibernate.dao;

import javax.persistence.*;

@Entity
@Table(name = "аction", schema = "bober_app_db", catalog = "")
public class ActionEntity {
    private int idAction;
    private Integer idEquipment;
    private Integer idTreaty;
    private Integer cost;
    private String rentTime;

    @Id
    @Column(name = "ID_Action", nullable = false)
    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    @Basic
    @Column(name = "ID_Equipment", nullable = true)
    public Integer getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(Integer idEquipment) {
        this.idEquipment = idEquipment;
    }

    @Basic
    @Column(name = "ID_Treaty", nullable = true)
    public Integer getIdTreaty() {
        return idTreaty;
    }

    public void setIdTreaty(Integer idTreaty) {
        this.idTreaty = idTreaty;
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
    @Column(name = "RentTime", nullable = true, length = 20)
    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionEntity that = (ActionEntity) o;

        if (idAction != that.idAction) return false;
        if (idEquipment != null ? !idEquipment.equals(that.idEquipment) : that.idEquipment != null) return false;
        if (idTreaty != null ? !idTreaty.equals(that.idTreaty) : that.idTreaty != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (rentTime != null ? !rentTime.equals(that.rentTime) : that.rentTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAction;
        result = 31 * result + (idEquipment != null ? idEquipment.hashCode() : 0);
        result = 31 * result + (idTreaty != null ? idTreaty.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (rentTime != null ? rentTime.hashCode() : 0);
        return result;
    }
}
