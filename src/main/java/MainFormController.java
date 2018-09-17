import TableModels.EquipmentTableModel;
import TableModels.ToolTableModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
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
import Models.Tool;
import hibernate.dao.EquipmentEntity;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

public class MainFormController {

    public JFXComboBox toolSelector,timeBox;
    //---- table Equipment ------
    public TableColumn tableEquipmentName,tableEquipmentSerialNumber,tableEquipmentPrice4, tableEquipmentPrice8,tableEquipmentPrice24,tableEquipmentDeposit, tableEquipmentStatus;
    //---------------------------
    public TreeTableColumn tableClientsFIO, tableClientsSerial, tableClientsNumber, tableClientsVidan,  tableClientsAdress, tableClientsFactAdress, tableClientsPhone;
    public TableView tableEquipment;

    private ObservableList<ToolTableModel> tableData = FXCollections.observableArrayList();
    private ObservableList<EquipmentTableModel> tableEquipmentData = FXCollections.observableArrayList();
    private ObservableList<EquipmentEntity> data = FXCollections.observableArrayList();
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
        tableEquipmentName.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,String>("name"));
        tableEquipmentSerialNumber.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,String>("serialNumber"));
        tableEquipmentPrice4.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("priceFor4"));
        tableEquipmentPrice8.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("priceFor8"));
        tableEquipmentPrice24.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("priceFor24"));
        tableEquipmentDeposit.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("deposit"));
        tableEquipmentStatus.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,String>("status"));
        //tableTreaty.setItems(tableData);

        timeBox.setItems(FXCollections.observableArrayList(
                "4 часа","8 часов","1 сутки","2 суток","3 суток","4 суток","5 суток","6 суток","7 суток",
                "8 суток","9 суток","10 суток","11 суток","12 суток","13 суток","14 суток","15 суток","16 суток","17 суток",
                "18 суток","19 суток","20 суток","21 сутоки","22 суток","23 суток","24 суток","25 суток","26 суток","27 суток",
                "28 суток","29 суток","30 суток","31 сутrи"
        ));
        //equipmentBox.setItems(dataComboBox);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        EquipmentEntity equipmentEntity = new EquipmentEntity();

        equipmentEntity = session.load(EquipmentEntity.class,1);
        tableEquipmentData = FXCollections.observableArrayList(session.createCriteria(EquipmentEntity.class).addOrder(Order.asc("name")).list());
        data = FXCollections.observableArrayList(session.createCriteria(EquipmentEntity.class).addOrder(Order.asc("name")).list());
        //tableEquipmentData.add(new EquipmentTableModel(equipmentEntity));
        //System.out.println(equipmentEntity.getName());

        tableEquipment.setItems(tableEquipmentData);
        toolSelector.setItems(FXCollections.observableArrayList(session.createCriteria(EquipmentEntity.class).setProjection(Projections.property("name")).addOrder(Order.asc("name")).list()));
        session.close();

        listView.getItems().add(new VBoxCell("Договор №1","Договор №2",Color.valueOf("#ff3b3b")));
    }

    @FXML
    public void addEquipmentInTable(){
        ToolTableModel tableItem = new ToolTableModel();
        //if () !!! Добавить проверку на пустые значения
        for (EquipmentEntity tool:data) {
            if (tool.getName().equals(toolSelector.getSelectionModel().getSelectedItem().toString())){
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
                break;
            }
        }
        tableItem.setPrice(Math.round(tableItem.getPrice()));
        tableItem.setCommonPrice(Math.round(tableItem.getCommonPrice()));
        tableData.add(tableItem);

        /*Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        EquipmentEntity equipmentEntity = new EquipmentEntity();

        equipmentEntity = session.load(EquipmentEntity.class,1);
        System.out.println(equipmentEntity.getName());
        session.close();
*/
    }

    public void getNameFromTable(){
        EquipmentTableModel equipmentTableModel = (EquipmentTableModel) tableEquipment.getSelectionModel().getSelectedItem();
        System.out.println(equipmentTableModel.getName());
    }
}
