/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.summary;

import cinema.Booking;
import cinema.CinemaController;
import cinema.Movie;
import cinema.NormalSeat;
import cinema.Promotion;
import cinema.PromotionController;
import cinema.Seat;
import cinema.Showtime;
import cinema.Theatre;
import cinema.User;
import cinema.UserController;
import cinema.ui.AlertMaker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BEAMCONAN
 */
public class summaryController implements Initializable {

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
    private ComboBox<Promotion> selectPromotion;
    @FXML
    private Text sumcost;
    @FXML
    private Text moneyUser;
    @FXML
    private Text discount;
    @FXML
    private Text details;
    
    @FXML
    private Text username;
    @FXML
    private Text showtime;
    @FXML
    private StackPane rootPane;

    User user;
    Booking booking;
    Movie movie;
    
    CinemaController cc;
    UserController uc;
    PromotionController pc;
    User loginUser;
    Promotion userSelectPromotion = null;
    
    @FXML
    private Button btnCancelPromotion;
    
    public summaryController() {
        this.uc = uc.getInstance();
        this.pc = pc.getInstance();
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
      
        loadPromotionSelect();
        
        discount.setText(Double.toString(0));
        details.setText(null);
        sumcost.setText(Double.toString(totalPrice));
        btnCancelPromotion.setVisible(false);
        userSelectPromotion = null;
//
//******************************Details user*********************
        username.setText(loginUser.getFirstname());
        moneyUser.setText(Double.toString(uc.getUser(loginUser.getId()).getMoney()));
////******************************* test Promotion *********************
        
    }
    
    public void loadPromotionSelect(){
        ObservableList<Promotion> promotionList = FXCollections.observableArrayList();
        promotionList.clear();
        List<Promotion> allPromotion = pc.getPromotionList();
        List<Promotion> usedPromotion = uc.getUser(loginUser.getId()).getPromotionList(); // update get user promotion from id
        
        // Get never used promotion
        if(usedPromotion.size() > 0){
            allPromotion = pc.getUnusePromotion(loginUser.getId());
            if(allPromotion.size() > 0){
                selectPromotion.setDisable(false);
                System.err.println(allPromotion.size());
                for (Promotion promotion : allPromotion) {
                    promotionList.add(promotion);
                }
                selectPromotion.setItems(promotionList);
            }else{
                selectPromotion.setDisable(true);
            }
        }else{
            if(allPromotion.size() > 0){
                selectPromotion.setDisable(false);
                for (Promotion aP : allPromotion) {
                     promotionList.add(aP);
                      System.err.println("USER NO PROMOTION");
                }
                selectPromotion.setItems(promotionList);
            }else{
                selectPromotion.setDisable(true);
            }
            
        }
        
        
        selectPromotion.setStyle("-fx-text-fill: #ffffff;");
        //selectPromotion.setValue("Don't use promotion");
        
    }

    public void topupUserMoney(Double money){
        uc.topupUserMoney(loginUser.getId(), money);
        moneyUser.setText(Double.toString(uc.getUserMoney(loginUser.getId())));
    }
    @FXML
    private void plusMoney100(ActionEvent event) {
        topupUserMoney(100.0);
    }
    @FXML
    private void plusMoney150(ActionEvent event) {
        topupUserMoney(150.0);
    }
    @FXML
    private void plusMoney200(ActionEvent event) {
        topupUserMoney(200.0);
    }
    @FXML
    private void plusMoney300(ActionEvent event) {
        topupUserMoney(300.0);
    }
    @FXML
    private void plusMoney500(ActionEvent event) {
        topupUserMoney(500.0);
    }
    @FXML
    private void plusMoney1000(ActionEvent event) {
        topupUserMoney(1000.0);
    }
    
    @FXML
    private void payment(ActionEvent event) throws IOException {
        //booking.payment();
        System.err.print("Start Booking...");
        double total = Double.valueOf(sumcost.getText());
        if(uc.getUser(loginUser.getId()).getMoney() >= total){
            Booking b = new Booking(st, seatList, uc.getUser(loginUser.getId()), userSelectPromotion, total);
            System.out.println(b);
            cc.addBooking(b);
            AlertMaker.showSimpleAlert("ซื้อที่นั่งสำเร็จ", "เหลือเงิน : " + uc.getUser(loginUser.getId()).getMoney());
            Parent parent;
            parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showmovie/showmovie.fxml"));
            Scene parentScene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(parentScene);
            window.show();
        }else{
            AlertMaker.showErrorMessage("จำนวนเงินไม่เพียงพอ","กรุณาเติมเงินเข้าสู่ระบบ");
        }
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

    @FXML
    private void handlePromotion(ActionEvent event) {
       // loadPromotionSelect();
        System.out.println("Hello Promotion");
            if(selectPromotion.getValue() != null){
            Promotion p = selectPromotion.getValue();
            discount.setText(Double.toString(p.getDiscount()));
            details.setText(p.getDescription());
            Double lastTotal = totalPrice - p.getDiscount();
            if(lastTotal >= 0){
                sumcost.setText(Double.toString(totalPrice - p.getDiscount()));
            }else{
                sumcost.setText("0.0");
            }
            btnCancelPromotion.setVisible(true);
            userSelectPromotion = p;
        }
    }

    @FXML
    private void handleCancelPromotion(ActionEvent event) {
        discount.setText(Double.toString(0));
        details.setText(null);
        sumcost.setText(Double.toString(totalPrice));
        selectPromotion.setValue(null);
        btnCancelPromotion.setVisible(false);
        userSelectPromotion = null;
    }


}
