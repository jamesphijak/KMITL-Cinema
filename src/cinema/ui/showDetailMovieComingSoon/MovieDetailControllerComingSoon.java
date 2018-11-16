package cinema.ui.showDetailMovieComingSoon;

import cinema.CinemaController;
import cinema.Movie;
import cinema.screensframework.ControlledScreen;
import cinema.screensframework.ScreensController;
import cinema.screensframework.ScreensFramework;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovieDetailControllerComingSoon implements Initializable, ControlledScreen {

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
    ScreensController myController;

    public MovieDetailControllerComingSoon() {
        this.cc = cc.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // เอา id ใส่เข้าไป
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

        Media media = new Media("http://weshare.lnw.mn/video.mp4");
//        
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        mediaV.setMediaPlayer(mediaPlayer);
//****************** webView***********************
//        webV.getEngine().load("https://youtu.be/TiblmGnet2Q");
    }


    @FXML
    private void back(ActionEvent event) throws IOException {

        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        
        System.out.println("movie detail");
        myController.loadScreen(ScreensFramework.userNowShowingScreenID, ScreensFramework.userNowShowingScreenFile);
        myController.setScreen(ScreensFramework.userNowShowingScreenID);
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

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

}
