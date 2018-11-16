package cinema.screensframework;

import cinema.ui.CinemaUtility;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Angie
 */
public class ScreensFramework extends Application {
    
    public static String userLoginScreenID = "main";
    public static String userLoginScreenFile = "/cinema/ui/auth/Login.fxml";
    public static String userRegisterScreenID = "Register";
    public static String userRegisterScreenFile = "/cinema/ui/auth/Register.fxml";
    public static String userNowShowingScreenID = "NowShowing";
    public static String userNowShowingScreenFile = "/cinema/ui/showmovie/showmovie.fxml";
    public static String administratorScreenID = "AdminPage";
    public static String administratorScreenFile = "/cinema/admin/AdminMain.fxml";
    public static String userShowMovieDetailsScreenID = "MovieDetails";
    public static String userShowMovieDetailsScreenFile = "/cinema/ui/showDetailsMovie/showMovieDetail.fxml";
    public static String userShowtimeScreenID = "Showtime";
    public static String userShowtimeScreenFile = "/cinema/ui/showtime/showtime.fxml";
    public static String userShowMovieDetailsComingSoonScreenID = "ComingSoonMovieDetails";
    public static String userShowMovieDetailsComingSoonScreenFile = "/cinema/ui/showDetailMovieComingSoon/showMovieDetailComingSoon.fxml";
    public static String userSeatLayoutScreenID = "SeatLayout";
    public static String userSeatLayoutScreenFile = "/cinema/ui/layoutMedium/layoutMedium.fxml";
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.userLoginScreenID, ScreensFramework.userLoginScreenFile);
        mainContainer.loadScreen(ScreensFramework.userRegisterScreenID, ScreensFramework.userRegisterScreenFile);
        mainContainer.loadScreen(ScreensFramework.userNowShowingScreenID, ScreensFramework.userNowShowingScreenFile);
        mainContainer.loadScreen(ScreensFramework.administratorScreenID, ScreensFramework.administratorScreenFile);
        
        mainContainer.setScreen(ScreensFramework.userNowShowingScreenID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KMITL Cinema");
        primaryStage.setMaximized(true);
        CinemaUtility.setStageIcon(primaryStage);
        primaryStage.show();
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
