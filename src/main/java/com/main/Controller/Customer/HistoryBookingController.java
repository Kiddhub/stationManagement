package com.main.Controller.Customer;

import com.main.Model.Booking;
import com.main.Model.Customer;
import com.main.Model.Station;
import com.main.View.Customer.BillInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class HistoryBookingController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<Booking> table;
    @FXML
    private TableColumn<Booking, String> date;

    @FXML
    private TableColumn<Booking, Integer> id;

    @FXML
    private TableColumn<Booking,String> stationName;

    @FXML
    private TableColumn<Booking,Boolean> status;

    @FXML
    private TableColumn<Booking, LocalTime> timeIn;

    @FXML
    private TableColumn<Booking, LocalTime> timeOut;

    @FXML
    private TableColumn<Booking, Double> totalPrice;

    @FXML
    private Button exportButton;
    @FXML
    private TextField bookingId;
    private Customer customer;
    public HistoryBookingController(Customer customer){
        this.customer = customer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println(customer.getCustomerId());
        id.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        date.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        stationName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStation().getStationName()));
        timeIn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        timeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<Booking> bookingList = Booking.getBookingList(customer.getCustomerId());
        table.setItems(FXCollections.observableList(bookingList));
//        exportButton.setOnAction(event -> exportBillOnAction());
    }
    public void exportBillOnAction() throws Exception{
        int searchBookingId = Integer.parseInt(bookingId.getText());
        Booking foundBooking = null;
        for (Booking booking : Booking.getBookingList(customer.getCustomerId())){
            if(booking.getBookingId() == searchBookingId){
                foundBooking = booking;
                break;
            }
        }
        if(foundBooking != null){
            if(foundBooking.isStatus()){
                BillInterface billInterface = new BillInterface(searchBookingId);
                billInterface.start(new Stage());
                showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Booking có ID " + searchBookingId + " đã được xác nhận");
            }else {
                showAlert(Alert.AlertType.ERROR, "Thông báo", "Hoá đơn chưa xác nhận");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Thông báo", "Không tìm thấy ID");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
