package cinema;

import cinema.ui.AlertMaker;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author pisit
 */
@Entity
public class Booking implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(fetch= FetchType.EAGER)
    private Showtime showtime;
    
    @OneToOne(fetch= FetchType.EAGER)
    private User user;
    
    @OneToOne(fetch= FetchType.EAGER)
    private Promotion promotion = null;
    
    private boolean isCancel = false;
    
    @OneToMany(fetch= FetchType.EAGER)
    private List<Seat> bookedSeatList = new ArrayList<Seat>();
    
    private double totalCost;
    
    String bookingCreateDatetime;
    String bookingUpdateDatetime;

    public Booking(Showtime showtime, List<Seat> seat, User user, Promotion promotion,double totalCost) {
        this.showtime = showtime;
        this.bookedSeatList = seat;
        this.user = user;
        this.promotion = promotion;
        this.totalCost = totalCost;
        this.isCancel = false;
        this.bookingCreateDatetime = getCurrentDatetime();
        this.bookingUpdateDatetime = getCurrentDatetime();
    }
    
    public String getCurrentDatetime(){
        DateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
    public void updateDatetime(){
        this.bookingUpdateDatetime = getCurrentDatetime();
    }

    public String getBookingCreateDatetime() {
        return bookingCreateDatetime;
    }
    public String getBookingUpdateDatetime() {
        return bookingUpdateDatetime;
    }
    public String getShowtimeDetail(){
        return "("+ showtime.getId() + ") Name(EN) : " + showtime.getMovieEng() + "\n" +
                   "Name(TH) : " + showtime.getMovieThai()+ "\n" +
                   showtime.getSoundtrack()+"/"+showtime.getSubtitle()+ " (" + showtime.getSystem() + ")\n" +
                   "Time : " + showtime.getShowtime()+ "\n" +
                   "Theatre : " + showtime.getTheatre().getTheatreNumber();      
    }
    public String getUserDetail(){
        return "(" + user.getId() + ") Name : " + user.getFirstname()+" "+ user.getLastname() + "\n" +
                   "Email : " + user.getEmail()+ "\n" +
                "Money : " + user.getMoney();         
    }
    
    public String getBookedSeatString(){
        String seatList = "";
        for (Seat seat : bookedSeatList) {
            seatList = seatList + "(" + seat.getId() + ") " + seat.getSeatName() + " : " + seat.getSeatStatus() + "\n";
        }
        return seatList;
    }

    public boolean isCancel() {
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
    public double getTotalSeatPrice(){
        double total = 0;
        for (Seat seat : bookedSeatList) {
            total += seat.getSeatPrice();
        }
        return total;
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


}
