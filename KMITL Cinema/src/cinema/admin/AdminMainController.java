package cinema.admin;

import cinema.Cinema;
import cinema.Movie;
import cinema.Showtime;
import cinema.User;
import cinema.ui.AlertMaker;
import com.jfoenix.controls.JFXTabPane;
import com.sun.javafx.scene.control.skin.NestedTableColumnHeader;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Phijak
 */
public class AdminMainController implements Initializable {
    // User
    @FXML
    private TextField txtUserSearch;
    @FXML
    private Button btnCancleUserSearch;
    @FXML
    private Label lbHeadUser;
    @FXML
    private Label lbUsername;
    @FXML
    private TextField txtUsername;
    @FXML
    private Label lbPassword;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lbConfirmPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private Label lbFirstname;
    @FXML
    private TextField txtFirstname;
    @FXML
    private Label lbLastname;
    @FXML
    private TextField txtLastname;
    @FXML
    private Label lbEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnAddUser;
    @FXML
    private Button btnSaveUser;
    @FXML
    private Button btnCancleEditUser;
    @FXML
    private TableView<User> tbUser;
    @FXML
    private TableColumn<User, Integer> colUserId;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private TableColumn<User, String> colFirstname;
    @FXML
    private TableColumn<User, String> colLastname;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colType;
    @FXML
    private Label lbUserSearch;
    @FXML
    private ChoiceBox<String> cbUserType;
    @FXML
    private Label msgUsername;
    @FXML
    private Label msgPassword;
    @FXML
    private Label msgConfirmPassword;
    @FXML
    private Label msgFirstname;
    @FXML
    private Label msgLastname;
    @FXML
    private Label msgEmail;
    @FXML
    private Label lbUserType;
    @FXML
    private Label msgUserType;
    @FXML
    private Label msgUserSearch;
    @FXML
    private ComboBox<String> cbUserSearch;
    
    // ============================================================
    // Movie
    @FXML
    private Label lbMovieSearch;
    @FXML
    private TextField txtMovieSearch;
    @FXML
    private Label msgMovieSearch;
    @FXML
    private ComboBox<String> cbMovieSearch;
    @FXML
    private Button btnCancleMovieSearch;
    @FXML
    private Label lbHeadMovie;
    @FXML
    private Label lbEnglishName;
    @FXML
    private TextField txtEnglishName;
    @FXML
    private Label lbThaiName;
    @FXML
    private TextField txtThaiName;
    @FXML
    private Label msgThaiName;
    @FXML
    private Label lbDirector;
    @FXML
    private TextField txtDirector;
    @FXML
    private Label msgDirector;
    @FXML
    private Label lbCast;
    @FXML
    private TextField txtCast;
    @FXML
    private Label msgCast;
    @FXML
    private Label lbSynopsis;
    @FXML
    private TextArea txtSynopsis;
    @FXML
    private Label msgSynopsis;
    @FXML
    private Label lbGenre;
    @FXML
    private Label msgGenre;
    @FXML
    private Label lbTime;
    @FXML
    private Label lbTimeHour;
    @FXML
    private ChoiceBox<String> cbTimeHour;
    @FXML
    private Label lbTimeMinute;
    @FXML
    private ChoiceBox<String> cbTimeMinute;
    private Label msgTime;
    @FXML
    private Label lbReleaseDate;
    @FXML
    private DatePicker datePickerReleaseDate;
    @FXML
    private Label msgReleaseDate;
    @FXML
    private Label lbPoster;
    @FXML
    private Button filePoster;
    @FXML
    private ImageView imagePoster;
    @FXML
    private Label msgPoster;
    @FXML
    private Label lbTrailer;
    @FXML
    private TextField txtTrailer;
    @FXML
    private Label msgTrailer;
    @FXML
    private Button btnAddMovie;
    @FXML
    private Button btnSaveMovie;
    @FXML
    private Button btnCancleEditMovie;
    @FXML
    private TableView<Movie> tbMovie;
    @FXML
    private TableColumn<Movie, Integer> colMovieId;
    @FXML
    private TableColumn<Movie, String> colEnglishName;
    @FXML
    private TableColumn<Movie, String> colThaiName;
    @FXML
    private TableColumn<Movie, String> colDirector;
    @FXML
    private TableColumn<Movie, String> colCast;
    @FXML
    private TableColumn<Movie, String> colSynopsis;
    @FXML
    private TableColumn<Movie, String> colGenre;
    @FXML
    private TableColumn<Movie, String> colTime;
    @FXML
    private TableColumn<Movie, String> colReleaseDate;
    @FXML
    private TableColumn<Movie, String> colPoster;
    @FXML
    private TableColumn<Movie, String> colTrailer;
    @FXML
    private Label msgEnglishName;
    @FXML
    private ChoiceBox<String> cbGenre;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Label msgHour;
    @FXML
    private Label msgMinute;
    
    // Cinema variable
    Cinema cinema;
    // User =======================================================================
    // User normal
    ObservableList<User> userList = FXCollections.observableArrayList();
    List<User> userListArray = new ArrayList<User>();
    // User search
    ObservableList<User> userSearchList = FXCollections.observableArrayList();
    List<User> userSearchListArray = new ArrayList<User>();
    
    // Movie ======================================================================
    // Movie normal
    ObservableList<Movie> movieList = FXCollections.observableArrayList();
    List<Movie> movieListArray = new ArrayList<Movie>();
    // Movie search
    ObservableList<Movie> movieSearchList = FXCollections.observableArrayList();
    List<Movie> movieSearchListArray = new ArrayList<Movie>();
    
    // Showtime ===================================================================
    // Showtime normal
    ObservableList<Showtime> showtimeList = FXCollections.observableArrayList();
    List<Showtime> showtimeListArray = new ArrayList<Showtime>();
    // Showtime search
    ObservableList<Showtime> showtimeSearchList = FXCollections.observableArrayList();
    List<Showtime> showtimeSearchListArray = new ArrayList<Showtime>();
    
    private File lastPath;
    private File selectedPoster;
    private String fileName = "";
    private Image posterImage;
    
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Label lbHeadShowtime1;
    @FXML
    private Label lbMovieSearch1;
    @FXML
    private ComboBox<String> cbShowtimeSearch1;
    @FXML
    private Label lbTheatreShow1;
    @FXML
    private ComboBox<String> cbTheatreSearch1;
    @FXML
    private Label lbSoundtrack1;
    @FXML
    private ComboBox<String> cbSoundtrack1;
    @FXML
    private Label lbIncreaseSeatPrice1;
    @FXML
    private TextField txtIncreaseSeatPrice1;
    @FXML
    private Label msgIncreaseSeatPrice1;
    @FXML
    private Label lbStartTime1;
    @FXML
    private Label lbTimeHourShow1;
    @FXML
    private ChoiceBox<String> cbTimeHourShow1;
    @FXML
    private Label lbTimeMinuteShow1;
    @FXML
    private ChoiceBox<String> cbTimeMinuteShow1;
    @FXML
    private Button btnAddShowtime1;
    @FXML
    private Button btnSaveShowtime1;
    @FXML
    private Button btnCancleEditShowtime1;
    @FXML
    private TableView<Showtime> tbShowtime1;
    @FXML
    private TableColumn<Showtime, Integer> colShowtimeId1;
    @FXML
    private TableColumn<Showtime, String> colMoviesName1;
    @FXML
    private TableColumn<Showtime, String> colTheatreShow1;
    @FXML
    private TableColumn<Showtime, String> colSoundtrackShow1;
    @FXML
    private TableColumn<Showtime, String> colPeriodShow1;
    @FXML
    private TableColumn<Showtime, Integer> colIncreaseSeatPrice1;
    @FXML
    private Label msgMovieSearch1;
    @FXML
    private Label msgTheatreShow1;
    

    public AdminMainController() {
        this.cinema = cinema.getInstance();
    }
    
    public boolean checkEmptyForm(String text, Label label, String textLabel){
        if (text.equals("")) {
            label.setText("Your " + textLabel + " is empty!");
            label.setTextFill(Color.rgb(210, 39, 30));
        } else {
            label.setText("Your " + textLabel + " is okay.");
            label.setTextFill(Color.rgb(21, 117, 84));
            return true;
        }
        return false;
    }
    
    // User ========================================================================================
    public void initUserCol(){
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
     
    public void loadUserData(){
        userList.clear();
        userListArray = cinema.getUserList();
        try {
            for (User user : userListArray) {
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tbUser.setItems(userList);
        
    }
    
    public void loadUserCombo(){
        ObservableList<String> userTypeOptions = FXCollections.observableArrayList("Customer","Staff","Admin");
        cbUserType.setItems(userTypeOptions);
        //cbUserType.setValue("Customer");
    }
    
    public void loadUserSearchCombo(){
        ObservableList<String> userSearchOptions = FXCollections.observableArrayList("Username","Firstname","Lastname","Email","Type");
        cbUserSearch.setItems(userSearchOptions);
        cbUserSearch.setValue("Username");
    }
    
    public void clearUserMessage(){
        msgUsername.setText(null);
        msgPassword.setText(null);
        msgConfirmPassword.setText(null);
        msgFirstname.setText(null);
        msgLastname.setText(null);
        msgEmail.setText(null);
        msgUserType.setText(null);
    }
    
    public void clearUserSearchMessage(){
        msgUserSearch.setText(null);
    }
    
    public void clearUserForm(){
        txtUsername.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
        txtFirstname.clear();
        txtLastname.clear();
        txtEmail.clear();
        cbUserType.setValue(null);
    }
    
    public void cancleUserEdit(){
        btnAddUser.setDisable(false);
        btnSaveUser.setDisable(true);
        btnCancleEditUser.setDisable(true);
        lbHeadUser.setText("Add new user");
        clearUserForm();
        editUserMode = false;
    }
    
    User selectedUserEdit; // global variable
    boolean editUserMode = false;
    int editUserId = 0;
    
    public void initUserEditMode(){
        selectedUserEdit = tbUser.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        if(selectedUserEdit == null){
            AlertMaker.showErrorMessage("No user selected", "Please select a user for edit.");
            return;
        }
        // Disable button to add & Enable button to save & Change head text to edit mode
        btnAddUser.setDisable(true);
        btnSaveUser.setDisable(false);
        btnCancleEditUser.setDisable(false);
        lbHeadUser.setText("Edit user id : " + selectedUserEdit.getId());
        
        txtUsername.setText(selectedUserEdit.getUsername());
        txtPassword.setText(selectedUserEdit.getPassword());
        txtConfirmPassword.setText(selectedUserEdit.getPassword());
        txtFirstname.setText(selectedUserEdit.getFirstname());
        txtLastname.setText(selectedUserEdit.getLastname());
        txtEmail.setText(selectedUserEdit.getEmail());
        cbUserType.setValue(selectedUserEdit.getType());
        clearUserMessage();
        editUserMode = true;
        editUserId = tbUser.getSelectionModel().getSelectedIndex();
    }
    
    public boolean checkExistUsername(String username, Label label, String textLabel){
        if (cinema.checkExistUsername(username)) {
            label.setText("Your " + textLabel + " is already used!");
            label.setTextFill(Color.rgb(210, 39, 30));
        } else {
            label.setText("Your " + textLabel + " is okay.");
            label.setTextFill(Color.rgb(21, 117, 84));
            return true;
        }
        return false;
    }
    
    public boolean checkExistEmail(String email, Label label, String textLabel){
        if (cinema.checkExistEmail(email)) {
            label.setText("Your " + textLabel + " is already used!");
            label.setTextFill(Color.rgb(210, 39, 30));
        } else {
            label.setText("Your " + textLabel + " is okay.");
            label.setTextFill(Color.rgb(21, 117, 84));
            return true;
        }
        return false;
    }
    
    public boolean checkPasswordMatch(String password, String confirmPassword, Label label, String textLabel){
        if (!password.equals(confirmPassword)) {
            label.setText("Your " + textLabel + " is not match!");
            label.setTextFill(Color.rgb(210, 39, 30));
        } else {
            label.setText("Your " + textLabel + " is okay.");
            label.setTextFill(Color.rgb(21, 117, 84));
            return true;
        }
        return false;
    }
    
    public static boolean isValidEmail(String email, Label label, String textLabel) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        if(!m.matches()){
            label.setText("Your " + textLabel + " is wrong format!");
            label.setTextFill(Color.rgb(210, 39, 30));   
        }else{
            label.setText("Your " + textLabel + " is okay.");
            label.setTextFill(Color.rgb(21, 117, 84));
            return true;
        }
        return false;
    }
    
    public boolean checkUserForm(String username, String password, String confirmPassword, String firstname, String lastname, String email, String userType){
        boolean checkUsername = checkEmptyForm(username, msgUsername, "username");
        if(checkUsername && !editUserMode){ 
            checkUsername = checkExistUsername(username, msgUsername, "username");
        }
        if(checkUsername && editUserMode){
            if(!username.equals(selectedUserEdit.getUsername())){ // if username same
                checkUsername = checkExistUsername(username, msgUsername, "username");
            }
        }
        
        boolean checkPassword = checkEmptyForm(password, msgPassword, "password");
        boolean checkConfirmPassword = checkEmptyForm(confirmPassword, msgConfirmPassword, "confirm password");
        if(checkPassword && checkConfirmPassword){ 
            checkConfirmPassword = checkPasswordMatch(password, confirmPassword, msgConfirmPassword, "confirm password");
        }
        
        boolean checkFirstname = checkEmptyForm(firstname, msgFirstname, "firstname");
        boolean checkLastname = checkEmptyForm(lastname, msgLastname, "lastname");
        
        boolean checkEmail = checkEmptyForm(email, msgEmail, "email");
        
        if(checkEmail){
            checkEmail = isValidEmail(email, msgEmail, "email");
            // in add mode
            if(checkEmail && !editUserMode){
               checkEmail = checkExistEmail(email, msgEmail, "email");
            }
            // in edit mode
            if(checkEmail && editUserMode){
                if(!email.equals(selectedUserEdit.getEmail())){ // if username same
                checkEmail = checkExistEmail(email, msgEmail, "email");
                } 
            }
        }
        
        if(cbUserType.getValue() != null){ userType = cbUserType.getValue(); }
        boolean checkUserType = checkEmptyForm(userType, msgUserType, "user type");
        
        return checkUsername && checkPassword && checkConfirmPassword && checkFirstname && checkLastname && checkEmail && checkUserType;      
    }
    
    public boolean checkUserSameValue(User editUser){
        boolean checkUsername = editUser.getUsername().equals(selectedUserEdit.getUsername());
        boolean checkPassword = true; // if text == it equal
        if(!txtPassword.getText().equals(selectedUserEdit.getPassword())){ // if text not same in box
//            System.out.println("Not same password");
            checkPassword = editUser.getPassword().equals(selectedUserEdit.getPassword()); // hash and check is it same?
        }
//        else{
//            System.out.println("Same password");
//            checkPassword = true;
//        }
        boolean checkFirstname = editUser.getFirstname().equals(selectedUserEdit.getFirstname());
        boolean checkLastname = editUser.getLastname().equals(selectedUserEdit.getLastname());
        boolean checkEmail = editUser.getEmail().equals(selectedUserEdit.getEmail());
        boolean checkUserType = editUser.getType().equals(selectedUserEdit.getType());
        
//        System.out.println("Username : " + checkUsername);
//        System.out.println("Password : " + checkPassword);
//        System.out.println("Firstname : " + checkFirstname);
//        System.out.println("Lastname : " + checkLastname);
//        System.out.println("Email : " + checkEmail);
//        System.out.println("UserType : " + checkUserType);
        return checkUsername && checkPassword && checkFirstname && checkLastname && checkEmail && checkUserType;
    }
    
    public boolean checkUserSearchForm(String search){
        boolean checkSearch = checkEmptyForm(search, msgUserSearch, "search");
        return checkSearch;
    }
    
    public void clearMovieSearchMessage(){
        msgMovieSearch.setText(null);
    }
    
    // Movie ========================================================================================
    public void initMovieCol(){
        colMovieId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEnglishName.setCellValueFactory(new PropertyValueFactory<>("englishName"));
        colThaiName.setCellValueFactory(new PropertyValueFactory<>("thaiName"));
        colDirector.setCellValueFactory(new PropertyValueFactory<>("director"));
        colCast.setCellValueFactory(new PropertyValueFactory<>("cast"));
        colSynopsis.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colReleaseDate.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        colPoster.setCellValueFactory(new PropertyValueFactory<>("poster"));
        colTrailer.setCellValueFactory(new PropertyValueFactory<>("trailer"));
    }
     
    public void loadMovieData(){
        movieList.clear();
        movieListArray = cinema.getMovieList();
        try {
            for (Movie movie : movieListArray) {
                movieList.add(movie);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tbMovie.setItems(movieList);
    }
    
    public void clearMovieMessage(){
        msgEnglishName.setText(null);
        msgThaiName.setText(null);
        msgDirector.setText(null);
        msgCast.setText(null);
        msgSynopsis.setText(null);
        msgGenre.setText(null);
        msgHour.setText(null);
        msgMinute.setText(null);
        msgReleaseDate.setText(null);
        msgPoster.setText(null);
        msgTrailer.setText(null);
    }
    
    public void loadMovieSearchCombo(){
        ObservableList<String> movieSearchOptions = FXCollections.observableArrayList("English Name","Thai Name","Director","Cast","Synopsis","Genre","Time","Release Date");
        cbMovieSearch.setItems(movieSearchOptions);
        cbMovieSearch.setValue("English Name");
    }
    
    public void loadMovieGenreCombo(){
        ObservableList<String> movieGenreOptions = FXCollections.observableArrayList("Action","Adventure","Comedy","Crime & Gangster","Drama","Epics/Historical","Horror",
                "Musicals/Dance","Science Fiction","War","Westerns");
        cbGenre.setItems(movieGenreOptions);
    }
    
    public void loadMovieHourCombo(){
        ArrayList<String> hour = new ArrayList<String>();
        for (int i = 0; i <= 10; i++) { 		      
            hour.add(String.valueOf(i));
        }   
        ObservableList<String> movieHourOptions = FXCollections.observableArrayList(hour);
        cbTimeHour.setItems(movieHourOptions);
    }
    
    public void loadMovieMinuteCombo(){
        ArrayList<String> minute = new ArrayList<String>();
        for (int i = 0; i <= 59; i++) { 		      
            minute.add(String.valueOf(i));
        }   
        ObservableList<String> movieMinuteOptions = FXCollections.observableArrayList(minute);
        cbTimeMinute.setItems(movieMinuteOptions);
    }
    
    public boolean checkMovieSearchForm(String search){
        boolean checkSearch = checkEmptyForm(search, msgMovieSearch, "search");
        return checkSearch;
    }
    
    public String convertDate(DatePicker datePicker){
        String formattedString = null;
        try{
        LocalDate ld = datePicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        formattedString = ld.format(formatter);
        //labelPickerReleaseDate.setText(formattedString);
        }catch(Exception e){
            System.out.println(e);
        }
        return formattedString;
    }
    
    public boolean checkMovieForm(String englishName, String thaiName, String director, String cast, String synopsis, String genre, String movieHour ,String movieMinute, String releaseDate, String poster, String trailer){
        boolean checkEnglishName = checkEmptyForm(englishName, msgEnglishName, "english name");
        boolean checkThaiName = checkEmptyForm(thaiName, msgThaiName, "thai name");
        boolean checkDirector = checkEmptyForm(director, msgDirector, "director");
        boolean checkCast = checkEmptyForm(cast, msgCast, "cast");
        boolean checkSynopsis = checkEmptyForm(synopsis, msgSynopsis, "synopsis");
        
        if(cbGenre.getValue() != null){ genre = cbGenre.getValue(); }
        boolean checkGenre = checkEmptyForm(genre, msgGenre, "genre");
        
        // check hour and minute
        if(cbTimeHour.getValue() != null){ movieHour = cbTimeHour.getValue(); }
        boolean checkHour = checkEmptyForm(movieHour, msgHour, "hour");
        
        if(cbTimeMinute.getValue() != null){ movieMinute = cbTimeMinute.getValue(); }
        boolean checkMinute = checkEmptyForm(movieMinute, msgMinute, "minute");
        
        if(datePickerReleaseDate.getValue() != null){ releaseDate = convertDate(datePickerReleaseDate); }
        boolean checkReleaseDate = checkEmptyForm(releaseDate, msgReleaseDate, "release date");
        
        boolean checkPoster = false;
        if(selectedPoster != null){ poster = selectedPoster.toURI().toString(); }
        if(!editMovieMode){
            checkPoster = checkEmptyForm(poster, msgPoster, "poster");
        }else{
            checkPoster = true;
        }
        
        boolean checkTrailer = checkEmptyForm(trailer, msgTrailer, "trailer");
        
        return checkEnglishName && checkThaiName && checkDirector && checkCast && checkSynopsis && checkGenre && checkHour && checkMinute && checkReleaseDate && checkPoster && checkTrailer;      
    }
    
    public void clearMovieForm(){
        txtEnglishName.clear();
        txtThaiName.clear();
        txtDirector.clear();
        txtCast.clear();
        txtSynopsis.clear();
        cbGenre.setValue(null);
        cbTimeHour.setValue(null);
        cbTimeMinute.setValue(null);
        datePickerReleaseDate.setValue(null);
        selectedPoster = null; // unset image select
        imagePoster.setImage(null);
        txtTrailer.clear();
    }
    
    Movie selectedMovieEdit; // global variable
    boolean editMovieMode = false;
    int editMovieId = 0;
    
    public void initMovieEditMode() throws ParseException{
        selectedMovieEdit = tbMovie.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        if(selectedMovieEdit == null){
            AlertMaker.showErrorMessage("No movie selected", "Please select a movie for edit.");
            return;
        }
        // Disable button to add & Enable button to save & Change head text to edit mode
        btnAddMovie.setDisable(true);
        btnSaveMovie.setDisable(false);
        btnCancleEditMovie.setDisable(false);
        
        lbHeadMovie.setText("Edit movie id : " + selectedMovieEdit.getId());
        
        txtEnglishName.setText(selectedMovieEdit.getEnglishName());
        txtThaiName.setText(selectedMovieEdit.getThaiName());
        txtDirector.setText(selectedMovieEdit.getDirector());
        txtCast.setText(selectedMovieEdit.getCast());
        txtSynopsis.setText(selectedMovieEdit.getSynopsis());
        cbGenre.setValue(selectedMovieEdit.getGenre());
        // split time with colon
        String time = selectedMovieEdit.getTime();
        String hourminute[] = time.split(":");
        String hour = hourminute[0];
        String minute = hourminute[1];
        cbTimeHour.setValue(hour);
        cbTimeMinute.setValue(minute);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String date = selectedMovieEdit.getReleaseDate();
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println(localDate);
        datePickerReleaseDate.setValue(localDate);
//        datePickerReleaseDate.setValue(localDate);
        
        // poster image
        posterImage = new Image(selectedMovieEdit.getPoster());
        imagePoster.setImage(posterImage);
        selectedPoster = new File(selectedMovieEdit.getPoster());

        txtTrailer.setText(selectedMovieEdit.getTrailer());
        
        clearMovieMessage();
        
        msgPoster.setText(selectedMovieEdit.getPoster());
        msgPoster.setTextFill(Color.BLACK);
        
        editMovieMode = true;
        editMovieId = tbMovie.getSelectionModel().getSelectedIndex();
    }
    
    public void cancleMovieEdit(){
        btnAddMovie.setDisable(false);
        btnSaveMovie.setDisable(true);
        btnCancleEditMovie.setDisable(true);
        lbHeadMovie.setText("Add new movie");
        clearMovieForm();
        editMovieMode = false;
    }
    
    public boolean checkMovieSameValue(Movie editMovie){
        boolean checkEnglishName = editMovie.getEnglishName().equals(selectedMovieEdit.getEnglishName());
        boolean checkThaiName = editMovie.getThaiName().equals(selectedMovieEdit.getThaiName());
        boolean checkDirector = editMovie.getDirector().equals(selectedMovieEdit.getDirector());
        boolean checkCast = editMovie.getCast().equals(selectedMovieEdit.getCast());
        boolean checkSynopsis = editMovie.getSynopsis().equals(selectedMovieEdit.getSynopsis());
        boolean checkGenre = editMovie.getGenre().equals(selectedMovieEdit.getGenre());
        boolean checkTime = editMovie.getTime().equals(selectedMovieEdit.getTime());
        
        boolean checkReleaseDate = editMovie.getReleaseDate().equals(selectedMovieEdit.getReleaseDate());
        System.out.println("MSG "+msgPoster.getText());
        System.out.println("EDIT "+selectedMovieEdit.getPoster());
        boolean checkPoster = msgPoster.getText().equals(selectedMovieEdit.getPoster());
        boolean checkTrailer = editMovie.getTrailer().equals(selectedMovieEdit.getTrailer());
        
        System.out.println(String.valueOf(checkEnglishName));
        System.out.println(String.valueOf(checkThaiName));
        System.out.println(String.valueOf(checkDirector));
        System.out.println(String.valueOf(checkCast));
        System.out.println(String.valueOf(checkSynopsis));
        System.out.println(String.valueOf(checkGenre));
        System.out.println(String.valueOf(checkTime));
        System.out.println(String.valueOf(checkReleaseDate));
        System.out.println(String.valueOf(checkPoster));
        System.out.println(String.valueOf(checkTrailer));

        return checkEnglishName && checkThaiName && checkDirector && checkCast && checkSynopsis && checkGenre && checkTime && checkReleaseDate && checkPoster && checkTrailer;    
    }
    
    
    
    // Showtime=====================================================================
    public void initShowtimeCol(){
        colShowtimeId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMoviesName1.setCellValueFactory(new PropertyValueFactory<>("movie"));
        colTheatreShow1.setCellValueFactory(new PropertyValueFactory<>("theatre"));
        colIncreaseSeatPrice1.setCellValueFactory(new PropertyValueFactory<>("increaseSeatPrice"));
        colSoundtrackShow1.setCellValueFactory(new PropertyValueFactory<>("soundtrack"));
        colPeriodShow1.setCellValueFactory(new PropertyValueFactory<>("period"));
    }
     
    public void loadShowtimeData(){
        showtimeList.clear();
        showtimeListArray = cinema.getShowtimeList();
        try {
            for (Showtime showtime : showtimeListArray) {
                showtimeList.add(showtime);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tbShowtime1.setItems(showtimeList);
    }
    
    public void clearShowtimeMessage(){
        msgIncreaseSeatPrice1.setText(null);
        msgMovieSearch1.setText(null);
        msgTheatreShow1.setText(null);
    }
    
    public void loadShowtimeSearchMovieCombo(){
        ObservableList<String> movieName = FXCollections.observableArrayList();
        movieListArray = cinema.getMovieList();
        try {
            for (Movie movie : movieListArray) {
                movieName.add(movie.getEnglishName());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cbShowtimeSearch1.setItems(movieName);
        cbShowtimeSearch1.setValue("None");
    }
    
    public void loadTheatreShowtimeCombo(){
        ObservableList<String> theatreNum = FXCollections.observableArrayList("1","2","3");
        cbTheatreSearch1.setItems(theatreNum);
        cbTheatreSearch1.setValue("None");
    }
    
    public void loadSoundtrackCombo(){
        ObservableList<String> soundTrack = FXCollections.observableArrayList("En","Th");
        cbSoundtrack1.setItems(soundTrack);
        cbSoundtrack1.setValue("-");
    }
    
    public void loadStartHourCombo(){
        ArrayList<String> hour = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) { 		      
            hour.add(String.valueOf(i));
        }   
        ObservableList<String> showHourOptions = FXCollections.observableArrayList(hour);
        cbTimeHourShow1.setItems(showHourOptions);
        cbTimeHourShow1.setValue("None");
    }
    
    public void loadStartMinCombo(){
        ArrayList<String> min = new ArrayList<String>();
        for (int i = 0; i <= 59; i++) { 		      
            min.add(String.valueOf(i));
        }   
        ObservableList<String> showMinOptions = FXCollections.observableArrayList(min);
        cbTimeMinuteShow1.setItems(showMinOptions);
        cbTimeMinuteShow1.setValue("None");
    }
     
    public void clearShowtimeForm(){
        txtIncreaseSeatPrice1.clear();
        cbShowtimeSearch1.setValue("None");
        cbTheatreSearch1.setValue("None");
        cbSoundtrack1.setValue("-");
        cbTimeHourShow1.setValue("None");
        cbTimeMinuteShow1.setValue("None");
    }
    
    Showtime selectedShowtimeEdit; // global variable
    boolean editShowtime = false;
    int editShowtimeId = 0;
    
    public void initShowtimeEditMode() throws ParseException{
        selectedShowtimeEdit = tbShowtime1.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        if(selectedShowtimeEdit == null){
            AlertMaker.showErrorMessage("No showtime selected", "Please select a showtime for edit.");
            return;
        }
        // Disable button to add & Enable button to save & Change head text to edit mode
        btnAddShowtime1.setDisable(true);
        btnSaveShowtime1.setDisable(false);
        btnCancleEditShowtime1.setDisable(false);
        
        lbHeadShowtime1.setText("Edit showtime id : " + String.valueOf(selectedShowtimeEdit.getId()));
        
        txtIncreaseSeatPrice1.setText(String.valueOf(selectedShowtimeEdit.getIncreaseSeatPrice()));
        cbShowtimeSearch1.setValue(selectedShowtimeEdit.getMovie());
        cbTheatreSearch1.setValue(selectedShowtimeEdit.getTheatre());
        cbSoundtrack1.setValue(selectedShowtimeEdit.getSoundtrack());
        // split time with colon
        String period = selectedShowtimeEdit.getPeriod();
        String houraminute[] = period.split(":");
        String hour = houraminute[0];
        String min = houraminute[1];
        cbTimeHourShow1.setValue(hour);
        cbTimeMinuteShow1.setValue(min);
        
        clearShowtimeMessage();
        
        editShowtime = true;
        editShowtimeId = tbShowtime1.getSelectionModel().getSelectedIndex();
    }
    
    public void cancleShowtimeEdit(){
        btnAddShowtime1.setDisable(false);
        btnSaveShowtime1.setDisable(true);
        btnCancleEditShowtime1.setDisable(true);
        lbHeadShowtime1.setText("Add new Showtime");
        clearShowtimeForm();
        editShowtime = false;
    }
    
    public boolean checkShowtimeForm(String movieName, String theatre, String increaseSP,String min,String hour,String sound){
        boolean checkIncrease = checkEmptyForm(increaseSP, msgIncreaseSeatPrice1, "increaseSeatPrice");
        
        if(cbShowtimeSearch1.getValue() != null){ movieName = cbShowtimeSearch1.getValue(); }
        boolean checkMoviename = checkEmptyForm(movieName, msgMovieSearch1, "moive name");
        
        if(cbTheatreSearch1.getValue() != null){ theatre = cbTheatreSearch1.getValue(); }
        boolean checktheatre = checkEmptyForm(theatre, msgTheatreShow1, "theatre");
        
        // check hour and minute
        if(cbTimeHourShow1.getValue() != null){ hour = cbTimeHourShow1.getValue(); }
//        boolean checkHour = checkEmptyForm(hour, msgHour, "hour");
        
        if(cbTimeMinuteShow1.getValue() != null){ min = cbTimeMinuteShow1.getValue(); }
//        boolean checkMinute = checkEmptyForm(min, msgMinute, "minute");

        if(cbSoundtrack1.getValue() != null){ sound = cbSoundtrack1.getValue(); }
        
        return checkIncrease && checkMoviename &&  checktheatre;      
    }
    
    
//    public boolean checkMovieSameValue(Movie editMovie){
//        boolean checkEnglishName = editMovie.getEnglishName().equals(selectedMovieEdit.getEnglishName());
//        boolean checkThaiName = editMovie.getThaiName().equals(selectedMovieEdit.getThaiName());
//        boolean checkDirector = editMovie.getDirector().equals(selectedMovieEdit.getDirector());
//        boolean checkCast = editMovie.getCast().equals(selectedMovieEdit.getCast());
//        boolean checkSynopsis = editMovie.getSynopsis().equals(selectedMovieEdit.getSynopsis());
//        boolean checkGenre = editMovie.getGenre().equals(selectedMovieEdit.getGenre());
//        boolean checkTime = editMovie.getTime().equals(selectedMovieEdit.getTime());
//        
//        boolean checkReleaseDate = editMovie.getReleaseDate().equals(selectedMovieEdit.getReleaseDate());
//        System.out.println("MSG "+msgPoster.getText());
//        System.out.println("EDIT "+selectedMovieEdit.getPoster());
//        boolean checkPoster = msgPoster.getText().equals(selectedMovieEdit.getPoster());
//        boolean checkTrailer = editMovie.getTrailer().equals(selectedMovieEdit.getTrailer());
//        
//        System.out.println(String.valueOf(checkEnglishName));
//        System.out.println(String.valueOf(checkThaiName));
//        System.out.println(String.valueOf(checkDirector));
//        System.out.println(String.valueOf(checkCast));
//        System.out.println(String.valueOf(checkSynopsis));
//        System.out.println(String.valueOf(checkGenre));
//        System.out.println(String.valueOf(checkTime));
//        System.out.println(String.valueOf(checkReleaseDate));
//        System.out.println(String.valueOf(checkPoster));
//        System.out.println(String.valueOf(checkTrailer));
//
//        return checkEnglishName && checkThaiName && checkDirector && checkCast && checkSynopsis && checkGenre && checkTime && checkReleaseDate && checkPoster && checkTrailer;    
//    }
    
    
    
    
    
    
    
    // Initial =====================================================================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tab select
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(1); // select movie
        // User =========================================================
        // User load data
        initUserCol();
        loadUserData();
        // Clear Message text
        clearUserMessage();
        // User combobox type load
        loadUserCombo();
        // Clear user search message
        clearUserSearchMessage();
        // User search combo
        loadUserSearchCombo();
        // Disable cancle user search
        btnCancleUserSearch.setDisable(true);
        // ===============================================================
        
        
        // Movie =========================================================
        initMovieCol();
        loadMovieData();
        clearMovieMessage();
        // Load combo
        loadMovieSearchCombo(); // search combo
        loadMovieGenreCombo(); // genre combo
        loadMovieHourCombo(); // hour combo
        loadMovieMinuteCombo(); // minute combo
        btnCancleMovieSearch.setDisable(true);
        clearMovieSearchMessage();
        // ===============================================================
        
        
        // Showtime ======================================================
        initShowtimeCol();
        loadShowtimeData();
        clearShowtimeMessage();
        // Load combo
        loadShowtimeSearchMovieCombo(); // movie
        loadTheatreShowtimeCombo(); // theatre
        loadSoundtrackCombo(); // soundtrack
        loadStartHourCombo();
        loadStartMinCombo();
        // ================================================================


    }    

    // User =======================================================================
    @FXML
    private void handleUserSearch(ActionEvent event) {
        String userSearch = txtUserSearch.getText();
        boolean isSearchOK = checkUserSearchForm(userSearch);
        if(isSearchOK){
            // System.out.println("Search Ready");
            String searchUserOf = cbUserSearch.getValue();
            // System.out.println(searchUserOf.toLowerCase());
            cinema.searchUser(userSearch, searchUserOf.toLowerCase()); // search
            clearUserSearchMessage();
            // clear list
            userSearchList.clear();
            
            userSearchListArray = cinema.getUserSearchList();
            try {
                for (User user : userSearchListArray) {
                    userSearchList.add(user);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            if(userSearchList.size() > 0){ // Found
                tbUser.setItems(userSearchList);
                msgUserSearch.setText("Found!");
                msgUserSearch.setTextFill(Color.rgb(21, 117, 84));
                btnCancleUserSearch.setDisable(false);
                cancleUserEdit(); // after search cancle edit
            }else{
                msgUserSearch.setText("Not found!");
                msgUserSearch.setTextFill(Color.rgb(210, 39, 30));
                btnCancleUserSearch.setDisable(true);
                loadUserData();
            }
        }else{
            loadUserData();
        }
    }

    @FXML
    private void handleCancleUserSearch(ActionEvent event) {
        // load normal table without search
        loadUserData();
        clearUserSearchMessage();
        txtUserSearch.setText(null);
        btnCancleUserSearch.setDisable(true);
    }

    @FXML
    private void handleAddUser(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String email = txtEmail.getText();
        String userType = ""; // global
        
        boolean isUserOK = checkUserForm(username, password, confirmPassword, firstname, lastname, email, userType);
        System.out.println("Edit mode " + String.valueOf(editUserMode));
        if(isUserOK){
            userType = cbUserType.getValue(); // get value from combobox
            User newUser = new User(username, password, firstname, lastname, email, userType);
            AlertMaker.showSimpleAlert("OK", newUser.toString());
            cinema.addUser(newUser); // add new user
            clearUserMessage(); // clear label
            clearUserForm(); // clear text form
            loadUserData(); // load new list after add
        }
    }

    @FXML
    private void handleSaveUser(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String email = txtEmail.getText();
        String userType = ""; // global
        
        boolean isUserOK = checkUserForm(username, password, confirmPassword, firstname, lastname, email, userType);
        System.out.println("Edit user mode " + String.valueOf(editUserMode));
        
        if(isUserOK){
            System.out.println("Edit mode start...");
            userType = cbUserType.getValue(); // get value from combobox
//            System.out.println(userType);
            
            User editUser = new User(username, password, firstname, lastname, email, userType);
//            System.out.println(editUser.toString());
//            System.out.println("=======================");
//            System.out.println(selectedUserEdit.toString());
            boolean isUserSame = checkUserSameValue(editUser);
//            System.out.println("Is same "+String.valueOf(isUserSame));
//            System.out.println("Textbox Password : " + password);
//            System.out.println("======================");
//            System.out.println("Selected Password : "+selectedUserEdit.getPassword());
            if(!isUserSame){ // have change
                cinema.editUser(selectedUserEdit.getId(), editUser);
                System.out.println("Not same");
                loadUserData(); // load new list after add
                // update edit select to new information
                selectedUserEdit = tbUser.getItems().get(editUserId);
                AlertMaker.showSimpleAlert("Saved!", "Save information completed");
                
            }else{
                AlertMaker.showSimpleAlert("No change!", "No changed for this user information");
            }
            
        }
    }

    @FXML
    private void handleCancleEditUser(ActionEvent event) {
        cancleUserEdit();
    }

    @FXML
    private void handleUserRefresh(ActionEvent event) {
        loadUserData();
    }
    
    @FXML
    private void handleUserEdit(ActionEvent event) {
        initUserEditMode();
        System.out.println("Selected edit id : "+editUserId);
    }

    @FXML
    private void handleUserDelete(ActionEvent event) {
        User selectedUserDelete = tbUser.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        if(selectedUserDelete == null){
            AlertMaker.showErrorMessage("No user selected", "Please select a user for delete.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting member");
        alert.setContentText("Are you sure want to delete the member "+ selectedUserDelete.getUsername() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK){
            cinema.deleteUser(selectedUserDelete.getId());
            cancleUserEdit();
            loadUserData();
        }
    }

    @FXML
    private void handleMouseClickUserEdit(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            initUserEditMode();
            //System.out.println(selectedUserEdit);    
            System.out.println("Selected edit user id : "+editUserId);
        }
    }

    
    // ==========================================================================
    // Movie
    @FXML
    private void handleMovieSearch(ActionEvent event) {
        String movieSearch = txtMovieSearch.getText();
        boolean isSearchOK = checkMovieSearchForm(movieSearch);
        if(isSearchOK){
            // System.out.println("Search Ready");
            String searchMovieOf = cbMovieSearch.getValue();
            String searchMovie = searchMovieOf.replaceAll("\\s+","");
            //System.out.println(searchMovie.substring(0, 1).toLowerCase() + searchMovie.substring(1));
            cinema.searchMovie(movieSearch, searchMovie.substring(0, 1).toLowerCase() + searchMovie.substring(1)); // search
            clearMovieSearchMessage();
            // clear list
            movieSearchList.clear();
            
            movieSearchListArray = cinema.getMovieSearchList();
            try {
                for (Movie movie : movieSearchListArray) {
                    movieSearchList.add(movie);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            if(movieSearchList.size() > 0){ // Found
                tbMovie.setItems(movieSearchList);
                msgMovieSearch.setText("Found!");
                msgMovieSearch.setTextFill(Color.rgb(21, 117, 84));
                btnCancleMovieSearch.setDisable(false);
                cancleMovieEdit(); // after search cancle edit
            }else{
                msgMovieSearch.setText("Not found!");
                msgMovieSearch.setTextFill(Color.rgb(210, 39, 30));
                btnCancleMovieSearch.setDisable(true);
                loadMovieData();
            }
        }else{
            loadMovieData();
        }
    }

    @FXML
    private void handleCancleMovieSearch(ActionEvent event) {
        // load normal table without search
        loadMovieData();
        clearMovieSearchMessage();
        txtMovieSearch.setText(null);
        btnCancleMovieSearch.setDisable(true);
    }

    @FXML
    private void handleFilePoster(ActionEvent event) {
        // เปิด Dialog รับแต่ Image
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        if (lastPath != null) {
           fileChooser.setInitialDirectory(lastPath);
        }
        selectedPoster = fileChooser.showOpenDialog(null);
        if (selectedPoster != null) {
           msgPoster.setText(selectedPoster.getName()); // Select file name
           msgPoster.setTextFill(Color.BLACK);
           posterImage = new Image(selectedPoster.toURI().toString());
           imagePoster.setImage(posterImage);
           lastPath = selectedPoster.getParentFile(); 
        }
        else {
           msgPoster.setText("");
           msgPoster.setTextFill(Color.BLACK);
           imagePoster.setImage(null);
           selectedPoster = null;
        }
    }

    @FXML
    private void handleAddMovie(ActionEvent event) {
        String englishName = txtEnglishName.getText();
        String thaiName = txtThaiName.getText();
        String director = txtDirector.getText();
        String cast = txtCast.getText();
        String synopsis = txtSynopsis.getText();
        String genre = "";
        String hour = "";
        String minute = "";
        String releaseDate = "";
        String poster = "";
        String trailer = txtTrailer.getText();
        
        boolean isMovieOK = checkMovieForm(englishName, thaiName, director, cast, synopsis, genre, hour, minute, releaseDate, poster, trailer);
        if(isMovieOK){
            System.out.println("Add movie"); 
            Movie movie = new Movie(
                    txtEnglishName.getText(),
                    txtThaiName.getText(),
                    txtDirector.getText(),
                    txtCast.getText(),
                    txtSynopsis.getText(),
                    cbGenre.getValue(),
                    cbTimeHour.getValue() + ":" +cbTimeMinute.getValue(),
                    convertDate(datePickerReleaseDate),
                    selectedPoster.toURI().toString(),
                    txtTrailer.getText());
            
            cinema.addMovie(movie);
            clearMovieMessage(); // clear label
            clearMovieForm(); // clear text form
            loadMovieData(); // load new list after add
            AlertMaker.showSimpleAlert("Add Complete", movie.toString());
        }
    }

    @FXML
    private void handleSaveMovie(ActionEvent event) {
        String englishName = txtEnglishName.getText();
        String thaiName = txtThaiName.getText();
        String director = txtDirector.getText();
        String cast = txtCast.getText();
        String synopsis = txtSynopsis.getText();
        String genre = "";
        String hour = "";
        String minute = "";
        String releaseDate = "";
        String poster = "";
        String trailer = txtTrailer.getText();
        
        boolean isMovieOK = checkMovieForm(englishName, thaiName, director, cast, synopsis, genre, hour, minute, releaseDate, poster, trailer);
        System.out.println("Edit movie mode " + String.valueOf(editMovieMode));
        
        if(isMovieOK){
            System.out.println("Edit movie"); 
            Movie editMovie = new Movie(
                    txtEnglishName.getText(),
                    txtThaiName.getText(),
                    txtDirector.getText(),
                    txtCast.getText(),
                    txtSynopsis.getText(),
                    cbGenre.getValue(),
                    cbTimeHour.getValue() + ":" +cbTimeMinute.getValue(),
                    convertDate(datePickerReleaseDate),
                    selectedPoster.toURI().toString(),
                    txtTrailer.getText());
            
            boolean isSame = checkMovieSameValue(editMovie);
            if(!isSame){
                System.out.println("Not same");
                cinema.editMovie(selectedMovieEdit.getId(), editMovie);
                loadMovieData(); // load new list after add
                // update edit select to new information
                selectedMovieEdit = tbMovie.getItems().get(editMovieId);
                AlertMaker.showSimpleAlert("Saved!", "Save information completed");
            }else{
                AlertMaker.showSimpleAlert("No change!", "No changed for this movie information");
            }
        }
            
//            cinema.addMovie(movie);
//            clearMovieMessage(); // clear label
//            clearMovieForm(); // clear text form
//            loadMovieData(); // load new list after add
//            AlertMaker.showSimpleAlert("Add Complete", movie.toString());
    }

    @FXML
    private void handleCancleEditMovie(ActionEvent event) {
        cancleMovieEdit();
    }

    @FXML
    private void handleMovieRefresh(ActionEvent event) {
        loadMovieData();
    }

    @FXML
    private void handleMovieEdit(ActionEvent event) throws ParseException {
            initMovieEditMode(); 
            System.out.println("Selected edit movie id : "+editMovieId);
    }

    @FXML
    private void handleMovieDelete(ActionEvent event) {
        Movie selectedMovieDelete = tbMovie.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        if(selectedMovieDelete == null){
            AlertMaker.showErrorMessage("No movie selected", "Please select a movie for delete.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting movie");
        alert.setContentText("Are you sure want to delete the movie "+ selectedMovieDelete.getEnglishName()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK){
            cinema.deleteMovie(selectedMovieDelete.getId());
            loadMovieData();
            cancleMovieEdit();
            clearMovieMessage();
        }
    }

    @FXML
    private void handleMouseClickMovieEdit(MouseEvent event) throws ParseException {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            initMovieEditMode();
            //System.out.println(selectedUserEdit);    
            System.out.println("Selected edit movie id : "+editMovieId);
        }
    }


    // Showtime ================================================================
    
    @FXML
    private void handleAddShowtime(ActionEvent event) {
        String movieName = "";
        String incSp = txtIncreaseSeatPrice1.getText();
        String theatre = "";
        String hour = "";
        String min = "";
        String soundtrack = "";
        
        boolean isShowtimeOK = checkShowtimeForm(movieName,theatre,incSp,hour,min,soundtrack);
        if(isShowtimeOK){
            System.out.println("Add showtime");
            Showtime st = new Showtime(
                    cbShowtimeSearch1.getValue(),
                    cbTheatreSearch1.getValue(),
                    txtIncreaseSeatPrice1.getText(),
                    cbSoundtrack1.getValue(),
                    cbTimeHourShow1.getValue()+":"+cbTimeMinuteShow1.getValue());
            cinema.addShowtime(st);
            System.out.println("YaY111");
            clearShowtimeMessage(); // clear label
            System.out.println("YaY222");
            clearShowtimeForm(); // clear text form
            System.out.println("YaY333");
            loadShowtimeData(); // load new list after add
            System.out.println("YaY444");
            AlertMaker.showSimpleAlert("Add Complete", st.toString());
        }
    }
    

    @FXML
    private void handleSaveShowtime(ActionEvent event) {
        String movieName = "";
        String incSp = txtIncreaseSeatPrice1.getText();
        String theatre = "";
        String hour = "";
        String min = "";
        String soundtrack = "";
        
        boolean isShowtimeOK = checkShowtimeForm(movieName,theatre,incSp,hour,min,soundtrack);
        System.out.println("Edit movie mode " + String.valueOf(editShowtime));
        if(isShowtimeOK){
            System.out.println("Edit showtime");
            Showtime editSt = new Showtime(
                    cbShowtimeSearch1.getValue(),
                    cbTheatreSearch1.getValue(),
                    txtIncreaseSeatPrice1.getText(),
                    cbSoundtrack1.getValue(),
                    cbTimeHourShow1.getValue()+":"+cbTimeMinuteShow1.getValue());
            
            cinema.editShowtime(selectedShowtimeEdit.getId(), editSt);
            loadShowtimeData(); // load new list after add
            // update edit select to new information
            selectedShowtimeEdit = tbShowtime1.getItems().get(editShowtimeId);
            AlertMaker.showSimpleAlert("Saved!", "Save information completed");
            
        }
        
    }

    @FXML
    private void handleCancleEditShowtime(ActionEvent event) {
        cancleShowtimeEdit();
    }

    @FXML
    private void handleMouseClickShowtimeEdit(MouseEvent event) throws ParseException {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            initShowtimeEditMode();
            //System.out.println(selectedUserEdit);    
            System.out.println("Selected edit Show id : "+editShowtimeId);
        }
    }
    
}
