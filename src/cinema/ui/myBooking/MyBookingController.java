/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.myBooking;

import cinema.Booking;
import cinema.CinemaController;
import cinema.UserController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Phijak
 */
public class MyBookingController implements Initializable {
    private UserController uc;
    private CinemaController cc;

 
    public MyBookingController() {
        this.uc = uc.getInstance();
        this.cc = cc.getInstance();
    }
    // Booking List ====================================================================
    ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    List<Booking> bookingListArray = new ArrayList<Booking>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
//    public void initBookingCol(){
//        colBookingID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colBookingCreated.setCellValueFactory(new PropertyValueFactory<>("BookingCreateDatetime"));
//        colBookingUpdate.setCellValueFactory(new PropertyValueFactory<>("BookingUpdateDatetime"));
//        colBookingShowtime.setCellValueFactory(new PropertyValueFactory<>("ShowtimeDetail"));
//        colBookingUser.setCellValueFactory(new PropertyValueFactory<>("UserDetail"));
//        colBookingPromotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
//        colBookingStatus.setCellValueFactory(new PropertyValueFactory<>("isCancel"));
//        colBookingSeat.setCellValueFactory(new PropertyValueFactory<>("BookedSeatString"));
//        colBookingTotal.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
//    }   
//    public void loadBookingData(){
//        bookingList.clear();
//        bookingListArray = cc.getBookingList();
//        try {
//            for (Booking booking : bookingListArray) {
//                bookingList.add(booking);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        
//        tbBooking.setItems(bookingList);
//    }
    
}
