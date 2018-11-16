/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.summaryStaff;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author BEAMCONAN
 */
public class summaryStaff extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent summary = FXMLLoader.load(getClass().getResource("/cinema/ui/summaryStaff/summaryStaff.fxml"));

        Scene summaryScene = new Scene(summary);

        stage.setScene(summaryScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
