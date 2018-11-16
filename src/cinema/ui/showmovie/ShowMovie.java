/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.showmovie;

import cinema.ui.CinemaUtility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author BEAMCONAN
 */
public class ShowMovie extends Application  {
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("showmovie.fxml"));
        Scene scene = new Scene(root);      
        stage.setScene(scene);
        stage.setTitle("KMITL Cinema");
        stage.show();
        CinemaUtility.setStageIcon(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
