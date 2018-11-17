package cinema.ui.showDetailsMovie;

import cinema.CinemaController;
import cinema.Movie;
import java.io.IOException;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovieDetailController implements Initializable {

    @FXML
    private ImageView movieImg;

    @FXML
    private Text movieNameEng;

    @FXML
    private Text movieNameTh;

    @FXML
    private Text releaseDate;

    @FXML
    private Text genre;

    @FXML
    private Text actor;

    @FXML
    private Text synopsis;
    @FXML
    private MediaView mediaV;
    @FXML
    private Text time;
    @FXML
    private Text director;

    MediaPlayer mediaPlayer;
    boolean play = true;

    Movie movie;
    CinemaController cc;
    @FXML
    private VBox bottomButton;
    @FXML
    private Button btnNext;

    public MovieDetailController() {
        this.cc = cc.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(cc.getIsComingSoon());
        if(cc.getIsComingSoon()){
            btnNext.setVisible(false);
        }
        
        
        // ใส่ ID เข้าไป
        movie = cc.getSelectMovie();
//        System.out.println(movie);
        InputStream imagePoster = getClass().getResourceAsStream("/cinema/posterImage/" + movie.getPoster());
//        URL videoPoster = getClass().getResource("/cinema/ui/video/"+movie.getTrailer());

        Image movie_image = new Image(imagePoster, 223, 301, false, false);
        movieImg.setImage(movie_image);
        movieNameEng.setText(movie.getEnglishName());
        //movieNameEng = new Text(movie.getEnglishName());
        movieNameTh.setText(movie.getThaiName());
        releaseDate.setText(movie.getReleaseDate());
        time.setText(movie.getTime());
        genre.setText(movie.getGenre());
        director.setText(movie.getDirector());
        actor.setText(movie.getCast());
        synopsis.setText(movie.getSynopsis());

        Media media = new Media(movie.getTrailer());
//        
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        mediaV.setMediaPlayer(mediaPlayer);
//****************** webView***********************
//        webV.getEngine().load("https://youtu.be/TiblmGnet2Q");
    }

    @FXML
    private void nextPage(ActionEvent event) throws IOException {
        System.out.println("movie detail");
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showtime/showtime.fxml"));
        Scene parentScene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(parentScene);
        window.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {

        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        
        System.out.println("movie detail");
        Parent parent;
                    parent = FXMLLoader.load(getClass().getResource("/cinema/ui/showmovie/showmovie.fxml"));
                    Scene parentScene = new Scene(parent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(parentScene);
                    window.show();
    }

    @FXML
    private void clickVideo(MouseEvent event) {
        boolean checkEndOfTrailer = mediaPlayer.getCurrentTime().equals(mediaPlayer.getStopTime());
        // Trailer is not end.
        if (!checkEndOfTrailer) {
            if (play) {
                mediaPlayer.pause();
                play = false;
            } else {
                mediaPlayer.play();
                play = true;
            }
        } // Trailer is end.
        else {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
            play = true;
        }
    }


}
