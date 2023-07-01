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
    private Button createEmployeeButton;
    @FXML
    private Button stationButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button billButton;
    @FXML
    private Button customerButton;

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

    public void stationManagementOnAction()throws Exception{
        StationManagement stationManagement = new StationManagement();
        stationManagement.start(new Stage());
        Stage currentStage = (Stage) stationButton.getScene().getWindow();
        currentStage.close();
    }
    public void billManagementOnAction() throws Exception{
        BillManagement billManagement = new BillManagement();
        billManagement.start(new Stage());
        Stage currentStage = (Stage) billButton.getScene().getWindow();
        currentStage.close();
    }
    public void customerManagementOnAction() throws Exception{
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.start(new Stage());
        Stage currentStage = (Stage) customerButton.getScene().getWindow();
        currentStage.close();
    }
}
