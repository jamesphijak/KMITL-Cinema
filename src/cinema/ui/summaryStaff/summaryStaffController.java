/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.summaryStaff;

import cinema.Booking;
import cinema.CinemaController;
import cinema.Movie;
import cinema.Promotion;
import cinema.PromotionController;
import cinema.Seat;
import cinema.Showtime;
import cinema.User;
import cinema.UserController;
import cinema.ui.AlertMaker;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BEAMCONAN
 */
public class summaryStaffController implements Initializable {

    @FXML
    private Text nameEng;
    @FXML
    private Text theatre;
    @FXML
    private Text date;
    @FXML
    private Text typeSeat;
    @FXML
    private Text amountSeat;
    @FXML
    private Text costPerSeat;
    @FXML
    private Text cost;
    @FXML
    private Text sumcost;
    @FXML
    private Text showtime;
    @FXML
    private StackPane rootPane;

    User user;
    Booking booking;
    Movie movie;
    
    CinemaController cc;
    UserController uc;
    User loginUser;
    Promotion userSelectPromotion = null;
    
    public summaryStaffController() {
        this.uc = uc.getInstance();
        this.cc = cc.getInstance();
    }
    
    Double totalPrice;
    Showtime st;
    List<Seat> seatList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(uc.getIsLogin()){
            loginUser = uc.getUser(uc.getLoginUser().getId()); // update data
            //System.out.println(loginUser.getUsername());
        }else{
            System.err.println("Please login");
        }
        
        st = cc.getShowtime(cc.getSelectShowtime()); // get showtime
        seatList = cc.getSeatList(); // get list of user select seat from layout
        totalPrice = seatList.size() * seatList.get(0).getSeatPrice();
        
//******************************Details movie*********************
        nameEng.setText(st.getMovieEng());
        theatre.setText(st.getTheatreNo() + " (" + st.getSystem() + ") " + st.getSoundtrack().toUpperCase() + "/" + st.getSubtitle().toUpperCase());
//****************************** get today ***************************
        date.setText(st.getDate());
        showtime.setText(st.getShowtime());
        typeSeat.setText(seatList.get(0).getSeatType());
        amountSeat.setText(String.valueOf(seatList.size()));
        costPerSeat.setText(String.valueOf(seatList.get(0).getSeatPrice()));
        cost.setText(Double.toString(totalPrice));
        sumcost.setText(Double.toString(totalPrice));
    }

    @FXML
    private void payment(ActionEvent event) throws IOException {
        // ทำการจอง
        System.err.println("Staff Booking");
        double total = Double.valueOf(sumcost.getText());
            Booking b = new Booking(st, seatList, uc.getUser(loginUser.getId()), userSelectPromotion, total);
            System.out.println(b);
            
            cc.addStaffBooking(b);
            AlertMaker.showSimpleAlert("OK", "Book complete");
            Parent parent;
            parent = FXMLLoader.load(getClass().getResource("/cinema/ui/myBooking/MyBooking.fxml"));
            Scene parentScene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(parentScene);
            window.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("/cinema/ui/layoutMedium/layoutMedium.fxml"));
        Scene parentScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(parentScene);
        window.show();
    }


}
