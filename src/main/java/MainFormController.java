import CustomControlObject.VBoxCell;
import DAO.ActionService;
import DAO.CustomerService;
import DAO.EquipmentService;
import DAO.TreatyService;
import TableModels.CustomerTableModel;
import TableModels.EquipmentTableModel;
import TableModels.ToolTableModel;
import com.jfoenix.controls.*;
import com.sun.javafx.scene.control.SelectedCellsMap;
import hibernate.dao.ActionEntity;
import hibernate.dao.CustomerEntity;
import hibernate.dao.TreatyEntity;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import hibernate.dao.EquipmentEntity;
import javafx.scene.text.Text;

import java.util.List;

public class MainFormController {

    public JFXComboBox toolSelector,timeBox, comboBoxVidan;
    public StackPane stackPane;
    public JFXTabPane tabPane;
    //---- table Equipment ------
    public TableColumn tableEquipmentName,tableEquipmentSerialNumber,tableEquipmentPrice4, tableEquipmentPrice8,tableEquipmentPrice24,tableEquipmentDeposit, tableEquipmentStatus;
    public TableColumn tableSelectEquipmentName, tableSelectEquipmentRent, tableSelectEquipmentPrice, tableSelectEquipmentCommon;
    //---------------------------
    public TableColumn tableClientsFIO, tableClientsSerial, tableClientsNumber, tableClientsVidan, tableClientsAdress, tableClientsFactAdress, tableClientsPhone;
    public TableView tableEquipment, tableSelectEquipment, tableClients;
    public JFXTextField labelFIO, labelSerialPass, labelNumberPass, labelPropiska, labelFactAdress, labelPhone;
    //-----tab new Treaty-------------
    public JFXDatePicker startDate1,endDate1;
    public Label commonPriceLabelSelect;
    //--------------------------------
    //-----tab Treaty-----------------
    public TableView actionTable;
    public TableColumn actionName, actionPrice, actionPeriod,actionCommonPrice;
    public Label commonPriceLabelAction;
    //--------------------------------

    private ObservableList<ToolTableModel> tableSelectData = FXCollections.observableArrayList();
    private ObservableList<ToolTableModel> tableActionData = FXCollections.observableArrayList();
    private ObservableList<TreatyEntity> treatyListData = FXCollections.observableArrayList();
    private ObservableList<CustomerEntity> tableCustomerData = FXCollections.observableArrayList();
    private ObservableList<EquipmentEntity> tableEquipmentData = FXCollections.observableArrayList();

    //private ObservableList<EquipmentEntity> data = FXCollections.observableArrayList();
    private ObservableList<String> dataComboBox = FXCollections.observableArrayList();

    //------------DB Services--------------
    public CustomerService customerService;
    public EquipmentService equipmentService;
    public ActionService actionService;
    public TreatyService treatyService;
    //-------------------------------------


    @FXML
    private JFXListView<VBoxCell> listView;

    @FXML
    private void closeAction(ActionEvent evt){

    }

    @FXML
    public void initialize() {
        customerService = new CustomerService();
        equipmentService = new EquipmentService();
        actionService = new ActionService();
        treatyService = new TreatyService();

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
        tableSelectEquipmentPrice.setCellValueFactory(new PropertyValueFactory<ToolTableModel,Integer>("Price"));;
        tableSelectEquipmentCommon.setCellValueFactory(new PropertyValueFactory<ToolTableModel,Integer>("CommonPrice"));
        //--------------------------------------------

        //---------- Table: Action ---------
        actionName.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("Name"));
        actionPeriod.setCellValueFactory(new PropertyValueFactory<ToolTableModel,String>("Period"));;
        actionPrice.setCellValueFactory(new PropertyValueFactory<ToolTableModel,Integer>("Price"));;
        actionCommonPrice.setCellValueFactory(new PropertyValueFactory<ToolTableModel,Integer>("CommonPrice"));
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
                "28 суток","29 суток","30 суток","31 сутки"
        ));

        //-------------on change ListView--------------
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<VBoxCell>() {
            @Override
            public void changed(ObservableValue<? extends VBoxCell> observable, VBoxCell oldTreatyBox, VBoxCell newTreatyBox) {
                cleanActionTable();
                if ((listView.getItems() != null) && (newTreatyBox != null)){
                    List<ActionEntity> actionList = actionService.findAllByIDTreaty(newTreatyBox.getTreatyID());
                    for (ActionEntity actionEntity : actionList) {
                        ToolTableModel toolTableModel = new ToolTableModel();
                        toolTableModel.setName(equipmentService.findById(actionEntity.getIdEquipment()).getName());
                        toolTableModel.setPeriod(actionEntity.getRentTime());
                    /*if (toolTableModel.getPeriod().equals("4 часа")) {

                    }else if(toolTableModel.getPeriod().equals("8 часов")){

                    }else if(timeBox.getItems())*/
                        //toolTableModel.setPrice(equipmentService.findById(actionEntity.getIdEquipment()).);//rerite
                        toolTableModel.setCommonPrice(actionEntity.getCost());
                        tableActionData.add(toolTableModel);
                    }
                    actionTable.setItems(tableActionData);
                    System.out.println("Selected Treaty ID: " + newTreatyBox.getTreatyID());
                }
            }
        });
        //----------------------------------------------

        tableClients.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            System.out.println(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getFio());

        });

        tableClients.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        moveCustomerDataFromTableCustomerToEdits();
                    }
                }
            }
        });


        tableCustomerData = FXCollections.observableArrayList(customerService.findAll());
        tableClients.setItems(tableCustomerData);

        tableEquipmentData = FXCollections.observableArrayList(equipmentService.findAll());
        tableEquipment.setItems(tableEquipmentData);

        treatyListData = FXCollections.observableArrayList(treatyService.findAll());

        for (TreatyEntity treaty:treatyListData) {
            listView.getItems().add(new VBoxCell(treaty.getIdTreaty(),"Договор №"+treaty.getIdTreaty(),customerService.findById(treaty.getIdCustomer()).getFio(),Color.valueOf("#ff3b3b")));
        }

        toolSelector.setItems(FXCollections.observableArrayList(equipmentService.findAllNames()));
    }

    @FXML
    public void addEquipmentInTable(){
        ToolTableModel tableSelectItem = new ToolTableModel();
        //if () !!! Добавить проверку на пустые значения
        for (EquipmentEntity tool:tableEquipmentData) {
            if (tool.getName().equals(toolSelector.getSelectionModel().getSelectedItem().toString())){
                tableSelectItem.setName(tool.getName());
                tableSelectItem.setPeriod(timeBox.getSelectionModel().getSelectedItem().toString());
                if (timeBox.getSelectionModel().getSelectedIndex() == 0){
                    tableSelectItem.setPrice(tool.getPriceFor4());
                    tableSelectItem.setCommonPrice(tool.getPriceFor4());
                }else if (timeBox.getSelectionModel().getSelectedIndex() == 1){
                    tableSelectItem.setPrice(tool.getPriceFor8());
                    tableSelectItem.setCommonPrice(tool.getPriceFor8());
                }else if (timeBox.getSelectionModel().getSelectedIndex() >1 && timeBox.getSelectionModel().getSelectedIndex() <4){
                    tableSelectItem.setPrice(tool.getPriceFor24());
                    tableSelectItem.setCommonPrice(tool.getPriceFor24() * (timeBox.getSelectionModel().getSelectedIndex() - 1));
                }else if (timeBox.getSelectionModel().getSelectedIndex() >3 && timeBox.getSelectionModel().getSelectedIndex() <8){
                    tableSelectItem.setPrice((int)(tool.getPriceFor24()*0.8));
                    tableSelectItem.setCommonPrice((tableSelectItem.getPrice() * (timeBox.getSelectionModel().getSelectedIndex() - 1)));
                }else if (timeBox.getSelectionModel().getSelectedIndex() >7 && timeBox.getSelectionModel().getSelectedIndex() <15){
                    tableSelectItem.setPrice((int)(tool.getPriceFor24()*0.65));
                    tableSelectItem.setCommonPrice((tableSelectItem.getPrice() * (timeBox.getSelectionModel().getSelectedIndex() - 1)));
                }else if (timeBox.getSelectionModel().getSelectedIndex() >14){
                    tableSelectItem.setPrice((int)(tool.getPriceFor24()*0.55));
                    tableSelectItem.setCommonPrice((tableSelectItem.getPrice() * (timeBox.getSelectionModel().getSelectedIndex() - 1)));
                }
                break;
            }
        }
        tableSelectItem.setPrice(tableSelectItem.getPrice());
        tableSelectItem.setCommonPrice(tableSelectItem.getCommonPrice());
        tableSelectData.add(tableSelectItem);
        tableSelectEquipment.setItems(tableSelectData);
        updateCommonPriceLabelSelect();
    }

    public void addCustomerButtonAction(){
        //------------New Customer---------------------
        CustomerEntity customer = new CustomerEntity();

        customer.setFio(labelFIO.getText().trim());
        customer.setPassSerialNumber(labelSerialPass.getText().trim());
        customer.setPassNumber(labelNumberPass.getText().trim());
        customer.setVidan(comboBoxVidan.getSelectionModel().getSelectedItem().toString().trim());
        customer.setAdressProp(labelPropiska.getText().trim());
        customer.setAdressFact(labelFactAdress.getText().trim());
        customer.setPhone(labelPhone.getText().trim());
        //---------------------------------------------

        //-----------------New Treaty------------------
        TreatyEntity treatyEntity = new TreatyEntity();

        if (customerService.findByNameAndPass(customer.getFio(),customer.getPassSerialNumber(),customer.getPassNumber()) == null){
            addCustomer(customer);
            treatyEntity.setIdCustomer(customerService.getLastID());
        }else{
            treatyEntity.setIdCustomer(customerService.findByNameAndPass(customer.getFio(),customer.getPassSerialNumber(),customer.getPassNumber()).getIdCustomer());
        }

        treatyEntity.setStart(startDate1.getValue());
        treatyEntity.setStop(endDate1.getValue());
        treatyEntity.setCommonPrice(getCommonPriceSelect());
        treatyEntity.setStatus("Активен");

        treatyService.persist(treatyEntity);
        //---------------------------------------------

        //-----------------New Actions--------------
        for (ToolTableModel tableItem:tableSelectData) {
            ActionEntity actionEntity = new ActionEntity();
            actionEntity.setIdEquipment(equipmentService.findByName(tableItem.getName()).getIdEquipment());
            actionEntity.setIdTreaty(treatyService.getLastID());
            actionEntity.setRentTime(timeBox.getSelectionModel().getSelectedItem().toString());
            actionEntity.setCost((int) tableItem.getCommonPrice());

            actionService.persist(actionEntity);
        }
        //---------------------------------------------
        listViewReload(true);
        tabPane.getSelectionModel().select(0);//select tab "Договора"
        listView.getFocusModel().focus(0);
        listView.getSelectionModel().selectLast();
    }

    public void addCustomer(CustomerEntity customer) {
        customerService.persist(customer);
    }

    public int getCommonPriceSelect() {
        try {
            int sum = 0;
            for (ToolTableModel tableItem:tableSelectData) {
                sum += (int) tableItem.getCommonPrice();
            }
            return sum;
        }catch (NullPointerException e){
            return 0;
        }
    }

    public int getCommonPriceAction() {
        try {
            int sum = 0;
            for (ToolTableModel tableItem:tableActionData) {
                sum += (int) tableItem.getCommonPrice();
            }
            return sum;
        }catch (NullPointerException e){
            return 0;
        }
    }

    public void updateCommonPriceLabelSelect() {
        commonPriceLabelSelect.setText("Итого: "+getCommonPriceSelect()+" руб.");
    }

    public void updateCommonPriceLabelAction() {
        //commonPriceLabelAction.setText("Итого: "+" руб.");
    }

    public void getNameFromTable(){
        EquipmentTableModel equipmentTableModel = (EquipmentTableModel) tableEquipment.getSelectionModel().getSelectedItem();
        System.out.println(equipmentTableModel.getName());
    }

    public void moveCustomerDataFromTableCustomerToEdits(){
        try{
            labelFIO.setText(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getFio());
            labelSerialPass.setText(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getPassSerialNumber());
            labelNumberPass.setText(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getPassNumber());
            if (comboBoxVidan.getItems().contains(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getVidan())){
                //Тогда выбирать "Кто выдал" из этого же КомбоБокса
            }else {
                comboBoxVidan.setValue(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getVidan());
            }
            labelPropiska.setText(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getAdressProp());
            labelFactAdress.setText(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getAdressFact());
            labelPhone.setText(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getPhone());
            tabPane.getSelectionModel().select(3);//select tab "Новый договор"
            //System.out.println(((CustomerEntity)tableClients.getSelectionModel().getSelectedItem()).getFio());
        }catch (Exception ex){
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Ошибка (NullPointerException)"));
            content.setBody(new Text("Выберите клиента! \n" +
                    "Для выбора клиета щелкните два раза по нужной строке."));
            JFXDialog dialog = new JFXDialog(stackPane,content, JFXDialog.DialogTransition.CENTER);
            dialog.show();
        }
    }

    public void cleanSelectetTable(){
        tableSelectData.clear();
        tableSelectEquipment.setItems(tableSelectData);
        updateCommonPriceLabelSelect();
    }

    public void cleanActionTable(){
        tableActionData.clear();
        actionTable.setItems(tableActionData);
        updateCommonPriceLabelAction();
    }

    public void deleteItemFromSelectetTable(){
        ToolTableModel tool = (ToolTableModel) tableSelectEquipment.getSelectionModel().getSelectedItem();
        tableSelectData.remove(tool);
        tableSelectEquipment.setItems(tableSelectData);
        updateCommonPriceLabelSelect();
    }

    public void listViewReload(Boolean onlyActiv){
        treatyListData.clear();
        listView.getItems().clear();
        treatyListData = FXCollections.observableArrayList(treatyService.findAll());
        for (TreatyEntity treaty:treatyListData) {
            listView.getItems().add(new VBoxCell(treaty.getIdTreaty(),"Договор №"+treaty.getIdTreaty(),customerService.findById(treaty.getIdCustomer()).getFio(),Color.valueOf("#ff3b3b")));
        }
    }

    public void tableClientsReload(){
        tableCustomerData = FXCollections.observableArrayList(customerService.findAll());
        tableClients.setItems(tableCustomerData);
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
}
