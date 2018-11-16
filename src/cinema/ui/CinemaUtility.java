package cinema.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CinemaUtility {
    private static final String IMAGE_LOC = "/cinema/ui/images/logo.png";
    
    public static void setStageIcon(Stage stage){
        stage.getIcons().add(new Image(IMAGE_LOC));
    }
    
    public static void resetTextStyle(JFXTextField tf, Label lb, String name){
        tf.getStyleClass().removeIf(style -> style.equals(name));
        lb.setText("");
    }
    
    public static void resetTextStyle(JFXPasswordField tf, Label lb,String name){
        tf.getStyleClass().removeIf(style -> style.equals(name));
        lb.setText("");
    }
}
