package com.main.Controller;

import com.main.DbConnection;
import com.main.Model.Employee;
import com.main.Model.Station;
import com.main.View.AdminInterface;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StationManagement implements Initializable {
    @FXML
    private TextField stationNameField;
    @FXML
    private TextField stationPriceField;
    @FXML
    private Button searchStationButton;
    @FXML
    private Button createStationButton;
    @FXML
    private Button deleteStationButton;
    @FXML
    private Button updateStationButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField stationIdField;
    @FXML
    private TableView<Station>table;
    @FXML
    private TableColumn<Station, Integer> id;
    @FXML
    private TableColumn<Station, String> name;
    @FXML
    private TableColumn<Station, Double> price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Station,Integer>("stationId"));
        name.setCellValueFactory(new PropertyValueFactory<Station,String>("stationName"));
        price.setCellValueFactory(new PropertyValueFactory<Station,Double>("stationPrice"));
        ObservableList<Station> stationLists = Station.getStationFromData();
        table.setItems(stationLists);
    }
    public void createStationOnAction(){
        String name = stationNameField.getText();
        Double price = Double.valueOf(stationPriceField.getText());
        if(name.isEmpty()||price == null){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Phải điền đầy đủ thông tin");
        }
        Station station = new Station(name,price);
        if(Station.addStationToData(station)){
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng ký thành công");
            ObservableList<Station> stationLists = Station.getStationFromData();
            table.setItems(stationLists);
            stationNameField.setText("");
            stationPriceField.setText("");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void searchStationOnAction(){
        String id = stationIdField.getText();
        if(id.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Phải điền thông tin");
        }else {
            Station station = Station.searchStation(id);
            if(station == null){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không tìm thấy thông tin Employee");
            }else {
                stationNameField.setText(station.getStationName());
                stationPriceField.setText(String.valueOf(station.getStationPrice()));
                stationIdField.setText(String.valueOf(station.getStationId()));
            }
        }
    }
    public void updateStationOnAction(){
        String id = stationIdField.getText();
        String name = stationNameField.getText();
        String price = stationPriceField.getText();
        boolean isUpdated = Station.updateStation(id,name,price);
        if(id.isEmpty()||name.isEmpty()||price.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Phải điền đầy đủ thông tin");
        }else {
            if(isUpdated){
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thông tin  được cập nhật ");
                ObservableList<Station> stationList = Station.getStationFromData();
                table.setItems(stationList);
            }else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật thông tin");
            }
        }

    }
    public void deleteStationOnAction(){
        String id = stationIdField.getText();
        if(id.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Nhập ID nhân viên");
        }else {
            boolean isDeleted = Station.deleteStation(id);
            if(isDeleted){
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Xoá thành công ");
                stationNameField.setText("");
                stationPriceField.setText("");
                stationIdField.setText("");
                ObservableList<Station> stationList = Station.getStationFromData();
                table.setItems(stationList);
            }else {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Xoá không thành công");
            }
        }
    }
    public void cancelButtonOnAction() throws Exception{
        AdminInterface adminInterface = new AdminInterface();
        adminInterface.start(new Stage());
        Stage cancel = (Stage) cancelButton.getScene().getWindow();
        cancel.close();
    }
}
