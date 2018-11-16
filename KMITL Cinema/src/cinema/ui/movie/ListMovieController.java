
package cinema.ui.movie;

import cinema.Cinema;
import cinema.Movie;
import cinema.ui.AlertMaker;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListMovieController implements Initializable {
    
    @FXML
    private TableView<Movie> tableView;
    @FXML
    private TableColumn<Movie, Integer> idCol;
    @FXML
    private TableColumn<Movie, String> englishCol;
    @FXML
    private TableColumn<Movie, String> thaiCol;
    @FXML
    private TableColumn<Movie, String> releaseDateCol;
    
    Cinema cinema;
    ObservableList<Movie> movieList = FXCollections.observableArrayList();
    List<Movie> movieListArray = new ArrayList<Movie>();
    
    public ListMovieController() {
        this.cinema = cinema.getInstance();
    }
    
    public void initcol(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        englishCol.setCellValueFactory(new PropertyValueFactory<>("englishName"));
        thaiCol.setCellValueFactory(new PropertyValueFactory<>("thaiName"));
        releaseDateCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        
    }
    
    public void loadData(){
        movieList.clear();
        movieListArray = cinema.getMovieList();
        try {
            for (Movie m : movieListArray) {
                movieList.add(m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tableView.setItems(movieList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initcol();
        loadData();
    }    

    @FXML
    private void handleRefresh(ActionEvent event) {
    }

    @FXML
    private void handleBookEditOption(ActionEvent event) {
    }

    @FXML
    private void handleBookDeleteOption(ActionEvent event) {
        Movie selectedForDeletion = tableView.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        System.out.println(selectedForDeletion.toString());
        AlertMaker.showSimpleAlert(String.valueOf(selectedForDeletion.getId()), selectedForDeletion.toString());
    }
   
}
