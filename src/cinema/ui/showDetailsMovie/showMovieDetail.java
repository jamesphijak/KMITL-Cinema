package cinema.ui.showDetailsMovie;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class showMovieDetail extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("showMovieDetail.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("KMITL Cinema - Movie Detail");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}