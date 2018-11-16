package cinema.ui.movie;

import cinema.ui.auth.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cinema.ui.CinemaUtility;

public class MovieLoader extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/cinema/ui/movie/MovieList.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/cinema/ui/theme.css");
        stage.setScene(scene);
        stage.setTitle("KMITL Cinema");
        stage.setMaximized(true);
        stage.show();
        CinemaUtility.setStageIcon(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
