package cinema.admin;

import cinema.Booking;
import cinema.CinemaController;
import cinema.Movie;
import cinema.Promotion;
import cinema.PromotionController;
import cinema.Showtime;
import cinema.Theatre;
import cinema.UserController;
import cinema.User;
import cinema.ui.AlertMaker;
import com.jfoenix.controls.JFXTabPane;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminMainController implements Initializable{
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
    @FXML
    private Button btnUserPassword;
    
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
    @FXML
    private TableColumn<User, Double> colMoney;
    // ===========================================================================
    // Promotion
    
    @FXML
    private Label lbPromotionSearch;
    @FXML
    private TextField txtPromotionSearch;
    @FXML
    private Label msgPromotionSearch;
    @FXML
    private Button btnCancelPromotionSearch;
    @FXML
    private Label lbHeadPromotion;
    @FXML
    private Label lbPromotion;
    @FXML
    private TextField txtPromotion;
    @FXML
    private Label msgPromotion;
    @FXML
    private Label lbDescription;
    @FXML
    private TextField txtDescription;
    @FXML
    private Label msgDescription;
    @FXML
    private Label lbDiscount;
    @FXML
    private TextField txtDiscount;
    @FXML
    private Label msgDiscount;
    @FXML
    private Label lbDate;
    @FXML
    private DatePicker pickerDate;
    @FXML
    private Label msgDate;
    @FXML
    private Button btnAddPromotion;
    @FXML
    private Button btnSavePromotion;
    @FXML
    private Button btnCancelPromotion;
    @FXML
    private TableView<Promotion> tbPromotion;
    @FXML
    private MenuItem handleUserDelete1;   
    @FXML
    private TableColumn<Promotion, Integer> colPromotionId;
    @FXML
    private TableColumn<Promotion, String> colPromotionName;
    @FXML
    private TableColumn<Promotion, String> colDate;
    @FXML
    private TableColumn<Promotion, String> colDescription;
    @FXML
    private TableColumn<Promotion, Double> colDiscount;
    
    // Cinema login
    // =======================================================================
    @FXML
    private Menu userLogin;
    // ===========================================================================
    // Theatre
    @FXML
    private TextField txtTheatre;
    @FXML
    private Label msgTheatre;
    @FXML
    private Button btnAddTheatre;
    @FXML
    private Button btnSaveTheatre;
    @FXML
    private Button btnCancelTheatre;
    @FXML
    private TableView<Theatre> tbTheatre;
    @FXML
    private TableColumn<Theatre, Integer> colTheatreId;
    @FXML
    private TableColumn<Theatre, Integer> colTheatreNumber;
    @FXML
    private Label lbHeadTheatre;
    
    // Showtime
    // ===============================================================================
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
    private TableColumn<Showtime, Movie> colMoviesName1;
    @FXML
    private TableColumn<Showtime, Theatre> colTheatreShow1;
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
    @FXML
    private Label msgTimeShowtime;
    @FXML
    private Label lbSoundtrack11;
    @FXML
    private ComboBox<String> cbSystem;
    @FXML
    private TableColumn<Showtime, String> colShowtimeSystem;
    @FXML
    private TableColumn<Showtime, String> colSubtitle;
    // Showtime Date
    @FXML
    private DatePicker datePickerShowtime;
    @FXML
    private Label msgShowtimeDate;
    @FXML
    private TableColumn<Showtime, String> colShowtimeDate;
    @FXML
    private ComboBox<String> cbTheatreType;
    @FXML
    private TableColumn<Theatre, String> colTheatreType;
    // Booking
    // ==========================================================
    @FXML
    private Text txtSummary;
    @FXML
    private TableView<Booking> tbBooking;
    @FXML
    private TableColumn<Booking, Integer> colBookingID;
    @FXML
    private TableColumn<Booking, String> colBookingCreated;
    @FXML
    private TableColumn<Booking, String> colBookingUpdate;
    @FXML
    private TableColumn<Booking, String> colBookingShowtime;
    @FXML
    private TableColumn<Booking, String> colBookingUser;
    @FXML
    private TableColumn<Booking, Promotion> colBookingPromotion;
    @FXML
    private TableColumn<Booking, Boolean> colBookingStatus;
    @FXML
    private TableColumn<Booking, String> colBookingSeat;
    @FXML
    private TableColumn<Booking, Double> colBookingTotal;
    
    
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
    // Promotion ======================================================================
    // Promotion Normal
    ObservableList<Promotion> promotionList = FXCollections.observableArrayList();
    List<Promotion> promotionListArray = new ArrayList<Promotion>();
    // Promotion Search
    ObservableList<Promotion> promotionSearchList = FXCollections.observableArrayList();
    List<Promotion> promotionSearchListArray = new ArrayList<Promotion>();
    // Theatre ======================================================================
    // Theatre Normal
    ObservableList<Theatre> theatreList = FXCollections.observableArrayList();
    List<Theatre> theatreListArray = new ArrayList<Theatre>();
    // Showtime ===================================================================
    // Showtime normal
    ObservableList<Showtime> showtimeList = FXCollections.observableArrayList();
    List<Showtime> showtimeListArray = new ArrayList<Showtime>();
    // Showtime search
    ObservableList<Showtime> showtimeSearchList = FXCollections.observableArrayList();
    List<Showtime> showtimeSearchListArray = new ArrayList<Showtime>();
    // Booking List ====================================================================
    ObservableList<Booking> bookingList = FXCollections.observableArrayList();
    List<Booking> bookingListArray = new ArrayList<Booking>();
    
    private File lastPath;
    private File selectedPoster;
    private String fileName = "";
    private Image posterImage;
    
    
    private UserController uc;
    private PromotionController pc;
    private CinemaController cc;
    @FXML
    private MenuBar menuAdmin;
    
    
    public AdminMainController() {
        this.uc = uc.getInstance();
        this.pc = pc.getInstance();
        this.cc = cc.getInstance();
    }
    
    // Initial =====================================================================
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(uc.getIsLogin()){
            userLogin.setText(uc.getLoginUser().getEmail());
        }else{
            userLogin.setText("Not Login");
            userLogin.setDisable(true);
        }
        
        // Tab select
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(4); // select theatre
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
        
        // Promotion --------------------------------------------
        initPromotionCol();
        loadPromotionData();
        clearPromotionMessage();
        btnCancelPromotionSearch.setDisable(true);
        clearPromotionSearchMessage();

        // Theatre
        initTheatreCol();
        loadTheatreData();
        clearTheatreMessage();
        loadTheatreTypeCombo();
        
        // Showtime ======================================================
        initShowtimeCol();
        loadShowtimeData();
        clearShowtimeMessage();
//        // Load combo
        loadShowtimeSearchMovieCombo(); // movie
        loadTheatreShowtimeCombo(); // theatre
        loadSoundtrackCombo(); // soundtrack
        loadStartHourCombo();
        loadStartMinCombo();
        cbSystem.setDisable(true);
        //loadSystemCombo();
        
        
//        Theatre t = tc.getTheatre(3);
//        Movie m = mc.getMovie(4);
//        Showtime s = new Showtime(m, t, "3D", "TH", "11:11", 0.0);
//        sc.addShowtime(s);
        initBookingCol();
        loadBookingData();
        setBookingTotal();
    }   
    
    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        Parent parent;
        parent = FXMLLoader.load(getClass().getResource("/cinema/ui/auth/Login.fxml"));
        Scene parentScene = new Scene(parent);
        Stage window = (Stage)menuAdmin.getScene().getWindow();
        window.setScene(parentScene);
        window.show();

        uc.setIsLogin(false);
        uc.unsetLoginUser();
        

System.out.println("Logout");
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
        colMoney.setCellValueFactory(new PropertyValueFactory<>("money"));
    }
    public void loadUserData(){
        userList.clear();
        userListArray = uc.getUserList();
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
        clearUserMessage();
        editUserMode = false;
        btnUserPassword.setDisable(true);
        
        txtUsername.setDisable(false);
        txtPassword.setDisable(false);
        txtPassword.setDisable(false);
        txtConfirmPassword.setDisable(false);
        txtFirstname.setDisable(false);
        txtLastname.setDisable(false);
        txtEmail.setDisable(false);
        cbUserType.setDisable(false);
        editUserPassword = false;
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
        txtUsername.setDisable(false);
        txtPassword.setDisable(false);
        txtPassword.setDisable(true);
        txtConfirmPassword.setDisable(true);
        txtFirstname.setDisable(false);
        txtLastname.setDisable(false);
        cbUserType.setDisable(false);
        
        
        // Disable button to add & Enable button to save & Change head text to edit mode
        btnAddUser.setDisable(true);
        btnSaveUser.setDisable(false);
        btnCancleEditUser.setDisable(false);
        lbHeadUser.setText("Edit user id : " + selectedUserEdit.getId());
        
        txtUsername.setText(selectedUserEdit.getUsername());
        txtPassword.setText(selectedUserEdit.getPassword());
        // Disable txt Password
        txtPassword.setDisable(true);
        txtConfirmPassword.setDisable(true);
        btnUserPassword.setDisable(false);
        
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
        if (uc.checkExistUsername(username)) {
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
        if (uc.checkExistEmail(email)) {
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
    
    public boolean checkUserEditForm(String username, String firstname, String lastname, String email, String userType){
        boolean checkUsername = checkEmptyForm(username, msgUsername, "username");
        if(checkUsername && !editUserMode){ 
            checkUsername = checkExistUsername(username, msgUsername, "username");
        }
        if(checkUsername && editUserMode){
            if(!username.equals(selectedUserEdit.getUsername())){ // if username same
                checkUsername = checkExistUsername(username, msgUsername, "username");
            }
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
        
        return checkUsername && checkFirstname && checkLastname && checkEmail && checkUserType;      
    }
    
    public boolean checkUserPasswordForm(String password, String confirmPassword){
        boolean checkPassword = checkEmptyForm(password, msgPassword, "password");
        boolean checkConfirmPassword = checkEmptyForm(confirmPassword, msgConfirmPassword, "confirm password");
        if(checkPassword && checkConfirmPassword){ 
            checkConfirmPassword = checkPasswordMatch(password, confirmPassword, msgConfirmPassword, "confirm password");
        }
        
        return checkPassword && checkConfirmPassword;      
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
        movieListArray = cc.getMovieList();
        try {
            for (Movie movie : movieListArray) {
                movieList.add(movie);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tbMovie.setItems(movieList);
        loadShowtimeSearchMovieCombo();
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
                "Musicals/Dance","Science Fiction","Romantic","War","Westerns");
        cbGenre.setItems(movieGenreOptions);
    }
    public void loadMovieHourCombo(){
        ArrayList<String> hour = new ArrayList<String>();
        for (int i = 0; i <= 10; i++) { 		      
            if(i<10){
                hour.add("0"+String.valueOf(i));
            }else{
                hour.add(String.valueOf(i));
            }
        }   
        ObservableList<String> movieHourOptions = FXCollections.observableArrayList(hour);
        cbTimeHour.setItems(movieHourOptions);
    }
    public void loadMovieMinuteCombo(){
        ArrayList<String> minute = new ArrayList<String>();
        for (int i = 0; i <= 59; i++) { 		      
            if(i<10){
                minute.add("0"+String.valueOf(i));
            }else{
                minute.add(String.valueOf(i));
            }
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
        InputStream image = getClass().getResourceAsStream("/cinema/posterImage/"+selectedMovieEdit.getPoster());
        File imageFile = new File("/cinema/posterImage/"+selectedMovieEdit.getPoster());
        
        posterImage = new Image(image);
        imagePoster.setImage(posterImage);
        
        // Selected file from old path
        selectedPoster = imageFile;

        txtTrailer.setText(selectedMovieEdit.getTrailer());
        
        clearMovieMessage();
        
        msgPoster.setText(imageFile.getName());
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
        clearMovieMessage();
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
    
    // Promotion --------------------------------------------------------------
    public void initPromotionCol(){
        colPromotionId.setCellValueFactory(new PropertyValueFactory<>("promotionID"));
        colPromotionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("remainingDate"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        
    }
    public void loadPromotionData() {
        promotionList.clear();
        promotionListArray = pc.getPromotionList();
        try {
            for (Promotion p : promotionListArray) {
                promotionList.add(p);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        tbPromotion.setItems(promotionList);
    }  
    public void clearPromotionMessage() {
        msgPromotion.setText(null);
        msgDescription.setText(null);
        msgDate.setText(null);
        msgDiscount.setText(null);
    }
    public void clearPromotionForm() {
        txtPromotion.clear();
        txtDescription.clear();
        pickerDate.setValue(null);
        txtDiscount.clear();
    }
    public boolean isNumeric(String str, Label label, String textLabel){
        if (!str.matches("-?\\d+(\\.\\d+)?")) {
            label.setText("Your " + textLabel + " is not number!");
            label.setTextFill(Color.rgb(210, 39, 30));
        } else {
            label.setText("Your " + textLabel + " is okay.");
            label.setTextFill(Color.rgb(21, 117, 84));
            return true;
        }
        return false;
    }
    public boolean checkPromotionForm(String name, String description, String date, String discount) {
        boolean checkName = checkEmptyForm(name, msgPromotion, "promotion name");
        boolean checkDesc = checkEmptyForm(description, msgDescription, "description");
        boolean checkDisc = checkEmptyForm(discount, msgDiscount, "discount");
        if(checkDisc){
            checkDisc = isNumeric(discount, msgDiscount, "discount");
        }
        if (date == null) {date = "";}
        boolean checkDate = checkEmptyForm(date, msgDate, "remaining date");
        return checkName && checkDesc && checkDate && checkDisc;
    }
    
    Promotion selectedPromotionEdit;
    boolean editPromotionMode = false;
    int editPromotionID = 0;
    
    public void initPromotionEdit() throws ParseException {
        selectedPromotionEdit = tbPromotion.getSelectionModel().getSelectedItem();
        if(selectedPromotionEdit == null) {
            AlertMaker.showErrorMessage("No promotion selected", "Please select a promotion to edit");
            return;
        }
        
        // Disable add button
        btnAddPromotion.setDisable(true);
        btnSavePromotion.setDisable(false);
        btnCancelPromotion.setDisable(false);
        
        lbHeadPromotion.setText("Edit promotion id : " + selectedPromotionEdit.getPromotionID());
        
        txtPromotion.setText(selectedPromotionEdit.getName());
        txtDescription.setText(selectedPromotionEdit.getDescription());
        txtDiscount.setText(selectedPromotionEdit.getDiscount()+"");
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate localdate = LocalDate.parse(selectedPromotionEdit.getRemainingDate(), formatter);
        System.out.println(localdate);
        pickerDate.setValue(localdate);
        
        clearPromotionMessage();
        editPromotionMode = true;
        editPromotionID = tbPromotion.getSelectionModel().getSelectedIndex();
    }  
    public void cancelPromotionEdit() {
        btnAddPromotion.setDisable(false);
        btnSavePromotion.setDisable(true);
        btnCancelPromotion.setDisable(true);
        
        lbHeadPromotion.setText("Add new promotion");
        clearPromotionForm();
        editPromotionMode = false;
    }
    public boolean checkPromotionSearchForm(String s) {
        boolean checkSearch = checkEmptyForm(s, msgPromotionSearch, "search");
        return checkSearch;
    }
    public void clearPromotionSearchMessage(){
        msgPromotionSearch.setText(null);
    }
    
    /*
    initTheatreCol();
    loadTheatreData();
    
    */
    // Theatre --------------------------------------------------------------
    public boolean checkInt(String str, Label label, String textLabel){
        if(!str.matches("[0-9]+")){
            label.setText("Your " + textLabel + " is not integer!");
            label.setTextFill(Color.rgb(210, 39, 30));
        }else{
            label.setText("Your " + textLabel + " is okay.");
            label.setTextFill(Color.rgb(21, 117, 84));
            return true;
        }
        return false;
    }
    public void initTheatreCol(){
        colTheatreId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTheatreNumber.setCellValueFactory(new PropertyValueFactory<>("theatreNumber"));
        colTheatreType.setCellValueFactory(new PropertyValueFactory<>("theatreType"));
    }
    public void loadTheatreData() {
        theatreList.clear();
        theatreListArray = cc.getTheatreList();
        try {
            for (Theatre t : theatreListArray) {
                theatreList.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        tbTheatre.setItems(theatreList);
        loadTheatreShowtimeCombo();
    }  
    public void clearTheatreMessage() {
        msgTheatre.setText(null);
    }
    public void clearTheatreForm() {
        txtTheatre.clear();
        cbTheatreType.setValue("2D/3D");
    }
    
    public void loadTheatreTypeCombo(){
        ObservableList<String> theatreTypeOptions = FXCollections.observableArrayList("2D/3D","4D");
        cbTheatreType.setItems(theatreTypeOptions);
        cbTheatreType.setValue("2D/3D");
    }
    
    public boolean checkTheatreForm(String number) {
        boolean checkTheatre = checkEmptyForm(number, msgTheatre, "theatre number");
        if(checkTheatre){
            checkTheatre = checkInt(number, msgTheatre, "theatre number");
        }
        return checkTheatre;
    }
    
    Theatre selectedTheatreEdit;
    boolean editTheatreMode = false;
    int editTheatreID = 0;
    
    public void initTheatreEdit() {
        selectedTheatreEdit = tbTheatre.getSelectionModel().getSelectedItem();
        if(selectedTheatreEdit == null) {
            AlertMaker.showErrorMessage("No theatre selected", "Please select a theatre to edit");
            return;
        }
        
        // Disable add button
        btnAddTheatre.setDisable(true);
        btnSaveTheatre.setDisable(false);
        btnCancelTheatre.setDisable(false);
        
        lbHeadTheatre.setText("Edit theatre id : " + selectedTheatreEdit.getId());
        
        txtTheatre.setText(String.valueOf(selectedTheatreEdit.getTheatreNumber()));
        cbTheatreType.setValue(selectedTheatreEdit.getTheatreType());
        
        clearTheatreMessage();
        editTheatreMode = true;
        editTheatreID = tbTheatre.getSelectionModel().getSelectedIndex();
    }  
    public void cancelTheatreEdit() {
        btnAddTheatre.setDisable(false);
        btnSaveTheatre.setDisable(true);
        btnCancelTheatre.setDisable(true);
        
        lbHeadTheatre.setText("Add new theatre");
        clearTheatreForm();
        editTheatreMode = false;
    }
    
    
    // Showtime=======================================================================
     // Showtime=====================================================================
    public void initShowtimeCol(){
        colShowtimeId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMoviesName1.setCellValueFactory(new PropertyValueFactory<>("movie"));
        colTheatreShow1.setCellValueFactory(new PropertyValueFactory<>("theatre"));
        colIncreaseSeatPrice1.setCellValueFactory(new PropertyValueFactory<>("increaseSeatPrice"));
        colSoundtrackShow1.setCellValueFactory(new PropertyValueFactory<>("soundtrack"));
        colShowtimeSystem.setCellValueFactory(new PropertyValueFactory<>("system"));
        colPeriodShow1.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colShowtimeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSubtitle.setCellValueFactory(new PropertyValueFactory<>("subtitle"));
    }   
    public void loadShowtimeData(){
        showtimeList.clear();
        showtimeListArray = cc.getShowtimeList();
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
        msgTimeShowtime.setText(null);
        msgShowtimeDate.setText(null);
    }
     
    public void loadShowtimeSearchMovieCombo(){
        ObservableList<String> movieShowtimeList = FXCollections.observableArrayList();
        movieListArray = cc.getMovieList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate releaseDate;
        long tempDayShowing; // ฉายมาแล้วกี่วัน (Return เป็น long)
        int dayShowing;// เปลี่ยน tempDayShowing เป็น int และไปบวกกับ showingDay
        int showingDay = 14; // จำนวนวันที่จะให้หนังอยู่ในโรง
        LocalDate today = LocalDate.now();
        try {
            for (Movie movie : movieListArray) {
                String date = movie.getReleaseDate();
                releaseDate = LocalDate.parse(date, formatter);

                tempDayShowing = today.until(releaseDate, ChronoUnit.DAYS); // ฉายมาแล้วกี่วัน ( จะได้ค่าติดลบจำนวนวัน )
                dayShowing = (int) tempDayShowing + showingDay; // เหลือเวลาฉายอีกกี่วัน
                if(today.until(releaseDate, ChronoUnit.DAYS) > 0 && today.until(releaseDate, ChronoUnit.DAYS) <= 7) {
                    // เป็น coming soon
                    movieShowtimeList.add(movie.getId() + " : " + movie.getEnglishName());
                }
                else {
                    // เป็น now showing
                    if(dayShowing >= 0) {
                        movieShowtimeList.add(movie.getId() + " : " + movie.getEnglishName());
                    }
                }
//                movieShowtimeList.add(movie.getId() + " : " + movie.getEnglishName());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        cbShowtimeSearch1.setItems(movieShowtimeList);
    }
    
    
    public void loadSoundtrackCombo(){
        ObservableList<String> soundTrack = FXCollections.observableArrayList("EN","TH");
        cbSoundtrack1.setItems(soundTrack);
        cbSoundtrack1.setValue("TH");
    }
   
    public void loadStartHourCombo(){
        ArrayList<String> hour = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) { 
            if(i<10){
                hour.add("0"+String.valueOf(i));
            }else{
                hour.add(String.valueOf(i));
            }
        }   
        ObservableList<String> showHourOptions = FXCollections.observableArrayList(hour);
        cbTimeHourShow1.setItems(showHourOptions);
//        cbTimeHourShow1.setValue("None");
    }
    public void loadStartMinCombo(){
        ArrayList<String> min = new ArrayList<String>();
        for (int i = 0; i <= 55; i++) { 
            if(i % 5 == 0){
                if(i<10){
                    min.add("0"+String.valueOf(i));
                }else{
                    min.add(String.valueOf(i));
                }
            }
        }   
        ObservableList<String> showMinOptions = FXCollections.observableArrayList(min);
        cbTimeMinuteShow1.setItems(showMinOptions);
        cbTimeMinuteShow1.setValue("");
    }
     
    public void loadTheatreShowtimeCombo(){
        ObservableList<String> theatreNum = FXCollections.observableArrayList();
        List<Theatre> theatre = new ArrayList<Theatre>();
        theatre = cc.getTheatreList();
        
        try {
            for (Theatre t : theatre) {
                theatreNum.add(t.getId()+ " : Theatre " + t.getTheatreNumber());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        cbTheatreSearch1.setItems(theatreNum);
        //cbTheatreSearch1.setValue("1");
    }
    
    // Check theatre and load combo
    boolean changeTheatre = false; // t คือ มีการเปลี่ยน f คือไม่มีการเปลี่ยน
    public void loadSystemCombo(){
        ObservableList<String> system = FXCollections.observableArrayList();
        Theatre t;
        // Find theatre
        if(!editShowtime){
            String[] selectTheId = cbTheatreSearch1.getValue().split(" :");
            //System.out.println("Theatre ID: " + selectTheId[0]);
            selectTheId[0].replaceAll("\\s+","");
            int theatre_id = Integer.valueOf(selectTheId[0]);
            t = cc.getTheatre(Integer.valueOf(theatre_id));
            selectShowtimeSystem(system,t);
        }else{
            if(!changeTheatre){
                t = selectedShowtimeEdit.getTheatre();
                selectShowtimeSystem(system,t);
            }else{
                String[] selectTheId = cbTheatreSearch1.getValue().split(" :");
                //System.out.println("Theatre ID: " + selectTheId[0]);
                selectTheId[0].replaceAll("\\s+","");
                int theatre_id = Integer.valueOf(selectTheId[0]);
                t = cc.getTheatre(Integer.valueOf(theatre_id));
                selectShowtimeSystem(system,t);
            }
        }
        
        // No Select

    }
    
    public void selectShowtimeSystem(ObservableList<String> system, Theatre t){
        if(t.getTheatreType().equals("2D/3D")){
            system = FXCollections.observableArrayList("2D","3D");
            cbSystem.setItems(system);
            cbSystem.setValue("2D");
        }else{
            system = FXCollections.observableArrayList("4DX");
            cbSystem.setItems(system);
            cbSystem.setValue("4DX");
        }
    }
     
    public void clearShowtimeForm(){
        txtIncreaseSeatPrice1.clear();
        cbShowtimeSearch1.setValue(null);
        cbTheatreSearch1.setValue(null);
        cbSoundtrack1.setValue("TH");
        cbTimeHourShow1.setValue(null);
        cbTimeMinuteShow1.setValue(null);
        //cbSystem.setValue("2D");
        datePickerShowtime.setValue(null);
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
        String selectMov = selectedShowtimeEdit.getMovie().getId()  + " : " + selectedShowtimeEdit.getMovie().getEnglishName();
        cbShowtimeSearch1.setValue(selectMov);
        String[] selectMovId = selectMov.split(":");
        System.out.println("Movie ID: " + selectMovId[0]);
        
        String selectThe = selectedShowtimeEdit.getTheatre().getId()+ " : Theatre " + selectedShowtimeEdit.getTheatre().getTheatreNumber();
        cbTheatreSearch1.setValue(selectThe);
        String[] selectTheId = selectThe.split(":");
        System.out.println("Theatre ID: " + selectTheId[0]);
       
        cbSoundtrack1.setValue(selectedShowtimeEdit.getSoundtrack());
        // split time with colon
        String period = selectedShowtimeEdit.getStartTime();
        String houraminute[] = period.split(":");
        String hour = houraminute[0];
        
        String min = houraminute[1];
        String minUse = min.substring(0,2);
        System.out.println("Min"+minUse);
        cbTimeHourShow1.setValue(hour);
        cbTimeMinuteShow1.setValue(minUse);
        
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String date = selectedShowtimeEdit.getDate();
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println(localDate);
        datePickerShowtime.setValue(localDate);
        
        clearShowtimeMessage();
        
        // load new combo
        changeTheatre = false;
        editShowtime = true;
        loadSystemCombo();
        cbSystem.setValue(selectedShowtimeEdit.getSystem());
        
        editShowtimeId = tbShowtime1.getSelectionModel().getSelectedIndex();
    }
    public void cancleShowtimeEdit(){
        btnAddShowtime1.setDisable(false);
        btnSaveShowtime1.setDisable(true);
        btnCancleEditShowtime1.setDisable(true);
        lbHeadShowtime1.setText("Add new Showtime");
        clearShowtimeForm();
        editShowtime = false;
        cbSystem.setDisable(true);
        //loadSystemCombo();
    }
    public boolean checkShowtimeForm(String movieName, String theatre, String increaseSP,String date,String min,String hour,String sound){
        boolean checkIncrease = checkEmptyForm(increaseSP, msgIncreaseSeatPrice1, "increase seat price");
        if(checkIncrease){
            checkIncrease = isNumeric(increaseSP, msgIncreaseSeatPrice1, "increase seat price");
            if(checkIncrease){
                double inc_double = Double.parseDouble(txtIncreaseSeatPrice1.getText());
                if (inc_double < 0) {
                    msgIncreaseSeatPrice1.setText("Your increase seat price is less than 0!");
                    msgIncreaseSeatPrice1.setTextFill(Color.rgb(210, 39, 30));
                    checkIncrease = false;
                }else{
                    msgIncreaseSeatPrice1.setText("Your increase seat price is okay.");
                    msgIncreaseSeatPrice1.setTextFill(Color.rgb(21, 117, 84));
                    checkIncrease = true;
                }
            }
        }
        
        if(datePickerShowtime.getValue() != null){ date = convertDate(datePickerShowtime); }
        boolean checkShowtimeDate = checkEmptyForm(date, msgShowtimeDate, "showtime date");

        
        if(cbShowtimeSearch1.getValue() != null){ movieName = cbShowtimeSearch1.getValue(); }
        boolean checkMoviename = checkEmptyForm(movieName, msgMovieSearch1, "moive name");
        
        if(cbTheatreSearch1.getValue() != null){ theatre = cbTheatreSearch1.getValue(); }
        boolean checktheatre = checkEmptyForm(theatre, msgTheatreShow1, "theatre");
        
        // check hour and minute
        if(cbTimeHourShow1.getValue() != null){ hour = cbTimeHourShow1.getValue(); }
        if(cbTimeMinuteShow1.getValue() != null){ min = cbTimeMinuteShow1.getValue(); }
        
        boolean checkHour = checkEmptyForm(hour, msgTimeShowtime, "time");
        boolean checkMinute = checkEmptyForm(min, msgTimeShowtime, "time");
        boolean checkTime = checkHour && checkMinute;
        if (checkTime) {} else { checkTime = checkEmptyForm("", msgTimeShowtime, "time"); }
        

        if(cbSoundtrack1.getValue() != null){ sound = cbSoundtrack1.getValue(); }
        
        return checkIncrease && checkMoviename &&  checktheatre && checkShowtimeDate && checkTime;      
    }
    
    // Booking =====================================================================
    public void initBookingCol(){
        colBookingID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookingCreated.setCellValueFactory(new PropertyValueFactory<>("BookingCreateDatetime"));
        colBookingUpdate.setCellValueFactory(new PropertyValueFactory<>("BookingUpdateDatetime"));
        colBookingShowtime.setCellValueFactory(new PropertyValueFactory<>("ShowtimeDetail"));
        colBookingUser.setCellValueFactory(new PropertyValueFactory<>("UserDetail"));
        colBookingPromotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        colBookingStatus.setCellValueFactory(new PropertyValueFactory<>("isCancel"));
        colBookingSeat.setCellValueFactory(new PropertyValueFactory<>("BookedSeatString"));
        colBookingTotal.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
    }   
    public void loadBookingData(){
        bookingList.clear();
        bookingListArray = cc.getBookingList();
        try {
            for (Booking booking : bookingListArray) {
                bookingList.add(booking);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        tbBooking.setItems(bookingList);
    }
    public void setBookingTotal(){
        txtSummary.setText("Total Income : " + cc.getSumTotal() + " Baht");
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
            uc.searchUser(userSearch, searchUserOf.toLowerCase()); // search
            clearUserSearchMessage();
            // clear list
            userSearchList.clear();
            
            userSearchListArray = uc.getUserSearchList();
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
            User newUser = new User(username, password, firstname, lastname, email, userType,0.0);
            newUser.setEncryptPassword();
            AlertMaker.showSimpleAlert("OK", newUser.toString());
            uc.addUser(newUser); // add new user
            clearUserMessage(); // clear label
            clearUserForm(); // clear text form
            loadUserData(); // load new list after add
        }
    }
    @FXML
    private void handleSaveUser(ActionEvent event) {
        String username = txtUsername.getText();
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String email = txtEmail.getText();
        String userType = ""; // global
        
        boolean isUserOK = checkUserEditForm(username, firstname, lastname, email, userType);
        
        System.out.println("Edit user mode " + String.valueOf(editUserMode));
        
        if(isUserOK){
            System.out.println("Edit mode start...");
            userType = cbUserType.getValue(); // get value from combobox
           
            User editUser = new User(username, null, firstname, lastname, email, userType,0.0);

            boolean isUserSame = checkUserSameValue(editUser);
            if(!isUserSame){ // have change
                uc.editUser(selectedUserEdit.getId(), editUser);
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
            uc.deleteUser(selectedUserDelete.getId());
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
    
    boolean editUserPassword = false;
    public void initChangeUserPassword(){
        txtUsername.setDisable(true);
            txtPassword.setDisable(true);

            txtPassword.clear();
            txtConfirmPassword.clear();
            txtPassword.setDisable(false);
            txtConfirmPassword.setDisable(false);

            txtFirstname.setDisable(true);
            txtLastname.setDisable(true);
            txtEmail.setDisable(true);
            cbUserType.setDisable(true);
            editUserPassword = true;
            btnSaveUser.setDisable(true);
            
    }
    @FXML
    private void handleChangePassword(ActionEvent event) {
        if(editUserPassword == false){
            initChangeUserPassword();
            System.out.println("Pre edit");
            editUserPassword = true;
        }else{
            
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            boolean isPassOK = checkUserPasswordForm(password, confirmPassword);
            if (isPassOK) {
                System.out.println("Ready for edit");
                //editUserPassword = false;
                initChangeUserPassword();
                clearUserMessage();
                uc.editUserPassword(selectedUserEdit.getId(), User.getEncryptPassword(password));
                loadUserData(); // load new list after add
                AlertMaker.showSimpleAlert("Ok", "Password Ok");
            }
            
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
            cc.searchMovie(movieSearch, searchMovie.substring(0, 1).toLowerCase() + searchMovie.substring(1)); // search
            clearMovieSearchMessage();
            // clear list
            movieSearchList.clear();
            
            movieSearchListArray = cc.getMovieSearchList();
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
           InputStream image = getClass().getResourceAsStream("/cinema/posterImage/"+selectedPoster.getName());
           posterImage = new Image(image);
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
            List<Movie> checkMovieList = cc.getMovieList();
            boolean checkAlreadyExists = false;
            for (Movie movie : checkMovieList) {
                if(txtEnglishName.getText().equals(movie.getEnglishName()) && !checkAlreadyExists) {
                    checkAlreadyExists = true;
                }
            }
            if(checkAlreadyExists){
                AlertMaker.showErrorMessage("Add Movie Error", "This movie is already exists");
                clearMovieMessage();
                clearMovieForm();
            }
            else {
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
                        selectedPoster.getName(),
                        //selectedPoster.toURI().toString(),
                        txtTrailer.getText());

                cc.addMovie(movie);
                clearMovieMessage(); // clear label
                clearMovieForm(); // clear text form
                loadMovieData(); // load new list after add
                AlertMaker.showSimpleAlert("Add Complete", movie.toString());
            }
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
        
        List<Movie> checkMovieList = cc.getMovieList();
        boolean checkAlreadyExists = false;
        for (Movie movie : checkMovieList) {
            if(txtEnglishName.getText().equals(movie.getEnglishName()) && !checkAlreadyExists) {
                if(selectedMovieEdit.getId() == movie.getId()) {
                    continue;
                }
                else {
                    checkAlreadyExists = true;
                }
            }
        }
        
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
                    selectedPoster.getName(),
                    // selectedPoster.toURI().toString(),
                    txtTrailer.getText());
            
            boolean isSame = checkMovieSameValue(editMovie);
            if(!isSame){
                if(checkAlreadyExists) {
                    AlertMaker.showErrorMessage("Save Movie Error", "This Movie is already exists (English name is same with others)");
                }
                else {
                    System.out.println("Not same");
                    cc.editMovie(selectedMovieEdit.getId(), editMovie);
                    loadMovieData(); // load new list after add
                    // update edit select to new information
                    selectedMovieEdit = tbMovie.getItems().get(editMovieId);
                    AlertMaker.showSimpleAlert("Saved!", "Save information completed");
                    cancleMovieEdit();
                } 
            } else {
                AlertMaker.showSimpleAlert("No change!", "No changed for this movie information");
            }
        }
            
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
            cc.deleteMovie(selectedMovieDelete.getId());
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

    // Promotion handle -------------------------------------------------------
    @FXML
    private void handleAddPromotion(ActionEvent event) {
        String promotionName = txtPromotion.getText();
        String description = txtDescription.getText();
        String discount = txtDiscount.getText();
        String date = convertDate(pickerDate);
        System.out.println("Add promotion");
        System.out.println(date);
        
        boolean isAddable = checkPromotionForm(promotionName,description,date,discount);
        
        double value_discount = Double.parseDouble(discount);
        //        double value_discount = 0.23;
        
        if (value_discount < 0) {
            msgDiscount.setText("Discount cannot be under 0 !");
            msgDiscount.setTextFill(Color.rgb(210, 39, 30));
            isAddable = false;
        }
        
        List<Promotion> checkPromotionList = pc.getPromotionList();
        boolean isPromotionExists = false;
        for (Promotion promotion : checkPromotionList) {
            if(txtPromotion.getText().equals(promotion.getName()) && !isPromotionExists) {
                isPromotionExists = true;
            }
        }
        if(isAddable){
            if(isPromotionExists) {
                AlertMaker.showErrorMessage("Add Promotion Error","This promotion is already exists");
                clearPromotionMessage();
                clearPromotionForm();
            }
            else {
                Promotion newPromotion = new Promotion(promotionName, description,date , value_discount);
                pc.addPromotion(newPromotion);
                clearPromotionMessage(); // clear label
                clearPromotionForm(); // clear text form
                loadPromotionData(); // load new list after add
                AlertMaker.showSimpleAlert("OK",newPromotion.toString());
            }
        }
    }
    @FXML
    private void handleCanclePromotionSearch(ActionEvent event) {
        loadPromotionData();
        clearPromotionSearchMessage();
        txtPromotionSearch.setText(null);
        btnCancelPromotionSearch.setDisable(true);
    }
    @FXML
    private void handleSavePromotion(ActionEvent event) {
        String promotionName = txtPromotion.getText();
        String description = txtDescription.getText();
        String discount = txtDiscount.getText();
        String date = convertDate(pickerDate);
        
        double value_discount = Double.parseDouble(discount);
        
        boolean isAddable = checkPromotionForm(promotionName,description,date,discount);
        
        List<Promotion> checkPromotionList = pc.getPromotionList();
        boolean isPromotionExists = false;
        for (Promotion promotion : checkPromotionList) {
            if(txtPromotion.getText().equals(promotion.getName()) && !isPromotionExists) {
                if(selectedPromotionEdit.getPromotionID() == promotion.getPromotionID()) {
                    continue;
                }
                else {
                    isPromotionExists = true;
                }
            }
        }
        
        if (value_discount < 0) {
            msgDiscount.setText("Discount cannot be under 0 !");
            msgDiscount.setTextFill(Color.rgb(210, 39, 30));
            isAddable = false;
        }
        if(isAddable) {
            if(isPromotionExists) {
                AlertMaker.showErrorMessage("Save Promotion Error", "This Promotion is already exists (Promotion name is same with others)");
            }
            else {
                System.out.println("Edit mode start...");
                Promotion editPromotion = new Promotion(promotionName, description,date , value_discount);
                pc.editPromotion(selectedPromotionEdit.getPromotionID(), editPromotion);
                cancelPromotionEdit();
            }
        }
        loadPromotionData();
        clearPromotionMessage();
    }
    @FXML
    private void handleCancleEditPromotion(ActionEvent event) {
        cancelPromotionEdit();
        clearPromotionMessage();
    }
    @FXML
    private void handlePromotionRefresh(ActionEvent event) {
        loadPromotionData();
    }
    @FXML
    private void handlePromotionEdit(ActionEvent event) throws ParseException{
        initPromotionEdit();
    }
    @FXML
    private void handlePromotionDelete(ActionEvent event) {
        Promotion selectedPromotionDelete = tbPromotion.getSelectionModel().getSelectedItem();
        if(selectedPromotionDelete == null) {
            AlertMaker.showErrorMessage("No promotion selected", "Please select a promotion to delete.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Promotion");
        alert.setContentText("Are you sure want to delete the promotion "+ selectedPromotionDelete.getName()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK){
            pc.deletePromotion(selectedPromotionDelete.getPromotionID());
            cancelPromotionEdit();
            loadPromotionData();
        }
    }
    @FXML
    private void handleMouseClickPromotionEdit(MouseEvent event) throws ParseException{
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            initPromotionEdit();
        }
    }
    @FXML
    private void handlePromotionSearch(ActionEvent event) {
        String promotionSearch = txtPromotionSearch.getText();
        boolean isSearch = checkPromotionSearchForm(promotionSearch);
        if(isSearch) {
            pc.searchPromotion(promotionSearch,"name");
            clearUserSearchMessage();
            promotionSearchList.clear();
            promotionSearchListArray = pc.getPromotionSearchList();
            try {
                for (Promotion promotion : promotionSearchListArray) {
                    promotionSearchList.add(promotion);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            if(promotionSearchList.size() > 0){ // Found
                tbPromotion.setItems(promotionSearchList);
                msgPromotionSearch.setText("Found!");
                msgPromotionSearch.setTextFill(Color.rgb(21, 117, 84));
                btnCancelPromotionSearch.setDisable(false);
                cancelPromotionEdit(); // after search cancle edit
            }else{
                msgPromotionSearch.setText("Not found!");
                msgPromotionSearch.setTextFill(Color.rgb(210, 39, 30));
                btnCancelPromotionSearch.setDisable(true);
                loadPromotionData();
            }
        }
        else {
            System.out.println("Not found");
            loadPromotionData();
        }
    }

    
    // Theatre handle -------------------------------------------------------
    @FXML
    private void handleTheatreRefresh(ActionEvent event) {
        loadTheatreData();
    }
    @FXML
    private void handleTheatreEdit(ActionEvent event)  {
        initTheatreEdit();
    }
    @FXML
    private void handleTheatreDelete(ActionEvent event) {
        Theatre selectedTheatreDelete = tbTheatre.getSelectionModel().getSelectedItem();
        if(selectedTheatreDelete == null) {
            AlertMaker.showErrorMessage("No theatre selected", "Please select a theatre to delete.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Theatre");
        alert.setContentText("Are you sure want to delete the theatre "+ selectedTheatreDelete.getTheatreNumber()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK){
            cc.deleteTheatre(selectedTheatreDelete.getId());
            cancelTheatreEdit();
            loadTheatreData();
        }
    }
    @FXML
    private void handleMouseClickTheatreEdit(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            initTheatreEdit();
        }
    }
    @FXML
    private void handleAddTheatre(ActionEvent event) {
        String theatreNum = txtTheatre.getText();
        
        boolean isAddable = checkTheatreForm(theatreNum);
        
        int theatre = Integer.parseInt(theatreNum);
        
        List<Theatre> checkTheatreExists = cc.getTheatreList();
        boolean isTheatreExists = false;
        for (Theatre checkTheatre : checkTheatreExists) {
            if((Integer.parseInt(txtTheatre.getText()) == (checkTheatre.getTheatreNumber())) && !isTheatreExists) {
                isTheatreExists = true;
            }
        }
        
        if(isAddable){
            if(isTheatreExists) {
                AlertMaker.showErrorMessage("Add Theatre Error", "This theatre number is already exists");
                clearTheatreMessage();
                clearTheatreForm();
            }
            else {
                Theatre newTheatre = new Theatre(theatre,cbTheatreType.getValue());
                cc.addTheatre(newTheatre);
                clearTheatreMessage(); // clear label
                clearTheatreForm(); // clear text form
                loadTheatreData(); // load new list after add
            }
        }
    }
    @FXML
    private void handleSaveTheatre(ActionEvent event) {
        String theatreNum = txtTheatre.getText();
        
        boolean isAddable = checkTheatreForm(theatreNum);
        int theatre = Integer.parseInt(theatreNum);
        Theatre editTheatre = new Theatre(theatre,cbTheatreType.getValue());
        
        List<Theatre> checkTheatreExists = cc.getTheatreList();
        boolean isTheatreExists = false;
        for (Theatre checkTheatre : checkTheatreExists) {
            if((Integer.parseInt(txtTheatre.getText()) == (checkTheatre.getTheatreNumber())) && !isTheatreExists) {
                if(selectedTheatreEdit.getId() == checkTheatre.getId()) {
                    continue;
                }
                else {
                    isTheatreExists = true;
                }
            }
        }
        
        if(isAddable) {
            if(isTheatreExists) {
                AlertMaker.showErrorMessage("Add Theatre Error", "This theatre number is already exists");
                clearTheatreMessage();
            }
            else {
                cc.editTheatre(selectedTheatreEdit.getId(), editTheatre);
                cancelTheatreEdit();
            }
        }
        loadTheatreData();
        clearTheatreMessage();
    }
    @FXML
    private void handleCancleEditTheatre(ActionEvent event) {
        cancelTheatreEdit();
        clearTheatreMessage();
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
        String date = "";
        
        boolean isShowtimeOK = checkShowtimeForm(movieName,theatre,incSp,date,hour,min,soundtrack);

        if(isShowtimeOK){
            System.out.println("OK");
                System.out.println("Add showtime");
                String[] selectMovId = cbShowtimeSearch1.getValue().split(" :");
                String[] selectTheId = cbTheatreSearch1.getValue().split(" :");
                System.out.println("Movie ID: " + selectMovId[0]);
                System.out.println("Theatre ID: " + selectTheId[0]);
                selectMovId[0].replaceAll("\\s+","");
                selectTheId[0].replaceAll("\\s+","");
                
                int movie_id = Integer.valueOf(selectMovId[0]);
                int theatre_id = Integer.valueOf(selectTheId[0]);

                Movie m = cc.getMovie(movie_id);
                Theatre t = cc.getTheatre(theatre_id);
                String start = cbTimeHourShow1.getValue()+":"+cbTimeMinuteShow1.getValue();
                String sound = cbSoundtrack1.getValue();
                double incSeat = Double.parseDouble(incSp);
                String system = cbSystem.getValue();
                
//                System.out.println(m.getEnglishName());
//                System.out.println(t.getTheatreNumber());
                
               List<Showtime> listShowtime = cc.getShowtimeList();
                
                String myTime = m.getTime(); // เรียกระยะเวลาหนังที่จะสร้างมา , ถูกต้อง
                int newStartTimeHourInt = Integer.parseInt(cbTimeHourShow1.getValue()); // หนังที่จะสร้าง
                int newStartTimeMinuteInt = Integer.parseInt(cbTimeMinuteShow1.getValue());
                int myTimeHour = Integer.parseInt(myTime.substring(0, 2));
                int myTimeMinute = Integer.parseInt(myTime.substring(3));
                DecimalFormat twoDigit = new DecimalFormat("00");
                
                /* calculate end time */
                int newEndTimeHourInt = newStartTimeHourInt + myTimeHour;
                int newEndTimeMinuteInt = newStartTimeMinuteInt + myTimeMinute;
                if(newEndTimeMinuteInt >= 60) {
                    newEndTimeHourInt = newEndTimeHourInt + 1;
                    newEndTimeMinuteInt = newEndTimeMinuteInt - 60;
                }
                int newStartTime = Integer.parseInt(twoDigit.format(newStartTimeHourInt) + "" + twoDigit.format(newStartTimeMinuteInt) + "");
                int newEndTime = Integer.parseInt(twoDigit.format(newEndTimeHourInt) + "" + twoDigit.format(newEndTimeMinuteInt) + "");
                 //&& showtime.getDate().equals(datePickerShowtime.getValue) 
                
                boolean checkOverlap = false;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                
                for (Showtime showtime : listShowtime) {
                    // โรงที่จะเพิ่ม ตรงกับเลขโรงไหมที่มีอยู่แล้วไหม
                   if(showtime.getTheatre().getId() == theatre_id && (datePickerShowtime.getValue().equals(LocalDate.parse(showtime.getDate(), formatter))) && (!checkOverlap)) {
                       String startString = showtime.getShowtime().substring(0, 2) + showtime.getShowtime().substring(3, 5);
                       String endString = showtime.getShowtime().substring(8, 10) + showtime.getShowtime().substring(11);
                       int oldStartTime = Integer.parseInt(startString); // หนังที่มีอยู่แแล้ว
                       int oldEndTime = Integer.parseInt(endString); // หนังที่มีอยู่แล้ว

                       // เวลาที่เริ่มที่จะเพิ่ม อยู่ในช่วง (เวลาที่มีอยู่) เวลาเริ่มและเวลาจบ
                       if(newStartTime >= oldStartTime && newStartTime < oldEndTime) {
                           // ชน
                           checkOverlap = true;
                       }
                       // เวลาเริ่มอยู่นอกช่วงนั้น
                       else {
                           // เวลาเริ่มน้อยกว่า แต่เวลาจบอยู่ในช่วง
                           if(newEndTime >= oldStartTime && newEndTime <= oldEndTime) {
                               // ชน
                               checkOverlap = true;
                           }
                       }
                   }
                }
                
                if(!checkOverlap) {
                    String showtimeDate = convertDate(datePickerShowtime);

                    Showtime st = new Showtime(m,t,system,sound,showtimeDate,start,incSeat);

                    cc.addShowtime(st);
                    clearShowtimeMessage(); // clear label
                    clearShowtimeForm(); // clear text form
                    loadShowtimeData(); // load new list after add
                  //  AlertMaker.showSimpleAlert("Add Complete", st.toString());
                }
                else {
                    // Overlap
                    AlertMaker.showSimpleAlert("Add Showtime Error", "The time is overlap.");
                }

        }else{
            System.out.println("NOT OK");
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
        String date = "";
        
        boolean isShowtimeOK = checkShowtimeForm(movieName,theatre,incSp,date,hour,min,soundtrack);

        if(isShowtimeOK){
            System.out.println("OK");
                System.out.println("Add showtime");
                String[] selectMovId = cbShowtimeSearch1.getValue().split(" :");
                String[] selectTheId = cbTheatreSearch1.getValue().split(" :");
                System.out.println("Movie ID: " + selectMovId[0]);
                System.out.println("Theatre ID: " + selectTheId[0]);
                selectMovId[0].replaceAll("\\s+","");
                selectTheId[0].replaceAll("\\s+","");
                
                int movie_id = Integer.valueOf(selectMovId[0]);
                int theatre_id = Integer.valueOf(selectTheId[0]);

                Movie m = cc.getMovie(movie_id);
                Theatre t = cc.getTheatre(theatre_id);
                
                String start = cbTimeHourShow1.getValue()+":"+cbTimeMinuteShow1.getValue();
                System.out.println("Start"+start);
                String sound = cbSoundtrack1.getValue();
                double incSeat = Double.parseDouble(incSp);
                
                String system = cbSystem.getValue();
                
//                System.out.println(m.getEnglishName());
//                System.out.println(t.getTheatreNumber());

                List<Showtime> listShowtime = cc.getShowtimeList();
                
                String myTime = m.getTime(); // เรียกระยะเวลาหนังที่จะสร้างมา , ถูกต้อง
                int newStartTimeHourInt = Integer.parseInt(cbTimeHourShow1.getValue()); // หนังที่จะสร้าง
                int newStartTimeMinuteInt = Integer.parseInt(cbTimeMinuteShow1.getValue());
                int myTimeHour = Integer.parseInt(myTime.substring(0, 2));
                int myTimeMinute = Integer.parseInt(myTime.substring(3));
                DecimalFormat twoDigit = new DecimalFormat("00");
                
                /* calculate end time */
                int newEndTimeHourInt = newStartTimeHourInt + myTimeHour;
                int newEndTimeMinuteInt = newStartTimeMinuteInt + myTimeMinute;
                if(newEndTimeMinuteInt >= 60) {
                    newEndTimeHourInt = newEndTimeHourInt + 1;
                    newEndTimeMinuteInt = newEndTimeMinuteInt - 60;
                }
                int newStartTime = Integer.parseInt(twoDigit.format(newStartTimeHourInt) + "" + twoDigit.format(newStartTimeMinuteInt) + "");
                int newEndTime = Integer.parseInt(twoDigit.format(newEndTimeHourInt) + "" + twoDigit.format(newEndTimeMinuteInt) + "");
                 //&& showtime.getDate().equals(datePickerShowtime.getValue) 
                
                boolean checkOverlap = false;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                
                for (Showtime showtime : listShowtime) {
                    // โรงที่จะเพิ่ม ตรงกับเลขโรงไหมที่มีอยู่แล้วไหม
                   if(showtime.getTheatre().getId() == theatre_id && (datePickerShowtime.getValue().equals(LocalDate.parse(showtime.getDate(), formatter))) && (!checkOverlap)) {
                       String startString = showtime.getShowtime().substring(0, 2) + showtime.getShowtime().substring(3, 5);
                       String endString = showtime.getShowtime().substring(8, 10) + showtime.getShowtime().substring(11);
                       int oldStartTime = Integer.parseInt(startString); // หนังที่มีอยู่แแล้ว
                       int oldEndTime = Integer.parseInt(endString); // หนังที่มีอยู่แล้ว
                       
                       if(showtime.getId() == selectedShowtimeEdit.getId()) {
                           continue;
                       }
                       else {
                            // เวลาที่เริ่มที่จะเพิ่ม อยู่ในช่วง (เวลาที่มีอยู่) เวลาเริ่มและเวลาจบ
                            if(newStartTime >= oldStartTime && newStartTime < oldEndTime) {
                                // ชน
                                checkOverlap = true;
                            }
                            // เวลาเริ่มอยู่นอกช่วงนั้น
                            else {
                                // เวลาเริ่มน้อยกว่า แต่เวลาจบอยู่ในช่วง
                                if(newEndTime >= oldStartTime && newEndTime <= oldEndTime) {
                                    // ชน
                                    checkOverlap = true;
                                }
                            }
                       }
                   }
                }
                
                if(!checkOverlap) {
                    String showtimeDate = convertDate(datePickerShowtime);

                    Showtime st = new Showtime(m,t,system,sound,showtimeDate,start,incSeat);
                    cc.editShowtime(selectedShowtimeEdit.getId(),st);
                    clearShowtimeMessage(); // clear label
                    clearShowtimeForm(); // clear text form
                    loadShowtimeData(); // load new list after add
                    cancleShowtimeEdit();
                  //  AlertMaker.showSimpleAlert("Add Complete", st.toString());
                  //  AlertMaker.showSimpleAlert("Add Complete", st.toString());
                }
                else {
                    // Overlap
                    AlertMaker.showSimpleAlert("Save Showtime Error", "The time is overlap.");
                }
                
               
        }else{
            System.out.println("NOT OK");
        }
        

        
    }
    @FXML
    private void handleCancleEditShowtime(ActionEvent event) {
        cancleShowtimeEdit();
    }
    @FXML
    private void handleMouseClickShowtimeEdit(MouseEvent event) throws ParseException, IOException {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            initShowtimeEditMode();
            //System.out.println(selectedUserEdit);    
            System.out.println("Selected edit Show id : "+editShowtimeId);
        }
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            initShowtimeEditMode();
            cc.setSelectShowtime(selectedShowtimeEdit.getId());
            Parent root = FXMLLoader.load(getClass().getResource("/cinema/admin/ViewSeat.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Seat List");
            stage.setScene(new Scene(root));
            //stage.setMaximized(true);
            stage.show();
        }
    }
    @FXML
    private void handleShowtimeRefresh(ActionEvent event) {
        loadShowtimeData();
        clearShowtimeForm();
        clearShowtimeMessage();
    }
    @FXML
    private void handleShowtimeEdit(ActionEvent event) throws ParseException {
        initShowtimeEditMode();
    }
    @FXML
    private void handleShowtimeDelete(ActionEvent event) {
        Showtime selectedShowtimeDelete = tbShowtime1.getSelectionModel().getSelectedItem(); // เก็บมาเป็น object จาก list ที่เลือก
        
        if(selectedShowtimeDelete == null){
            AlertMaker.showErrorMessage("No Showtime selected", "Please select a Showtime for delete.");
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Showtime");
        alert.setContentText("Are you sure want to delete the Showtime ID "+ selectedShowtimeDelete.getId()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.get() == ButtonType.OK){
            System.out.println(selectedShowtimeDelete.getId());
            cc.deleteShowtime(selectedShowtimeDelete.getId());
            System.out.println("Deleting Showtime..... ");
            loadShowtimeData();
            cancleShowtimeEdit();
            clearShowtimeMessage();
        }
    }
    @FXML
    private void handleFindShowtimeSystem(ActionEvent event) {
        System.out.println("Load System");
        if(editShowtime){
            changeTheatre = true;
        }
        if(cbTheatreSearch1.getValue() != null){
            loadSystemCombo();
            cbSystem.setDisable(false);
        }
        
        
    }
    

    @FXML
    private void handleRefreshBooking(ActionEvent event) {
        initBookingCol();
        loadBookingData();
        setBookingTotal();
    }

    



}
