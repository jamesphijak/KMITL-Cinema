/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;




import cinema.HoneymoonSeat;
import cinema.NormalSeat;
import cinema.PairedSeat;
import cinema.Seat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author pisit
 */
public class Theatre {
    private int id;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int theatreNumber;
    private String theatreSize = "Medium";
    private List<Showtime> showtimeIDList; // Display updated showtime when booking
    
    private Seat[][] seatLayout = new Seat[6][20];
    
    public Theatre(int theatreNumber) {
        this.id = count.incrementAndGet();
        this.theatreNumber = theatreNumber;
        createSeatLayout();
    }
   
    public Theatre(int theatreNumber, String theatreSize) {
        this.id = count.incrementAndGet();
        this.theatreNumber = theatreNumber;
        this.theatreSize = theatreSize;
        createSeatLayout();
    }
    
    public final void createSeatLayout() {
        /* Create Seat Layout */
        // Create Paired Seat for 1 rows, each row contains 20 seats
        for(int i = 0; i < 1; i++) {
            for(int j = 0; j < 5; j++) {
                seatLayout[i][j] = new PairedSeat();
            }
        }
        
        // Create Honeymoon Seat for 2 rows, each row contains 20 seats
        for(int i = 1; i < 3; i++) {
            for(int j = 0; j < 20; j++) {
                seatLayout[i][j] = new HoneymoonSeat();
            }
        }
        
        // Create Normal Seat for 3 rows, each row contains 20 seats
        for(int i = 3; i < 6; i++) {
            for(int j = 0; j < 20; j++) {
                seatLayout[i][j] = new NormalSeat();
            }
        }
    }
    
    public int [] convertSeatName(String seat) {
        String[] layout = new String[3];
        if(seat.length() == 2) {
            layout[0] = seat.substring(0, 1); // layout[0] is row
            layout[1] = seat.substring(1, 2); // layout[1] is column
        }
        else if(seat.length() == 3) {
            layout[0] = seat.substring(0, 1); // layout[0] is row
            layout[1] = seat.substring(1, 3); // layout[1] is column
        }

        int row = 99;
        int [] renum = new int[2];
        int column = Integer.parseInt(layout[1]) - 1;
        
        // To get row from input seat
        switch(layout[0]) {
            case "A" :
                row = 0;
                break;
            case "B" :
                row = 1;
                break;
            case "C" :
                row = 2;
                break;
            case "D" :
                row = 3;
                break;
            case "E" :
                row = 4;
                break;
            case "F" :
                row = 5;
                break;
        }
        renum[0] = row;
        renum[1] = column;
        return renum;
    }
    
//    public int convertSeatNameToColumn(String seat) {
//        String[] layout = new String[3];
//        if(seat.length() == 2) {
//            layout[0] = seat.substring(0, 1); // layout[0] is row
//            layout[1] = seat.substring(1, 2); // layout[1] is column
//        }
//        else if(seat.length() == 3) {
//            layout[0] = seat.substring(0, 1); // layout[0] is row
//            layout[1] = seat.substring(1, 3); // layout[1] is column
//        }
//
//        // Change string number to integer
//        int column = Integer.parseInt(layout[1]) - 1;
//
//        return column;
//    }
    
    public double getSeatPrice(String seat) {
        int [] re = new int[2];
        re = convertSeatName(seat);
        return seatLayout[re[0]][re[1]].getSeatPrice();
        
    }
    
    public void updateShowtime() {
        // showtimeIDList = ID of showtime in database.
    }
    
    public void editTheatre(int theatreNumber) {
        this.theatreNumber = theatreNumber;
    }
    
    public void editTheatre(int theatreNumber, String theatreSize) {
        this.theatreNumber = theatreNumber;
        this.theatreSize = theatreSize;
    }

    public int getTheatreNumber() {
        return this.theatreNumber;
    }

    public void setTheatreNumber(int theatreNumber) {
        this.theatreNumber = theatreNumber;
    }

    public String getTheatreSize() {
        return theatreSize;
    }

    public void setTheatreSize(String theatreSize) {
        this.theatreSize = theatreSize;
    }

    public List<Showtime> getShowtimeIDList() {
        return showtimeIDList;
    }

    public void getSeatLayout() {
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 20; j++) {
                String s = "[" + i + "]" + "[" + j + "] ";
                System.out.print(s);
            }
            System.out.println();
        }
    }
    
    @Override
    public String toString() {
        return "Theatre number : " + this.theatreNumber + ", Theatre size : " + this.theatreSize;
    }
}

