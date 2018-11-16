/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.auth;

import cinema.User;
import cinema.UserController;
import cinema.screensframework.ControlledScreen;
import cinema.screensframework.ScreensController;
import cinema.ui.AlertMaker;
import cinema.ui.CinemaUtility;
import cinema.ui.FieldValidation;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Phijak
 */
public class LoginController implements Initializable, ControlledScreen {

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
    
    List<User> allUser;
    UserController uc;
    User loginUser;
    ScreensController myController;

    public LoginController() {
        this.uc = uc.getInstance();
    }
    
    // ลบค่า Label
    public void clearLabel(){
        labelUsername.setText("");
        labelPassword.setText("");
    }
    
    // ลบค่า Form
    public void clearForm(){
        txtUsername.setText("");
        txtPassword.setText("");
    }
    
    public void resetAllStyle(){
        resetUsernameStyle();
        resetPasswordStyle();
    }
    
    public void resetUsernameStyle(){
        CinemaUtility.resetTextStyle(txtUsername, labelUsername, "text-field-error");
    }
    
    public void resetPasswordStyle(){
        CinemaUtility.resetTextStyle(txtPassword, labelPassword , "text-field-error");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearForm(); // clear initial form
        clearLabel(); // clear initial label
//        cinema.getInstance();
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println(txtUsername.getText());
        System.out.println(txtPassword.getText());
   
        boolean isUernameNotEmpty = FieldValidation.isTextFieldNotEmpty(txtUsername, labelUsername, "ต้องใส่ชื่อผู้ใช้งาน");
        boolean isPasswordNotEmpty = FieldValidation.isTextFieldNotEmpty(txtPassword, labelPassword, "ต้องใส่รหัสผ่าน");
        
        if(isUernameNotEmpty && isPasswordNotEmpty){
            
            System.out.println("ชื่อผู้ใช้งาน และรหัสผ่าน ตรวจสอบแล้วถูกต้อง!");
            System.out.println("กำลังเช็คในฐานข้อมูล...");
            resetAllStyle();
            
            if (uc.checkValidUser(txtUsername.getText(), txtPassword.getText())){
                loginUser = uc.getLoginUser();
                AlertMaker.showSimpleAlert("Login Completed", loginUser.toString());
                closeStage(); // close screen
                loadMain(); // show main screen
            }else{
                AlertMaker.showErrorMessage("Login Failed", "Not found this account in database.");
            }
        }else{
            if(isUernameNotEmpty){
                resetUsernameStyle(); // if username correct remove error style
            }
            if(isPasswordNotEmpty){
                resetPasswordStyle(); // if password correct remove error style
            }
            
        }
    }
    
    void loadMain(){
        try {
             if(loginUser.getType().equals("Admin")){
                Parent root = FXMLLoader.load(getClass().getResource("/cinema/admin/AdminMain.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("KMITL Cinema");
                stage.setScene(new Scene(root));
                stage.setMaximized(true);
                stage.show();
                CinemaUtility.setStageIcon(stage);
             }else{
                 System.out.println("None");
             }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private void closeStage() {
        ((Stage)rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void handleForgotPassword(MouseEvent event) {
        System.out.println("forgot");
        
    }

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
         Parent register = FXMLLoader.load(getClass().getResource("/cinema/ui/auth/Register.fxml"));
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle("สมัครสมาชิก");

            Scene registerScene = new Scene(register);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.setMaximized(true);
            window.show();
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
}
