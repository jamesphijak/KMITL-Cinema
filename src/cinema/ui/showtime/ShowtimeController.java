/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.showtime;

import cinema.CinemaController;
import cinema.Showtime;
import cinema.Theatre;
import cinema.UserController;
import cinema.ui.AlertMaker;
import cinema.ui.showDetailsMovie.MovieDetailController;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowtimeController implements Initializable{

    @FXML
    private Text nameEng;
    @FXML
    private JFXDatePicker date;
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scroll;
    //Movie movie;
    CinemaController cc;
    UserController uc;
    @FXML
    private StackPane rootPane;
    @FXML
    private Text textNotHaveShowtime;
    HBox hbox_showtimepertheatre = new HBox();
 
    public ShowtimeController() {
        this.cc = cc.getInstance();
        this.uc = uc.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        date.setValue(LocalDate.now());
        
        //hbox_showtimepertheatre.setVisible(true);
//        hbox_showtimepertheatre.getChildren().removeAll();
        loadShowtime(date.getValue());
        
    }
    
    public void loadShowtime(LocalDate date) {
        String formattedString = null;
        try{
            LocalDate ld = date;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            formattedString = ld.format(formatter);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        List<Theatre> theatreList = cc.getTheatreList();
        /* --------- ได้ showtime ที่มีหนังที่เรากดเข้ามา -----------*/
        List<Showtime> showtimeHaveSelectedMovie = new ArrayList<>();
        List<Theatre> tList = cc.getTheatreList();
        boolean checkShowtimeExist = false;
        // เช็คว่า showtime มีหนังนั้นไหม ถ้ามีเพิ่มเข้า List
        for (Theatre t : theatreList) {
            List <Showtime> stList = t.getShowtimeList();
            for (Showtime s : stList) {                
                if(s.getDate().equals(formattedString)) {
                    if(s.getMovie().getId() == cc.getSelectMovie().getId()){
                        showtimeHaveSelectedMovie.add(s);
                        checkShowtimeExist = true;
                    }
                }
            }
        }
        
        nameEng.setText(cc.getSelectMovie().getEnglishName());
        
        if(!checkShowtimeExist) {
            textNotHaveShowtime.setText("ไม่มีรอบฉายในวันที่ที่ท่านเลือก");
            //hbox_showtimepertheatre.setVisible(false);
        }else{
            //hbox_showtimepertheatre.setVisible(true);
            textNotHaveShowtime.setText(null);
            
            int amount = showtimeHaveSelectedMovie.size();
            Integer[] showtimeId = new Integer[amount];

            Showtime[] showtime = new Showtime[amount];
            for (int i = 0; i < showtimeHaveSelectedMovie.size(); i++) {
                showtime[i] = showtimeHaveSelectedMovie.get(i);
            }

            int i = 0, tempTheatre = showtime[0].getTheatre().getTheatreNumber(), countT = 0;
            Button[] bt = new Button[amount];
            VBox vbox_theatre;

            Text theatreNo = new Text(Integer.toString(showtime[i].getTheatre().getTheatreNumber()));

            theatreNo.setFont(Font.font("Sukhumvit Set Text", 22));
            theatreNo.setFill(Color.web("0xFFFFFF"));
            Text detailsTheatre = new Text(showtime[i].getSystem() + " - " + showtime[i].getSoundtrack().toUpperCase() + "/" + showtime[i].getSubtitle().toUpperCase());
            detailsTheatre.setFont(Font.font("Sukhumvit Set Text", 18));
            detailsTheatre.setFill(Color.web("0xFFFFFF"));

            vbox_theatre = new VBox(theatreNo, detailsTheatre);
            vbox_theatre.setPrefWidth(100);
            vbox_theatre.setAlignment(Pos.CENTER);
            hbox_showtimepertheatre = new HBox(vbox_theatre);
            HBox.setMargin(vbox_theatre, new Insets(0, 20, 0, 10));

            while (i < amount) {
                if (tempTheatre != showtime[i].getTheatreNo()) {
                    System.out.println("Noo: " + tempTheatre);
                    if (countT != 0) {
                        countT = 0;//0;
                        tempTheatre = showtime[i].getTheatreNo();
                        System.out.println("No: " + tempTheatre);
                    }
                    if (countT == 0) {
                        vbox.getChildren().add(hbox_showtimepertheatre);
                        theatreNo = new Text(Integer.toString(showtime[i].getTheatreNo()));
                        theatreNo.setFont(Font.font("Sukhumvit Set Text", 22));
                        theatreNo.setFill(Color.web("0xFFFFFF"));
                        detailsTheatre = new Text(showtime[i].getSystem() + " - " + showtime[i].getSoundtrack().toUpperCase() + "/" + showtime[i].getSubtitle().toUpperCase());
                        detailsTheatre.setFont(Font.font("Sukhumvit Set Text", 18));
                        detailsTheatre.setFill(Color.web("0xFFFFFF"));

                        vbox_theatre = new VBox(theatreNo, detailsTheatre);
                        vbox_theatre.setPrefWidth(100);
                        vbox_theatre.setAlignment(Pos.CENTER);
                        hbox_showtimepertheatre = new HBox(vbox_theatre);
                        HBox.setMargin(vbox_theatre, new Insets(0, 20, 0, 10));
                        bt[i] = new Button(showtime[i].getShowtime());
                        bt[i].setFont(Font.font("Sukhumvit Set Semi Text", 22));
                        bt[i].setMaxWidth(200);
                        bt[i].getStyleClass().add("button-st");
                        HBox.setMargin(bt[i], new Insets(5, 10, 5, 10));
                        hbox_showtimepertheatre.getChildren().add(bt[i]);
                        showtimeId[i] = showtime[i].getId();
                        System.err.println(showtimeId[i]);
                        int finalI = i;
                        bt[i].setOnAction(event -> {
                            try {
                                cc.setSelectShowtime(showtimeId[finalI]);

                                Parent parent;
                                parent = FXMLLoader.load(getClass().getResource("/cinema/ui/layoutMedium/layoutMedium.fxml"));
                                Scene parentScene = new Scene(parent);
                                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                                window.setScene(parentScene);
                                window.show();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }

                        });
                        if (i == amount - 1) {
                            vbox.getChildren().add(hbox_showtimepertheatre);
                        }
                    }
                } else {

                    bt[i] = new Button(showtime[i].getShowtime());
                    bt[i].setFont(Font.font("Sukhumvit Set Semi Text", 22));
                    bt[i].setMaxWidth(200);
                    bt[i].getStyleClass().add("button-st");
                    HBox.setMargin(bt[i], new Insets(5, 10, 5, 10));
                    hbox_showtimepertheatre.getChildren().add(bt[i]);
                    showtimeId[i] = showtime[i].getId();
                    System.err.println(showtimeId[i]);
                    int finalI = i;
                    bt[i].setOnAction(event -> {
                        try {
                                cc.setSelectShowtime(showtimeId[finalI]);

                                Parent parent;
                                parent = FXMLLoader.load(getClass().getResource("/cinema/ui/layoutMedium/layoutMedium.fxml"));
                                Scene parentScene = new Scene(parent);
                                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                                window.setScene(parentScene);
                                window.show();
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }

                    });
                    if (i == amount - 1) {
                        vbox.getChildren().add(hbox_showtimepertheatre);
                    }
                }
                countT += 1;
                i += 1;
            }
        }
    }

    @FXML
    private void getDate(ActionEvent event) {
        System.out.println(date.getValue());
        LocalDate today = LocalDate.now();
        vbox.getChildren().clear();
        if((today.until(date.getValue(), ChronoUnit.DAYS) < 0)) {
            textNotHaveShowtime.setText("ไม่สามารถแสดงเวลาฉายที่น้อยกว่าวันที่ปัจจุบันได้");
            //hbox_showtimepertheatre.setVisible(false);
//            hbox_showtimepertheatre.getChildren().clear();
        }
        else {
            if((today.until(date.getValue(), ChronoUnit.DAYS) > 7)) {
                textNotHaveShowtime.setText("ไม่สามารถแสดงรายการเวลาฉายได้เกิน 7 วัน");
                //hbox_showtimepertheatre.setVisible(false);
//                hbox_showtimepertheatre.getChildren().clear();
            }
            else {
                //hbox_showtimepertheatre.setVisible(true);
//                hbox_showtimepertheatre.getChildren().clear();
                loadShowtime(date.getValue());
            }
        }
//        hbox_showtimepertheatre.getChildren().clear();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent parent;
        
        if(uc.getIsLogin()){
            if(uc.getLoginUser().getType().equals("Staff")){
                parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showmovie/showmovie.fxml"));
                Scene parentScene = new Scene(parent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(parentScene);
                window.show();
            }
            else {
                parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showDetailsMovie/showMovieDetail.fxml"));
                Scene parentScene = new Scene(parent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(parentScene);
                window.show();
            }
        }
        else {
            parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showDetailsMovie/showMovieDetail.fxml"));
            Scene parentScene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(parentScene);
            window.show();
        }
    }

}
