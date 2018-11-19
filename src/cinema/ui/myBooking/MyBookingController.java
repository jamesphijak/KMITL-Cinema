/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.myBooking;

import cinema.Booking;
import cinema.CinemaController;
import cinema.Promotion;
import cinema.UserController;
import cinema.ui.AlertMaker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MyBookingController implements Initializable {
    private UserController uc;
    private CinemaController cc;
    @FXML
    private Text txtMyMoney;
    @FXML
    private MenuItem menuCancelBooking;
    @FXML
    private TableView<Booking> tbBooking;
    @FXML
    private TableColumn<Booking, Integer> colBookingID;
    @FXML
    private TableColumn<Booking, String> colBookingCreated;
    @FXML
    private TableColumn<Booking, String> colBookingUpdate;
    @FXML
    private TableColumn<Booking, String> colBookingShowtime;
    @FXML
    private TableColumn<Booking, Promotion> colBookingPromotion;
    @FXML
    private TableColumn<Booking, Boolean> colBookingStatus;
    @FXML
    private TableColumn<Booking, String> colBookingSeat;
    @FXML
    private TableColumn<Booking, Double> colBookingTotal;

    public MyBookingController() {
        this.uc = uc.getInstance();
        this.cc = cc.getInstance();
    }
    // Booking List ====================================================================
    ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    List<Booking> bookingListArray = new ArrayList<Booking>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initBookingCol();
        loadBookingData();
        txtMyMoney.setText(String.valueOf(uc.getUser(uc.getLoginUser().getId()).getMoney()));
    }    
    
    public void initBookingCol(){
        colBookingID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookingCreated.setCellValueFactory(new PropertyValueFactory<>("BookingCreateDatetime"));
        colBookingUpdate.setCellValueFactory(new PropertyValueFactory<>("BookingUpdateDatetime"));
        colBookingShowtime.setCellValueFactory(new PropertyValueFactory<>("ShowtimeDetail"));
        colBookingPromotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        colBookingStatus.setCellValueFactory(new PropertyValueFactory<>("isCancel"));
        colBookingSeat.setCellValueFactory(new PropertyValueFactory<>("BookedSeatString"));
        colBookingTotal.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
    }   
    public void loadBookingData(){
        bookingList.clear();
        bookingListArray = cc.getMyBookingList(uc.getLoginUser().getId());
        try {
            for (Booking booking : bookingListArray) {
                bookingList.add(booking);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tbBooking.setItems(bookingList);
    }

    @FXML
    private void handleRefreshBooking(ActionEvent event) {
        loadBookingData();
        txtMyMoney.setText(String.valueOf(uc.getUser(uc.getLoginUser().getId()).getMoney()));
    }

    @FXML
    private void handleCancelBooking(ActionEvent event) {
        Booking selectedBookingDelete = tbBooking.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        if(selectedBookingDelete == null){
            AlertMaker.showErrorMessage("No movie selected", "Please select a movie for delete.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel booking");
        alert.setContentText("Are you sure want to cancel this booking "+ selectedBookingDelete.getId()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK){
            cc.cancleBooking(selectedBookingDelete.getId());
            loadBookingData();
            txtMyMoney.setText(String.valueOf(uc.getUser(uc.getLoginUser().getId()).getMoney()));
        }
    }

    @FXML
    private void handleMouseClickBooking(MouseEvent event) {
    }

    @FXML
    private void handleBackMovie(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showmovie/showmovie.fxml"));
        Scene parentScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(parentScene);
        window.show();
    }
    
}
