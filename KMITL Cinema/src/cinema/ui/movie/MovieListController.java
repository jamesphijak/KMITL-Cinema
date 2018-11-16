/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.movie;

import cinema.Movie;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Phijak
 */
public class MovieListController implements Initializable {

    @FXML
    private AnchorPane test;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Movie movie = new Movie(
                "Test EN",
                "Test TH",
                "Test Director",
                "Test cast",
                "Test Synopsis",
                "Test Genre",
                "Test Release Date",
                "Test Time",
                "seat.png",
                "Test Trailer"
        );
        
            System.out.println(movie.toString());
                
            
            //FileInputStream seats_fileInputStream;
            
            //seats_fileInputStream = new FileInputStream("C:\\Users\\Phijak\\Documents\\GitHub\\OOAD\\KMITL Cinema\\src\\cinema\\ui\\movie\\");
            InputStream content = getClass().getResourceAsStream("/cinema/ui/movie/"+movie.getPoster());
            
            Image seats_image = new Image(content,50,50,false,false);
            ImageView[] seats = new ImageView[30];
            
            for(int i = 0;i<30;i++){
                seats[i] = new ImageView(seats_image);
            }

            HBox seatsRaw_hbox[] = new HBox[5];
            VBox seatsLine_vbox = new VBox();

            for(int i=0;i<5;i++){
                seatsRaw_hbox[i]= new HBox();
                for(int j=0;j<6;j++){
                    seatsRaw_hbox[i].getChildren().addAll(seats[j]);             
                }
                seatsLine_vbox.getChildren().addAll(seatsRaw_hbox[i]);
            } 
                
            test.getChildren().add(seatsLine_vbox);
        } catch (Exception ex) {    
            Logger.getLogger(MovieListController.class.getName()).log(Level.SEVERE, null, ex);
        }    

    }
    
}
