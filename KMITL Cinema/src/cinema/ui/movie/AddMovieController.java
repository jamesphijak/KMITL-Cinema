/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.movie;

import cinema.Cinema;
import cinema.Movie;
import cinema.ui.AlertMaker;
import cinema.ui.CinemaUtility;
import cinema.ui.FieldValidation;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Phijak
 */
public class AddMovieController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField txtEnglishName;
    @FXML
    private Label labelEnglishName;
    @FXML
    private JFXTextField txtThaiName;
    @FXML
    private Label labelThaiName;
    @FXML
    private JFXTextField txtDirector;
    @FXML
    private Label labelDirector;
    @FXML
    private JFXTextField txtCast;
    @FXML
    private Label labelCast;
    @FXML
    private JFXTextField txtSynopsis;
    @FXML
    private Label labelSynopsis;
    @FXML
    private JFXTextField txtGenre;
    @FXML
    private Label labelGenre;
    @FXML
    private JFXDatePicker pickerReleaseDate;
    @FXML
    private Label labelPickerReleaseDate;
    @FXML
    private Label labelBrowseImage;
    @FXML
    private JFXTextField txtTrailer;
    @FXML
    private Label labelTrailer;
    @FXML
    private ImageView browseImageShow;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private Label labelTime;
    
    
    private File lastPath;
    
    private File selectedFile;
    private String fileName = "";
    private Image browseImage;
    
    private String dateName = "";
    
    private Path to;
    private Path from;

    Cinema cinema;

    public AddMovieController() {
        this.cinema = cinema.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clearLabel(); // Remove all error label
        
    }    
    
    // ลบค่า Label
    public void clearLabel(){
        labelEnglishName.setText("");
        labelThaiName.setText("");
        labelDirector.setText("");
        labelCast.setText("");
        labelSynopsis.setText("");
        labelGenre.setText("");
        labelTime.setText("");
        labelPickerReleaseDate.setText("");
        labelBrowseImage.setText("");
        labelTrailer.setText("");
    }
    
    public void resetAllStyle(){
        resetEnglishNameStyle();
        resetThaiNameStyle();
        resetDirectorStyle();
        resetCastStyle();       
        resetSynopsisStyle();
        resetGenreStyle();
        resetTimeStyle();
        resetTrailerStyle();
    }
    
    public void resetEnglishNameStyle(){ CinemaUtility.resetTextStyle(txtEnglishName, labelEnglishName, "text-field-error"); }
    public void resetThaiNameStyle(){ CinemaUtility.resetTextStyle(txtThaiName, labelThaiName, "text-field-error"); }
    
    public void resetDirectorStyle(){ CinemaUtility.resetTextStyle(txtDirector, labelDirector, "text-field-error"); }
    public void resetCastStyle(){ CinemaUtility.resetTextStyle(txtCast, labelCast, "text-field-error"); }
    public void resetSynopsisStyle(){ CinemaUtility.resetTextStyle(txtSynopsis, labelSynopsis, "text-field-error"); }
    public void resetGenreStyle(){ CinemaUtility.resetTextStyle(txtGenre, labelGenre, "text-field-error"); }
    public void resetTimeStyle(){ CinemaUtility.resetTextStyle(txtTime, labelTime, "text-field-error"); }
    
    public void resetTrailerStyle(){ CinemaUtility.resetTextStyle(txtTrailer, labelTrailer, "text-field-error"); }

    @FXML
    private void handleBrowseImage(ActionEvent event) {
         // เปิด Dialog รับแต่ Image
         FileChooser fileChooser = new FileChooser();
         FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
         fileChooser.getExtensionFilters().add(imageFilter);
         if (lastPath != null) {
            fileChooser.setInitialDirectory(lastPath);
         }
         selectedFile = fileChooser.showOpenDialog(null);
         if (selectedFile != null) {
            labelBrowseImage.setText(selectedFile.getName()); // Select file name
            browseImage = new Image(selectedFile.toURI().toString());
            browseImageShow.setImage(browseImage);
            lastPath = selectedFile.getParentFile();
         }
         else {
            labelBrowseImage.setText("");
            browseImageShow.setImage(null);
            selectedFile = null;
         }
    }
    


    @FXML
    private void handleCreate(ActionEvent event) {
        // แปลง Date เป็น Pattern ปกติ
        System.out.println(txtEnglishName.getText());
        System.out.println(txtThaiName.getText());
        System.out.println(txtDirector.getText());
        System.out.println(txtCast.getText());
        System.out.println(txtSynopsis.getText());
        System.out.println(txtGenre.getText());
        System.out.println(txtTime.getText());
        // Check ว่า Picker ว่างมั้ย
        if(pickerReleaseDate.getValue() != null){
            System.out.println(convertDate(pickerReleaseDate)); // แปลงวันที่
            dateName = convertDate(pickerReleaseDate);
        }else{
            dateName = "";
        }
        // Check ว่า Browse file หรือยัง
        if(selectedFile != null){
            System.out.println(selectedFile.getName());
            fileName = selectedFile.toURI().toString();
        }else{
            fileName = "";
        }
        System.out.println(txtTrailer.getText());
        
        // ตรวจสอบค่าว่าง
        boolean isEnglishNameNotEmpty = FieldValidation.isTextFieldNotEmpty(txtEnglishName, labelEnglishName, "Please enter english name");
        boolean isThaiNameNotEmpty = FieldValidation.isTextFieldNotEmpty(txtThaiName, labelThaiName, "Please enter thai name");
        boolean isDirectorNotEmpty = FieldValidation.isTextFieldNotEmpty(txtDirector, labelDirector, "Please enter director");
        boolean isCastNotEmpty = FieldValidation.isTextFieldNotEmpty(txtCast, labelCast, "Please enter cast");
        boolean isSynopsisNotEmpty = FieldValidation.isTextFieldNotEmpty(txtSynopsis, labelSynopsis, "Please enter synopsis");
        boolean isGenreNotEmpty = FieldValidation.isTextFieldNotEmpty(txtGenre, labelGenre, "Please enter genre");
        boolean isTimeNotEmpty = FieldValidation.isTextFieldNotEmpty(txtTime, labelTime, "Please enter time");
        
        boolean isDateNotEmpty = FieldValidation.isTextNotEmpty(dateName,labelPickerReleaseDate, "Please select date");
        boolean isFileNotEmpty = FieldValidation.isTextNotEmpty(fileName,labelBrowseImage, "Please select poster image");
        
        boolean isTrailerNotEmpty = FieldValidation.isTextFieldNotEmpty(txtTrailer, labelTrailer, "Please enter trailer");
        
        
        if(isEnglishNameNotEmpty && isThaiNameNotEmpty && isDirectorNotEmpty && isCastNotEmpty && isSynopsisNotEmpty &&
           isGenreNotEmpty && isTimeNotEmpty && isDateNotEmpty && isFileNotEmpty && isTrailerNotEmpty){
                // เริ่ม Add
                System.out.println("Add Operation");
                resetAllStyle();
                clearLabel();
                // copy image
                
                // create movie object
                Movie movie = new Movie(
                    txtEnglishName.getText(),
                    txtThaiName.getText(),
                    txtDirector.getText(),
                    txtCast.getText(),
                    txtSynopsis.getText(),
                    txtGenre.getText(),
                    txtTime.getText(),
                    dateName,
                    fileName,
                    txtTrailer.getText());
                cinema.addMovie(movie); // Add movie
                // Save image to project directory
                
                AlertMaker.showSimpleAlert("Add movie success", movie.toString());
                
        }
    }
    
    public String convertDate(JFXDatePicker pickerDate){
        String formattedString = null;
        try{
        LocalDate ld = pickerDate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        formattedString = ld.format(formatter);
        //labelPickerReleaseDate.setText(formattedString);
        }catch(Exception e){
            System.out.println(e);
        }
        return formattedString;
    }

    @FXML
    private void handleBack(ActionEvent event) {
    }


    
}
