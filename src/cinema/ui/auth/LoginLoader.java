package cinema.ui.auth;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cinema.ui.CinemaUtility;
import javafx.scene.text.Font;

public class LoginLoader extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/cinema/ui/auth/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/cinema/ui/theme.css");
        Font.loadFont(LoginLoader.class.getResource("/cinema/ui/sukhumvit-set_text.ttf").toExternalForm(), 10);
        Font.loadFont(LoginLoader.class.getResource("/cinema/ui/sukhumvit-set_medium.ttf").toExternalForm(), 10);
        Font.loadFont(LoginLoader.class.getResource("/cinema/ui/sukhumvit-set_bold.ttf").toExternalForm(), 10);
        stage.setScene(scene);
        stage.setTitle("KMITL Cinema");
        //stage.setMaximized(true);
        stage.show();
        CinemaUtility.setStageIcon(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
