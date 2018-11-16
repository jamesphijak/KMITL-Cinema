package cinema;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author pisit
 */
public class Booking {
    private int bookid;
    private Showtime showtime;
    
    public Booking(Showtime showtime) {
        this.showtime = showtime;
    }
    
    //------------------------------------------------------------------- Method
    public void postpone(int id) {
        
    }
    
    public void requestCancel(int id) {
        
    }
    
    public void requestPayment() {
        
    }
    
    public void setBooking(int id, Showtime st) {
        
    }
    
    public boolean checkPrice(double price) { //---------------------- รอ User->Member
        return true;
    }
    
    public double getPrice(String seatID) {
        return showtime.getIncreaseSeatPrice(seatID);
    }
    
}
