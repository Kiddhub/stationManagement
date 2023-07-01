package com.main.Model;

import com.main.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bill {
    private int billId;
    private Booking booking;
    private Employee employee;

    public Bill(Booking booking) {
        this.booking = booking;
    }
    public Bill(){

    }

    public Bill(int billId, Booking booking, Employee employee) {
        this.billId = billId;
        this.booking = booking;
        this.employee = employee;
    }

    public Bill(Booking booking, Employee employee) {
        this.booking = booking;
        this.employee = employee;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public static ObservableList<Bill> getBillList() {
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        try{
            Connection connection = DbConnection.getConnection();
            String sql = "SELECT bo.bookingId, s.stationName, c.customerName, c.customerPhone, bo.bookingDate, bo.timeIn, bo.timeOut, bo.totalPrice, bo.status " +
                    "FROM bookings bo " +
                    "JOIN customers c ON bo.customerId = c.customerId " +
                    "JOIN stations s ON bo.stationId = s.stationId";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int bookingId = rs.getInt("bookingId");
                String stationName = rs.getString("stationName");
                String customerName = rs.getString("customerName");
                String customerPhone = rs.getString("customerPhone");
                LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
                LocalTime timeIn = rs.getTime("timeIn").toLocalTime();
                LocalTime timeOut = rs.getTime("timeOut").toLocalTime();
                double totalPrice = rs.getDouble("totalPrice");
                boolean status = rs.getBoolean("status");
                Customer customer = new Customer(customerName,customerPhone);
                Station station = new Station(stationName);
                Booking booking = new Booking(bookingId,customer,station,bookingDate,timeIn,timeOut,totalPrice,status);
                Bill bill = new Bill(booking);
                billList.add(bill);
            }
            statement.close();
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return billList;

    }

    public static boolean createBill(Bill bill){
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "INSERT INTO bills (bookingId, employeeId) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bill.booking.getBookingId());
            statement.setInt(2, bill.employee.getEmployeeId());
            statement.executeUpdate();

            // Đóng kết nối và statement
            statement.close();
            connection.close();
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
            // Hiển thị thông báo lỗi nếu xảy ra lỗi trong quá trình lưu hoá đơn
        }


    }

}
