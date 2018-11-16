package cinema;

import cinema.Movie;
import cinema.Seat;
import cinema.Theatre;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author pisit
 */

@Entity
public class Showtime implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @OneToOne(fetch= FetchType.EAGER)
    private Movie movie; // เก็บหนังเรื่องอะไร
    @OneToOne(fetch= FetchType.EAGER)
    private Theatre theatre; // โรงไหน
    
    private double increaseSeatPrice = 0;
    private String system = "2D";
    private String soundtrack = "TH";
    private String subtitle = "-";
    private String startTime = "00:00";
    private String date;
    
    //@OneToOne(cascade=CascadeType.PERSIST)
    @OneToOne(mappedBy = "showtime",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Seat> seatList = new ArrayList<Seat>();
    
    //private List<Seat> bookedList = new ArrayList<Seat>();

    public Showtime(Movie movie, Theatre theatre, String system, String soundtrack, String date, String startTime, double increaseSeatPrice) {
        this.movie = movie;
        this.theatre = theatre;
        this.system = system;
        this.soundtrack = soundtrack;
        this.subtitle = chooseSubtitle(soundtrack);

        this.startTime = startTime;
        this.date = date;
        this.increaseSeatPrice = increaseSeatPrice;
        
        createSeatLayout(increaseSeatPrice);
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    public String chooseSubtitle(String soundtrack){
        String sub = "TH";
        if (this.soundtrack.toUpperCase().equals("TH")) { // ถ้าเป็น Thai ไม่มีซับ
            sub = "-";
        }
        return sub;
    }
    
    // Seat operation one showtime to many seat
    public void addNormalSeat(int size, String name, double increaseSeatPrice){
        for(int i = 1; i <= size; i++) {
            seatList.add(new NormalSeat(name+i,this,increaseSeatPrice));
        }
    }
    public void addHoneymoonSeat(int size, String name, double increaseSeatPrice){
        for(int i = 1; i <= size; i++) {
            seatList.add(new HoneymoonSeat(name+i,this,increaseSeatPrice));
        }
    }
    public void addPairedSeat(int size, String name, double increaseSeatPrice){
        for(int i = 1; i <= size; i++) {
            seatList.add(new PairedSeat(name+i,this,increaseSeatPrice));
        }
    }

    public final void createSeatLayout(double increaseSeatPrice) {
        /* Create Seat Layout */
        // Create Pair Seat for 1 row, 5 columns
        addPairedSeat(5,"A",increaseSeatPrice);
        // Create Honeymoon Seat for 2 rows, each row contains 20 seats
        addHoneymoonSeat(20,"B",increaseSeatPrice);
        addHoneymoonSeat(20,"C",increaseSeatPrice);

        // Create Normal Seat for 3 rows, each row contains 20 seats
        addNormalSeat(20,"D",increaseSeatPrice);
        addNormalSeat(20,"E",increaseSeatPrice);
        addNormalSeat(20,"F",increaseSeatPrice);
        
    } 
    public List<Seat> getSeatList() {return seatList;}
    
    public void setSeatPrice(double increaseSeatPrice){
        for (Seat seat : seatList) {
            seat.setSeatPrice(increaseSeatPrice);
        }
    }
    public void setSeatStatus(String seatNumber,boolean seatStatus){
        for (Seat seat : seatList) {
            if(seat.getSeatName().equals(seatNumber)){
                seat.setSeatStatus(seatStatus);
            }
        }
    }
    public Seat getSeat(String seatNumber){
        Seat s = null;
        for (Seat seat : seatList) {
            if(seat.getSeatName().equals(seatNumber)){
               s = seat;
            }
        }
        return s;
    }
    
    //------------------------------------------------------------------- Method
    //*************************** setter & getter********************************************************
    public void setMovie(Movie movie) {this.movie = movie;}
    public Movie getMovie() {return movie;}

    public void setTheatre(Theatre theatre) {this.theatre = theatre;}
    public Theatre getTheatre() {return theatre;}

    public void setSystem(String system) {this.system = system;}
    public String getSystem() {return system;}

    public void setSoundtrack(String soundtrack) {
        this.soundtrack = soundtrack;
        this.subtitle = chooseSubtitle(soundtrack);
    }
    public String getSoundtrack() {return soundtrack;}

    public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
    public String getSubtitle() {return subtitle;}

    public void setStartTime(String startTime) {this.startTime = startTime;}
    public String getOnlyStartTime(){
        return this.startTime;
    }
    
    public String getStartTime() {return getShowtime();}

    public void setIncreaseSeatPrice(double increaseSeatPrice) {this.increaseSeatPrice = increaseSeatPrice;}
    public double getIncreaseSeatPrice(String id) {return increaseSeatPrice;}
    public double getIncreaseSeatPrice(int id) {return increaseSeatPrice;}
    public double getIncreaseSeatPrice() {return increaseSeatPrice;}

    public int getTheatreNo() {return theatre.getTheatreNumber();}
    
    public void setId(int id) {this.id = id;}
    public int getId() {return id;}
    
    public String getMovieEng() {return movie.getEnglishName();}
    public String getMovieThai() {return movie.getThaiName();}


    public boolean checkValidShowtime(String check) {
        return check.equalsIgnoreCase(startTime);
    }

    //public double getPrice(String id) {return increaseSeatPrice + theatre.getSeatPrice(id);}

    public int getTimeMovie() {return Integer.parseInt(movie.getTime());}
    
    
    public String getShowtime() {
        String myTime = this.startTime;
        
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = df.parse(myTime);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int hour = Integer.parseInt(movie.getTime().substring(0, 2)) * 60;
        int minute = Integer.parseInt(movie.getTime().substring(3));
        int periodTime = hour + minute;
//        System.out.println(hour);
//        System.out.println(minute);
//        System.out.println(periodTime);

        cal.add(Calendar.MINUTE, periodTime);
        String newTime = df.format(cal.getTime());
        return (myTime + " - " +newTime);
    }

//    @Override
//    public String toString() {
//        return "Showtime{" + "\n" + 
//               "movie=" + movie + "\n ================= \n" +  
//             ", theatre=" + theatre + "\n ================= \n" +
//             ", id=" + id + "\n" +
//             ", increaseSeatPrice=" + increaseSeatPrice + "\n" + 
//             ", system=" + system + "\n" + 
//             ", soundtrack=" + soundtrack + "\n" + 
//             ", subtitle=" + subtitle + "\n" + 
//             ", startTime=" + startTime + "\n" ;
//          //   ", seatList=" + seatList  ;
//         //    + ", bookedList=" + bookedList + '}';
//    }

    

}
