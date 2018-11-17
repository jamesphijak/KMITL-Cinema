/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.showtime;

import cinema.CinemaController;
import cinema.Movie;
import cinema.Showtime;
import cinema.Theatre;
import cinema.UserController;
import cinema.screensframework.ControlledScreen;
import cinema.screensframework.ScreensController;
import cinema.screensframework.ScreensFramework;
import cinema.ui.showmovie.showmovieController;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author BEAMCONAN
 */
public class ShowtimeController implements Initializable, ControlledScreen {

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
    
    ScreensController myController;
    

    /**
     * Initializes the controller class.
     */
    public ShowtimeController() {
        this.cc = cc.getInstance();
        this.uc = uc.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        if(!uc.getIsLogin()){
//            btnNext.setVisible(false);
//        }
//        date.getStyleClass().add("date");
//        date.setTextFill();
        // TODO
        List<Theatre> theatreList = cc.getTheatreList();
        List<Showtime> showtimeOfMovie = cc.getShowtimeList();

        /*for (Theatre theatre : theatreList) {
            if (theatre.getShowtimeList().isEmpty()) {
                System.out.println("Null");
            }
            else {
                int size = theatre.getShowtimeList().size();
            }
        }*/
 /* --------- ได้ showtime ที่มีหนังที่เรากดเข้ามา -----------*/
        List<Showtime> showtimeHaveSelectedMovie = new ArrayList<>();
//        for (Showtime showtime : showtimeOfMovie) {
//            if (showtime.getMovie().getId() == cc.getSelectMovie().getId()) {
//                showtimeHaveSelectedMovie.add(showtime);
//            }
//        }
//
//        System.out.println(showtimeHaveSelectedMovie);

       // Movie movie = cc.getSelectMovie();
       // Movie m = cc.getMovie(2);
        List<Theatre> tList = cc.getTheatreList();

        for (Theatre t : theatreList) {
            List <Showtime> stList = t.getShowtimeList();
            for (Showtime s : stList) {
                if(s.getMovie().getId() == cc.getSelectMovie().getId()){
                    showtimeHaveSelectedMovie.add(s);
                }
            }
        }
        
        
        
        HBox hbox_showtimepertheatre = new HBox();
        int amount = showtimeHaveSelectedMovie.size();
        Integer[] showtimeId = new Integer[amount];

//        Showtime[] showtime = new Showtime[amount];
//        for (int i = 0; i < showtimeOfMovie.size(); i++) { 		      
//            showtime[i] = showtimeOfMovie.get(i);		
//        }
        Showtime[] showtime = new Showtime[amount];
        for (int i = 0; i < showtimeHaveSelectedMovie.size(); i++) {
            showtime[i] = showtimeHaveSelectedMovie.get(i);
        }

        nameEng.setText(showtime[0].getMovie().getEnglishName());
        int i = 0, tempTheatre = showtime[0].getTheatre().getTheatreNumber(), countT = 0;
        Button[] bt = new Button[amount];
        //System.out.println("No: " + tempTheatre); // สร้าง ช่องโรง
        VBox vbox_theatre;

        vbox.getChildren().add(hbox_showtimepertheatre);
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
                    int finalI = i;
                    bt[i].setOnAction(event -> {

//                        System.out.println(showtimeHaveSelectedMovie.get(finalI).getId()); // ปริ้นท์ดู
                        cc.setSelectShowtime(showtimeHaveSelectedMovie.get(finalI).getId());
                        
                        myController.loadScreen(ScreensFramework.userSeatLayoutScreenID, ScreensFramework.userSeatLayoutScreenFile);
                        myController.setScreen(ScreensFramework.userSeatLayoutScreenID);

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
                bt[i].setOnAction(event -> {

//                      System.out.println(showtimeHaveSelectedMovie.get(0).getId()); // ปริ้นท์ดู
                    cc.setSelectShowtime(showtimeHaveSelectedMovie.get(0).getId());
                    myController.loadScreen(ScreensFramework.userSeatLayoutScreenID, ScreensFramework.userSeatLayoutScreenFile);
                    myController.setScreen(ScreensFramework.userSeatLayoutScreenID);

                });
                if (i == amount - 1) {
                    vbox.getChildren().add(hbox_showtimepertheatre);
                }
            }
            countT += 1;
            i += 1;
        }
    }

    @FXML
    private void getDate(ActionEvent event) {
        System.out.println(date.getValue());
        // select showtime
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}
