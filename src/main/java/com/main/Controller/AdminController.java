package com.main.Controller;

import com.main.View.EmployeeManagement;
import com.main.View.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {
    @FXML
    private Button createEmployeeButton;
    @FXML
    private Button stationButton;
    @FXML
    private Button logoutButton;

    public void logoutButtonOnAction() throws Exception {
        Login login  = new Login();
        login.start(new Stage());
        Stage logoutStage = (Stage) logoutButton.getScene().getWindow();
        logoutStage.close();
    }

    public void createEmployeeOnAction() throws Exception{
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.start(new Stage());
        Stage createEmployee = (Stage) createEmployeeButton.getScene().getWindow();
        createEmployee.close();
    }
}
