/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.admin;

import cinema.CinemaController;
import cinema.Seat;
import cinema.Showtime;
import cinema.User;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Phijak
 */
public class ViewSeatController implements Initializable {
    private CinemaController cc;
    private TableView<Seat> tbSeat;
    private TableColumn<Seat, Integer> colSeatId;
    private TableColumn<Seat, String> colSeatName;
    private TableColumn<Seat, Double> colSeatPrice;
    private TableColumn<Seat, Boolean> colSeatStatus;
    private TableColumn<Seat, String> colSeatType;
    @FXML
    private Text txtMyMoney;
    @FXML
    private TableView<?> tbBooking;
    @FXML
    private MenuItem menuCancelBooking;
    @FXML
    private TableColumn<?, ?> colBookingID;
    @FXML
    private TableColumn<?, ?> colBookingCreated;
    @FXML
    private TableColumn<?, ?> colBookingUpdate;
    @FXML
    private TableColumn<?, ?> colBookingShowtime;
    @FXML
    private TableColumn<?, ?> colBookingPromotion;
    @FXML
    private TableColumn<?, ?> colBookingStatus;
    @FXML
    private TableColumn<?, ?> colBookingSeat;
    @FXML
    private TableColumn<?, ?> colBookingTotal;

    public ViewSeatController() {
        this.cc = cc.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<String> seatData = FXCollections.observableArrayList();
        
        ObservableList<Seat> seatList = FXCollections.observableArrayList();
        colSeatId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSeatName.setCellValueFactory(new PropertyValueFactory<>("seatName"));
        colSeatPrice.setCellValueFactory(new PropertyValueFactory<>("seatPrice"));
        colSeatStatus.setCellValueFactory(new PropertyValueFactory<>("seatStatus"));
        colSeatType.setCellValueFactory(new PropertyValueFactory<>("seatType"));
        
       seatList.clear(); 
        System.out.println(cc.getSelectShowtime());
       Showtime s = cc.getShowtime(cc.getSelectShowtime());
       List<Seat> seatShowtime = s.getSeatList();
       try{
           for (Seat seat : seatShowtime) {
            seatList.add(seat);
            //seatData.add(seat.toString());
           }
       } catch (Exception e) {
            System.out.println(e);
       }
       tbSeat.setItems(seatList);
       tbSeat.getSortOrder().add(colSeatName);

       //seatListview.getItems().setAll(seatData);
        
    }    

    @FXML
    private void handleRefreshBooking(ActionEvent event) {
    }

    @FXML
    private void handleCancelBooking(ActionEvent event) {
    }

    @FXML
    private void handleMouseClickBooking(MouseEvent event) {
    }
    
}
