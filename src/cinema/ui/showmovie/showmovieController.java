/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.showmovie;

import cinema.CinemaController;
import cinema.Movie;
import cinema.UserController;
import cinema.ui.AlertMaker;
import cinema.ui.showDetailsMovie.MovieDetailController;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class showmovieController implements Initializable {

    @FXML
    private ScrollPane scrollp1;
    @FXML
    private VBox field;
    @FXML
    private ScrollPane scrollp2;
    @FXML
    private VBox field2;
    @FXML
    private StackPane rootPane;

    CinemaController cc;
    UserController uc;
    List<Movie> movieList = new ArrayList<Movie>();

    
    @FXML
    private Label labelAuthen;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Label labelMyBooking;

    public showmovieController() {
        this.cc = cc.getInstance();
        this.uc = uc.getInstance();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(uc.getIsLogin()){
            labelAuthen.setText(uc.getLoginUser().getEmail() + " -> Logout");
            labelMyBooking.setVisible(true);
        }else{
            labelAuthen.setText("Login");
            labelMyBooking.setVisible(false);
        }
        
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        if(cc.getIsComingSoon()){
            selectionModel.select(1); // select theatre
        }else{
            selectionModel.select(0); // select theatre
        }
        
        //***************************************** Now showing ************************************************
        movieList = cc.getMovieList();
        /* ----------------------- check is now showing ------------------------------ */
        int showingDay = 14; // จำนวนวันที่จะให้หนังอยู่ในโรง
        LocalDate today = LocalDate.now();
        List<Movie> nowShowingList = new ArrayList<>();
        List<Movie> comingSoonList = new ArrayList<>();
        List<String> dateAbbrevMonth = new ArrayList<>();
        String stringReleaseDate;
        LocalDate releaseDate;
        long tempDayShowing; // ฉายมาแล้วกี่วัน (Return เป็น long)
        int dayShowing;// เปลี่ยน tempDayShowing เป็น int และไปบวกกับ showingDay
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        
        for (Movie movie : movieList) {
            String date = movie.getReleaseDate();
            releaseDate = LocalDate.parse(date, formatter);

            tempDayShowing = today.until(releaseDate, ChronoUnit.DAYS); // ฉายมาแล้วกี่วัน ( จะได้ค่าติดลบจำนวนวัน )
            dayShowing = (int) tempDayShowing + showingDay; // เหลือเวลาฉายอีกกี่วัน
            if(today.until(releaseDate, ChronoUnit.DAYS) > 0) {
                // เป็น coming soon
                comingSoonList.add(movie);
            }
            else {
                // เป็น now showing
                if(dayShowing >= 0) {
                    nowShowingList.add(movie);
                }
            }
        }

        
        int amountN = nowShowingList.size(); // count movie
        Movie[] movieN = new Movie[amountN];
        for (int i = 0; i < nowShowingList.size(); i++) {
            movieN[i] = nowShowingList.get(i);
        }
        VBox[] vboxN = new VBox[amountN];
        ImageView[] movieImgN = new ImageView[amountN];
        Text[] nameEN = new Text[amountN];
        Text[] nameTN = new Text[amountN];
        Button[] btN = new Button[amountN];
        Integer[] movieId = new Integer[amountN];
        for (int i = 0; i < amountN; i++) {
            InputStream image = getClass().getResourceAsStream("/cinema/posterImage/" + movieN[i].getPoster());
            Image movie_image = new Image(image, 185, 275, false, false);
            movieImgN[i] = new ImageView(movie_image);
            String e = movieN[i].getEnglishName();
            if (e.length() > 20) {
                e = e.substring(0, 20) + "...";
            }
            nameEN[i] = new Text(185, 30, e);
            nameEN[i].setFont(Font.font("Sukhumvit Set Semi Bold", 28));
            nameEN[i].setFill(Color.web("0xFFFFFF"));
            String t = movieN[i].getThaiName();
            if (t.length() > 20) {
                t = t.substring(0, 20) + "...";
            }
            nameTN[i] = new Text(185, 30, t);
            nameTN[i].setFont(Font.font("Sukhumvit Set Text", 20));
            nameTN[i].setFill(Color.web("0xFFFFFF"));
            btN[i] = new Button("BUY NOW");
            btN[i].setId("BUY NOW" + i);
            //movieId[i] = movieN[i].getId();
            int finalI = i;
            btN[i].setOnAction(event -> {
                try {
                    //checkID(btN[finalI]);
                    cc.setSelectMovie(nowShowingList.get(finalI).getId());
                    cc.setIsComingSoon(false);
                    Parent parent;
//                    parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showDetailsMovie/showMovieDetail.fxml"));
                    if(uc.getIsLogin()){
                        if(uc.getLoginUser().getType().equals("Staff")){
                            parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showtime/showtime.fxml"));
                            Scene parentScene = new Scene(parent);
                            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                            window.setScene(parentScene);
                            window.show();

//                            MovieDetailController.mediaPlayer.stop();
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
//                    Scene parentScene = new Scene(parent);
//                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                    window.setScene(parentScene);
//                    window.show();
                } catch (IOException ex) {
                    System.out.println(ex);
                }

            });
            btN[i].setMaxWidth(180);
            btN[i].getStyleClass().add("button-account");
            btN[i].setFont(Font.font("Sukhumvit Set Semi Bold", 20));

            vboxN[i] = new VBox(movieImgN[i], nameEN[i], nameTN[i], btN[i]);
            vboxN[i].setPrefWidth(50);
            vboxN[i].setAlignment(Pos.CENTER);
            vboxN[i].setPrefSize(295, 300);
            VBox.setMargin(nameEN[i], new Insets(5, 0, 0, 0));
            VBox.setMargin(nameTN[i], new Insets(5, 0, 0, 0));
            VBox.setMargin(btN[i], new Insets(10, 0, 0, 0));
            HBox.setMargin(vboxN[i], new Insets(10, 10, 10, 10));
        }
        HBox movieRaw_hboxN[] = new HBox[4];
        VBox movieLine_vboxN = new VBox();
        int i = 0;
        //System.out.print(Math.floor(amountN / 4));
        while (i <= Math.floor(amountN / 4)) {
            movieRaw_hboxN[i] = new HBox();
            int j = 0;
            while (j < 4 && (i * 4) + j < movieN.length) {
                movieRaw_hboxN[i].getChildren().addAll(vboxN[(i * 4) + j]);
                j++;
            }
            movieLine_vboxN.getChildren().addAll(movieRaw_hboxN[i]);
            i++;
        }
        movieLine_vboxN.setPrefSize(1280, movieLine_vboxN.getHeight());
        scrollp1.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        field.getChildren().add(movieLine_vboxN);
        scrollp1.setContent(field);
        
//***************************************** Coming Soon ************************************************
        //movieList = cc.getMovieList();
        int amountC = comingSoonList.size();
        Movie[] movieC = new Movie[amountC];
        for (int j = 0; j < comingSoonList.size(); j++) {
            movieC[j] = comingSoonList.get(j);
        }
        VBox[] vboxC = new VBox[amountC];
        ImageView[] movieImgC = new ImageView[amountC];
        Text[] nameEC = new Text[amountC];
        Text[] nameTC = new Text[amountC];
        Button[] btC = new Button[amountC];
        for (i = 0; i < amountC; i++) {
            InputStream image = getClass().getResourceAsStream("/cinema/posterImage/" + movieC[i].getPoster());
            Image movie_image = new Image(image, 185, 275, false, false);
            movieImgC[i] = new ImageView(movie_image);
            String e = movieC[i].getEnglishName();
            if (e.length() > 20) {
                e = e.substring(0, 20) + "...";
            }
            nameEC[i] = new Text(185, 30, e);
            nameEC[i].setFont(Font.font("Sukhumvit Set Semi Bold", 28));
            nameEC[i].setFill(Color.web("0xFFFFFF"));
            String t = movieC[i].getThaiName();
            if (t.length() > 20) {
                t = t.substring(0, 20) + "...";
            }
            nameTC[i] = new Text(185, 30, t);
            nameTC[i].setFont(Font.font("Sukhumvit Set Text", 20));
            nameTC[i].setFill(Color.web("0xFFFFFF"));
            btC[i] = new Button("SELECT");
            btC[i].setId("SELECT" + i);
            int finalI = i;
            btC[i].setOnAction(event -> {
               try {
                    //checkID(btN[finalI]);
                    cc.setSelectMovie(comingSoonList.get(finalI).getId());
                    cc.setIsComingSoon(true);
                    Parent parent;
                    parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showDetailsMovie/showMovieDetail.fxml"));
//                    if(uc.getIsLogin()){
//                        if(uc.getLoginUser().getType().equals("Staff")){
//                             parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showtime/showtime.fxml"));
//                        }
//                    }
                    Scene parentScene = new Scene(parent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(parentScene);
                    window.show();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            });
            btC[i].setMaxWidth(180);
            btC[i].getStyleClass().add("button-account");
            btC[i].setFont(Font.font("Sukhumvit Set Semi Bold", 20));

            vboxC[i] = new VBox(movieImgC[i], nameEC[i], nameTC[i], btC[i]);
            vboxC[i].setPrefWidth(50);
            vboxC[i].setAlignment(Pos.CENTER);
            vboxC[i].setPrefSize(295, 300);
            VBox.setMargin(nameEC[i], new Insets(5, 0, 0, 0));
            VBox.setMargin(nameTC[i], new Insets(5, 0, 0, 0));
            VBox.setMargin(btC[i], new Insets(10, 0, 0, 0));
            HBox.setMargin(vboxC[i], new Insets(10, 10, 10, 10));
        }
        HBox movieRaw_hboxC[] = new HBox[4];
        VBox movieLine_vboxC = new VBox();
        i = 0;
        //System.out.print(Math.floor(amountC / 4));
        while (i <= Math.floor(amountC / 4)) {
            movieRaw_hboxC[i] = new HBox();
            int j = 0;
            while (j < 4 && (i * 4) + j < movieC.length) {
                movieRaw_hboxC[i].getChildren().addAll(vboxC[(i * 4) + j]);
                j++;
            }
            movieLine_vboxC.getChildren().addAll(movieRaw_hboxC[i]);
            i++;
        }
        movieLine_vboxC.setPrefSize(1280, movieLine_vboxC.getHeight());
        scrollp2.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        field2.getChildren().add(movieLine_vboxC);
        scrollp2.setContent(field2);
    }

    @FXML
    private void handleAuthen(MouseEvent event) throws IOException{
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            if(uc.getIsLogin()){
                uc.setIsLogin(false);
                uc.unsetLoginUser();
                cc.setIsComingSoon(false);
            }
            
                Parent parent;
                parent = FXMLLoader.load(getClass().getResource("/cinema/ui/auth/Login.fxml"));
                Scene parentScene = new Scene(parent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(parentScene);
                window.show();
            }
            System.out.println("CLICK");
    }

    @FXML
    private void handleBooking(MouseEvent event) throws IOException {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            if(uc.getIsLogin()){
                Parent parent;
                parent = FXMLLoader.load(getClass().getResource("/cinema/ui/myBooking/MyBooking.fxml"));
                Scene parentScene = new Scene(parent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(parentScene);
                window.show();
            }
            
        }
    }
}
