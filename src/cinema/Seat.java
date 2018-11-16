
package cinema;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class Seat implements Serializable{
    private static final long serialVersionUID = 1L;
        
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id;
    
    @ManyToOne 
    protected Showtime showtime; // เป็นของ showtime อะไร
    
    protected String seatName;
    protected String seatType;
    protected double seatPrice;
    protected boolean seatStatus;
    
    protected double basePrice = 120;
    
    abstract public double getSeatPrice();
    abstract void setSeatPrice(double seatPrice);
    abstract public String getSeatType();
    // set for which showtime?
    abstract public void setShowtime(Showtime showtime);
    abstract public Showtime getShowtime();
    // set and get status of seat
    abstract void setSeatStatus(boolean status);
    abstract public boolean getSeatStatus();
    // get seat id
    abstract public int getId();
    // get seat name
    abstract public String getSeatName();
}
