package cinema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.CascadeType;
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
public class Theatre implements Serializable{
    private static final long serialVersionUID = 1L;
        
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private int theatreNumber;
    //private String theatreSize = "Medium"; // Default size is medium
    @OneToMany(fetch= FetchType.EAGER)
    private List<Showtime> showtimeList = new ArrayList<Showtime>(); // Display updated showtime when booking
    
    public Theatre(int theatreNumber) {
        this.theatreNumber = theatreNumber;
    }

    public void addShowtime(Showtime showtime){
        this.showtimeList.add(showtime);
    }
    
    public void deleteShowtime(Showtime st){
        showtimeList.remove(st);
    }
    
    public List<Showtime> getShowtimeList(){
        this.showtimeList.sort(new Comparator<Showtime>() {
            public int compare(Showtime o1, Showtime o2) {
                // compare two instance of `Score` and return `int` as result.
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });
        return this.showtimeList;
    }

    public void setShowtimeList(List<Showtime> showtimeList) {
        this.showtimeList = showtimeList;
    }
    
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public void setTheatreNumber(int theatreNumber) {this.theatreNumber = theatreNumber;}
    public int getTheatreNumber() {return theatreNumber;}
    
    

    @Override
    public String toString() {
        return "Theatre number : " + this.theatreNumber;
    }
}

