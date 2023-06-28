package com.main.Controller;

import com.main.Model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

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

}
