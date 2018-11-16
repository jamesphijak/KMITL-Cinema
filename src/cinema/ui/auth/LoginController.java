/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.ui.auth;

import cinema.User;
import cinema.UserController;
import cinema.screensframework.ScreensController;
import cinema.ui.AlertMaker;
import cinema.ui.CinemaUtility;
import cinema.ui.FieldValidation;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class LoginController implements Initializable {

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
        boolean isUernameNotEmpty = FieldValidation.isTextFieldNotEmpty(txtUsername, labelUsername, "ต้องใส่ชื่อผู้ใช้งาน");
        boolean isPasswordNotEmpty = FieldValidation.isTextFieldNotEmpty(txtPassword, labelPassword, "ต้องใส่รหัสผ่าน");
        
        if(isUernameNotEmpty && isPasswordNotEmpty){
            System.out.println("Login...");
            resetAllStyle();
            
            if (uc.checkValidUser(txtUsername.getText(), txtPassword.getText())){
                loginUser = uc.getLoginUser();
                //AlertMaker.showSimpleAlert("Login Completed", loginUser.toString());
//                closeStage(); // close screen
//                loadMain(); // show main screen
                System.out.println(loginUser.getType());
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                if(loginUser.getType().equals("Admin")){
                    // Go to admin
                    System.out.println("admin");
                    loadScreen(window , "/cinema/admin/AdminMain.fxml");
                }else if(loginUser.getType().equals("Staff")){
                    // Goto Staff
                    System.out.println("staff");
                    loadScreen(window , "/cinema/ui/showmovie/showmovie.fxml");
                }else{
                    // Goto customer
                    System.out.println("user");
                    loadScreen(window , "/cinema/ui/showmovie/showmovie.fxml");
                }
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
    
    @FXML
    private void handleRegister(ActionEvent event){
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        loadScreen(window , "/cinema/ui/auth/Register.fxml");
    }
    
    public void loadScreen(Stage window , String path){
        try {
            ((Stage)rootPane.getScene().getWindow()).close();
            Parent parent;
            parent = FXMLLoader.load(getClass().getResource(path));
            Scene parentScene = new Scene(parent);
            window.setScene(parentScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
