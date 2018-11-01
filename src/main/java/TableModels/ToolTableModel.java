package TableModels;

public class ToolTableModel {

    private String Name;
    private String SerialNumber;
    private String Period;
    private Integer Price;
    private Integer CommonPrice;

    public ToolTableModel() {
        Name = null;
        Period = null;
        Price = 0;
        CommonPrice = 0;
    }

    public ToolTableModel(String name, String period, Integer price, Integer commonPrice) {
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

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getCommonPrice() {
        return CommonPrice;
    }

    public void setCommonPrice(Integer commonPrice) {
        CommonPrice = commonPrice;
    }
}
