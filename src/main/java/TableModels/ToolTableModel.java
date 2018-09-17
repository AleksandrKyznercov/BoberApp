package TableModels;

public class ToolTableModel {

    private String Name;
    private String SerialNumber;
    private String Period;
    private double Price;
    private double CommonPrice;

    public ToolTableModel() {
        Name = null;
        Period = null;
        Price = 0;
        CommonPrice = 0;
    }

    public ToolTableModel(String name, String period, double price, double commonPrice) {
        Name = name;
        Period = period;
        Price = price;
        CommonPrice = commonPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getCommonPrice() {
        return CommonPrice;
    }

    public void setCommonPrice(double commonPrice) {
        CommonPrice = commonPrice;
    }
}
