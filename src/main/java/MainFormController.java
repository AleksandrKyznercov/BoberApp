import DAO.CustomerService;
import TableModels.CustomerTableModel;
import TableModels.EquipmentTableModel;
import TableModels.ToolTableModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import hibernate.dao.CustomerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

public class MainFormController {

    public JFXComboBox toolSelector,timeBox, comboBoxVidan;
    //---- table Equipment ------
    public TableColumn tableEquipmentName,tableEquipmentSerialNumber,tableEquipmentPrice4, tableEquipmentPrice8,tableEquipmentPrice24,tableEquipmentDeposit, tableEquipmentStatus;
    public TableColumn tableSelectEquipmentName, tableSelectEquipmentRent, tableSelectEquipmentPrice, tableSelectEquipmentCommon;
    //---------------------------
    public TableColumn tableClientsFIO, tableClientsSerial, tableClientsNumber, tableClientsVidan, tableClientsAdress, tableClientsFactAdress, tableClientsPhone;
    public TableView tableEquipment, tableSelectEquipment, tableClients;
    public JFXTextField labelFIO, labelSerialPass, labelNumberPass, labelPropiska, labelFactAdress, labelPhone;


    private ObservableList<ToolTableModel> tableData = FXCollections.observableArrayList();
    private ObservableList<CustomerTableModel> tableCustomerData = FXCollections.observableArrayList();
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
    private void closeAction(ActionEvent evt){

    }

    @FXML
    public void initialize() {
        //---------- Table: Equipment ------------
        tableEquipmentName.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,String>("name"));
        tableEquipmentSerialNumber.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,String>("serialNumber"));
        tableEquipmentPrice4.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("priceFor4"));
        tableEquipmentPrice8.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("priceFor8"));
        tableEquipmentPrice24.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("priceFor24"));
        tableEquipmentDeposit.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,Integer>("deposit"));
        tableEquipmentStatus.setCellValueFactory(new PropertyValueFactory<EquipmentTableModel,String>("status"));
        //-----------------------------------------

        //---------- Table: Select Equipment ---------
        tableSelectEquipmentName.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("Name"));
        tableSelectEquipmentRent.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("Period"));;
        tableSelectEquipmentPrice.setCellValueFactory(new PropertyValueFactory<ToolTableModel,Double>("Price"));;
        tableSelectEquipmentCommon.setCellValueFactory(new PropertyValueFactory<ToolTableModel,Double>("CommonPrice"));
        //--------------------------------------------

        //---------- Table: Clients ------------------
        tableClientsFIO.setCellValueFactory(new PropertyValueFactory<CustomerTableModel,String>("fio"));
        tableClientsSerial.setCellValueFactory(new PropertyValueFactory<CustomerTableModel,String>("passSerialNumber"));
        tableClientsNumber.setCellValueFactory(new PropertyValueFactory<CustomerTableModel,String>("passNumber"));
        tableClientsVidan.setCellValueFactory(new PropertyValueFactory<CustomerTableModel,String>("vidan"));
        tableClientsAdress.setCellValueFactory(new PropertyValueFactory<CustomerTableModel,String>("adressProp"));
        tableClientsFactAdress.setCellValueFactory(new PropertyValueFactory<CustomerTableModel,String>("adressFact"));
        tableClientsPhone.setCellValueFactory(new PropertyValueFactory<CustomerTableModel,String>("phone"));
        //--------------------------------------------

        timeBox.setItems(FXCollections.observableArrayList(
                "4 часа","8 часов","1 сутки","2 суток","3 суток","4 суток","5 суток","6 суток","7 суток",
                "8 суток","9 суток","10 суток","11 суток","12 суток","13 суток","14 суток","15 суток","16 суток","17 суток",
                "18 суток","19 суток","20 суток","21 сутоки","22 суток","23 суток","24 суток","25 суток","26 суток","27 суток",
                "28 суток","29 суток","30 суток","31 сутrи"
        ));
        //equipmentBox.setItems(dataComboBox);

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

            tableEquipmentData = FXCollections.observableArrayList(session.createCriteria(EquipmentEntity.class).addOrder(Order.asc("name")).list());
            tableCustomerData = FXCollections.observableArrayList(session.createCriteria(CustomerEntity.class).list());
            data = FXCollections.observableArrayList(session.createCriteria(EquipmentEntity.class).addOrder(Order.asc("name")).list());;

            tableClients.setItems(tableCustomerData);
            tableEquipment.setItems(tableEquipmentData);
            toolSelector.setItems(FXCollections.observableArrayList(session.createCriteria(EquipmentEntity.class).setProjection(Projections.property("name")).addOrder(Order.asc("name")).list()));

        session.close();
        //HibernateSessionFactory.shutdown();

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
        tableSelectEquipment.setItems(tableData);

        /*Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        EquipmentEntity equipmentEntity = new EquipmentEntity();

        equipmentEntity = session.load(EquipmentEntity.class,1);
        System.out.println(equipmentEntity.getName());
        session.close();
*/
    }

    public void addCustomerButtonAction(){
        CustomerEntity customer = new CustomerEntity();
        customer.setFio(labelFIO.getText());
        customer.setPassSerialNumber(labelSerialPass.getText());
        customer.setPassNumber(labelNumberPass.getText());
        customer.setVidan(comboBoxVidan.getSelectionModel().getSelectedItem().toString());
        customer.setAdressProp(labelPropiska.getText());
        customer.setAdressFact(labelFactAdress.getText());
        customer.setPhone(labelPhone.getText());
        addCustomer(customer);
    }

    public void addCustomer(CustomerEntity customer) {
        CustomerService customerService = new CustomerService();
        customerService.persist(customer);
    }

    public void getNameFromTable(){
        EquipmentTableModel equipmentTableModel = (EquipmentTableModel) tableEquipment.getSelectionModel().getSelectedItem();
        System.out.println(equipmentTableModel.getName());
    }

    public void cleanSelectetTable(){
        tableData.clear();
        tableSelectEquipment.setItems(tableData);
    }

    public void deleteItemFromSelectetTable(){
        ToolTableModel tool = (ToolTableModel) tableSelectEquipment.getSelectionModel().getSelectedItem();
        tableData.remove(tool);
        tableSelectEquipment.setItems(tableData);
    }
}
