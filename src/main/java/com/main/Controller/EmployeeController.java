package com.main.Controller;

import com.main.Model.Employee;
import com.main.View.Bill;
import com.main.View.CustomerManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeeController {
    @FXML
    private Button markAttendButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button customerButton;
    @FXML
    private Button billButton;
    private String username;

    public EmployeeController(String username){
        this.username = username;
    }

    public void markAttendOnAction() {
        Employee employee = new Employee();
        if(employee.checkAttend(username)){
            showAlert(Alert.AlertType.INFORMATION,"Thông báo","Điểm danh thành công");
            markAttendButton.setDisable(true);
        }else {
            showAlert(Alert.AlertType.ERROR,"Lỗi","Điểm danh không thành cong");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message ) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void createCustomerOnAction() throws Exception{
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.start(new Stage());
        Stage currentStage = (Stage) customerButton.getScene().getWindow();
        currentStage.close();
    }
    public void billListOnAction() throws Exception {
        Bill bill = new Bill(username);
        bill.start(new Stage());
        Stage currentStage = (Stage) billButton.getScene().getWindow();
        currentStage.close();
    }

}
