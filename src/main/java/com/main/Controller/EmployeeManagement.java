package com.main.Controller;

import com.main.DbConnection;
import com.main.Model.Employee;
import com.main.View.AdminInterface;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EmployeeManagement implements Initializable {
    @FXML
    private TextField employeeNameField;
    @FXML
    private TextField employeeAddressField;
    @FXML
    private TextField employeeIdCardField;
    @FXML
    private TextField employeeUsernameField;
    @FXML
    private PasswordField employeePasswordField;
    @FXML
    private TextField employeePhoneField;
    @FXML
    private Button createEmployeeButton;
    @FXML
    private Button updateEmployeeButton;
    @FXML
    private Button deleteEmployeeButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button searchEmployeeButton;
    @FXML
    private TableView<Employee> table;
    @FXML
    private TableColumn<Employee, String> dob;
    @FXML
    private TableColumn<Employee, String> address;
    @FXML
    private TableColumn<Employee, Integer> id;
    @FXML
    private TableColumn<Employee, String> idCard;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, Double> salary;
    @FXML
    private TableColumn<Employee,Integer>hours_worked;
    @FXML
    private TableColumn<Employee,String>username;
    @FXML
    private TableColumn<Employee,String>password;
    @FXML
    private DatePicker employeeDobField;
    @FXML
    private TextField employeeIdField;


    public void createEmployeeOnAction() {
        String name = employeeNameField.getText();
        LocalDate dob = employeeDobField.getValue();
        String idCard = employeeIdCardField.getText();
        String username = employeeUsernameField.getText();
        String password = employeePasswordField.getText();
        String address = employeeAddressField.getText();
        String phone = employeePhoneField.getText();
        String formattedDob = dob.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (name.isEmpty() || formattedDob.isEmpty() || idCard.isEmpty() || username.isEmpty() || password.isEmpty() || address.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Phải điền đầy đủ thông tin");
        }//thêm ngoại lệ kiểm tra username trùng nhau
        Employee employee = new Employee(name, address, idCard, phone, username, password, 2, formattedDob);
        if (Employee.addEmployeeToDatabase(employee)){
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng ký thành công");
            employeeNameField.setText("");
            employeeAddressField.setText("");
            employeeIdCardField.setText("");
            employeeDobField.setValue(null);
            employeePhoneField.setText("");
            employeeUsernameField.setText("");
            employeePasswordField.setText("");
            employeeUsernameField.setText("");
            ObservableList<Employee> employeeLists = Employee.getEmployeeFromData();
            table.setItems(employeeLists);
        }else {
            showAlert(Alert.AlertType.ERROR, "Error", "Lỗi");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("employeeId"));
        dob.setCellValueFactory(new PropertyValueFactory<Employee,String>("dob"));
        address.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeAddress"));
        name.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeName"));
        idCard.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeIdCard"));
        hours_worked.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("hours_worked"));
        salary.setCellValueFactory(new PropertyValueFactory<Employee,Double>("salary"));
        username.setCellValueFactory(new PropertyValueFactory<Employee,String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<Employee,String>("password"));
        ObservableList<Employee> employeeLists = Employee.getEmployeeFromData();
        table.setItems(employeeLists);
    }

    public void searchEmployeeOnAction(){
        String id = employeeIdField.getText();
        if(id.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Phải điền thông tin");
        }else {
            Employee employee = Employee.searchEmployee(id);
            if(employee == null){
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không tìm thấy thông tin Employee");
            }else {
                employeeNameField.setText(employee.getEmployeeName());
                employeeAddressField.setText(employee.getEmployeeAddress());
                employeeIdCardField.setText(employee.getEmployeeIdCard());
                employeeDobField.setValue(LocalDate.parse(employee.getDob()));
                employeePhoneField.setText(employee.getEmployeePhone());
                employeeUsernameField.setText(employee.getUsername());
                employeePasswordField.setText(employee.getPassword());
                employeeUsernameField.setEditable(false);
            }
        }
    }
    public void updateEmployeeOnAction(){
        String id = employeeIdField.getText();
        String name = employeeNameField.getText();
        String address = employeeAddressField.getText();
        String idCard = employeeIdCardField.getText();
        LocalDate dob = employeeDobField.getValue();
        String phone = employeePhoneField.getText();
        String password = employeePasswordField.getText();
        String formattedDob = dob.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        boolean isUpdated = Employee.updateEmployee(id,name,idCard,address,formattedDob,phone,password);
        if(isUpdated){
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Thông tin  được cập nhật ");
            ObservableList<Employee> employeeLists = Employee.getEmployeeFromData();
            table.setItems(employeeLists);
        }else {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật thông tin");
        }
    }
    public void deleteEmployeeOnAction(){
        String id = employeeIdField.getText();
        if(id.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Nhập ID nhân viên");
        }else {
            boolean isDeleted = Employee.deleteEmployee(id);
            if(isDeleted){
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Xoá thành công ");
                employeeNameField.setText("");
                employeeAddressField.setText("");
                employeeIdCardField.setText("");
                employeeDobField.setValue(null);
                employeePhoneField.setText("");
                employeeUsernameField.setText("");
                employeePasswordField.setText("");
                employeeUsernameField.setText("");
                ObservableList<Employee> employeeLists = Employee.getEmployeeFromData();
                table.setItems(employeeLists);
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
