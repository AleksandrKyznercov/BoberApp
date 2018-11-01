package hibernate.dao;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "treaty", schema = "bober_app_db", catalog = "")
public class TreatyEntity {
    private int idTreaty;
    private Integer idCustomer;
    private LocalDate start;
    private LocalDate stop;
    private String status;
    private Integer commonPrice;

    @Id
    @Column(name = "ID_Treaty", nullable = false)
    public int getIdTreaty() {
        return idTreaty;
    }

    public void setIdTreaty(int idTreaty) {
        this.idTreaty = idTreaty;
    }

    @Basic
    @Column(name = "ID_Customer", nullable = true)
    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Basic
    @Column(name = "Start", nullable = true)
    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    @Basic
    @Column(name = "Stop", nullable = true)
    public LocalDate getStop() {
        return stop;
    }

    public void setStop(LocalDate stop) {
        this.stop = stop;
    }

    @Basic
    @Column(name = "Status", nullable = true, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CommonPrice", nullable = true)
    public Integer getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(Integer commonPrice) {
        this.commonPrice = commonPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreatyEntity that = (TreatyEntity) o;

        if (idTreaty != that.idTreaty) return false;
        if (idCustomer != null ? !idCustomer.equals(that.idCustomer) : that.idCustomer != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (stop != null ? !stop.equals(that.stop) : that.stop != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (commonPrice != null ? !commonPrice.equals(that.commonPrice) : that.commonPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTreaty;
        result = 31 * result + (idCustomer != null ? idCustomer.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (stop != null ? stop.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (commonPrice != null ? commonPrice.hashCode() : 0);
        return result;
    }
}
