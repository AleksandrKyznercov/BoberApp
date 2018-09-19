package hibernate.dao;

import javax.persistence.*;

@Entity
@Table(name = "customer", schema = "bober_app_db", catalog = "")
public class CustomerEntity {
    private int idCustomer;
    private String fio;
    private String passSerialNumber;
    private String passNumber;
    private String vidan;
    private String adressProp;
    private String adressFact;
    private String phone;

    @Id
    @Column(name = "ID_Customer", nullable = false)
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Basic
    @Column(name = "FIO", nullable = false, length = 400)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "passSerialNumber", nullable = false, length = 220)
    public String getPassSerialNumber() {
        return passSerialNumber;
    }

    public void setPassSerialNumber(String passSerialNumber) {
        this.passSerialNumber = passSerialNumber;
    }

    @Basic
    @Column(name = "passNumber", nullable = false, length = 220)
    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    @Basic
    @Column(name = "vidan", nullable = true, length = 550)
    public String getVidan() {
        return vidan;
    }

    public void setVidan(String vidan) {
        this.vidan = vidan;
    }

    @Basic
    @Column(name = "adressProp", nullable = true, length = 500)
    public String getAdressProp() {
        return adressProp;
    }

    public void setAdressProp(String adressProp) {
        this.adressProp = adressProp;
    }

    @Basic
    @Column(name = "adressFact", nullable = true, length = 500)
    public String getAdressFact() {
        return adressFact;
    }

    public void setAdressFact(String adressFact) {
        this.adressFact = adressFact;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 220)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (idCustomer != that.idCustomer) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (passSerialNumber != null ? !passSerialNumber.equals(that.passSerialNumber) : that.passSerialNumber != null)
            return false;
        if (passNumber != null ? !passNumber.equals(that.passNumber) : that.passNumber != null) return false;
        if (vidan != null ? !vidan.equals(that.vidan) : that.vidan != null) return false;
        if (adressProp != null ? !adressProp.equals(that.adressProp) : that.adressProp != null) return false;
        if (adressFact != null ? !adressFact.equals(that.adressFact) : that.adressFact != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCustomer;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (passSerialNumber != null ? passSerialNumber.hashCode() : 0);
        result = 31 * result + (passNumber != null ? passNumber.hashCode() : 0);
        result = 31 * result + (vidan != null ? vidan.hashCode() : 0);
        result = 31 * result + (adressProp != null ? adressProp.hashCode() : 0);
        result = 31 * result + (adressFact != null ? adressFact.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
