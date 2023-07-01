package com.main.Controller.Admin;

import com.main.Model.Bill;
import com.main.View.Admin.AdminInterface;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class BillManagementController implements Initializable {
    @FXML
    private TableColumn<Bill, Integer> billId;

    @FXML
    private TableColumn<Bill, LocalDate> bookingDate;

    @FXML
    private TableColumn<Bill, String> customerName;

    @FXML
    private TableColumn<Bill, String> customerPhone;

    @FXML
    private TableColumn<Bill, String> username;

    @FXML
    private TableColumn<Bill, String> stationName;

    @FXML
    private TableView<Bill> table;

    @FXML
    private TableColumn<Bill, LocalTime> timeIn;

    @FXML
    private TableColumn<Bill, LocalTime> timeOut;

    @FXML
    private TableColumn<Bill, Double> totalPrice;

    @FXML
    private Button cancelButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        billId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        customerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBooking().getCustomer().getCustomerName()));
        customerPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBooking().getCustomer().getCustomerPhone()));
        bookingDate.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBooking().getBookingDate()));
        timeIn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBooking().getTimeIn()));
        timeOut.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBooking().getTimeOut()));
        username.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployee().getUsername()));
        stationName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBooking().getStation().getStationName()));
        totalPrice.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBooking().getTotalPrice()));
        ObservableList<Bill> billList = Bill.getBillFromData();
        table.setItems(FXCollections.observableList(billList));
    }

    public void cancelOnAction() throws Exception{
        AdminInterface adminInterface = new AdminInterface();
        adminInterface.start(new Stage());
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }
}
