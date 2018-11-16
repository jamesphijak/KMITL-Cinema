package cinema;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pisit
 */

@Entity
public class Promotion implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private int promotionID;
    
    private String name;
    private String description;
    private String remainingDate;
    private double discount = 0;

    public Promotion(String name, String description, String remainingDate, Double discount) {
        this.name = name;
        this.description = description;
        this.remainingDate = remainingDate;
        this.discount = discount;
    }

    public int getPromotionID() {
        return promotionID;
    }
    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRemainingDate() {
        return remainingDate;
    }
    public void setRemainingDate(String remainingDate) {
        this.remainingDate = remainingDate;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return ("Promotion ID : "+ this.getPromotionID()+ 
              "\nName : " + this.getName()+ 
              "\nDescription : " + this.getDescription()+ 
              "\nRemainingDate : " + this.getRemainingDate()+ 
              "\nDiscount : " + this.getDiscount() + "\n"
              );
    }

    
    
    
}
