
package cinema.ui.auth;

import cinema.Cinema;
import cinema.User;
import cinema.ui.AlertMaker;
import cinema.ui.CinemaUtility;
import cinema.ui.FieldValidation;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController implements Initializable{

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private Label labelUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label labelPassword;
    @FXML
    private JFXPasswordField txtConfirmPassword;
    @FXML
    private Label labelConfirmPassword;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private Label labelFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private Label labelLastName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private Label labelEmail;

    List<User> allUser;
    Cinema cinema;
    
    public RegisterController() {
        this.cinema = cinema.getInstance();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearLabel();
        cinema.getInstance();
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        // Form validation
        boolean isUernameNotEmpty = FieldValidation.isTextFieldNotEmpty(txtUsername, labelUsername, "ใส่ชื่อผู้ใช้งาน");
        boolean isPasswordNotEmpty = FieldValidation.isTextFieldNotEmpty(txtPassword, labelPassword, "ใส่รหัสผ่าน");
        boolean isConfirmPasswordNotEmpty = FieldValidation.isTextFieldNotEmpty(txtConfirmPassword, labelConfirmPassword, "ใส่ยืนยันรหัสผ่าน");
        boolean isFirstNameNotEmpty = FieldValidation.isTextFieldNotEmpty(txtFirstName, labelFirstName, "ใส่ชื่อจริง");
        boolean isLastNameNotEmpty = FieldValidation.isTextFieldNotEmpty(txtLastName, labelLastName, "ใส่นามสกุล");
        boolean isEmailNotEmpty = FieldValidation.isTextFieldNotEmpty(txtEmail, labelEmail, "ใส่อีเมล");
        
        if(isUernameNotEmpty && isPasswordNotEmpty && isConfirmPasswordNotEmpty && isFirstNameNotEmpty && isLastNameNotEmpty && isEmailNotEmpty){
            System.out.println("All textfield is not empty!");
            resetAllStyle();
            
            // ตรวจสอบ Password ว่าตรงกันมั้ย
            boolean isPasswordMatch = FieldValidation.isTextMatch(txtPassword, txtConfirmPassword, labelConfirmPassword, "ใส่รหัสผ่านไม่ตรงกัน");
            if(isPasswordMatch){
                CinemaUtility.resetTextStyle(txtPassword, labelPassword, "text-field-error");
                CinemaUtility.resetTextStyle(txtConfirmPassword, labelConfirmPassword, "text-field-error");
                System.out.println("Password is match.");
            }
            
            // ดึงข้อมูลทั้งหมดใส่ List
            cinema.updateUserList();
            allUser = cinema.getUserList();
            
            // ค้นหา Username ซ้ำ
            boolean searchUsernameFound = false;
            searchUsernameFound = cinema.checkExistUsername(txtUsername.getText());

                // ถ้าเจอ Username แสดง error ว่ามีแล้ว
                if(searchUsernameFound){
                    labelUsername.setText("This username is already used.");
                    labelUsername.getStyleClass().add("text-error");
                    // ให้ Textfield เปลี่ยนเป็นสี Error
                    txtUsername.getStyleClass().add("text-field-error");
                }else{
                    CinemaUtility.resetTextStyle(txtUsername, labelUsername, "text-field-error");
                }
            
                
            // ตรวจสอบว่า email ถูกต้องมั้ย
            boolean searchEmailFound = false;
            boolean isEmailValid = FieldValidation.isValidEmailAddress(txtEmail.getText(), txtEmail, labelEmail, "ใส่อีเมลไม่ถูกต้อง");
            if(isEmailValid){
                CinemaUtility.resetTextStyle(txtEmail, labelEmail, "text-field-error");
                System.out.println("Email is valid.");
                
                // ตรวจสอบว่าอีเมลซ้ำมั้ย
                searchEmailFound = cinema.checkExistEmail(txtEmail.getText());
                    
                    // ถ้าเจอ Email แสดง error
                    if(searchEmailFound){
                        labelEmail.setText("This email is already used.");
                        labelEmail.getStyleClass().add("text-error");
                        // ให้ Textfield เปลี่ยนเป็นสี Error
                        txtEmail.getStyleClass().add("text-field-error"); // บอก Confirm password ไม่ตรง
                    }else{
                        CinemaUtility.resetTextStyle(txtEmail, labelEmail, "text-field-error");
                    }
                }
            
            
            // ถ้าทุกอย่างผ่านหมด Password ตรง , Username ไม่ซ้ำ , email ถูกต้องและไม่ซ้ำ
            if(isPasswordMatch && !searchUsernameFound && isEmailValid && !searchEmailFound){
                System.out.println("Create Operation...");
                // สร้าง Object
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                String firstname = txtFirstName.getText();
                String lastname = txtLastName.getText();
                String email = txtEmail.getText();
                // 0 = admin , 1 = staff , 2 = customer
                String type = "Customer"; // Default Customer
                AlertMaker.showSimpleAlert("Password Prehash",password);
                User newUser = new User(username, password, firstname, lastname, email, type);
                AlertMaker.showSimpleAlert("Password hashed",newUser.getPassword());
                AlertMaker.showSimpleAlert("Test pass", newUser.encryptPassword(password));
                boolean testPass = newUser.getPassword().equals(newUser.encryptPassword(password));
                String testPassStr = String.valueOf(testPass);
                AlertMaker.showSimpleAlert("Euqal ?", testPassStr);
                cinema.addUser(newUser); // add new user
                System.out.println("Add user to database cinema completed");
                AlertMaker.showSimpleAlert("Register Completed", newUser.toString());
                boolean test = cinema.checkValidUser(username, password);
                String testStr = String.valueOf(test);
                AlertMaker.showSimpleAlert("Test",testStr);
                clearForm();
    
            }
            
        }else{
            if(isUernameNotEmpty){ 
                CinemaUtility.resetTextStyle(txtUsername, labelUsername, "text-field-error");
            }
                
            if(isPasswordNotEmpty){
                CinemaUtility.resetTextStyle(txtPassword, labelPassword , "text-field-error");
            }
            
            if(isConfirmPasswordNotEmpty){
                CinemaUtility.resetTextStyle(txtConfirmPassword, labelConfirmPassword , "text-field-error");
            }
            
            if(isFirstNameNotEmpty){
                CinemaUtility.resetTextStyle(txtFirstName, labelFirstName , "text-field-error");
            }
            
            if(isLastNameNotEmpty){
                CinemaUtility.resetTextStyle(txtLastName, labelLastName , "tex;t-field-error");
            }
            
            if(isEmailNotEmpty){
                CinemaUtility.resetTextStyle(txtEmail, labelEmail , "text-field-error");
            }
            
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
//        clearForm();
//        resetAllStyle();
          boolean searchUsernameFound = cinema.checkExistUsername(txtUsername.getText());
          boolean searchEmailFound = cinema.checkExistEmail(txtEmail.getText());
          System.out.println(searchUsernameFound);
          System.out.println(searchEmailFound);
    }
    
    // ลบค่า Form
    public void clearForm(){
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
    }
    
    public void clearLabel(){
        labelUsername.setText("");
        labelPassword.setText("");
        labelConfirmPassword.setText("");
        labelFirstName.setText("");
        labelLastName.setText("");
        labelEmail.setText("");
        
    }
    
    public void resetAllStyle(){
        CinemaUtility.resetTextStyle(txtUsername, labelUsername, "text-field-error");
        CinemaUtility.resetTextStyle(txtPassword, labelPassword , "text-field-error");
        CinemaUtility.resetTextStyle(txtConfirmPassword, labelConfirmPassword , "text-field-error");
        CinemaUtility.resetTextStyle(txtFirstName, labelFirstName , "text-field-error");
        CinemaUtility.resetTextStyle(txtLastName, labelLastName , "text-field-error");
        CinemaUtility.resetTextStyle(txtEmail, labelEmail , "text-field-error");
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        // ((Stage)rootPane.getScene().getWindow()).close();
        Parent register = FXMLLoader.load(getClass().getResource("/cinema/ui/auth/Login.fxml"));
            Scene registerScene = new Scene(register);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.setMaximized(true);
            window.show();
    }
    
}
