package cinema;

import cinema.ui.AlertMaker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pisit
 */
@Entity
public class Booking implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private Showtime showtime;
    private User user;
    private Promotion promotion = null;
    private boolean isCancel = false;
    
    @OneToMany(fetch= FetchType.EAGER)
    private List<Seat> bookedSeatList = new ArrayList<Seat>();
    
    private double totalCost;

    public Booking(Showtime showtime, List<Seat> seat, User user, Promotion promotion,double totalCost) {
        this.showtime = showtime;
        this.bookedSeatList = seat;
        this.user = user;
        this.promotion = promotion;
        this.totalCost = totalCost;
        this.isCancel = false;
    }

    public boolean isIsCancel() {
        return isCancel;
    }
    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }
    public int getId() {
        return id;
    }
    public Showtime getShowtime() {
        return showtime;
    }
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Promotion getPromotion() {
        return promotion;
    }
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    public List<Seat> getBookedSeatList() {
        return bookedSeatList;
    }
    public void setBookedSeatList(List<Seat> bookedSeatList) {
        this.bookedSeatList = bookedSeatList;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Booking{\n" + "id=" + id + 
               "Showtime=" + showtime + "\n" + 
               "User=" + user + "\n" + 
               "Promotion=" + promotion + "\n" + 
               "BookedSeatList=" + bookedSeatList + "\n" +
               "TotalCost=" + totalCost + '}';
    }
    
    public double cancelBooking(){
        double userReturn = this.totalCost - (this.totalCost * 0.1); // return to user 90%
        this.totalCost = this.totalCost - userReturn; // booking will left only 10%
        return userReturn;
    }

    //------------------------------------------------------------------- Method
//    public void postpone(int id, Showtime next_st, List<Seat> seat) {
//        for (Seat s : this.bookedSeat) {                  // วนเอาที่นั่งที่จองไว้แล้วไปยกเลิกที่นั่งที่ showtime
//            this.showtime.setBooked(s, false);
//        }
//        this.showtime = next_st;                           // แก้ Showtime เป็นล่าสุด
//        this.bookedSeat = seat;
//        for (Seat s : this.bookedSeat) {                  // วนเอาที่นั่งใหม่ที่จะจองแล้วไปใส่ที่ showtime ใหม่
//            this.showtime.setBooked(s, true);
//        }
//    }



}
