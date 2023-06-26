package com.main.Controller;

import javafx.fxml.FXML;
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
        System.out.println(username);
    }
}
