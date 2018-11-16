package cinema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author pisit
 */
@Entity
public class Showtime implements Serializable{
    private String movieName;
    private String theatreNum;
    @Id @GeneratedValue
    private int id;
    private double increaseSeatPrice = 0;
    private String soundtrack = "Th";
    private String subtitle = "-";
    private String period = "00:00";
//    private List<Seat> seatList = new ArrayList<Seat>();
//    private List<Seat> bookedList = new ArrayList<Seat>();
    
//    public Showtime(Movie movie, int id, List<Seat> list) {
//        this.movie = movie;
//        this.id = id;
//       // this.seatList = list;
//    }
    
    public Showtime(String movie,String theatre,String incSP,String sound,String time) {
        this.increaseSeatPrice = Double.valueOf(incSP);
        this.period = time;
        this.soundtrack = sound;
        if(sound.toUpperCase() == "TH"){
            this.subtitle = "-";
        }
        else if(sound.toUpperCase() == "EN"){
            this.subtitle = "Th";
        }
        this.movieName = movie;
        this.theatreNum = theatre;

        
    }
    //------------------------------------------------------------------- Method
    public int getId() {
        return id;
    }
    
//    public List<Seat> getSeatList() {
//        return seatList;
//    }
//    
//    public void setBooked(Seat seat, boolean t) {
//        for (Seat i : this.seatList) {
//            if (i.seatID == seat.seatID) {
//                i.setSeat(t);
//            }
//        }
//    }
    
    public boolean checkValidShowtime(String check) {
        return check.equalsIgnoreCase(period);
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setTheatreNum(String theatreNum) {
        this.theatreNum = theatreNum;
    }

    public void setIncreaseSeatPrice(double increaseSeatPrice) {
        this.increaseSeatPrice = increaseSeatPrice;
    }

    public void setSoundtrack(String soundtrack) {
        this.soundtrack = soundtrack;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    
    
    
    
    public String getMovie() {
        return this.movieName;
    }
     public String getTheatre() {
        return this.theatreNum;
    }
    
    public double getIncreaseSeatPrice(String id) {
        return increaseSeatPrice ;
    }
    
    public double getIncreaseSeatPrice(int id) {
        return increaseSeatPrice ;
    }

    public double getIncreaseSeatPrice() {
        return increaseSeatPrice;
    }
    
    
    
    public String getPeriod() {
        return this.period;
    }
    
//    public String [] getPeriodArray() {
//        String[] layout = new String[3];
//        layout[0] = this.period.substring(0, 2); // layout[0] is Hour
//        layout[1] = this.period.substring(3, 5); // layout[1] is min
//        return layout;
//    }
//    
//    public String getHour(){
//        String h = this.getPeriodArray()[0];
//        return h;
//    }
//    
//    public String getMin(){
//        String m = this.getPeriodArray()[1];
//        return m;
//    }
    
    public String getSoundtrack() {
        return this.soundtrack;
    }
    
    public String getSubtitle() {
        return this.subtitle;
    }
    
    public String getShowtime() {
        return (this.getMovie() + " : " + this.getPeriod() +
                "\nSoundtrack : "+ this.getSoundtrack() + "\nSubtitle : " + this.subtitle);
    }
    @Override
    public String toString() {
        return (this.getMovie() + " : " + this.getPeriod() +
                "\nSoundtrack : "+ this.getSoundtrack() + "\nSubtitle : " + this.subtitle);
    }
    
}
