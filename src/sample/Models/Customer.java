package sample.Models;

public class Customer {
    private int ID;
    private String FIO;
    private String passSerialNumber;
    private String passNumber;
    private String vidan;
    private String adressProp;
    private String adressFact;
    private String phone;

    public Customer() {
        this.FIO = null;
        this.passSerialNumber = null;
        this.passNumber = null;
        this.vidan = null;
        this.adressProp = null;
        this.adressFact = null;
        this.phone = null;
    }

    public Customer(int ID, String FIO, String passSerialNumber, String passNumber, String vidan, String adressProp, String adressFact, String phone) {
        this.ID = ID;
        this.FIO = FIO;
        this.passSerialNumber = passSerialNumber;
        this.passNumber = passNumber;
        this.vidan = vidan;
        this.adressProp = adressProp;
        this.adressFact = adressFact;
        this.phone = phone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPassSerialNumber() {
        return passSerialNumber;
    }

    public void setPassSerialNumber(String passSerialNumber) {
        this.passSerialNumber = passSerialNumber;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

    public String getVidan() {
        return vidan;
    }

    public void setVidan(String vidan) {
        this.vidan = vidan;
    }

    public String getAdressProp() {
        return adressProp;
    }

    public void setAdressProp(String adressProp) {
        this.adressProp = adressProp;
    }

    public String getAdressFact() {
        return adressFact;
    }

    public void setAdressFact(String adressFact) {
        this.adressFact = adressFact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
