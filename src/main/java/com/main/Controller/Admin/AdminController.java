package com.main.Controller.Admin;

import com.main.View.Admin.BillManagement;
import com.main.View.Employee.CustomerManagement;
import com.main.View.Admin.EmployeeManagement;
import com.main.View.Login;
import com.main.View.Admin.StationManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {
    @FXML
    private Button employeeButton;
    @FXML
    private Button stationButton;
    @FXML
    private Button billButton;
    @FXML
    private Button customerButton;
    @FXML
    private Button logOutButton;


    public void employeeButtonOnAction() throws Exception {
        com.main.View.Admin.EmployeeManagement employeeManagement = new com.main.View.Admin.EmployeeManagement();
        employeeManagement.start(new Stage());
        Stage currentStage = (Stage) employeeButton.getScene().getWindow();
        currentStage.close();
    }
    public void stationButtonOnAction() throws Exception {
        com.main.View.Admin.StationManagement stationManagement = new com.main.View.Admin.StationManagement();
        stationManagement.start(new Stage());
        Stage currentStage = (Stage) stationButton.getScene().getWindow();
        currentStage.close();
    }
    public void billButtonOnAction() throws Exception {
        BillManagement billManagement = new BillManagement();
        billManagement.start(new Stage());
        Stage currentStage = (Stage) billButton.getScene().getWindow();
        currentStage.close();
    }
    public void customerButtonOnAction() throws Exception{
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.start(new Stage());
        Stage currentStage = (Stage) customerButton.getScene().getWindow();
        currentStage.close();
    }
    public void logOutOnAction() throws Exception {
        Login login = new Login();
        login.start(new Stage());
        Stage currentStage = (Stage) logOutButton.getScene().getWindow();
        currentStage.close();
    }
}

