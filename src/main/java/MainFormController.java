import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import org.hibernate.Session;
import DAO.HibernateSessionFactory;
import Models.Tool;
import TableModels.ToolTableModel;
import hibernate.dao.EquipmentEntity;

public class MainFormController {

    public JFXComboBox timeBox;
    public TableColumn table1Name,table1Period,table1Price,table1Common;
    public TreeTableColumn tableClientsFIO, tableClientsSerial, tableClientsNumber, tableClientsVidan,  tableClientsAdress, tableClientsFactAdress, tableClientsPhone;
    public TableView tableTreaty;

    private ObservableList<ToolTableModel> tableData = FXCollections.observableArrayList();
    private ObservableList<Tool> data = FXCollections.observableArrayList();
    private ObservableList<String> dataComboBox = FXCollections.observableArrayList();

    public static class VBoxCell extends VBox {
        Label label1 = new Label();
        Label label2 = new Label();
        Circle circle = new Circle();

        VBoxCell(String labelText, String labelText2, Color circleColor) {
            super();

            label1.setText(labelText);
            label2.setText(labelText2);
            label1.setFont(Font.font(14));
            label2.setFont(Font.font(8));
            label1.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label1, Priority.ALWAYS);
            HBox.setHgrow(label2, Priority.NEVER);
            HBox.setMargin(label2, new Insets(0,0,0,30));
            circle.setFill(circleColor);
            circle.setRadius(10);
            HBox hBox = new HBox();
            hBox.getChildren().addAll(label1, circle);
            this.getChildren().addAll(hBox, label2);
        }
    }

    @FXML
    private JFXListView<VBoxCell> listView;

    @FXML
    public void initialize() {
        table1Name.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("name"));
        table1Period.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("period"));
        table1Price.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("price"));
        table1Common.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("commonPrice"));
        tableTreaty.setItems(tableData);

        timeBox.setItems(FXCollections.observableArrayList(
                "4 часа","8 часов","1 сутки","2 суток","3 суток","4 суток","5 суток","6 суток","7 суток",
                "8 суток","9 суток","10 суток","11 суток","12 суток","13 суток","14 суток","15 суток","16 суток","17 суток",
                "18 суток","19 суток","20 суток","21 сутоки","22 суток","23 суток","24 суток","25 суток","26 суток","27 суток",
                "28 суток","29 суток","30 суток","31 сутоки"
        ));
        //equipmentBox.setItems(dataComboBox);

        listView.getItems().add(new VBoxCell("Договор №1","Договор №2",Color.valueOf("#ff3b3b")));
    }

    @FXML
    public void addEquipmentInTable(){
        /*ToolTableModel tableItem = new ToolTableModel();
        //if () !!! Добавить проверку на пустые значения
        for (Tool tool:data) {
            if (tool.getName().equals(equipmentBox.getSelectionModel().getSelectedItem().toString())){
                tableItem.setName(tool.getName());
                tableItem.setPeriod(timeBox.getSelectionModel().getSelectedItem().toString());
                if (timeBox.getSelectionModel().getSelectedIndex() == 0){
                    tableItem.setPrice(tool.getPriceFor4());
                    tableItem.setCommonPrice(tool.getPriceFor4());
                }else if (timeBox.getSelectionModel().getSelectedIndex() == 1){
                    tableItem.setPrice(tool.getPriceFor8());
                    tableItem.setCommonPrice(tool.getPriceFor8());
                }else if (timeBox.getSelectionModel().getSelectedIndex() >1 && timeBox.getSelectionModel().getSelectedIndex() <4){
                    tableItem.setPrice(tool.getPriceFor24());
                    tableItem.setCommonPrice(tool.getPriceFor24() * (timeBox.getSelectionModel().getSelectedIndex() - 1));
                }else if (timeBox.getSelectionModel().getSelectedIndex() >3 && timeBox.getSelectionModel().getSelectedIndex() <8){
                    tableItem.setPrice(tool.getPriceFor24()*0.8);
                    tableItem.setCommonPrice(tableItem.getPrice() * (timeBox.getSelectionModel().getSelectedIndex() - 1));
                }else if (timeBox.getSelectionModel().getSelectedIndex() >7 && timeBox.getSelectionModel().getSelectedIndex() <15){
                    tableItem.setPrice(tool.getPriceFor24()*0.65);
                    tableItem.setCommonPrice(tableItem.getPrice() * (timeBox.getSelectionModel().getSelectedIndex() - 1));
                }else if (timeBox.getSelectionModel().getSelectedIndex() >14){
                    tableItem.setPrice(tool.getPriceFor24()*0.55);
                    tableItem.setCommonPrice(tableItem.getPrice() * (timeBox.getSelectionModel().getSelectedIndex() - 1));
                }
            }
        }
        tableItem.setPrice(Math.round(tableItem.getPrice()));
        tableItem.setCommonPrice(Math.round(tableItem.getCommonPrice()));
        tableData.add(tableItem);*/
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        EquipmentEntity equipmentEntity = new EquipmentEntity();

        equipmentEntity = session.load(EquipmentEntity.class,1);

        session.close();
        System.out.println(equipmentEntity.getName());
    }
}
