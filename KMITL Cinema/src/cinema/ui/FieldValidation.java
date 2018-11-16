package cinema.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;


public class FieldValidation {
    public static boolean isTextFieldNotEmpty(JFXTextField textField){
        boolean b = false;
        if(!(textField.getText().isEmpty())){
            b = true;
        }
        return b;
    }
    
    public static boolean isTextFieldNotEmpty(JFXPasswordField textField){
        boolean b = false;
        if(!(textField.getText().isEmpty())){
            b = true;
        }
        return b;
    }
    
    public static boolean isTextNotEmpty(String string){
        boolean b = false;
        if(!(string.isEmpty())){
            b = true;
        }
        return b;
    }
    
    public static boolean isTextNotEmpty(String string,Label label, String errorMessage){
        boolean b = true;
        if(!isTextNotEmpty(string)){
            b = false;
            // ให้ Label แสดงข้อความ และเปลี่ยนเป็นสี Error
            label.setText(errorMessage);
            label.getStyleClass().add("text-error");
            
            // ให้ Textfield เปลี่ยนเป็นสี Error
            //textField.getStyleClass().add("text-field-error");
            // AlertMaker.showErrorMessage("Form field can't be empty","Please enter ");
        }else{
            b = true;
        }
        
        return b;
    }
    
    public static boolean isTextFieldNotEmpty(JFXTextField textField,Label label, String errorMessage){
        boolean b = true;
        if(!isTextFieldNotEmpty(textField)){
            b = false;
            // ให้ Label แสดงข้อความ และเปลี่ยนเป็นสี Error
            label.setText(errorMessage);
            label.getStyleClass().add("text-error");
            
            // ให้ Textfield เปลี่ยนเป็นสี Error
            textField.getStyleClass().add("text-field-error");
            // AlertMaker.showErrorMessage("Form field can't be empty","Please enter ");
        }else{
            b = true;
        }
        
        return b;
    }
    
    public static boolean isTextFieldNotEmpty(JFXPasswordField textField,Label label, String errorMessage){
        boolean b = true;
        if(!isTextFieldNotEmpty(textField)){
            b = false;
            // ให้ Label แสดงข้อความ และเปลี่ยนเป็นสี Error
            label.setText(errorMessage);
            label.getStyleClass().add("text-error");
            
            // ให้ Textfield เปลี่ยนเป็นสี Error
            textField.getStyleClass().add("text-field-error");
            // AlertMaker.showErrorMessage("Form field can't be empty","Please enter ");
        }else{
            b = true;
        }
        
        return b;
    }
    
    public static boolean isTextMatch(JFXPasswordField text, JFXPasswordField confirmText){
        boolean b = false;
        if(text.getText().equals(confirmText.getText())){
            b = true;
        }
        return b;
    }
    
    public static boolean isTextMatch(String text, JFXTextField confirmText){
        boolean b = false;
        if(text.equals(confirmText.getText())){
            b = true;
        }
        return b;
    }
    
    public static boolean isTextMatch(JFXPasswordField text, JFXPasswordField confirmText,Label label, String errorMessage){
        boolean b = true;
        if(!isTextMatch(text, confirmText)){
            b = false;
            // ให้ Label แสดงข้อความ และเปลี่ยนเป็นสี Error
            label.setText(errorMessage);
            label.getStyleClass().add("text-error");
            
            // ให้ Textfield เปลี่ยนเป็นสี Error
            confirmText.getStyleClass().add("text-field-error"); // บอก Confirm password ไม่ตรง
        }else{
            b = true;
        }
        return b;
    }
    
    public static boolean isTextMatch(String text, JFXTextField confirmText,Label label, String errorMessage){
        boolean b = true;
        if(!isTextMatch(text, confirmText)){
            b = false;
            // ให้ Label แสดงข้อความ และเปลี่ยนเป็นสี Error
            label.setText(errorMessage);
            label.getStyleClass().add("text-error");
            
            // ให้ Textfield เปลี่ยนเป็นสี Error
            confirmText.getStyleClass().add("text-field-error"); // บอก Confirm password ไม่ตรง
        }else{
            b = true;
        }
        return b;
    }
    
    public static boolean isValidEmailAddress(String email, JFXTextField confirmText,Label label, String errorMessage) {
           boolean b = true;   
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           Pattern p = java.util.regex.Pattern.compile(ePattern);
           Matcher m = p.matcher(email);
           if(!m.matches()){
               b = false;
               label.setText(errorMessage);
               label.getStyleClass().add("text-error");
            
               // ให้ Textfield เปลี่ยนเป็นสี Error
               confirmText.getStyleClass().add("text-field-error"); // บอก Confirm password ไม่ตรง
           }else{
               b = true;
           }
           return b;
    }
}
