package cinema;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
        
    @Id @GeneratedValue
    private int id;
    
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String type;
    private double money;
    
    private List<Promotion> promotionList = new ArrayList<Promotion>();
    
    public User(){}
    
    public User(String username, String password, String firstname, String lastname, String email, String type, Double money) {
        this.username = username;
        this.password = encryptPassword(password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.type = type;
        this.money = money;
    }
    
    public static String encryptPassword(String password){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(password.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedPassword;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    
    // ใช้เก็บ Promotion ที่เคยใช้แล้ว
    public List<Promotion> getPromotionList() {
        return this.promotionList;
    }
    
    public void setPromotionList(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }
    public void addPromotion(Promotion promotion){
        this.promotionList.add(promotion);
    }
    
    
    @Override
    public String toString() {
        return ("User ID : "+ this.getId()+ 
              "\nUsername : " + this.getUsername()+ 
              "\nPassword : "+ this.getPassword()+ 
              "\nFirstname : " + this.getFirstname()+
              "\nLastname : " + this.getLastname()+ 
              "\nEmail : " + this.getEmail()+ 
              "\nType : " + this.getType()+
              "\nMoney : " + this.getMoney()+
              "\nPromotion : " + this.getPromotionList() + "\n"
              );
    }
    
}
