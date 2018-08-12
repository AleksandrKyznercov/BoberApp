package Models;

public class Tool {
    private String name;
    private Double cost;
    private String serialNumber;
    private double priceFor4;
    private double priceFor8;
    private double priceFor24;
    private double pledge;
    private double commonPrice;

    public Tool() {
    }

    public Tool(String name, Double cost, String serialNumber, double priceFor4, double priceFor8, double priceFor24, double commonPrice) {
        this.name = name;
        this.cost = cost;
        this.serialNumber = serialNumber;
        this.priceFor4 = priceFor4;
        this.priceFor8 = priceFor8;
        this.priceFor24 = priceFor24;
        this.commonPrice = commonPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPriceFor4() {
        return priceFor4;
    }

    public void setPriceFor4(double priceFor4) {
        this.priceFor4 = priceFor4;
    }

    public double getPriceFor8() {
        return priceFor8;
    }

    public void setPriceFor8(double priceFor8) {
        this.priceFor8 = priceFor8;
    }

    public double getPriceFor24() {
        return priceFor24;
    }

    public void setPriceFor24(double priceFor24) {
        this.priceFor24 = priceFor24;
    }

    public double getPledge() {
        return pledge;
    }

    public void setPledge(double pledge) {
        this.pledge = pledge;
    }

    public double getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(double commonPrice) {
        this.commonPrice = commonPrice;
    }
}
